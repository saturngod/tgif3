package org.json;

import kotlin.text.Typography;

public class HTTPTokener extends JSONTokener {
    public HTTPTokener(String str) {
        super(str);
    }

    public String nextToken() throws JSONException {
        char next;
        StringBuilder stringBuilder = new StringBuilder();
        do {
            next = next();
        } while (Character.isWhitespace(next));
        if (next != Typography.quote) {
            if (next != '\'') {
                while (next != '\u0000') {
                    if (Character.isWhitespace(next)) {
                        break;
                    }
                    stringBuilder.append(next);
                    next = next();
                }
                return stringBuilder.toString();
            }
        }
        while (true) {
            char next2 = next();
            if (next2 < ' ') {
                throw syntaxError("Unterminated string.");
            } else if (next2 == next) {
                return stringBuilder.toString();
            } else {
                stringBuilder.append(next2);
            }
        }
    }
}
