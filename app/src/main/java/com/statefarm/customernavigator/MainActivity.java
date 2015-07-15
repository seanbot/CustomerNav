package com.statefarm.customernavigator;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends FragmentActivity implements MainActivityFragment.OnCustomerSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset("customers.json"));
            JSONArray m_jArry = obj.getJSONArray("formules");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Log.d("Details-->", jo_inside.getString("formule"));
                String formula_value = jo_inside.getString("formule");
                String url_value = jo_inside.getString("url");

                //Add your values in your `ArrayList` as below:
                m_li = new HashMap<String, String>();
                m_li.put("formule", formula_value);
                m_li.put("url", url_value);

                formList.add(m_li);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
