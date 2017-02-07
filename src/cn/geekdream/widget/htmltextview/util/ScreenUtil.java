package cn.geekdream.widget.htmltextview.util;

import android.content.Context;
import android.util.DisplayMetrics;

public class ScreenUtil {
	public static int getScreenHeight(Context context){
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.heightPixels;
	}
	public static int getScreenWidth(Context context){
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.widthPixels;
	}
}
