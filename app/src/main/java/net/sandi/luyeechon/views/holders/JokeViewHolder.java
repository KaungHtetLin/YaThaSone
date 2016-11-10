package net.sandi.luyeechon.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.sandi.luyeechon.R;
import net.sandi.luyeechon.data.vos.JokeVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by UNiQUE on 9/19/2016.
 */
public class JokeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

    @BindView(R.id.tv_joke_title)
    TextView txtJokeTitle;

    @BindView(R.id.iv_joke_photo)
    ImageView ivJoke;


    private JokeVO mJoke;
    private ControllerJokeItem mController;

    public JokeViewHolder(View itemView,ControllerJokeItem controller) {

        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        this.mController =  controller;
    }
    public void setData(JokeVO joke) {
        this.mJoke = joke;
        txtJokeTitle.setText(mJoke.getJokeTitle());

        Glide.with(ivJoke.getContext())
                .load(joke.getImageJoke())
                .centerCrop()
                .placeholder(R.drawable.joke_placeholder)
                .into(ivJoke);

    }

    @Override
    public void onClick(View view) {
        mController.onTapJoke(mJoke,ivJoke);
    }

    public interface ControllerJokeItem {
        void onTapJoke(JokeVO joke, ImageView ivJoke);
    }
}
