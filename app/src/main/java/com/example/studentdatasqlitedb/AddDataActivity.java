package com.example.studentdatasqlitedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDataActivity extends AppCompatActivity {

    DBHelper mydb;

    EditText editRollnum;
    EditText editName;
    EditText editEmail;
    EditText editPhone;
    Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        setTitle("Adding Student Data");

        mydb = new DBHelper(this);
        editRollnum = findViewById(R.id.edit_RollNumber);
        editName = findViewById(R.id.edit_Name);
        editEmail = findViewById(R.id.edit_Email);
        editPhone = findViewById(R.id.edit_Phone);
        buttonAdd = findViewById(R.id.button_AddData);
        AddData();
    }
    // Adding data.
    public void AddData(){

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = mydb.insertData(editRollnum.getText().toString(),
                        editName.getText().toString(),
                        editEmail.getText().toString(),
                        editPhone.getText().toString());

                if (isInserted = true){
                    Toast.makeText(AddDataActivity.this,"DATA ADDED", Toast.LENGTH_SHORT).show();
                    editRollnum.getText().clear();
                    editName.getText().clear();
                    editEmail.getText().clear();
                    editPhone.getText().clear();
                }else {
                    Toast.makeText(AddDataActivity.this,"DATA NOT ADDED", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
