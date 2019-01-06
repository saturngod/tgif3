package android.support.v4.app;

import android.graphics.Rect;
import android.support.annotation.RequiresApi;
import android.transition.Transition;
import android.transition.Transition.EpicenterCallback;
import android.transition.Transition.TransitionListener;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(21)
class FragmentTransitionCompat21 extends FragmentTransitionImpl {
    FragmentTransitionCompat21() {
    }

    public boolean canHandle(Object obj) {
        return obj instanceof Transition;
    }

    public Object cloneTransition(Object obj) {
        return obj != null ? ((Transition) obj).clone() : null;
    }

    public Object wrapTransitionInSet(Object obj) {
        if (obj == null) {
            return null;
        }
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition((Transition) obj);
        return transitionSet;
    }

    public void setSharedElementTargets(Object obj, View view, ArrayList<View> arrayList) {
        TransitionSet transitionSet = (TransitionSet) obj;
        List targets = transitionSet.getTargets();
        targets.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            FragmentTransitionImpl.bfsAddViewChildren(targets, (View) arrayList.get(i));
        }
        targets.add(view);
        arrayList.add(view);
        addTargets(transitionSet, arrayList);
    }

    public void setEpicenter(Object obj, View view) {
        if (view != null) {
            Transition transition = (Transition) obj;
            final Rect rect = new Rect();
            getBoundsOnScreen(view, rect);
            transition.setEpicenterCallback(new EpicenterCallback() {
                public Rect onGetEpicenter(Transition transition) {
                    return rect;
                }
            });
        }
    }

    public void addTargets(Object obj, ArrayList<View> arrayList) {
        Transition transition = (Transition) obj;
        if (transition != null) {
            int i = 0;
            int transitionCount;
            if (transition instanceof TransitionSet) {
                TransitionSet transitionSet = (TransitionSet) transition;
                transitionCount = transitionSet.getTransitionCount();
                while (i < transitionCount) {
                    addTargets(transitionSet.getTransitionAt(i), arrayList);
                    i++;
                }
            } else if (!hasSimpleTarget(transition) && FragmentTransitionImpl.isNullOrEmpty(transition.getTargets())) {
                transitionCount = arrayList.size();
                while (i < transitionCount) {
                    transition.addTarget((View) arrayList.get(i));
                    i++;
                }
            }
        }
    }

    private static boolean hasSimpleTarget(Transition transition) {
        if (FragmentTransitionImpl.isNullOrEmpty(transition.getTargetIds()) && FragmentTransitionImpl.isNullOrEmpty(transition.getTargetNames())) {
            if (FragmentTransitionImpl.isNullOrEmpty(transition.getTargetTypes()) != null) {
                return null;
            }
        }
        return true;
    }

    public Object mergeTransitionsTogether(Object obj, Object obj2, Object obj3) {
        TransitionSet transitionSet = new TransitionSet();
        if (obj != null) {
            transitionSet.addTransition((Transition) obj);
        }
        if (obj2 != null) {
            transitionSet.addTransition((Transition) obj2);
        }
        if (obj3 != null) {
            transitionSet.addTransition((Transition) obj3);
        }
        return transitionSet;
    }

    public void scheduleHideFragmentView(Object obj, final View view, final ArrayList<View> arrayList) {
        ((Transition) obj).addListener(new TransitionListener() {
            public void onTransitionCancel(Transition transition) {
            }

            public void onTransitionPause(Transition transition) {
            }

            public void onTransitionResume(Transition transition) {
            }

            public void onTransitionStart(Transition transition) {
            }

            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                view.setVisibility(8);
                transition = arrayList.size();
                for (int i = 0; i < transition; i++) {
                    ((View) arrayList.get(i)).setVisibility(0);
                }
            }
        });
    }

    public Object mergeTransitionsInSequence(Object obj, Object obj2, Object obj3) {
        obj = (Transition) obj;
        Transition transition = (Transition) obj2;
        Transition transition2 = (Transition) obj3;
        if (obj != null && transition != null) {
            obj = new TransitionSet().addTransition(obj).addTransition(transition).setOrdering(1);
        } else if (obj == null) {
            obj = transition != null ? transition : null;
        }
        if (transition2 == null) {
            return obj;
        }
        obj2 = new TransitionSet();
        if (obj != null) {
            obj2.addTransition(obj);
        }
        obj2.addTransition(transition2);
        return obj2;
    }

    public void beginDelayedTransition(ViewGroup viewGroup, Object obj) {
        TransitionManager.beginDelayedTransition(viewGroup, (Transition) obj);
    }

    public void scheduleRemoveTargets(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3) {
        final Object obj5 = obj2;
        final ArrayList<View> arrayList4 = arrayList;
        final Object obj6 = obj3;
        final ArrayList<View> arrayList5 = arrayList2;
        final Object obj7 = obj4;
        final ArrayList<View> arrayList6 = arrayList3;
        ((Transition) obj).addListener(new TransitionListener() {
            public void onTransitionCancel(Transition transition) {
            }

            public void onTransitionEnd(Transition transition) {
            }

            public void onTransitionPause(Transition transition) {
            }

            public void onTransitionResume(Transition transition) {
            }

            public void onTransitionStart(Transition transition) {
                if (obj5 != null) {
                    FragmentTransitionCompat21.this.replaceTargets(obj5, arrayList4, null);
                }
                if (obj6 != null) {
                    FragmentTransitionCompat21.this.replaceTargets(obj6, arrayList5, null);
                }
                if (obj7 != null) {
                    FragmentTransitionCompat21.this.replaceTargets(obj7, arrayList6, null);
                }
            }
        });
    }

    public void swapSharedElementTargets(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        TransitionSet transitionSet = (TransitionSet) obj;
        if (transitionSet != null) {
            transitionSet.getTargets().clear();
            transitionSet.getTargets().addAll(arrayList2);
            replaceTargets(transitionSet, arrayList, arrayList2);
        }
    }

    public void replaceTargets(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        Transition transition = (Transition) obj;
        int i = 0;
        int transitionCount;
        if (transition instanceof TransitionSet) {
            TransitionSet transitionSet = (TransitionSet) transition;
            transitionCount = transitionSet.getTransitionCount();
            while (i < transitionCount) {
                replaceTargets(transitionSet.getTransitionAt(i), arrayList, arrayList2);
                i++;
            }
        } else if (!hasSimpleTarget(transition)) {
            List targets = transition.getTargets();
            if (targets != null && targets.size() == arrayList.size() && targets.containsAll(arrayList)) {
                if (arrayList2 == null) {
                    transitionCount = 0;
                } else {
                    transitionCount = arrayList2.size();
                }
                while (i < transitionCount) {
                    transition.addTarget((View) arrayList2.get(i));
                    i++;
                }
                for (arrayList2 = arrayList.size() - 1; arrayList2 >= null; arrayList2--) {
                    transition.removeTarget((View) arrayList.get(arrayList2));
                }
            }
        }
    }

    public void addTarget(Object obj, View view) {
        if (obj != null) {
            ((Transition) obj).addTarget(view);
        }
    }

    public void removeTarget(Object obj, View view) {
        if (obj != null) {
            ((Transition) obj).removeTarget(view);
        }
    }

    public void setEpicenter(Object obj, final Rect rect) {
        if (obj != null) {
            ((Transition) obj).setEpicenterCallback(new EpicenterCallback() {
                public Rect onGetEpicenter(Transition transition) {
                    if (rect != null) {
                        if (rect.isEmpty() == null) {
                            return rect;
                        }
                    }
                    return null;
                }
            });
        }
    }
}
