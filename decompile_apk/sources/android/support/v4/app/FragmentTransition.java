package android.support.v4.app;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class FragmentTransition {
    private static final int[] INVERSE_OPS = new int[]{0, 3, 0, 1, 5, 4, 7, 6, 9, 8};
    private static final FragmentTransitionImpl PLATFORM_IMPL = (VERSION.SDK_INT >= 21 ? new FragmentTransitionCompat21() : null);
    private static final FragmentTransitionImpl SUPPORT_IMPL = resolveSupportImpl();

    static class FragmentContainerTransition {
        public Fragment firstOut;
        public boolean firstOutIsPop;
        public BackStackRecord firstOutTransaction;
        public Fragment lastIn;
        public boolean lastInIsPop;
        public BackStackRecord lastInTransaction;

        FragmentContainerTransition() {
        }
    }

    private static android.support.v4.app.FragmentTransitionImpl resolveSupportImpl() {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1603198149.run(Unknown Source)
*/
        /*
        r0 = "android.support.transition.FragmentTransitionSupport";	 Catch:{ Exception -> 0x0016 }
        r0 = java.lang.Class.forName(r0);	 Catch:{ Exception -> 0x0016 }
        r1 = 0;	 Catch:{ Exception -> 0x0016 }
        r2 = new java.lang.Class[r1];	 Catch:{ Exception -> 0x0016 }
        r0 = r0.getDeclaredConstructor(r2);	 Catch:{ Exception -> 0x0016 }
        r1 = new java.lang.Object[r1];	 Catch:{ Exception -> 0x0016 }
        r0 = r0.newInstance(r1);	 Catch:{ Exception -> 0x0016 }
        r0 = (android.support.v4.app.FragmentTransitionImpl) r0;	 Catch:{ Exception -> 0x0016 }
        return r0;
    L_0x0016:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.FragmentTransition.resolveSupportImpl():android.support.v4.app.FragmentTransitionImpl");
    }

    static void startTransitions(FragmentManagerImpl fragmentManagerImpl, ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, int i, int i2, boolean z) {
        if (fragmentManagerImpl.mCurState >= 1) {
            SparseArray sparseArray = new SparseArray();
            for (int i3 = i; i3 < i2; i3++) {
                BackStackRecord backStackRecord = (BackStackRecord) arrayList.get(i3);
                if (((Boolean) arrayList2.get(i3)).booleanValue()) {
                    calculatePopFragments(backStackRecord, sparseArray, z);
                } else {
                    calculateFragments(backStackRecord, sparseArray, z);
                }
            }
            if (sparseArray.size() != 0) {
                View view = new View(fragmentManagerImpl.mHost.getContext());
                int size = sparseArray.size();
                for (int i4 = 0; i4 < size; i4++) {
                    int keyAt = sparseArray.keyAt(i4);
                    ArrayMap calculateNameOverrides = calculateNameOverrides(keyAt, arrayList, arrayList2, i, i2);
                    FragmentContainerTransition fragmentContainerTransition = (FragmentContainerTransition) sparseArray.valueAt(i4);
                    if (z) {
                        configureTransitionsReordered(fragmentManagerImpl, keyAt, fragmentContainerTransition, view, calculateNameOverrides);
                    } else {
                        configureTransitionsOrdered(fragmentManagerImpl, keyAt, fragmentContainerTransition, view, calculateNameOverrides);
                    }
                }
            }
        }
    }

    private static ArrayMap<String, String> calculateNameOverrides(int i, ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        ArrayMap<String, String> arrayMap = new ArrayMap();
        for (i3--; i3 >= i2; i3--) {
            BackStackRecord backStackRecord = (BackStackRecord) arrayList.get(i3);
            if (backStackRecord.interactsWith(i)) {
                boolean booleanValue = ((Boolean) arrayList2.get(i3)).booleanValue();
                if (backStackRecord.mSharedElementSourceNames != null) {
                    ArrayList arrayList3;
                    ArrayList arrayList4;
                    int size = backStackRecord.mSharedElementSourceNames.size();
                    if (booleanValue) {
                        arrayList3 = backStackRecord.mSharedElementSourceNames;
                        arrayList4 = backStackRecord.mSharedElementTargetNames;
                    } else {
                        ArrayList arrayList5 = backStackRecord.mSharedElementSourceNames;
                        arrayList3 = backStackRecord.mSharedElementTargetNames;
                        arrayList4 = arrayList5;
                    }
                    for (int i4 = 0; i4 < size; i4++) {
                        String str = (String) arrayList4.get(i4);
                        String str2 = (String) arrayList3.get(i4);
                        String str3 = (String) arrayMap.remove(str2);
                        if (str3 != null) {
                            arrayMap.put(str, str3);
                        } else {
                            arrayMap.put(str, str2);
                        }
                    }
                }
            }
        }
        return arrayMap;
    }

    private static void configureTransitionsReordered(FragmentManagerImpl fragmentManagerImpl, int i, FragmentContainerTransition fragmentContainerTransition, View view, ArrayMap<String, String> arrayMap) {
        FragmentManagerImpl fragmentManagerImpl2 = fragmentManagerImpl;
        FragmentContainerTransition fragmentContainerTransition2 = fragmentContainerTransition;
        View view2 = view;
        View view3 = fragmentManagerImpl2.mContainer.onHasView() ? (ViewGroup) fragmentManagerImpl2.mContainer.onFindViewById(i) : null;
        if (view3 != null) {
            Fragment fragment = fragmentContainerTransition2.lastIn;
            Fragment fragment2 = fragmentContainerTransition2.firstOut;
            FragmentTransitionImpl chooseImpl = chooseImpl(fragment2, fragment);
            if (chooseImpl != null) {
                boolean z = fragmentContainerTransition2.lastInIsPop;
                boolean z2 = fragmentContainerTransition2.firstOutIsPop;
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                Object enterTransition = getEnterTransition(chooseImpl, fragment, z);
                Object exitTransition = getExitTransition(chooseImpl, fragment2, z2);
                Object obj = enterTransition;
                View view4 = view3;
                ArrayList arrayList3 = arrayList2;
                Object configureSharedElementsReordered = configureSharedElementsReordered(chooseImpl, view3, view, arrayMap, fragmentContainerTransition, arrayList2, arrayList, enterTransition, exitTransition);
                Object obj2 = obj;
                if (obj2 == null && configureSharedElementsReordered == null) {
                    enterTransition = exitTransition;
                    if (enterTransition == null) {
                        return;
                    }
                }
                enterTransition = exitTransition;
                ArrayList configureEnteringExitingViews = configureEnteringExitingViews(chooseImpl, enterTransition, fragment2, arrayList3, view2);
                ArrayList configureEnteringExitingViews2 = configureEnteringExitingViews(chooseImpl, obj2, fragment, arrayList, view2);
                setViewVisibility(configureEnteringExitingViews2, 4);
                Fragment fragment3 = fragment;
                ArrayList arrayList4 = configureEnteringExitingViews;
                Object mergeTransitions = mergeTransitions(chooseImpl, obj2, enterTransition, configureSharedElementsReordered, fragment3, z);
                if (mergeTransitions != null) {
                    replaceHide(chooseImpl, enterTransition, fragment2, arrayList4);
                    ArrayList prepareSetNameOverridesReordered = chooseImpl.prepareSetNameOverridesReordered(arrayList);
                    chooseImpl.scheduleRemoveTargets(mergeTransitions, obj2, configureEnteringExitingViews2, enterTransition, arrayList4, configureSharedElementsReordered, arrayList);
                    View view5 = view4;
                    chooseImpl.beginDelayedTransition(view5, mergeTransitions);
                    chooseImpl.setNameOverridesReordered(view5, arrayList3, arrayList, prepareSetNameOverridesReordered, arrayMap);
                    setViewVisibility(configureEnteringExitingViews2, 0);
                    chooseImpl.swapSharedElementTargets(configureSharedElementsReordered, arrayList3, arrayList);
                }
            }
        }
    }

    private static void replaceHide(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Fragment fragment, final ArrayList<View> arrayList) {
        if (fragment != null && obj != null && fragment.mAdded && fragment.mHidden && fragment.mHiddenChanged) {
            fragment.setHideReplaced(true);
            fragmentTransitionImpl.scheduleHideFragmentView(obj, fragment.getView(), arrayList);
            OneShotPreDrawListener.add(fragment.mContainer, new Runnable() {
                public void run() {
                    FragmentTransition.setViewVisibility(arrayList, 4);
                }
            });
        }
    }

    private static void configureTransitionsOrdered(FragmentManagerImpl fragmentManagerImpl, int i, FragmentContainerTransition fragmentContainerTransition, View view, ArrayMap<String, String> arrayMap) {
        FragmentManagerImpl fragmentManagerImpl2 = fragmentManagerImpl;
        FragmentContainerTransition fragmentContainerTransition2 = fragmentContainerTransition;
        View view2 = view;
        ArrayMap<String, String> arrayMap2 = arrayMap;
        View view3 = fragmentManagerImpl2.mContainer.onHasView() ? (ViewGroup) fragmentManagerImpl2.mContainer.onFindViewById(i) : null;
        if (view3 != null) {
            Fragment fragment = fragmentContainerTransition2.lastIn;
            Fragment fragment2 = fragmentContainerTransition2.firstOut;
            FragmentTransitionImpl chooseImpl = chooseImpl(fragment2, fragment);
            if (chooseImpl != null) {
                Object obj;
                Object obj2;
                Object mergeTransitions;
                FragmentTransitionImpl fragmentTransitionImpl;
                ArrayList arrayList;
                boolean z = fragmentContainerTransition2.lastInIsPop;
                boolean z2 = fragmentContainerTransition2.firstOutIsPop;
                Object enterTransition = getEnterTransition(chooseImpl, fragment, z);
                Object exitTransition = getExitTransition(chooseImpl, fragment2, z2);
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                ArrayList arrayList4 = arrayList2;
                Object obj3 = exitTransition;
                Object obj4 = enterTransition;
                FragmentTransitionImpl fragmentTransitionImpl2 = chooseImpl;
                exitTransition = configureSharedElementsOrdered(chooseImpl, view3, view, arrayMap, fragmentContainerTransition, arrayList2, arrayList3, enterTransition, obj3);
                Object obj5 = obj4;
                if (obj5 == null && exitTransition == null) {
                    obj = obj3;
                    if (obj == null) {
                        return;
                    }
                }
                obj = obj3;
                ArrayList configureEnteringExitingViews = configureEnteringExitingViews(fragmentTransitionImpl2, obj, fragment2, arrayList4, view2);
                if (configureEnteringExitingViews != null) {
                    if (!configureEnteringExitingViews.isEmpty()) {
                        obj2 = obj;
                        fragmentTransitionImpl2.addTarget(obj5, view2);
                        mergeTransitions = mergeTransitions(fragmentTransitionImpl2, obj5, obj2, exitTransition, fragment, fragmentContainerTransition2.lastInIsPop);
                        if (mergeTransitions != null) {
                            arrayList4 = new ArrayList();
                            fragmentTransitionImpl = fragmentTransitionImpl2;
                            fragmentTransitionImpl.scheduleRemoveTargets(mergeTransitions, obj5, arrayList4, obj2, configureEnteringExitingViews, exitTransition, arrayList3);
                            scheduleTargetChange(fragmentTransitionImpl, view3, fragment, view, arrayList3, obj5, arrayList4, obj2, configureEnteringExitingViews);
                            arrayList = arrayList3;
                            fragmentTransitionImpl2.setNameOverridesOrdered(view3, arrayList, arrayMap2);
                            fragmentTransitionImpl2.beginDelayedTransition(view3, mergeTransitions);
                            fragmentTransitionImpl2.scheduleNameReset(view3, arrayList, arrayMap2);
                        }
                    }
                }
                obj2 = null;
                fragmentTransitionImpl2.addTarget(obj5, view2);
                mergeTransitions = mergeTransitions(fragmentTransitionImpl2, obj5, obj2, exitTransition, fragment, fragmentContainerTransition2.lastInIsPop);
                if (mergeTransitions != null) {
                    arrayList4 = new ArrayList();
                    fragmentTransitionImpl = fragmentTransitionImpl2;
                    fragmentTransitionImpl.scheduleRemoveTargets(mergeTransitions, obj5, arrayList4, obj2, configureEnteringExitingViews, exitTransition, arrayList3);
                    scheduleTargetChange(fragmentTransitionImpl, view3, fragment, view, arrayList3, obj5, arrayList4, obj2, configureEnteringExitingViews);
                    arrayList = arrayList3;
                    fragmentTransitionImpl2.setNameOverridesOrdered(view3, arrayList, arrayMap2);
                    fragmentTransitionImpl2.beginDelayedTransition(view3, mergeTransitions);
                    fragmentTransitionImpl2.scheduleNameReset(view3, arrayList, arrayMap2);
                }
            }
        }
    }

    private static void scheduleTargetChange(FragmentTransitionImpl fragmentTransitionImpl, ViewGroup viewGroup, Fragment fragment, View view, ArrayList<View> arrayList, Object obj, ArrayList<View> arrayList2, Object obj2, ArrayList<View> arrayList3) {
        final Object obj3 = obj;
        final FragmentTransitionImpl fragmentTransitionImpl2 = fragmentTransitionImpl;
        final View view2 = view;
        final Fragment fragment2 = fragment;
        final ArrayList<View> arrayList4 = arrayList;
        final ArrayList<View> arrayList5 = arrayList2;
        final ArrayList<View> arrayList6 = arrayList3;
        final Object obj4 = obj2;
        Runnable c00612 = new Runnable() {
            public void run() {
                if (obj3 != null) {
                    fragmentTransitionImpl2.removeTarget(obj3, view2);
                    arrayList5.addAll(FragmentTransition.configureEnteringExitingViews(fragmentTransitionImpl2, obj3, fragment2, arrayList4, view2));
                }
                if (arrayList6 != null) {
                    if (obj4 != null) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(view2);
                        fragmentTransitionImpl2.replaceTargets(obj4, arrayList6, arrayList);
                    }
                    arrayList6.clear();
                    arrayList6.add(view2);
                }
            }
        };
        ViewGroup viewGroup2 = viewGroup;
        OneShotPreDrawListener.add(viewGroup, c00612);
    }

    private static FragmentTransitionImpl chooseImpl(Fragment fragment, Fragment fragment2) {
        List arrayList = new ArrayList();
        if (fragment != null) {
            Object exitTransition = fragment.getExitTransition();
            if (exitTransition != null) {
                arrayList.add(exitTransition);
            }
            exitTransition = fragment.getReturnTransition();
            if (exitTransition != null) {
                arrayList.add(exitTransition);
            }
            fragment = fragment.getSharedElementReturnTransition();
            if (fragment != null) {
                arrayList.add(fragment);
            }
        }
        if (fragment2 != null) {
            fragment = fragment2.getEnterTransition();
            if (fragment != null) {
                arrayList.add(fragment);
            }
            fragment = fragment2.getReenterTransition();
            if (fragment != null) {
                arrayList.add(fragment);
            }
            fragment = fragment2.getSharedElementEnterTransition();
            if (fragment != null) {
                arrayList.add(fragment);
            }
        }
        if (arrayList.isEmpty() != null) {
            return null;
        }
        if (PLATFORM_IMPL != null && canHandleAll(PLATFORM_IMPL, arrayList) != null) {
            return PLATFORM_IMPL;
        }
        if (SUPPORT_IMPL != null && canHandleAll(SUPPORT_IMPL, arrayList) != null) {
            return SUPPORT_IMPL;
        }
        if (PLATFORM_IMPL == null && SUPPORT_IMPL == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    private static boolean canHandleAll(FragmentTransitionImpl fragmentTransitionImpl, List<Object> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!fragmentTransitionImpl.canHandle(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static Object getSharedElementTransition(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, Fragment fragment2, boolean z) {
        if (fragment != null) {
            if (fragment2 != null) {
                if (z) {
                    fragment = fragment2.getSharedElementReturnTransition();
                } else {
                    fragment = fragment.getSharedElementEnterTransition();
                }
                return fragmentTransitionImpl.wrapTransitionInSet(fragmentTransitionImpl.cloneTransition(fragment));
            }
        }
        return null;
    }

    private static Object getEnterTransition(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        if (z) {
            fragment = fragment.getReenterTransition();
        } else {
            fragment = fragment.getEnterTransition();
        }
        return fragmentTransitionImpl.cloneTransition(fragment);
    }

    private static Object getExitTransition(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        if (z) {
            fragment = fragment.getReturnTransition();
        } else {
            fragment = fragment.getExitTransition();
        }
        return fragmentTransitionImpl.cloneTransition(fragment);
    }

    private static Object configureSharedElementsReordered(FragmentTransitionImpl fragmentTransitionImpl, ViewGroup viewGroup, View view, ArrayMap<String, String> arrayMap, FragmentContainerTransition fragmentContainerTransition, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        FragmentTransitionImpl fragmentTransitionImpl2 = fragmentTransitionImpl;
        View view2 = view;
        ArrayMap<String, String> arrayMap2 = arrayMap;
        FragmentContainerTransition fragmentContainerTransition2 = fragmentContainerTransition;
        ArrayList<View> arrayList3 = arrayList;
        ArrayList<View> arrayList4 = arrayList2;
        Object obj3 = obj;
        Fragment fragment = fragmentContainerTransition2.lastIn;
        Fragment fragment2 = fragmentContainerTransition2.firstOut;
        if (fragment != null) {
            fragment.getView().setVisibility(0);
        }
        if (fragment != null) {
            if (fragment2 != null) {
                Object obj4;
                Object obj5;
                boolean z = fragmentContainerTransition2.lastInIsPop;
                if (arrayMap.isEmpty()) {
                    obj4 = null;
                } else {
                    obj4 = getSharedElementTransition(fragmentTransitionImpl2, fragment, fragment2, z);
                }
                ArrayMap captureOutSharedElements = captureOutSharedElements(fragmentTransitionImpl2, arrayMap2, obj4, fragmentContainerTransition2);
                ArrayMap captureInSharedElements = captureInSharedElements(fragmentTransitionImpl2, arrayMap2, obj4, fragmentContainerTransition2);
                if (arrayMap.isEmpty()) {
                    if (captureOutSharedElements != null) {
                        captureOutSharedElements.clear();
                    }
                    if (captureInSharedElements != null) {
                        captureInSharedElements.clear();
                    }
                    obj5 = null;
                } else {
                    addSharedElementsWithMatchingNames(arrayList3, captureOutSharedElements, arrayMap.keySet());
                    addSharedElementsWithMatchingNames(arrayList4, captureInSharedElements, arrayMap.values());
                    obj5 = obj4;
                }
                if (obj3 == null && obj2 == null && obj5 == null) {
                    return null;
                }
                Rect rect;
                View view3;
                callSharedElementStartEnd(fragment, fragment2, z, captureOutSharedElements, true);
                if (obj5 != null) {
                    arrayList4.add(view2);
                    fragmentTransitionImpl2.setSharedElementTargets(obj5, view2, arrayList3);
                    setOutEpicenter(fragmentTransitionImpl, obj5, obj2, captureOutSharedElements, fragmentContainerTransition2.firstOutIsPop, fragmentContainerTransition2.firstOutTransaction);
                    Rect rect2 = new Rect();
                    View inEpicenterView = getInEpicenterView(captureInSharedElements, fragmentContainerTransition2, obj3, z);
                    if (inEpicenterView != null) {
                        fragmentTransitionImpl2.setEpicenter(obj3, rect2);
                    }
                    rect = rect2;
                    view3 = inEpicenterView;
                } else {
                    view3 = null;
                    rect = view3;
                }
                final Fragment fragment3 = fragment;
                final Fragment fragment4 = fragment2;
                final boolean z2 = z;
                final ArrayMap arrayMap3 = captureInSharedElements;
                fragmentTransitionImpl2 = fragmentTransitionImpl;
                OneShotPreDrawListener.add(viewGroup, new Runnable() {
                    public void run() {
                        FragmentTransition.callSharedElementStartEnd(fragment3, fragment4, z2, arrayMap3, false);
                        if (view3 != null) {
                            fragmentTransitionImpl2.getBoundsOnScreen(view3, rect);
                        }
                    }
                });
                return obj5;
            }
        }
        return null;
    }

    private static void addSharedElementsWithMatchingNames(ArrayList<View> arrayList, ArrayMap<String, View> arrayMap, Collection<String> collection) {
        for (int size = arrayMap.size() - 1; size >= 0; size--) {
            View view = (View) arrayMap.valueAt(size);
            if (collection.contains(ViewCompat.getTransitionName(view))) {
                arrayList.add(view);
            }
        }
    }

    private static Object configureSharedElementsOrdered(FragmentTransitionImpl fragmentTransitionImpl, ViewGroup viewGroup, View view, ArrayMap<String, String> arrayMap, FragmentContainerTransition fragmentContainerTransition, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        FragmentTransitionImpl fragmentTransitionImpl2 = fragmentTransitionImpl;
        FragmentContainerTransition fragmentContainerTransition2 = fragmentContainerTransition;
        ArrayList<View> arrayList3 = arrayList;
        Object obj3 = obj;
        Fragment fragment = fragmentContainerTransition2.lastIn;
        Fragment fragment2 = fragmentContainerTransition2.firstOut;
        if (fragment != null) {
            if (fragment2 != null) {
                ArrayMap arrayMap2;
                Object obj4;
                Object obj5;
                boolean z = fragmentContainerTransition2.lastInIsPop;
                if (arrayMap.isEmpty()) {
                    arrayMap2 = arrayMap;
                    obj4 = null;
                } else {
                    obj4 = getSharedElementTransition(fragmentTransitionImpl2, fragment, fragment2, z);
                    arrayMap2 = arrayMap;
                }
                ArrayMap captureOutSharedElements = captureOutSharedElements(fragmentTransitionImpl2, arrayMap2, obj4, fragmentContainerTransition2);
                if (arrayMap.isEmpty()) {
                    obj5 = null;
                } else {
                    arrayList3.addAll(captureOutSharedElements.values());
                    obj5 = obj4;
                }
                if (obj3 == null && obj2 == null && obj5 == null) {
                    return null;
                }
                Rect rect;
                callSharedElementStartEnd(fragment, fragment2, z, captureOutSharedElements, true);
                if (obj5 != null) {
                    rect = new Rect();
                    fragmentTransitionImpl2.setSharedElementTargets(obj5, view, arrayList3);
                    setOutEpicenter(fragmentTransitionImpl, obj5, obj2, captureOutSharedElements, fragmentContainerTransition2.firstOutIsPop, fragmentContainerTransition2.firstOutTransaction);
                    if (obj3 != null) {
                        fragmentTransitionImpl2.setEpicenter(obj3, rect);
                    }
                } else {
                    rect = null;
                }
                final FragmentTransitionImpl fragmentTransitionImpl3 = fragmentTransitionImpl;
                final ArrayMap<String, String> arrayMap3 = arrayMap;
                final Object obj6 = obj5;
                final FragmentContainerTransition fragmentContainerTransition3 = fragmentContainerTransition;
                C00634 c00634 = r0;
                final ArrayList<View> arrayList4 = arrayList2;
                final View view2 = view;
                final Fragment fragment3 = fragment;
                fragment = fragment2;
                final boolean z2 = z;
                arrayList3 = arrayList;
                obj3 = obj;
                final Rect rect2 = rect;
                C00634 c006342 = new Runnable() {
                    public void run() {
                        ArrayMap captureInSharedElements = FragmentTransition.captureInSharedElements(fragmentTransitionImpl3, arrayMap3, obj6, fragmentContainerTransition3);
                        if (captureInSharedElements != null) {
                            arrayList4.addAll(captureInSharedElements.values());
                            arrayList4.add(view2);
                        }
                        FragmentTransition.callSharedElementStartEnd(fragment3, fragment, z2, captureInSharedElements, false);
                        if (obj6 != null) {
                            fragmentTransitionImpl3.swapSharedElementTargets(obj6, arrayList3, arrayList4);
                            View inEpicenterView = FragmentTransition.getInEpicenterView(captureInSharedElements, fragmentContainerTransition3, obj3, z2);
                            if (inEpicenterView != null) {
                                fragmentTransitionImpl3.getBoundsOnScreen(inEpicenterView, rect2);
                            }
                        }
                    }
                };
                OneShotPreDrawListener.add(viewGroup, c00634);
                return obj5;
            }
        }
        return null;
    }

    private static ArrayMap<String, View> captureOutSharedElements(FragmentTransitionImpl fragmentTransitionImpl, ArrayMap<String, String> arrayMap, Object obj, FragmentContainerTransition fragmentContainerTransition) {
        if (!arrayMap.isEmpty()) {
            if (obj != null) {
                obj = fragmentContainerTransition.firstOut;
                ArrayMap<String, View> arrayMap2 = new ArrayMap();
                fragmentTransitionImpl.findNamedViews(arrayMap2, obj.getView());
                fragmentTransitionImpl = fragmentContainerTransition.firstOutTransaction;
                if (fragmentContainerTransition.firstOutIsPop != null) {
                    obj = obj.getEnterTransitionCallback();
                    fragmentTransitionImpl = fragmentTransitionImpl.mSharedElementTargetNames;
                } else {
                    obj = obj.getExitTransitionCallback();
                    fragmentTransitionImpl = fragmentTransitionImpl.mSharedElementSourceNames;
                }
                arrayMap2.retainAll(fragmentTransitionImpl);
                if (obj != null) {
                    obj.onMapSharedElements(fragmentTransitionImpl, arrayMap2);
                    for (obj = fragmentTransitionImpl.size() - 1; obj >= null; obj--) {
                        String str = (String) fragmentTransitionImpl.get(obj);
                        View view = (View) arrayMap2.get(str);
                        if (view == null) {
                            arrayMap.remove(str);
                        } else if (!str.equals(ViewCompat.getTransitionName(view))) {
                            arrayMap.put(ViewCompat.getTransitionName(view), (String) arrayMap.remove(str));
                        }
                    }
                } else {
                    arrayMap.retainAll(arrayMap2.keySet());
                }
                return arrayMap2;
            }
        }
        arrayMap.clear();
        return null;
    }

    static ArrayMap<String, View> captureInSharedElements(FragmentTransitionImpl fragmentTransitionImpl, ArrayMap<String, String> arrayMap, Object obj, FragmentContainerTransition fragmentContainerTransition) {
        Fragment fragment = fragmentContainerTransition.lastIn;
        View view = fragment.getView();
        if (!(arrayMap.isEmpty() || obj == null)) {
            if (view != null) {
                obj = new ArrayMap();
                fragmentTransitionImpl.findNamedViews(obj, view);
                fragmentTransitionImpl = fragmentContainerTransition.lastInTransaction;
                if (fragmentContainerTransition.lastInIsPop != null) {
                    fragmentContainerTransition = fragment.getExitTransitionCallback();
                    fragmentTransitionImpl = fragmentTransitionImpl.mSharedElementSourceNames;
                } else {
                    fragmentContainerTransition = fragment.getEnterTransitionCallback();
                    fragmentTransitionImpl = fragmentTransitionImpl.mSharedElementTargetNames;
                }
                if (fragmentTransitionImpl != null) {
                    obj.retainAll(fragmentTransitionImpl);
                    obj.retainAll(arrayMap.values());
                }
                if (fragmentContainerTransition != null) {
                    fragmentContainerTransition.onMapSharedElements(fragmentTransitionImpl, obj);
                    for (fragmentContainerTransition = fragmentTransitionImpl.size() - 1; fragmentContainerTransition >= null; fragmentContainerTransition--) {
                        String str = (String) fragmentTransitionImpl.get(fragmentContainerTransition);
                        view = (View) obj.get(str);
                        if (view == null) {
                            str = findKeyForValue(arrayMap, str);
                            if (str != null) {
                                arrayMap.remove(str);
                            }
                        } else if (!str.equals(ViewCompat.getTransitionName(view))) {
                            str = findKeyForValue(arrayMap, str);
                            if (str != null) {
                                arrayMap.put(str, ViewCompat.getTransitionName(view));
                            }
                        }
                    }
                } else {
                    retainValues(arrayMap, obj);
                }
                return obj;
            }
        }
        arrayMap.clear();
        return null;
    }

    private static String findKeyForValue(ArrayMap<String, String> arrayMap, String str) {
        int size = arrayMap.size();
        for (int i = 0; i < size; i++) {
            if (str.equals(arrayMap.valueAt(i))) {
                return (String) arrayMap.keyAt(i);
            }
        }
        return null;
    }

    static View getInEpicenterView(ArrayMap<String, View> arrayMap, FragmentContainerTransition fragmentContainerTransition, Object obj, boolean z) {
        fragmentContainerTransition = fragmentContainerTransition.lastInTransaction;
        if (obj == null || arrayMap == null || fragmentContainerTransition.mSharedElementSourceNames == null || fragmentContainerTransition.mSharedElementSourceNames.isEmpty() != null) {
            return null;
        }
        if (z) {
            fragmentContainerTransition = (String) fragmentContainerTransition.mSharedElementSourceNames.get(0);
        } else {
            fragmentContainerTransition = (String) fragmentContainerTransition.mSharedElementTargetNames.get(0);
        }
        return (View) arrayMap.get(fragmentContainerTransition);
    }

    private static void setOutEpicenter(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Object obj2, ArrayMap<String, View> arrayMap, boolean z, BackStackRecord backStackRecord) {
        if (backStackRecord.mSharedElementSourceNames != null && !backStackRecord.mSharedElementSourceNames.isEmpty()) {
            if (z) {
                z = (String) backStackRecord.mSharedElementTargetNames.get(0);
            } else {
                z = (String) backStackRecord.mSharedElementSourceNames.get(0);
            }
            View view = (View) arrayMap.get(z);
            fragmentTransitionImpl.setEpicenter(obj, view);
            if (obj2 != null) {
                fragmentTransitionImpl.setEpicenter(obj2, view);
            }
        }
    }

    private static void retainValues(ArrayMap<String, String> arrayMap, ArrayMap<String, View> arrayMap2) {
        for (int size = arrayMap.size() - 1; size >= 0; size--) {
            if (!arrayMap2.containsKey((String) arrayMap.valueAt(size))) {
                arrayMap.removeAt(size);
            }
        }
    }

    static void callSharedElementStartEnd(Fragment fragment, Fragment fragment2, boolean z, ArrayMap<String, View> arrayMap, boolean z2) {
        if (z) {
            fragment = fragment2.getEnterTransitionCallback();
        } else {
            fragment = fragment.getEnterTransitionCallback();
        }
        if (fragment != null) {
            int i;
            fragment2 = new ArrayList();
            z = new ArrayList();
            int i2 = 0;
            if (arrayMap == null) {
                i = 0;
            } else {
                i = arrayMap.size();
            }
            while (i2 < i) {
                z.add(arrayMap.keyAt(i2));
                fragment2.add(arrayMap.valueAt(i2));
                i2++;
            }
            if (z2) {
                fragment.onSharedElementStart(z, fragment2, null);
            } else {
                fragment.onSharedElementEnd(z, fragment2, null);
            }
        }
    }

    static ArrayList<View> configureEnteringExitingViews(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList();
        fragment = fragment.getView();
        if (fragment != null) {
            fragmentTransitionImpl.captureTransitioningViews(arrayList2, fragment);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (arrayList2.isEmpty() != null) {
            return arrayList2;
        }
        arrayList2.add(view);
        fragmentTransitionImpl.addTargets(obj, arrayList2);
        return arrayList2;
    }

    static void setViewVisibility(ArrayList<View> arrayList, int i) {
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((View) arrayList.get(size)).setVisibility(i);
            }
        }
    }

    private static Object mergeTransitions(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z) {
        fragment = (obj == null || obj2 == null || fragment == null) ? true : z ? fragment.getAllowReturnTransitionOverlap() : fragment.getAllowEnterTransitionOverlap();
        if (fragment != null) {
            return fragmentTransitionImpl.mergeTransitionsTogether(obj2, obj, obj3);
        }
        return fragmentTransitionImpl.mergeTransitionsInSequence(obj2, obj, obj3);
    }

    public static void calculateFragments(BackStackRecord backStackRecord, SparseArray<FragmentContainerTransition> sparseArray, boolean z) {
        int size = backStackRecord.mOps.size();
        for (int i = 0; i < size; i++) {
            addToFirstInLastOut(backStackRecord, (Op) backStackRecord.mOps.get(i), sparseArray, false, z);
        }
    }

    public static void calculatePopFragments(BackStackRecord backStackRecord, SparseArray<FragmentContainerTransition> sparseArray, boolean z) {
        if (backStackRecord.mManager.mContainer.onHasView()) {
            for (int size = backStackRecord.mOps.size() - 1; size >= 0; size--) {
                addToFirstInLastOut(backStackRecord, (Op) backStackRecord.mOps.get(size), sparseArray, true, z);
            }
        }
    }

    static boolean supportsTransition() {
        if (PLATFORM_IMPL == null) {
            if (SUPPORT_IMPL == null) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void addToFirstInLastOut(android.support.v4.app.BackStackRecord r15, android.support.v4.app.BackStackRecord.Op r16, android.util.SparseArray<android.support.v4.app.FragmentTransition.FragmentContainerTransition> r17, boolean r18, boolean r19) {
        /*
        r0 = r15;
        r1 = r16;
        r2 = r17;
        r3 = r18;
        r10 = r1.fragment;
        if (r10 != 0) goto L_0x000c;
    L_0x000b:
        return;
    L_0x000c:
        r11 = r10.mContainerId;
        if (r11 != 0) goto L_0x0011;
    L_0x0010:
        return;
    L_0x0011:
        if (r3 == 0) goto L_0x001a;
    L_0x0013:
        r4 = INVERSE_OPS;
        r1 = r1.cmd;
        r1 = r4[r1];
        goto L_0x001c;
    L_0x001a:
        r1 = r1.cmd;
    L_0x001c:
        r4 = 0;
        r5 = 1;
        if (r1 == r5) goto L_0x007c;
    L_0x0020:
        switch(r1) {
            case 3: goto L_0x0052;
            case 4: goto L_0x003a;
            case 5: goto L_0x0028;
            case 6: goto L_0x0052;
            case 7: goto L_0x007c;
            default: goto L_0x0023;
        };
    L_0x0023:
        r1 = 0;
    L_0x0024:
        r12 = 0;
        r13 = 0;
        goto L_0x008f;
    L_0x0028:
        if (r19 == 0) goto L_0x0037;
    L_0x002a:
        r1 = r10.mHiddenChanged;
        if (r1 == 0) goto L_0x008b;
    L_0x002e:
        r1 = r10.mHidden;
        if (r1 != 0) goto L_0x008b;
    L_0x0032:
        r1 = r10.mAdded;
        if (r1 == 0) goto L_0x008b;
    L_0x0036:
        goto L_0x0089;
    L_0x0037:
        r1 = r10.mHidden;
        goto L_0x008c;
    L_0x003a:
        if (r19 == 0) goto L_0x0049;
    L_0x003c:
        r1 = r10.mHiddenChanged;
        if (r1 == 0) goto L_0x006d;
    L_0x0040:
        r1 = r10.mAdded;
        if (r1 == 0) goto L_0x006d;
    L_0x0044:
        r1 = r10.mHidden;
        if (r1 == 0) goto L_0x006d;
    L_0x0048:
        goto L_0x006b;
    L_0x0049:
        r1 = r10.mAdded;
        if (r1 == 0) goto L_0x006d;
    L_0x004d:
        r1 = r10.mHidden;
        if (r1 != 0) goto L_0x006d;
    L_0x0051:
        goto L_0x0048;
    L_0x0052:
        if (r19 == 0) goto L_0x006f;
    L_0x0054:
        r1 = r10.mAdded;
        if (r1 != 0) goto L_0x006d;
    L_0x0058:
        r1 = r10.mView;
        if (r1 == 0) goto L_0x006d;
    L_0x005c:
        r1 = r10.mView;
        r1 = r1.getVisibility();
        if (r1 != 0) goto L_0x006d;
    L_0x0064:
        r1 = r10.mPostponedAlpha;
        r6 = 0;
        r1 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1));
        if (r1 < 0) goto L_0x006d;
    L_0x006b:
        r1 = 1;
        goto L_0x0078;
    L_0x006d:
        r1 = 0;
        goto L_0x0078;
    L_0x006f:
        r1 = r10.mAdded;
        if (r1 == 0) goto L_0x006d;
    L_0x0073:
        r1 = r10.mHidden;
        if (r1 != 0) goto L_0x006d;
    L_0x0077:
        goto L_0x006b;
    L_0x0078:
        r13 = r1;
        r1 = 0;
        r12 = 1;
        goto L_0x008f;
    L_0x007c:
        if (r19 == 0) goto L_0x0081;
    L_0x007e:
        r1 = r10.mIsNewlyAdded;
        goto L_0x008c;
    L_0x0081:
        r1 = r10.mAdded;
        if (r1 != 0) goto L_0x008b;
    L_0x0085:
        r1 = r10.mHidden;
        if (r1 != 0) goto L_0x008b;
    L_0x0089:
        r1 = 1;
        goto L_0x008c;
    L_0x008b:
        r1 = 0;
    L_0x008c:
        r4 = r1;
        r1 = 1;
        goto L_0x0024;
    L_0x008f:
        r6 = r2.get(r11);
        r6 = (android.support.v4.app.FragmentTransition.FragmentContainerTransition) r6;
        if (r4 == 0) goto L_0x00a1;
    L_0x0097:
        r6 = ensureContainer(r6, r2, r11);
        r6.lastIn = r10;
        r6.lastInIsPop = r3;
        r6.lastInTransaction = r0;
    L_0x00a1:
        r14 = r6;
        r9 = 0;
        if (r19 != 0) goto L_0x00c9;
    L_0x00a5:
        if (r1 == 0) goto L_0x00c9;
    L_0x00a7:
        if (r14 == 0) goto L_0x00af;
    L_0x00a9:
        r1 = r14.firstOut;
        if (r1 != r10) goto L_0x00af;
    L_0x00ad:
        r14.firstOut = r9;
    L_0x00af:
        r4 = r0.mManager;
        r1 = r10.mState;
        if (r1 >= r5) goto L_0x00c9;
    L_0x00b5:
        r1 = r4.mCurState;
        if (r1 < r5) goto L_0x00c9;
    L_0x00b9:
        r1 = r0.mReorderingAllowed;
        if (r1 != 0) goto L_0x00c9;
    L_0x00bd:
        r4.makeActive(r10);
        r6 = 1;
        r7 = 0;
        r8 = 0;
        r1 = 0;
        r5 = r10;
        r9 = r1;
        r4.moveToState(r5, r6, r7, r8, r9);
    L_0x00c9:
        if (r13 == 0) goto L_0x00db;
    L_0x00cb:
        if (r14 == 0) goto L_0x00d1;
    L_0x00cd:
        r1 = r14.firstOut;
        if (r1 != 0) goto L_0x00db;
    L_0x00d1:
        r14 = ensureContainer(r14, r2, r11);
        r14.firstOut = r10;
        r14.firstOutIsPop = r3;
        r14.firstOutTransaction = r0;
    L_0x00db:
        if (r19 != 0) goto L_0x00e8;
    L_0x00dd:
        if (r12 == 0) goto L_0x00e8;
    L_0x00df:
        if (r14 == 0) goto L_0x00e8;
    L_0x00e1:
        r0 = r14.lastIn;
        if (r0 != r10) goto L_0x00e8;
    L_0x00e5:
        r0 = 0;
        r14.lastIn = r0;
    L_0x00e8:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.FragmentTransition.addToFirstInLastOut(android.support.v4.app.BackStackRecord, android.support.v4.app.BackStackRecord$Op, android.util.SparseArray, boolean, boolean):void");
    }

    private static FragmentContainerTransition ensureContainer(FragmentContainerTransition fragmentContainerTransition, SparseArray<FragmentContainerTransition> sparseArray, int i) {
        if (fragmentContainerTransition != null) {
            return fragmentContainerTransition;
        }
        fragmentContainerTransition = new FragmentContainerTransition();
        sparseArray.put(i, fragmentContainerTransition);
        return fragmentContainerTransition;
    }

    private FragmentTransition() {
    }
}
