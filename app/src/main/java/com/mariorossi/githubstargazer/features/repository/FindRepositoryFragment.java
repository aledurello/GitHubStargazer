package com.mariorossi.githubstargazer.features.repository;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.mariorossi.githubstargazer.R;
import com.mariorossi.githubstargazer.core.model.Repository;
import com.mariorossi.githubstargazer.core.network.RestApi;
import com.mariorossi.githubstargazer.features.shared.item.RepositoryItem;
import com.mariorossi.githubstargazer.features.shared.utils.BaseFragment;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.mikepenz.fastadapter.listeners.OnClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindRepositoryFragment extends BaseFragment {

    public static final String USER = "user";

    public static Fragment getInstance() {
        return getInstance("");
    }

    public static Fragment getInstance(String login) {
        Fragment fragment = new FindRepositoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString(USER, login);
        fragment.setArguments(bundle);
        return fragment;
    }

    public interface OnFindRepositoryListener {

        void onRepositoryClicked(Repository repository);
    }

    @BindView(R.id.layout_no_data)
    View noData;
    @BindView(R.id.layout_loading)
    View loading;
    @BindView(R.id.repo_list)
    RecyclerView recyclerView;
    @BindView(R.id.user_edit_text)
    EditText editText;

    private OnFindRepositoryListener listener;
    private FastItemAdapter<RepositoryItem> itemAdapter = new FastItemAdapter<>();
    private TextWatcher textWatcher = new TextWatcher() {

        private String text;
        private Handler handler = new Handler();
        private Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d("Search", "Search");
                search(text, false);
            }
        };

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            handler.removeCallbacks(runnable);
            if (s.length() > 2) {
                Log.d("Search", "TextChanged");
                text = s.toString();
                handler.postDelayed(runnable, 550);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private Callback<List<Repository>> searchResult = new Callback<List<Repository>>() {
        @Override
        public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
            if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                showRepository(response.body());
            } else {
                showNoData();
            }
        }

        @Override
        public void onFailure(Call<List<Repository>> call, Throwable t) {
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFindRepositoryListener)
            setOnFindRepositoryListener((OnFindRepositoryListener) context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnFindRepositoryListener)
            setOnFindRepositoryListener((OnFindRepositoryListener) activity);
    }

    public void setOnFindRepositoryListener(OnFindRepositoryListener onFindRepositoryListener) {
        this.listener = onFindRepositoryListener;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (listener != null)
            listener = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_repository, container, false);
        ButterKnife.bind(this, view);

        editText.addTextChangedListener(textWatcher);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (v.getText().length() > 0) {
                        search(v.getText().toString(), true);
                        return true;
                    }
                }
                return false;
            }
        });

        itemAdapter.withOnClickListener(new OnClickListener<RepositoryItem>() {
            @Override
            public boolean onClick(View v, IAdapter<RepositoryItem> adapter, RepositoryItem item, int position) {
                listener.onRepositoryClicked(item.getRepository());
                return true;
            }
        });

        //get our recyclerView and do basic setup
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.getItemAnimator().setAddDuration(500);
        recyclerView.getItemAnimator().setRemoveDuration(500);
        recyclerView.setAdapter(itemAdapter);

        Bundle bundle = getArguments();
        String user = bundle != null ? bundle.getString(USER) : null;
        if (!TextUtils.isEmpty(user)) {
            search(user, false);
        }

        return view;
    }

    private void showRepository(List<Repository> body) {
        if (itemAdapter.getAdapterItemCount() > 0)
            itemAdapter.clear();
        itemAdapter.add(RepositoryItem.getRepositoryItems(body, getActivity()));
        showData();
    }

    /**
     * @param searchString search keyword
     */
    private void search(String searchString, boolean closeKeyboard) {
        cancelCall();
        setCall(RestApi.requestUserRepositories(searchString, searchResult));
        showLoading();
        if (closeKeyboard)
            hideKeyboard();
    }

    private void showLoading() {
        loading.setVisibility(View.VISIBLE);
        noData.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
    }

    private void showNoData() {
        loading.setVisibility(View.GONE);
        noData.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    private void showData() {
        loading.setVisibility(View.GONE);
        noData.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }
}