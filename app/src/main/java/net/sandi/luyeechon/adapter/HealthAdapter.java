package net.sandi.luyeechon.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.sandi.luyeechon.LuYeeChonApp;
import net.sandi.luyeechon.R;
import net.sandi.luyeechon.data.vos.HealthVO;
import net.sandi.luyeechon.views.holders.HealthViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by UNiQUE on 9/18/2016.
 */
public class HealthAdapter extends RecyclerView.Adapter<HealthViewHolder> {

    private LayoutInflater mInflater;
    private List<HealthVO> healthList;

    private HealthViewHolder.ControllerHealthItem mControllerHealthItem;

    public HealthAdapter(List<HealthVO> healthList,HealthViewHolder.ControllerHealthItem controllerHealthItem){
        mInflater = LayoutInflater.from(LuYeeChonApp.getContext());
        this.healthList=healthList;
        this.mControllerHealthItem=controllerHealthItem;
    }

    @Override
    public HealthViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_health, parent, false);

        final HealthViewHolder healthVH = new HealthViewHolder(itemView,mControllerHealthItem);
        return healthVH;
    }

    @Override
    public void onBindViewHolder(HealthViewHolder holder, int position) {

        holder.setData(healthList.get(position));
    }

    @Override
    public int getItemCount() {
        return healthList.size();
    }

    public void setNewData(List<HealthVO> newHealthList) {
        healthList = newHealthList;
        notifyDataSetChanged();
    }

    public void setNewData(String type) {
        List<HealthVO> newDataList = new ArrayList<>();
        for (HealthVO healthVO : this.healthList) {
            if (healthVO.getType().equals(type)) {
                newDataList.add(healthVO);
            }
        }
        this.healthList = newDataList;
        notifyDataSetChanged();
    }
}
