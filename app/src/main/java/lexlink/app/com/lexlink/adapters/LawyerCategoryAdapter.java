package lexlink.app.com.lexlink.adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import lexlink.app.com.lexlink.R;
import lexlink.app.com.lexlink.baseviews.BaseTextview;
import lexlink.app.com.lexlink.models.LawyerCategoryList;

/**
 * Created by harmis on 16/3/17.
 */

public class LawyerCategoryAdapter extends RecyclerView.Adapter {

    Context context;
    List<LawyerCategoryList> lawyerCategoryLists;
    Dialog dialog;

    public LawyerCategoryAdapter(Context context, List<LawyerCategoryList> lawyerCategoryLists, Dialog dialog) {
        this.context = context;
        this.lawyerCategoryLists = lawyerCategoryLists;
        this.dialog = dialog;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.signup_lawyer_category_item, parent, false);

        vh = new CateforyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final LawyerCategoryList list = lawyerCategoryLists.get(position);
        ((CateforyViewHolder) holder).category_tv_name.setText(list.getCategoryName());
        if (list.isItemSelected()) {
            ((CateforyViewHolder) holder).category_iv_radio_on_off.setImageResource(R.drawable.radio_btn_selected);
        } else {
            ((CateforyViewHolder) holder).category_iv_radio_on_off.setImageResource(R.drawable.radio_btn_unselected);

        }


        ((CateforyViewHolder) holder).view.setTag(list);


    }

    @Override
    public int getItemCount() {
        return lawyerCategoryLists.size();
    }

    public static class CateforyViewHolder extends RecyclerView.ViewHolder {
        BaseTextview category_tv_name = null;
        ImageView category_iv_radio_on_off = null;
        View view = null;


        public CateforyViewHolder(View v) {
            super(v);
            category_tv_name = (BaseTextview) v.findViewById(R.id.category_tv_name);
            category_iv_radio_on_off = (ImageView) v.findViewById(R.id.category_iv_radio_on_off);
            view = v;
        }
    }

}
