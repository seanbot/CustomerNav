package com.statefarm.customernavigator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        TextView tvAddress1 = (TextView) v.findViewById(R.id.tvAddressOne);
        TextView tvAddress2 = (TextView) v.findViewById(R.id.tvAddressTwo);
        TextView tvPhone = (TextView) v.findViewById(R.id.tvPhone);

        Bundle bundle = getArguments();
        tvName.setText(bundle.getString(Constants.NAME));
        tvPriority.setText(bundle.getString(Constants.PRIORITY));
        tvPriority.setTextColor(MainApplication.getInstance().getColorDependingOnPriority(bundle.getString(Constants.PRIORITY)));
        tvAddress1.setText(bundle.getString(Constants.ADDRESS));
        tvAddress2.setText(bundle.getString(Constants.CITYSTATE));
        tvPhone.setText(bundle.getString(Constants.PHONE));
        return v;
    }

}
