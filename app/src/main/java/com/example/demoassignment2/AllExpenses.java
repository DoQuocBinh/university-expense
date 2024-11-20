package com.example.demoassignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import database.DatabaseHelper;
import database.ExpenseEntity;

public class AllExpenses extends AppCompatActivity {
    List<ExpenseEntity> allExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_expenses);

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());

        allExpense = dbHelper.getAllExpenses();
        ArrayAdapter adapter = new ArrayAdapter<ExpenseEntity>(this,
                R.layout.activity_listview, allExpense);
        ListView listView = (ListView) findViewById(R.id.listExpense);
        listView.setAdapter(adapter);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.newExpense) {
            Intent intent = new Intent(AllExpenses.this,NewExpene.class);
            startActivity(intent);

        }
        else if (item.getItemId()== R.id.listExpense) {
            Toast.makeText(getApplicationContext(),"You are here already!",Toast.LENGTH_LONG).show();
        }
        return true;
    }
}