package com.violin.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.violin.recyclerviewdemo.demo.GridActivity;
import com.violin.recyclerviewdemo.demo.LinearActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {



        Button addHeader = (Button) findViewById(R.id.bt_liner);
        addHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearActivity.launch(v.getContext());
            }
        });

        Button addFooter = (Button) findViewById(R.id.bt_grid);
        addFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GridActivity.launch(v.getContext());
            }
        });


    }
}
