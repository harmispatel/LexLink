package lexlink.app.com.lexlink.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import lexlink.app.com.lexlink.R;
import lexlink.app.com.lexlink.models.Homebean;

public class HomeActivity extends AppCompatActivity {

    ArrayList<Homebean> homebeanArrayList = new ArrayList<>();

    GridView androidGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);


        initViews();
    }

    private void initViews() {
        prepareData();
        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(HomeActivity.this);
        androidGridView=(GridView)findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
                Toast.makeText(HomeActivity.this, "GridView Item: " + homebeanArrayList.get(i).getTitle(), Toast.LENGTH_LONG).show();

                Intent  intent=new Intent(HomeActivity.this,FeedActivity.class);
                startActivity(intent);
            }
        });
    }

    private ArrayList<Homebean> prepareData() {

        Homebean homebean1=new Homebean();
        homebean1.setTitle(getResources().getString(R.string.home));
        homebean1.setResourceID(R.drawable.home_icon);

        Homebean homebean2=new Homebean();
        homebean2.setTitle(getResources().getString(R.string.feeds));
        homebean2.setResourceID(R.drawable.feeds_icon);

        Homebean homebean3=new Homebean();
        homebean3.setTitle(getResources().getString(R.string.ongoing_claims));
        homebean3.setResourceID(R.drawable.ongoing_claims_icon);

        Homebean homebean4=new Homebean();
        homebean4.setTitle(getResources().getString(R.string.past_claims));
        homebean4.setResourceID(R.drawable.past_claims_icon);

        Homebean homebean5=new Homebean();
        homebean5.setTitle(getResources().getString(R.string.notificaitons));
        homebean5.setResourceID(R.drawable.notifications_icon);

        Homebean homebean6=new Homebean();
        homebean6.setTitle(getResources().getString(R.string.my_profile));
        homebean6.setResourceID(R.drawable.my_profile_icon);






        homebeanArrayList.add(homebean1);
        homebeanArrayList.add(homebean2);
        homebeanArrayList.add(homebean3);
        homebeanArrayList.add(homebean4);
        homebeanArrayList.add(homebean5);
        homebeanArrayList.add(homebean6);


        return homebeanArrayList;
    }



    public class CustomGridViewActivity extends BaseAdapter {

        private Context mContext;


        public CustomGridViewActivity(Context context) {
            mContext = context;

        }

        @Override
        public int getCount() {
            return homebeanArrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            View gridViewAndroid;
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {

                gridViewAndroid = new View(mContext);
                gridViewAndroid = inflater.inflate(R.layout.home_grid_single_item, null);
                TextView textViewAndroid = (TextView) gridViewAndroid.findViewById(R.id.android_gridview_text);
                ImageView imageViewAndroid = (ImageView) gridViewAndroid.findViewById(R.id.android_gridview_image);
                textViewAndroid.setText(homebeanArrayList.get(i).getTitle());
                imageViewAndroid.setImageResource(homebeanArrayList.get(i).getResourceID());
            } else {
                gridViewAndroid = convertView;
            }

            return gridViewAndroid;
        }
    }
}
