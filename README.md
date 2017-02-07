# HtmlTextView
Show Html(especially image resources) in TextView for Android . Very easy.<br>
在TextView中显示HTML内容，自动缓存网络图片到本地，图片适配屏幕，点击图片会放大.<br>
没有网络时显示默认图片，联网后点击默认图片会自动加载<br>
[www.geekdream.cn](http://www.geekdream.cn "GeekDream")  
##How to use?
###第一种：直接使用HtmlTextView
```Java
HtmlTextView htmlTextView = (HtmlTextView)findViewById(R.id.tv);
String htmlText = "...";//your htmlText
htmlTextView.setHtmlText(htmlText);
```
###第二种：为继承自TextView的控件设置html
```Java
Button button = (Button)findViewById(R.id.btn);
TextView,RadioButton,EditText...//all widgets that extends TextView
HtmlTextViewUtil util = new HtmlTextViewUtil(context);
util.setHtmlText(TextView tv, String htmlText);
```
