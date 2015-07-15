package com.statefarm.customernavigator;

import android.app.Application;

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
        customerArrayList = new ArrayList();
        Customer c = new Customer();
        c.setName("Sean");
        c.setPriority("HIGH");
        customerArrayList.add(c);

        Customer d = new Customer();
        d.setName("Barb");
        d.setPriority("LOW");
        customerArrayList.add(d);
        singleton = this;
    }

    public ArrayList<Customer> getCustomerArrayList() {
        return customerArrayList;
    }

    public void setCustomerArrayList(ArrayList<Customer> customerArrayList) {
        this.customerArrayList = customerArrayList;
    }
}
