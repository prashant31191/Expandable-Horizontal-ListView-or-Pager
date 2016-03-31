package com.example.lemeridian;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.elite.common.App;
import com.elite.common.AppFonts;
import com.elite.model.RestaurantListModel;
import com.facebook.CallbackManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class BaseMenuActivity extends Activity {

    public App app;
    protected RelativeLayout imageView;
    protected LinearLayout linearContentLayout;
    protected TextView headertitle;
    protected RelativeLayout sharelayout;
    protected SearchView svSearch;
    protected EditText etSearch;
    protected RelativeLayout headerlayout;
    ArrayList<RestaurantListModel.Restaurant> students;
    CallbackManager callbackManager;
    ArrayList<String> groupItem = new ArrayList<String>();
    ArrayList<Object> childItem = new ArrayList<Object>();
    private DrawerLayout drawer;
    private AnimatedExpandableListView drawerList;
    private RelativeLayout left_drawer;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_menu);


        try {

            linearContentLayout = (LinearLayout) findViewById(R.id.linearContentLayout);
            headerlayout = (RelativeLayout) findViewById(R.id.headerlayout);
            imageView = (RelativeLayout) findViewById(R.id.ivMenu);
            headertitle = (TextView) findViewById(R.id.tvTitle);
            sharelayout = (RelativeLayout) findViewById(R.id.sharelayout);

            left_drawer = (RelativeLayout) findViewById(R.id.left_drawer);
            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawerList = (AnimatedExpandableListView) findViewById(R.id.left_drawer_list);
            svSearch = (SearchView) findViewById(R.id.svSearch);
            etSearch = (EditText) findViewById(R.id.etSearch);

            sharelayout.setVisibility(View.GONE);
            svSearch.setVisibility(View.GONE);

            ActionBar bar = getActionBar();
            if (bar != null) {

                bar.hide();
            } else {

            }


            app = (App) getApplication();

            setGroupData();
            if (app.preferences.getStringPref("RestaurantList") != null && app.preferences.getStringPref("RestaurantList").length() > 0) {
                Gson gson = new Gson();

                Type type = new TypeToken<ArrayList<RestaurantListModel.Restaurant>>() {
                }.getType();
                students = gson.fromJson(app.preferences.getStringPref("RestaurantList"), type);
                ArrayList<String> child = new ArrayList<String>();
                for (RestaurantListModel.Restaurant model : students
                        ) {
                    child.add(model.restaurant_name);

                }
                childItem.add(child);


            } else {
                setChildGroupData();
            }

            initDrawer();


            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (drawer.isDrawerOpen(left_drawer)) {
                        drawer.closeDrawers();
                    } else {
                        drawer.openDrawer(left_drawer);
                    }
                }
            });

            etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        performSearch(etSearch.getText().toString());
                        etSearch.setText("");
                        return true;
                    }
                    return false;
                }
            });

            int id = svSearch.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
            TextView textView = (TextView) svSearch.findViewById(id);
            textView.setTextColor(Color.WHITE);

            AppFonts appFonts = new AppFonts();
            headertitle.setTypeface(appFonts.getGriffithGothicBold(BaseMenuActivity.this));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void performSearch(String strSearchText) {
        if (strSearchText != null && strSearchText.length() > 0) {
            Intent i = new Intent(BaseMenuActivity.this, SearchResultActivity.class);
            i.putExtra("From", "MainScreen");
            i.putExtra("keyword", strSearchText);
            startActivity(i);
        } else {
            //  setToast("Please enter text for search.");
        }
    }

    public void setToast(String strMessage) {
        Toast.makeText(BaseMenuActivity.this, strMessage, Toast.LENGTH_SHORT).show();
    }

    public void setToastNoConnection() {
        Toast.makeText(BaseMenuActivity.this, getResources().getString(R.string.strNetworkError), Toast.LENGTH_SHORT).show();
    }

    public boolean isInternetAvailable() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer_layout_test, menu);
        return true;
    }

    private void initDrawer() {

        try {
            drawerList.setAdapter(new NewAdapter(this, groupItem, childItem, students));
            drawerList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                    if (drawerList.isGroupExpanded(groupPosition)) {
                        drawerList.collapseGroupWithAnimation(groupPosition);
                    } else {

                        if (groupItem.get(groupPosition).equals("HOME")) {
                            if (headertitle.getText().toString() != null && headertitle.getText().toString().equals("HOME")) {
                                drawer.closeDrawers();
                            } else {
                                Intent i = new Intent(BaseMenuActivity.this, DrawerLayoutTest.class);
                                i.putExtra("From", "MainScreen");
                                startActivity(i);
                                finish();
                            }
                        }

                        if (groupItem.get(groupPosition).equals("RESTAURANTS")) {
                            drawerList.expandGroupWithAnimation(groupPosition);
                        }
                        if (groupItem.get(groupPosition).equals("RESERVATIONS")) {
                            if (headertitle.getText().toString() != null && headertitle.getText().toString().equals("RESERVATIONS")) {
                                drawer.closeDrawers();
                            } else {
                                Intent i = new Intent(BaseMenuActivity.this, ReservationActivity.class);
                                i.putExtra("From", "MainScreen");
                                startActivity(i);
                                finish();
                            }
                        }

                        if (groupItem.get(groupPosition).equals("PROMOTIONS")) {
                            if (headertitle.getText().toString() != null && headertitle.getText().toString().equals("PROMOTIONS")) {
                                drawer.closeDrawers();
                            } else {
                                Intent i = new Intent(BaseMenuActivity.this, PromotionsActivity.class);
                                i.putExtra("From", "MainScreen");
                                startActivity(i);
                                finish();
                            }
                        }


                        if (groupItem.get(groupPosition).equals("LOCATION")) {
                            if (headertitle.getText().toString() != null && headertitle.getText().toString().equals("LOCATION")) {
                                drawer.closeDrawers();
                            } else {
                                Intent i = new Intent(BaseMenuActivity.this, RestaurantLocationActivity.class);
                                i.putExtra("From", "MainScreen");
                                startActivity(i);
                                finish();
                            }
                        }

                        if (groupItem.get(groupPosition).equals("CONTACT")) {
                            //drawer.closeDrawers();
                            if (headertitle.getText().toString() != null && headertitle.getText().toString().equals("CONTACT")) {
                                drawer.closeDrawers();
                            } else {
                                Intent i = new Intent(BaseMenuActivity.this, ContactActivity.class);
                                i.putExtra("From", "MainScreen");
                                startActivity(i);
                                finish();
                            }
                        }

                        if (groupItem.get(groupPosition).equals("SETTINGS")) {
                            //drawer.closeDrawers();
                            if (headertitle.getText().toString() != null && headertitle.getText().toString().equals("SETTINGS")) {
                                drawer.closeDrawers();
                            } else {
                                Intent i = new Intent(BaseMenuActivity.this, ActSettings.class);
                                i.putExtra("From", "MainScreen");
                                startActivity(i);
                                finish();
                            }
                        }
                    }
                    return true;
                }

            });


            left_drawer.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {// TODO Auto-generated method stub
                    // TODO Auto-generated method stub
                    app.LogI("==base menu act==", "===on touch==");

                    if (v instanceof EditText) {
                        app.LogI("==base menu act==", "=touch no hide edittext==2=");
                    } else {
                        app.LogI("==base menu act==", "===on touch hide=2=");
                        //   v.clearFocus();
                        app.hideSoftKeyboardMy(BaseMenuActivity.this);
                    }
                    return true;
                }
            });


            drawer.setDrawerListener(new DrawerLayout.DrawerListener() {
                @Override
                public void onDrawerSlide(View drawerView, float slideOffset) {
                }

                @Override
                public void onDrawerOpened(View drawerView) {

                }

                @Override
                public void onDrawerClosed(View drawerView) {

                    if (drawerView instanceof EditText) {
                        app.LogI("==base menu act==", "=touch no hide edittext==2=");
                    } else {
                        app.LogI("==base menu act==", "===on touch hide=2=");
                        //   v.clearFocus();
                        app.hideSoftKeyboardMy(BaseMenuActivity.this);
                    }
                }

                @Override
                public void onDrawerStateChanged(int newState) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setGroupData() {
        groupItem.add("HOME");
        groupItem.add("RESTAURANTS");
        groupItem.add("RESERVATIONS");
        groupItem.add("PROMOTIONS");
        groupItem.add("CONTACT");
        groupItem.add("LOCATION");
        groupItem.add("SETTINGS");
    }

    public void setChildGroupData() {
        /**
         * Add Data For TecthNology
         */
        ArrayList<String> child = new ArrayList<String>();
        child.add("Acropolis");
        child.add("Talay");
        child.add("Pappagallo");
        child.add("Wakataua");
        child.add("NRG Sports Cafe");
        child.add("The Captain's Arms");
        childItem.add(child);


    }

	/*@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		Toast.makeText(this, "Clicked On Child" + v.getTag(),
				Toast.LENGTH_SHORT).show();
		return true;
	}*/

    @Override
    protected void onResume() {

        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            //drawer is open
            drawer.closeDrawers();
        }

        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            //drawer is open
            drawer.closeDrawers();
        } else {
            super.onBackPressed();
        }

    }
}