package com.aurum.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aurum.R;
import com.squareup.picasso.Picasso;


public class ExhibitionSbListAdapter extends BaseAdapter {

	// ArrayList<ExhibitionMainListModel> arrayDetails;
	Context context;
	String strImg, strUName, strType;
	String from = "", eventId = "";
	App app;
	private int datapos = 0;
	MyListener listener;

	ArrayList<String> arrayExhibitionList;
	ArrayList<String> arrayimages;
	ArrayList<String> arrayids;
	ArrayList<String> arrayCatImage;
	// ArrayList<String> int_arr_groupsListListChildItemName;

	ArrayList<ArrayList<String>> mainnamelist = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> imagelist = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> catids = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> catmainimages = new ArrayList<ArrayList<String>>();
	int[] images;// ={R.drawable.home_bg,R.drawable.book_bg,R.drawable.electronics_bg,R.drawable.fashion_bg,R.drawable.home_bg,R.drawable.book_bg,R.drawable.electronics_bg,R.drawable.fashion_bg,R.drawable.home_bg,R.drawable.book_bg};

	int size = 0;
	
    
	public ExhibitionSbListAdapter(Context context, ArrayList<String> array,
			ArrayList<String> arrimages, ArrayList<String> arrids,
			ArrayList<String> catmain) {
		// this.arrayDetails = arrayExhibitionList;
		this.arrayExhibitionList = array;
		this.arrayimages = arrimages;
		this.context = context;
		this.arrayids = arrids;
		this.arrayCatImage = catmain;
		datapos = 0;
		listener = new MyListener();
		app = (App) context.getApplicationContext();

		int j = 0;
		for (int i = j; i < array.size(); i++) {

			ArrayList<String> int_arr_groupsListListChildItemImage = new ArrayList<>();
			for (j = i; j < (i + 4); j++) {
				if (j >= array.size()) {
					break;
				} else {
					int_arr_groupsListListChildItemImage.add(array.get(j));
				}
			}
			i = j - 1;
			mainnamelist.add(int_arr_groupsListListChildItemImage);

			// Use the list further...
		}

		int jj = 0;
		for (int i = jj; i < arrayimages.size(); i++) {
			ArrayList<String> int_arr_groupsListListChildItemImages = new ArrayList<>();
			for (jj = i; jj < (i + 4); jj++) {
				if (jj >= arrayimages.size()) {
					break;
				} else {
					int_arr_groupsListListChildItemImages.add(arrayimages
							.get(jj));
				}
			}
			i = jj - 1;
			imagelist.add(int_arr_groupsListListChildItemImages);
		}

		int k = 0;
		for (int i = k; i < arrayids.size(); i++) {
			ArrayList<String> int_arr_groupsListListChildItemIds = new ArrayList<>();
			for (k = i; k < (i + 4); k++) {
				if (k >= arrayids.size()) {
					break;
				} else {
					int_arr_groupsListListChildItemIds.add(arrayids.get(k));
				}
			}
			i = k - 1;
			catids.add(int_arr_groupsListListChildItemIds);
		}

		int l = 0;
		for (int i = l; i < arrayCatImage.size(); i++) {
			ArrayList<String> int_arr_groupsListListChildItemImages = new ArrayList<>();
			for (l = i; l < (i + 4); l++) {
				if (l >= arrayCatImage.size()) {
					break;
				} else {
					int_arr_groupsListListChildItemImages.add(arrayCatImage
							.get(l));
				}
			}
			i = l - 1;
			catmainimages.add(int_arr_groupsListListChildItemImages);
		}

	}

	private class MyViewHolder {
		LinearLayout lay_one, lay_two, lay_three, lay_four;
		TextView txtTitle_one, txtTitle_two, txtTitle_three, txtTitle_four;
		ImageView imgBack, imgOne, imgTwo, imgThree, imgFour;
		FrameLayout frameNext, framePrev;
		// tv_exhibition_item
	}

	@Override
	public int getCount() {

		int t = (int) (arrayExhibitionList.size() % 4);
		if (t == 0) {
			return (int) (arrayExhibitionList.size() / 4);
		} else {
			return (int) ((arrayExhibitionList.size() / 4) + 1);
		}

	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		MyViewHolder viewHolder;
		datapos = -1;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		Log.i("Data Disp[layed --------------------------->", "" + datapos);
		if (convertView == null) {

			convertView = inflater.inflate(R.layout.temp2, null);
			viewHolder = new MyViewHolder();
			viewHolder.txtTitle_one = (TextView) convertView
					.findViewById(R.id.tv_exhibition_item_one);
			viewHolder.txtTitle_two = (TextView) convertView
					.findViewById(R.id.tv_exhibition_item_two);
			viewHolder.txtTitle_three = (TextView) convertView
					.findViewById(R.id.tv_exhibition_item_three);
			viewHolder.txtTitle_four = (TextView) convertView
					.findViewById(R.id.tv_exhibition_item_four);

			viewHolder.lay_one = (LinearLayout) convertView
					.findViewById(R.id.lay_one);
			viewHolder.lay_two = (LinearLayout) convertView
					.findViewById(R.id.lay_two);
			viewHolder.lay_three = (LinearLayout) convertView
					.findViewById(R.id.lay_three);
			viewHolder.lay_four = (LinearLayout) convertView
					.findViewById(R.id.lay_four);

			viewHolder.imgOne = (ImageView) convertView
					.findViewById(R.id.iv_imageCategory1);
			viewHolder.imgTwo = (ImageView) convertView
					.findViewById(R.id.iv_imageCategory2);
			viewHolder.imgThree = (ImageView) convertView
					.findViewById(R.id.iv_imageCategory3);
			viewHolder.imgFour = (ImageView) convertView
					.findViewById(R.id.iv_imageCategory4);


			

			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (MyViewHolder) convertView.getTag();
		}

		datapos++;
		// mainnamelist.get(position).get(datapos)
		boolean flag = false;

		

		
		if (mainnamelist.get(position).size() > datapos
				&& mainnamelist.get(position).get(datapos) != null) {
			viewHolder.txtTitle_one.setText(mainnamelist.get(position).get(
					datapos));
			viewHolder.lay_one.setTag(mainnamelist.get(position).get(datapos)
					+ "@" + imagelist.get(position).get(datapos) + "@"
					+ catids.get(position).get(datapos) + "@"
					+ catmainimages.get(position).get(datapos));
			/*ImageLoader imageLoader = new ImageLoader(context);
			imageLoader
			.DisplayImage(App.Sub_Product_Path
					+ imagelist.get(position).get(datapos),
					viewHolder.imgOne);*/
			
			Picasso.with(context)
		     .load(App.Sub_Product_Path
		    + imagelist.get(position).get(datapos))
		     .placeholder(R.drawable.oneplus_1)
		     .error(R.drawable.oneplus_x)
		     .into(viewHolder.imgOne);
			
			viewHolder.lay_one.setOnClickListener(listener);
			Log.i("Tag Data-------------------------->" + datapos, mainnamelist
					.get(position).get(datapos));
		} else {
			viewHolder.lay_one.setVisibility(View.GONE);
		}

		
		datapos++;
		if (mainnamelist.get(position).size() > datapos
				&& mainnamelist.get(position).get(datapos) != null) {
			viewHolder.txtTitle_two.setText(mainnamelist.get(position).get(
					datapos));
			viewHolder.lay_two.setTag(mainnamelist.get(position).get(datapos)
					+ "@" + imagelist.get(position).get(datapos) + "@"
					+ catids.get(position).get(datapos) + "@"
					+ catmainimages.get(position).get(datapos));
			viewHolder.lay_two.setOnClickListener(listener);
			/*ImageLoader imageLoader = new ImageLoader(context);
			imageLoader
			.DisplayImage(App.Sub_Product_Path
					+ imagelist.get(position).get(datapos),
					viewHolder.imgTwo);*/
			
			Picasso.with(context)
		     .load(App.Sub_Product_Path
		    + imagelist.get(position).get(datapos))
		     .placeholder(R.drawable.oneplus_1)
		     .error(R.drawable.oneplus_x)
		     .into(viewHolder.imgTwo);
			
			Log.i("Tag Data-------------------------->" + datapos, mainnamelist
					.get(position).get(datapos));
		} else {
			viewHolder.lay_two.setVisibility(View.GONE);
		}

		datapos++;
		if (mainnamelist.get(position).size() > datapos
				&& mainnamelist.get(position).get(datapos) != null) {
			viewHolder.txtTitle_three.setText(mainnamelist.get(position).get(
					datapos));
			viewHolder.lay_three.setTag(mainnamelist.get(position).get(datapos)
					+ "@" + imagelist.get(position).get(datapos) + "@"
					+ catids.get(position).get(datapos) + "@"
					+ catmainimages.get(position).get(datapos));
			viewHolder.lay_three.setOnClickListener(listener);
			/*ImageLoader imageLoader = new ImageLoader(context);
			imageLoader
			.DisplayImage(App.Sub_Product_Path
					+ imagelist.get(position).get(datapos),
					viewHolder.imgThree);*/
			
			Picasso.with(context)
		     .load(App.Sub_Product_Path
		    + imagelist.get(position).get(datapos))
		     .placeholder(R.drawable.oneplus_1)
		     .error(R.drawable.oneplus_x)
		     .into(viewHolder.imgThree);
			
			Log.i("Tag Data-------------------------->" + datapos, mainnamelist
					.get(position).get(datapos));
		} else {
			viewHolder.lay_three.setVisibility(View.GONE);
		}

		datapos++;
		if (mainnamelist.get(position).size() > datapos
				&& mainnamelist.get(position).get(datapos) != null) {
			viewHolder.txtTitle_four.setText(mainnamelist.get(position).get(
					datapos));
			viewHolder.lay_four.setTag(mainnamelist.get(position).get(datapos)
					+ "@" + imagelist.get(position).get(datapos) + "@"
					+ catids.get(position).get(datapos) + "@"
					+ catmainimages.get(position).get(datapos));
			viewHolder.lay_four.setOnClickListener(listener);
			/*ImageLoader imageLoader = new ImageLoader(context);
			imageLoader
			.DisplayImage(App.Sub_Product_Path
					+ imagelist.get(position).get(datapos),
					viewHolder.imgFour);*/
			
			Picasso.with(context)
		     .load(App.Sub_Product_Path
		    + imagelist.get(position).get(datapos))
		     .placeholder(R.drawable.oneplus_2)
		     .error(R.drawable.oneplus_x)
		     .into(viewHolder.imgFour);
			
			Log.i("Tag Data-------------------------->" + datapos, mainnamelist
					.get(position).get(datapos));
		} else {
			viewHolder.lay_four.setVisibility(View.GONE);
		}

		
		
		
		return convertView;
	}

	public class MyListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			LinearLayout layout = (LinearLayout) v;
			if (v.getId() == R.id.lay_one) {
				Log.i("Tag Data-------------------------->1----->", "");
			} else if (v.getId() == R.id.lay_two) {
				Log.i("Tag Data-------------------------->2----->", "");
			} else if (v.getId() == R.id.lay_three) {
				Log.i("Tag Data-------------------------->3----->", "");
			} else {
				Log.i("Tag Data-------------------------->4----->", "");
			}

			String tag = layout.getTag().toString();
			String[] cat_name = null;
			// String cat_image = tag.substring(tag.indexOf("@")+1);
			cat_name = tag.split("@", 4);
			Log.i("Tag Data-------------------------->", tag);

			if (App.isInternetAvail(context)) {

				if (cat_name != null) {
					Intent detail = new Intent(context, MainActivity.class);
					detail.putExtra("sub_cat_name", cat_name[0]);
					detail.putExtra("sub_cat_image", cat_name[3]);
					detail.putExtra("sub_cat_id", cat_name[2]);
					detail.putExtra("main_cat_image", cat_name[3]);
					detail.putExtra("from", "ExhibitionMain");
					context.startActivity(detail);
				}
			} else {
				
			}
			
			
		}
	}

	 
}
