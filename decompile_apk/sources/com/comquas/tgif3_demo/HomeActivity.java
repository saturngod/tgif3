package com.comquas.tgif3_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import java.util.HashMap;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/comquas/tgif3_demo/HomeActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "button1", "Landroid/widget/Button;", "getButton1", "()Landroid/widget/Button;", "button2", "getButton2", "button3", "getButton3", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 1, 11})
/* compiled from: HomeActivity.kt */
public final class HomeActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    @Nullable
    private final Button button1;
    @Nullable
    private final Button button2;
    @Nullable
    private final Button button3;

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
    public final Button getButton1() {
        return this.button1;
    }

    @Nullable
    public final Button getButton2() {
        return this.button2;
    }

    @Nullable
    public final Button getButton3() {
        return this.button3;
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0240R.layout.activity_home);
        Button button = (Button) findViewById(C0240R.id.button);
        Button button2 = (Button) findViewById(C0240R.id.button2);
        Button button3 = (Button) findViewById(C0240R.id.button3);
        if (button != null) {
            button.setOnClickListener(new HomeActivity$onCreate$1(this));
        }
        if (button2 != null) {
            button2.setOnClickListener((OnClickListener) new HomeActivity$onCreate$2(this));
        }
        if (button3 != null) {
            button3.setOnClickListener((OnClickListener) new HomeActivity$onCreate$3(this));
        }
    }
}
