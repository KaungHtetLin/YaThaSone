package net.sandi.luyeechon.data.models;

import net.sandi.luyeechon.data.vos.QuizVO;
import net.sandi.luyeechon.events.DataEvent;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Kaung Htet Lin on 9/24/2016.
 */
public class QuizModel extends BaseModel{

    private static QuizModel objInstance;

    private List<QuizVO> mQuizList;

//    private LuYeeChonDataAgent dataAgent;

    private QuizModel() {
        super();
        mQuizList = new ArrayList<>();
        //    initDataAgent(INIT_DATA_AGENT_RETROFIT);
        dataAgent.loadQuiz();
    }


    public static QuizModel getInstance() {
        if (objInstance == null) {
            objInstance = new QuizModel();
        }
        return objInstance;
    }

    public List<QuizVO> getQuizList() {
        return mQuizList;
    }


    public void notifyQuizLoaded(List<QuizVO> quizList) {
        //Notify that the data is ready - using LocalBroadcast
        mQuizList = quizList;

        //keep the data in persistent layer.
        QuizVO.saveQuiz(mQuizList);

        broadcastQuizLoadedWithEventBus();
        //broadcastMotivatorLoadedWithLocalBroadcastManager();
    }

    public void notifyErrorInLoadingQuiz(String message) {

    }


    private void broadcastQuizLoadedWithEventBus() {
        EventBus.getDefault().post(new DataEvent.QuizDataLoadEvent("extra-in-broadcast", mQuizList));
    }

    public void setStoredData(List<QuizVO> quizList) {
        mQuizList = quizList;
    }


}
