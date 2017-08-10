package com.violin.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.violin.recyclerviewdemo.demo.GridActivity;
import com.violin.recyclerviewdemo.demo.LinearActivity;
import com.violin.recyclerviewdemo.demo.StaggeredActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {



        Button linear = (Button) findViewById(R.id.bt_liner);
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearActivity.launch(v.getContext());
            }
        });

        Button grid = (Button) findViewById(R.id.bt_grid);
        grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GridActivity.launch(v.getContext());
            }
        });

        final Button staggered= (Button) findViewById(R.id.bt_staggered);

        staggered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaggeredActivity.launch(v.getContext());
            }
        });


    }
}
