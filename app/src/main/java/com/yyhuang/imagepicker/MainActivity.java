package com.yyhuang.imagepicker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yyhuang.imagepicker.Adapter.ResultImageAdapter;
import com.yyhuang.imagepicker.domain.ImageItem;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, PickerConfig.OnImageSelectedFinishListener {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private Button pickImage;
    private final static int MAX_SELECTED_COUNT = 9;
    private PickerConfig pickerConfig;
    private RecyclerView resultList;
    private ResultImageAdapter resultImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取外部扩展卡的权限
        checkPermission();
        initView();
        initPickerConfig();
        pickImage = this.findViewById(R.id.pickImage);
        initListener();
    }

    private void initView() {
        resultList = this.findViewById(R.id.result_list);

        resultImageAdapter = new ResultImageAdapter();
        resultList.setAdapter(resultImageAdapter);
    }

    private void initPickerConfig() {
        pickerConfig = PickerConfig.getInstance();
        pickerConfig.setMaxSelectedCount(MAX_SELECTED_COUNT);

        pickerConfig.setOnImageSelectedFinishListener(this);
    }

    private void checkPermission() {
        int permission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        if(permission != PackageManager.PERMISSION_GRANTED){
            //没有权限
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == PERMISSION_REQUEST_CODE) {
//            if (grantResults.length == 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                //有权限
//
//            }else{
//                //没有权限
//                //根据交互去处理
//
//            }
//        }
//    }

    private void initListener() {
        pickImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == pickImage){
            // 打开另外一个界面
            Intent intent = new Intent();
            intent.setClass(this,PickerActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onSelected(List<ImageItem> result) {
        //所选择的图片列表展示出来
        int horizontalCount = 3;
        resultList.setLayoutManager(new GridLayoutManager(this,horizontalCount));
        resultImageAdapter.setData(result,horizontalCount);
    }
}