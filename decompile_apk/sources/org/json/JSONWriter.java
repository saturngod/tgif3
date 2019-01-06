package org.json;

import java.io.Writer;

public class JSONWriter {
    private static final int maxdepth = 200;
    private boolean comma = false;
    protected char mode = 'i';
    private final JSONObject[] stack = new JSONObject[maxdepth];
    private int top = 0;
    protected Writer writer;

    public JSONWriter(Writer writer) {
        this.writer = writer;
    }

    private JSONWriter append(String str) throws JSONException {
        if (str != null) {
            if (this.mode != 'o') {
                if (this.mode != 'a') {
                    throw new JSONException("Value out of sequence.");
                }
            }
            try {
                if (this.comma && this.mode == 'a') {
                    this.writer.write(44);
                }
                this.writer.write(str);
                if (this.mode == 111) {
                    this.mode = 'k';
                }
                this.comma = true;
                return this;
            } catch (Throwable e) {
                throw new JSONException(e);
            }
        }
        throw new JSONException("Null pointer");
    }

    public JSONWriter array() throws JSONException {
        if (!(this.mode == 'i' || this.mode == 'o')) {
            if (this.mode != 'a') {
                throw new JSONException("Misplaced array.");
            }
        }
        push(null);
        append("[");
        this.comma = false;
        return this;
    }

    private JSONWriter end(char c, char c2) throws JSONException {
        if (this.mode != c) {
            throw new JSONException(c == 'a' ? "Misplaced endArray." : "Misplaced endObject.");
        }
        pop(c);
        try {
            this.writer.write(c2);
            this.comma = true;
            return this;
        } catch (Throwable e) {
            throw new JSONException(e);
        }
    }

    public JSONWriter endArray() throws JSONException {
        return end('a', ']');
    }

    public JSONWriter endObject() throws JSONException {
        return end('k', '}');
    }

    public JSONWriter key(String str) throws JSONException {
        if (str == null) {
            throw new JSONException("Null key.");
        } else if (this.mode == 'k') {
            try {
                this.stack[this.top - 1].putOnce(str, Boolean.TRUE);
                if (this.comma) {
                    this.writer.write(44);
                }
                this.writer.write(JSONObject.quote(str));
                this.writer.write(58);
                this.comma = null;
                this.mode = 'o';
                return this;
            } catch (Throwable e) {
                throw new JSONException(e);
            }
        } else {
            throw new JSONException("Misplaced key.");
        }
    }

    public JSONWriter object() throws JSONException {
        if (this.mode == 'i') {
            this.mode = 'o';
        }
        if (this.mode != 'o') {
            if (this.mode != 'a') {
                throw new JSONException("Misplaced object.");
            }
        }
        append("{");
        push(new JSONObject());
        this.comma = false;
        return this;
    }

    private void pop(char c) throws JSONException {
        if (this.top > 0) {
            char c2 = 'k';
            if ((this.stack[this.top + -1] == null ? 'a' : 'k') == c) {
                this.top--;
                if (this.top == '\u0000') {
                    c2 = 'd';
                } else if (this.stack[this.top - 1] == '\u0000') {
                    c2 = 'a';
                }
                this.mode = c2;
                return;
            }
            throw new JSONException("Nesting error.");
        }
        throw new JSONException("Nesting error.");
    }

    private void push(JSONObject jSONObject) throws JSONException {
        if (this.top < maxdepth) {
            this.stack[this.top] = jSONObject;
            this.mode = jSONObject == null ? 97 : 107;
            this.top++;
            return;
        }
        throw new JSONException("Nesting too deep.");
    }

    public JSONWriter value(boolean z) throws JSONException {
        return append(z ? "true" : "false");
    }

    public JSONWriter value(double d) throws JSONException {
        return value(new Double(d));
    }

    public JSONWriter value(long j) throws JSONException {
        return append(Long.toString(j));
    }

    public JSONWriter value(Object obj) throws JSONException {
        return append(JSONObject.valueToString(obj));
    }
}
