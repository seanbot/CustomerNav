package com.statefarm.customernavigator;

import android.app.Application;
import android.graphics.Color;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Sean on 7/15/2015.
 */
public class MainApplication extends Application {

    private static MainApplication singleton;
    private ArrayList<Customer> customerArrayList;

    public static MainApplication getInstance(){
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // TODO: Create new ArrayList and popupalte the data... append to the customerArrayList;
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset("dummy.json"));
            JSONArray m_jArry = obj.getJSONArray("customers");
            customerArrayList = new ArrayList();
            for (int i = 0; i < m_jArry.length(); i++) {
                Customer cust = new Customer();
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                cust.setName(jo_inside.getString("name"));
                cust.setGender(jo_inside.getString("sex"));
                cust.setPriority(jo_inside.getString("priority"));
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
                customerArrayList.add(cust);
                Log.d("name -->", cust.getName());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        singleton = this;
    }

    public ArrayList<Customer> getCustomerArrayList() {
        return customerArrayList;
    }

    public void setCustomerArrayList(ArrayList<Customer> customerArrayList) {
        this.customerArrayList = customerArrayList;
    }

    public int getColorDependingOnPriority(String priority){
        if(priority.toUpperCase().contentEquals("HIGH")) {
            return Color.RED;
        } else if (priority.toUpperCase().contentEquals("LOW")){
            return Color.GREEN;
        } else if (priority.toUpperCase().contentEquals("MEDIUM")) {
            return Color.YELLOW;
        } else return Color.BLACK;
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
