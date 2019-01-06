package org.json;

public class Cookie {
    public static String escape(String str) {
        str = str.trim();
        int length = str.length();
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (!(charAt < ' ' || charAt == '+' || charAt == '%' || charAt == '=')) {
                if (charAt != ';') {
                    stringBuilder.append(charAt);
                }
            }
            stringBuilder.append('%');
            stringBuilder.append(Character.forDigit((char) ((charAt >>> 4) & 15), 16));
            stringBuilder.append(Character.forDigit((char) (charAt & 15), 16));
        }
        return stringBuilder.toString();
    }

    public static JSONObject toJSONObject(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONTokener jSONTokener = new JSONTokener(str);
        jSONObject.put("name", jSONTokener.nextTo('='));
        jSONTokener.next('=');
        jSONObject.put("value", jSONTokener.nextTo(';'));
        jSONTokener.next();
        while (jSONTokener.more() != null) {
            Object unescape;
            str = unescape(jSONTokener.nextTo("=;"));
            if (jSONTokener.next() == '=') {
                unescape = unescape(jSONTokener.nextTo(';'));
                jSONTokener.next();
            } else if (str.equals("secure")) {
                unescape = Boolean.TRUE;
            } else {
                throw jSONTokener.syntaxError("Missing '=' in cookie parameter.");
            }
            jSONObject.put(str, unescape);
        }
        return jSONObject;
    }

    public static String toString(JSONObject jSONObject) throws JSONException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(escape(jSONObject.getString("name")));
        stringBuilder.append("=");
        stringBuilder.append(escape(jSONObject.getString("value")));
        if (jSONObject.has("expires")) {
            stringBuilder.append(";expires=");
            stringBuilder.append(jSONObject.getString("expires"));
        }
        if (jSONObject.has("domain")) {
            stringBuilder.append(";domain=");
            stringBuilder.append(escape(jSONObject.getString("domain")));
        }
        if (jSONObject.has("path")) {
            stringBuilder.append(";path=");
            stringBuilder.append(escape(jSONObject.getString("path")));
        }
        if (jSONObject.optBoolean("secure") != null) {
            stringBuilder.append(";secure");
        }
        return stringBuilder.toString();
    }

    public static String unescape(String str) {
        int length = str.length();
        StringBuilder stringBuilder = new StringBuilder(length);
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt == '+') {
                charAt = ' ';
            } else if (charAt == '%') {
                int i2 = i + 2;
                if (i2 < length) {
                    int dehexchar = JSONTokener.dehexchar(str.charAt(i + 1));
                    int dehexchar2 = JSONTokener.dehexchar(str.charAt(i2));
                    if (dehexchar >= 0 && dehexchar2 >= 0) {
                        charAt = (char) ((dehexchar * 16) + dehexchar2);
                        i = i2;
                    }
                }
            }
            stringBuilder.append(charAt);
            i++;
        }
        return stringBuilder.toString();
    }
}
