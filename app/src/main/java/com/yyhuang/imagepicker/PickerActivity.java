package com.yyhuang.imagepicker;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import androidx.annotation.Nullable;

public class PickerActivity extends Activity{
    private static final String TAG = "PickerActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);

        ContentResolver resolver = getContentResolver();
        Uri ImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor query = resolver.query(ImageUri,null,null,null,null);
        String[] columnNames = query.getColumnNames();
        while (query.moveToNext()) {
            Log.d(TAG, "==============");
            for (String columnName : columnNames) {
                Log.d(TAG, columnName + "===" + query.getString(query.getColumnIndex(columnName)));
            }
            Log.d(TAG, "==============");
        }
        query.close();
    }
}
