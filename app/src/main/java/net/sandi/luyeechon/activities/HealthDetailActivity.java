package net.sandi.luyeechon.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.sandi.luyeechon.LuYeeChonApp;
import net.sandi.luyeechon.R;
import net.sandi.luyeechon.data.vos.HealthVO;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by UNiQUE on 9/20/2016.
 */
public class HealthDetailActivity extends AppCompatActivity {

    private static final String IE_HEALTH_TOPIC_NAME = "IE_HEALTH_TOPIC_NAME";


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab_health_detail)
    FloatingActionButton fab;

    @BindView(R.id.btn_share_health)
    Button btnShareHealth;

    @BindView(R.id.tv_attraction_desc)
    TextView tvAttractionDesc;

    @BindView(R.id.iv_attraction)
    ImageView ivHealth;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindString(R.string.health_list_detail_name) String HEALTH_IMAGE_TRANSITION_NAME;

    private String mAttractionTitle;
    private static HealthVO mHealth;


    public static Intent newIntent(HealthVO healthVO) {
        Intent intent = new Intent(LuYeeChonApp.getContext(), HealthDetailActivity.class);
        intent.putExtra(IE_HEALTH_TOPIC_NAME,healthVO.getHealthTitle());
        mHealth=healthVO;
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_health_detail);

        ButterKnife.bind(this, this);

        ViewCompat.setTransitionName(ivHealth, HEALTH_IMAGE_TRANSITION_NAME);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        btnShareHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(HealthDetailActivity.this)
                        .setType("text/plain")
                        .setText(mHealth.getHealthTitle() + " \n\n\n " + mHealth.getHealthDes())
                        .getIntent(), getString(R.string.action_share)));
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mHealth.getFav().equals("1")) {
                    mHealth.setFav("0");
                    HealthVO.removeFavouriteHealth(mHealth);
                }
                else if(mHealth.getFav().equals("0")) {
                    mHealth.setFav("1");
                    HealthVO.saveFavouriteHealths(mHealth,mHealth.getFav());
//                    fabJoke.setBackgroundColor(Color.parseColor(String.valueOf(R.color.red)));

                }
            }
        });

        mAttractionTitle = getIntent().getStringExtra(IE_HEALTH_TOPIC_NAME);
        tvAttractionDesc.setText(mHealth.getHealthDes() );

        Glide.with(ivHealth.getContext())
                .load(mHealth.getImage())
                .centerCrop()
                .placeholder(R.drawable.yathasone_placeholder)
                .into(ivHealth);

        collapsingToolbar.setTitle(mAttractionTitle);

        /*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Context context = MyanmarAttractionsApp.getContext();
            String transitionName = context.getResources().getString(R.string.attraction_list_detail_transition_name);
            ivAttraction.setTransitionName(transitionName);
        }
        */

        final Typeface tf = Typeface.createFromAsset(LuYeeChonApp.getContext().getAssets(), "fonts/Zawgyi.ttf");
        collapsingToolbar.setCollapsedTitleTypeface(tf);
        collapsingToolbar.setExpandedTitleTypeface(tf);


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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
    }
    private void bindData(HealthVO healthVO) {
        tvAttractionDesc.setText(healthVO.getHealthDes() + "\n\n"
                + healthVO.getHealthDes());

        Glide.with(ivHealth.getContext())
                .load(healthVO.getImage())
                .centerCrop()
                .placeholder(R.drawable.yathasone_placeholder)
                .into(ivHealth);

        collapsingToolbar.setTitle(mAttractionTitle);
    }


}
