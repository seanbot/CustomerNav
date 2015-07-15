package com.statefarm.customernavigator;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_main, container, false);
        ListView listview = (ListView) v.findViewById(R.id.customerListView);

        ArrayList<Customer> custList = new ArrayList();
        Customer c = new Customer();
        c.setName("Sean");
        c.setPriority("HIGH");
        custList.add(c);

        Customer d = new Customer();
        d.setName("Barb");
        d.setPriority("LOW");
        custList.add(d);

        listview.setAdapter(new CustomerAdapter(custList, getActivity()));

        return v;
    }
}
