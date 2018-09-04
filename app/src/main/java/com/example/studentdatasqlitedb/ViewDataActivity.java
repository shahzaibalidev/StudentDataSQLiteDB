package com.example.studentdatasqlitedb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewDataActivity extends AppCompatActivity {

    DBHelper mydb;

    Button buttonViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        setTitle("View Student Data");
        mydb = new DBHelper(this);

        buttonViewData = findViewById(R.id.button_ViewData);
        ViewData();
    }

    public void ViewData(){

        buttonViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = mydb.getAllData();

                if (res.getCount() == 0){
                    showMessage("Error","No Data is found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){

                    buffer.append("ID: "+ res.getString(0)+"\n");
                    buffer.append("RollNumber: "+ res.getString(1)+"\n");
                    buffer.append("Name: "+ res.getString(2)+"\n");
                    buffer.append("Email: "+ res.getString(3)+"\n");
                    buffer.append("Phone: "+ res.getString(4)+"\n\n");
                }

                showMessage("Data", buffer.toString());
            }


        });


    }

    public void showMessage(String tital,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(tital);
        builder.setMessage(message);
        builder.show();

    }
}
