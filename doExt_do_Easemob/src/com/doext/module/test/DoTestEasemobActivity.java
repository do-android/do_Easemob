package com.doext.module.test;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;

import com.doext.module.frame.debug.DoService;

import core.DoServiceContainer;
import doext.implement.do_HuanXinIM_Model;

public class DoTestEasemobActivity extends DoTestActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void initModuleModel() throws Exception {
		this.model = new do_HuanXinIM_Model();
	}

	@Override
	protected void doTestSyncMethod() {
		/*Map<String, String>  _paras_chat = new HashMap<String, String>();
		_paras_chat.put("username", "heping");
		DoService.syncMethod(this.model, "enterChat", _paras_chat);*/
	}

	@Override
	protected void doTestAsyncMethod() {
		Map<String, String>  _paras_login = new HashMap<String, String>();
		_paras_login.put("username", "zzy");
		_paras_login.put("password", "123456");
		DoService.ansyncMethod(this.model, "login", _paras_login, new DoService.EventCallBack() {
			
			@Override
			public void eventCallBack(String _data) {
				DoServiceContainer.getLogEngine().writeDebug("IMLogin：" + _data);
				Map<String, String>  _paras_chat = new HashMap<String, String>();
				_paras_chat.put("username", "heping");
				_paras_chat.put("userNickname", "nick");
				//_paras_chat.put("userIcon", "http://downloads.easemob.com/downloads/57.png");
				//_paras_chat.put("myIcon", "http://zhouzhou.dcdmt.cn/files/UploadImage/20150508/5de7e1c345a44ebeb2c2e9e60de14a2a.jpg");
				DoService.syncMethod(model, "enterChat", _paras_chat);
				
			}
		});
	}
	
	
}
