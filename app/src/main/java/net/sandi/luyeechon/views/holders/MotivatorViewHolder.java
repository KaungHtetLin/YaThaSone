package net.sandi.luyeechon.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.sandi.luyeechon.R;
import net.sandi.luyeechon.data.vos.MotivatorVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kaung Htet Lin on 9/20/2016.
 */
public class MotivatorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.iv_motivator)
    ImageView ivMotivator;

    ControllerMotivatorItem mController;

    private MotivatorVO mMotivator;

    public MotivatorViewHolder(View itemView,ControllerMotivatorItem controller) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController = controller;

    }

    public void bindData(MotivatorVO mMotivator) {

        String imageUrl = mMotivator.getImage();
        this.mMotivator = mMotivator;

        Glide.with(ivMotivator.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.yathasone_placeholder)
                .error(R.drawable.yathasone_placeholder)
                .into(ivMotivator);
    }

    @Override
    public void onClick(View view) {
  //      Toast.makeText(LuYeeChonApp.getContext(),"hello",Toast.LENGTH_SHORT).show();
        mController.onTapMotivator(mMotivator,ivMotivator);
    }

    public interface ControllerMotivatorItem {
        void onTapMotivator(MotivatorVO motivatorVO, ImageView ivMotivator);
    }

}