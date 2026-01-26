package com.example.simpletodolist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtTask;
    Button btnAdd;
    ListView listViewTasks;
    ArrayList<String> taskList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        edtTask = findViewById(R.id.edtTask);
//        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);

        listViewTasks = findViewById(R.id.listViewTasks);

        // Initialize ArrayList and Adapter
        taskList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        listViewTasks.setAdapter(adapter);

        // Add Button Click using lambda
        btnAdd.setOnClickListener(v -> {
            String task = edtTask.getText().toString().trim();
            if(!task.isEmpty()) {
                taskList.add(task);
                adapter.notifyDataSetChanged(); // refresh ListView
                edtTask.setText("");            // clear EditText
                Toast.makeText(MainActivity.this, "Task Added!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Please enter a task!", Toast.LENGTH_SHORT).show();
            }
        });

        // Remove task on click
        listViewTasks.setOnItemClickListener((parent, view, position, id) -> {
            String removedTask = taskList.get(position);
            taskList.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, removedTask + " removed!", Toast.LENGTH_SHORT).show();
        });
    }
}
