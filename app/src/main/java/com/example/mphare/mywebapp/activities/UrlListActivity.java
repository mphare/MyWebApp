package com.example.mphare.mywebapp.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mphare.mywebapp.R;
import com.example.mphare.mywebapp.StableArrayAdapter;

import java.util.ArrayList;

public class UrlListActivity extends ActionBarActivity
{

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_url_list);

    final ListView listView = (ListView) findViewById(R.id.url_list_view);
    String[] values = new String[]{"192.168.1.13:8080", "192.168.43.141:8080"};
    final ArrayList<String> list = new ArrayList<String>();

    for (int i = 0; i < values.length; ++i)
    {
      list.add(values[i]);
    }
    final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
    listView.setAdapter(adapter);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      @Override
      public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
      {
        final String item = (String) parent.getItemAtPosition(position);
        view.animate().setDuration(2000).alpha(0).withEndAction(new Runnable()
        {
          @Override public void run()
          {
            list.remove(item);
            adapter.notifyDataSetChanged();
            view.setAlpha(1);
          }
        });
      }
    });

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_url_list, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings)
    {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}


