package net.sandi.luyeechon.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.sandi.luyeechon.LuYeeChonApp;
import net.sandi.luyeechon.R;
import net.sandi.luyeechon.data.models.QuizModel;
import net.sandi.luyeechon.data.persistence.LuYeeChonContract;
import net.sandi.luyeechon.data.vos.QuizVO;
import net.sandi.luyeechon.events.DataEvent;
import net.sandi.luyeechon.utils.LuYeeChonConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by Kaung Htet Lin on 9/18/2016.
 */
public class QuizActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static List<QuizVO> quizVOList = new ArrayList<>();

    public static Intent newIntent() {
        Intent intent = new Intent(LuYeeChonApp.getContext(), QuizActivity.class);
        return intent;
    }

    private int[] thinkingImgaeArr = {
            R.drawable.thinking_quiz,
            R.drawable.thinking_quiz2,
            R.drawable.thinking_quiz3,
            R.drawable.thinking_quiz4,
            R.drawable.thinking_quiz5,
            R.drawable.thinking_quiz6,
            R.drawable.thinking_quiz7,
            R.drawable.thinking_quiz8
    };

    int randomNum = 0;
    int trueCount = 0;

    @BindView(R.id.txt_question)
    TextView txtQuestion;

    @BindView(R.id.et_answer)
    EditText etAnswer;

    @BindView(R.id.txt_answer)
    TextView txtAnswer;

    @BindView(R.id.txt_result)
    TextView txtResult;

    @BindView(R.id.btn_done)
    Button btnDone;

    @BindView(R.id.btn_show)
    Button btnShow;

    @BindView(R.id.iv_think)
    ImageView ivThink;

//    @BindView(R.id.tv_quiz_title)
//    TextView tvQuizTitle;

//  //  public static void loadQuiz()
//    {
//        quizVOList = QuizModel.getInstance().getQuizList();
//    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this, this);
//        final ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayShowTitleEnabled(false);
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
//        tvQuizTitle.setText(R.string.txt_title_quiz);

        List<QuizVO> quizList = QuizModel.getInstance().getQuizList();
        //   setData();
        //       randomNum = new Random().nextInt(quizVOList.size() - 0 + 1) ;



        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ans = etAnswer.getText().toString();

                if (btnDone.getText().toString().equalsIgnoreCase("next")) {
                    setData();
                } else {
                    if (check(ans)) {
                        btnShow.setVisibility(View.INVISIBLE);
                        txtResult.setVisibility(View.VISIBLE);
                        txtResult.setText(R.string.txt_true);
                        btnDone.setText(R.string.btn_next);

                    } else {

                        btnShow.setVisibility(View.VISIBLE);

                        etAnswer.setHint(R.string.txt_false);
                        etAnswer.setText("");
                        Animation shake = AnimationUtils.loadAnimation(QuizActivity.this, R.anim.shake);
                        etAnswer.startAnimation(shake);

                    }
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnShow.setVisibility(View.INVISIBLE);
                etAnswer.setVisibility(View.INVISIBLE);
                txtAnswer.setVisibility(View.VISIBLE);

                txtAnswer.setText("Answer: " + quizVOList.get(randomNum).getAnswer());
                etAnswer.setHint(R.string.et_hint);
                btnDone.setText(R.string.btn_next);
            }
        });

        getSupportLoaderManager().initLoader(LuYeeChonConstants.QUIZ_LIST_LOADER, null, this);

    }

    public void setData() {

        ivThink.setImageResource(thinkingImgaeArr[new Random().nextInt(8)]);
        txtAnswer.setVisibility(View.INVISIBLE);
  //      etAnswer.setVisibility(View.VISIBLE);
  //      btnDone.setVisibility(View.VISIBLE);

        etAnswer.setText("");
        etAnswer.setHint(R.string.et_hint);
        if (randomNum == quizVOList.size() - 1) {
            randomNum = 0;
        } else {
            randomNum++;
        }
        if (quizVOList.size() > 0) {
            txtQuestion.setText(quizVOList.get(randomNum).getQuestion());
            etAnswer.setVisibility(View.VISIBLE);
            btnDone.setVisibility(View.VISIBLE);
        }else {
            etAnswer.setVisibility(View.INVISIBLE);
            btnDone.setVisibility(View.INVISIBLE);
        }
        btnDone.setText(R.string.btn_done);
        txtResult.setVisibility(View.INVISIBLE);
    }

    public boolean check(String ans) {

        String answer = quizVOList.get(randomNum).getAnswer();
        String contain = quizVOList.get(randomNum).getContain();


        if (ans.equalsIgnoreCase(answer) || ans.contains(contain)) {
            trueCount++;
            return true;
        }
        return false;
    }


    @Override
    public void onStop() {
        super.onStop();

        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
              case android.R.id.home:
                  Toast.makeText(this,"You tured "+trueCount+" question.",Toast.LENGTH_SHORT).show();
                  startActivity(new Intent(this,HomeActivity.class));
                 return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onEventMainThread(DataEvent.QuizDataLoadEvent event) {
        String extra = event.getExtraMessage();
        //        Toast.makeText(getApplicationContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();

        //    List<AttractionVO> newAttractionList = AttractionModel.getInstance().getQuizList();
        List<QuizVO> newQuizList = event.getQuizList();
        quizVOList = newQuizList;
        randomNum = new Random().nextInt(quizVOList.size() + 1);

        setData();
        //      mMotivatorAdapter.setNewData(newAttractionList);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                LuYeeChonContract.QuizEntry.CONTENT_URI,
                null,
                null,
                null,
                LuYeeChonContract.QuizEntry._ID);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        List<QuizVO> quizList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                QuizVO quiz = QuizVO.parseFromCursor(data);
                //   quiz.setImages(AttractionVO.loadAttractionImagesByTitle(quiz.getTitle()));
                quizList.add(quiz);
            } while (data.moveToNext());
        }

        Log.d(LuYeeChonApp.TAG, "Retrieved attractions DESC : " + quizList.size());
        //    mAttractionAdapter.setNewData(quizList);

        QuizModel.getInstance().setStoredData(quizList);

        quizVOList=quizList;
        randomNum = new Random().nextInt(quizVOList.size() + 1);
        setData();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this,"You tured "+trueCount+" question.",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,HomeActivity.class));
    }
}
