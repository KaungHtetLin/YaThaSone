package net.sandi.luyeechon.data.agents.retrofit;

import net.sandi.luyeechon.data.agents.DataAgent;
import net.sandi.luyeechon.data.models.HealthModel;
import net.sandi.luyeechon.data.models.JokeModel;
import net.sandi.luyeechon.data.models.MotivatorModel;
import net.sandi.luyeechon.data.models.QuizModel;
import net.sandi.luyeechon.data.responses.HealthListResponse;
import net.sandi.luyeechon.data.responses.JokeListResponse;
import net.sandi.luyeechon.data.responses.MotivatorListResponse;
import net.sandi.luyeechon.data.responses.QuizListResponse;
import net.sandi.luyeechon.utils.LuYeeChonConstants;
import net.sandi.luyeechon.utils.CommonInstances;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by UNiQUE on 9/25/2016.
 */
public class RetrofitDataAgent implements DataAgent {


    private static RetrofitDataAgent objInstance;

    private final LuYeeChonApi theApi;

    private RetrofitDataAgent() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LuYeeChonConstants.APP_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(CommonInstances.getGsonInstance()))
                .client(okHttpClient)
                .build();

        theApi = retrofit.create(LuYeeChonApi.class);
    }

    public static RetrofitDataAgent getInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgent();
        }
        return objInstance;
    }


    @Override
    public void loadHealthList() {
        Call<HealthListResponse> loadHealthCall = theApi.loadHealthList(LuYeeChonConstants.ACCESS_TOKEN);
        loadHealthCall.enqueue(new Callback<HealthListResponse>() {
            @Override
            public void onResponse(Call<HealthListResponse> call, Response<HealthListResponse> response) {
                HealthListResponse healthListResponse = response.body();
                if (healthListResponse == null) {
                    HealthModel.getInstance().notifyErrorInLoadingHealths(response.message());
                } else {
                    HealthModel.getInstance().notifyHealthListLoaded(healthListResponse.getHealthList());

                }
            }


        @Override
        public void onFailure(Call<HealthListResponse> call, Throwable throwable) {
            throwable.printStackTrace();
            HealthModel.getInstance().notifyErrorInLoadingHealths(throwable.getMessage());
        }
    });

    }
    @Override
    public void loadJokeList() {
        Call<JokeListResponse> loadJokeCall = theApi.loadJokeList(LuYeeChonConstants.ACCESS_TOKEN);
        loadJokeCall.enqueue(new Callback<JokeListResponse>() {
            @Override
            public void onResponse(Call<JokeListResponse> call, Response<JokeListResponse> response) {
                JokeListResponse jokeListResponse = response.body();
                if (jokeListResponse == null) {
                    JokeModel.getInstance().notifyErrorInLoadingJokes(response.message());
                } else {
                    JokeModel.getInstance().notifyJokeListLoaded(jokeListResponse.getJokeList());
                }
            }


            @Override
            public void onFailure(Call<JokeListResponse> call, Throwable throwable) {
                JokeModel.getInstance().notifyErrorInLoadingJokes(throwable.getMessage());
            }
        });

    }

    @Override
    public void loadMotivator() {

        Call<MotivatorListResponse> loadMotivatorCall = theApi.loadMotivator(LuYeeChonConstants.ACCESS_TOKEN);
        loadMotivatorCall.enqueue(new Callback<MotivatorListResponse>() {  //preparatin state  before request
            @Override
            public void onResponse(Call<MotivatorListResponse> call, Response<MotivatorListResponse> response) {
                MotivatorListResponse motivatorListResponse = response.body();
                if (motivatorListResponse == null) {
                    MotivatorModel.getInstance().notifyErrorInLoadingMotivator(response.message());
                } else {
                    MotivatorModel.getInstance().notifyMotivatorLoaded(motivatorListResponse.getMotivatorList());
                }
            }

            @Override
            public void onFailure(Call<MotivatorListResponse> call, Throwable throwable) {
                MotivatorModel.getInstance().notifyErrorInLoadingMotivator(throwable.getMessage());
            }
        });
    }

    @Override
    public void loadQuiz() {
        Call<QuizListResponse> loadQuizCall = theApi.loadQuiz(LuYeeChonConstants.ACCESS_TOKEN);
        loadQuizCall.enqueue(new Callback<QuizListResponse>() {  //preparatin state  before request
            @Override
            public void onResponse(Call<QuizListResponse> call, Response<QuizListResponse> response) {
                QuizListResponse quizListResponse = response.body();
                if (quizListResponse == null) {
                    QuizModel.getInstance().notifyErrorInLoadingQuiz(response.message());
                } else {
                    QuizModel.getInstance().notifyQuizLoaded(quizListResponse.getQuizList());
                }
            }

            @Override
            public void onFailure(Call<QuizListResponse> call, Throwable throwable) {
                QuizModel.getInstance().notifyErrorInLoadingQuiz(throwable.getMessage());
            }
        });
    }
}
