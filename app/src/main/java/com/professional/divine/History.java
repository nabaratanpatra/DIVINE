package com.professional.divine;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class History extends AppCompatActivity {

    ListView lv_customerList;
    com.professional.divine.DataBaseHelper dataBaseHelper;
    ArrayAdapter alertArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        lv_customerList=findViewById(R.id.lv_custList);

        dataBaseHelper = new com.professional.divine.DataBaseHelper(com.professional.divine.History.this);
        ShowCustomersOnListView(dataBaseHelper);
    }

    private void ShowCustomersOnListView(com.professional.divine.DataBaseHelper dataBaseHelper) {
        alertArrayAdapter = new ArrayAdapter<AlertModel>(com.professional.divine.History.this, android.R.layout.simple_list_item_1, dataBaseHelper.getAllAlerts());
        if(alertArrayAdapter.isEmpty()){
            Toast.makeText(com.professional.divine.History.this,"No entries to show", Toast.LENGTH_SHORT).show();

        }
        else
        lv_customerList.setAdapter(alertArrayAdapter);
    }
}
