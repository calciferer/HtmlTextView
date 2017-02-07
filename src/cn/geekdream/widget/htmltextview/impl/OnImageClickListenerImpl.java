package cn.geekdream.widget.htmltextview.impl;

import java.io.File;

import uk.co.senab.photoview.PhotoViewAttacher;
import uk.co.senab.photoview.PhotoViewAttacher.OnViewTapListener;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import cn.geekdream.htmltextview.R;
import cn.geekdream.widget.htmltextview.HtmlTextView;
import cn.geekdream.widget.htmltextview.HtmlTextViewUtil;
import cn.geekdream.widget.htmltextview.interfaces.OnImageClickListener;
import cn.geekdream.widget.htmltextview.util.ScreenUtil;

/**
 * 实现OnImageClickListener
 * @author calcifer
 * @website www.geekdream.cn
 * @date 2017-2-7下午9:27:36
 *
 */
public class OnImageClickListenerImpl implements OnImageClickListener{

	private Context mContext;
	private HtmlTextView mHtmlTextView;
	private HtmlTextViewUtil mHtmlTextViewUtil;
	
	public OnImageClickListenerImpl(Context context,HtmlTextView htmlTextView){
		this.mContext = context;
		mHtmlTextView = htmlTextView;
	}
	
	public OnImageClickListenerImpl(Context context,HtmlTextViewUtil htmlTextViewUtil){
		this.mContext = context;
		mHtmlTextViewUtil = htmlTextViewUtil;
	}
	
	@Override
	public void onClick(View v, String imgUrl) {
		System.out.println(imgUrl);
		// TODO Auto-generated method stub
		//根据URL得到文件路径
		String[] split = imgUrl.split("/");
		String fileName = split[split.length - 1];

		File imgFile = new File(mContext.getFilesDir(), fileName);
		//如果文件存在，打开显示
		if (imgFile.exists()) {
			Drawable drawable = Drawable.createFromPath(imgFile
					.getAbsolutePath());
			drawable.setBounds(0, 0, ScreenUtil.getScreenWidth(mContext)/2,
					drawable.getIntrinsicHeight()*(ScreenUtil.getScreenWidth(mContext)/2)/drawable.getIntrinsicWidth());
			
			final Dialog dialog = new Dialog(mContext,R.style.Dialog_Fullscreen);
			ImageView imageView = new ImageView(mContext);
			imageView.setImageDrawable(drawable);
			PhotoViewAttacher attacher = new PhotoViewAttacher(imageView);
			attacher.setOnViewTapListener(new OnViewTapListener() {
				
				@Override
				public void onViewTap(View arg0, float arg1, float arg2) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});
			dialog.setContentView(imageView);
			WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
			lp.height = ScreenUtil.getScreenHeight(mContext);
			lp.width = ScreenUtil.getScreenWidth(mContext);
			dialog.getWindow().setAttributes(lp);
			dialog.show();
		}else {
			//否则刷新
			if (mHtmlTextView!=null) {
				mHtmlTextView.flushTextView();
			}
			if (mHtmlTextViewUtil!=null) {
				mHtmlTextViewUtil.flushTextView();
				
			}
		}
	}

}
