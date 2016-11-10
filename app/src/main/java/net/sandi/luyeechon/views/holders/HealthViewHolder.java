package net.sandi.luyeechon.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.sandi.luyeechon.R;
import net.sandi.luyeechon.data.vos.HealthVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by UNiQUE on 9/18/2016.
 */
public class HealthViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

    @BindView(R.id.tv_health_title)
    TextView txtHealthTitle;

    @BindView(R.id.tv_health_desc)
    TextView txtHealthDes;

    @BindView(R.id.iv_health_photo)
    ImageView ivHealth;

    private HealthVO mHealth;
    private ControllerHealthItem mController;

    public HealthViewHolder(View itemView,ControllerHealthItem mController) {

        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(this);
        this.mController = mController;

    }
    public void setData(HealthVO health) {
        this.mHealth = health;
        txtHealthTitle.setText(mHealth.getHealthTitle());
        txtHealthDes.setText(mHealth.getHealthDes());

        Glide.with(ivHealth.getContext())
                .load(health.getImage())
                .centerCrop()
                .placeholder(R.drawable.yathasone_placeholder)
                .into(ivHealth);
    }

    @Override
    public void onClick(View view) {

        mController.onTapHealth(mHealth,ivHealth);
    }
    public interface ControllerHealthItem {
        void onTapHealth(HealthVO health, ImageView ivAttraction);
    }
}
