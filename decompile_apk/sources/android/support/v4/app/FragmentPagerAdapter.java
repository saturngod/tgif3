package android.support.v4.app;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public abstract class FragmentPagerAdapter extends PagerAdapter {
    private static final boolean DEBUG = false;
    private static final String TAG = "FragmentPagerAdapter";
    private FragmentTransaction mCurTransaction = null;
    private Fragment mCurrentPrimaryItem = null;
    private final FragmentManager mFragmentManager;

    public abstract Fragment getItem(int i);

    public long getItemId(int i) {
        return (long) i;
    }

    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
    }

    public Parcelable saveState() {
        return null;
    }

    public FragmentPagerAdapter(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    public void startUpdate(@NonNull ViewGroup viewGroup) {
        if (viewGroup.getId() == -1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ViewPager with adapter ");
            stringBuilder.append(this);
            stringBuilder.append(" requires a view id");
            throw new IllegalStateException(stringBuilder.toString());
        }
    }

    @NonNull
    public Object instantiateItem(@NonNull ViewGroup viewGroup, int i) {
        if (this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }
        long itemId = getItemId(i);
        Fragment findFragmentByTag = this.mFragmentManager.findFragmentByTag(makeFragmentName(viewGroup.getId(), itemId));
        if (findFragmentByTag != null) {
            this.mCurTransaction.attach(findFragmentByTag);
        } else {
            findFragmentByTag = getItem(i);
            this.mCurTransaction.add(viewGroup.getId(), findFragmentByTag, makeFragmentName(viewGroup.getId(), itemId));
        }
        if (findFragmentByTag != this.mCurrentPrimaryItem) {
            findFragmentByTag.setMenuVisibility(false);
            findFragmentByTag.setUserVisibleHint(false);
        }
        return findFragmentByTag;
    }

    public void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
        if (this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }
        this.mCurTransaction.detach((Fragment) obj);
    }

    public void setPrimaryItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
        Fragment fragment = (Fragment) obj;
        if (fragment != this.mCurrentPrimaryItem) {
            if (this.mCurrentPrimaryItem != null) {
                this.mCurrentPrimaryItem.setMenuVisibility(false);
                this.mCurrentPrimaryItem.setUserVisibleHint(false);
            }
            fragment.setMenuVisibility(true);
            fragment.setUserVisibleHint(true);
            this.mCurrentPrimaryItem = fragment;
        }
    }

    public void finishUpdate(@NonNull ViewGroup viewGroup) {
        if (this.mCurTransaction != null) {
            this.mCurTransaction.commitNowAllowingStateLoss();
            this.mCurTransaction = null;
        }
    }

    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return ((Fragment) obj).getView() == view ? true : null;
    }

    private static String makeFragmentName(int i, long j) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("android:switcher:");
        stringBuilder.append(i);
        stringBuilder.append(":");
        stringBuilder.append(j);
        return stringBuilder.toString();
    }
}
