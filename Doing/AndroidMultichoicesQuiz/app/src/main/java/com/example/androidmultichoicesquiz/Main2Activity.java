package com.example.androidmultichoicesquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button = (Button) findViewById(R.id.btn_bacajuzamma);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });

        button = (Button) findViewById(R.id.btn_tajwid);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain2();
            }
        });
    }

    public void openMain()  {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openMain2()  {
        Intent intent = new Intent(this, Contact.class);
        startActivity(intent);
    }


}
