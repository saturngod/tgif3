package org.json;

import java.util.Iterator;
import kotlin.text.Typography;

public class JSONML {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object parse(org.json.XMLTokener r7, boolean r8, org.json.JSONArray r9) throws org.json.JSONException {
        /*
    L_0x0000:
        r0 = r7.more();
        if (r0 == 0) goto L_0x01e2;
    L_0x0006:
        r0 = r7.nextContent();
        r1 = org.json.XML.LT;
        if (r0 != r1) goto L_0x01d1;
    L_0x000e:
        r0 = r7.nextToken();
        r1 = r0 instanceof java.lang.Character;
        if (r1 == 0) goto L_0x00c3;
    L_0x0016:
        r1 = org.json.XML.SLASH;
        if (r0 != r1) goto L_0x004e;
    L_0x001a:
        r8 = r7.nextToken();
        r9 = r8 instanceof java.lang.String;
        if (r9 == 0) goto L_0x0032;
    L_0x0022:
        r9 = r7.nextToken();
        r0 = org.json.XML.GT;
        if (r9 != r0) goto L_0x002b;
    L_0x002a:
        return r8;
    L_0x002b:
        r8 = "Misshaped close tag";
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x0032:
        r7 = new org.json.JSONException;
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r0 = "Expected a closing name instead of '";
        r9.append(r0);
        r9.append(r8);
        r8 = "'.";
        r9.append(r8);
        r8 = r9.toString();
        r7.<init>(r8);
        throw r7;
    L_0x004e:
        r1 = org.json.XML.BANG;
        if (r0 != r1) goto L_0x00b1;
    L_0x0052:
        r0 = r7.next();
        r1 = 45;
        if (r0 != r1) goto L_0x006a;
    L_0x005a:
        r0 = r7.next();
        if (r0 != r1) goto L_0x0066;
    L_0x0060:
        r0 = "-->";
        r7.skipPast(r0);
        goto L_0x0000;
    L_0x0066:
        r7.back();
        goto L_0x0000;
    L_0x006a:
        r1 = 91;
        if (r0 != r1) goto L_0x0092;
    L_0x006e:
        r0 = r7.nextToken();
        r2 = "CDATA";
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x008b;
    L_0x007a:
        r0 = r7.next();
        if (r0 != r1) goto L_0x008b;
    L_0x0080:
        if (r9 == 0) goto L_0x0000;
    L_0x0082:
        r0 = r7.nextCDATA();
        r9.put(r0);
        goto L_0x0000;
    L_0x008b:
        r8 = "Expected 'CDATA['";
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x0092:
        r0 = 1;
    L_0x0093:
        r1 = r7.nextMeta();
        if (r1 == 0) goto L_0x00aa;
    L_0x0099:
        r2 = org.json.XML.LT;
        if (r1 != r2) goto L_0x00a0;
    L_0x009d:
        r0 = r0 + 1;
        goto L_0x00a6;
    L_0x00a0:
        r2 = org.json.XML.GT;
        if (r1 != r2) goto L_0x00a6;
    L_0x00a4:
        r0 = r0 + -1;
    L_0x00a6:
        if (r0 > 0) goto L_0x0093;
    L_0x00a8:
        goto L_0x0000;
    L_0x00aa:
        r8 = "Missing '>' after '<!'.";
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x00b1:
        r1 = org.json.XML.QUEST;
        if (r0 != r1) goto L_0x00bc;
    L_0x00b5:
        r0 = "?>";
        r7.skipPast(r0);
        goto L_0x0000;
    L_0x00bc:
        r8 = "Misshaped tag";
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x00c3:
        r1 = r0 instanceof java.lang.String;
        if (r1 == 0) goto L_0x01b6;
    L_0x00c7:
        r0 = (java.lang.String) r0;
        r1 = new org.json.JSONArray;
        r1.<init>();
        r2 = new org.json.JSONObject;
        r2.<init>();
        if (r8 == 0) goto L_0x00de;
    L_0x00d5:
        r1.put(r0);
        if (r9 == 0) goto L_0x00e8;
    L_0x00da:
        r9.put(r1);
        goto L_0x00e8;
    L_0x00de:
        r3 = "tagName";
        r2.put(r3, r0);
        if (r9 == 0) goto L_0x00e8;
    L_0x00e5:
        r9.put(r2);
    L_0x00e8:
        r3 = 0;
    L_0x00e9:
        r4 = r3;
    L_0x00ea:
        if (r4 != 0) goto L_0x00f0;
    L_0x00ec:
        r4 = r7.nextToken();
    L_0x00f0:
        if (r4 == 0) goto L_0x01af;
    L_0x00f2:
        r5 = r4 instanceof java.lang.String;
        if (r5 != 0) goto L_0x0169;
    L_0x00f6:
        if (r8 == 0) goto L_0x0101;
    L_0x00f8:
        r3 = r2.length();
        if (r3 <= 0) goto L_0x0101;
    L_0x00fe:
        r1.put(r2);
    L_0x0101:
        r3 = org.json.XML.SLASH;
        if (r4 != r3) goto L_0x011a;
    L_0x0105:
        r0 = r7.nextToken();
        r3 = org.json.XML.GT;
        if (r0 != r3) goto L_0x0113;
    L_0x010d:
        if (r9 != 0) goto L_0x0000;
    L_0x010f:
        if (r8 == 0) goto L_0x0112;
    L_0x0111:
        return r1;
    L_0x0112:
        return r2;
    L_0x0113:
        r8 = "Misshaped tag";
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x011a:
        r3 = org.json.XML.GT;
        if (r4 != r3) goto L_0x0162;
    L_0x011e:
        r3 = parse(r7, r8, r1);
        r3 = (java.lang.String) r3;
        if (r3 == 0) goto L_0x0000;
    L_0x0126:
        r4 = r3.equals(r0);
        if (r4 == 0) goto L_0x013f;
    L_0x012c:
        if (r8 != 0) goto L_0x0139;
    L_0x012e:
        r0 = r1.length();
        if (r0 <= 0) goto L_0x0139;
    L_0x0134:
        r0 = "childNodes";
        r2.put(r0, r1);
    L_0x0139:
        if (r9 != 0) goto L_0x0000;
    L_0x013b:
        if (r8 == 0) goto L_0x013e;
    L_0x013d:
        return r1;
    L_0x013e:
        return r2;
    L_0x013f:
        r8 = new java.lang.StringBuilder;
        r8.<init>();
        r9 = "Mismatched '";
        r8.append(r9);
        r8.append(r0);
        r9 = "' and '";
        r8.append(r9);
        r8.append(r3);
        r9 = "'";
        r8.append(r9);
        r8 = r8.toString();
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x0162:
        r8 = "Misshaped tag";
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x0169:
        r4 = (java.lang.String) r4;
        if (r8 != 0) goto L_0x0185;
    L_0x016d:
        r5 = "tagName";
        r5 = r5.equals(r4);
        if (r5 != 0) goto L_0x017e;
    L_0x0175:
        r5 = "childNode";
        r5 = r5.equals(r4);
        if (r5 != 0) goto L_0x017e;
    L_0x017d:
        goto L_0x0185;
    L_0x017e:
        r8 = "Reserved attribute.";
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x0185:
        r5 = r7.nextToken();
        r6 = org.json.XML.EQ;
        if (r5 != r6) goto L_0x01a7;
    L_0x018d:
        r5 = r7.nextToken();
        r6 = r5 instanceof java.lang.String;
        if (r6 == 0) goto L_0x01a0;
    L_0x0195:
        r5 = (java.lang.String) r5;
        r5 = org.json.XML.stringToValue(r5);
        r2.accumulate(r4, r5);
        goto L_0x00e9;
    L_0x01a0:
        r8 = "Missing value";
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x01a7:
        r6 = "";
        r2.accumulate(r4, r6);
        r4 = r5;
        goto L_0x00ea;
    L_0x01af:
        r8 = "Misshaped tag";
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x01b6:
        r8 = new java.lang.StringBuilder;
        r8.<init>();
        r9 = "Bad tagName '";
        r8.append(r9);
        r8.append(r0);
        r9 = "'.";
        r8.append(r9);
        r8 = r8.toString();
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x01d1:
        if (r9 == 0) goto L_0x0000;
    L_0x01d3:
        r1 = r0 instanceof java.lang.String;
        if (r1 == 0) goto L_0x01dd;
    L_0x01d7:
        r0 = (java.lang.String) r0;
        r0 = org.json.XML.stringToValue(r0);
    L_0x01dd:
        r9.put(r0);
        goto L_0x0000;
    L_0x01e2:
        r8 = "Bad XML";
        r7 = r7.syntaxError(r8);
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONML.parse(org.json.XMLTokener, boolean, org.json.JSONArray):java.lang.Object");
    }

    public static JSONArray toJSONArray(String str) throws JSONException {
        return toJSONArray(new XMLTokener(str));
    }

    public static JSONArray toJSONArray(XMLTokener xMLTokener) throws JSONException {
        return (JSONArray) parse(xMLTokener, true, null);
    }

    public static JSONObject toJSONObject(XMLTokener xMLTokener) throws JSONException {
        return (JSONObject) parse(xMLTokener, false, null);
    }

    public static JSONObject toJSONObject(String str) throws JSONException {
        return toJSONObject(new XMLTokener(str));
    }

    public static String toString(JSONArray jSONArray) throws JSONException {
        int i;
        StringBuilder stringBuilder = new StringBuilder();
        String string = jSONArray.getString(0);
        XML.noSpace(string);
        string = XML.escape(string);
        stringBuilder.append(Typography.less);
        stringBuilder.append(string);
        Object opt = jSONArray.opt(1);
        if (opt instanceof JSONObject) {
            i = 2;
            JSONObject jSONObject = (JSONObject) opt;
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                XML.noSpace(str);
                String optString = jSONObject.optString(str);
                if (optString != null) {
                    stringBuilder.append(' ');
                    stringBuilder.append(XML.escape(str));
                    stringBuilder.append('=');
                    stringBuilder.append(Typography.quote);
                    stringBuilder.append(XML.escape(optString));
                    stringBuilder.append(Typography.quote);
                }
            }
        } else {
            i = 1;
        }
        int length = jSONArray.length();
        if (i >= length) {
            stringBuilder.append('/');
            stringBuilder.append(Typography.greater);
        } else {
            stringBuilder.append(Typography.greater);
            do {
                Object obj = jSONArray.get(i);
                i++;
                if (obj != null) {
                    if (obj instanceof String) {
                        stringBuilder.append(XML.escape(obj.toString()));
                        continue;
                    } else if (obj instanceof JSONObject) {
                        stringBuilder.append(toString((JSONObject) obj));
                        continue;
                    } else if (obj instanceof JSONArray) {
                        stringBuilder.append(toString((JSONArray) obj));
                        continue;
                    } else {
                        stringBuilder.append(obj.toString());
                        continue;
                    }
                }
            } while (i < length);
            stringBuilder.append(Typography.less);
            stringBuilder.append('/');
            stringBuilder.append(string);
            stringBuilder.append(Typography.greater);
        }
        return stringBuilder.toString();
    }

    public static String toString(JSONObject jSONObject) throws JSONException {
        StringBuilder stringBuilder = new StringBuilder();
        String optString = jSONObject.optString("tagName");
        if (optString == null) {
            return XML.escape(jSONObject.toString());
        }
        XML.noSpace(optString);
        optString = XML.escape(optString);
        stringBuilder.append(Typography.less);
        stringBuilder.append(optString);
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            if (!("tagName".equals(str) || "childNodes".equals(str))) {
                XML.noSpace(str);
                String optString2 = jSONObject.optString(str);
                if (optString2 != null) {
                    stringBuilder.append(' ');
                    stringBuilder.append(XML.escape(str));
                    stringBuilder.append('=');
                    stringBuilder.append(Typography.quote);
                    stringBuilder.append(XML.escape(optString2));
                    stringBuilder.append(Typography.quote);
                }
            }
        }
        jSONObject = jSONObject.optJSONArray("childNodes");
        if (jSONObject == null) {
            stringBuilder.append('/');
            stringBuilder.append(Typography.greater);
        } else {
            stringBuilder.append(Typography.greater);
            int length = jSONObject.length();
            for (int i = 0; i < length; i++) {
                Object obj = jSONObject.get(i);
                if (obj != null) {
                    if (obj instanceof String) {
                        stringBuilder.append(XML.escape(obj.toString()));
                    } else if (obj instanceof JSONObject) {
                        stringBuilder.append(toString((JSONObject) obj));
                    } else if (obj instanceof JSONArray) {
                        stringBuilder.append(toString((JSONArray) obj));
                    } else {
                        stringBuilder.append(obj.toString());
                    }
                }
            }
            stringBuilder.append(Typography.less);
            stringBuilder.append('/');
            stringBuilder.append(optString);
            stringBuilder.append(Typography.greater);
        }
        return stringBuilder.toString();
    }
}
