package net.sandi.luyeechon.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.sandi.luyeechon.LuYeeChonApp;
import net.sandi.luyeechon.R;
import net.sandi.luyeechon.data.vos.JokeVO;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class JokeDetailActivity extends AppCompatActivity {

    private static final String IE_JOKE_TOPIC_NAME = "IE_JOKE_TOPIC_NAME";

    @BindView(R.id.toolbar_joke)
    Toolbar toolbarJoke;

    @BindView(R.id.fab_joke_detail)
    FloatingActionButton fabJoke;

    @BindView(R.id.tv_joke_desc)
    TextView tvJokeDes;

    @BindView(R.id.iv_joke_photo)
    ImageView ivJoke;

//    @BindView(R.id.btn_share_joke)
//    Button btnShareJoke;

    @BindString(R.string.joke_list_detail_name) String JOKE_IMAGE_TRANSITION_NAME;

    @BindView(R.id.collapsing_toolbar_joke)
    CollapsingToolbarLayout collapsingToolbarJoke;

    private String mJokeTitle;
    private static JokeVO mJoke;


    public static Intent newIntent(JokeVO joke) {
        Intent intent = new Intent(LuYeeChonApp.getContext(), JokeDetailActivity.class);
        intent.putExtra(IE_JOKE_TOPIC_NAME,joke.getJokeTitle());
        mJoke=joke;
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_detail);

        ButterKnife.bind(this, this);

        ViewCompat.setTransitionName(ivJoke, JOKE_IMAGE_TRANSITION_NAME);

        setSupportActionBar(toolbarJoke);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

//        btnShareJoke.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(JokeDetailActivity.this)
//                        .setType("text/plain")
//                        .setText(mJoke.getJokeTitle() + " \n\n\n " + mJoke.getJokeDes())
//                        .getIntent(), getString(R.string.action_share)));
//            }
//        });

        fabJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJoke.getFav().equals("1")) {
                    mJoke.setFav("0");
                    JokeVO.removeFavouriteJoke(mJoke);
                    fabJoke.setImageResource(R.drawable.ic_favorite_white_24dp);
                }
                else if(mJoke.getFav().equals("0")) {
                    mJoke.setFav("1");
                    JokeVO.saveFavouriteJoke(mJoke,mJoke.getFav());
                    fabJoke.setImageResource(R.drawable.ic_favorite_red_24dp);
//                    fabJoke.setBackgroundColor(Color.parseColor(String.valueOf(R.color.red)));

                }
            }
        });
        mJokeTitle = getIntent().getStringExtra(IE_JOKE_TOPIC_NAME);
        tvJokeDes.setText(mJoke.getJokeDes());

        Glide.with(ivJoke.getContext())
                .load(mJoke.getImageJoke())
                .centerCrop()
                .placeholder(R.drawable.joke_placeholder)
                .into(ivJoke);
        collapsingToolbarJoke.setTitle(mJokeTitle);
        // tvJokeTitle.setText(mJokeTitle);
        /*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Context context = MyanmarAttractionsApp.getContext();
            String transitionName = context.getResources().getString(R.string.attraction_list_detail_transition_name);
            ivAttraction.setTransitionName(transitionName);
        }
        */

        final Typeface tf = Typeface.createFromAsset(LuYeeChonApp.getContext().getAssets(), "fonts/Zawgyi.ttf");
        collapsingToolbarJoke.setCollapsedTitleTypeface(tf);
        collapsingToolbarJoke.setExpandedTitleTypeface(tf);

//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            boolean isShow = false;
//            int scrollRange = -1;
//
//            @Override public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if (scrollRange == -1) {
//                    scrollRange = appBarLayout.getTotalScrollRange();
//                }
//                if (scrollRange + verticalOffset == 0) {
//                    tvJokeTitle.setText(mJokeTitle);
//               //     getSupportActionBar().setTitle(series.title_english);
//                    isShow = true;
//                } else if (isShow) {
//                 //   getSupportActionBar().setTitle("");
//                    tvJokeTitle.setText("");
//                    isShow = false;
//                }
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_joke_detail_screen, menu);
        return super.onCreateOptionsMenu(menu);
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
    private void bindData(JokeVO jokeVO) {
        tvJokeDes.setText(jokeVO.getJokeDes());

        /*
        String imageUrl = MyanmarAttractionsConstants.IMAGE_ROOT_DIR + attraction.getImages()[0];
        Glide.with(ivAttraction.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.stock_photo_placeholder)
                .error(R.drawable.stock_photo_placeholder)
                .into(ivAttraction);
                */

        collapsingToolbarJoke.setTitle(mJokeTitle);
    }

}

