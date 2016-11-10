package net.sandi.luyeechon.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import net.sandi.luyeechon.LuYeeChonApp;
import net.sandi.luyeechon.R;
import net.sandi.luyeechon.views.items.ViewItemHealthTopic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by UNiQUE on 9/20/2016.
 */
public class HealthTopicListAdapter extends BaseAdapter{

    private List<String> mHealthTopicsList;
    private LayoutInflater mInflater;

    public HealthTopicListAdapter(List<String> healthTopicsList) {
        if (healthTopicsList != null) {
            this.mHealthTopicsList = healthTopicsList;
        } else {
            this.mHealthTopicsList = new ArrayList<>();
        }
        mInflater = LayoutInflater.from(LuYeeChonApp.getContext());
    }

    @Override
    public int getCount() {
        return mHealthTopicsList.size();
    }

    @Override
    public String getItem(int position) {
        return mHealthTopicsList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.view_item_health_topic, parent, false);
        }

        if (convertView instanceof ViewItemHealthTopic) {
            ViewItemHealthTopic viCountry = (ViewItemHealthTopic) convertView;
            viCountry.setData(getItem(position));
        }

        return convertView;
    }


}

