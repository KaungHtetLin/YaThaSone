package net.sandi.luyeechon.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.sandi.luyeechon.LuYeeChonApp;
import net.sandi.luyeechon.R;
import net.sandi.luyeechon.data.vos.JokeVO;
import net.sandi.luyeechon.views.holders.JokeViewHolder;

import java.util.List;

/**
 * Created by UNiQUE on 9/19/2016.
 */
public class JokeAdapter extends RecyclerView.Adapter<JokeViewHolder> {

    private LayoutInflater mInflater;
    private List<JokeVO> jokeVOList;

    private JokeViewHolder.ControllerJokeItem mController;

    public JokeAdapter(List<JokeVO> jokeVOs,JokeViewHolder.ControllerJokeItem mController){
        mInflater = LayoutInflater.from(LuYeeChonApp.getContext());
        this.jokeVOList=jokeVOs;
        this.mController = mController;
    }


    @Override
    public JokeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_joke, parent, false);
        final JokeViewHolder jokeVH = new JokeViewHolder(itemView,mController);
        return jokeVH;
    }

    @Override
    public void onBindViewHolder(JokeViewHolder holder, int position) {
        holder.setData(jokeVOList.get(position));
    }

    @Override
    public int getItemCount() {
        return jokeVOList.size();
    }

    public void setNewData(List<JokeVO> newJokeList) {
        jokeVOList = newJokeList;
        notifyDataSetChanged();
    }
}

