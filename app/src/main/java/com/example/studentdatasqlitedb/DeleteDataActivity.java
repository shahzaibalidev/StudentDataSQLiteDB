package com.example.studentdatasqlitedb;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteDataActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DBHelper mydb;
    TextView ViewName, ViewEmail, ViewPhone;
    Button buttonDeleteData;
    Spinner sp;
    String spSelectedDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_data);
        setTitle("Delete Student Data");
        mydb = new DBHelper(this);

        ViewName = findViewById(R.id.view_Name);
        ViewEmail = findViewById(R.id.view_Email);
        ViewPhone = findViewById(R.id.view_Phone);
        buttonDeleteData = findViewById(R.id.button_DeleteData);
        sp = findViewById(R.id.spinnerDelete);
        spinnerData();
       DeleteData();
    }
    // RollNumber List Viewer.
    public void spinnerData(){
        ArrayList<String> namelist=mydb.getRollNumberLists();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, namelist);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //selected item from spinner.
        spSelectedDel = parent.getItemAtPosition(position).toString();
        Toast.makeText(this,spSelectedDel, Toast.LENGTH_SHORT).show();
        //send value in dbhelper.class
        mydb.spinnervalue = spSelectedDel;
        //getting values.
        Cursor res = mydb.getRollNumberData();
        while (res.moveToNext()){

            ViewName.setText(res.getString(2));
            ViewEmail.setText(res.getString(3));
            ViewPhone.setText(res.getString(4));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void DeleteData(){
        buttonDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer DeletedRows = mydb.DeleteData(spSelectedDel);
                if (DeletedRows > 0){
                    Toast.makeText(DeleteDataActivity.this,"DATA Deleted", Toast.LENGTH_SHORT).show();
                   /* editName.getText().clear();
                    editEmail.getText().clear();
                    editPhone.getText().clear(); */
                    spinnerData();
                }else {
                    Toast.makeText(DeleteDataActivity.this,"DATA NOT Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
