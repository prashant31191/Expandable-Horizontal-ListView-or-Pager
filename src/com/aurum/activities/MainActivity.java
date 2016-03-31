package com.aurum.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.aurum.R;
import com.aurum.adapters.BrandAdapter;
import com.aurum.models.Brand;
import com.aurum.models.Mobile;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("NewApi")
public class MainActivity extends AppCompatActivity {

    private ExpandableListView mBrandsListView;
    private ArrayList<Brand> brands = new ArrayList<Brand>();

    private ExpandableListAdapter mBrandAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBrandsListView = (ExpandableListView) findViewById(R.id.list_brands);

        data();

        mBrandAdapter = new BrandAdapter(this, brands);
        mBrandsListView.setAdapter(mBrandAdapter);

        mBrandsListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if ((previousGroup != -1) && (groupPosition != previousGroup)) {
                    mBrandsListView.collapseGroup(previousGroup);
                }
                previousGroup = groupPosition;
            }
        });

    }

    private void data() {
        List<Mobile> oneplusMobiles = new ArrayList<Mobile>();
        oneplusMobiles.add(new Mobile(R.drawable.oneplus_1, "OnePlus 1", "$250.00"));
        oneplusMobiles.add(new Mobile(R.drawable.oneplus_2, "OnePlus 2", "$349.00"));
        oneplusMobiles.add(new Mobile(R.drawable.oneplus_x, "OnePlus X", "$230.00"));
        oneplusMobiles.add(new Mobile(R.drawable.oneplus_1, "OnePlus 1", "$250.00"));
        oneplusMobiles.add(new Mobile(R.drawable.oneplus_2, "OnePlus 2", "$349.00"));
        oneplusMobiles.add(new Mobile(R.drawable.oneplus_x, "OnePlus X", "$230.00"));
        Brand oneplus = new Brand("OnePlus", oneplusMobiles);

        List<Mobile> nexusMobiles = new ArrayList<Mobile>();
        nexusMobiles.add(new Mobile(R.drawable.nexus_5, "Nexux 5", "$210.00"));
        nexusMobiles.add(new Mobile(R.drawable.nexus_5x, "Nexux 5x", "$250.00"));
        nexusMobiles.add(new Mobile(R.drawable.nexus_5x_r, "Nexux 5x R", "$270.00"));
        nexusMobiles.add(new Mobile(R.drawable.nexus_6p, "Nexux 6P", "$450.00"));;
        Brand nexus = new Brand("Google Nexus", nexusMobiles);

        
        List<Mobile> samsungMobiles = new ArrayList<Mobile>();
        samsungMobiles.add(new Mobile(R.drawable.nexus_5, "Nexux 5", "$210.00"));
        samsungMobiles.add(new Mobile(R.drawable.nexus_5x, "Nexux 5x", "$250.00"));
        nexusMobiles.add(new Mobile(R.drawable.nexus_5x_r, "Nexux 5x R", "$270.00"));
        samsungMobiles.add(new Mobile(R.drawable.nexus_6p, "Nexux 6P", "$450.00"));;
        Brand samsung = new Brand("Samsung", samsungMobiles);
        
        brands.add(oneplus);
        brands.add(nexus);
        brands.add(samsung);
       
    }
}
