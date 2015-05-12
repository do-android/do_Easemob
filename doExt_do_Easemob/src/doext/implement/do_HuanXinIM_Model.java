package doext.implement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.easemob.EMCallBack;
import com.easemob.EMConnectionListener;
import com.easemob.EMError;
import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.easemob.util.NetUtils;

import core.DoServiceContainer;
import core.helper.jsonparse.DoJsonNode;
import core.interfaces.DoIScriptEngine;
import core.object.DoInvokeResult;
import core.object.DoSingletonModule;
import doext.app.do_HuanXinIM_App;
import doext.define.do_HuanXinIM_IMethod;
import doext.easemob.activity.ChatActivity;
import doext.easemob.domain.User;

/**
 * 自定义扩展SM组件Model实现，继承DoSingletonModule抽象类，并实现M0005_HuanXinIM_IMethod接口方法；
 * #如何调用组件自定义事件？可以通过如下方法触发事件：
 * this.model.getEventCenter().fireEvent(_messageName, jsonResult);
 * 参数解释：@_messageName字符串事件名称，@jsonResult传递事件参数对象；
 * 获取DoInvokeResult对象方式new DoInvokeResult(this.getUniqueKey());
 */
public class do_HuanXinIM_Model extends DoSingletonModule implements do_HuanXinIM_IMethod{
	
	private Context mContext;
	
	public do_HuanXinIM_Model() throws Exception {
		super();
		this.mContext = DoServiceContainer.getPageViewFactory().getAppContext();
		registerNewMessageReceiver();
		// 注册一个监听连接状态的listener
		EMChatManager.getInstance().addConnectionListener(new MyConnectionListener());
	}
	
	/**
	 * 同步方法，JS脚本调用该组件对象方法时会被调用，可以根据_methodName调用相应的接口实现方法；
	 * @_methodName 方法名称
	 * @_dictParas 参数（K,V）
	 * @_scriptEngine 当前Page JS上下文环境对象
	 * @_invokeResult 用于返回方法结果对象
	 */
	@Override
	public boolean invokeSyncMethod(String _methodName, DoJsonNode _dictParas,
			DoIScriptEngine _scriptEngine, DoInvokeResult _invokeResult)
			throws Exception {
		if("enterChat".equals(_methodName)){
			enterChat(_dictParas, _scriptEngine, _invokeResult);
			return true;
		}
		if("logout".equals(_methodName)){
			logout(_dictParas, _scriptEngine, _invokeResult);
			return true;
		}
		return super.invokeSyncMethod(_methodName, _dictParas, _scriptEngine, _invokeResult);
	}
	
	/**
	 * 异步方法（通常都处理些耗时操作，避免UI线程阻塞），JS脚本调用该组件对象方法时会被调用，
	 * 可以根据_methodName调用相应的接口实现方法；
	 * @_methodName 方法名称
	 * @_dictParas 参数（K,V）
	 * @_scriptEngine 当前page JS上下文环境
	 * @_callbackFuncName 回调函数名
	 * #如何执行异步方法回调？可以通过如下方法：
	 * _scriptEngine.callback(_callbackFuncName, _invokeResult);
	 * 参数解释：@_callbackFuncName回调函数名，@_invokeResult传递回调函数参数对象；
	 * 获取DoInvokeResult对象方式new DoInvokeResult(this.getUniqueKey());
	 */
	@Override
	public boolean invokeAsyncMethod(String _methodName, DoJsonNode _dictParas,
			DoIScriptEngine _scriptEngine, String _callbackFuncName)throws Exception {
		if("login".equals(_methodName)){
			login(_dictParas, _scriptEngine, _callbackFuncName);
			return true;
		}
		return super.invokeAsyncMethod(_methodName, _dictParas, _scriptEngine, _callbackFuncName);
	}

	/**
	 * 进入聊天；
	 * @_dictParas 参数（K,V），可以通过此对象提供相关方法来获取参数值（Key：为参数名称）；
	 * @_scriptEngine 当前Page JS上下文环境对象
	 * @_invokeResult 用于返回方法结果对象
	 */
	@Override
	public void enterChat(DoJsonNode _dictParas, DoIScriptEngine _scriptEngine,
			DoInvokeResult _invokeResult) throws Exception {
		String userId = _dictParas.getOneText("username", "");
		String userNickname = _dictParas.getOneText("userNickname", "");
		String userIcon = _dictParas.getOneText("userIcon", "");
		String myIcon = _dictParas.getOneText("myIcon", "");
		String loginUserId = do_HuanXinIM_App.getInstance().getLoginUserId();
		do_HuanXinIM_App.getInstance().putUserInfo(loginUserId, new User(loginUserId,myIcon));
		do_HuanXinIM_App.getInstance().putUserInfo(userId, new User(userId,userIcon));
		Intent chat = new Intent();
		chat.putExtra("userId", userId);
		chat.putExtra("userNickname", userNickname);
		chat.setClass(mContext, ChatActivity.class);
		mContext.startActivity(chat);
	}

	/**
	 * IM用户登录；
	 * @throws Exception 
	 * @_dictParas 参数（K,V），可以通过此对象提供相关方法来获取参数值（Key：为参数名称）；
	 * @_scriptEngine 当前Page JS上下文环境对象
	 * @_callbackFuncName 回调函数名
	 */
	@Override
	public void login(DoJsonNode _dictParas, final DoIScriptEngine _scriptEngine,final String _callbackFuncName) throws Exception {
		final String username = _dictParas.getOneText("username", "");
		String password = _dictParas.getOneText("password", "");
		final DoInvokeResult invokeResult = new DoInvokeResult(getUniqueKey());
    	final DoJsonNode jsonNode = new DoJsonNode();
		EMChatManager.getInstance().login(username, password, new EMCallBack() {
		    @Override
		    public void onSuccess() {
		    	try {
		    		do_HuanXinIM_App.getInstance().setLoginUserId(username);
		    		jsonNode.setOneInteger("state", 0);
			    	jsonNode.setOneText("message", "success");
					invokeResult.setResultNode(jsonNode);
					_scriptEngine.callback(_callbackFuncName, invokeResult);
				} catch (Exception e) {
					DoServiceContainer.getLogEngine().writeError("IM用户登录发生错误", e);
				}
		    }
			
		    @Override
		    public void onProgress(int progress, String status) {
		    	
		    }
				
		    @Override
		    public void onError(int code, String message) {
		    	try {
		    		jsonNode.setOneInteger("state", 1);
			    	jsonNode.setOneText("message", "onError#code:" + code + "message:" + message);
					invokeResult.setResultNode(jsonNode);
					_scriptEngine.callback(_callbackFuncName, invokeResult);
				} catch (Exception e) {
					DoServiceContainer.getLogEngine().writeError("IM用户登录发生错误", e);
				}
		    }
		});
	}


	/**
	 * 注销用户；
	 * @_dictParas 参数（K,V），可以通过此对象提供相关方法来获取参数值（Key：为参数名称）；
	 * @_scriptEngine 当前Page JS上下文环境对象
	 * @_invokeResult 用于返回方法结果对象
	 */
	@Override
	public void logout(DoJsonNode _dictParas, DoIScriptEngine _scriptEngine,
			DoInvokeResult _invokeResult) throws Exception {
		EMChatManager.getInstance().logout();
	}
	
	private void registerNewMessageReceiver(){
		NewMessageBroadcastReceiver msgReceiver = new NewMessageBroadcastReceiver();
		IntentFilter intentFilter = new IntentFilter(EMChatManager.getInstance().getNewMessageBroadcastAction());
		intentFilter.setPriority(3);
		mContext.registerReceiver(msgReceiver, intentFilter);
		EMChat.getInstance().setAppInited();
	}
	
	/**
	 * 消息广播接收者
	 * 
	 */
	private class NewMessageBroadcastReceiver extends BroadcastReceiver {
	    @Override
	    public void onReceive(Context context, Intent intent) {
	        //消息id
	        String msgId = intent.getStringExtra("msgid");
	        //发消息的人的username(userid)
	        String msgFrom = intent.getStringExtra("from");
	        //消息类型，文本，图片，语音消息等,这里返回的值为msg.type.ordinal()。
	        //所以消息type实际为是enum类型
	        int msgType = intent.getIntExtra("type", 0);
	        Log.d("main", "new message id:" + msgId + " from:" + msgFrom + " type:" + msgType);
	        //更方便的方法是通过msgId直接获取整个message
	        //EMMessage message = EMChatManager.getInstance().getMessage(msgId);
	        getEventCenter().fireEvent("receive", new DoInvokeResult(getUniqueKey()));
	    }
	}
	
	/**
	 * 连接监听listener
	 * 
	 */
	private class MyConnectionListener implements EMConnectionListener {

		@Override
		public void onConnected() {
	    	try {
	    		DoInvokeResult jsonResult = new DoInvokeResult(getUniqueKey());
				DoJsonNode jsonNode = new DoJsonNode();
				jsonNode.setOneInteger("state", 0);
				getEventCenter().fireEvent("connection", jsonResult);
			} catch (Exception e) {
				DoServiceContainer.getLogEngine().writeError("IM连接监听发生错误", e);
			}
		}

		@Override
		public void onDisconnected(final int error) {
			try {
	    		DoInvokeResult jsonResult = new DoInvokeResult(getUniqueKey());
				DoJsonNode jsonNode = new DoJsonNode();
				if(error == EMError.USER_REMOVED){
					jsonNode.setOneInteger("state", 1);//显示帐号已经被移除
				} else if (error == EMError.CONNECTION_CONFLICT) {
					jsonNode.setOneInteger("state", 2);//显示帐号在其他设备登陆
				} else{
					if (NetUtils.hasNetwork(mContext)){
						jsonNode.setOneInteger("state", 3);//连接不到聊天服务器
					}else{
						jsonNode.setOneInteger("state", 4);//当前网络不可用 请检查网络设置
					}
				}
				jsonResult.setResultNode(jsonNode);
				getEventCenter().fireEvent("connection", jsonResult);
			} catch (Exception e) {
				DoServiceContainer.getLogEngine().writeError("IM连接监听发生错误", e);
			}
		}
	}
}