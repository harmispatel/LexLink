package lexlink.app.com.lexlink.activities;

import android.os.Bundle;
import android.view.View;

import lexlink.app.com.lexlink.adapters.FeedAdapter;
import lexlink.app.com.lexlink.baseviews.BaseTextview;

public class FeedActivity extends BaseActivity {


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
        super.onBackPressed();
    }
}
