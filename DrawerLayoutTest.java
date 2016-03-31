package com.example.lemeridian;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.elite.common.App;
import com.elite.common.AppFonts;

import java.util.Vector;

@SuppressLint("NewApi")
public class DrawerLayoutTest extends BaseMenuActivity implements ViewSwitcher.ViewFactory /*implements OnChildClickListener */ {



    private LinearLayout restaurants, reservations, promotions, contacts;
    private TextView restauranttext,reservationtext,promotionstext,contactstext;
    private App app;
    private ImageSwitcher content_frame_image;
    private RelativeLayout generalarea;
    private Vector<Integer> random_background_image_array;
    //{R.drawable.two,R.drawable.three,R.drawable.eight,R.drawable.ten};

    private int index = 0;
    private final int interval = 10000;
    private boolean isRunning = true;
    Runnable runnable;
    RelativeLayout rlMenuHomeDrawer;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
  //  private GoogleApiClient client;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        try {
            ViewGroup.inflate(this, R.layout.activity_drawer_layout_test, linearContentLayout);
            app = (App) getApplication();
            // check if GPS enabled
            //GPSTracker gpsTracker = new GPSTracker(this);

            linearContentLayout.setBackgroundColor(0x000000);
             headerlayout.setVisibility(View.GONE);
            headertitle.setText("HOME");



        random_background_image_array = new Vector<Integer>();

        random_background_image_array.add(R.drawable.two);
        random_background_image_array.add(R.drawable.three);
        random_background_image_array.add(R.drawable.eight);
        random_background_image_array.add(R.drawable.two);


        generalarea = (RelativeLayout) findViewById(R.id.generalarea);
        generalarea.setVisibility(View.VISIBLE);
        content_frame_image = (ImageSwitcher) findViewById(R.id.content_frame_image);
        app = (App)getApplication();
        Animation slide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottom_down);
        content_frame_image.startAnimation(slide);
        final Animation slideother = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottom_up);
        generalarea.startAnimation(slideother);

        restaurants = (LinearLayout) findViewById(R.id.restaurant);
        reservations = (LinearLayout) findViewById(R.id.reservation);
        promotions = (LinearLayout) findViewById(R.id.promotions);
        contacts = (LinearLayout) findViewById(R.id.contacts);

        rlMenuHomeDrawer = (RelativeLayout) findViewById(R.id.rlMenuHomeDrawer);
        rlMenuHomeDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.performClick();
            }
        });

        restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DrawerLayoutTest.this, RestaurantsActivity.class);
                i.putExtra("From", "MainScreen");
                startActivity(i);
                //finish();

            }
        });

        reservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DrawerLayoutTest.this, ReservationActivity.class);
                i.putExtra("From", "MainScreen");
                startActivity(i);
            }
        });

        promotions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DrawerLayoutTest.this, PromotionsActivity.class);
                i.putExtra("From", "MainScreen");
                startActivity(i);
            }
        });

        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DrawerLayoutTest.this, ContactActivity.class);
                i.putExtra("From", "MainScreen");
                startActivity(i);
            }
        });


        slideother.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                generalarea.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startAnimatedBackground();

            }
        }, 5000);

        AppFonts appFonts = new AppFonts();

            restauranttext = (TextView) findViewById(R.id.restauranttext);
            reservationtext = (TextView) findViewById(R.id.reservationtext);
            promotionstext = (TextView) findViewById(R.id.promotionstext);
            contactstext = (TextView) findViewById(R.id.contactstext);

            restauranttext.setTypeface(appFonts.getGriffithGothicBold(DrawerLayoutTest.this));
        reservationtext.setTypeface(appFonts.getGriffithGothicBold(DrawerLayoutTest.this));
        promotionstext.setTypeface(appFonts.getGriffithGothicBold(DrawerLayoutTest.this));
            contactstext.setTypeface(appFonts.getGriffithGothicBold(DrawerLayoutTest.this));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    protected void onPause() {
        isRunning = false;
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
        super.onPause();
    }

    private void startAnimatedBackground() {
        Animation aniIn = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in);
        aniIn.setDuration(3000);
        Animation aniOut = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out);
        aniOut.setDuration(3000);

        final ImageSwitcher imageSwitcher = (ImageSwitcher) findViewById(R.id.content_frame_image);
        imageSwitcher.setInAnimation(aniIn);
        imageSwitcher.setOutAnimation(aniOut);
        imageSwitcher.setFactory(DrawerLayoutTest.this);
        imageSwitcher.setImageResource(random_background_image_array.get(index));

        try {
            final Handler handler = new Handler();
            runnable = new Runnable() {

                @Override
                public void run() {
                    if (isRunning) {
                        index++;
                        index = index % random_background_image_array.size();
                        Log.d("Intro Screen", "Change Image " + index);
                        final BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        BitmapFactory.decodeResource(getResources(), random_background_image_array.get(index), options);
                        //
                        // final int REQUIRED_SIZE=95;

                        // Find the correct scale value. It should be the power of 2.
                   /* int scale = 1;
                    while(options.outWidth / scale / 2 >= REQUIRED_SIZE && options.outHeight / scale / 2 >= REQUIRED_SIZE) {
                        scale *= 2;
                    }*/

                        // Decode with inSampleSize
                        BitmapFactory.Options o2 = new BitmapFactory.Options();
                        ///   o2.inSampleSize = scale;
                        Bitmap bit = null;
                        bit = BitmapFactory.decodeResource(getResources(), random_background_image_array.get(index), o2);
                        Drawable drawable = new BitmapDrawable(bit);
                        imageSwitcher.setImageDrawable(drawable);
                        handler.postDelayed(this, interval);
                    } else {
                        handler.removeCallbacks(runnable);
                    }
                }
            };
            handler.postDelayed(runnable, interval);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void finish() {
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
        isRunning = false;
        super.finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer_layout_test, menu);
        return true;
    }


    @Override
    public View makeView() {
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));
        return imageView;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        /*client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "DrawerLayoutTest Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.drawerlayouttest/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);*/
    }

    @Override
    public void onStop() {

        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       /* Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "DrawerLayoutTest Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.drawerlayouttest/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();*/
    }

	/*@Override
    public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		Toast.makeText(this, "Clicked On Child" + v.getTag(),
				Toast.LENGTH_SHORT).show();
		return true;
	}*/

}
