package com.example.rozin.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // get the categories
        CategoriesRequest categoriesRequest = new CategoriesRequest(this);
        categoriesRequest.getCategories(this);
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {

        // if you have gotten the categories, put them in your listview
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,categories);
        ListView listview = findViewById(R.id.categoriesList);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new ListItemClickListener());
    }

    @Override
    public void gotCategoriesError(String message) {

        // print an errormessage in case of an error
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, message, duration);
        toast.show();
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            // get the category you clicked on
            String clickedCategory = (String) adapterView.getItemAtPosition(i);

            // start MenuActivity and remember the category
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("category", clickedCategory);
            startActivity(intent);
        }
    }
}
