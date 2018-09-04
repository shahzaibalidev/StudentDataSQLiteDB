package com.example.studentdatasqlitedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button AddAct, ViewAct, EditAct, DeleteAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Student Database App");

        AddAct = findViewById(R.id.but_AddData_Act);
        AddAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, AddDataActivity.class);
                startActivity(i);

            }
        });

        ViewAct = findViewById(R.id.but_ViewData_Act);
        ViewAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, ViewDataActivity.class);
                startActivity(i);

            }
        });

        EditAct = findViewById(R.id.but_EditData_Act);
        EditAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, EditDataActivity.class);
                startActivity(i);
            }
        });

        DeleteAct = findViewById(R.id.but_DeleteData_Act);
        DeleteAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DeleteDataActivity.class);
                startActivity(i);
            }
        });

    }


}
