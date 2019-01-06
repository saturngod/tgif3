package org.json;

import java.util.Iterator;
import kotlin.text.Typography;

public class XML {
    public static final Character AMP = Character.valueOf(Typography.amp);
    public static final Character APOS = Character.valueOf('\'');
    public static final Character BANG = Character.valueOf('!');
    public static final Character EQ = Character.valueOf('=');
    public static final Character GT = Character.valueOf(Typography.greater);
    public static final Character LT = Character.valueOf(Typography.less);
    public static final Character QUEST = Character.valueOf('?');
    public static final Character QUOT = Character.valueOf(Typography.quote);
    public static final Character SLASH = Character.valueOf('/');

    public static String escape(String str) {
        StringBuilder stringBuilder = new StringBuilder(str.length());
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == Typography.quote) {
                stringBuilder.append("&quot;");
            } else if (charAt == Typography.less) {
                stringBuilder.append("&lt;");
            } else if (charAt != Typography.greater) {
                switch (charAt) {
                    case '&':
                        stringBuilder.append("&amp;");
                        break;
                    case '\'':
                        stringBuilder.append("&apos;");
                        break;
                    default:
                        stringBuilder.append(charAt);
                        break;
                }
            } else {
                stringBuilder.append("&gt;");
            }
        }
        return stringBuilder.toString();
    }

    public static void noSpace(String str) throws JSONException {
        int length = str.length();
        if (length != 0) {
            for (int i = 0; i < length; i++) {
                if (Character.isWhitespace(str.charAt(i))) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("'");
                    stringBuilder.append(str);
                    stringBuilder.append("' contains a space character.");
                    throw new JSONException(stringBuilder.toString());
                }
            }
            return;
        }
        throw new JSONException("Empty string.");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean parse(org.json.XMLTokener r7, org.json.JSONObject r8, java.lang.String r9) throws org.json.JSONException {
        /*
        r0 = r7.nextToken();
        r1 = BANG;
        r2 = 1;
        r3 = 0;
        if (r0 != r1) goto L_0x006c;
    L_0x000a:
        r9 = r7.next();
        r0 = 45;
        if (r9 != r0) goto L_0x0022;
    L_0x0012:
        r8 = r7.next();
        if (r8 != r0) goto L_0x001e;
    L_0x0018:
        r8 = "-->";
        r7.skipPast(r8);
        return r3;
    L_0x001e:
        r7.back();
        goto L_0x004f;
    L_0x0022:
        r0 = 91;
        if (r9 != r0) goto L_0x004f;
    L_0x0026:
        r9 = r7.nextToken();
        r1 = "CDATA";
        r9 = r1.equals(r9);
        if (r9 == 0) goto L_0x0048;
    L_0x0032:
        r9 = r7.next();
        if (r9 != r0) goto L_0x0048;
    L_0x0038:
        r7 = r7.nextCDATA();
        r9 = r7.length();
        if (r9 <= 0) goto L_0x0047;
    L_0x0042:
        r9 = "content";
        r8.accumulate(r9, r7);
    L_0x0047:
        return r3;
    L_0x0048:
        r8 = "Expected 'CDATA['";
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x004f:
        r8 = r7.nextMeta();
        if (r8 == 0) goto L_0x0065;
    L_0x0055:
        r9 = LT;
        if (r8 != r9) goto L_0x005c;
    L_0x0059:
        r2 = r2 + 1;
        goto L_0x0062;
    L_0x005c:
        r9 = GT;
        if (r8 != r9) goto L_0x0062;
    L_0x0060:
        r2 = r2 + -1;
    L_0x0062:
        if (r2 > 0) goto L_0x004f;
    L_0x0064:
        return r3;
    L_0x0065:
        r8 = "Missing '>' after '<!'.";
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x006c:
        r1 = QUEST;
        if (r0 != r1) goto L_0x0076;
    L_0x0070:
        r8 = "?>";
        r7.skipPast(r8);
        return r3;
    L_0x0076:
        r1 = SLASH;
        if (r0 != r1) goto L_0x00ca;
    L_0x007a:
        r8 = r7.nextToken();
        if (r9 == 0) goto L_0x00b4;
    L_0x0080:
        r0 = r8.equals(r9);
        if (r0 == 0) goto L_0x0096;
    L_0x0086:
        r8 = r7.nextToken();
        r9 = GT;
        if (r8 != r9) goto L_0x008f;
    L_0x008e:
        return r2;
    L_0x008f:
        r8 = "Misshaped close tag";
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x0096:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Mismatched ";
        r0.append(r1);
        r0.append(r9);
        r9 = " and ";
        r0.append(r9);
        r0.append(r8);
        r8 = r0.toString();
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x00b4:
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r0 = "Mismatched close tag ";
        r9.append(r0);
        r9.append(r8);
        r8 = r9.toString();
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x00ca:
        r9 = r0 instanceof java.lang.Character;
        if (r9 != 0) goto L_0x01a0;
    L_0x00ce:
        r0 = (java.lang.String) r0;
        r9 = new org.json.JSONObject;
        r9.<init>();
        r1 = 0;
    L_0x00d6:
        r4 = r1;
    L_0x00d7:
        if (r4 != 0) goto L_0x00dd;
    L_0x00d9:
        r4 = r7.nextToken();
    L_0x00dd:
        r5 = r4 instanceof java.lang.String;
        if (r5 == 0) goto L_0x010b;
    L_0x00e1:
        r4 = (java.lang.String) r4;
        r5 = r7.nextToken();
        r6 = EQ;
        if (r5 != r6) goto L_0x0104;
    L_0x00eb:
        r5 = r7.nextToken();
        r6 = r5 instanceof java.lang.String;
        if (r6 == 0) goto L_0x00fd;
    L_0x00f3:
        r5 = (java.lang.String) r5;
        r5 = stringToValue(r5);
        r9.accumulate(r4, r5);
        goto L_0x00d6;
    L_0x00fd:
        r8 = "Missing value";
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x0104:
        r6 = "";
        r9.accumulate(r4, r6);
        r4 = r5;
        goto L_0x00d7;
    L_0x010b:
        r1 = SLASH;
        if (r4 != r1) goto L_0x012e;
    L_0x010f:
        r1 = r7.nextToken();
        r2 = GT;
        if (r1 != r2) goto L_0x0127;
    L_0x0117:
        r7 = r9.length();
        if (r7 <= 0) goto L_0x0121;
    L_0x011d:
        r8.accumulate(r0, r9);
        goto L_0x0126;
    L_0x0121:
        r7 = "";
        r8.accumulate(r0, r7);
    L_0x0126:
        return r3;
    L_0x0127:
        r8 = "Misshaped tag";
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x012e:
        r1 = GT;
        if (r4 != r1) goto L_0x0199;
    L_0x0132:
        r1 = r7.nextContent();
        if (r1 != 0) goto L_0x0151;
    L_0x0138:
        if (r0 != 0) goto L_0x013b;
    L_0x013a:
        return r3;
    L_0x013b:
        r8 = new java.lang.StringBuilder;
        r8.<init>();
        r9 = "Unclosed tag ";
        r8.append(r9);
        r8.append(r0);
        r8 = r8.toString();
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x0151:
        r4 = r1 instanceof java.lang.String;
        if (r4 == 0) goto L_0x0167;
    L_0x0155:
        r1 = (java.lang.String) r1;
        r4 = r1.length();
        if (r4 <= 0) goto L_0x0132;
    L_0x015d:
        r4 = "content";
        r1 = stringToValue(r1);
        r9.accumulate(r4, r1);
        goto L_0x0132;
    L_0x0167:
        r4 = LT;
        if (r1 != r4) goto L_0x0132;
    L_0x016b:
        r1 = parse(r7, r9, r0);
        if (r1 == 0) goto L_0x0132;
    L_0x0171:
        r7 = r9.length();
        if (r7 != 0) goto L_0x017d;
    L_0x0177:
        r7 = "";
        r8.accumulate(r0, r7);
        goto L_0x0198;
    L_0x017d:
        r7 = r9.length();
        if (r7 != r2) goto L_0x0195;
    L_0x0183:
        r7 = "content";
        r7 = r9.opt(r7);
        if (r7 == 0) goto L_0x0195;
    L_0x018b:
        r7 = "content";
        r7 = r9.opt(r7);
        r8.accumulate(r0, r7);
        goto L_0x0198;
    L_0x0195:
        r8.accumulate(r0, r9);
    L_0x0198:
        return r3;
    L_0x0199:
        r8 = "Misshaped tag";
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x01a0:
        r8 = "Misshaped tag";
        r7 = r7.syntaxError(r8);
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.XML.parse(org.json.XMLTokener, org.json.JSONObject, java.lang.String):boolean");
    }

    public static java.lang.Object stringToValue(java.lang.String r2) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = "true";
        r0 = r0.equalsIgnoreCase(r2);
        if (r0 == 0) goto L_0x000b;
    L_0x0008:
        r2 = java.lang.Boolean.TRUE;
        return r2;
    L_0x000b:
        r0 = "false";
        r0 = r0.equalsIgnoreCase(r2);
        if (r0 == 0) goto L_0x0016;
    L_0x0013:
        r2 = java.lang.Boolean.FALSE;
        return r2;
    L_0x0016:
        r0 = "null";
        r0 = r0.equalsIgnoreCase(r2);
        if (r0 == 0) goto L_0x0021;
    L_0x001e:
        r2 = org.json.JSONObject.NULL;
        return r2;
    L_0x0021:
        r0 = 0;
        r0 = r2.charAt(r0);	 Catch:{ Exception -> 0x0042 }
        r1 = 45;	 Catch:{ Exception -> 0x0042 }
        if (r0 == r1) goto L_0x0032;	 Catch:{ Exception -> 0x0042 }
    L_0x002a:
        r1 = 48;	 Catch:{ Exception -> 0x0042 }
        if (r0 < r1) goto L_0x0052;	 Catch:{ Exception -> 0x0042 }
    L_0x002e:
        r1 = 57;	 Catch:{ Exception -> 0x0042 }
        if (r0 > r1) goto L_0x0052;	 Catch:{ Exception -> 0x0042 }
    L_0x0032:
        r0 = new java.lang.Long;	 Catch:{ Exception -> 0x0042 }
        r0.<init>(r2);	 Catch:{ Exception -> 0x0042 }
        r1 = r0.toString();	 Catch:{ Exception -> 0x0042 }
        r1 = r1.equals(r2);	 Catch:{ Exception -> 0x0042 }
        if (r1 == 0) goto L_0x0052;
    L_0x0041:
        return r0;
    L_0x0042:
        r0 = new java.lang.Double;	 Catch:{ Exception -> 0x0052 }
        r0.<init>(r2);	 Catch:{ Exception -> 0x0052 }
        r1 = r0.toString();	 Catch:{ Exception -> 0x0052 }
        r1 = r1.equals(r2);	 Catch:{ Exception -> 0x0052 }
        if (r1 == 0) goto L_0x0052;
    L_0x0051:
        return r0;
    L_0x0052:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.XML.stringToValue(java.lang.String):java.lang.Object");
    }

    public static JSONObject toJSONObject(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        XMLTokener xMLTokener = new XMLTokener(str);
        while (xMLTokener.more() != null && xMLTokener.skipPast("<") != null) {
            parse(xMLTokener, jSONObject, null);
        }
        return jSONObject;
    }

    public static String toString(Object obj) throws JSONException {
        return toString(obj, null);
    }

    public static String toString(Object obj, String str) throws JSONException {
        StringBuilder stringBuilder = new StringBuilder();
        if (obj instanceof JSONObject) {
            if (str != null) {
                stringBuilder.append(Typography.less);
                stringBuilder.append(str);
                stringBuilder.append(Typography.greater);
            }
            JSONObject jSONObject = (JSONObject) obj;
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                Object opt = jSONObject.opt(str2);
                if (opt == null) {
                    opt = "";
                }
                if (opt instanceof String) {
                    String str3 = (String) opt;
                }
                JSONArray jSONArray;
                int i;
                if ("content".equals(str2)) {
                    if (opt instanceof JSONArray) {
                        jSONArray = (JSONArray) opt;
                        int length = jSONArray.length();
                        for (i = 0; i < length; i++) {
                            if (i > 0) {
                                stringBuilder.append('\n');
                            }
                            stringBuilder.append(escape(jSONArray.get(i).toString()));
                        }
                    } else {
                        stringBuilder.append(escape(opt.toString()));
                    }
                } else if (opt instanceof JSONArray) {
                    jSONArray = (JSONArray) opt;
                    i = jSONArray.length();
                    for (int i2 = 0; i2 < i; i2++) {
                        Object obj2 = jSONArray.get(i2);
                        if (obj2 instanceof JSONArray) {
                            stringBuilder.append(Typography.less);
                            stringBuilder.append(str2);
                            stringBuilder.append(Typography.greater);
                            stringBuilder.append(toString(obj2));
                            stringBuilder.append("</");
                            stringBuilder.append(str2);
                            stringBuilder.append(Typography.greater);
                        } else {
                            stringBuilder.append(toString(obj2, str2));
                        }
                    }
                } else if ("".equals(opt)) {
                    stringBuilder.append(Typography.less);
                    stringBuilder.append(str2);
                    stringBuilder.append("/>");
                } else {
                    stringBuilder.append(toString(opt, str2));
                }
            }
            if (str != null) {
                stringBuilder.append("</");
                stringBuilder.append(str);
                stringBuilder.append(Typography.greater);
            }
            return stringBuilder.toString();
        }
        if (obj.getClass().isArray()) {
            obj = new JSONArray(obj);
        }
        if (obj instanceof JSONArray) {
            JSONArray jSONArray2 = (JSONArray) obj;
            int length2 = jSONArray2.length();
            for (int i3 = 0; i3 < length2; i3++) {
                stringBuilder.append(toString(jSONArray2.opt(i3), str == null ? "array" : str));
            }
            return stringBuilder.toString();
        }
        if (obj == null) {
            obj = "null";
        } else {
            obj = escape(obj.toString());
        }
        if (str == null) {
            str = new StringBuilder();
            str.append("\"");
            str.append(obj);
            str.append("\"");
            obj = str.toString();
        } else if (obj.length() == 0) {
            obj = new StringBuilder();
            obj.append("<");
            obj.append(str);
            obj.append("/>");
            obj = obj.toString();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("<");
            stringBuilder.append(str);
            stringBuilder.append(">");
            stringBuilder.append(obj);
            stringBuilder.append("</");
            stringBuilder.append(str);
            stringBuilder.append(">");
            obj = stringBuilder.toString();
        }
        return obj;
    }
}
