package com.mariorossi.githubstargazer.features.shared.utils;

import android.app.Activity;
import android.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import retrofit2.Call;

public abstract class BaseFragment extends Fragment {

    private Call call;

    public void setCall(Call call) {
        this.call = call;
    }

    @Override
    public void onStop() {
        cancelCall();
        super.onStop();
    }

    public void cancelCall() {
        if (call != null)
            call.cancel();
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = getActivity().getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(getActivity());
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    protected void log(String message) {
        Log.d(getClass().getSimpleName(), message);
    }

}