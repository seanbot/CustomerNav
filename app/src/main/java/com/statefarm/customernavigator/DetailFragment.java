package com.statefarm.customernavigator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sean on 7/15/2015.
 */
public class DetailFragment extends Fragment {
    Customer customer;

    public DetailFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_detail, container, false);
        TextView tvName = (TextView) v.findViewById(R.id.tvDetailName);
        TextView tvPriority = (TextView) v.findViewById(R.id.tvDetailPriority);

        Bundle bundle = getArguments();
        tvName.setText(bundle.getString("name"));
        tvPriority.setText(bundle.getString("priority"));
        tvPriority.setTextColor(MainApplication.getInstance().getColorDependingOnPriority(bundle.getString("priority")));

        return v;
    }

}
