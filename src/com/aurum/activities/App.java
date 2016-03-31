package com.aurum.activities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import org.json.JSONArray;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.widget.TextView;
import android.widget.Toast;

public class App extends Application {
	
	

	public ArrayList<ExhibitionMainListModel> arrayExhibitionList;
	public ArrayList<ExhibitionSubListModel> arrayExhibitionChildList;
	
	

	public static String GCM_SENDER_ID = "811668447152"; // 811668447152 gcm
	// sender id
	public static String reg_id;
	public static String regid = "";
	public static final int BACKOFF_MILLI_SECONDS = 2000;
	public static final Random random = new Random();
	public static final int MAX_ATTEMPTS = 5;

	



	

	static Context con;
	public static final String WEB_API_URL = "https://ishtoapp.com/ist-service.php";
	//public static final String WEB_API_URL = "https://ishtoapp.com/dev/ist-service.php";
	public static final String USER_PROFILE = "https://ishtoapp.com/upload/user_img/profile_30/"; // both
	//public static final String USER_PROFILE = "https://ishtoapp.com/dev/upload/user_img/profile_30/"; // both
	public static final String UPLOADIMAGE_PATH = "https://ishtoapp.com/"; // both
	//public static final String UPLOADIMAGE_PATH = "https://ishtoapp.com/dev/"; // both

	public static final String USER_COVER_IMAGE = "https://ishtoapp.com/upload/cover_img/";
	//public static final String USER_COVER_IMAGE = "https://ishtoapp.com/dev/upload/cover_img/";

	public static final String EXHIVITION_IMAGE = "https://ishtoapp.com/upload/cat_pic/";
	//public static final String EXHIVITION_IMAGE = "https://ishtoapp.com/dev/upload/cat_pic/";

	public static final String Sub_Product_Path = "https://ishtoapp.com/upload/subcat_img/";
	//public static final String Sub_Product_Path = "https://ishtoapp.com/dev/upload/subcat_img/";

	public static final String PRODUCT_IMAGE = "https://ishtoapp.com/upload/itm_image/"; // both
	//public static final String PRODUCT_IMAGE = "https://ishtoapp.com/dev/upload/itm_image/"; // both

	public static final String COMPANY_IMAGE = "https://ishtoapp.com/upload/company/";
	//public static final String COMPANY_IMAGE = "https://ishtoapp.com/dev/upload/company/";

	public static final String METHOD_REGISTER = "Register";
	public static final String METHOD_VALIDATE_USERNAME = "ValidateUsername";
	public static final String METHOD_VALIDATE_EMAIL = "ValidateEmail";
	public static final String METHOD_VERIFY_ACCOUNT = "verify_acBymsg";
	public static final String METHOD_LOGIN = "Login";
	public static final String METHOD_OAUTH = "Oauth";
	public static final String METHOD_LOGOUT = "Logout";
	public static final String METHOD_FORGET_PASSWORD = "Forgot_pwd";
	public static final String METHOD_EXHIBITION_LIST = "Exhibitn_list";
	public static final String METHOD_EXHIBITION_LIST_CHILD = "Discsion_list";
	public static final String METHOD_CHANGE_PASSWORD = "Change_pwd";
	public static final String METHOD_COUNTRY_LIST = "Country_list";
	public static final String METHOD_COUNTRY_ALL = "Country_All";
	public static final String METHOD_PROFILE_EDIT = "Profile_Edit";
	public static final String LIST_PARTICIPANT = "cusr_det";
	public static final String METHOD_VIEW_PROFILE = "View_profile";
	public static final String METHOD_ADD_FEEDBACK = "Add_Feedbk";
	public static final String METHOD_FEEDBACK_TYPE_LIST = "FeedbktypeList";
	public static final String METHOD_FOLLOW = "Follow";
	public static final String METHOD_UNFOLLOW = "Unfollow";
	public static final String METHOD_REVIEW = "Review";
	public static final String METHOD_CATEGORY = "Category";
	public static final String METHOD_SUB_CATEGORY = "Sub_Category";
	public static final String METHOD_ADD_PRODUCT = "ProdAdd";
	public static final String METHOD_DETAIL_PRODUCT = "Detail_product";
	public static final String METHOD_DISCOVER_PRODUCT = "discover_Product";
	public static final String METHOD_LIST_PRODUCT = "List_Product";
	public static final String METHOD_LIST_PRODUCTGRID = "List_ProductGrid";
	public static final String METHOD_BLOCK_USER_LIST = "ListallBlockusr";
	public static final String METHOD_SET_BLOCK_USER = "Block_User";
	public static final String METHOD_SET_UNBLOCK_USER = "Unblock_User";
	public static final String METHOD_REPORT_ABUSE_USER = "report_AbuseUsr";
	public static final String METHOD_GET_MY_INFO = "Get_UsrInfo";
	public static final String METHOD_GET_EDIT_PERSONAL_MY_INFO = "Get_UsrForedit";
	public static final String METHOD_LIST_TIMELINE_FEED = "List_TimelineFeed";

	public static final String METHOD_FOLLOWING_LIST = "ListFollowing";

	public static final String METHOD_DELETE_PRODUCT = "Delete_Product";
	public static final String METHOD_DEACTIVE_ACCOUNT = "Delete_Ac";
	public static final String METHOD_CREATE_PROFILE = "create_Profile";
	public static final String METHOD_LIST_SEARCH_PRODUCT = "Msearch";
	public static final String METHOD_PUSH = "Mpush_Notification";
	public static final String METHOD_CREATE_NOTIFICATION = "create_notificaiton";
	public static final String METHOD_INTEREST = "Interest_list";
	public static final String METHOD_GETUSERID = "Get_UsrId";
	public static final String METHOD_UPLOADCHAT = "upload_chat";
	public boolean login = false;
	public boolean isOnline = false;
	public boolean isNotify=false;
	public static int counter;

	
	//message count webuk01
	public int msgCount = 0;

	public Boolean  blnGetNewMessage = false;

	public static final String ACTIVITY_HOME="Login Home";
	public static final String ACTIVITY_SIGNUP="Register";
	public static final String ACTIVITY_LOGIN="Login";
	public static final String ACTIVITY_ADDPRODUCT="Add Product";
	public static final String ACTIVITY_BLOCKEDPEOPLE="Block List";
	public static final String ACTIVITY_CONVO="Convo";
	public static final String ACTIVITY_CONVOPRIVATECHAT="Private Convo";
	public static final String ACTIVITY_GROUPCHAT="Room Conversation";
	public static final String ACTIVITY_CREATEPROFILE="Create Profile";
	public static final String ACTIVITY_EDITCOMPANYPROFILE="Edit Company profile";
	public static final String ACTIVITY_EDITPERSONALPROFILE="Edit Personal profile";
	public static final String ACTIVITY_EXHIBITIONMAIN="Exhibition List";
	public static final String ACTIVITY_EXHIBITIONSUB="Room";
	public static final String ACTIVITY_FEEDBACK="Feedback Form";
	public static final String ACTIVITY_FORGETPASSWORD="Forgot Password";
	public static final String ACTIVITY_MYPROFILE="My profile";
	public static final String ACTIVITY_OTHERPROFILE="Other profile";
	public static final String ACTIVITY_IMAGEVIEWING="Image View";
	public static final String ACTIVITY_PRODUCTDETAIL="Product Detail";
	public static final String ACTIVITY_TIMELINE="Timeline";
	public static final String ACTIVITY_SEARCHPRODUCT="Search Product";
	public static final String ACTIVITY_CHANGEPASSWORD="Change Password";
	public static final String ACTIVITY_SETTINGS="Setting";
	public static final String ACTIVITY_SHOWCASE="Showcase";
	public static final String ACTIVITY_SHOWCASESUBDETAILS="Showcase SubDetails";
	public static final String EVENT_ADDPRODUCT="";

	private boolean inForeground = true;
	private int resumed = 0;
	private int paused = 0;
	public JSONArray resultTableChat,resultTableChatList,resultGroupList;
	
	Timer timer;
	@Override
	public void onCreate() {
		super.onCreate();
		

	}

	

	public void fontFace(String str, TextView txtView) {
		Typeface t = Typeface.createFromAsset(getAssets(), str);
		txtView.setTypeface(t);
	}

	public static boolean isInternetAvail(Context context) {
		ConnectivityManager connect = (ConnectivityManager) context
				.getSystemService(context.CONNECTIVITY_SERVICE);
		android.net.NetworkInfo wifi = connect
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		android.net.NetworkInfo mobile = connect
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

		if (wifi.isConnected()) {
			return true;
		} else if (!mobile.isConnected()) {
			return false;
		} else if (mobile.isConnected()) {
			return true;
		}
		return false;
	}

	public void showToast(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();

	}

	public String convertStreamToString(InputStream is) {
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(is));
		BufferedReader reader;
		StringBuilder sb = null;
		try {
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"),
					8000);
			sb = new StringBuilder();

			String line = null;
			try {
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		return sb.toString();
	}

	
	public void foregroundOrBackground()
	{
		if( paused >= resumed && inForeground )
		{
			inForeground = false;
		}
		else if( resumed > paused && !inForeground )
		{
			inForeground = true;
		}
	}

}

