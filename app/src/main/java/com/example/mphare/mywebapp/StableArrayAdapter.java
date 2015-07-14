package com.example.mphare.mywebapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by mphare on 6/13/2015.
 */
public class StableArrayAdapter extends ArrayAdapter<String>
{

  private final Context  context;
  private final String[] uriValues;

  public StableArrayAdapter(Context context, String[] uriValues)
  {
    super(context, -1, uriValues);
    this.context = context;
    this.uriValues = uriValues;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent)
  {
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.uri_list_item_layout, parent, false);
    TextView textView = (TextView) rowView.findViewById(R.id.uri_list_value);
    textView.setText(uriValues[position]);

    return rowView;
  }
}
