package doext.define;

import org.json.JSONObject;

import core.interfaces.DoIScriptEngine;
import core.object.DoInvokeResult;

/**
 * 声明自定义扩展组件方法
 */
public interface do_HuanXinIM_IMethod {
	void enterChat(JSONObject _dictParas,DoIScriptEngine _scriptEngine, DoInvokeResult _invokeResult) throws Exception ;
	void login(JSONObject _dictParas,DoIScriptEngine _scriptEngine, String _callbackFuncName) throws Exception ;
	void logout(JSONObject _dictParas,DoIScriptEngine _scriptEngine, DoInvokeResult _invokeResult) throws Exception ;
}