package android.support.v7.widget;

import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

class AppCompatHintHelper {
    static InputConnection onCreateInputConnection(InputConnection inputConnection, EditorInfo editorInfo, View view) {
        if (inputConnection != null && editorInfo.hintText == null) {
            for (view = view.getParent(); view instanceof View; view = view.getParent()) {
                if (view instanceof WithHint) {
                    editorInfo.hintText = ((WithHint) view).getHint();
                    break;
                }
            }
        }
        return inputConnection;
    }

    private AppCompatHintHelper() {
    }
}
