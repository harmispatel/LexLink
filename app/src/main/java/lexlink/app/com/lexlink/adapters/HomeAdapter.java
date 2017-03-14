package lexlink.app.com.lexlink.adapters;

/**
 * Created by Harmis on 14/03/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

import lexlink.app.com.lexlink.R;
import lexlink.app.com.lexlink.beans.Homebean;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private ArrayList<Homebean> android;
    private Context context;

    public HomeAdapter(Context context,ArrayList<Homebean> android) {
        this.android = android;
        this.context = context;
    }

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_grid_single_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_android.setText(android.get(i).getTitle());
        viewHolder.img_android.setImageResource(android.get(i).getResourceID());

    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_android;
        private ImageView img_android;
        public ViewHolder(View view) {
            super(view);


        }
    }

}