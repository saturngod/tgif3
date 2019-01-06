package org.json;

import kotlin.text.Typography;

public class CDL {
    private static String getValue(JSONTokener jSONTokener) throws JSONException {
        while (true) {
            char next = jSONTokener.next();
            if (next != ' ' && next != '\t') {
                break;
            }
        }
        if (next == '\u0000') {
            return null;
        }
        if (next == Typography.quote || next == '\'') {
            StringBuilder stringBuilder;
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                char next2 = jSONTokener.next();
                if (next2 == next) {
                    return stringBuffer.toString();
                }
                if (next2 == '\u0000' || next2 == '\n' || next2 == '\r') {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Missing close quote '");
                    stringBuilder.append(next);
                    stringBuilder.append("'.");
                } else {
                    stringBuffer.append(next2);
                }
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append("Missing close quote '");
            stringBuilder.append(next);
            stringBuilder.append("'.");
            throw jSONTokener.syntaxError(stringBuilder.toString());
        } else if (next != ',') {
            jSONTokener.back();
            return jSONTokener.nextTo(',');
        } else {
            jSONTokener.back();
            return "";
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONArray rowToJSONArray(org.json.JSONTokener r5) throws org.json.JSONException {
        /*
        r0 = new org.json.JSONArray;
        r0.<init>();
    L_0x0005:
        r1 = getValue(r5);
        r2 = r5.next();
        if (r1 == 0) goto L_0x005e;
    L_0x000f:
        r3 = r0.length();
        r4 = 44;
        if (r3 != 0) goto L_0x0020;
    L_0x0017:
        r3 = r1.length();
        if (r3 != 0) goto L_0x0020;
    L_0x001d:
        if (r2 == r4) goto L_0x0020;
    L_0x001f:
        goto L_0x005e;
    L_0x0020:
        r0.put(r1);
    L_0x0023:
        if (r2 != r4) goto L_0x0026;
    L_0x0025:
        goto L_0x0005;
    L_0x0026:
        r1 = 32;
        if (r2 == r1) goto L_0x0059;
    L_0x002a:
        r1 = 10;
        if (r2 == r1) goto L_0x0058;
    L_0x002e:
        r1 = 13;
        if (r2 == r1) goto L_0x0058;
    L_0x0032:
        if (r2 != 0) goto L_0x0035;
    L_0x0034:
        goto L_0x0058;
    L_0x0035:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Bad character '";
        r0.append(r1);
        r0.append(r2);
        r1 = "' (";
        r0.append(r1);
        r0.append(r2);
        r1 = ").";
        r0.append(r1);
        r0 = r0.toString();
        r5 = r5.syntaxError(r0);
        throw r5;
    L_0x0058:
        return r0;
    L_0x0059:
        r2 = r5.next();
        goto L_0x0023;
    L_0x005e:
        r5 = 0;
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.CDL.rowToJSONArray(org.json.JSONTokener):org.json.JSONArray");
    }

    public static JSONObject rowToJSONObject(JSONArray jSONArray, JSONTokener jSONTokener) throws JSONException {
        jSONTokener = rowToJSONArray(jSONTokener);
        return jSONTokener != null ? jSONTokener.toJSONObject(jSONArray) : null;
    }

    public static String rowToString(JSONArray jSONArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < jSONArray.length(); i++) {
            if (i > 0) {
                stringBuilder.append(',');
            }
            Object opt = jSONArray.opt(i);
            if (opt != null) {
                String obj = opt.toString();
                if (obj.length() <= 0 || (obj.indexOf(44) < 0 && obj.indexOf(10) < 0 && obj.indexOf(13) < 0 && obj.indexOf(0) < 0 && obj.charAt(0) != Typography.quote)) {
                    stringBuilder.append(obj);
                } else {
                    stringBuilder.append(Typography.quote);
                    int length = obj.length();
                    for (int i2 = 0; i2 < length; i2++) {
                        char charAt = obj.charAt(i2);
                        if (charAt >= ' ' && charAt != Typography.quote) {
                            stringBuilder.append(charAt);
                        }
                    }
                    stringBuilder.append(Typography.quote);
                }
            }
        }
        stringBuilder.append('\n');
        return stringBuilder.toString();
    }

    public static JSONArray toJSONArray(String str) throws JSONException {
        return toJSONArray(new JSONTokener(str));
    }

    public static JSONArray toJSONArray(JSONTokener jSONTokener) throws JSONException {
        return toJSONArray(rowToJSONArray(jSONTokener), jSONTokener);
    }

    public static JSONArray toJSONArray(JSONArray jSONArray, String str) throws JSONException {
        return toJSONArray(jSONArray, new JSONTokener(str));
    }

    public static JSONArray toJSONArray(JSONArray jSONArray, JSONTokener jSONTokener) throws JSONException {
        if (jSONArray != null) {
            if (jSONArray.length() != 0) {
                JSONArray jSONArray2 = new JSONArray();
                while (true) {
                    Object rowToJSONObject = rowToJSONObject(jSONArray, jSONTokener);
                    if (rowToJSONObject == null) {
                        break;
                    }
                    jSONArray2.put(rowToJSONObject);
                }
                if (jSONArray2.length() == null) {
                    return null;
                }
                return jSONArray2;
            }
        }
        return null;
    }

    public static String toString(JSONArray jSONArray) throws JSONException {
        JSONObject optJSONObject = jSONArray.optJSONObject(0);
        if (optJSONObject != null) {
            JSONArray names = optJSONObject.names();
            if (names != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(rowToString(names));
                stringBuilder.append(toString(names, jSONArray));
                return stringBuilder.toString();
            }
        }
        return null;
    }

    public static String toString(JSONArray jSONArray, JSONArray jSONArray2) throws JSONException {
        if (jSONArray != null) {
            if (jSONArray.length() != 0) {
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < jSONArray2.length(); i++) {
                    JSONObject optJSONObject = jSONArray2.optJSONObject(i);
                    if (optJSONObject != null) {
                        stringBuffer.append(rowToString(optJSONObject.toJSONArray(jSONArray)));
                    }
                }
                return stringBuffer.toString();
            }
        }
        return null;
    }
}
