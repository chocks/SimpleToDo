package com.chocks.org.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;

import static com.chocks.org.simpletodo.R.id.etAddItem;

public class MainActivity extends AppCompatActivity {
    static final int EDIT_REQUEST = 2903;
    ArrayList<Items> items;
    ItemsAdapter itemsAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FlowManager.init(new FlowConfig.Builder(this).build());
        setContentView(R.layout.activity_main);
        readItems();
        itemsAdapter = new ItemsAdapter(this, items);
        lvItems = (ListView)findViewById(R.id.lvItems);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
        setupEditViewListener();
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(etAddItem);
        Items itemText = new Items();
        itemText.text = etNewItem.getText().toString();
        itemsAdapter.add(itemText);
        etNewItem.setText("");
        itemText.save();
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter,
                                           View item, int pos, long id) {
                Items it = items.get(pos);
                it.delete();
                items.remove(pos);
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    private void setupEditViewListener() {
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
                intent.putExtra("text", items.get(position).text);
                intent.putExtra("position", position);
                startActivityForResult(intent, EDIT_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EDIT_REQUEST && resultCode == RESULT_OK) {
            String text = data.getExtras().getString("text");
            int position = data.getExtras().getInt("position", 0);
            Items newItem = new Items();
            long id = items.get(position).id;
            newItem.id = id;
            newItem.text = text;
            items.set(position, newItem);
            newItem.update();
            newItem.save();
            itemsAdapter.notifyDataSetChanged();
            items.get(position).update();
        }
    }

    private void readItems() {
        items = new ArrayList<>(SQLite.select().from(Items.class).queryList());
    }
}