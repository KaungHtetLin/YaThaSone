package net.sandi.luyeechon.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.sandi.luyeechon.LuYeeChonApp;
import net.sandi.luyeechon.R;
import net.sandi.luyeechon.adapter.MotivatorAdapter;
import net.sandi.luyeechon.data.models.MotivatorModel;
import net.sandi.luyeechon.data.persistence.LuYeeChonContract;
import net.sandi.luyeechon.data.vos.MotivatorVO;
import net.sandi.luyeechon.events.DataEvent;
import net.sandi.luyeechon.utils.LuYeeChonConstants;
import net.sandi.luyeechon.views.holders.MotivatorViewHolder;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * A placeholder fragment containing a simple view.
 */
public class MotivatorFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private MotivatorAdapter mMotivatorAdapter;

    private List<MotivatorVO> motivatorVOs;

    private MotivatorViewHolder.ControllerMotivatorItem mControllerMotivator;


    public static MotivatorFragment newInstance() {
        MotivatorFragment fragment = new MotivatorFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mControllerMotivator = (MotivatorViewHolder.ControllerMotivatorItem) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_motivator, container, false);

        List<MotivatorVO> motivatorList=MotivatorModel.getInstance().getMotivatorList();
        mMotivatorAdapter = new MotivatorAdapter(motivatorList,mControllerMotivator);
        RecyclerView rvMotivator = (RecyclerView) view.findViewById(R.id.rv_motivator);
        rvMotivator.setAdapter(mMotivatorAdapter);

        int columnSpanCount = getResources().getInteger(R.integer.motivaor_list_grid);
        rvMotivator.setLayoutManager(new GridLayoutManager(getContext(),columnSpanCount));

    //    rvMotivator.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(LuYeeChonConstants.MOTIVATOR_LIST_LOADER, null, this);

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }

    public void onEventMainThread(DataEvent.MotivatorDataLoadedEvent event) {
        String extra = event.getExtraMessage();
        //     Toast.makeText(getApplicationContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();

        //    List<AttractionVO> newAttractionList = AttractionModel.getInstance().getAttractionList();
        List<MotivatorVO> newAttractionList = event.getMotivatorList();
        mMotivatorAdapter.setNewData(newAttractionList);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                LuYeeChonContract.MotivatorEntry.CONTENT_URI,
                null,
                null,
                null,
                LuYeeChonContract.MotivatorEntry._ID );
        //  return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<MotivatorVO> motivatorList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                MotivatorVO motivator = MotivatorVO.parseFromCursor(data);
                //  motivator.setImage(Mo.loadAttractionImagesByTitle(motivator.getTitle()));
                motivatorList.add(motivator);
            } while (data.moveToNext());
        }

        Log.d(LuYeeChonApp.TAG, "Retrieved attractions DESC : " + motivatorList.size());
        mMotivatorAdapter.setNewData(motivatorList);

        MotivatorModel.getInstance().setStoredData(motivatorList);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

}
