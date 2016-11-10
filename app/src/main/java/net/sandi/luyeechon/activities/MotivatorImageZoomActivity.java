package net.sandi.luyeechon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.bumptech.glide.Glide;

import net.sandi.luyeechon.LuYeeChonApp;
import net.sandi.luyeechon.R;
import net.sandi.luyeechon.data.vos.MotivatorVO;
import net.sandi.luyeechon.utils.TouchImageView;

/**
 * Created by Kaung Htet Lin on 10/2/2016.
 */
public class MotivatorImageZoomActivity extends AppCompatActivity {

    private static final String IE_MOTIVATOR_TOPIC_NAME = "IE_MOTIVATOR_TOPIC_NAME";

    private static MotivatorVO mMotivator;

    ActionBar actionBar;

    public static Intent newIntent(MotivatorVO motivator) {
        Intent intent = new Intent(LuYeeChonApp.getContext(), MotivatorImageZoomActivity.class);
        intent.putExtra(IE_MOTIVATOR_TOPIC_NAME,motivator.getImage());
        mMotivator=motivator;
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar actionBar = getSupportActionBar();
        this.actionBar=actionBar;
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        actionBar.setTitle(R.string.motivator_title);


        TouchImageView img = new TouchImageView(this);
        img.setMaxZoom(4f);

        Glide.with(img.getContext())
                .load(mMotivator.getImage())
                .placeholder(R.drawable.yathasone_placeholder)
                .into(img);

        setContentView(img);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
