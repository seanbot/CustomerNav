package com.statefarm.customernavigator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ImageView;

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
        ImageView iv= (ImageView) v.findViewById(R.id.imageView);
        TextView tvName = (TextView) v.findViewById(R.id.tvDetailName);
        TextView tvPriority = (TextView) v.findViewById(R.id.tvDetailPriority);
        TextView tvType = (TextView) v.findViewById(R.id.tvType);
        TextView tvAddress1 = (TextView) v.findViewById(R.id.tvAddressOne);
        TextView tvAddress2 = (TextView) v.findViewById(R.id.tvAddressTwo);
        TextView tvPhone = (TextView) v.findViewById(R.id.tvPhone);
        TextView tvEmail = (TextView) v.findViewById(R.id.tvEmail);
        TextView tvPrice = (TextView) v.findViewById(R.id.tvPrice);
        TextView tvIsShopping = (TextView) v.findViewById(R.id.tvIsShopping);
        TextView tvPendingRateIncrease = (TextView) v.findViewById(R.id.tvPendingRateIncrease);
        TextView tvAge = (TextView) v.findViewById(R.id.tvAge);
        TextView tvHouseholdSize = (TextView) v.findViewById(R.id.tvHouseHoldSize);
        TextView tvNumProducts = (TextView) v.findViewById(R.id.tvNumOfProducts);
        TextView tvNumClaims = (TextView) v.findViewById(R.id.tvNumOfClaims);
        TextView tvTimeWithSF = (TextView) v.findViewById(R.id.tvYearsWithSF);
        ProgressBar pb = (ProgressBar) v.findViewById(R.id.progressBar);


        Bundle bundle = getArguments();
        tvName.setText(bundle.getString(Constants.NAME));
        tvPriority.setText(bundle.getString(Constants.PRIORITY));
        tvType.setText(bundle.getString(Constants.TYPE));
        tvPriority.setTextColor(MainApplication.getInstance().getColorDependingOnPriority(bundle.getString(Constants.PRIORITY)));
        tvAddress1.setText(bundle.getString(Constants.ADDRESS));
        tvAddress2.setText(bundle.getString(Constants.CITYSTATE));
        tvPhone.setText(bundle.getString(Constants.PHONE));
        tvEmail.setText(bundle.getString(Constants.EMAIL));
        tvPrice.setText("Price: $" + bundle.getInt(Constants.PREMIUM));
        tvIsShopping.setText(bundle.getString(Constants.SHOPPING));
        tvIsShopping.setTextColor(MainApplication.getInstance().getColorDependingOnShopping(bundle.getString(Constants.SHOPPING)));
        String rateIncreaseTime = bundle.getString(Constants.RATEINCREASETIME);
        if (rateIncreaseTime.contentEquals("none")) {
            tvPendingRateIncrease.setText("none");
        }
        else {
            tvPendingRateIncrease.setText(rateIncreaseTime + " " + bundle.getString(Constants.RATEINCREASEPERCENT) + "%");
        }
        tvPendingRateIncrease.setTextColor(MainApplication.getInstance().getColorDependingOnRateIncrease(rateIncreaseTime));
        tvAge.setText("Age: " + bundle.getInt(Constants.AGE)+"");
        tvHouseholdSize.setText("Household Size: " + bundle.getInt(Constants.HOUSESIZE)+"");
        tvNumProducts.setText("Products: " + bundle.getString(Constants.POLICIES));
        tvNumClaims.setText("# of Claims: " + bundle.getInt(Constants.NUMCLAIMS)+"");
        tvTimeWithSF.setText(bundle.getString(Constants.TIMEWITHSF));
        Integer householdSize = bundle.getInt(Constants.HOUSESIZE);
        if (householdSize > 1) {
            iv.setImageResource(R.drawable.family);
        }
        else {
            iv.setImageResource(R.drawable.ic_face_black_24dp);
        }        pb.setProgress(bundle.getInt(Constants.PREMIUM) / 10);
        return v;
    }

}
