package android.support.v4.app;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ListFragment extends Fragment {
    static final int INTERNAL_EMPTY_ID = 16711681;
    static final int INTERNAL_LIST_CONTAINER_ID = 16711683;
    static final int INTERNAL_PROGRESS_CONTAINER_ID = 16711682;
    ListAdapter mAdapter;
    CharSequence mEmptyText;
    View mEmptyView;
    private final Handler mHandler = new Handler();
    ListView mList;
    View mListContainer;
    boolean mListShown;
    private final OnItemClickListener mOnClickListener = new C00732();
    View mProgressContainer;
    private final Runnable mRequestFocus = new C00721();
    TextView mStandardEmptyView;

    /* renamed from: android.support.v4.app.ListFragment$1 */
    class C00721 implements Runnable {
        C00721() {
        }

        public void run() {
            ListFragment.this.mList.focusableViewAvailable(ListFragment.this.mList);
        }
    }

    /* renamed from: android.support.v4.app.ListFragment$2 */
    class C00732 implements OnItemClickListener {
        C00732() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            ListFragment.this.onListItemClick((ListView) adapterView, view, i, j);
        }
    }

    public void onListItemClick(ListView listView, View view, int i, long j) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = getContext();
        viewGroup = new FrameLayout(layoutInflater);
        bundle = new LinearLayout(layoutInflater);
        bundle.setId(INTERNAL_PROGRESS_CONTAINER_ID);
        bundle.setOrientation(1);
        bundle.setVisibility(8);
        bundle.setGravity(17);
        bundle.addView(new ProgressBar(layoutInflater, null, 16842874), new LayoutParams(-2, -2));
        viewGroup.addView(bundle, new LayoutParams(-1, -1));
        bundle = new FrameLayout(layoutInflater);
        bundle.setId(INTERNAL_LIST_CONTAINER_ID);
        View textView = new TextView(layoutInflater);
        textView.setId(INTERNAL_EMPTY_ID);
        textView.setGravity(17);
        bundle.addView(textView, new LayoutParams(-1, -1));
        View listView = new ListView(layoutInflater);
        listView.setId(16908298);
        listView.setDrawSelectorOnTop(null);
        bundle.addView(listView, new LayoutParams(-1, -1));
        viewGroup.addView(bundle, new LayoutParams(-1, -1));
        viewGroup.setLayoutParams(new LayoutParams(-1, -1));
        return viewGroup;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        ensureList();
    }

    public void onDestroyView() {
        this.mHandler.removeCallbacks(this.mRequestFocus);
        this.mList = null;
        this.mListShown = false;
        this.mListContainer = null;
        this.mProgressContainer = null;
        this.mEmptyView = null;
        this.mStandardEmptyView = null;
        super.onDestroyView();
    }

    public void setListAdapter(ListAdapter listAdapter) {
        boolean z = false;
        Object obj = this.mAdapter != null ? 1 : null;
        this.mAdapter = listAdapter;
        if (this.mList != null) {
            this.mList.setAdapter(listAdapter);
            if (this.mListShown == null && obj == null) {
                if (getView().getWindowToken() != null) {
                    z = true;
                }
                setListShown(true, z);
            }
        }
    }

    public void setSelection(int i) {
        ensureList();
        this.mList.setSelection(i);
    }

    public int getSelectedItemPosition() {
        ensureList();
        return this.mList.getSelectedItemPosition();
    }

    public long getSelectedItemId() {
        ensureList();
        return this.mList.getSelectedItemId();
    }

    public ListView getListView() {
        ensureList();
        return this.mList;
    }

    public void setEmptyText(CharSequence charSequence) {
        ensureList();
        if (this.mStandardEmptyView != null) {
            this.mStandardEmptyView.setText(charSequence);
            if (this.mEmptyText == null) {
                this.mList.setEmptyView(this.mStandardEmptyView);
            }
            this.mEmptyText = charSequence;
            return;
        }
        throw new IllegalStateException("Can't be used with a custom content view");
    }

    public void setListShown(boolean z) {
        setListShown(z, true);
    }

    public void setListShownNoAnimation(boolean z) {
        setListShown(z, false);
    }

    private void setListShown(boolean z, boolean z2) {
        ensureList();
        if (this.mProgressContainer == null) {
            throw new IllegalStateException("Can't be used with a custom content view");
        } else if (this.mListShown != z) {
            this.mListShown = z;
            if (z) {
                if (z2) {
                    this.mProgressContainer.startAnimation(AnimationUtils.loadAnimation(getContext(), 17432577));
                    this.mListContainer.startAnimation(AnimationUtils.loadAnimation(getContext(), 17432576));
                } else {
                    this.mProgressContainer.clearAnimation();
                    this.mListContainer.clearAnimation();
                }
                this.mProgressContainer.setVisibility(8);
                this.mListContainer.setVisibility(0);
            } else {
                if (z2) {
                    this.mProgressContainer.startAnimation(AnimationUtils.loadAnimation(getContext(), 17432576));
                    this.mListContainer.startAnimation(AnimationUtils.loadAnimation(getContext(), 17432577));
                } else {
                    this.mProgressContainer.clearAnimation();
                    this.mListContainer.clearAnimation();
                }
                this.mProgressContainer.setVisibility(0);
                this.mListContainer.setVisibility(8);
            }
        }
    }

    public ListAdapter getListAdapter() {
        return this.mAdapter;
    }

    private void ensureList() {
        if (this.mList == null) {
            View view = getView();
            if (view != null) {
                if (view instanceof ListView) {
                    this.mList = (ListView) view;
                } else {
                    this.mStandardEmptyView = (TextView) view.findViewById(INTERNAL_EMPTY_ID);
                    if (this.mStandardEmptyView == null) {
                        this.mEmptyView = view.findViewById(16908292);
                    } else {
                        this.mStandardEmptyView.setVisibility(8);
                    }
                    this.mProgressContainer = view.findViewById(INTERNAL_PROGRESS_CONTAINER_ID);
                    this.mListContainer = view.findViewById(INTERNAL_LIST_CONTAINER_ID);
                    view = view.findViewById(16908298);
                    if (view instanceof ListView) {
                        this.mList = (ListView) view;
                        if (this.mEmptyView != null) {
                            this.mList.setEmptyView(this.mEmptyView);
                        } else if (this.mEmptyText != null) {
                            this.mStandardEmptyView.setText(this.mEmptyText);
                            this.mList.setEmptyView(this.mStandardEmptyView);
                        }
                    } else if (view == null) {
                        throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
                    } else {
                        throw new RuntimeException("Content has view with id attribute 'android.R.id.list' that is not a ListView class");
                    }
                }
                this.mListShown = true;
                this.mList.setOnItemClickListener(this.mOnClickListener);
                if (this.mAdapter != null) {
                    ListAdapter listAdapter = this.mAdapter;
                    this.mAdapter = null;
                    setListAdapter(listAdapter);
                } else if (this.mProgressContainer != null) {
                    setListShown(false, false);
                }
                this.mHandler.post(this.mRequestFocus);
                return;
            }
            throw new IllegalStateException("Content view not yet created");
        }
    }
}
