package com.mariorossi.githubstargazer.features.shared.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.mariorossi.githubstargazer.R;

public abstract class BaseAppActivity extends AppCompatActivity {

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    protected void log(String message) {
        Log.d(getClass().getSimpleName(), message);
    }

    /**
     * Convenience for calling {@link #setFragment(Fragment, int, boolean)}.
     * con int <code>R.id.container</code> e boolean <code>false</code>
     *
     * @param frag Fragment
     */
    protected void setFragment(Fragment frag) {
        setFragment(frag, R.id.container, false);
    }

    protected void addFragment(Fragment frag) {
        addFragment(frag, R.id.container, false);
    }

    protected void setFragment(Fragment frag, boolean addBack) {
        setFragment(frag, R.id.container, addBack);
    }

    protected void addFragment(Fragment frag, boolean addBack) {
        addFragment(frag, R.id.container, addBack);
    }

    /**
     * Convenience for calling {@link #setFragment(Fragment, int, String, boolean)}.
     * con String data <code>Fragment.getClass().getName();</code>
     *
     * @param frag        Fragment
     * @param idContainer id of the layout
     * @param addBack     true if add to the back stack
     */
    protected void setFragment(Fragment frag, int idContainer, boolean addBack) {
        setFragment(frag, idContainer, frag.getClass().getName(), addBack);
    }

    protected void addFragment(Fragment frag, int idContainer, boolean addBack) {
        addFragment(frag, idContainer, frag.getClass().getName(), addBack);
    }

    /**
     * Se the fragment on the view indicate by the container
     *
     * @param frag      fragment to set
     * @param container view to put the fragment
     * @param tag       tag for identifier the fragment on the activity
     */
    protected void setFragment(Fragment frag, int container, String tag, boolean addBack) {
        if (isFinishing())
            return;

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(container, frag, tag);
        if (addBack)
            transaction.addToBackStack(tag);
        transaction.commitAllowingStateLoss();
    }

    protected void addFragment(Fragment frag, int container, String tag, boolean addBack) {
        if (isFinishing())
            return;

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(container, frag, tag);
        if (addBack)
            transaction.addToBackStack(tag);
        transaction.commitAllowingStateLoss();
    }

    protected boolean removeFragment(int id) {
        return removeFragment((Fragment) getFragment(id));
    }

    protected boolean removeFragment(Class c) {
        return removeFragment((Fragment) getFragment(c));
    }

    protected boolean removeFragment(String tag) {
        return removeFragment((Fragment) getFragment(tag));
    }

    protected boolean removeFragment(Fragment frag) {
        if (frag == null)
            return false;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.remove(frag);
        transaction.commitAllowingStateLoss();

        return true;
    }

    protected boolean isFragmentOnScreen(Class tag) {
        return isFragmentOnScreen(tag.getName());
    }

    protected boolean isFragmentOnScreen(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag) != null;
    }

    @SuppressWarnings("unchecked")
    protected <T extends Fragment> T getFragment(int id) {
        Fragment frag = getFragmentManager().findFragmentById(id);
        if (frag != null)
            return (T) frag;

        return null;
    }

    protected <T extends Fragment> T getFragment(Class c) {
        return getFragment(c.getName());
    }

    @SuppressWarnings("unchecked")
    protected <T extends Fragment> T getFragment(String tag) {
        Fragment frag = getFragmentManager().findFragmentByTag(tag);
        if (frag != null)
            return (T) frag;

        return null;
    }

    @Override
    public void onBackPressed() {
        if (getNumItemBackStack() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public int getNumItemBackStack() {
        return getFragmentManager().getBackStackEntryCount();
    }

    public void clearStackFragment() {
        getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}