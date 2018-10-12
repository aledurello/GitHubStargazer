package com.mariorossi.githubstargazer.features.home;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mariorossi.githubstargazer.R;
import com.mariorossi.githubstargazer.core.model.Repository;
import com.mariorossi.githubstargazer.core.model.User;
import com.mariorossi.githubstargazer.features.repository.FindRepositoryFragment;
import com.mariorossi.githubstargazer.features.repository.RepositoryDetailsFragment;
import com.mariorossi.githubstargazer.features.shared.utils.BaseAppActivity;

public class MainActivity extends BaseAppActivity implements RepositoryDetailsFragment.OnRepositoryDetailsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        setFragment(FindRepositoryFragment.getInstance());
    }

    @Override
    public void onUserClick(User user) {
        setFragment(FindRepositoryFragment.getInstance(user.getLogin()), true);
    }
}
