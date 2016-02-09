//package utils.connection_utils;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//
////import org.apache.http.auth.UsernamePasswordCredentials;
////import org.apache.http.client.ClientProtocolException;
////import org.apache.http.client.ResponseHandler;
////import org.apache.http.client.methods.HttpPost;
////import org.apache.http.entity.StringEntity;
////import org.apache.http.impl.auth.BasicScheme;
////import org.apache.http.impl.client.BasicResponseHandler;
////import org.apache.http.impl.client.DefaultHttpClient;
////import org.apache.http.params.BasicHttpParams;
//import org.apache.http.params.HttpConnectionParams;
//import org.apache.http.params.HttpParams;
//
//import android.content.Context;
//import android.os.AsyncTask;
//import utils.widget.MyProgressDialog;
//
///*Being used for executing Http POST request*/
//public class AsyncTaskPost extends AsyncTask<String, Void, String> {
//
//	MyProgressDialog Loading;
//	Context cont;
//	String resp;
//	PostDataDownloadListener dataDownloadListener;
//	// MultipartEntity post_data;
//	StringEntity data;
//	private String message;
//
//	public AsyncTaskPost(Context con, String data) {
//		this.cont = con;
//		try {
//			this.data = new StringEntity(data);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Loading = new MyProgressDialog(cont);
//		Loading.setCancelable(false);
//		if(message!=null)
//			Loading.setMessage(message);
//	}
//
//	// public AsyncTaskPost(Context con, MultipartEntity postData) {
//	// this.cont = con;
//	// this.post_data = postData;
//	//
//	// Loading = new ProgressDialog(cont);
//	// Loading.setCancelable(false);
//	// }
//
//	// private Exception exception;
//	protected void onPreExecute() {
//		Loading.setMessage("Loading Data...");
//		Loading.show();
//	}
//
//	public void setPostDataDownloadListener(
//			PostDataDownloadListener dataDownloadListener) {
//		this.dataDownloadListener = dataDownloadListener;
//	}
//
//	protected String doInBackground(String... urls) {
//		try {
//			String url = urls[0];
//			System.out.println("Hitting Post URL : " + url);
//			int timeoutConnection = 12000;
//			HttpParams httpParameters = new BasicHttpParams();
//			HttpConnectionParams.setConnectionTimeout(httpParameters,
//					timeoutConnection);
//			// Set the default socket timeout (SO_TIMEOUT)
//			// in milliseconds which is the timeout for waiting for data.
//			int timeoutSocket = 12000;
//			HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
//			DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
//			String finalURL = url.replaceAll(" ", "%20");
//			HttpPost request = new HttpPost(finalURL);
////			request.addHeader(BasicScheme.authenticate(
////					new UsernamePasswordCredentials(DataUtils.HTTP_HEADER_UN,
////							DataUtils.HTTP_HEADER_PWD), "UTF-8", false));
//			ResponseHandler<String> responseHandler = new BasicResponseHandler();
//			// if (data != null)
//			request.setEntity(data);
//			// else
//			// request.setEntity(post_data);
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
//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}
//
//	public static interface PostDataDownloadListener {
//		void dataDownloadedSuccessfully(String data);
//
//		void dataDownloadFailed();
//	}
//
//}
