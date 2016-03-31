package com.aurum.models;

import android.widget.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 2/3/16.
 */
public class Brand {

    public String brandName;
    public List<Mobile> mobiles = new ArrayList<Mobile>();

    public Brand(String brandName, List<Mobile> mobiles) {
        this.brandName = brandName;
        this.mobiles = mobiles;
    }

}
