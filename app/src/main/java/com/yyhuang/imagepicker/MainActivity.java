package com.yyhuang.imagepicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private Button pickImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取外部扩展卡的权限
        checkPermission();

        pickImage = this.findViewById(R.id.pickImage);
        initListener();
    }

    private void checkPermission() {
        int permission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        if(permission != PackageManager.PERMISSION_GRANTED){
            //没有权限
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length == 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //有权限

            }else{
                //没有权限
                //根据交互去处理

            }
        }
    }

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


}