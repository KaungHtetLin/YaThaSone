package net.sandi.luyeechon.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.sandi.luyeechon.LuYeeChonApp;
import net.sandi.luyeechon.R;
import net.sandi.luyeechon.data.vos.HealthVO;
import net.sandi.luyeechon.views.holders.HealthViewHolder;

import java.util.List;

/**
 * Created by UNiQUE on 10/2/2016.
 */
public class FavouriteHealthAdapter extends RecyclerView.Adapter<HealthViewHolder> {

    private LayoutInflater mInflater;
    private List<HealthVO> healthVOList;

    private HealthViewHolder.ControllerHealthItem mController;

    public FavouriteHealthAdapter(List<HealthVO> healthVOs, HealthViewHolder.ControllerHealthItem mController) {
        mInflater = LayoutInflater.from(LuYeeChonApp.getContext());
        this.healthVOList = healthVOs;
        this.mController = mController;
    }


    @Override
    public HealthViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_health, parent, false);
        final HealthViewHolder healthVH = new HealthViewHolder(itemView, mController);
        return healthVH;
    }

    @Override
    public void onBindViewHolder(HealthViewHolder holder, int position) {
        holder.setData(healthVOList.get(position));
    }

    @Override
    public int getItemCount() {
        return healthVOList.size();
    }

    public void setNewData(List<HealthVO> newHealthList) {
        healthVOList = newHealthList;
        notifyDataSetChanged();
    }
}

