package com.chocks.org.simpletodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Chocks on 2/7/17.
 */

public class ItemsAdapter extends ArrayAdapter<Items> {
    public ItemsAdapter(Context context, ArrayList<Items> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Items item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_main, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        // Populate the data into the template view using the data object
        tvName.setText(item.text);
        // Return the completed view to render on screen
        return convertView;
    }
}