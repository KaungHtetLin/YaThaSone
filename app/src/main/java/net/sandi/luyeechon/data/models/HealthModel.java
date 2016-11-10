package net.sandi.luyeechon.data.models;

import net.sandi.luyeechon.data.vos.HealthVO;
import net.sandi.luyeechon.events.DataEvent;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by aung on 6/25/16.
 */
public class HealthModel extends BaseModel{

//    private static final String DUMMY_HEALTH_LIST = "health_list.json";

    private static HealthModel objInstance;

    private List<HealthVO> healthVOList;

    private HealthModel(){
            healthVOList = new ArrayList<HealthVO>();
            dataAgent.loadHealthList();
    }

    public static HealthModel getInstance(){
        if(objInstance == null) {
            objInstance = new HealthModel();
        }

        return objInstance;
    }
    public List<HealthVO> getHealthVOList() {
        return healthVOList;
    }

    private void broadcastHealthsLoadedWithEventBus() {
        EventBus.getDefault().post(new DataEvent.HealthDataLoadedEvent("extra-in-broadcast", healthVOList));
    }
    public void notifyHealthListLoaded(List<HealthVO> healthVOList) {
        //Notify that the data is ready - using LocalBroadcast
        this.healthVOList = healthVOList;

        //keep the data in persistent layer.
        HealthVO.saveHealths(healthVOList);

        broadcastHealthsLoadedWithEventBus();
    }

    public void notifyErrorInLoadingHealths(String message) {

    }

    public void setStoredData(List<HealthVO> healthList) {
        healthVOList = healthList;
    }
}
