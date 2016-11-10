package net.sandi.luyeechon.events;

import net.sandi.luyeechon.data.vos.HealthVO;
import net.sandi.luyeechon.data.vos.JokeVO;
import net.sandi.luyeechon.data.vos.MotivatorVO;
import net.sandi.luyeechon.data.vos.QuizVO;

import java.util.List;

/**
 * Created by UNiQUE on 9/25/2016.
 */
public class DataEvent {

    public static class HealthDataLoadedEvent{
        private String extraMessage;
        private List<HealthVO> healthList;

        public HealthDataLoadedEvent(String extraMessage, List<HealthVO> healthList) {
            this.extraMessage = extraMessage;
            this.healthList = healthList;
        }

        public String getExtraMessage() {
            return extraMessage;
        }

        public List<HealthVO> getHealthList() {
            return healthList;
        }
    }

    public static class JokeDataLoadedEvent{
        private String extraMessage;
        private List<JokeVO> jokeList;

        public JokeDataLoadedEvent(String extraMessage, List<JokeVO> jokeList) {
            this.extraMessage = extraMessage;
            this.jokeList = jokeList;
        }

        public String getExtraMessage() {
            return extraMessage;
        }

        public List<JokeVO> getJokeList() {
            return jokeList;
        }
    }
    public static class MotivatorDataLoadedEvent{
        private String extraMessage;
        private List<MotivatorVO> motivatorList;

        public MotivatorDataLoadedEvent(String extraMessage, List<MotivatorVO> motivatorList) {
            this.extraMessage = extraMessage;
            this.motivatorList = motivatorList;
        }

        public String getExtraMessage() {
            return extraMessage;
        }

        public List<MotivatorVO> getMotivatorList() {
            return motivatorList;
        }
    }

    public static class QuizDataLoadEvent {
        private String extraMessage;
        private List<QuizVO> quizList;

        public QuizDataLoadEvent(String extraMessage, List<QuizVO> quizList) {
            this.extraMessage = extraMessage;
            this.quizList = quizList;
        }

        public String getExtraMessage() {
            return extraMessage;
        }

        public List<QuizVO> getQuizList() {
            return quizList;
        }
    }

}
