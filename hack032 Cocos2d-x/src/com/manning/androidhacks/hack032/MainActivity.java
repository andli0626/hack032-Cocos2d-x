package com.manning.androidhacks.hack032;

import org.cocos2dx.lib.Cocos2dxActivity;
import org.cocos2dx.lib.Cocos2dxEditText;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;
import org.cocos2dx.lib.Cocos2dxRenderer;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Cocos2dxActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (detectOpenGLES20()) {
			// get the packageName,it's used to set the resource path
			String packageName = getApplication().getPackageName();
			super.setPackageName(packageName);

			setContentView(R.layout.game_demo);
			mGLView = (Cocos2dxGLSurfaceView) findViewById(R.id.game_gl_surfaceview);
			Cocos2dxEditText edittext = (Cocos2dxEditText) findViewById(R.id.game_edittext);

			mGLView.setEGLContextClientVersion(2);
			mGLView.setCocos2dxRenderer(new Cocos2dxRenderer());
			mGLView.setTextField(edittext);

		} else {
			Log.d("activity", "don't support gles2.0");
			finish();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		mGLView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mGLView.onResume();
	}

	private boolean detectOpenGLES20() {
		ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		ConfigurationInfo info = am.getDeviceConfigurationInfo();
		return (info.reqGlEsVersion >= 0x20000);
	}

	static {
		System.loadLibrary("game");
	}
}
