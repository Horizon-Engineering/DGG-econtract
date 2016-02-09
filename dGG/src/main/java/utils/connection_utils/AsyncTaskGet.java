//package utils.connection_utils;
//
//import java.io.IOException;
//
////import org.apache.http.auth.UsernamePasswordCredentials;
////import org.apache.http.client.ClientProtocolException;
////import org.apache.http.client.ResponseHandler;
////import org.apache.http.client.methods.HttpGet;
////import org.apache.http.impl.auth.BasicScheme;
////import org.apache.http.impl.client.BasicResponseHandler;
////import org.apache.http.impl.client.DefaultHttpClient;
////import org.apache.http.params.BasicHttpParams;
//import org.apache.http.params.HttpConnectionParams;
//import org.apache.http.params.HttpParams;
//
//
//import android.content.Context;
//import android.os.AsyncTask;
//import utils.widget.MyProgressDialog;
//
///*Being used for executing Http POST request*/
//public class AsyncTaskGet extends AsyncTask<String, Void, String> {
//
//	MyProgressDialog Loading;
//	Context cont;
//	String resp;
//	GetDataDownloadListener dataDownloadListener;
//
//	public AsyncTaskGet(Context con) {
//		this.cont = con;
//		Loading = new MyProgressDialog(cont);
//		Loading.setCancelable(false);
//	}
//
//	private boolean shouldLoad = true;
//
//	// private Exception exception;
//	protected void onPreExecute() {
//		Loading.setMessage("Loading Data...");
//		if (shouldLoad)
//			Loading.show();
//	}
//
//	public void setPostDataDownloadListener(
//			GetDataDownloadListener dataDownloadListener) {
//		this.dataDownloadListener = dataDownloadListener;
//	}
//
//	protected String doInBackground(String... urls) {
//		try {
//			String url = urls[0];
//			System.out.println("Hitting Post URL : " + url);
//			int timeoutConnection = 12000;
//			HttpParams httpParameters = new BasicHttpParams();
//			HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
//			// Set the default socket timeout (SO_TIMEOUT)
//			// in milliseconds which is the timeout for waiting for data.
//			int timeoutSocket = 12000;
//			HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
//			DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
//			String finalURL = url.replaceAll(" ", "%20");
//			HttpGet request = new HttpGet(finalURL);
////			request.addHeader(BasicScheme.authenticate(
////					new UsernamePasswordCredentials(DataUtils.HTTP_HEADER_UN,
////							DataUtils.HTTP_HEADER_PWD), "UTF-8", false));
//			ResponseHandler<String> responseHandler = new BasicResponseHandler();
//			// send the variable and value, in other words post, to the URL
//			String response = httpClient.execute(request, responseHandler);
//			return response;
//
//		} catch (ClientProtocolException e) {
//
//			e.printStackTrace();
//			return null;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//
//		// System.out.println("RES :"+response_body);
//
//		// System.out.println("Sample :"+strJson);
//	}
//
//	protected void onPostExecute(String feed) {
//		if (Loading.isShowing()) {
//			Loading.dismiss();
//			// Log.d("test Data", feed);
//			// parseMyJSON(jsonStr);
//		}
//		if (feed != null) {
//			System.out.println("Json From Server : " + feed);
//			dataDownloadListener.dataDownloadedSuccessfully(feed);
//		} else {
//			dataDownloadListener.dataDownloadFailed();
//		}
//	}
//
//	public boolean isShouldLoad() {
//		return shouldLoad;
//	}
//
//	public void setShouldLoad(boolean shouldLoad) {
//		this.shouldLoad = shouldLoad;
//	}
//
//	public static interface GetDataDownloadListener {
//		void dataDownloadedSuccessfully(String data);
//
//		void dataDownloadFailed();
//	}
//
//}
