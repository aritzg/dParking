package net.sareweb.android.dParking.custom;

import net.sareweb.android.dParking.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.googlecode.androidannotations.annotations.EViewGroup;
import com.googlecode.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.dashboard_button)
public class DashboardButton extends RelativeLayout {

	private String TAG ="DasboardButton";
	private Context context;
	@ViewById
	ImageView imgButton;
	@ViewById
	TextView txButtonText;
	Drawable image;
	String buttonText="";

	public DashboardButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
		TypedArray attrsArray = context.obtainStyledAttributes(attrs,
                R.styleable.DashboardButton);
		image  = attrsArray.getDrawable(R.styleable.DashboardButton_imgResId); 
		buttonText = attrsArray.getString(R.styleable.DashboardButton_buttonText);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		txButtonText.setText(buttonText);
		imgButton.setImageDrawable(image);
	}
}
