package com.comquas.tgif3_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import org.jetbrains.anko.AsyncKt;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\u0006\u0010\u001c\u001a\u00020\u0019R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u001d"}, d2 = {"Lcom/comquas/tgif3_demo/MainActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "button", "Landroid/widget/Button;", "getButton", "()Landroid/widget/Button;", "setButton", "(Landroid/widget/Button;)V", "data1", "Landroid/widget/EditText;", "getData1", "()Landroid/widget/EditText;", "setData1", "(Landroid/widget/EditText;)V", "data2", "getData2", "setData2", "responseView", "Landroid/widget/TextView;", "getResponseView", "()Landroid/widget/TextView;", "setResponseView", "(Landroid/widget/TextView;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "sendDataRequest", "app_release"}, k = 1, mv = {1, 1, 11})
/* compiled from: MainActivity.kt */
public final class MainActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    @Nullable
    private Button button;
    @Nullable
    private EditText data1;
    @Nullable
    private EditText data2;
    @Nullable
    private TextView responseView;

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        view = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), view);
        return view;
    }

    @Nullable
    public final Button getButton() {
        return this.button;
    }

    public final void setButton(@Nullable Button button) {
        this.button = button;
    }

    @Nullable
    public final TextView getResponseView() {
        return this.responseView;
    }

    public final void setResponseView(@Nullable TextView textView) {
        this.responseView = textView;
    }

    @Nullable
    public final EditText getData1() {
        return this.data1;
    }

    public final void setData1(@Nullable EditText editText) {
        this.data1 = editText;
    }

    @Nullable
    public final EditText getData2() {
        return this.data2;
    }

    public final void setData2(@Nullable EditText editText) {
        this.data2 = editText;
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0240R.layout.activity_main);
        this.button = (Button) findViewById(C0240R.id.sendButton);
        this.responseView = (TextView) findViewById(C0240R.id.responseText);
        this.data1 = (EditText) findViewById(C0240R.id.data1Txt);
        this.data2 = (EditText) findViewById(C0240R.id.data2Txt);
        bundle = this.button;
        if (bundle != null) {
            bundle.setOnClickListener(new MainActivity$onCreate$1(this));
        }
    }

    public final void sendDataRequest() {
        TextView textView = this.responseView;
        if (textView != null) {
            textView.setText("Loading...");
        }
        EditText editText = this.data1;
        if (editText == null) {
            Intrinsics.throwNpe();
        }
        String obj = editText.getText().toString();
        EditText editText2 = this.data2;
        if (editText2 == null) {
            Intrinsics.throwNpe();
        }
        String obj2 = editText2.getText().toString();
        ObjectRef objectRef = new ObjectRef();
        objectRef.element = MapsKt__MapsKt.mapOf(TuplesKt.to("data1", obj), TuplesKt.to("data2", obj2));
        AsyncKt.doAsync$default(this, null, new MainActivity$sendDataRequest$1(this, objectRef), 1, null);
    }
}
