package org.json;

import java.util.HashMap;
import kotlin.text.Typography;

public class XMLTokener extends JSONTokener {
    public static final HashMap<String, Character> entity = new HashMap(8);

    static {
        entity.put("amp", XML.AMP);
        entity.put("apos", XML.APOS);
        entity.put("gt", XML.GT);
        entity.put("lt", XML.LT);
        entity.put("quot", XML.QUOT);
    }

    public XMLTokener(String str) {
        super(str);
    }

    public String nextCDATA() throws JSONException {
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            char next = next();
            if (end()) {
                throw syntaxError("Unclosed CDATA");
            }
            stringBuilder.append(next);
            int length = stringBuilder.length() - 3;
            if (length >= 0 && stringBuilder.charAt(length) == ']' && stringBuilder.charAt(length + 1) == ']' && stringBuilder.charAt(length + 2) == Typography.greater) {
                stringBuilder.setLength(length);
                return stringBuilder.toString();
            }
        }
    }

    public Object nextContent() throws JSONException {
        char next;
        do {
            next = next();
        } while (Character.isWhitespace(next));
        if (next == '\u0000') {
            return null;
        }
        if (next == Typography.less) {
            return XML.LT;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (next != Typography.less) {
            if (next == '\u0000') {
                break;
            }
            if (next == Typography.amp) {
                stringBuilder.append(nextEntity(next));
            } else {
                stringBuilder.append(next);
            }
            next = next();
        }
        back();
        return stringBuilder.toString().trim();
    }

    public Object nextEntity(char c) throws JSONException {
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            char next = next();
            if (!Character.isLetterOrDigit(next)) {
                if (next != '#') {
                    break;
                }
            }
            stringBuilder.append(Character.toLowerCase(next));
        }
        if (next == ';') {
            String stringBuilder2 = stringBuilder.toString();
            Object obj = entity.get(stringBuilder2);
            if (obj != null) {
                return obj;
            }
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(c);
            stringBuilder3.append(stringBuilder2);
            stringBuilder3.append(";");
            return stringBuilder3.toString();
        }
        c = new StringBuilder();
        c.append("Missing ';' in XML entity: &");
        c.append(stringBuilder);
        throw syntaxError(c.toString());
    }

    public Object nextMeta() throws JSONException {
        char next;
        do {
            next = next();
        } while (Character.isWhitespace(next));
        if (next != '\u0000') {
            if (next != '\'') {
                if (next == '/') {
                    return XML.SLASH;
                }
                switch (next) {
                    case '!':
                        return XML.BANG;
                    case '\"':
                        break;
                    default:
                        switch (next) {
                            case '<':
                                return XML.LT;
                            case '=':
                                return XML.EQ;
                            case '>':
                                return XML.GT;
                            case '?':
                                return XML.QUEST;
                        }
                        while (true) {
                            next = next();
                            if (Character.isWhitespace(next)) {
                                return Boolean.TRUE;
                            }
                            if (!(next == '\u0000' || next == '\'' || next == '/')) {
                                switch (next) {
                                    case '!':
                                    case '\"':
                                        break;
                                    default:
                                        switch (next) {
                                            case '<':
                                            case '=':
                                            case '>':
                                            case '?':
                                                break;
                                            default:
                                        }
                                }
                            }
                            back();
                            return Boolean.TRUE;
                        }
                }
            }
            char next2;
            do {
                next2 = next();
                if (next2 == '\u0000') {
                    throw syntaxError("Unterminated string");
                }
            } while (next2 != next);
            return Boolean.TRUE;
        }
        throw syntaxError("Misshaped meta tag");
    }

    public Object nextToken() throws JSONException {
        char next;
        do {
            next = next();
        } while (Character.isWhitespace(next));
        if (next != '\u0000') {
            if (next != '\'') {
                if (next == '/') {
                    return XML.SLASH;
                }
                switch (next) {
                    case '!':
                        return XML.BANG;
                    case '\"':
                        break;
                    default:
                        switch (next) {
                            case '<':
                                throw syntaxError("Misplaced '<'");
                            case '=':
                                return XML.EQ;
                            case '>':
                                return XML.GT;
                            case '?':
                                return XML.QUEST;
                            default:
                                StringBuilder stringBuilder = new StringBuilder();
                                while (true) {
                                    stringBuilder.append(next);
                                    next = next();
                                    if (Character.isWhitespace(next)) {
                                        return stringBuilder.toString();
                                    }
                                    if (next == '\u0000') {
                                        return stringBuilder.toString();
                                    }
                                    if (next != '\'') {
                                        if (!(next == '/' || next == '[' || next == ']')) {
                                            switch (next) {
                                                case '!':
                                                    break;
                                                case '\"':
                                                    break;
                                                default:
                                                    switch (next) {
                                                        case '<':
                                                            break;
                                                        case '=':
                                                        case '>':
                                                        case '?':
                                                            break;
                                                        default:
                                                    }
                                            }
                                        }
                                        back();
                                        return stringBuilder.toString();
                                    }
                                    throw syntaxError("Bad character in a name");
                                }
                        }
                }
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            while (true) {
                char next2 = next();
                if (next2 == '\u0000') {
                    throw syntaxError("Unterminated string");
                } else if (next2 == next) {
                    return stringBuilder2.toString();
                } else {
                    if (next2 == Typography.amp) {
                        stringBuilder2.append(nextEntity(next2));
                    } else {
                        stringBuilder2.append(next2);
                    }
                }
            }
        } else {
            throw syntaxError("Misshaped element");
        }
    }

    public boolean skipPast(String str) throws JSONException {
        int i;
        int length = str.length();
        char[] cArr = new char[length];
        for (i = 0; i < length; i++) {
            char next = next();
            if (next == '\u0000') {
                return false;
            }
            cArr[i] = next;
        }
        i = 0;
        while (true) {
            Object obj;
            int i2 = i;
            for (int i3 = 0; i3 < length; i3++) {
                if (cArr[i2] != str.charAt(i3)) {
                    obj = null;
                    break;
                }
                i2++;
                if (i2 >= length) {
                    i2 -= length;
                }
            }
            obj = 1;
            if (obj != null) {
                return true;
            }
            next = next();
            if (next == '\u0000') {
                return false;
            }
            cArr[i] = next;
            i++;
            if (i >= length) {
                i -= length;
            }
        }
    }
}
