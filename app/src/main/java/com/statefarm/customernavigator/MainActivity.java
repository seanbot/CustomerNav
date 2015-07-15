package com.statefarm.customernavigator;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends ActionBarActivity implements MainActivityFragment.OnCustomerSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.d("herpderp", loadJSONFromAsset("customers.json"));

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            MainActivityFragment listFragment = new MainActivityFragment();

            listFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, listFragment).commit();
        }
    }

    @Override
    public void onCustomerSelected(int position) {
        //call the new fragment with the position of the item in the list.
        DetailFragment detailFrag = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", MainApplication.getInstance().getCustomerArrayList().get(position).getName());
        bundle.putString("priority", MainApplication.getInstance().getCustomerArrayList().get(position).getPriority());
        detailFrag.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, detailFrag).addToBackStack(null).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Menu options to set and cancel the alarm.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.seach_action:
                //handle the search action
                return true;
        }
        return false;
    }


    public String loadJSONFromAsset(String filename) {
        String json = null;
        try {
            InputStream is = getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
