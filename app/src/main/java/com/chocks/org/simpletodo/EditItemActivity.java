package com.chocks.org.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import static com.chocks.org.simpletodo.R.id.etSaveItem;

public class EditItemActivity extends AppCompatActivity {

    private Integer position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        String text = getIntent().getStringExtra("text");
        position = getIntent().getIntExtra("position", position);
        EditText etEditItem = (EditText) findViewById(etSaveItem);
        etEditItem.setText(text);
    }

    public void onSaveItem(View v) {
        EditText etName = (EditText) findViewById(etSaveItem);
        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("text", etName.getText().toString());
        data.putExtra("position", position);
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }
}
