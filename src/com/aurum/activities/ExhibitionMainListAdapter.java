package com.aurum.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aurum.R;
import com.squareup.picasso.Picasso;

public class ExhibitionMainListAdapter extends BaseAdapter
{

	//ArrayList<ExhibitionMainListModel> arrayDetails;
	Context context;
	String strImg,strUName,strType;
	String from="",eventId="";
	App app;
	ArrayList<ExhibitionMainListModel> arrayExhibitionList;
	
	int[] images;// ={R.drawable.home_bg,R.drawable.book_bg,R.drawable.electronics_bg,R.drawable.fashion_bg,R.drawable.home_bg,R.drawable.book_bg,R.drawable.electronics_bg,R.drawable.fashion_bg,R.drawable.home_bg,R.drawable.book_bg};
	public ExhibitionMainListAdapter (Context context,ArrayList<ExhibitionMainListModel> array)
	{
		//this.arrayDetails = arrayExhibitionList;
		this.arrayExhibitionList=array;
		this.context = context;
		app =(App)context.getApplicationContext();
	}

	private class MyViewHolder 
	{
		TextView txtTitle,txtTotal;
		ImageView imgBack;
		
	}   

	@Override
	public int getCount() 
	{
		return arrayExhibitionList.size();
	}

	@Override
	public Object getItem(int position) 
	{
		return arrayExhibitionList.get(position);
	}

	@Override
	public long getItemId(int position) 
	{
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) 
	{

		MyViewHolder viewHolder;
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

		if (convertView == null) 
		{
			convertView = inflater.inflate(R.layout.row_exhibition_itemmain, null);	
			viewHolder = new MyViewHolder();
			viewHolder.txtTitle =(TextView)convertView.findViewById(R.id.textViewTotalSubcategory);
			viewHolder.txtTotal= (TextView)convertView.findViewById(R.id.textViewMainTitle);
			viewHolder.imgBack = (ImageView)convertView.findViewById(R.id.imageView1);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder = (MyViewHolder)convertView.getTag();
		}

		
		
		
		viewHolder.txtTitle.setText(arrayExhibitionList.get(position).cnm);
		viewHolder.txtTotal.setText(arrayExhibitionList.get(position).total_subct);
		//viewHolder.imgBack.setBackgroundResource(images[position]);
		
		/*ImageLoader imagerloader = new ImageLoader(context);
		imagerloader.DisplayImage(App.EXHIVITION_IMAGE+arrayExhibitionList.get(position).cimg, viewHolder.imgBack);*/
		
		Picasso.with(context)
	     .load(App.EXHIVITION_IMAGE
	    + arrayExhibitionList.get(position).cimg)
	     .placeholder(R.drawable.oneplus_x)
	     .error(R.drawable.oneplus_2)
	     .into(viewHolder.imgBack);

		
		return convertView;
	}

	
}
