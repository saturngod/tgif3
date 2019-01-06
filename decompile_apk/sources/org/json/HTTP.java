package org.json;

import java.util.Iterator;
import kotlin.text.Typography;

public class HTTP {
    public static final String CRLF = "\r\n";

    public static JSONObject toJSONObject(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        HTTPTokener hTTPTokener = new HTTPTokener(str);
        Object nextToken = hTTPTokener.nextToken();
        if (nextToken.toUpperCase().startsWith("HTTP")) {
            jSONObject.put("HTTP-Version", nextToken);
            jSONObject.put("Status-Code", hTTPTokener.nextToken());
            jSONObject.put("Reason-Phrase", hTTPTokener.nextTo('\u0000'));
            hTTPTokener.next();
        } else {
            jSONObject.put("Method", nextToken);
            jSONObject.put("Request-URI", hTTPTokener.nextToken());
            jSONObject.put("HTTP-Version", hTTPTokener.nextToken());
        }
        while (hTTPTokener.more() != null) {
            String nextTo = hTTPTokener.nextTo(':');
            hTTPTokener.next(':');
            jSONObject.put(nextTo, hTTPTokener.nextTo('\u0000'));
            hTTPTokener.next();
        }
        return jSONObject;
    }

    public static String toString(JSONObject jSONObject) throws JSONException {
        Iterator keys = jSONObject.keys();
        StringBuilder stringBuilder = new StringBuilder();
        if (jSONObject.has("Status-Code") && jSONObject.has("Reason-Phrase")) {
            stringBuilder.append(jSONObject.getString("HTTP-Version"));
            stringBuilder.append(' ');
            stringBuilder.append(jSONObject.getString("Status-Code"));
            stringBuilder.append(' ');
            stringBuilder.append(jSONObject.getString("Reason-Phrase"));
        } else if (jSONObject.has("Method") && jSONObject.has("Request-URI")) {
            stringBuilder.append(jSONObject.getString("Method"));
            stringBuilder.append(' ');
            stringBuilder.append(Typography.quote);
            stringBuilder.append(jSONObject.getString("Request-URI"));
            stringBuilder.append(Typography.quote);
            stringBuilder.append(' ');
            stringBuilder.append(jSONObject.getString("HTTP-Version"));
        } else {
            throw new JSONException("Not enough material for an HTTP header.");
        }
        stringBuilder.append(CRLF);
        while (keys.hasNext()) {
            String str = (String) keys.next();
            if (!("HTTP-Version".equals(str) || "Status-Code".equals(str) || "Reason-Phrase".equals(str) || "Method".equals(str) || "Request-URI".equals(str) || jSONObject.isNull(str))) {
                stringBuilder.append(str);
                stringBuilder.append(": ");
                stringBuilder.append(jSONObject.getString(str));
                stringBuilder.append(CRLF);
            }
        }
        stringBuilder.append(CRLF);
        return stringBuilder.toString();
    }
}
