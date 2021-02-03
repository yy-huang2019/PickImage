package com.yyhuang.imagepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button pickImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pickImage = this.findViewById(R.id.pickImage);
        initListener();
    }

    private void initListener() {
        pickImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == pickImage){
            //TODO:打开另外一个界面

        }
    }


}