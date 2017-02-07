package cn.geekdream.widget.htmltextview;

import java.io.File;

import uk.co.senab.photoview.PhotoViewAttacher;
import uk.co.senab.photoview.PhotoViewAttacher.OnViewTapListener;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
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
 * 适用于所有继承自TextView的控件，如Button,RadioButton等
 * 
 * @author calcifer
 * @website www.geekdream.cn
 * @date 2017-2-6下午12:54:56
 * 
 */
public class HtmlTextViewUtil {
	private String htmlText;
	private Context mContext;
	private TextView mTextView;

	public HtmlTextViewUtil(Context context) {
		this.mContext = context;
	}

	private MyImageGetter imageGetter = new MyImageGetter(mContext,
			new GetImageListener() {

				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					flushTextView();
				}

				@Override
				public void onFail() {
					// TODO Auto-generated method stub
					Toast.makeText(mContext, "请连接网络后再点击图片显示", Toast.LENGTH_LONG).show();
				}
			});
	
private MyTagHandler tagHandler = new MyTagHandler(mContext, new OnImageClickListenerImpl(mContext,this));

	/**
	 * 只用调用此方法即可
	 * 
	 * @param htmlText
	 *            输入htmlText
	 */
	public void setHtmlText(TextView tv, String htmlText) {
		mTextView = tv;
		mTextView.setMovementMethod(LinkMovementMethod.getInstance());
		this.htmlText = htmlText;
		flushTextView();
	}

	public void flushTextView() {
		mTextView.setText(Html.fromHtml(htmlText, imageGetter, tagHandler));
	}
}
