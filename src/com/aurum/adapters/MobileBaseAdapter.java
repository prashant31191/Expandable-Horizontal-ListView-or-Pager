package com.aurum.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aurum.R;
import com.aurum.models.Mobile;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 2/3/16.
 */
public class MobileBaseAdapter extends BaseAdapter
{

	private LayoutInflater inflater;
    private Context context;
    private List<Mobile> mobiles = new ArrayList<Mobile>();

    public MobileBaseAdapter(Context context, List<Mobile> mobiles) {
        this.context = context;
        this.mobiles = mobiles;
         inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
    @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
    	ViewHolder viewHolder;
    	 if(convertView ==null)
    	 {
    		  viewHolder = new ViewHolder();
    		 convertView = inflater.inflate(R.layout.item_child, null, false);
	         
	         viewHolder.mobileImage = (ImageView) convertView.findViewById(R.id.image_mobile);
	         viewHolder.modelName = (TextView) convertView.findViewById(R.id.text_mobile_model);
	         viewHolder.price = (TextView) convertView.findViewById(R.id.text_mobile_price);
	         
	         convertView.setTag(viewHolder);
    	 }
    	 else
    	 {
    		 viewHolder = (ViewHolder) convertView.getTag();
    	 }
    	 
    	 
    	 viewHolder.mobileImage.setImageResource(mobiles.get(position).imageResource);
    	 viewHolder.modelName.setText(mobiles.get(position).modelName);
    	 viewHolder.price.setText(mobiles.get(position).price);
    	 
    	 return convertView;    	
	}
    
    /*

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View cardView = inflater.inflate(R.layout.item_child, null, false);
        ViewHolder viewHolder = new ViewHolder(cardView);
        viewHolder.mobileImage = (ImageView) cardView.findViewById(R.id.image_mobile);
        viewHolder.modelName = (TextView) cardView.findViewById(R.id.text_mobile_model);
        viewHolder.price = (TextView) cardView.findViewById(R.id.text_mobile_price);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ImageView mobileImageView = (ImageView) holder.mobileImage;
        mobileImageView.setImageResource(mobiles.get(position).imageResource);

        TextView modelTextView = (TextView) holder.modelName;
        modelTextView.setText(mobiles.get(position).modelName);

        TextView priceTextView = (TextView) holder.price;
        priceTextView.setText(mobiles.get(position).price);

    }
*/
   
   

    public static class ViewHolder  {

        ImageView mobileImage;
        TextView modelName;
        TextView price;
    }




	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mobiles.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mobiles.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return mobiles.size();
	}

	

}
