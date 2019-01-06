package org.json;

import java.util.Iterator;

public class CookieList {
    public static JSONObject toJSONObject(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONTokener jSONTokener = new JSONTokener(str);
        while (jSONTokener.more() != null) {
            String unescape = Cookie.unescape(jSONTokener.nextTo('='));
            jSONTokener.next('=');
            jSONObject.put(unescape, Cookie.unescape(jSONTokener.nextTo(';')));
            jSONTokener.next();
        }
        return jSONObject;
    }

    public static String toString(JSONObject jSONObject) throws JSONException {
        Iterator keys = jSONObject.keys();
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = null;
        while (keys.hasNext()) {
            String str = (String) keys.next();
            if (!jSONObject.isNull(str)) {
                if (obj != null) {
                    stringBuilder.append(';');
                }
                stringBuilder.append(Cookie.escape(str));
                stringBuilder.append("=");
                stringBuilder.append(Cookie.escape(jSONObject.getString(str)));
                obj = 1;
            }
        }
        return stringBuilder.toString();
    }
}
