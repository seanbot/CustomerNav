package com.statefarm.customernavigator;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends FragmentActivity implements MainActivityFragment.OnCustomerSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("herpderp", loadJSONFromAsset("customers.json"));

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            MainActivityFragment listFragment = new MainActivityFragment();

            listFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, listFragment).commit();
        }
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu items for use in the action bar
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_main, menu);
//        return super.onCreateOptionsMenu(menu);
//    }


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

    public String loadJSONFromAsset(String filename) {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open(filename);
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
