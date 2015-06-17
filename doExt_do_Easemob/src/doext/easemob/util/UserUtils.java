package doext.easemob.util;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import core.DoServiceContainer;
import core.helper.DoResourcesHelper;
import doext.app.do_HuanXinIM_App;
import doext.easemob.domain.User;

public class UserUtils {
    /**
     * 根据username获取相应user，由于demo没有真实的用户数据，这里给的模拟的数据；
     * @param username
     * @return
     */
    public static User getUserInfo(String username){
        //User user = DemoApplication.getInstance().getContactList().get(username);
    	
        User user = do_HuanXinIM_App.getInstance().getUserInfo(username);
    	if(user == null){
            user = new User(username);
        }
            
        if(user != null){
            //demo没有这些数据，临时填充
            user.setNick(username);
            //user.setAvatar("http://downloads.easemob.com/downloads/57.png");
        }
        return user;
    }
    
    /**
     * 设置用户头像
     * @param username
     */
    public static void setUserAvatar(Context context, String username, ImageView imageView){
        User user = getUserInfo(username);
        if(user != null){
        	DoServiceContainer.getLogEngine().writeInfo("setUserAvatar", "icon:" + user.getAvatar());
            Picasso.with(context).load(user.getAvatar()).placeholder(DoResourcesHelper.getRIdByDrawable("default_avatar", do_HuanXinIM_App.getInstance().getModuleTypeID())).into(imageView);
        }else{
            Picasso.with(context).load(DoResourcesHelper.getRIdByDrawable("default_avatar", do_HuanXinIM_App.getInstance().getModuleTypeID())).into(imageView);
        }
    }
    
}
