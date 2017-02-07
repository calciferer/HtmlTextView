package cn.geekdream.widget.htmltextview.util;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.content.Context;
import android.os.AsyncTask;

/**
 * 下载图片类
 * 
 * @author calcifer
 * @website www.geekdream.cn
 * @date 2017-2-5上午11:57:48
 * 
 */
public class DownloadImageUtil {
	/**
	 * 
	 * @param context
	 * @param imgUrl
	 * @param imgFile
	 *            储存到哪个file
	 * @param listener
	 */
	public void downloadImage(final Context context, final String imgUrl,
			final File imgFile, final OnDownloadListener listener) {
		// URL url = new URL(urlString.replace(" ", "%20"));
		// 启动新线程下载

		new AsyncTask<Void, Void, Boolean>() {

			@Override
			protected Boolean doInBackground(Void... params) {
				// TODO Auto-generated method stub
				try {
					URL url = new URL(imgUrl);
					URLConnection con = url.openConnection();
					con.setConnectTimeout(5 * 1000);
					InputStream is = con.getInputStream();
					byte[] bs = new byte[1024];
					int len;
					OutputStream outputStream = context.openFileOutput(
							imgFile.getName(), Context.MODE_PRIVATE);
					while ((len = is.read(bs)) != -1) {
						outputStream.write(bs, 0, len);
					}
					outputStream.close();
					is.close();
					return Boolean.TRUE;
					// listener.onSuccess();
				} catch (Exception e) {
					// TODO: handle exception
					// listener.onFail();
					e.printStackTrace();
					return Boolean.FALSE;
				}
			}

			@Override
			protected void onPostExecute(Boolean result) {
				super.onPostExecute(result);
				boolean isSuccess = result.booleanValue();
				if (isSuccess) {
					listener.onSuccess();
				} else {
					listener.onFail();
				}

			}

		}
		.execute();

		// new Thread(new Runnable() {
		//
		// @Override
		// public void run() {
		// try {
		// URL url = new URL(imgUrl);
		// URLConnection con = url.openConnection();
		// con.setConnectTimeout(5 * 1000);
		// InputStream is = con.getInputStream();
		// byte[] bs = new byte[1024];
		// int len;
		// OutputStream outputStream = context.openFileOutput(
		// imgFile.getName(), Context.MODE_PRIVATE);
		// while ((len = is.read(bs)) != -1) {
		// outputStream.write(bs, 0, len);
		// }
		// outputStream.close();
		// is.close();
		//
		// listener.onSuccess();
		// } catch (Exception e) {
		// // TODO: handle exception
		// listener.onFail();
		// e.printStackTrace();
		// }
		//
		// }
		// }).start();
	}

	public interface OnDownloadListener {
		void onSuccess();

		void onFail();
		// 下面两个也可以添加
		// void onProgress(int progress);
		// void onFail(String failReason);
	}
}