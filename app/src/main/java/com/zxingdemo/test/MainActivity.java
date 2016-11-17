package com.zxingdemo.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends Activity implements View.OnClickListener {
    Button btn_start,btn_create;
    TextView tv_result;
    EditText et_text;
    ImageView iv_result;
    CheckBox cb_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_create = (Button) findViewById(R.id.btn_create);
        btn_start.setOnClickListener(this);
        btn_create.setOnClickListener(this);
        tv_result = (TextView) findViewById(R.id.tv_result);
        et_text = (EditText) findViewById(R.id.et_text);
        iv_result = (ImageView) findViewById(R.id.iv_result);
        cb_logo= (CheckBox) findViewById(R.id.cb_logo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            tv_result.setText(result);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                startActivityForResult(new Intent(MainActivity.this,
                        CaptureActivity.class), 0);
                break;
            case R.id.btn_create:
                String input = et_text.getText().toString();
                if (input.equals("")) {
                    Toast.makeText(MainActivity.this, "输入不能为空！", Toast.LENGTH_LONG).show();
                } else {
                    Bitmap bitmap = EncodingUtils.createQRCode(input, 600, 600,
                            cb_logo.isChecked()? BitmapFactory.decodeResource
                                    (getResources(),R.mipmap.ic_launcher):null);
                    iv_result.setImageBitmap(bitmap);
                }
                break;
        }
    }
}

