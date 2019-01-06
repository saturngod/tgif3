package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.provider.FontsContractCompat.FontRequestCallback;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.NestedScrollView.OnScrollChangeListener;
import android.support.v7.appcompat.C0192R;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import java.lang.ref.WeakReference;

class AlertController {
    ListAdapter mAdapter;
    private int mAlertDialogLayout;
    private final OnClickListener mButtonHandler = new C01781();
    private final int mButtonIconDimen;
    Button mButtonNegative;
    private Drawable mButtonNegativeIcon;
    Message mButtonNegativeMessage;
    private CharSequence mButtonNegativeText;
    Button mButtonNeutral;
    private Drawable mButtonNeutralIcon;
    Message mButtonNeutralMessage;
    private CharSequence mButtonNeutralText;
    private int mButtonPanelLayoutHint = 0;
    private int mButtonPanelSideLayout;
    Button mButtonPositive;
    private Drawable mButtonPositiveIcon;
    Message mButtonPositiveMessage;
    private CharSequence mButtonPositiveText;
    int mCheckedItem = -1;
    private final Context mContext;
    private View mCustomTitleView;
    final AppCompatDialog mDialog;
    Handler mHandler;
    private Drawable mIcon;
    private int mIconId = 0;
    private ImageView mIconView;
    int mListItemLayout;
    int mListLayout;
    ListView mListView;
    private CharSequence mMessage;
    private TextView mMessageView;
    int mMultiChoiceItemLayout;
    NestedScrollView mScrollView;
    private boolean mShowTitle;
    int mSingleChoiceItemLayout;
    private CharSequence mTitle;
    private TextView mTitleView;
    private View mView;
    private int mViewLayoutResId;
    private int mViewSpacingBottom;
    private int mViewSpacingLeft;
    private int mViewSpacingRight;
    private boolean mViewSpacingSpecified = false;
    private int mViewSpacingTop;
    private final Window mWindow;

    /* renamed from: android.support.v7.app.AlertController$1 */
    class C01781 implements OnClickListener {
        C01781() {
        }

        public void onClick(View view) {
            view = (view != AlertController.this.mButtonPositive || AlertController.this.mButtonPositiveMessage == null) ? (view != AlertController.this.mButtonNegative || AlertController.this.mButtonNegativeMessage == null) ? (view != AlertController.this.mButtonNeutral || AlertController.this.mButtonNeutralMessage == null) ? null : Message.obtain(AlertController.this.mButtonNeutralMessage) : Message.obtain(AlertController.this.mButtonNegativeMessage) : Message.obtain(AlertController.this.mButtonPositiveMessage);
            if (view != null) {
                view.sendToTarget();
            }
            AlertController.this.mHandler.obtainMessage(1, AlertController.this.mDialog).sendToTarget();
        }
    }

    public static class AlertParams {
        public ListAdapter mAdapter;
        public boolean mCancelable;
        public int mCheckedItem = -1;
        public boolean[] mCheckedItems;
        public final Context mContext;
        public Cursor mCursor;
        public View mCustomTitleView;
        public boolean mForceInverseBackground;
        public Drawable mIcon;
        public int mIconAttrId = 0;
        public int mIconId = 0;
        public final LayoutInflater mInflater;
        public String mIsCheckedColumn;
        public boolean mIsMultiChoice;
        public boolean mIsSingleChoice;
        public CharSequence[] mItems;
        public String mLabelColumn;
        public CharSequence mMessage;
        public Drawable mNegativeButtonIcon;
        public DialogInterface.OnClickListener mNegativeButtonListener;
        public CharSequence mNegativeButtonText;
        public Drawable mNeutralButtonIcon;
        public DialogInterface.OnClickListener mNeutralButtonListener;
        public CharSequence mNeutralButtonText;
        public OnCancelListener mOnCancelListener;
        public OnMultiChoiceClickListener mOnCheckboxClickListener;
        public DialogInterface.OnClickListener mOnClickListener;
        public OnDismissListener mOnDismissListener;
        public OnItemSelectedListener mOnItemSelectedListener;
        public OnKeyListener mOnKeyListener;
        public OnPrepareListViewListener mOnPrepareListViewListener;
        public Drawable mPositiveButtonIcon;
        public DialogInterface.OnClickListener mPositiveButtonListener;
        public CharSequence mPositiveButtonText;
        public boolean mRecycleOnMeasure = true;
        public CharSequence mTitle;
        public View mView;
        public int mViewLayoutResId;
        public int mViewSpacingBottom;
        public int mViewSpacingLeft;
        public int mViewSpacingRight;
        public boolean mViewSpacingSpecified = false;
        public int mViewSpacingTop;

        public interface OnPrepareListViewListener {
            void onPrepareListView(ListView listView);
        }

        public AlertParams(Context context) {
            this.mContext = context;
            this.mCancelable = true;
            this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        public void apply(AlertController alertController) {
            if (this.mCustomTitleView != null) {
                alertController.setCustomTitle(this.mCustomTitleView);
            } else {
                if (this.mTitle != null) {
                    alertController.setTitle(this.mTitle);
                }
                if (this.mIcon != null) {
                    alertController.setIcon(this.mIcon);
                }
                if (this.mIconId != 0) {
                    alertController.setIcon(this.mIconId);
                }
                if (this.mIconAttrId != 0) {
                    alertController.setIcon(alertController.getIconAttributeResId(this.mIconAttrId));
                }
            }
            if (this.mMessage != null) {
                alertController.setMessage(this.mMessage);
            }
            if (!(this.mPositiveButtonText == null && this.mPositiveButtonIcon == null)) {
                alertController.setButton(-1, this.mPositiveButtonText, this.mPositiveButtonListener, null, this.mPositiveButtonIcon);
            }
            if (!(this.mNegativeButtonText == null && this.mNegativeButtonIcon == null)) {
                alertController.setButton(-2, this.mNegativeButtonText, this.mNegativeButtonListener, null, this.mNegativeButtonIcon);
            }
            if (!(this.mNeutralButtonText == null && this.mNeutralButtonIcon == null)) {
                alertController.setButton(-3, this.mNeutralButtonText, this.mNeutralButtonListener, null, this.mNeutralButtonIcon);
            }
            if (!(this.mItems == null && this.mCursor == null && this.mAdapter == null)) {
                createListView(alertController);
            }
            if (this.mView != null) {
                if (this.mViewSpacingSpecified) {
                    alertController.setView(this.mView, this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
                    return;
                }
                alertController.setView(this.mView);
            } else if (this.mViewLayoutResId != 0) {
                alertController.setView(this.mViewLayoutResId);
            }
        }

        private void createListView(final AlertController alertController) {
            ListAdapter simpleCursorAdapter;
            final RecycleListView recycleListView = (RecycleListView) this.mInflater.inflate(alertController.mListLayout, null);
            if (!this.mIsMultiChoice) {
                int i;
                if (this.mIsSingleChoice) {
                    i = alertController.mSingleChoiceItemLayout;
                } else {
                    i = alertController.mListItemLayout;
                }
                int i2 = i;
                if (this.mCursor != null) {
                    simpleCursorAdapter = new SimpleCursorAdapter(this.mContext, i2, this.mCursor, new String[]{this.mLabelColumn}, new int[]{16908308});
                } else if (this.mAdapter != null) {
                    simpleCursorAdapter = this.mAdapter;
                } else {
                    simpleCursorAdapter = new CheckedItemAdapter(this.mContext, i2, 16908308, this.mItems);
                }
            } else if (this.mCursor == null) {
                final RecycleListView recycleListView2 = recycleListView;
                r1 = new ArrayAdapter<CharSequence>(this.mContext, alertController.mMultiChoiceItemLayout, 16908308, this.mItems) {
                    public View getView(int i, View view, ViewGroup viewGroup) {
                        view = super.getView(i, view, viewGroup);
                        if (!(AlertParams.this.mCheckedItems == null || AlertParams.this.mCheckedItems[i] == null)) {
                            recycleListView2.setItemChecked(i, true);
                        }
                        return view;
                    }
                };
            } else {
                final RecycleListView recycleListView3 = recycleListView;
                final AlertController alertController2 = alertController;
                r1 = new CursorAdapter(this.mContext, this.mCursor, false) {
                    private final int mIsCheckedIndex;
                    private final int mLabelIndex;

                    public void bindView(View view, Context context, Cursor cursor) {
                        ((CheckedTextView) view.findViewById(16908308)).setText(cursor.getString(this.mLabelIndex));
                        view = recycleListView3;
                        context = cursor.getPosition();
                        boolean z = true;
                        if (cursor.getInt(this.mIsCheckedIndex) != 1) {
                            z = false;
                        }
                        view.setItemChecked(context, z);
                    }

                    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                        return AlertParams.this.mInflater.inflate(alertController2.mMultiChoiceItemLayout, viewGroup, false);
                    }
                };
            }
            if (this.mOnPrepareListViewListener != null) {
                this.mOnPrepareListViewListener.onPrepareListView(recycleListView);
            }
            alertController.mAdapter = simpleCursorAdapter;
            alertController.mCheckedItem = this.mCheckedItem;
            if (this.mOnClickListener != null) {
                recycleListView.setOnItemClickListener(new OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        AlertParams.this.mOnClickListener.onClick(alertController.mDialog, i);
                        if (AlertParams.this.mIsSingleChoice == null) {
                            alertController.mDialog.dismiss();
                        }
                    }
                });
            } else if (this.mOnCheckboxClickListener != null) {
                recycleListView.setOnItemClickListener(new OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        if (AlertParams.this.mCheckedItems != null) {
                            AlertParams.this.mCheckedItems[i] = recycleListView.isItemChecked(i);
                        }
                        AlertParams.this.mOnCheckboxClickListener.onClick(alertController.mDialog, i, recycleListView.isItemChecked(i));
                    }
                });
            }
            if (this.mOnItemSelectedListener != null) {
                recycleListView.setOnItemSelectedListener(this.mOnItemSelectedListener);
            }
            if (this.mIsSingleChoice) {
                recycleListView.setChoiceMode(1);
            } else if (this.mIsMultiChoice) {
                recycleListView.setChoiceMode(2);
            }
            alertController.mListView = recycleListView;
        }
    }

    private static final class ButtonHandler extends Handler {
        private static final int MSG_DISMISS_DIALOG = 1;
        private WeakReference<DialogInterface> mDialog;

        public ButtonHandler(DialogInterface dialogInterface) {
            this.mDialog = new WeakReference(dialogInterface);
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 1) {
                switch (i) {
                    case FontRequestCallback.FAIL_REASON_FONT_LOAD_ERROR /*-3*/:
                    case -2:
                    case -1:
                        ((DialogInterface.OnClickListener) message.obj).onClick((DialogInterface) this.mDialog.get(), message.what);
                        return;
                    default:
                        return;
                }
            }
            ((DialogInterface) message.obj).dismiss();
        }
    }

    private static class CheckedItemAdapter extends ArrayAdapter<CharSequence> {
        public long getItemId(int i) {
            return (long) i;
        }

        public boolean hasStableIds() {
            return true;
        }

        public CheckedItemAdapter(Context context, int i, int i2, CharSequence[] charSequenceArr) {
            super(context, i, i2, charSequenceArr);
        }
    }

    public static class RecycleListView extends ListView {
        private final int mPaddingBottomNoButtons;
        private final int mPaddingTopNoTitle;

        public RecycleListView(Context context) {
            this(context, null);
        }

        public RecycleListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            context = context.obtainStyledAttributes(attributeSet, C0192R.styleable.RecycleListView);
            this.mPaddingBottomNoButtons = context.getDimensionPixelOffset(C0192R.styleable.RecycleListView_paddingBottomNoButtons, -1);
            this.mPaddingTopNoTitle = context.getDimensionPixelOffset(C0192R.styleable.RecycleListView_paddingTopNoTitle, -1);
        }

        public void setHasDecor(boolean z, boolean z2) {
            if (!z2 || !z) {
                setPadding(getPaddingLeft(), z ? getPaddingTop() : this.mPaddingTopNoTitle, getPaddingRight(), z2 ? getPaddingBottom() : this.mPaddingBottomNoButtons);
            }
        }
    }

    private static boolean shouldCenterSingleButton(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(C0192R.attr.alertDialogCenterButtons, typedValue, true);
        if (typedValue.data != null) {
            return true;
        }
        return false;
    }

    public AlertController(Context context, AppCompatDialog appCompatDialog, Window window) {
        this.mContext = context;
        this.mDialog = appCompatDialog;
        this.mWindow = window;
        this.mHandler = new ButtonHandler(appCompatDialog);
        context = context.obtainStyledAttributes(null, C0192R.styleable.AlertDialog, C0192R.attr.alertDialogStyle, 0);
        this.mAlertDialogLayout = context.getResourceId(C0192R.styleable.AlertDialog_android_layout, 0);
        this.mButtonPanelSideLayout = context.getResourceId(C0192R.styleable.AlertDialog_buttonPanelSideLayout, 0);
        this.mListLayout = context.getResourceId(C0192R.styleable.AlertDialog_listLayout, 0);
        this.mMultiChoiceItemLayout = context.getResourceId(C0192R.styleable.AlertDialog_multiChoiceItemLayout, 0);
        this.mSingleChoiceItemLayout = context.getResourceId(C0192R.styleable.AlertDialog_singleChoiceItemLayout, 0);
        this.mListItemLayout = context.getResourceId(C0192R.styleable.AlertDialog_listItemLayout, 0);
        this.mShowTitle = context.getBoolean(C0192R.styleable.AlertDialog_showTitle, true);
        this.mButtonIconDimen = context.getDimensionPixelSize(C0192R.styleable.AlertDialog_buttonIconDimen, 0);
        context.recycle();
        appCompatDialog.supportRequestWindowFeature(1);
    }

    static boolean canTextInput(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (childCount > 0) {
            childCount--;
            if (canTextInput(viewGroup.getChildAt(childCount))) {
                return true;
            }
        }
        return false;
    }

    public void installContent() {
        this.mDialog.setContentView(selectContentView());
        setupView();
    }

    private int selectContentView() {
        if (this.mButtonPanelSideLayout == 0) {
            return this.mAlertDialogLayout;
        }
        if (this.mButtonPanelLayoutHint == 1) {
            return this.mButtonPanelSideLayout;
        }
        return this.mAlertDialogLayout;
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        if (this.mTitleView != null) {
            this.mTitleView.setText(charSequence);
        }
    }

    public void setCustomTitle(View view) {
        this.mCustomTitleView = view;
    }

    public void setMessage(CharSequence charSequence) {
        this.mMessage = charSequence;
        if (this.mMessageView != null) {
            this.mMessageView.setText(charSequence);
        }
    }

    public void setView(int i) {
        this.mView = null;
        this.mViewLayoutResId = i;
        this.mViewSpacingSpecified = false;
    }

    public void setView(View view) {
        this.mView = view;
        this.mViewLayoutResId = 0;
        this.mViewSpacingSpecified = false;
    }

    public void setView(View view, int i, int i2, int i3, int i4) {
        this.mView = view;
        this.mViewLayoutResId = null;
        this.mViewSpacingSpecified = true;
        this.mViewSpacingLeft = i;
        this.mViewSpacingTop = i2;
        this.mViewSpacingRight = i3;
        this.mViewSpacingBottom = i4;
    }

    public void setButtonPanelLayoutHint(int i) {
        this.mButtonPanelLayoutHint = i;
    }

    public void setButton(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message, Drawable drawable) {
        if (message == null && onClickListener != null) {
            message = this.mHandler.obtainMessage(i, onClickListener);
        }
        switch (i) {
            case FontRequestCallback.FAIL_REASON_FONT_LOAD_ERROR /*-3*/:
                this.mButtonNeutralText = charSequence;
                this.mButtonNeutralMessage = message;
                this.mButtonNeutralIcon = drawable;
                return;
            case -2:
                this.mButtonNegativeText = charSequence;
                this.mButtonNegativeMessage = message;
                this.mButtonNegativeIcon = drawable;
                return;
            case -1:
                this.mButtonPositiveText = charSequence;
                this.mButtonPositiveMessage = message;
                this.mButtonPositiveIcon = drawable;
                return;
            default:
                throw new IllegalArgumentException("Button does not exist");
        }
    }

    public void setIcon(int i) {
        this.mIcon = null;
        this.mIconId = i;
        if (this.mIconView == null) {
            return;
        }
        if (i != 0) {
            this.mIconView.setVisibility(0);
            this.mIconView.setImageResource(this.mIconId);
            return;
        }
        this.mIconView.setVisibility(8);
    }

    public void setIcon(Drawable drawable) {
        this.mIcon = drawable;
        this.mIconId = 0;
        if (this.mIconView == null) {
            return;
        }
        if (drawable != null) {
            this.mIconView.setVisibility(0);
            this.mIconView.setImageDrawable(drawable);
            return;
        }
        this.mIconView.setVisibility(8);
    }

    public int getIconAttributeResId(int i) {
        TypedValue typedValue = new TypedValue();
        this.mContext.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.resourceId;
    }

    public ListView getListView() {
        return this.mListView;
    }

    public Button getButton(int i) {
        switch (i) {
            case FontRequestCallback.FAIL_REASON_FONT_LOAD_ERROR /*-3*/:
                return this.mButtonNeutral;
            case -2:
                return this.mButtonNegative;
            case -1:
                return this.mButtonPositive;
            default:
                return 0;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return (this.mScrollView == 0 || this.mScrollView.executeKeyEvent(keyEvent) == 0) ? false : true;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return (this.mScrollView == 0 || this.mScrollView.executeKeyEvent(keyEvent) == 0) ? false : true;
    }

    @Nullable
    private ViewGroup resolvePanel(@Nullable View view, @Nullable View view2) {
        if (view == null) {
            if ((view2 instanceof ViewStub) != null) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if ((view instanceof ViewStub) != null) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }

    private void setupView() {
        View findViewById = this.mWindow.findViewById(C0192R.id.parentPanel);
        View findViewById2 = findViewById.findViewById(C0192R.id.topPanel);
        View findViewById3 = findViewById.findViewById(C0192R.id.contentPanel);
        View findViewById4 = findViewById.findViewById(C0192R.id.buttonPanel);
        ViewGroup viewGroup = (ViewGroup) findViewById.findViewById(C0192R.id.customPanel);
        setupCustomContent(viewGroup);
        View findViewById5 = viewGroup.findViewById(C0192R.id.topPanel);
        View findViewById6 = viewGroup.findViewById(C0192R.id.contentPanel);
        View findViewById7 = viewGroup.findViewById(C0192R.id.buttonPanel);
        ViewGroup resolvePanel = resolvePanel(findViewById5, findViewById2);
        ViewGroup resolvePanel2 = resolvePanel(findViewById6, findViewById3);
        ViewGroup resolvePanel3 = resolvePanel(findViewById7, findViewById4);
        setupContent(resolvePanel2);
        setupButtons(resolvePanel3);
        setupTitle(resolvePanel);
        int i = 0;
        Object obj = (viewGroup == null || viewGroup.getVisibility() == 8) ? null : 1;
        boolean z = (resolvePanel == null || resolvePanel.getVisibility() == 8) ? false : true;
        boolean z2 = (resolvePanel3 == null || resolvePanel3.getVisibility() == 8) ? false : true;
        if (!(z2 || resolvePanel2 == null)) {
            findViewById5 = resolvePanel2.findViewById(C0192R.id.textSpacerNoButtons);
            if (findViewById5 != null) {
                findViewById5.setVisibility(0);
            }
        }
        if (z) {
            if (this.mScrollView != null) {
                this.mScrollView.setClipToPadding(true);
            }
            findViewById5 = null;
            if (!(this.mMessage == null && this.mListView == null)) {
                findViewById5 = resolvePanel.findViewById(C0192R.id.titleDividerNoCustom);
            }
            if (findViewById5 != null) {
                findViewById5.setVisibility(0);
            }
        } else if (resolvePanel2 != null) {
            findViewById2 = resolvePanel2.findViewById(C0192R.id.textSpacerNoTitle);
            if (findViewById2 != null) {
                findViewById2.setVisibility(0);
            }
        }
        if (this.mListView instanceof RecycleListView) {
            ((RecycleListView) this.mListView).setHasDecor(z, z2);
        }
        if (obj == null) {
            findViewById = this.mListView != null ? this.mListView : this.mScrollView;
            if (findViewById != null) {
                if (z2) {
                    i = 2;
                }
                setScrollIndicators(resolvePanel2, findViewById, z | i, 3);
            }
        }
        ListView listView = this.mListView;
        if (listView != null && this.mAdapter != null) {
            listView.setAdapter(this.mAdapter);
            int i2 = this.mCheckedItem;
            if (i2 > -1) {
                listView.setItemChecked(i2, true);
                listView.setSelection(i2);
            }
        }
    }

    private void setScrollIndicators(ViewGroup viewGroup, View view, int i, int i2) {
        View findViewById = this.mWindow.findViewById(C0192R.id.scrollIndicatorUp);
        View findViewById2 = this.mWindow.findViewById(C0192R.id.scrollIndicatorDown);
        if (VERSION.SDK_INT >= 23) {
            ViewCompat.setScrollIndicators(view, i, i2);
            if (findViewById != null) {
                viewGroup.removeView(findViewById);
            }
            if (findViewById2 != null) {
                viewGroup.removeView(findViewById2);
                return;
            }
            return;
        }
        view = null;
        if (findViewById != null && (i & 1) == 0) {
            viewGroup.removeView(findViewById);
            findViewById = null;
        }
        if (findViewById2 == null || (i & 2) != 0) {
            view = findViewById2;
        } else {
            viewGroup.removeView(findViewById2);
        }
        if (findViewById != null || view != null) {
            if (this.mMessage != 0) {
                this.mScrollView.setOnScrollChangeListener(new OnScrollChangeListener() {
                    public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                        AlertController.manageScrollIndicators(nestedScrollView, findViewById, view);
                    }
                });
                this.mScrollView.post(new Runnable() {
                    public void run() {
                        AlertController.manageScrollIndicators(AlertController.this.mScrollView, findViewById, view);
                    }
                });
            } else if (this.mListView != 0) {
                this.mListView.setOnScrollListener(new OnScrollListener() {
                    public void onScrollStateChanged(AbsListView absListView, int i) {
                    }

                    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                        AlertController.manageScrollIndicators(absListView, findViewById, view);
                    }
                });
                this.mListView.post(new Runnable() {
                    public void run() {
                        AlertController.manageScrollIndicators(AlertController.this.mListView, findViewById, view);
                    }
                });
            } else {
                if (findViewById != null) {
                    viewGroup.removeView(findViewById);
                }
                if (view != null) {
                    viewGroup.removeView(view);
                }
            }
        }
    }

    private void setupCustomContent(ViewGroup viewGroup) {
        boolean z = false;
        View inflate = this.mView != null ? this.mView : this.mViewLayoutResId != 0 ? LayoutInflater.from(this.mContext).inflate(this.mViewLayoutResId, viewGroup, false) : null;
        if (inflate != null) {
            z = true;
        }
        if (!(z && canTextInput(inflate))) {
            this.mWindow.setFlags(131072, 131072);
        }
        if (z) {
            FrameLayout frameLayout = (FrameLayout) this.mWindow.findViewById(C0192R.id.custom);
            frameLayout.addView(inflate, new LayoutParams(-1, -1));
            if (this.mViewSpacingSpecified) {
                frameLayout.setPadding(this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
            }
            if (this.mListView != null) {
                ((LinearLayoutCompat.LayoutParams) viewGroup.getLayoutParams()).weight = 0.0f;
                return;
            }
            return;
        }
        viewGroup.setVisibility(8);
    }

    private void setupTitle(ViewGroup viewGroup) {
        if (this.mCustomTitleView != null) {
            viewGroup.addView(this.mCustomTitleView, 0, new LayoutParams(-1, -2));
            this.mWindow.findViewById(C0192R.id.title_template).setVisibility(8);
            return;
        }
        this.mIconView = (ImageView) this.mWindow.findViewById(16908294);
        if ((TextUtils.isEmpty(this.mTitle) ^ 1) == 0 || !this.mShowTitle) {
            this.mWindow.findViewById(C0192R.id.title_template).setVisibility(8);
            this.mIconView.setVisibility(8);
            viewGroup.setVisibility(8);
            return;
        }
        this.mTitleView = (TextView) this.mWindow.findViewById(C0192R.id.alertTitle);
        this.mTitleView.setText(this.mTitle);
        if (this.mIconId != null) {
            this.mIconView.setImageResource(this.mIconId);
        } else if (this.mIcon != null) {
            this.mIconView.setImageDrawable(this.mIcon);
        } else {
            this.mTitleView.setPadding(this.mIconView.getPaddingLeft(), this.mIconView.getPaddingTop(), this.mIconView.getPaddingRight(), this.mIconView.getPaddingBottom());
            this.mIconView.setVisibility(8);
        }
    }

    private void setupContent(ViewGroup viewGroup) {
        this.mScrollView = (NestedScrollView) this.mWindow.findViewById(C0192R.id.scrollView);
        this.mScrollView.setFocusable(false);
        this.mScrollView.setNestedScrollingEnabled(false);
        this.mMessageView = (TextView) viewGroup.findViewById(16908299);
        if (this.mMessageView != null) {
            if (this.mMessage != null) {
                this.mMessageView.setText(this.mMessage);
            } else {
                this.mMessageView.setVisibility(8);
                this.mScrollView.removeView(this.mMessageView);
                if (this.mListView != null) {
                    viewGroup = (ViewGroup) this.mScrollView.getParent();
                    int indexOfChild = viewGroup.indexOfChild(this.mScrollView);
                    viewGroup.removeViewAt(indexOfChild);
                    viewGroup.addView(this.mListView, indexOfChild, new LayoutParams(-1, -1));
                } else {
                    viewGroup.setVisibility(8);
                }
            }
        }
    }

    static void manageScrollIndicators(View view, View view2, View view3) {
        int i = 4;
        if (view2 != null) {
            view2.setVisibility(view.canScrollVertically(-1) ? 0 : 4);
        }
        if (view3 != null) {
            if (view.canScrollVertically(1) != null) {
                i = 0;
            }
            view3.setVisibility(i);
        }
    }

    private void setupButtons(ViewGroup viewGroup) {
        int i;
        this.mButtonPositive = (Button) viewGroup.findViewById(16908313);
        this.mButtonPositive.setOnClickListener(this.mButtonHandler);
        Object obj = 1;
        if (TextUtils.isEmpty(this.mButtonPositiveText) && this.mButtonPositiveIcon == null) {
            this.mButtonPositive.setVisibility(8);
            i = 0;
        } else {
            this.mButtonPositive.setText(this.mButtonPositiveText);
            if (this.mButtonPositiveIcon != null) {
                this.mButtonPositiveIcon.setBounds(0, 0, this.mButtonIconDimen, this.mButtonIconDimen);
                this.mButtonPositive.setCompoundDrawables(this.mButtonPositiveIcon, null, null, null);
            }
            this.mButtonPositive.setVisibility(0);
            i = 1;
        }
        this.mButtonNegative = (Button) viewGroup.findViewById(16908314);
        this.mButtonNegative.setOnClickListener(this.mButtonHandler);
        if (TextUtils.isEmpty(this.mButtonNegativeText) && this.mButtonNegativeIcon == null) {
            this.mButtonNegative.setVisibility(8);
        } else {
            this.mButtonNegative.setText(this.mButtonNegativeText);
            if (this.mButtonNegativeIcon != null) {
                this.mButtonNegativeIcon.setBounds(0, 0, this.mButtonIconDimen, this.mButtonIconDimen);
                this.mButtonNegative.setCompoundDrawables(this.mButtonNegativeIcon, null, null, null);
            }
            this.mButtonNegative.setVisibility(0);
            i |= 2;
        }
        this.mButtonNeutral = (Button) viewGroup.findViewById(16908315);
        this.mButtonNeutral.setOnClickListener(this.mButtonHandler);
        if (TextUtils.isEmpty(this.mButtonNeutralText) && this.mButtonNeutralIcon == null) {
            this.mButtonNeutral.setVisibility(8);
        } else {
            this.mButtonNeutral.setText(this.mButtonNeutralText);
            if (this.mButtonPositiveIcon != null) {
                this.mButtonPositiveIcon.setBounds(0, 0, this.mButtonIconDimen, this.mButtonIconDimen);
                this.mButtonPositive.setCompoundDrawables(this.mButtonPositiveIcon, null, null, null);
            }
            this.mButtonNeutral.setVisibility(0);
            i |= 4;
        }
        if (shouldCenterSingleButton(this.mContext)) {
            if (i == 1) {
                centerButton(this.mButtonPositive);
            } else if (i == 2) {
                centerButton(this.mButtonNegative);
            } else if (i == 4) {
                centerButton(this.mButtonNeutral);
            }
        }
        if (i == 0) {
            obj = null;
        }
        if (obj == null) {
            viewGroup.setVisibility(8);
        }
    }

    private void centerButton(Button button) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
        layoutParams.gravity = 1;
        layoutParams.weight = 0.5f;
        button.setLayoutParams(layoutParams);
    }
}
