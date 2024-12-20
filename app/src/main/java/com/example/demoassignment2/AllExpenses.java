package com.example.demoassignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ExpenseEntity entry = (ExpenseEntity) parent.getItemAtPosition(position);
                final String[]  options = {"Delete","Update"};
                AlertDialog.Builder builder = new AlertDialog.Builder(AllExpenses.this);
                builder.setItems(options,(dialog,item)->{
                    if (options[item]=="Delete"){
                        //1/Remove from the ListView
                        allExpense.remove(position);
                        adapter.notifyDataSetChanged();
                        //2.Remove from database
                        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
                        dbHelper.deleteExpense(entry.getId());
                    }else if(options[item]=="Update"){
                        Snackbar.make(view, "Update", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });
                builder.show();
            }
        });
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