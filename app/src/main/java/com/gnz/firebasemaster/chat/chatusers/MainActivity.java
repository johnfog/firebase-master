package com.gnz.firebasemaster.chat.chatusers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.gnz.firebasemaster.R;
import com.gnz.firebasemaster.account.LoginSignupActivity;
import com.gnz.firebasemaster.application.App;
import com.gnz.firebasemaster.chat.chatusers.main.ChatFragment;
import com.gnz.firebasemaster.common.ui.BaseActivity;

import butterknife.BindView;


public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.fragment_container)
    FrameLayout frameLayout;

    private MainContract.Presenter presenter;
    private MainComponent component;

    public static void startActivity(Context context) {
        final Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_activity);
        if (savedInstanceState == null) {
            replaceFragment(R.id.fragment_container, ChatFragment.newInstance(), ChatFragment.TAG).commit();
        }
        createComponent();
        createPresenter();
        getPresenter().attachView(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            getPresenter().logOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createComponent() {
        component = App.getAppComponent(this).mainComponent();
    }

    private void createPresenter() {
        presenter = component.getPresenter();
    }

    private MainContract.Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void logOut() {
        LoginSignupActivity.startActivity(this);
        finish();
    }

    @Override
    public void showError(@NonNull String msg) {
        Snackbar.make(frameLayout, msg, Snackbar.LENGTH_LONG).show();
    }
}
