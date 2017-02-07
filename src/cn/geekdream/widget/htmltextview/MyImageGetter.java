package cn.geekdream.widget.htmltextview;

import java.io.File;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import cn.geekdream.htmltextview.R;
import cn.geekdream.widget.htmltextview.util.DownloadImageUtil;
import cn.geekdream.widget.htmltextview.util.DownloadImageUtil.OnDownloadListener;
import cn.geekdream.widget.htmltextview.util.ScreenUtil;

public class MyImageGetter implements Html.ImageGetter{

	private GetImageListener mListener;
	private Context mContext;
	
	public MyImageGetter(Context context,GetImageListener listener){
		this.mContext = context;
		mListener = listener;
	}
	
	@Override
	public Drawable getDrawable(String imgUrl) {
		// 只保留img连接的文件名
		String[] split = imgUrl.split("/");
		String fileName = split[split.length - 1];

		File imgFile = new File(mContext.getFilesDir(), fileName);
		// 如果存在这个图片文件，则直接返回
		if (imgFile.exists()) {
			Drawable drawable = Drawable.createFromPath(imgFile
					.getAbsolutePath());
			drawable.setBounds(0, 0, ScreenUtil.getScreenWidth(mContext)/2,
					drawable.getIntrinsicHeight()*(ScreenUtil.getScreenWidth(mContext)/2)/drawable.getIntrinsicWidth());
			return drawable;
		} else {
			// 否则，将图片从网络上下载下来
			DownloadImageUtil downloadImageUtil = new DownloadImageUtil();
			downloadImageUtil.downloadImage(mContext, imgUrl, imgFile,
					new OnDownloadListener() {

						@Override
						public void onSuccess() {
							// TODO Auto-generated method stub
							// 这时图片已经下载完了，再设置一次，就可以会执行上面的if里面的语句
							System.out.println("success");
							mListener.onSuccess();
						}

						@Override
						public void onFail() {
							// TODO Auto-generated method stub
							System.out.println("fail");
							mListener.onFail();

						}
					});
			// 还没下载完。直接返回“正在加载”的图片即可
			Drawable defaultDrawable = mContext.getResources().getDrawable(R.drawable.ic_launcher);
			defaultDrawable.setBounds(0, 0, ScreenUtil.getScreenWidth(mContext)/2,
					defaultDrawable.getIntrinsicHeight()*(ScreenUtil.getScreenWidth(mContext)/2)/defaultDrawable.getIntrinsicWidth());
			return defaultDrawable;
		}
	}
	
	public interface GetImageListener{
		void onSuccess();
		void onFail();
	}

}
