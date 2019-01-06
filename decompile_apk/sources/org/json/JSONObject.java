package org.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;
import kotlin.text.Typography;

public class JSONObject {
    public static final Object NULL = new Null();
    private final Map<String, Object> map;

    private static final class Null {
        protected final Object clone() {
            return this;
        }

        public boolean equals(Object obj) {
            if (obj != null) {
                if (obj != this) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            return "null";
        }

        private Null() {
        }
    }

    public JSONObject() {
        this.map = new HashMap();
    }

    public JSONObject(org.json.JSONObject r4, java.lang.String[] r5) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r3 = this;
        r3.<init>();
        r0 = 0;
    L_0x0004:
        r1 = r5.length;
        if (r0 >= r1) goto L_0x0015;
    L_0x0007:
        r1 = r5[r0];	 Catch:{ Exception -> 0x0012 }
        r2 = r5[r0];	 Catch:{ Exception -> 0x0012 }
        r2 = r4.opt(r2);	 Catch:{ Exception -> 0x0012 }
        r3.putOnce(r1, r2);	 Catch:{ Exception -> 0x0012 }
    L_0x0012:
        r0 = r0 + 1;
        goto L_0x0004;
    L_0x0015:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONObject.<init>(org.json.JSONObject, java.lang.String[]):void");
    }

    public JSONObject(JSONTokener jSONTokener) throws JSONException {
        this();
        if (jSONTokener.nextClean() == '{') {
            char nextClean;
            while (true) {
                nextClean = jSONTokener.nextClean();
                if (nextClean == '\u0000') {
                    throw jSONTokener.syntaxError("A JSONObject text must end with '}'");
                } else if (nextClean != '}') {
                    jSONTokener.back();
                    String obj = jSONTokener.nextValue().toString();
                    if (jSONTokener.nextClean() == ':') {
                        putOnce(obj, jSONTokener.nextValue());
                        nextClean = jSONTokener.nextClean();
                        if (nextClean != ',' && nextClean != ';') {
                            break;
                        } else if (jSONTokener.nextClean() != '}') {
                            jSONTokener.back();
                        } else {
                            return;
                        }
                    }
                    throw jSONTokener.syntaxError("Expected a ':' after a key");
                } else {
                    return;
                }
            }
            if (nextClean != '}') {
                throw jSONTokener.syntaxError("Expected a ',' or '}'");
            }
            return;
        }
        throw jSONTokener.syntaxError("A JSONObject text must begin with '{'");
    }

    public JSONObject(Map<String, Object> map) {
        this.map = new HashMap();
        if (map != null) {
            map = map.entrySet().iterator();
            while (map.hasNext()) {
                Entry entry = (Entry) map.next();
                Object value = entry.getValue();
                if (value != null) {
                    this.map.put(entry.getKey(), wrap(value));
                }
            }
        }
    }

    public JSONObject(Object obj) {
        this();
        populateMap(obj);
    }

    public JSONObject(java.lang.Object r5, java.lang.String[] r6) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r4 = this;
        r4.<init>();
        r0 = r5.getClass();
        r1 = 0;
    L_0x0008:
        r2 = r6.length;
        if (r1 >= r2) goto L_0x001b;
    L_0x000b:
        r2 = r6[r1];
        r3 = r0.getField(r2);	 Catch:{ Exception -> 0x0018 }
        r3 = r3.get(r5);	 Catch:{ Exception -> 0x0018 }
        r4.putOpt(r2, r3);	 Catch:{ Exception -> 0x0018 }
    L_0x0018:
        r1 = r1 + 1;
        goto L_0x0008;
    L_0x001b:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONObject.<init>(java.lang.Object, java.lang.String[]):void");
    }

    public JSONObject(String str) throws JSONException {
        this(new JSONTokener(str));
    }

    public JSONObject(String str, Locale locale) throws JSONException {
        this();
        str = ResourceBundle.getBundle(str, locale, Thread.currentThread().getContextClassLoader());
        locale = str.getKeys();
        while (locale.hasMoreElements()) {
            Object nextElement = locale.nextElement();
            if (nextElement != null) {
                String str2 = (String) nextElement;
                String[] split = str2.split("\\.");
                int length = split.length - 1;
                JSONObject jSONObject = this;
                for (int i = 0; i < length; i++) {
                    String str3 = split[i];
                    JSONObject optJSONObject = jSONObject.optJSONObject(str3);
                    if (optJSONObject == null) {
                        optJSONObject = new JSONObject();
                        jSONObject.put(str3, (Object) optJSONObject);
                    }
                    jSONObject = optJSONObject;
                }
                jSONObject.put(split[length], str.getString(str2));
            }
        }
    }

    public JSONObject accumulate(String str, Object obj) throws JSONException {
        testValidity(obj);
        Object opt = opt(str);
        if (opt == null) {
            if (obj instanceof JSONArray) {
                obj = new JSONArray().put(obj);
            }
            put(str, obj);
        } else if (opt instanceof JSONArray) {
            ((JSONArray) opt).put(obj);
        } else {
            put(str, new JSONArray().put(opt).put(obj));
        }
        return this;
    }

    public JSONObject append(String str, Object obj) throws JSONException {
        testValidity(obj);
        Object opt = opt(str);
        if (opt == null) {
            put(str, new JSONArray().put(obj));
        } else if (opt instanceof JSONArray) {
            put(str, ((JSONArray) opt).put(obj));
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("JSONObject[");
            stringBuilder.append(str);
            stringBuilder.append("] is not a JSONArray.");
            throw new JSONException(stringBuilder.toString());
        }
        return this;
    }

    public static String doubleToString(double d) {
        if (!Double.isInfinite(d)) {
            if (!Double.isNaN(d)) {
                d = Double.toString(d);
                if (d.indexOf(46) > 0 && d.indexOf(101) < 0 && d.indexOf(69) < 0) {
                    while (d.endsWith("0")) {
                        d = d.substring(0, d.length() - 1);
                    }
                    if (d.endsWith(".")) {
                        d = d.substring(0, d.length() - 1);
                    }
                }
                return d;
            }
        }
        return "null";
    }

    public Object get(String str) throws JSONException {
        if (str != null) {
            Object opt = opt(str);
            if (opt != null) {
                return opt;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("JSONObject[");
            stringBuilder.append(quote(str));
            stringBuilder.append("] not found.");
            throw new JSONException(stringBuilder.toString());
        }
        throw new JSONException("Null key.");
    }

    public <E extends Enum<E>> E getEnum(Class<E> cls, String str) throws JSONException {
        E optEnum = optEnum(cls, str);
        if (optEnum != null) {
            return optEnum;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("JSONObject[");
        stringBuilder.append(quote(str));
        stringBuilder.append("] is not an enum of type ");
        stringBuilder.append(quote(cls.getSimpleName()));
        stringBuilder.append(".");
        throw new JSONException(stringBuilder.toString());
    }

    public boolean getBoolean(String str) throws JSONException {
        Object obj = get(str);
        if (!obj.equals(Boolean.FALSE)) {
            boolean z = obj instanceof String;
            if (!z || !((String) obj).equalsIgnoreCase("false")) {
                if (!obj.equals(Boolean.TRUE)) {
                    if (!z || !((String) obj).equalsIgnoreCase("true")) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("JSONObject[");
                        stringBuilder.append(quote(str));
                        stringBuilder.append("] is not a Boolean.");
                        throw new JSONException(stringBuilder.toString());
                    }
                }
                return true;
            }
        }
        return null;
    }

    public java.math.BigInteger getBigInteger(java.lang.String r4) throws org.json.JSONException {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r3 = this;
        r0 = r3.get(r4);
        r1 = new java.math.BigInteger;	 Catch:{ Exception -> 0x000e }
        r0 = r0.toString();	 Catch:{ Exception -> 0x000e }
        r1.<init>(r0);	 Catch:{ Exception -> 0x000e }
        return r1;
    L_0x000e:
        r0 = new org.json.JSONException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "JSONObject[";
        r1.append(r2);
        r4 = quote(r4);
        r1.append(r4);
        r4 = "] could not be converted to BigInteger.";
        r1.append(r4);
        r4 = r1.toString();
        r0.<init>(r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONObject.getBigInteger(java.lang.String):java.math.BigInteger");
    }

    public java.math.BigDecimal getBigDecimal(java.lang.String r4) throws org.json.JSONException {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r3 = this;
        r0 = r3.get(r4);
        r1 = new java.math.BigDecimal;	 Catch:{ Exception -> 0x000e }
        r0 = r0.toString();	 Catch:{ Exception -> 0x000e }
        r1.<init>(r0);	 Catch:{ Exception -> 0x000e }
        return r1;
    L_0x000e:
        r0 = new org.json.JSONException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "JSONObject[";
        r1.append(r2);
        r4 = quote(r4);
        r1.append(r4);
        r4 = "] could not be converted to BigDecimal.";
        r1.append(r4);
        r4 = r1.toString();
        r0.<init>(r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONObject.getBigDecimal(java.lang.String):java.math.BigDecimal");
    }

    public double getDouble(java.lang.String r4) throws org.json.JSONException {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r3 = this;
        r0 = r3.get(r4);
        r1 = r0 instanceof java.lang.Number;	 Catch:{ Exception -> 0x0016 }
        if (r1 == 0) goto L_0x000f;	 Catch:{ Exception -> 0x0016 }
    L_0x0008:
        r0 = (java.lang.Number) r0;	 Catch:{ Exception -> 0x0016 }
        r0 = r0.doubleValue();	 Catch:{ Exception -> 0x0016 }
        goto L_0x0015;	 Catch:{ Exception -> 0x0016 }
    L_0x000f:
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0016 }
        r0 = java.lang.Double.parseDouble(r0);	 Catch:{ Exception -> 0x0016 }
    L_0x0015:
        return r0;
    L_0x0016:
        r0 = new org.json.JSONException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "JSONObject[";
        r1.append(r2);
        r4 = quote(r4);
        r1.append(r4);
        r4 = "] is not a number.";
        r1.append(r4);
        r4 = r1.toString();
        r0.<init>(r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONObject.getDouble(java.lang.String):double");
    }

    public int getInt(java.lang.String r4) throws org.json.JSONException {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r3 = this;
        r0 = r3.get(r4);
        r1 = r0 instanceof java.lang.Number;	 Catch:{ Exception -> 0x0016 }
        if (r1 == 0) goto L_0x000f;	 Catch:{ Exception -> 0x0016 }
    L_0x0008:
        r0 = (java.lang.Number) r0;	 Catch:{ Exception -> 0x0016 }
        r0 = r0.intValue();	 Catch:{ Exception -> 0x0016 }
        goto L_0x0015;	 Catch:{ Exception -> 0x0016 }
    L_0x000f:
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0016 }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ Exception -> 0x0016 }
    L_0x0015:
        return r0;
    L_0x0016:
        r0 = new org.json.JSONException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "JSONObject[";
        r1.append(r2);
        r4 = quote(r4);
        r1.append(r4);
        r4 = "] is not an int.";
        r1.append(r4);
        r4 = r1.toString();
        r0.<init>(r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONObject.getInt(java.lang.String):int");
    }

    public JSONArray getJSONArray(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof JSONArray) {
            return (JSONArray) obj;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("JSONObject[");
        stringBuilder.append(quote(str));
        stringBuilder.append("] is not a JSONArray.");
        throw new JSONException(stringBuilder.toString());
    }

    public JSONObject getJSONObject(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("JSONObject[");
        stringBuilder.append(quote(str));
        stringBuilder.append("] is not a JSONObject.");
        throw new JSONException(stringBuilder.toString());
    }

    public long getLong(java.lang.String r4) throws org.json.JSONException {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r3 = this;
        r0 = r3.get(r4);
        r1 = r0 instanceof java.lang.Number;	 Catch:{ Exception -> 0x0016 }
        if (r1 == 0) goto L_0x000f;	 Catch:{ Exception -> 0x0016 }
    L_0x0008:
        r0 = (java.lang.Number) r0;	 Catch:{ Exception -> 0x0016 }
        r0 = r0.longValue();	 Catch:{ Exception -> 0x0016 }
        goto L_0x0015;	 Catch:{ Exception -> 0x0016 }
    L_0x000f:
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0016 }
        r0 = java.lang.Long.parseLong(r0);	 Catch:{ Exception -> 0x0016 }
    L_0x0015:
        return r0;
    L_0x0016:
        r0 = new org.json.JSONException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "JSONObject[";
        r1.append(r2);
        r4 = quote(r4);
        r1.append(r4);
        r4 = "] is not a long.";
        r1.append(r4);
        r4 = r1.toString();
        r0.<init>(r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONObject.getLong(java.lang.String):long");
    }

    public static String[] getNames(JSONObject jSONObject) {
        int length = jSONObject.length();
        if (length == 0) {
            return null;
        }
        jSONObject = jSONObject.keys();
        String[] strArr = new String[length];
        int i = 0;
        while (jSONObject.hasNext()) {
            strArr[i] = (String) jSONObject.next();
            i++;
        }
        return strArr;
    }

    public static String[] getNames(Object obj) {
        if (obj == null) {
            return null;
        }
        obj = obj.getClass().getFields();
        int length = obj.length;
        if (length == 0) {
            return null;
        }
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = obj[i].getName();
        }
        return strArr;
    }

    public String getString(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof String) {
            return (String) obj;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("JSONObject[");
        stringBuilder.append(quote(str));
        stringBuilder.append("] not a string.");
        throw new JSONException(stringBuilder.toString());
    }

    public boolean has(String str) {
        return this.map.containsKey(str);
    }

    public JSONObject increment(String str) throws JSONException {
        Object opt = opt(str);
        if (opt == null) {
            put(str, 1);
        } else if (opt instanceof BigInteger) {
            put(str, ((BigInteger) opt).add(BigInteger.ONE));
        } else if (opt instanceof BigDecimal) {
            put(str, ((BigDecimal) opt).add(BigDecimal.ONE));
        } else if (opt instanceof Integer) {
            put(str, ((Integer) opt).intValue() + 1);
        } else if (opt instanceof Long) {
            put(str, ((Long) opt).longValue() + 1);
        } else if (opt instanceof Double) {
            put(str, ((Double) opt).doubleValue() + 1.0d);
        } else if (opt instanceof Float) {
            put(str, (double) (((Float) opt).floatValue() + 1.0f));
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unable to increment [");
            stringBuilder.append(quote(str));
            stringBuilder.append("].");
            throw new JSONException(stringBuilder.toString());
        }
        return this;
    }

    public boolean isNull(String str) {
        return NULL.equals(opt(str));
    }

    public Iterator<String> keys() {
        return keySet().iterator();
    }

    public Set<String> keySet() {
        return this.map.keySet();
    }

    public int length() {
        return this.map.size();
    }

    public JSONArray names() {
        JSONArray jSONArray = new JSONArray();
        Iterator keys = keys();
        while (keys.hasNext()) {
            jSONArray.put(keys.next());
        }
        return jSONArray.length() == 0 ? null : jSONArray;
    }

    public static String numberToString(Number number) throws JSONException {
        if (number != null) {
            testValidity(number);
            number = number.toString();
            if (number.indexOf(46) <= 0 || number.indexOf(101) >= 0 || number.indexOf(69) >= 0) {
                return number;
            }
            while (number.endsWith("0")) {
                number = number.substring(0, number.length() - 1);
            }
            return number.endsWith(".") ? number.substring(0, number.length() - 1) : number;
        } else {
            throw new JSONException("Null pointer");
        }
    }

    public Object opt(String str) {
        return str == null ? null : this.map.get(str);
    }

    public <E extends Enum<E>> E optEnum(Class<E> cls, String str) {
        return optEnum(cls, str, null);
    }

    public <E extends java.lang.Enum<E>> E optEnum(java.lang.Class<E> r2, java.lang.String r3, E r4) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r1 = this;
        r3 = r1.opt(r3);	 Catch:{ IllegalArgumentException -> 0x0023, IllegalArgumentException -> 0x0023 }
        r0 = NULL;	 Catch:{ IllegalArgumentException -> 0x0023, IllegalArgumentException -> 0x0023 }
        r0 = r0.equals(r3);	 Catch:{ IllegalArgumentException -> 0x0023, IllegalArgumentException -> 0x0023 }
        if (r0 == 0) goto L_0x000d;	 Catch:{ IllegalArgumentException -> 0x0023, IllegalArgumentException -> 0x0023 }
    L_0x000c:
        return r4;	 Catch:{ IllegalArgumentException -> 0x0023, IllegalArgumentException -> 0x0023 }
    L_0x000d:
        r0 = r3.getClass();	 Catch:{ IllegalArgumentException -> 0x0023, IllegalArgumentException -> 0x0023 }
        r0 = r2.isAssignableFrom(r0);	 Catch:{ IllegalArgumentException -> 0x0023, IllegalArgumentException -> 0x0023 }
        if (r0 == 0) goto L_0x001a;	 Catch:{ IllegalArgumentException -> 0x0023, IllegalArgumentException -> 0x0023 }
    L_0x0017:
        r3 = (java.lang.Enum) r3;	 Catch:{ IllegalArgumentException -> 0x0023, IllegalArgumentException -> 0x0023 }
        return r3;	 Catch:{ IllegalArgumentException -> 0x0023, IllegalArgumentException -> 0x0023 }
    L_0x001a:
        r3 = r3.toString();	 Catch:{ IllegalArgumentException -> 0x0023, IllegalArgumentException -> 0x0023 }
        r2 = java.lang.Enum.valueOf(r2, r3);	 Catch:{ IllegalArgumentException -> 0x0023, IllegalArgumentException -> 0x0023 }
        return r2;
    L_0x0023:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONObject.optEnum(java.lang.Class, java.lang.String, java.lang.Enum):E");
    }

    public boolean optBoolean(String str) {
        return optBoolean(str, false);
    }

    public boolean optBoolean(java.lang.String r1, boolean r2) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = this;
        r1 = r0.getBoolean(r1);	 Catch:{ Exception -> 0x0005 }
        return r1;
    L_0x0005:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONObject.optBoolean(java.lang.String, boolean):boolean");
    }

    public double optDouble(String str) {
        return optDouble(str, Double.NaN);
    }

    public java.math.BigInteger optBigInteger(java.lang.String r1, java.math.BigInteger r2) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = this;
        r1 = r0.getBigInteger(r1);	 Catch:{ Exception -> 0x0005 }
        return r1;
    L_0x0005:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONObject.optBigInteger(java.lang.String, java.math.BigInteger):java.math.BigInteger");
    }

    public java.math.BigDecimal optBigDecimal(java.lang.String r1, java.math.BigDecimal r2) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = this;
        r1 = r0.getBigDecimal(r1);	 Catch:{ Exception -> 0x0005 }
        return r1;
    L_0x0005:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONObject.optBigDecimal(java.lang.String, java.math.BigDecimal):java.math.BigDecimal");
    }

    public double optDouble(java.lang.String r3, double r4) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r2 = this;
        r0 = r2.getDouble(r3);	 Catch:{ Exception -> 0x0005 }
        return r0;
    L_0x0005:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONObject.optDouble(java.lang.String, double):double");
    }

    public int optInt(String str) {
        return optInt(str, 0);
    }

    public int optInt(java.lang.String r1, int r2) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = this;
        r1 = r0.getInt(r1);	 Catch:{ Exception -> 0x0005 }
        return r1;
    L_0x0005:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONObject.optInt(java.lang.String, int):int");
    }

    public JSONArray optJSONArray(String str) {
        str = opt(str);
        return str instanceof JSONArray ? (JSONArray) str : null;
    }

    public JSONObject optJSONObject(String str) {
        str = opt(str);
        return str instanceof JSONObject ? (JSONObject) str : null;
    }

    public long optLong(String str) {
        return optLong(str, 0);
    }

    public long optLong(java.lang.String r3, long r4) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r2 = this;
        r0 = r2.getLong(r3);	 Catch:{ Exception -> 0x0005 }
        return r0;
    L_0x0005:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONObject.optLong(java.lang.String, long):long");
    }

    public String optString(String str) {
        return optString(str, "");
    }

    public String optString(String str, String str2) {
        str = opt(str);
        return NULL.equals(str) ? str2 : str.toString();
    }

    private void populateMap(java.lang.Object r9) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r8 = this;
        r0 = r9.getClass();
        r1 = r0.getClassLoader();
        r2 = 0;
        r3 = 1;
        if (r1 == 0) goto L_0x000e;
    L_0x000c:
        r1 = 1;
        goto L_0x000f;
    L_0x000e:
        r1 = 0;
    L_0x000f:
        if (r1 == 0) goto L_0x0016;
    L_0x0011:
        r0 = r0.getMethods();
        goto L_0x001a;
    L_0x0016:
        r0 = r0.getDeclaredMethods();
    L_0x001a:
        r1 = 0;
    L_0x001b:
        r4 = r0.length;
        if (r1 >= r4) goto L_0x00bc;
    L_0x001e:
        r4 = r0[r1];	 Catch:{ Exception -> 0x00b8 }
        r5 = r4.getModifiers();	 Catch:{ Exception -> 0x00b8 }
        r5 = java.lang.reflect.Modifier.isPublic(r5);	 Catch:{ Exception -> 0x00b8 }
        if (r5 == 0) goto L_0x00b8;	 Catch:{ Exception -> 0x00b8 }
    L_0x002a:
        r5 = r4.getName();	 Catch:{ Exception -> 0x00b8 }
        r6 = "";	 Catch:{ Exception -> 0x00b8 }
        r7 = "get";	 Catch:{ Exception -> 0x00b8 }
        r7 = r5.startsWith(r7);	 Catch:{ Exception -> 0x00b8 }
        if (r7 == 0) goto L_0x0052;	 Catch:{ Exception -> 0x00b8 }
    L_0x0038:
        r6 = "getClass";	 Catch:{ Exception -> 0x00b8 }
        r6 = r6.equals(r5);	 Catch:{ Exception -> 0x00b8 }
        if (r6 != 0) goto L_0x004f;	 Catch:{ Exception -> 0x00b8 }
    L_0x0040:
        r6 = "getDeclaringClass";	 Catch:{ Exception -> 0x00b8 }
        r6 = r6.equals(r5);	 Catch:{ Exception -> 0x00b8 }
        if (r6 == 0) goto L_0x0049;	 Catch:{ Exception -> 0x00b8 }
    L_0x0048:
        goto L_0x004f;	 Catch:{ Exception -> 0x00b8 }
    L_0x0049:
        r6 = 3;	 Catch:{ Exception -> 0x00b8 }
        r6 = r5.substring(r6);	 Catch:{ Exception -> 0x00b8 }
        goto L_0x005f;	 Catch:{ Exception -> 0x00b8 }
    L_0x004f:
        r6 = "";	 Catch:{ Exception -> 0x00b8 }
        goto L_0x005f;	 Catch:{ Exception -> 0x00b8 }
    L_0x0052:
        r7 = "is";	 Catch:{ Exception -> 0x00b8 }
        r7 = r5.startsWith(r7);	 Catch:{ Exception -> 0x00b8 }
        if (r7 == 0) goto L_0x005f;	 Catch:{ Exception -> 0x00b8 }
    L_0x005a:
        r6 = 2;	 Catch:{ Exception -> 0x00b8 }
        r6 = r5.substring(r6);	 Catch:{ Exception -> 0x00b8 }
    L_0x005f:
        r5 = r6.length();	 Catch:{ Exception -> 0x00b8 }
        if (r5 <= 0) goto L_0x00b8;	 Catch:{ Exception -> 0x00b8 }
    L_0x0065:
        r5 = r6.charAt(r2);	 Catch:{ Exception -> 0x00b8 }
        r5 = java.lang.Character.isUpperCase(r5);	 Catch:{ Exception -> 0x00b8 }
        if (r5 == 0) goto L_0x00b8;	 Catch:{ Exception -> 0x00b8 }
    L_0x006f:
        r5 = r4.getParameterTypes();	 Catch:{ Exception -> 0x00b8 }
        r5 = r5.length;	 Catch:{ Exception -> 0x00b8 }
        if (r5 != 0) goto L_0x00b8;	 Catch:{ Exception -> 0x00b8 }
    L_0x0076:
        r5 = r6.length();	 Catch:{ Exception -> 0x00b8 }
        if (r5 != r3) goto L_0x0081;	 Catch:{ Exception -> 0x00b8 }
    L_0x007c:
        r6 = r6.toLowerCase();	 Catch:{ Exception -> 0x00b8 }
        goto L_0x00a6;	 Catch:{ Exception -> 0x00b8 }
    L_0x0081:
        r5 = r6.charAt(r3);	 Catch:{ Exception -> 0x00b8 }
        r5 = java.lang.Character.isUpperCase(r5);	 Catch:{ Exception -> 0x00b8 }
        if (r5 != 0) goto L_0x00a6;	 Catch:{ Exception -> 0x00b8 }
    L_0x008b:
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00b8 }
        r5.<init>();	 Catch:{ Exception -> 0x00b8 }
        r7 = r6.substring(r2, r3);	 Catch:{ Exception -> 0x00b8 }
        r7 = r7.toLowerCase();	 Catch:{ Exception -> 0x00b8 }
        r5.append(r7);	 Catch:{ Exception -> 0x00b8 }
        r6 = r6.substring(r3);	 Catch:{ Exception -> 0x00b8 }
        r5.append(r6);	 Catch:{ Exception -> 0x00b8 }
        r6 = r5.toString();	 Catch:{ Exception -> 0x00b8 }
    L_0x00a6:
        r5 = 0;	 Catch:{ Exception -> 0x00b8 }
        r5 = (java.lang.Object[]) r5;	 Catch:{ Exception -> 0x00b8 }
        r4 = r4.invoke(r9, r5);	 Catch:{ Exception -> 0x00b8 }
        if (r4 == 0) goto L_0x00b8;	 Catch:{ Exception -> 0x00b8 }
    L_0x00af:
        r5 = r8.map;	 Catch:{ Exception -> 0x00b8 }
        r4 = wrap(r4);	 Catch:{ Exception -> 0x00b8 }
        r5.put(r6, r4);	 Catch:{ Exception -> 0x00b8 }
    L_0x00b8:
        r1 = r1 + 1;
        goto L_0x001b;
    L_0x00bc:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONObject.populateMap(java.lang.Object):void");
    }

    public JSONObject put(String str, boolean z) throws JSONException {
        put(str, z ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }

    public JSONObject put(String str, Collection<Object> collection) throws JSONException {
        put(str, new JSONArray((Collection) collection));
        return this;
    }

    public JSONObject put(String str, double d) throws JSONException {
        put(str, new Double(d));
        return this;
    }

    public JSONObject put(String str, int i) throws JSONException {
        put(str, new Integer(i));
        return this;
    }

    public JSONObject put(String str, long j) throws JSONException {
        put(str, new Long(j));
        return this;
    }

    public JSONObject put(String str, Map<String, Object> map) throws JSONException {
        put(str, new JSONObject((Map) map));
        return this;
    }

    public JSONObject put(String str, Object obj) throws JSONException {
        if (str != null) {
            if (obj != null) {
                testValidity(obj);
                this.map.put(str, obj);
            } else {
                remove(str);
            }
            return this;
        }
        throw new NullPointerException("Null key.");
    }

    public JSONObject putOnce(String str, Object obj) throws JSONException {
        if (!(str == null || obj == null)) {
            if (opt(str) == null) {
                put(str, obj);
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Duplicate key \"");
                stringBuilder.append(str);
                stringBuilder.append("\"");
                throw new JSONException(stringBuilder.toString());
            }
        }
        return this;
    }

    public JSONObject putOpt(String str, Object obj) throws JSONException {
        if (!(str == null || obj == null)) {
            put(str, obj);
        }
        return this;
    }

    public static java.lang.String quote(java.lang.String r2) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = new java.io.StringWriter;
        r0.<init>();
        r1 = r0.getBuffer();
        monitor-enter(r1);
        r2 = quote(r2, r0);	 Catch:{ IOException -> 0x0016 }
        r2 = r2.toString();	 Catch:{ IOException -> 0x0016 }
        monitor-exit(r1);	 Catch:{ all -> 0x0014 }
        return r2;	 Catch:{ all -> 0x0014 }
    L_0x0014:
        r2 = move-exception;	 Catch:{ all -> 0x0014 }
        goto L_0x001a;	 Catch:{ all -> 0x0014 }
    L_0x0016:
        r2 = "";	 Catch:{ all -> 0x0014 }
        monitor-exit(r1);	 Catch:{ all -> 0x0014 }
        return r2;	 Catch:{ all -> 0x0014 }
    L_0x001a:
        monitor-exit(r1);	 Catch:{ all -> 0x0014 }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONObject.quote(java.lang.String):java.lang.String");
    }

    public static Writer quote(String str, Writer writer) throws IOException {
        if (str != null) {
            if (str.length() != 0) {
                int length = str.length();
                writer.write(34);
                int i = 0;
                char c = '\u0000';
                while (i < length) {
                    char charAt = str.charAt(i);
                    if (charAt != Typography.quote) {
                        if (charAt != '/') {
                            if (charAt != '\\') {
                                switch (charAt) {
                                    case '\b':
                                        writer.write("\\b");
                                        break;
                                    case '\t':
                                        writer.write("\\t");
                                        break;
                                    case '\n':
                                        writer.write("\\n");
                                        break;
                                    default:
                                        switch (charAt) {
                                            case '\f':
                                                writer.write("\\f");
                                                break;
                                            case '\r':
                                                writer.write("\\r");
                                                break;
                                            default:
                                                if (charAt >= ' ' && (charAt < '' || charAt >= Typography.nbsp)) {
                                                    if (charAt < ' ' || charAt >= '℀') {
                                                        writer.write(charAt);
                                                        break;
                                                    }
                                                }
                                                writer.write("\\u");
                                                String toHexString = Integer.toHexString(charAt);
                                                writer.write("0000", 0, 4 - toHexString.length());
                                                writer.write(toHexString);
                                                break;
                                        }
                                }
                            }
                        }
                        if (c == '<') {
                            writer.write(92);
                        }
                        writer.write(charAt);
                        i++;
                        c = charAt;
                    }
                    writer.write(92);
                    writer.write(charAt);
                    i++;
                    c = charAt;
                }
                writer.write(34);
                return writer;
            }
        }
        writer.write("\"\"");
        return writer;
    }

    public Object remove(String str) {
        return this.map.remove(str);
    }

    public boolean similar(java.lang.Object r6) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r5 = this;
        r0 = 0;
        r1 = r6 instanceof org.json.JSONObject;	 Catch:{ Throwable -> 0x0056 }
        if (r1 != 0) goto L_0x0006;	 Catch:{ Throwable -> 0x0056 }
    L_0x0005:
        return r0;	 Catch:{ Throwable -> 0x0056 }
    L_0x0006:
        r1 = r5.keySet();	 Catch:{ Throwable -> 0x0056 }
        r2 = r6;	 Catch:{ Throwable -> 0x0056 }
        r2 = (org.json.JSONObject) r2;	 Catch:{ Throwable -> 0x0056 }
        r2 = r2.keySet();	 Catch:{ Throwable -> 0x0056 }
        r2 = r1.equals(r2);	 Catch:{ Throwable -> 0x0056 }
        if (r2 != 0) goto L_0x0018;	 Catch:{ Throwable -> 0x0056 }
    L_0x0017:
        return r0;	 Catch:{ Throwable -> 0x0056 }
    L_0x0018:
        r1 = r1.iterator();	 Catch:{ Throwable -> 0x0056 }
    L_0x001c:
        r2 = r1.hasNext();	 Catch:{ Throwable -> 0x0056 }
        if (r2 == 0) goto L_0x0054;	 Catch:{ Throwable -> 0x0056 }
    L_0x0022:
        r2 = r1.next();	 Catch:{ Throwable -> 0x0056 }
        r2 = (java.lang.String) r2;	 Catch:{ Throwable -> 0x0056 }
        r3 = r5.get(r2);	 Catch:{ Throwable -> 0x0056 }
        r4 = r6;	 Catch:{ Throwable -> 0x0056 }
        r4 = (org.json.JSONObject) r4;	 Catch:{ Throwable -> 0x0056 }
        r2 = r4.get(r2);	 Catch:{ Throwable -> 0x0056 }
        r4 = r3 instanceof org.json.JSONObject;	 Catch:{ Throwable -> 0x0056 }
        if (r4 == 0) goto L_0x0040;	 Catch:{ Throwable -> 0x0056 }
    L_0x0037:
        r3 = (org.json.JSONObject) r3;	 Catch:{ Throwable -> 0x0056 }
        r2 = r3.similar(r2);	 Catch:{ Throwable -> 0x0056 }
        if (r2 != 0) goto L_0x001c;	 Catch:{ Throwable -> 0x0056 }
    L_0x003f:
        return r0;	 Catch:{ Throwable -> 0x0056 }
    L_0x0040:
        r4 = r3 instanceof org.json.JSONArray;	 Catch:{ Throwable -> 0x0056 }
        if (r4 == 0) goto L_0x004d;	 Catch:{ Throwable -> 0x0056 }
    L_0x0044:
        r3 = (org.json.JSONArray) r3;	 Catch:{ Throwable -> 0x0056 }
        r2 = r3.similar(r2);	 Catch:{ Throwable -> 0x0056 }
        if (r2 != 0) goto L_0x001c;	 Catch:{ Throwable -> 0x0056 }
    L_0x004c:
        return r0;	 Catch:{ Throwable -> 0x0056 }
    L_0x004d:
        r2 = r3.equals(r2);	 Catch:{ Throwable -> 0x0056 }
        if (r2 != 0) goto L_0x001c;
    L_0x0053:
        return r0;
    L_0x0054:
        r6 = 1;
        return r6;
    L_0x0056:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONObject.similar(java.lang.Object):boolean");
    }

    public static java.lang.Object stringToValue(java.lang.String r5) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = "";
        r0 = r5.equals(r0);
        if (r0 == 0) goto L_0x0009;
    L_0x0008:
        return r5;
    L_0x0009:
        r0 = "true";
        r0 = r5.equalsIgnoreCase(r0);
        if (r0 == 0) goto L_0x0014;
    L_0x0011:
        r5 = java.lang.Boolean.TRUE;
        return r5;
    L_0x0014:
        r0 = "false";
        r0 = r5.equalsIgnoreCase(r0);
        if (r0 == 0) goto L_0x001f;
    L_0x001c:
        r5 = java.lang.Boolean.FALSE;
        return r5;
    L_0x001f:
        r0 = "null";
        r0 = r5.equalsIgnoreCase(r0);
        if (r0 == 0) goto L_0x002a;
    L_0x0027:
        r5 = NULL;
        return r5;
    L_0x002a:
        r0 = 0;
        r0 = r5.charAt(r0);
        r1 = 48;
        if (r0 < r1) goto L_0x0037;
    L_0x0033:
        r1 = 57;
        if (r0 <= r1) goto L_0x003b;
    L_0x0037:
        r1 = 45;
        if (r0 != r1) goto L_0x008c;
    L_0x003b:
        r0 = 46;
        r0 = r5.indexOf(r0);	 Catch:{ Exception -> 0x008c }
        r1 = -1;	 Catch:{ Exception -> 0x008c }
        if (r0 > r1) goto L_0x007b;	 Catch:{ Exception -> 0x008c }
    L_0x0044:
        r0 = 101; // 0x65 float:1.42E-43 double:5.0E-322;	 Catch:{ Exception -> 0x008c }
        r0 = r5.indexOf(r0);	 Catch:{ Exception -> 0x008c }
        if (r0 > r1) goto L_0x007b;	 Catch:{ Exception -> 0x008c }
    L_0x004c:
        r0 = 69;	 Catch:{ Exception -> 0x008c }
        r0 = r5.indexOf(r0);	 Catch:{ Exception -> 0x008c }
        if (r0 <= r1) goto L_0x0055;	 Catch:{ Exception -> 0x008c }
    L_0x0054:
        goto L_0x007b;	 Catch:{ Exception -> 0x008c }
    L_0x0055:
        r0 = new java.lang.Long;	 Catch:{ Exception -> 0x008c }
        r0.<init>(r5);	 Catch:{ Exception -> 0x008c }
        r1 = r0.toString();	 Catch:{ Exception -> 0x008c }
        r1 = r5.equals(r1);	 Catch:{ Exception -> 0x008c }
        if (r1 == 0) goto L_0x008c;	 Catch:{ Exception -> 0x008c }
    L_0x0064:
        r1 = r0.longValue();	 Catch:{ Exception -> 0x008c }
        r3 = r0.intValue();	 Catch:{ Exception -> 0x008c }
        r3 = (long) r3;	 Catch:{ Exception -> 0x008c }
        r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));	 Catch:{ Exception -> 0x008c }
        if (r1 != 0) goto L_0x007a;	 Catch:{ Exception -> 0x008c }
    L_0x0071:
        r0 = r0.intValue();	 Catch:{ Exception -> 0x008c }
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Exception -> 0x008c }
        return r0;	 Catch:{ Exception -> 0x008c }
    L_0x007a:
        return r0;	 Catch:{ Exception -> 0x008c }
    L_0x007b:
        r0 = java.lang.Double.valueOf(r5);	 Catch:{ Exception -> 0x008c }
        r1 = r0.isInfinite();	 Catch:{ Exception -> 0x008c }
        if (r1 != 0) goto L_0x008c;	 Catch:{ Exception -> 0x008c }
    L_0x0085:
        r1 = r0.isNaN();	 Catch:{ Exception -> 0x008c }
        if (r1 != 0) goto L_0x008c;
    L_0x008b:
        return r0;
    L_0x008c:
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONObject.stringToValue(java.lang.String):java.lang.Object");
    }

    public static void testValidity(Object obj) throws JSONException {
        if (obj == null) {
            return;
        }
        if (obj instanceof Double) {
            Double d = (Double) obj;
            if (d.isInfinite() || d.isNaN() != null) {
                throw new JSONException("JSON does not allow non-finite numbers.");
            }
        } else if (obj instanceof Float) {
            Float f = (Float) obj;
            if (f.isInfinite() || f.isNaN() != null) {
                throw new JSONException("JSON does not allow non-finite numbers.");
            }
        }
    }

    public JSONArray toJSONArray(JSONArray jSONArray) throws JSONException {
        if (jSONArray != null) {
            if (jSONArray.length() != 0) {
                JSONArray jSONArray2 = new JSONArray();
                for (int i = 0; i < jSONArray.length(); i++) {
                    jSONArray2.put(opt(jSONArray.getString(i)));
                }
                return jSONArray2;
            }
        }
        return null;
    }

    public java.lang.String toString() {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r1 = this;
        r0 = 0;
        r0 = r1.toString(r0);	 Catch:{ Exception -> 0x0006 }
        return r0;
    L_0x0006:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONObject.toString():java.lang.String");
    }

    public String toString(int i) throws JSONException {
        Writer stringWriter = new StringWriter();
        synchronized (stringWriter.getBuffer()) {
            i = write(stringWriter, i, 0).toString();
        }
        return i;
    }

    public static String valueToString(Object obj) throws JSONException {
        if (obj != null) {
            if (!obj.equals(null)) {
                if (obj instanceof JSONString) {
                    try {
                        obj = ((JSONString) obj).toJSONString();
                        if (obj instanceof String) {
                            return (String) obj;
                        }
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Bad value from toJSONString: ");
                        stringBuilder.append(obj);
                        throw new JSONException(stringBuilder.toString());
                    } catch (Throwable e) {
                        throw new JSONException(e);
                    }
                } else if (obj instanceof Number) {
                    return numberToString((Number) obj);
                } else {
                    if (!((obj instanceof Boolean) || (obj instanceof JSONObject))) {
                        if (!(obj instanceof JSONArray)) {
                            if (obj instanceof Map) {
                                return new JSONObject((Map) obj).toString();
                            }
                            if (obj instanceof Collection) {
                                return new JSONArray((Collection) obj).toString();
                            }
                            if (obj.getClass().isArray()) {
                                return new JSONArray(obj).toString();
                            }
                            return quote(obj.toString());
                        }
                    }
                    return obj.toString();
                }
            }
        }
        return "null";
    }

    public static java.lang.Object wrap(java.lang.Object r2) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        if (r2 != 0) goto L_0x0005;
    L_0x0002:
        r2 = NULL;	 Catch:{ Exception -> 0x00a6 }
        return r2;	 Catch:{ Exception -> 0x00a6 }
    L_0x0005:
        r0 = r2 instanceof org.json.JSONObject;	 Catch:{ Exception -> 0x00a6 }
        if (r0 != 0) goto L_0x00a5;	 Catch:{ Exception -> 0x00a6 }
    L_0x0009:
        r0 = r2 instanceof org.json.JSONArray;	 Catch:{ Exception -> 0x00a6 }
        if (r0 != 0) goto L_0x00a5;	 Catch:{ Exception -> 0x00a6 }
    L_0x000d:
        r0 = NULL;	 Catch:{ Exception -> 0x00a6 }
        r0 = r0.equals(r2);	 Catch:{ Exception -> 0x00a6 }
        if (r0 != 0) goto L_0x00a5;	 Catch:{ Exception -> 0x00a6 }
    L_0x0015:
        r0 = r2 instanceof org.json.JSONString;	 Catch:{ Exception -> 0x00a6 }
        if (r0 != 0) goto L_0x00a5;	 Catch:{ Exception -> 0x00a6 }
    L_0x0019:
        r0 = r2 instanceof java.lang.Byte;	 Catch:{ Exception -> 0x00a6 }
        if (r0 != 0) goto L_0x00a5;	 Catch:{ Exception -> 0x00a6 }
    L_0x001d:
        r0 = r2 instanceof java.lang.Character;	 Catch:{ Exception -> 0x00a6 }
        if (r0 != 0) goto L_0x00a5;	 Catch:{ Exception -> 0x00a6 }
    L_0x0021:
        r0 = r2 instanceof java.lang.Short;	 Catch:{ Exception -> 0x00a6 }
        if (r0 != 0) goto L_0x00a5;	 Catch:{ Exception -> 0x00a6 }
    L_0x0025:
        r0 = r2 instanceof java.lang.Integer;	 Catch:{ Exception -> 0x00a6 }
        if (r0 != 0) goto L_0x00a5;	 Catch:{ Exception -> 0x00a6 }
    L_0x0029:
        r0 = r2 instanceof java.lang.Long;	 Catch:{ Exception -> 0x00a6 }
        if (r0 != 0) goto L_0x00a5;	 Catch:{ Exception -> 0x00a6 }
    L_0x002d:
        r0 = r2 instanceof java.lang.Boolean;	 Catch:{ Exception -> 0x00a6 }
        if (r0 != 0) goto L_0x00a5;	 Catch:{ Exception -> 0x00a6 }
    L_0x0031:
        r0 = r2 instanceof java.lang.Float;	 Catch:{ Exception -> 0x00a6 }
        if (r0 != 0) goto L_0x00a5;	 Catch:{ Exception -> 0x00a6 }
    L_0x0035:
        r0 = r2 instanceof java.lang.Double;	 Catch:{ Exception -> 0x00a6 }
        if (r0 != 0) goto L_0x00a5;	 Catch:{ Exception -> 0x00a6 }
    L_0x0039:
        r0 = r2 instanceof java.lang.String;	 Catch:{ Exception -> 0x00a6 }
        if (r0 != 0) goto L_0x00a5;	 Catch:{ Exception -> 0x00a6 }
    L_0x003d:
        r0 = r2 instanceof java.math.BigInteger;	 Catch:{ Exception -> 0x00a6 }
        if (r0 != 0) goto L_0x00a5;	 Catch:{ Exception -> 0x00a6 }
    L_0x0041:
        r0 = r2 instanceof java.math.BigDecimal;	 Catch:{ Exception -> 0x00a6 }
        if (r0 == 0) goto L_0x0046;	 Catch:{ Exception -> 0x00a6 }
    L_0x0045:
        goto L_0x00a5;	 Catch:{ Exception -> 0x00a6 }
    L_0x0046:
        r0 = r2 instanceof java.util.Collection;	 Catch:{ Exception -> 0x00a6 }
        if (r0 == 0) goto L_0x0052;	 Catch:{ Exception -> 0x00a6 }
    L_0x004a:
        r2 = (java.util.Collection) r2;	 Catch:{ Exception -> 0x00a6 }
        r0 = new org.json.JSONArray;	 Catch:{ Exception -> 0x00a6 }
        r0.<init>(r2);	 Catch:{ Exception -> 0x00a6 }
        return r0;	 Catch:{ Exception -> 0x00a6 }
    L_0x0052:
        r0 = r2.getClass();	 Catch:{ Exception -> 0x00a6 }
        r0 = r0.isArray();	 Catch:{ Exception -> 0x00a6 }
        if (r0 == 0) goto L_0x0062;	 Catch:{ Exception -> 0x00a6 }
    L_0x005c:
        r0 = new org.json.JSONArray;	 Catch:{ Exception -> 0x00a6 }
        r0.<init>(r2);	 Catch:{ Exception -> 0x00a6 }
        return r0;	 Catch:{ Exception -> 0x00a6 }
    L_0x0062:
        r0 = r2 instanceof java.util.Map;	 Catch:{ Exception -> 0x00a6 }
        if (r0 == 0) goto L_0x006e;	 Catch:{ Exception -> 0x00a6 }
    L_0x0066:
        r2 = (java.util.Map) r2;	 Catch:{ Exception -> 0x00a6 }
        r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x00a6 }
        r0.<init>(r2);	 Catch:{ Exception -> 0x00a6 }
        return r0;	 Catch:{ Exception -> 0x00a6 }
    L_0x006e:
        r0 = r2.getClass();	 Catch:{ Exception -> 0x00a6 }
        r0 = r0.getPackage();	 Catch:{ Exception -> 0x00a6 }
        if (r0 == 0) goto L_0x007d;	 Catch:{ Exception -> 0x00a6 }
    L_0x0078:
        r0 = r0.getName();	 Catch:{ Exception -> 0x00a6 }
        goto L_0x007f;	 Catch:{ Exception -> 0x00a6 }
    L_0x007d:
        r0 = "";	 Catch:{ Exception -> 0x00a6 }
    L_0x007f:
        r1 = "java.";	 Catch:{ Exception -> 0x00a6 }
        r1 = r0.startsWith(r1);	 Catch:{ Exception -> 0x00a6 }
        if (r1 != 0) goto L_0x00a0;	 Catch:{ Exception -> 0x00a6 }
    L_0x0087:
        r1 = "javax.";	 Catch:{ Exception -> 0x00a6 }
        r0 = r0.startsWith(r1);	 Catch:{ Exception -> 0x00a6 }
        if (r0 != 0) goto L_0x00a0;	 Catch:{ Exception -> 0x00a6 }
    L_0x008f:
        r0 = r2.getClass();	 Catch:{ Exception -> 0x00a6 }
        r0 = r0.getClassLoader();	 Catch:{ Exception -> 0x00a6 }
        if (r0 != 0) goto L_0x009a;	 Catch:{ Exception -> 0x00a6 }
    L_0x0099:
        goto L_0x00a0;	 Catch:{ Exception -> 0x00a6 }
    L_0x009a:
        r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x00a6 }
        r0.<init>(r2);	 Catch:{ Exception -> 0x00a6 }
        return r0;	 Catch:{ Exception -> 0x00a6 }
    L_0x00a0:
        r2 = r2.toString();	 Catch:{ Exception -> 0x00a6 }
        return r2;
    L_0x00a5:
        return r2;
    L_0x00a6:
        r2 = 0;
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONObject.wrap(java.lang.Object):java.lang.Object");
    }

    public Writer write(Writer writer) throws JSONException {
        return write(writer, 0, 0);
    }

    static final Writer writeValue(Writer writer, Object obj, int i, int i2) throws JSONException, IOException {
        if (obj != null) {
            if (!obj.equals(null)) {
                if (obj instanceof JSONObject) {
                    ((JSONObject) obj).write(writer, i, i2);
                } else if (obj instanceof JSONArray) {
                    ((JSONArray) obj).write(writer, i, i2);
                } else if (obj instanceof Map) {
                    new JSONObject((Map) obj).write(writer, i, i2);
                } else if (obj instanceof Collection) {
                    new JSONArray((Collection) obj).write(writer, i, i2);
                } else if (obj.getClass().isArray()) {
                    new JSONArray(obj).write(writer, i, i2);
                } else if ((obj instanceof Number) != 0) {
                    writer.write(numberToString((Number) obj));
                } else if ((obj instanceof Boolean) != 0) {
                    writer.write(obj.toString());
                } else if ((obj instanceof JSONString) != 0) {
                    try {
                        i = ((JSONString) obj).toJSONString();
                        writer.write(i != 0 ? i.toString() : quote(obj.toString()));
                    } catch (Throwable e) {
                        throw new JSONException(e);
                    }
                } else {
                    quote(obj.toString(), writer);
                }
                return writer;
            }
        }
        writer.write("null");
        return writer;
    }

    static final void indent(Writer writer, int i) throws IOException {
        for (int i2 = 0; i2 < i; i2++) {
            writer.write(32);
        }
    }

    Writer write(Writer writer, int i, int i2) throws JSONException {
        Object obj = null;
        try {
            int length = length();
            Iterator keys = keys();
            writer.write(123);
            if (length == 1) {
                obj = keys.next();
                writer.write(quote(obj.toString()));
                writer.write(58);
                if (i > 0) {
                    writer.write(32);
                }
                writeValue(writer, this.map.get(obj), i, i2);
            } else if (length != 0) {
                length = i2 + i;
                while (keys.hasNext()) {
                    Object next = keys.next();
                    if (obj != null) {
                        writer.write(44);
                    }
                    if (i > 0) {
                        writer.write(10);
                    }
                    indent(writer, length);
                    writer.write(quote(next.toString()));
                    writer.write(58);
                    if (i > 0) {
                        writer.write(32);
                    }
                    writeValue(writer, this.map.get(next), i, length);
                    obj = 1;
                }
                if (i > 0) {
                    writer.write(10);
                }
                indent(writer, i2);
            }
            writer.write(125);
            return writer;
        } catch (Throwable e) {
            throw new JSONException(e);
        }
    }
}
