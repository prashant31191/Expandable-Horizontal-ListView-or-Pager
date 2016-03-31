package com.aurum.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aurum.R;




/**
 * This is an expandable grid view which extends GridView. By using this
 * ExpandableGridView, you can get OSX/iOS folder expand like experience. The
 * whole idea of the "expand" functionality is to add a cover layer beyond the
 * grid view and insert a sub grid view in the cover layer. This can achieve the
 * experience of split/expand the grid view.
 */
public class ExpandableGridView extends GridView implements OnTouchListener
{

	private WindowManager.LayoutParams mLayoutParams;
	private LinearLayout mCoverView;

	private ViewGroup mParentViewGroup;
	private boolean hasScrolled = false;
	private int scrollY = 0;
	private OnExpandItemClickListener mListener;

	int width, height;
	int middleViewHeight;
	int widthCount;

	private int viewLeftMargin;
	private int viewTopMargin;
	private int viewBottomMargin;
	private int viewRightMargin;
	private int lastMarginLeft;
	private int lastMarginRight;
	GridView gridView;
	public static ImageView imagenext,imagePrev;
	public ExpandableGridView(Context context) {
		this(context, null);

	}

	public ExpandableGridView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		width = context.getResources().getDisplayMetrics().widthPixels;
		height = context.getResources().getDisplayMetrics().heightPixels;

		Log.d("Height is ", "" + height);
		Log.d("Width is ", "" + width);
	}

	public ExpandableGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * Set listener for sub grid view item. When sub grid view item is clicked,
	 * it will invoke the listener's onItemClick function.
	 * 
	 * @param listener
	 */
	public void setOnExpandItemClickListener(OnExpandItemClickListener listener) {
		mListener = listener;
	}

	/**
	 * Expand the grid view under the clicked item.
	 * 
	 * @param clickedView
	 *            The clicked item.
	 * @param expandGridAdapter
	 *            Adapter set to sub grid view.
	 */
	@SuppressLint("NewApi")
	public void expandGridViewAtView(View clickedView,
			final ExhibitionSbListAdapter expandGridAdapter) {

		// 0. Init the cover layer
		mCoverView = new LinearLayout(getContext());
		mCoverView.setOrientation(LinearLayout.VERTICAL);

		// 1. Init the up, middle and down part views for the cover layer
		ImageView imageViewUp = new ImageView(getContext());
		ImageView imageViewDown = new ImageView(getContext());
		FrameLayout frame = new FrameLayout(getContext());
		frame.setLayoutParams(new LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
		HorizontalScrollView middleView = new HorizontalScrollView(getContext());

		// bottom position of clicked item view
		int touchBottom = clickedView.getBottom();
		// when the clicked item view is not fully showed, scroll up to make it
		// fully show
		if (touchBottom > (getMeasuredHeight() - getPaddingBottom() - getVerticalSpacing())) {
			hasScrolled = true;
			scrollY = touchBottom - getMeasuredHeight() + getPaddingBottom()
					+ getVerticalSpacing() / 2;
			scrollBy(0, scrollY);
		}
		// 2. Take snapshot of current grid view
		this.setDrawingCacheEnabled(true);
		Bitmap bitmap = Bitmap.createBitmap(this.getDrawingCache());
		this.destroyDrawingCache();// clear the draw cache, so that next time
		// will not get the same drawing

		int heightUp = 1;// height of up cover image
		int heightDown = 1;// height of down cover image

		if (height >= 1150 && height <= 1300) {
			middleViewHeight = clickedView.getHeight() + 200;
		} else if (height >= 775 && height <= 825) {
			middleViewHeight = clickedView.getHeight() + 210;
		} else if (height >= 1900 && height <= 1950) {
			middleViewHeight = clickedView.getHeight() + 350;
		} else if(height >= 300 && height <= 340){
			middleViewHeight = clickedView.getHeight() + 100;
		}
		else if(height >= 460 && height <= 500){
			middleViewHeight = clickedView.getHeight() + 110;
		}
		// height of middle sub grid view
		int bottomPos = bitmap.getHeight() - touchBottom - getVerticalSpacing()
				/ 2 - middleViewHeight;
		int upY = 0; // y position where up image start to split the image
		int downY = touchBottom;// y position where down image start to split
		// the image
		// if the middle sub grid view cannot fully display, decrease up cover
		// image's height of middleViewHeight
		// so that the cover layer can scroll up to make middle sub grid view
		// display


		if (bottomPos <= 0) {
			heightUp = touchBottom + getVerticalSpacing() / 2
					- middleViewHeight;
			upY = middleViewHeight;
			// down image height, decrease middle view height so that cover
			// layer height matches the grid view height
			heightDown = bitmap.getHeight() - heightUp - middleViewHeight;
			// when down image view cannot fully display, set it's height to the
			// bottom padding height
			Log.d("came in first", "if part");

			if (heightDown < 0) {
				Log.d("came in second", "if part");
				if (height >= 1150 && height <= 1300) {

					heightUp += heightDown;
					heightDown = 1;
					heightUp -= heightDown;
				} else if (height >= 775 && height <= 825) {
					heightUp += heightDown;
					heightDown = 1;
					heightUp -= heightDown;
				} 
				else if (height >= 1900 && height <= 1950) {
					heightUp += heightDown;
					heightDown = 1;
					heightUp -= heightDown;
				}
				else if (height >= 300 && height <= 340) {
					heightUp += heightDown;
					heightDown = 1;
					heightUp -= heightDown;
				} 
				else if (height >= 460 && height <= 500) {
					heightUp += heightDown;
					heightDown = 1;
					heightUp -= heightDown;
				} 

			} else {
				Log.d("came in first", "else part");
				if (height >= 1150 && height <= 1300) {
					bottomPos = 1;
					heightUp += heightDown;
					heightDown = bottomPos;
					heightUp -= heightDown;
				} else if (height >= 775 && height <= 825) {
					bottomPos = 1;
					heightUp += heightDown;
					heightDown = bottomPos;
					heightUp -= heightDown;
				}else if (height >= 1900 && height <= 1950) {
					bottomPos = 1;
					heightUp += heightDown;
					heightDown = bottomPos;
					heightUp -= heightDown;
				}
				else if (height >= 300 && height <= 340) {
					bottomPos = 1;
					heightUp += heightDown;
					heightDown = bottomPos;
					heightUp -= heightDown;
				} 
				else if (height >= 460 && height <= 500) {
					bottomPos = 1;
					heightUp += heightDown;
					heightDown = bottomPos;
					heightUp -= heightDown;
				} 


			}
			downY = upY + heightUp;
		} else {
			// when the middle view can fully display, decrease down image
			// view's height of middle view height
			Log.d("came in second", "else part");
			heightUp = touchBottom + getVerticalSpacing() / 2;
			heightDown = bottomPos;
		}

		Log.d("Bitmap Width", ""+bitmap.getWidth());
		Log.d("heightUp", ""+heightUp);
		Log.d("bottomPos", ""+bottomPos);
		Log.d("upY", ""+upY);
		Log.d("downY", ""+downY);
		Log.d("heightDown", ""+heightDown);

		// 3. Split the snapshot image to up/down image
		Bitmap bitmapUp = Bitmap.createBitmap(bitmap, 0, upY,
				bitmap.getWidth(), heightUp);
		Bitmap bitmapDown = Bitmap.createBitmap(bitmap, 0, downY,
				bitmap.getWidth(), heightDown);
		// add click handler to the up/down image view: collapse the expand grid
		// view
		imageViewUp.setImageBitmap(bitmapUp);
		imageViewUp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				collapseGridView();
			}
		});
		imageViewDown.setImageBitmap(bitmapDown);
		imageViewDown.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				collapseGridView();
			}
		});

		// 4. Middle sub grid view, set it's to one row, horizontal scrollable
		// grid view
		LinearLayout linearLayout = new LinearLayout(getContext());
		linearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		gridView = new GridView(getContext());
		gridView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		int count = expandGridAdapter.getCount();
		int vSpace = getVerticalSpacing();
		int hSpace = getHorizontalSpacing();

		if (width >= 700 && width <= 725) {
			widthCount = 320;
		} else if (width >= 470 && width <= 490) {
			widthCount = 250;
		} else if (width >= 1050 && width <= 1100) {
			widthCount = 470;
		}
		else if(width >= 220 && width <= 260)
		{
			widthCount = 100;
		}
		else if(width >= 300 && width <= 340)
		{
			widthCount = 150;
		}
		LayoutParams gridParams = new LayoutParams(count
				* (getColumnWidth() + hSpace + widthCount),
				ViewGroup.LayoutParams.WRAP_CONTENT);
		gridView.setLayoutParams(gridParams);
		gridView.setColumnWidth(getColumnWidth());
		gridView.setHorizontalSpacing(hSpace);
		gridView.setVerticalSpacing(vSpace);
		gridView.setNumColumns(count);

		//gridView.setPadding(hSpace, vSpace, hSpace, vSpace);
		gridView.setAdapter(expandGridAdapter);

		imagenext = new ImageView(getContext());
		FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		lp.gravity=Gravity.CENTER|Gravity.RIGHT;
		lp.rightMargin=10;
		lp.bottomMargin=20;
		// imagenext.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		imagenext.setLayoutParams(lp);
		imagenext.setBackgroundResource(R.drawable.notification_template_icon_bg);
		imagenext.bringToFront();


		FrameLayout.LayoutParams lp2 = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		lp2.gravity=Gravity.CENTER|Gravity.LEFT;
		lp2.leftMargin=10;
		lp2.bottomMargin=20;
		imagePrev = new ImageView(getContext());
		imagePrev.setLayoutParams(lp2);
		imagePrev.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_18dp);
		imagePrev.bringToFront();
		imagePrev.setVisibility(View.GONE);
		// 5. Set sub grid view's item click listener
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long l) {
				if (mListener != null) {
					mListener.onItemClick(position,
							expandGridAdapter.getItem(position));
				}
			}
		});



		//linearLayout.addView(imagenext);
		linearLayout.addView(gridView);
		//linearLayout.addView(imagePrev);


		// Triangle arrow up
		int touchX = clickedView.getLeft() + getColumnWidth() / 2;
		int touchY = heightUp;
		Canvas canvas = new Canvas(bitmapUp);// use Canvas to draw triangle in
		// the up cover image
		Path path = new Path();
		path.moveTo(touchX - 15, touchY);
		path.lineTo(touchX + 15, touchY);
		path.lineTo(touchX, touchY - 15);
		path.lineTo(touchX - 15, touchY);
		ShapeDrawable circle = new ShapeDrawable(new PathShape(path,
				getWidth(), getHeight()));
		circle.getPaint().setColor(Color.WHITE);
		circle.setBounds(0, 0, getWidth(), getHeight());
		circle.draw(canvas);

		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(getWidth(),
				middleViewHeight);
		middleView.setLayoutParams(params);
		middleView.setBackgroundColor(Color.WHITE);

		frame.addView(middleView);
		frame.bringChildToFront(imagePrev);
		frame.bringChildToFront(imagenext);
		frame.addView(imagePrev);
		frame.addView(imagenext);

		// 6. Add the up/middle/down sub views to the cover layer
		middleView.addView(linearLayout);
		mCoverView.addView(imageViewUp);
		mCoverView.addView(frame);
		mCoverView.addView(imageViewDown);

		// 7. Add the cover layer to the grid view and bring it to the front
		mParentViewGroup = (ViewGroup) getParent();
		mLayoutParams = new WindowManager.LayoutParams();
		mLayoutParams.format = PixelFormat.TRANSLUCENT;
		mLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;
		mLayoutParams.x = getLeft();// start x
		mLayoutParams.y = getTop(); // start y
		mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
		mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
		mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
				| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
		mParentViewGroup.addView(mCoverView, 0, mLayoutParams);
		mCoverView.bringToFront();

		gridView.setOnTouchListener(this);
		LinearLayout.LayoutParams lParams = (LinearLayout.LayoutParams) gridView
				.getLayoutParams();
		lastMarginLeft=0;
		lastMarginRight=0;
		lastMarginLeft = lParams.leftMargin;
		lastMarginRight = lParams.rightMargin;





	}

	/**
	 * Collapse the grid view and remove the cover layer
	 */
	public void collapseGridView() {
		// remove the cover layer
		if (mParentViewGroup != null && mCoverView != null) {
			mCoverView.removeAllViews();
			mParentViewGroup.removeView(mCoverView);
			mLayoutParams = null;
			mCoverView = null;
			mParentViewGroup = null;
		} else {
			Log.d("came in second", "else part");
		}
		// if the grid view has scrolled before expand, scroll it back
		if (hasScrolled) {
			scrollBy(0, -scrollY);
			hasScrolled = false;
			scrollY = 0;
		} else {
			Log.d("came in", "else part");
		}
	}

	/**
	 * Sub item click listener interface
	 */
	public interface OnExpandItemClickListener 
	{
		public void onItemClick(int position, Object clickPositionData);
	}


	@Override
	public boolean onTouch(View view, MotionEvent event) {
		// TODO Auto-generated method stub
		final int X = (int) event.getRawX();
		Log.d("came in","touch event");
		switch (event.getAction() & MotionEvent.ACTION_MASK) {

		case MotionEvent.ACTION_DOWN:
			Log.d("came in","action down");
			LinearLayout.LayoutParams lParams = (LinearLayout.LayoutParams) view
					.getLayoutParams();
			viewLeftMargin = X - lParams.leftMargin;
			viewRightMargin = X - lParams.rightMargin;
			break;

		case MotionEvent.ACTION_MOVE:
			Log.d("came in","action move");
			LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view
					.getLayoutParams();
			layoutParams.rightMargin = X - viewRightMargin;
			layoutParams.leftMargin = X - viewLeftMargin;
			//view.setLayoutParams(layoutParams);
			Log.d("lastMarginLeft", ""+lastMarginLeft);
			Log.d("lastMarginRight", ""+lastMarginRight);
			Log.d("Layout Param RIGHT", ""+layoutParams.rightMargin);
			Log.d("Layout Param LEFT", ""+layoutParams.leftMargin);


			if (layoutParams.leftMargin >= lastMarginLeft) {
				Log.d("came in","first");
				lastMarginLeft = layoutParams.leftMargin;
				lastMarginRight = layoutParams.rightMargin; // Here



				imagenext.setVisibility(View.VISIBLE);
				imagePrev.setVisibility(View.GONE);




			}else {
				Log.d("came in","second");
				lastMarginRight = layoutParams.rightMargin;
				lastMarginLeft = layoutParams.leftMargin; // and here
				imagenext.setVisibility(View.GONE);
				imagePrev.setVisibility(View.VISIBLE);


			}
			break;
		}
		return true;
		
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
	    return false;
	}
}
