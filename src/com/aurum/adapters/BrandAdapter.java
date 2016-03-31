package com.aurum.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aurum.R;
import com.aurum.activities.MainActivity;
import com.aurum.activities.ViewPagerAdapter;
import com.aurum.models.Brand;

/**
 * Created by root on 2/3/16.
 */
public class BrandAdapter implements ExpandableListAdapter {

    private Context context;
    private List<Brand> brands = new ArrayList<Brand>();

    public BrandAdapter(Context context, List<Brand> brands) {
        this.context = context;
        this.brands = brands;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return brands.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return brands.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return brands.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ParentHolder parentHolder = null;

        Brand brand = (Brand) getGroup(groupPosition);

        if(convertView == null) {
            LayoutInflater userInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = userInflater.inflate(R.layout.item_parent, null);
            convertView.setHorizontalScrollBarEnabled(true);

            parentHolder = new ParentHolder();
            convertView.setTag(parentHolder);

        } else {
            parentHolder = (ParentHolder) convertView.getTag();
        }

        parentHolder.brandName = (TextView) convertView.findViewById(R.id.text_brand);
        parentHolder.brandName.setText(brand.brandName);

        parentHolder.indicator = (ImageView) convertView.findViewById(R.id.image_indicator);

        if(isExpanded) {
            parentHolder.indicator.setImageResource(R.drawable.ic_keyboard_arrow_up_black_18dp);
        } else {
            parentHolder.indicator.setImageResource(R.drawable.ic_keyboard_arrow_down_black_18dp);
        }

        return convertView;
    }

   /* @SuppressWarnings("static-access")
	@Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ChildHolder childHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_group_child, parent, false);
            childHolder = new ChildHolder();
            convertView.setTag(childHolder);
        }
        else {
            childHolder = (ChildHolder) convertView.getTag();
        }

        childHolder.horizontalListView = (RecyclerView) convertView.findViewById(R.id.mobiles);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        childHolder.horizontalListView.setLayoutManager(layoutManager);

        MobileAdapter horizontalListAdapter = new MobileAdapter(context, brands.get(groupPosition).mobiles);
        childHolder.horizontalListView.setAdapter(horizontalListAdapter);

        return convertView;
    }*/
    
    
    PagerAdapter adapter;
	String[] rank;
	String[] country;
	String[] population;
	int[] flag;
	
	
    @SuppressWarnings("static-access")
   	@Override
       public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

           ChildHolder childHolder = null;
           if (convertView == null) {
               LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
               convertView = inflater.inflate(R.layout.my_viewpager_item_group_child, parent, false);
               childHolder = new ChildHolder();
               convertView.setTag(childHolder);
           }
           else {
               childHolder = (ChildHolder) convertView.getTag();
           }

           childHolder.horizontalListView = (ViewPager) convertView.findViewById(R.id.mobiles);
          /* LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
           childHolder.horizontalListView.setLayoutManager(layoutManager);*/
           
    //       MobileAdapter horizontalListAdapter = new MobileAdapter(context, brands.get(groupPosition).mobiles);
           
/*           MobileBaseAdapter horizontalListAdapter = new MobileBaseAdapter(context, brands.get(groupPosition).mobiles);
           childHolder.horizontalListView.setAdapter(horizontalListAdapter);
*/
           

   		// Generate sample data
   		rank = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
    
   		country = new String[] { "China", "India", "United States",
   				"Indonesia", "Brazil", "Pakistan", "Nigeria", "Bangladesh",
   				"Russia", "Japan" };
    
   		population = new String[] { "1,354,040,000", "1,210,193,422",
   				"315,761,000", "237,641,326", "193,946,886", "182,912,000",
   				"170,901,000", "152,518,015", "143,369,806", "127,360,000" };
    
   		flag = new int[] { R.drawable.oneplus_x, R.drawable.nexus_6p,
   				R.drawable.oneplus_2, R.drawable.abc_text_cursor_material,
   				R.drawable.oneplus_2, R.drawable.notification_template_icon_bg, R.drawable.nexus_5,
   				R.drawable.oneplus_x, R.drawable.nexus_6p, R.drawable.oneplus_x };
           
           
        // Pass results to ViewPagerAdapter Class
   		adapter = new ViewPagerAdapter(context, rank, country, population, flag);
   		// Binds the Adapter to the ViewPager
   		childHolder.horizontalListView.setAdapter(adapter);
   		

   	// make this at least however many pages you can see
   		childHolder.horizontalListView.setOffscreenPageLimit(adapter.getCount());
        //A little space between pages
   		//childHolder.horizontalListView.setPageMargin(15);

        //If hardware acceleration is enabled, you should also remove
        // clipping on the pager for its children.
   		childHolder.horizontalListView.setClipChildren(false);
   		
           return convertView;
       }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

   /* private static class ChildHolder {
        static RecyclerView horizontalListView;
    }*/
    
    private static class ChildHolder {
        static ViewPager horizontalListView;
    }

    private static class ParentHolder {
        TextView brandName;
        ImageView indicator;
    }
}
