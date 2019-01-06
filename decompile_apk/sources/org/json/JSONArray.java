package org.json;

import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class JSONArray implements Iterable<Object> {
    private final ArrayList<Object> myArrayList;

    public JSONArray() {
        this.myArrayList = new ArrayList();
    }

    public JSONArray(JSONTokener jSONTokener) throws JSONException {
        this();
        if (jSONTokener.nextClean() != '[') {
            throw jSONTokener.syntaxError("A JSONArray text must start with '['");
        } else if (jSONTokener.nextClean() != ']') {
            char nextClean;
            jSONTokener.back();
            while (true) {
                if (jSONTokener.nextClean() == ',') {
                    jSONTokener.back();
                    this.myArrayList.add(JSONObject.NULL);
                } else {
                    jSONTokener.back();
                    this.myArrayList.add(jSONTokener.nextValue());
                }
                nextClean = jSONTokener.nextClean();
                if (nextClean != ',') {
                    break;
                } else if (jSONTokener.nextClean() != ']') {
                    jSONTokener.back();
                } else {
                    return;
                }
            }
            if (nextClean != ']') {
                throw jSONTokener.syntaxError("Expected a ',' or ']'");
            }
        }
    }

    public JSONArray(String str) throws JSONException {
        this(new JSONTokener(str));
    }

    public JSONArray(Collection<Object> collection) {
        this.myArrayList = new ArrayList();
        if (collection != null) {
            for (Object wrap : collection) {
                this.myArrayList.add(JSONObject.wrap(wrap));
            }
        }
    }

    public JSONArray(Object obj) throws JSONException {
        this();
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                put(JSONObject.wrap(Array.get(obj, i)));
            }
            return;
        }
        throw new JSONException("JSONArray initial value should be a string or collection or array.");
    }

    public Iterator<Object> iterator() {
        return this.myArrayList.iterator();
    }

    public Object get(int i) throws JSONException {
        Object opt = opt(i);
        if (opt != null) {
            return opt;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("JSONArray[");
        stringBuilder.append(i);
        stringBuilder.append("] not found.");
        throw new JSONException(stringBuilder.toString());
    }

    public boolean getBoolean(int i) throws JSONException {
        Object obj = get(i);
        if (!obj.equals(Boolean.FALSE)) {
            boolean z = obj instanceof String;
            if (!z || !((String) obj).equalsIgnoreCase("false")) {
                if (!obj.equals(Boolean.TRUE)) {
                    if (!z || !((String) obj).equalsIgnoreCase("true")) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("JSONArray[");
                        stringBuilder.append(i);
                        stringBuilder.append("] is not a boolean.");
                        throw new JSONException(stringBuilder.toString());
                    }
                }
                return true;
            }
        }
        return false;
    }

    public double getDouble(int r4) throws org.json.JSONException {
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
        r2 = "JSONArray[";
        r1.append(r2);
        r1.append(r4);
        r4 = "] is not a number.";
        r1.append(r4);
        r4 = r1.toString();
        r0.<init>(r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONArray.getDouble(int):double");
    }

    public <E extends Enum<E>> E getEnum(Class<E> cls, int i) throws JSONException {
        E optEnum = optEnum(cls, i);
        if (optEnum != null) {
            return optEnum;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("JSONObject[");
        stringBuilder.append(JSONObject.quote(Integer.toString(i)));
        stringBuilder.append("] is not an enum of type ");
        stringBuilder.append(JSONObject.quote(cls.getSimpleName()));
        stringBuilder.append(".");
        throw new JSONException(stringBuilder.toString());
    }

    public java.math.BigDecimal getBigDecimal(int r4) throws org.json.JSONException {
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
        r2 = "JSONArray[";
        r1.append(r2);
        r1.append(r4);
        r4 = "] could not convert to BigDecimal.";
        r1.append(r4);
        r4 = r1.toString();
        r0.<init>(r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONArray.getBigDecimal(int):java.math.BigDecimal");
    }

    public java.math.BigInteger getBigInteger(int r4) throws org.json.JSONException {
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
        r2 = "JSONArray[";
        r1.append(r2);
        r1.append(r4);
        r4 = "] could not convert to BigInteger.";
        r1.append(r4);
        r4 = r1.toString();
        r0.<init>(r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONArray.getBigInteger(int):java.math.BigInteger");
    }

    public int getInt(int r4) throws org.json.JSONException {
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
        r2 = "JSONArray[";
        r1.append(r2);
        r1.append(r4);
        r4 = "] is not a number.";
        r1.append(r4);
        r4 = r1.toString();
        r0.<init>(r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONArray.getInt(int):int");
    }

    public JSONArray getJSONArray(int i) throws JSONException {
        Object obj = get(i);
        if (obj instanceof JSONArray) {
            return (JSONArray) obj;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("JSONArray[");
        stringBuilder.append(i);
        stringBuilder.append("] is not a JSONArray.");
        throw new JSONException(stringBuilder.toString());
    }

    public JSONObject getJSONObject(int i) throws JSONException {
        Object obj = get(i);
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("JSONArray[");
        stringBuilder.append(i);
        stringBuilder.append("] is not a JSONObject.");
        throw new JSONException(stringBuilder.toString());
    }

    public long getLong(int r4) throws org.json.JSONException {
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
        r2 = "JSONArray[";
        r1.append(r2);
        r1.append(r4);
        r4 = "] is not a number.";
        r1.append(r4);
        r4 = r1.toString();
        r0.<init>(r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONArray.getLong(int):long");
    }

    public String getString(int i) throws JSONException {
        Object obj = get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("JSONArray[");
        stringBuilder.append(i);
        stringBuilder.append("] not a string.");
        throw new JSONException(stringBuilder.toString());
    }

    public boolean isNull(int i) {
        return JSONObject.NULL.equals(opt(i));
    }

    public String join(String str) throws JSONException {
        int length = length();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                stringBuilder.append(str);
            }
            stringBuilder.append(JSONObject.valueToString(this.myArrayList.get(i)));
        }
        return stringBuilder.toString();
    }

    public int length() {
        return this.myArrayList.size();
    }

    public Object opt(int i) {
        if (i >= 0) {
            if (i < length()) {
                return this.myArrayList.get(i);
            }
        }
        return 0;
    }

    public boolean optBoolean(int i) {
        return optBoolean(i, false);
    }

    public boolean optBoolean(int r1, boolean r2) {
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
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONArray.optBoolean(int, boolean):boolean");
    }

    public double optDouble(int i) {
        return optDouble(i, Double.NaN);
    }

    public double optDouble(int r3, double r4) {
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
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONArray.optDouble(int, double):double");
    }

    public int optInt(int i) {
        return optInt(i, 0);
    }

    public int optInt(int r1, int r2) {
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
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONArray.optInt(int, int):int");
    }

    public <E extends Enum<E>> E optEnum(Class<E> cls, int i) {
        return optEnum(cls, i, null);
    }

    public <E extends java.lang.Enum<E>> E optEnum(java.lang.Class<E> r2, int r3, E r4) {
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
        r0 = org.json.JSONObject.NULL;	 Catch:{ IllegalArgumentException -> 0x0023, IllegalArgumentException -> 0x0023 }
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
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONArray.optEnum(java.lang.Class, int, java.lang.Enum):E");
    }

    public java.math.BigInteger optBigInteger(int r1, java.math.BigInteger r2) {
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
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONArray.optBigInteger(int, java.math.BigInteger):java.math.BigInteger");
    }

    public java.math.BigDecimal optBigDecimal(int r1, java.math.BigDecimal r2) {
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
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONArray.optBigDecimal(int, java.math.BigDecimal):java.math.BigDecimal");
    }

    public JSONArray optJSONArray(int i) {
        i = opt(i);
        return i instanceof JSONArray ? (JSONArray) i : 0;
    }

    public JSONObject optJSONObject(int i) {
        i = opt(i);
        return i instanceof JSONObject ? (JSONObject) i : 0;
    }

    public long optLong(int i) {
        return optLong(i, 0);
    }

    public long optLong(int r3, long r4) {
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
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONArray.optLong(int, long):long");
    }

    public String optString(int i) {
        return optString(i, "");
    }

    public String optString(int i, String str) {
        i = opt(i);
        return JSONObject.NULL.equals(i) ? str : i.toString();
    }

    public JSONArray put(boolean z) {
        put(z ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }

    public JSONArray put(Collection<Object> collection) {
        put(new JSONArray((Collection) collection));
        return this;
    }

    public JSONArray put(double d) throws JSONException {
        Object d2 = new Double(d);
        JSONObject.testValidity(d2);
        put(d2);
        return this;
    }

    public JSONArray put(int i) {
        put(new Integer(i));
        return this;
    }

    public JSONArray put(long j) {
        put(new Long(j));
        return this;
    }

    public JSONArray put(Map<String, Object> map) {
        put(new JSONObject((Map) map));
        return this;
    }

    public JSONArray put(Object obj) {
        this.myArrayList.add(obj);
        return this;
    }

    public JSONArray put(int i, boolean z) throws JSONException {
        put(i, z ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }

    public JSONArray put(int i, Collection<Object> collection) throws JSONException {
        put(i, new JSONArray((Collection) collection));
        return this;
    }

    public JSONArray put(int i, double d) throws JSONException {
        put(i, new Double(d));
        return this;
    }

    public JSONArray put(int i, int i2) throws JSONException {
        put(i, new Integer(i2));
        return this;
    }

    public JSONArray put(int i, long j) throws JSONException {
        put(i, new Long(j));
        return this;
    }

    public JSONArray put(int i, Map<String, Object> map) throws JSONException {
        put(i, new JSONObject((Map) map));
        return this;
    }

    public JSONArray put(int i, Object obj) throws JSONException {
        JSONObject.testValidity(obj);
        if (i >= 0) {
            if (i < length()) {
                this.myArrayList.set(i, obj);
            } else {
                while (i != length()) {
                    put(JSONObject.NULL);
                }
                put(obj);
            }
            return this;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("JSONArray[");
        stringBuilder.append(i);
        stringBuilder.append("] not found.");
        throw new JSONException(stringBuilder.toString());
    }

    public Object remove(int i) {
        return (i < 0 || i >= length()) ? 0 : this.myArrayList.remove(i);
    }

    public boolean similar(Object obj) {
        if (!(obj instanceof JSONArray)) {
            return false;
        }
        int length = length();
        JSONArray jSONArray = (JSONArray) obj;
        if (length != jSONArray.length()) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            Object obj2 = get(i);
            Object obj3 = jSONArray.get(i);
            if (obj2 instanceof JSONObject) {
                if (!((JSONObject) obj2).similar(obj3)) {
                    return false;
                }
            } else if (obj2 instanceof JSONArray) {
                if (!((JSONArray) obj2).similar(obj3)) {
                    return false;
                }
            } else if (!obj2.equals(obj3)) {
                return false;
            }
        }
        return true;
    }

    public JSONObject toJSONObject(JSONArray jSONArray) throws JSONException {
        if (!(jSONArray == null || jSONArray.length() == 0)) {
            if (length() != 0) {
                JSONObject jSONObject = new JSONObject();
                for (int i = 0; i < jSONArray.length(); i++) {
                    jSONObject.put(jSONArray.getString(i), opt(i));
                }
                return jSONObject;
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
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONArray.toString():java.lang.String");
    }

    public String toString(int i) throws JSONException {
        Writer stringWriter = new StringWriter();
        synchronized (stringWriter.getBuffer()) {
            i = write(stringWriter, i, 0).toString();
        }
        return i;
    }

    public Writer write(Writer writer) throws JSONException {
        return write(writer, 0, 0);
    }

    Writer write(Writer writer, int i, int i2) throws JSONException {
        try {
            int length = length();
            writer.write(91);
            int i3 = 0;
            if (length == 1) {
                JSONObject.writeValue(writer, this.myArrayList.get(0), i, i2);
            } else if (length != 0) {
                int i4 = i2 + i;
                Object obj = null;
                while (i3 < length) {
                    if (obj != null) {
                        writer.write(44);
                    }
                    if (i > 0) {
                        writer.write(10);
                    }
                    JSONObject.indent(writer, i4);
                    JSONObject.writeValue(writer, this.myArrayList.get(i3), i, i4);
                    i3++;
                    obj = 1;
                }
                if (i > 0) {
                    writer.write(10);
                }
                JSONObject.indent(writer, i2);
            }
            writer.write(93);
            return writer;
        } catch (Throwable e) {
            throw new JSONException(e);
        }
    }
}
