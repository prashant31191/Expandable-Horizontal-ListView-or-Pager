package com.aurum.activities;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.aurum.R;




@SuppressLint("NewApi")
public class ExhibitionMainActivity extends Activity {
	
	
	ExhibitionMainListAdapter adapter;
	ExhibitionSbListAdapter adapter_child;
	
	ArrayList<String> cat_names;
	ArrayList<String> cat_MainImage;
	String strCountryName, strCountryCode, strCountryId, from;
	ExhibitionMainListAdapter exhibitionMainListAdapter;
	ArrayList<String> cat_ids;

	ArrayList<String> cat_images;
	Handler handler;
	private int previousTotal = 0;
	private int pageNum = 0;
	private int oldCount = 0;
	public boolean loading = true;
	EditText editTextSearch;
	LinearLayout llSearch_inner;
	/*int[] intArrExibitionItemImages = { R.drawable.home_bg, R.drawable.book_bg, R.drawable.electronics_bg, R.drawable.fashion_bg,
			R.drawable.home_bg, R.drawable.book_bg, R.drawable.electronics_bg, R.drawable.fashion_bg, R.drawable.home_bg,
			R.drawable.book_bg };*/
	PopupWindow mpopup;
	ExpandableGridView countryGridView;
	App app;

	
	
	String search;
	TextView tvExibitionDetailsTag;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_exhibition_main); //
		
		initView();
	}
	
/*	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.act_exhibition_main, container, false);
	}
*/
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		
		if(app.arrayExhibitionList != null && app.arrayExhibitionList.size() > 0)
		{
			exhibitionMainListAdapter = new ExhibitionMainListAdapter(this, app.arrayExhibitionList);
		}
		else
		{
			
			/*if (app.isInternetAvail(ExhibitionMainActivity.this)) {

				asyncExhibitionListMain(true);
			} else {
			
			}
			*/
			
		}
		
		super.onResume();
	}

	private void initView()
	{
		
		handler = new Handler();
		View view = null;
		initComponent(view);
		// setUpPager(view);
	}

	/*

	public void asyncExhibitionListMain(boolean flag) {
		asyncCall = new AsyncCall(activity);
		asyncCall.webMethod = RequestMethod.GET;
		asyncCall.setUrl(App.WEB_API_URL);
		asyncCall.addParam("op", App.METHOD_EXHIBITION_LIST);

		
		if (search != null && search.length() > 0) {
			asyncCall.addParam("key", search);
		}
		asyncCall.addParam("page", "" + pageNum);
		asyncCall.showProgressBar(flag);
		asyncCall.setAsyncCallListener(new AsyncCallAdapter(activity) {

			ExhibitionMainListResponse response;

			@Override
			public void onResponseReceived(String str) {
				Log.d("response", str);

				try {
					Gson gson = new Gson();
					response = gson.fromJson(str, ExhibitionMainListResponse.class);
					if (response != null && response.message.equalsIgnoreCase("1")) {
						if (response.arrayExhibitionList != null && response.arrayExhibitionList.size() > 0) {

							if (app.arrayExhibitionList != null && app.arrayExhibitionList.size() > 0) {
								oldCount = app.arrayExhibitionList.size();
								app.arrayExhibitionList.addAll(response.arrayExhibitionList);
								if (response.arrayExhibitionList.size() > 12) {
									if (loading)
										loading = false;

								}
								exhibitionMainListAdapter.notifyDataSetChanged();
								
								
								
							} else {
								app.arrayExhibitionList = new ArrayList<>(response.arrayExhibitionList);

								if (app.arrayExhibitionList.size() > 12) {
									if (loading)
										loading = false;
								}
								setAdapter();
							}

						} else {
						}

					}
				} catch (Exception ex) {
					Log.e("Error", ex.toString());

				}

			}

			@Override
			public void onErrorReceived(String str) {
				if (loading)
					loading = false;

			}
		});
		asyncCall.execute();
	}*/

	public void setAdapter() {
		if (app.arrayExhibitionList != null) {

			exhibitionMainListAdapter = new ExhibitionMainListAdapter(this, app.arrayExhibitionList);
			countryGridView.setAdapter(exhibitionMainListAdapter);
		}
	}

	

	private void initComponent(View view) {

		String[] strArrExibitionMainItems = { "Agriculture", "Food & Beverages", "Appareal", "Fashion Accessories", "Transportation",
				"Kids Appreal", "Gifts & Crafts", "Toys & Hobbies", "Machinery" };
		String[] strArrExibitionTotalItems = { "50", "83", "80", "10", "21", "51", "30", "11", "40", "10" };

		final String[] stateData = { "State A", "State B", "State C", "State D" };
		tvExibitionDetailsTag = (TextView)findViewById(R.id.tvExibitionDetailsTag);
		tvExibitionDetailsTag.setText("Choose country from Top Right. Following are Exhibition Halls. Select Category and Sub-Category");
		tvExibitionDetailsTag.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("==setting Null click event--on--textview -tvExibitionDetailsTag==");
			}
		});

		// 0.Init the ExpandableGridView
		countryGridView = (ExpandableGridView) findViewById(R.id.country_grid);
		countryGridView.setOnScrollListener(new EndlessScrollListener());

		/*
		 * LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
		 * View headerView =
		 * layoutInflater.inflate(R.layout.exhibition_grid_header, null);
		 * countryGridView.addView(headerView);
		 */

		app = (App) getApplicationContext();

		
		app.arrayExhibitionList = new ArrayList<>();
		app.arrayExhibitionChildList = new ArrayList<>();
		
		
		for(int i=0; i< strArrExibitionMainItems.length; i++)
		{
			ExhibitionMainListModel  exhibitionMainListModel = new  ExhibitionMainListModel();
			exhibitionMainListModel.cid = ""+i;
			exhibitionMainListModel.cnm = ""+strArrExibitionMainItems[i];
			exhibitionMainListModel.cimg = "http://s3.india.com/wp-content/uploads/2015/10/Virat-Kohli-of-India-hits-a-six-as-Quinton-de-Kock-of-South-Africa-looks-on-during-the-ICC-World-Twenty20-901.jpg";
			
			app.arrayExhibitionChildList = new ArrayList<>();
			for(int j=0; j <stateData.length ; j++)
			{
				ExhibitionSubListModel exhibitionSubListModel = new  ExhibitionSubListModel();
				exhibitionSubListModel.cnm = "Cat name";
				exhibitionSubListModel.gimg= "https://javaxwest.files.wordpress.com/2015/03/feature_actionbar_badge.jpg";
				exhibitionSubListModel.desc = "This is the desc";
				exhibitionSubListModel.simg = "https://javaxwest.files.wordpress.com/2015/03/feature_swipe_to_delete.jpg";
				app.arrayExhibitionChildList.add(exhibitionSubListModel);
			}
			
			
			exhibitionMainListModel.arraySubList = app.arrayExhibitionChildList ;
			
			app.arrayExhibitionList.add(exhibitionMainListModel);
		}
		
		

	

		// 2.Add click event listener to the grid view, expand grid view when
		// item is clicked
		
		setAdapter();

		countryGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// expand the grid view
				// --main countryGridView.expandGridViewAtView(view,new
				// ArrayAdapter<String>(getActivity(),R.layout.temp,R.id.dummyid,stateData));
				if (app.arrayExhibitionList.get(position).arraySubList != null && app.arrayExhibitionList.get(position).arraySubList.size() > 0) {
					app.arrayExhibitionChildList = new ArrayList<>(app.arrayExhibitionList.get(position).arraySubList);
					cat_names = new ArrayList<>();
					cat_ids = new ArrayList<>();
					cat_images = new ArrayList<>();
					cat_MainImage = new ArrayList<>();
					for (int i = 0; i < app.arrayExhibitionChildList.size(); i++) {
						cat_ids.add(app.arrayExhibitionChildList.get(i).sid);
						cat_names.add(app.arrayExhibitionChildList.get(i).snm);
						cat_images.add(app.arrayExhibitionChildList.get(i).simg);
						cat_MainImage.add(app.arrayExhibitionChildList.get(i).gimg);
					}
					
					adapter_child = new ExhibitionSbListAdapter(ExhibitionMainActivity.this, cat_names, cat_images, cat_ids,cat_MainImage);
					countryGridView.expandGridViewAtView(view, adapter_child);
					
				}
				
				

			}
		});

	}

	

	public class EndlessScrollListener implements OnScrollListener {
		private int visibleThreshold = 0;

		public EndlessScrollListener() {
		}

		public EndlessScrollListener(int visibleThreshold) {
			this.visibleThreshold = visibleThreshold;
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
			
			
			
			
			
			
			if (loading) {
				if (totalItemCount > previousTotal) {
					loading = false;
					previousTotal = totalItemCount;
				} else if (previousTotal > totalItemCount) {
					visibleThreshold = 1;
					previousTotal = 0;
					loading = true;
				}
				if (totalItemCount == visibleItemCount) {
					loading = true;
				}
			}
			if ((!loading) && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) 
			{
				System.out.println("==========IN========");
				loading = true;
				pageNum = pageNum + 1;

				if (app.isInternetAvail(ExhibitionMainActivity.this)) {
				//--	asyncExhibitionListMain(true);

				} else {
					
				}

			}
		}

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
		}
	}

}
