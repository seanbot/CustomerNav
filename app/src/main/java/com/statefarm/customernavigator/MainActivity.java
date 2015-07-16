package com.statefarm.customernavigator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;



public class MainActivity extends ActionBarActivity implements MainActivityFragment.OnCustomerSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            MainActivityFragment listFragment = new MainActivityFragment();

            listFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, listFragment).commit();
        }
    }


    @Override
    public void onCustomerSelected(int position) {
        //call the new fragment with the position of the item in the list.
        DetailFragment detailFrag = new DetailFragment();
        Bundle bundle = new Bundle();
        Customer c = MainApplication.getInstance().getCustomerArrayList().get(position);
        bundle.putString(Constants.NAME, c.getName());
        bundle.putInt(Constants.AGE, c.getAge());
        bundle.putString(Constants.TYPE, c.getCustomerSegment());
        bundle.putString(Constants.PRIORITY, c.getPriority());
        bundle.putString(Constants.ADDRESS, c.getAddress());
        bundle.putString(Constants.CITYSTATE, c.getCityState());
        bundle.putString(Constants.DOB, c.getDob());
        bundle.putString(Constants.PHONE, c.getPhone());
        bundle.putString(Constants.EMAIL, c.getEmail());
        bundle.putString(Constants.POLICIES, c.getPolicies());
        bundle.putInt(Constants.PREMIUM, c.getPremium());
        bundle.putInt(Constants.HOUSESIZE, c.getHouseholdSize());
        bundle.putString(Constants.TIMEWITHSF, c.getTimeWithStateFarm());
        bundle.putInt(Constants.NUMCLAIMS, c.getNumClaims());
        bundle.putString(Constants.RATEINCREASETIME, c.getRateIncreaseTime());
        bundle.putString(Constants.RATEINCREASEPERCENT, c.getRateIncreasePercent());
        bundle.putString(Constants.SHOPPING, c.getIsShopping());
        detailFrag.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, detailFrag).addToBackStack(null).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Menu options to set and cancel the alarm.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.seach_action:
                //handle the search action
                return true;
        }
        return false;
    }


}
