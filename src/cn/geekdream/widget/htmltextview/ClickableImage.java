package cn.geekdream.widget.htmltextview;

import cn.geekdream.widget.htmltextview.interfaces.OnImageClickListener;
import android.content.Context;
import android.text.style.ClickableSpan;
import android.view.View;

public class ClickableImage extends ClickableSpan{
	
	private Context mContext;
	private OnImageClickListener mListener;
	private String imgPath; 
	
	public ClickableImage(Context context,OnImageClickListener listener,String imgPath){
		this.mContext = context;
		mListener = listener;
		this.imgPath = imgPath;
	}

	@Override
	public void onClick(View widget) {
		// TODO Auto-generated method stub
		mListener.onClick(widget,imgPath);
	}
	

}
