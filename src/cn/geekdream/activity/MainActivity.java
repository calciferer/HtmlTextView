package cn.geekdream.activity;

import android.app.Activity;
import android.os.Bundle;
import cn.geekdream.htmltextview.R;
import cn.geekdream.widget.htmltextview.HtmlTextView;

public class MainActivity extends Activity{

	private HtmlTextView htmlTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		htmlTextView = (HtmlTextView) findViewById(R.id.tv);
		final String htmlString = "测试图片信息：<br><img src=\"http://pic004.cnblogs.com/news/201211/20121108_091749_1.jpg\" /><img src=\"http://pic004.cnblogs.com/news/201211/20121108_091749_1.jpg\" />";
		htmlTextView.setHtmlText(htmlString);
		
	}
	
}
