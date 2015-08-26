package doext.app;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.util.Log;

import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMChatOptions;
import com.easemob.chat.EMMessage;
import com.easemob.chat.OnNotificationClickListener;

import core.interfaces.DoIAppDelegate;
import doext.easemob.domain.User;

/**
 * APP启动的时候会执行onCreate方法；
 * 
 */
public class do_HuanXinIM_App implements DoIAppDelegate {

	private static final String TAG = "do_Easemob_App";
	private static do_HuanXinIM_App instance;
	private Context currentContext;
	private String loginUserId;
	private Map<String,User> chatUserInfos = new HashMap<String, User>();

	private do_HuanXinIM_App() {

	}

	@Override
	public void onCreate(Context context) {
		this.currentContext = context;
		onInit(context);
	}

	public static do_HuanXinIM_App getInstance() {
		if (instance == null) {
			instance = new do_HuanXinIM_App();
		}
		return instance;
	}

	public synchronized boolean onInit(Context context) {

		int pid = android.os.Process.myPid();
		String processAppName = getAppName(pid);
		// 如果app启用了远程的service，此application:onCreate会被调用2次
		// 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
		// 默认的app会在以包名为默认的process name下运行，如果查到的process name不是app的process
		// name就立即返回
		if (processAppName == null
				|| !processAppName.equalsIgnoreCase(context.getPackageName())) {
			Log.e(TAG, "enter the service process!");
			// "com.easemob.chatuidemo"为demo的包名，换到自己项目中要改成自己包名
			// 则此application::onCreate 是被service 调用的，直接返回
			return false;
		}
		// 初始化环信SDK
		Log.d("DemoApplication", "Initialize EMChat SDK");
		EMChat.getInstance().init(currentContext);
		// 获取到EMChatOptions对象
		EMChatOptions options = EMChatManager.getInstance().getChatOptions();
		//设置notification点击listener
		options.setOnNotificationClickListener(new OnNotificationClickListener() {

			@Override
			public Intent onNotificationClick(EMMessage message) {
				Intent intent = new Intent(Intent.ACTION_MAIN);
				//Intent intent = new Intent();
				//Intent intent = new Intent(currentContext, ChatActivity.class);
				//ChatType chatType = message.getChatType();
				/*if(chatType == ChatType.Chat){ //单聊信息
					intent.putExtra("chatType", ChatActivity.CHATTYPE_SINGLE);
					intent.putExtra("userId", message.getFrom());
					intent.putExtra("userNick", message.getStringAttribute("userNick", ""));
					intent.putExtra("selfNick", message.getStringAttribute("nick",""));
					intent.putExtra("selfIcon", message.getStringAttribute("icon", ""));
					intent.putExtra("uniqueKey", message.getStringAttribute("uniqueKey", ""));
					intent.putExtra("tag", message.getStringAttribute("tag", ""));
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
				}else{ //群聊信息
					//message.getTo()为群聊id
					intent.putExtra("groupId", message.getTo());
					intent.putExtra("chatType", ChatActivity.CHATTYPE_GROUP);
				}*/
				try {
					Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
			    	resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
			    	resolveIntent.setPackage(currentContext.getPackageName());
					List<ResolveInfo> apps = currentContext.getPackageManager().queryIntentActivities(resolveIntent, 0);
					if(apps.size() == 0){
						return intent;
					}
			    	ResolveInfo ri = apps.iterator().next();
			    	String packageName = ri.activityInfo.packageName;
			    	String className = ri.activityInfo.name;
			    	ComponentName cn = new ComponentName(packageName, className);
			    	intent.addCategory(Intent.CATEGORY_LAUNCHER);
			    	intent.setComponent(cn);
			    	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			    	intent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return intent;
			}
		});
		// 默认添加好友时，是不需要验证的，改成需要验证
		options.setAcceptInvitationAlways(false);
		// 设置收到消息是否有新消息通知，默认为true
		//options.setNotificationEnable(false);
		// 设置收到消息是否有声音提示，默认为true
		//options.setNoticeBySound(true);
		// 设置收到消息是否震动 默认为true
		//options.setNoticedByVibrate(true);
		// 设置语音消息播放是否设置为扬声器播放 默认为true
		options.setUseSpeaker(true);
		
		return true;
	}

	@SuppressWarnings("rawtypes")
	private String getAppName(int pID) {
		String processName = null;
		ActivityManager am = (ActivityManager) currentContext
				.getSystemService(Context.ACTIVITY_SERVICE);
		List l = am.getRunningAppProcesses();
		Iterator i = l.iterator();
		// PackageManager pm = appContext.getPackageManager();
		while (i.hasNext()) {
			ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
			try {
				if (info.pid == pID) {
					// CharSequence c =
					// pm.getApplicationLabel(pm.getApplicationInfo(info.processName,PackageManager.GET_META_DATA));
					processName = info.processName;
					return processName;
				}
			} catch (Exception e) {
			}
		}
		return processName;
	}

	public User getUserInfo(String userId) {
		return chatUserInfos.get(userId);
	}

	public void putUserInfo(String userId,User user) {
		this.chatUserInfos.put(userId, user);
	}

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getModuleTypeID() {
		return "do_HuanXinIM";
	}

	@Override
	public String getTypeID() {
		// TODO Auto-generated method stub
		return getModuleTypeID();
	}

}
