package com.tow.sso.codegears_sso_android.http;

import android.util.Log;


import com.tow.sso.codegears_sso_android.model.InputItem;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HttpCall {

	public static void RQ_GET_CACHE(final String url, final RQListener listener, final Object state, final String cache_name) {
		// String catch_data = LocalFileHelper.readCatchFromSD(cache_name);
		// if (!catch_data.equals("")) {
		// listener.onComplete(200, catch_data, state);
		// }
		//
		// RQ_GET(url, listener, state);
	}

	public static void RQ_GET(final String url, final RQListener listener, final Object state) {
		new Thread() {

			@Override
			public void run() {
				try {
					String url_encode = url;

					url_encode = url_encode.replace(" ", "%20");
					url_encode = url_encode.replace("\"", "%22");
					url_encode = url_encode.replace("#", "%23");
					url_encode = url_encode.replace("{", "%7B");
					url_encode = url_encode.replace("}", "%7D");
					url_encode = url_encode.replace("<", "%3C");
					url_encode = url_encode.replace(">", "%3E");

					Log.i("MyLog", "url_encode=" + url_encode);

					HttpRS resp = this.requestGet(url_encode);
					listener.onComplete(resp.response_code, resp.response_text, state);
				} catch (FileNotFoundException e) {
					listener.onFileNotFoundException(e, state);
				} catch (MalformedURLException e) {
					listener.onMalformedURLException(e, state);
				} catch (IOException e) {
					listener.onIOException(e, state);
				}
			}

			public HttpRS requestGet(String url) throws FileNotFoundException, MalformedURLException, IOException {
				BufferedReader in = null;
				StringBuffer sb = new StringBuffer("");
				try {
					HttpClient client = new DefaultHttpClient();
					HttpGet request = new HttpGet();
					request.setURI(new URI(url));
					HttpResponse response = client.execute(request);
					in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

					String line = "";
					String NL = System.getProperty("line.separator");
					while ((line = in.readLine()) != null) {
						sb.append(line + NL);
					}
					in.close();
					// String page = sb.toString();
					// System.out.println(page);
				} catch (Exception ex) {
					Log.i("MyLog", "(Exception) HttpCall.RQ_GET:" + ex.getMessage());
					return new HttpRS(-1, ex.getMessage());
				} finally {
					if (in != null) {
						try {
							in.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				return new HttpRS(200, sb.toString());
			}

		}.start();
	}

	public static void RQ_POST(final String url, final ArrayList<InputItem> params, final RQListener listener, final Object state) {
		new Thread() {

			@Override
			public void run() {
				try {
					String url_encode = url;

					url_encode = url_encode.replace(" ", "%20");
					url_encode = url_encode.replace("\"", "%22");
					url_encode = url_encode.replace("#", "%23");
					url_encode = url_encode.replace("{", "%7B");
					url_encode = url_encode.replace("}", "%7D");
					url_encode = url_encode.replace("<", "%3C");
					url_encode = url_encode.replace(">", "%3E");

					Log.i("MyLog", "url_encode=" + url_encode);

					HttpRS resp = this.requestPost(url_encode, params);
					listener.onComplete(resp.response_code, resp.response_text, state);
				} catch (FileNotFoundException e) {
					listener.onFileNotFoundException(e, state);
				} catch (MalformedURLException e) {
					listener.onMalformedURLException(e, state);
				} catch (IOException e) {
					listener.onIOException(e, state);
				}
			}

			public HttpRS requestPost(String url, ArrayList<InputItem> params) throws FileNotFoundException, MalformedURLException, IOException {
				// do this wherever you are wanting to POST
				URL obj_url;
				HttpURLConnection conn;

				try {
					// if you are using https, make sure to import
					// java.net.HttpsURLConnection
					obj_url = new URL(url);

					// you need to encode ONLY the values of the parameters
					String string_param = "";
					// String param = "facebookId=" +
					// URLEncoder.encode(UserLogin.FacebookUID, "UTF-8")
					// + "&accessToken=" +
					// URLEncoder.encode(Utility.mFacebook.getAccessToken(),
					// "UTF-8");
					String str_and = "";
					if (params != null) {
						for (int i = 0; i < params.size(); i++) {
							InputItem item = params.get(i);
							string_param += str_and + item.key + "=" + URLEncoder.encode(item.value, "UTF-8");
							str_and = "&";
						}
					}

					conn = (HttpURLConnection) obj_url.openConnection();
					// set the output to true, indicating you are
					// outputting(uploading)
					// POST data
					conn.setDoOutput(true);
					// once you set the output to true, you don't really need to
					// set the
					// request method to post, but I'm doing it anyway
					conn.setRequestMethod("POST");

					// Android documentation suggested that you set the length
					// of the
					// data you are sending to the server, BUT
					// do NOT specify this length in the header by using
					// conn.setRequestProperty("Content-Length", length);
					// use this instead.
					conn.setFixedLengthStreamingMode(string_param.getBytes().length);
					conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
					// send the POST out
					PrintWriter out = new PrintWriter(conn.getOutputStream());
					out.print(string_param);
					out.close();

					// build the string to store the response text from the
					// server
					String response = "";

					// start listening to the stream
					int response_code = conn.getResponseCode();
					Log.i("MyLog", "(getResponseCode=" + conn.getResponseCode() + ")");
					Scanner s;
					if (conn.getResponseCode() != 200) {
						s = new Scanner(conn.getErrorStream());

						// process the stream and store it in StringBuilder
						while (s.hasNextLine()) {
							response += (s.nextLine());
						}
					} else {
						s = new Scanner(conn.getInputStream());

						// process the stream and store it in StringBuilder
						while (s.hasNextLine()) {
							response += (s.nextLine());
						}
					}

					return new HttpRS(response_code, response);
				} catch (MalformedURLException ex) {
					Log.i("MyLog", "(MalformedURLException) HttpCall.RQ_POST:" + ex.getMessage());
					return new HttpRS(-1, "(MalformedURLException):" + ex.toString());
				} catch (IOException ex) {
					Log.i("MyLog", "(IOException) HttpCall.RQ_POST:" + ex.getMessage());
					return new HttpRS(-1, "(getLocalizedMessage):" + ex.getLocalizedMessage() + "(getMessage):" + ex.getMessage() + "(toString):" + ex.toString());
				} catch (Exception ex) {
					Log.i("MyLog", "(Exception) HttpCall.RQ_POST:" + ex.getMessage());
					return new HttpRS(-1, "(Exception):" + ex.toString());
				}
			}

		}.start();
	}

	public static void RQ_DELETE(final String url, final ArrayList<InputItem> params, final RQListener listener, final Object state) {
		new Thread() {

			@Override
			public void run() {
				try {
					String url_encode = url;

					url_encode = url_encode.replace(" ", "%20");
					url_encode = url_encode.replace("\"", "%22");
					url_encode = url_encode.replace("#", "%23");
					url_encode = url_encode.replace("{", "%7B");
					url_encode = url_encode.replace("}", "%7D");
					url_encode = url_encode.replace("<", "%3C");
					url_encode = url_encode.replace(">", "%3E");

					Log.i("MyLog", "url_encode=" + url_encode);

					// HttpRS resp = this.requestPost(url_encode, params);
					// listener.onComplete(resp.response_code,
					// resp.response_text, state);
					HttpDelete method = new HttpDelete(url_encode);
					HttpClient client = new DefaultHttpClient();
					HttpResponse http_response = client.execute(method);
					Log.i("MyLog", "(http_response|getStatusCode)=" + http_response.getStatusLine().getStatusCode());
					listener.onComplete(http_response.getStatusLine().getStatusCode(), HttpUtil.getResponseBody(http_response), state);
				} catch (FileNotFoundException e) {
					listener.onFileNotFoundException(e, state);
				} catch (MalformedURLException e) {
					listener.onMalformedURLException(e, state);
				} catch (IOException e) {
					listener.onIOException(e, state);
				}
			}

		}.start();
	}

	public static void RQ_POST_APACHE(final String url, final ArrayList<InputItem> params, final RQListener listener, final Object state) {
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://www.yoursite.com/script.php");

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("id", "12345"));
			nameValuePairs.add(new BasicNameValuePair("stringdata", "AndDev is Cool!"));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}

}
