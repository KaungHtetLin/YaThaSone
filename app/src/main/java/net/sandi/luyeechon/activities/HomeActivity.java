package net.sandi.luyeechon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import net.sandi.luyeechon.R;
import net.sandi.luyeechon.data.vos.HealthVO;
import net.sandi.luyeechon.data.vos.JokeVO;
import net.sandi.luyeechon.data.vos.MotivatorVO;
import net.sandi.luyeechon.fragments.FavouriteHealthFragment;
import net.sandi.luyeechon.fragments.FavouriteJokeFragment;
import net.sandi.luyeechon.fragments.HealthFragment;
import net.sandi.luyeechon.fragments.JokeFragment;
import net.sandi.luyeechon.fragments.MotivatorFragment;
import net.sandi.luyeechon.utils.MMFontUtils;
import net.sandi.luyeechon.views.holders.HealthViewHolder;
import net.sandi.luyeechon.views.holders.JokeViewHolder;
import net.sandi.luyeechon.views.holders.MotivatorViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,HealthViewHolder.ControllerHealthItem,JokeViewHolder.ControllerJokeItem,MotivatorViewHolder.ControllerMotivatorItem{

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.tv_screen_title)
    TextView tvScreenTitle;

    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        this.actionBar=actionBar;
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
        actionBar.setTitle(R.string.app_name);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        Menu leftMenu = navigationView.getMenu();
        MMFontUtils.applyMMFontToMenu(leftMenu);



        if (savedInstanceState == null) {
            navigateToMotivator();
        }


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                return true;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(final MenuItem item) {
        item.setChecked(true);
        drawerLayout.closeDrawers();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (item.getItemId()) {
                    case R.id.menu_health_topics:
                        navigateToHealth();
                        break;
                    case R.id.menu_quiz:
                        navigateToQuiz();
                        break;
                    case R.id.menu_sate_khon_arr:
                        navigateToMotivator();
                        break;
                    case R.id.menu_short_jokes:
                        navigateToJoke();
                        break;
                    case R.id.menu_fav_joke:
                        navigateToFavJoke();
                        break;
                    case R.id.menu_fav_health:
                        navigateToFavHealth();
                        break;

                }
            }
        }, 100); //to close drawer smoothly.

        return true;
    }
    public void navigateToHealth(){

        tvScreenTitle.setText(R.string.menu_health_topics);
        //  actionBar.setTitle(R.string.menu_health_topics);
        HealthFragment healthFragment = HealthFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, healthFragment)
                .commit();
    }

    public void navigateToFavJoke(){
        tvScreenTitle.setText(R.string.txt_fav_joke);
        FavouriteJokeFragment favouriteJokeFragment = FavouriteJokeFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container,favouriteJokeFragment)
                .commit();
    }

    public void navigateToFavHealth(){
        tvScreenTitle.setText(R.string.txt_fav_health);
        FavouriteHealthFragment favouriteHealthFragment = FavouriteHealthFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container,favouriteHealthFragment)
                .commit();
    }
    public void navigateToJoke(){
        tvScreenTitle.setText(R.string.menu_short_jokes);
        //  actionBar.setTitle(R.string.menu_short_jokes);
        JokeFragment jokeFragment = JokeFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container,jokeFragment)
                .commit();
    }
    public void navigateToQuiz(){
        Intent intent = QuizActivity.newIntent();
        startActivity(intent);
    }

    public void navigateToMotivator(){
        tvScreenTitle.setText(R.string.menu_motivator);
        //  actionBar.setTitle(R.string.menu_motivator);
        MotivatorFragment motivatorFragment = MotivatorFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container,motivatorFragment)
                .commit();
    }

    @Override
    public void onTapHealth(HealthVO health, ImageView ivHealth) {
        Intent intent = HealthDetailActivity.newIntent(health);
//        startActivity(intent);


        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                new Pair(ivHealth, getString(R.string.health_list_detail_name)));
        ActivityCompat.startActivity(this, intent, activityOptions.toBundle());


    }

    @Override
    public void onTapJoke(JokeVO joke, ImageView ivJoke) {
        Intent intent = JokeDetailActivity.newIntent(joke);

        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                new Pair(ivJoke, getString(R.string.joke_list_detail_name)));
        ActivityCompat.startActivity(this, intent, activityOptions.toBundle());

    }

    @Override
    public void onTapMotivator(MotivatorVO motivatorVO, ImageView ivMotivator) {
        Intent intent= MotivatorImageZoomActivity.newIntent(motivatorVO);
        startActivity(intent);
     //   Toast.makeText(LuYeeChonApp.getContext(),"Yay you tapped! ok ! ",Toast.LENGTH_LONG).show();

    }
}
