package com.yyhuang.imagepicker;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yyhuang.imagepicker.Adapter.ImageListAdapter;
import com.yyhuang.imagepicker.domain.ImageItem;

import java.util.ArrayList;
import java.util.List;

public class PickerActivity extends AppCompatActivity {
    private static final String TAG = "PickerActivity";
    private static final int LOADER_ID = 1;
    private List<ImageItem> mImageItems = new ArrayList<>();
    private ImageListAdapter imageListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);
        initLoadManage();
        initView();

//        ContentResolver resolver = getContentResolver();
//        Uri ImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//        Cursor query = resolver.query(ImageUri,null,null,null,null);
//        String[] columnNames = query.getColumnNames();
//        while (query.moveToNext()) {
//            Log.d(TAG, "==============");
//            for (String columnName : columnNames) {
//                Log.d(TAG, columnName + "===" + query.getString(query.getColumnIndex(columnName)));
//            }
//            Log.d(TAG, "==============");
//        }
//        query.close();

    }

    private void initView() {
        //找到控件
        RecyclerView listView = this.findViewById(R.id.result_list);
        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        listView.setLayoutManager(gridLayoutManager);
        //设置适配器
        imageListAdapter = new ImageListAdapter();
        listView.setAdapter(imageListAdapter);
    }

    private void initLoadManage() {
        LoaderManager loaderManager = LoaderManager.getInstance(this);

        //加载时清除数据
        mImageItems.clear();

        loaderManager.initLoader(LOADER_ID, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            @NonNull
            @Override
            public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
                if (id == LOADER_ID) {
                    return new CursorLoader(PickerActivity.this, MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            new String[]{"_data","_display_name","date_added"},
                            null,null,"date_added DESC");
                }
                return null;
            }

            @Override
            public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
                if (cursor != null) {
                    String[] columnNames = cursor.getColumnNames();
                    while (cursor.moveToNext()) {

                        //获取所有数据信息
//                        Log.d(TAG, "==============");
//                        for (String columnName : columnNames) {
//                            Log.d(TAG, columnName + "===" + cursor.getString(cursor.getColumnIndex(columnName)));
//                        }
//                        Log.d(TAG, "==============");

                        String path = cursor.getString(0);
                        Log.d(TAG,"path------"+cursor.getString(0));

                        String title = cursor.getString(1);
                        Log.d(TAG,"title------"+cursor.getString(1));
                        long date = cursor.getLong(2);
                        ImageItem imageItem = new ImageItem(date,title,path);
                        mImageItems.add(imageItem);
                    }

                    imageListAdapter.setData(mImageItems);

                    cursor.close();
//                    for (ImageItem mImageItem : mImageItems) {
//                        Log.d(TAG,"image===>>>"+mImageItem);
//                    }
                    }
                }

            @Override
            public void onLoaderReset(@NonNull Loader<Cursor> loader) {

            }
        });
    }
}
