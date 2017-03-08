package com.gnz.firebasemaster.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.gnz.firebasemaster.R;
import com.gnz.firebasemaster.chat.main.ChatFragment;
import com.gnz.firebasemaster.common.ui.BaseActivity;


public class MainActivity extends BaseActivity {

    public static void startActivity(Context context) {
        final Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_activity);
        if (savedInstanceState == null) {
            replaceFragment(R.id.fragment_container, ChatFragment.newInstance(), ChatFragment.TAG).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            //TODO logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
