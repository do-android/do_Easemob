package com.doext.module.frame.debug;

import java.util.ArrayList;
import java.util.List;

import core.interfaces.DoIAppDelegate;
import core.object.DoResources;
import android.app.Application;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class DoApplication extends Application {
	
	private static List<DoIAppDelegate> appDelegateArrays = new ArrayList<DoIAppDelegate>();
	
	@Override
	public void onCreate() {
		super.onCreate();
		DoIAppDelegate app = doext.app.do_HuanXinIM_App.getInstance();
		app.onCreate(this);
		appDelegateArrays.add(app);
	}
	
	@Override
	public Resources getResources() {
		AssetManager assets = getBaseContext().getResources().getAssets();
		DisplayMetrics metrics = getBaseContext().getResources().getDisplayMetrics();
		Configuration config = getBaseContext().getResources().getConfiguration();
		return new DoResources(assets, metrics, config, appDelegateArrays);
	}
	
}
