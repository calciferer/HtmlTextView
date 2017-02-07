package cn.geekdream.widget.htmltextview;

import java.io.File;

import uk.co.senab.photoview.PhotoViewAttacher;
import uk.co.senab.photoview.PhotoViewAttacher.OnViewTapListener;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.geekdream.htmltextview.R;
import cn.geekdream.widget.htmltextview.MyImageGetter.GetImageListener;
import cn.geekdream.widget.htmltextview.impl.OnImageClickListenerImpl;
import cn.geekdream.widget.htmltextview.interfaces.OnImageClickListener;
import cn.geekdream.widget.htmltextview.util.ScreenUtil;

/**
 * textview解析html，并显示从网络上获取的img
 * 
 * @author calcifer
 * @website www.geekdream.cn
 * @date 2017-2-5上午11:49:57
 * 
 */
public class HtmlTextView extends TextView{
	
	
	
	@SuppressLint("NewApi")
	public HtmlTextView(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
	}

	public HtmlTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public HtmlTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public HtmlTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	private String htmlText;

	private MyImageGetter imageGetter = new MyImageGetter(getContext(), new GetImageListener() {
		
		@Override
		public void onSuccess() {
			// TODO Auto-generated method stub
			flushTextView();
		}
		@Override
		public void onFail() {
			// TODO Auto-generated method stub
			Toast.makeText(getContext(), "请连接网络后再点击图片显示", Toast.LENGTH_LONG).show();
		}
	});
	private MyTagHandler tagHandler = new MyTagHandler(getContext(), new OnImageClickListenerImpl(getContext(), this));
	
	
	/**
	 * 只用调用此方法即可
	 * 
	 * @param htmlText
	 *            输入htmlText
	 */
	public void setHtmlText(String htmlText) {
		setMovementMethod(LinkMovementMethod.getInstance());
		this.htmlText = htmlText;
		flushTextView();
	}

	public void flushTextView() {
		setText(Html.fromHtml(htmlText, imageGetter, tagHandler));
	}

}
