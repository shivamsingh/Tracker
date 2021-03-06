package com.tracker.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.tracker.R;
import com.tracker.ui.base.BaseActivity;
import com.tracker.ui.tracker.TrackerFragment;
import com.tracker.ui.tracks.TracksActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Lazy;
import polanski.option.Option;

import static com.tracker.utils.ActivityUtils.addFragmentToActivity;


public class HomeActivity extends BaseActivity {

    @Inject
    Lazy<TrackerFragment> trackerFragmentLazy;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Option.ofObj(savedInstanceState).ifNone(this::showTrackerFragment);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.inflateMenu(R.menu.home);
        toolbar.setOnMenuItemClickListener(this::optionMenuSelected);
    }

    private boolean optionMenuSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.all_tracks) {
            startActivity(new Intent(this, TracksActivity.class));
            return true;
        }
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_container;
    }

    private void showTrackerFragment() {
        addFragmentToActivity(getSupportFragmentManager(), trackerFragmentLazy.get(), R.id.container);
    }
}
