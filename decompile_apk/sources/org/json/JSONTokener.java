package org.json;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import kotlin.text.Typography;

public class JSONTokener {
    private long character;
    private boolean eof;
    private long index;
    private long line;
    private char previous;
    private Reader reader;
    private boolean usePrevious;

    public static int dehexchar(char c) {
        return (c < '0' || c > '9') ? (c < 'A' || c > 'F') ? (c < 'a' || c > 'f') ? -1 : c - 87 : c - 55 : c - 48;
    }

    public JSONTokener(Reader reader) {
        if (!reader.markSupported()) {
            reader = new BufferedReader(reader);
        }
        this.reader = reader;
        this.eof = false;
        this.usePrevious = false;
        this.previous = '\u0000';
        this.index = 0;
        this.character = 1;
        this.line = 1;
    }

    public JSONTokener(InputStream inputStream) throws JSONException {
        this(new InputStreamReader(inputStream));
    }

    public JSONTokener(String str) {
        this(new StringReader(str));
    }

    public void back() throws JSONException {
        if (this.usePrevious || this.index <= 0) {
            throw new JSONException("Stepping back two steps is not supported");
        }
        this.index--;
        this.character--;
        this.usePrevious = true;
        this.eof = false;
    }

    public boolean end() {
        return this.eof && !this.usePrevious;
    }

    public boolean more() throws JSONException {
        next();
        if (end()) {
            return false;
        }
        back();
        return true;
    }

    public char next() throws JSONException {
        int i = 0;
        if (this.usePrevious) {
            this.usePrevious = false;
            i = this.previous;
        } else {
            try {
                int read = this.reader.read();
                if (read <= 0) {
                    this.eof = true;
                } else {
                    i = read;
                }
            } catch (Throwable e) {
                throw new JSONException(e);
            }
        }
        long j = 1;
        this.index++;
        if (this.previous == '\r') {
            this.line++;
            if (i == 10) {
                j = 0;
            }
            this.character = j;
        } else if (i == 10) {
            this.line++;
            this.character = 0;
        } else {
            this.character++;
        }
        this.previous = (char) i;
        return this.previous;
    }

    public char next(char c) throws JSONException {
        char next = next();
        if (next == c) {
            return next;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected '");
        stringBuilder.append(c);
        stringBuilder.append("' and instead saw '");
        stringBuilder.append(next);
        stringBuilder.append("'");
        throw syntaxError(stringBuilder.toString());
    }

    public String next(int i) throws JSONException {
        if (i == 0) {
            return "";
        }
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = next();
            if (end()) {
                throw syntaxError("Substring bounds error");
            }
        }
        return new String(cArr);
    }

    public char nextClean() throws JSONException {
        char next;
        do {
            next = next();
            if (next == '\u0000') {
                break;
            }
        } while (next <= ' ');
        return next;
    }

    public String nextString(char c) throws JSONException {
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            char next = next();
            if (next == '\u0000' || next == '\n' || next == '\r') {
                throw syntaxError("Unterminated string");
            } else if (next == '\\') {
                next = next();
                if (next == Typography.quote || next == '\'' || next == '/' || next == '\\') {
                    stringBuilder.append(next);
                } else if (next == 'b') {
                    stringBuilder.append('\b');
                } else if (next == 'f') {
                    stringBuilder.append('\f');
                } else if (next == 'n') {
                    stringBuilder.append('\n');
                } else if (next != 'r') {
                    switch (next) {
                        case 't':
                            stringBuilder.append('\t');
                            break;
                        case 'u':
                            stringBuilder.append((char) Integer.parseInt(next(4), 16));
                            break;
                        default:
                            throw syntaxError("Illegal escape.");
                    }
                } else {
                    stringBuilder.append('\r');
                }
            } else if (next == c) {
                return stringBuilder.toString();
            } else {
                stringBuilder.append(next);
            }
        }
    }

    public String nextTo(char c) throws JSONException {
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            char next = next();
            if (next == c || next == '\u0000' || next == '\n') {
                break;
            } else if (next == '\r') {
                break;
            } else {
                stringBuilder.append(next);
            }
        }
        if (next != '\u0000') {
            back();
        }
        return stringBuilder.toString().trim();
    }

    public String nextTo(String str) throws JSONException {
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            char next = next();
            if (str.indexOf(next) >= 0 || next == '\u0000' || next == '\n') {
                break;
            } else if (next == '\r') {
                break;
            } else {
                stringBuilder.append(next);
            }
        }
        if (next != '\u0000') {
            back();
        }
        return stringBuilder.toString().trim();
    }

    public Object nextValue() throws JSONException {
        char nextClean = nextClean();
        if (nextClean == Typography.quote || nextClean == '\'') {
            return nextString(nextClean);
        }
        if (nextClean == '[') {
            back();
            return new JSONArray(this);
        } else if (nextClean != '{') {
            StringBuilder stringBuilder = new StringBuilder();
            while (nextClean >= ' ' && ",:]}/\\\"[{;=#".indexOf(nextClean) < 0) {
                stringBuilder.append(nextClean);
                nextClean = next();
            }
            back();
            String trim = stringBuilder.toString().trim();
            if (!"".equals(trim)) {
                return JSONObject.stringToValue(trim);
            }
            throw syntaxError("Missing value");
        } else {
            back();
            return new JSONObject(this);
        }
    }

    public char skipTo(char c) throws JSONException {
        try {
            char next;
            long j = this.index;
            long j2 = this.character;
            long j3 = this.line;
            this.reader.mark(1000000);
            do {
                next = next();
                if (next == '\u0000') {
                    this.reader.reset();
                    this.index = j;
                    this.character = j2;
                    this.line = j3;
                    return next;
                }
            } while (next != c);
            back();
            return next;
        } catch (Throwable e) {
            throw new JSONException(e);
        }
    }

    public JSONException syntaxError(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(toString());
        return new JSONException(stringBuilder.toString());
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" at ");
        stringBuilder.append(this.index);
        stringBuilder.append(" [character ");
        stringBuilder.append(this.character);
        stringBuilder.append(" line ");
        stringBuilder.append(this.line);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
