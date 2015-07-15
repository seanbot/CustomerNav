package com.statefarm.customernavigator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sean on 7/15/2015.
 */
public class CustomerAdapter extends BaseAdapter {
    ArrayList<Customer> custList;
    Context context;

    public CustomerAdapter(ArrayList<Customer> customerList, Context context) {
        super();
        this.custList = customerList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return custList.size();
    }

    @Override
    public Object getItem(int position) {
        return custList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        if (row == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = mInflater.inflate(R.layout.customer_list_element, null);
            final TextView tvCustomer = (TextView) row.findViewById(R.id.tvDetailName);
            final TextView tvPriority = (TextView) row.findViewById(R.id.tvPriority);

            String customerName = custList.get(position).getName();
            String customerPriority = custList.get(position).getPriority();
            tvCustomer.setText(customerName);
            tvPriority.setText(customerPriority);
        }
        return row;
    }
}
