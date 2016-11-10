package net.sandi.luyeechon.data.models;

import net.sandi.luyeechon.data.vos.JokeVO;
import net.sandi.luyeechon.events.DataEvent;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by UNiQUE on 9/23/2016.
 */
public class JokeModel extends BaseModel{
//    private static final String DUMMY_JOKE_LIST = "joke_list.json";

    private static JokeModel objInstance;

    private List<JokeVO> jokeVOList;

    private JokeModel(){
        jokeVOList = new ArrayList<JokeVO>();
        dataAgent.loadJokeList();

    }

    public static JokeModel getInstance(){
        if(objInstance == null) {
            objInstance = new JokeModel();
        }

        return objInstance;
    }

    public List<JokeVO> getJokeVOList() {
        return jokeVOList;
    }

    private void broadcastJokesLoadedWithEventBus() {
        EventBus.getDefault().post(new DataEvent.JokeDataLoadedEvent("extra-in-broadcast", jokeVOList));
    }

    public void notifyJokeListLoaded(List<JokeVO> jokeList) {
        //Notify that the data is ready - using LocalBroadcast
        this.jokeVOList = jokeList;
        //keep the data in persistent layer.
        JokeVO.saveJokes(jokeVOList);

        broadcastJokesLoadedWithEventBus();
    }

    public void notifyErrorInLoadingJokes(String message) {

    }

    public void setStoredData(List<JokeVO> jokeList) {
        jokeVOList = jokeList;
    }
}
