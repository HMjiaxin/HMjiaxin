package cn.hmjiaxin.util;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

public class StringUtil {
	public static String JSONCallBackForDataTables(HttpServletRequest request, Object obj) {
		String jsonCallback = request.getParameter("callback");
		String JSONObject = "";
		if (!obj.getClass().equals(String.class)) {

			JSONObject = JSONArray.fromObject(obj).toString();
			JSONObject = JSONObject.substring(1, JSONObject.length() - 1);
		} else {
			JSONObject = obj.toString();
		}
		return jsonCallback + "(" + JSONObject + ")";
	}
	public static String JSONCallBack(HttpServletRequest request, Object obj) {
		String jsonCallback = request.getParameter("callback");
		String JSONObject = "";
		if (!obj.getClass().equals(String.class)) {

			JSONObject = JSONArray.fromObject(obj).toString();
			
		} else {
			JSONObject = obj.toString();
		}
		return jsonCallback + "(" + JSONObject + ")";
	}
}
