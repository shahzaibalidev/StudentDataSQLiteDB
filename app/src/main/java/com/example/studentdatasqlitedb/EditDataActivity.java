package com.example.studentdatasqlitedb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EditDataActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DBHelper mydb;
    Spinner sp;
    Button buttonUpdateData;
    EditText editName, editEmail, editPhone;
    String spSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        setTitle("Edit Student Data");
        mydb = new DBHelper(this);
        sp = findViewById(R.id.spinnerDelete);
        buttonUpdateData = findViewById(R.id.button_UpdateData);
        editName = findViewById(R.id.edit_Name);
        editEmail = findViewById(R.id.edit_Email);
        editPhone = findViewById(R.id.edit_Phone);
        spinnerData();
        updatedata();




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
        spSelected = parent.getItemAtPosition(position).toString();
        Toast.makeText(this,spSelected, Toast.LENGTH_SHORT).show();
        //send value in dbhelper.class
        mydb.spinnervalue = spSelected;
        //getting values.
       Cursor res = mydb.getRollNumberData();
        if (res.getCount() == 0){
            showMessage("Error","No Data is found");
            return;
        }
        while (res.moveToNext()){

            editName.setText(res.getString(2));
            editEmail.setText(res.getString(3));
            editPhone.setText(res.getString(4));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    //Error message box.
    public void showMessage(String tital,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(tital);
        builder.setMessage(message);
        builder.show();

    }
    //Update data in database.
    public void updatedata(){

        buttonUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = mydb.updatedata(spSelected, editName.getText().toString(), editEmail.getText().toString(),
                        editPhone.getText().toString());
                if (isUpdate == true){
                    Toast.makeText(EditDataActivity.this,"DATA ADDED", Toast.LENGTH_SHORT).show();
                   /* editName.getText().clear();
                    editEmail.getText().clear();
                    editPhone.getText().clear(); */
                }else {
                    Toast.makeText(EditDataActivity.this,"DATA NOT ADDED", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}
