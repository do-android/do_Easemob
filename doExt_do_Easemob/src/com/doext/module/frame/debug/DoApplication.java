package com.doext.module.frame.debug;

import android.app.Application;

public class DoApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		doext.app.do_HuanXinIM_App.getInstance().onCreate(this);
	}
	
}
