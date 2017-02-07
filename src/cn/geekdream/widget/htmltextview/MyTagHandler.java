package cn.geekdream.widget.htmltextview;

import java.util.Locale;

import org.xml.sax.XMLReader;

import cn.geekdream.widget.htmltextview.interfaces.OnImageClickListener;

import android.content.Context;
import android.text.Editable;
import android.text.Html.TagHandler;
import android.text.Spanned;
import android.text.style.ImageSpan;

public class MyTagHandler implements TagHandler{
	
	private Context mContext;
//	private HtmlTextView mHtmlTextView;
	private OnImageClickListener mListener;

	public MyTagHandler (Context context , OnImageClickListener listener){
		this.mContext = context;
//		this.mHtmlTextView = htmlTextView;
		mListener = listener;
	}
	
	@Override
	public void handleTag(boolean opening, String tag, Editable output,
			XMLReader xmlReader) {
		if (tag.toLowerCase(Locale.getDefault()).equals("img")) {
			// 获取长度
			int len = output.length();
			// 获取图片地址
			ImageSpan[] images = output.getSpans(len - 1, len,
					ImageSpan.class);
			String imgPath = images[0].getSource();
			// 使图片可点击并监听点击事件
			output.setSpan(new ClickableImage(mContext,mListener,imgPath), len - 1,
					len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			System.out.println("setspan:"+imgPath);
		}
	}

}
