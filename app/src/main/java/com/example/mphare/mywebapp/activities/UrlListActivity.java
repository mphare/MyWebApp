package com.example.mphare.mywebapp.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mphare.mywebapp.R;
import com.example.mphare.mywebapp.ServerObject;

import java.util.ArrayList;
import android.util.Log;

public class UrlListActivity extends ActionBarActivity
{

  ArrayList<ServerObject> serverObjectArrayList = new ArrayList<ServerObject>();

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_url_list);

    String[] myStrings = getResources().getStringArray(R.array.server_uri_array);

    for (String url : myStrings) {

      Log.d("String Resource","URL: "+url);
      ServerObject so = new ServerObject(url);
      serverObjectArrayList.add(so);

    }

    
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
