package com.tow.sso.codegears_sso_android.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.tow.sso.codegears_sso_android.AppSetting;
import com.tow.sso.codegears_sso_android.R;
import com.tow.sso.codegears_sso_android.http.HttpCall;
import com.tow.sso.codegears_sso_android.http.HttpRQListener;
import com.tow.sso.codegears_sso_android.logger.Logger;
import com.tow.sso.codegears_sso_android.model.InputItem;
import com.tow.sso.codegears_sso_android.ui.Const;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class SplashScreenActivity extends Activity {

	private Handler mHandler;
	protected int _splashTime = 2000;
	private Thread splashTread;
	private GoogleCloudMessaging gcm;
	private SharedPreferences prefs;
	private Context context;
	private String regid;
	private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	private  final String EXTRA_MESSAGE = "message";
	private  final String PROPERTY_REG_ID = "registration_id";
	private  final String PROPERTY_APP_VERSION = "appVersion";
	private  final String SENDER_ID = "623418801133";
	AtomicInteger msgId = new AtomicInteger();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mHandler = new Handler();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		context = getApplicationContext();
		Logger.Log("SplashScreenActivity", "SplashScreenActivity");
		if (checkPlayServices()) {
			gcm = GoogleCloudMessaging.getInstance(this);
			regid = getRegistrationId(context);

			if (regid == null || regid.equals("")) {
				registerInBackground();
				Logger.Log("call register ",regid.toString());

			}
			Logger.Log("checkPlayServices", "checkPlayServices");
		}

		goNext();

//		ArrayList<InputItem> params = new ArrayList<InputItem>();
//		params.add(new InputItem("product_key", Const.PRODUCT_KEY)); //send product_key
//		HttpCall.RQ_POST(Const.API_DEFAULT_PROTECTION, params, new DefaultProtectionListener(), null); //dp_check_api
	}
	@Override
	protected void onResume() {
		super.onResume();
		checkPlayServices();
	}
	public class PushNotificationRegisterListener extends HttpRQListener {
		@Override
		public void onComplete(int response_code, String response_text, Object state) {
			// TODO Auto-generated method stub
			//Notification ???
		}
	}
	public class DefaultProtectionListener extends HttpRQListener {
		public RelativeLayout layoutAuth;
		public LinearLayout layoutMenu;
		public void onComplete(final int response_code, final String response_text, Object state) {
			mHandler.post(new Runnable() {
				public void run() {
					if (response_code == 200) {
						try {
							JSONObject auth = new JSONObject(response_text);
							if (auth.has("result")) {   // has
								AppSetting.auth_result = auth.getString("result");
								if (AppSetting.auth_result.equals("fail")) {
									AppSetting.lockScreen = true;
								} else {
									AppSetting.lockScreen = false;
								}
							}
							if (auth.has("message")) {
								AppSetting.auth_message = auth.getString("message");
							}

//							goNext();  //call next
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						AppSetting.lockScreen = true;
						AppSetting.auth_result = "Authentication Fail";
						AppSetting.auth_message = "Please check your internet connection";
						Thread thread = new Thread() {
							@Override
							public void run() {
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
								}
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										AlertDialog completeDialog = new AlertDialog.Builder(SplashScreenActivity.this).create();
										completeDialog.setTitle("Authentication Fail");
										completeDialog.setMessage("Please check your internet connection");
										completeDialog.setButton("OK", new DialogInterface.OnClickListener() {
											public void onClick(DialogInterface dialog, int which) {
												finish();
											}
										});
										completeDialog.show();
									}
								});
							}
						};
						thread.start();
					}
				}
			});
		}
	}

	public void goNext() {
		final SplashScreenActivity sPlashScreen = this;
		splashTread = new Thread() {
			@Override
			public void run() {
				try {
					synchronized (this) {
						wait(_splashTime);
					}

				} catch (InterruptedException e) {
				} finally {
					finish();
					Logger.Log("goNext","goNext");
					Intent i = new Intent();
					i.setClass(sPlashScreen, MainActivity.class);
					startActivity(i);
				}
			}
		};
		splashTread.start();
	}

	private boolean checkPlayServices() {
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if (resultCode != ConnectionResult.SUCCESS) {
			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
				GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST).show();
			} else {
				finish();
			}
			return false;
		}
		return true;
	}

	public void showToast(final String msg) {
		mHandler.post(new Runnable() {

			@Override
			public void run() {
				Toast toast = Toast.makeText(SplashScreenActivity.this, msg, Toast.LENGTH_LONG);
				toast.show();
			}
		});
	}
	private String getRegistrationId(Context context) {
		final SharedPreferences prefs = getGCMPreferences(context);
		String registrationId = prefs.getString(PROPERTY_REG_ID, "");
		if (registrationId == null || registrationId.equals("")) {
			return "";
		}
		// Check if app was updated; if so, it must clear the registration ID
		// since the existing regID is not guaranteed to work with the new
		// app version.
		int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
		int currentVersion = getAppVersion(context);
		if (registeredVersion != currentVersion) {
			return "";
		}
		return registrationId;
	}
	private SharedPreferences getGCMPreferences(Context context) {
		// This sample app persists the registration ID in shared preferences, but
		// how you store the regID in your app is up to you.
		return getSharedPreferences(SplashScreenActivity.class.getSimpleName(), Context.MODE_PRIVATE);
	}

	private static int getAppVersion(Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			// should never happen
			throw new RuntimeException("Could not get package name: " + e);
		}
	}

	/**
	 * Registers the application with GCM servers asynchronously.
	 * <p>
	 * Stores the registration ID and app versionCode in the application's shared preferences.
	 */
	private void registerInBackground() {
		new AsyncTask() {
			@Override
			protected Object doInBackground(Object... params) {
				String msg = "";
				try {
					if (gcm == null) {
						gcm = GoogleCloudMessaging.getInstance(context);
					}
					regid = gcm.register(SENDER_ID);
					msg = "Device registered, registration ID=" + regid;
				//	sendRegistrationIdToBackend();
					storeRegistrationId(context, regid);
				} catch (IOException ex) {
					msg = "Error :" + ex.getMessage();
				}
				return msg;
			}

		}.execute(null, null, null);

		/**
		 * Sends the registration ID to your server over HTTP, so it can use GCM/HTTP or CCS to send messages to your app. Not needed for this demo since the device sends upstream messages to a server that echoes back the message using the 'from' address in the message.
		 */
	}

	private static final String serviceToken = "a2452089a4b5f8b6f9ed8ceee706b5d0";

	private void sendRegistrationIdToBackend() {
		// Your implementation here.
		String android_id = Secure.getString(getBaseContext().getContentResolver(), Secure.ANDROID_ID);
		ArrayList<InputItem> params = new ArrayList<InputItem>();
		params.add(new InputItem("serviceToken", serviceToken));
		params.add(new InputItem("uUid", android_id));
		params.add(new InputItem("deviceToken", regid));
		HttpCall.RQ_POST(Const.API_REGISTER, params, new PushNotificationRegisterListener(), null);
	}
	/**
	 * Stores the registration ID and app versionCode in the application's {@code SharedPreferences}.
	 *
	 * @param context
	 *            application's context.
	 * @param regId
	 *            registration ID
	 */
	private void storeRegistrationId(Context context, String regId) {
		final SharedPreferences prefs = getGCMPreferences(context);
		int appVersion = getAppVersion(context);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(PROPERTY_REG_ID, regId);
		editor.putInt(PROPERTY_APP_VERSION, appVersion);
		editor.commit();
	}
}