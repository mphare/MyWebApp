package com.example.mphare.mywebapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends ActionBarActivity implements View.OnClickListener
{

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    findViewById(R.id.my_button).setOnClickListener(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    // return super.onCreateOptionsMenu(menu);
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
      openSettings();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private void openSettings()
  {
    Intent intent = new Intent(this, SettingsActivity.class);
    startActivity(intent);

  }

  @Override public void onClick(View v)
  {
    Button b = (Button) findViewById(R.id.my_button);
    b.setClickable(false);
    new LongRunningGetIO().execute();

  }

  private class LongRunningGetIO extends AsyncTask<Void, Void, String>
  {
    protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException
    {
      InputStream in = entity.getContent();
      StringBuffer out = new StringBuffer();
      int n = 1;
      while (n > 0)
      {
        byte[] b = new byte[4096];
        n = in.read(b);
        if (n > 0)
        { out.append(new String(b, 0, n)); }
      }
      return out.toString();
    }

    @Override
    protected String doInBackground(Void... params)
    {
      HttpClient httpClient = new DefaultHttpClient();
      HttpContext localContext = new BasicHttpContext();
      // Cannot use 'localhost' here, remember?
      // Must use the IP Address of the local host instead
      String urlValue = "http://192.168.1.13:8080";
      HttpGet httpGet = new HttpGet(urlValue + "/VogelREST/rest/hello/arg?idx=5");
      String text = null;

      try
      {
        HttpResponse response = httpClient.execute(httpGet, localContext);
        HttpEntity entity = response.getEntity();
        text = getASCIIContentFromEntity(entity);
      } catch (Exception e)
      {
        return e.getLocalizedMessage();
      }
      return text;
    }

    protected void onPostExecute(String results)
    {
      if (results != null)
      {
        EditText et = (EditText) findViewById(R.id.my_edit);
        et.setText(results);
      }

      Button b = (Button) findViewById(R.id.my_button);
      b.setClickable(true);
    }
  }
}
