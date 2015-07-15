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
            JSONObject obj = new JSONObject(loadJSONFromAsset("dummy.json"));
            JSONArray m_jArry = obj.getJSONArray("customers");

            ArrayList<Customer> customers = new ArrayList<Customer>();
            for (int i = 0; i < m_jArry.length(); i++) {
                Customer cust = new Customer();
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                cust.setName(jo_inside.getString("name"));
                cust.setGender(jo_inside.getString("sex"));
                cust.setDob(jo_inside.getString("dob"));
                cust.setHouseholdSize(jo_inside.getString("householdsize"));
                cust.setNumClaims(jo_inside.getString("claims"));
                cust.setPolicy1(jo_inside.getString("policy1"));
                cust.setPolicy2(jo_inside.getString("policy2"));
                cust.setPolicy3(jo_inside.getString("policy3"));
                cust.setPremium1(jo_inside.getString("premium1"));
                cust.setPremium2(jo_inside.getString("premium2"));
                cust.setPremium3(jo_inside.getString("premium3"));
                cust.setIsShopping(jo_inside.getString("shopping"));
                cust.setCustomerSegment(jo_inside.getString("type"));
                customers.add(cust);
                Log.d("name -->", cust.getName());
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
