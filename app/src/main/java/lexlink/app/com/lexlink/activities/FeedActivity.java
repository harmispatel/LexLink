package lexlink.app.com.lexlink.activities;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import lexlink.app.com.lexlink.R;
import lexlink.app.com.lexlink.adapters.FeedAdapter;
import lexlink.app.com.lexlink.baseviews.BaseTextview;
import lexlink.app.com.lexlink.beans.Feedbean;
import lexlink.app.com.lexlink.recyle_decorate.DividerItemDecoration;

public class FeedActivity extends BaseActivity {

    public static ArrayList<Feedbean> feedbeanArrayList = new ArrayList<>();

    BaseTextview baseText_error = null;
    FeedAdapter feedAdapter = null;

    View home = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /*RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        baseText_error = (BaseTextview) home.findViewById(R.id.empty_view);
        RecyclerView mRecyclerView = (RecyclerView) home.findViewById(R.id.my_recycler_view);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(25);
        // use a linear layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        feedAdapter = new FeedAdapter(mRecyclerView, FeedActivity.this);
        mRecyclerView.setAdapter(feedAdapter);*/

    }

    @Override
    public void onBackPressed() {

    }




}
