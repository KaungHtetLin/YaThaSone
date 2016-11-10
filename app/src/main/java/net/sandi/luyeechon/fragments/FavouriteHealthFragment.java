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
import net.sandi.luyeechon.adapter.FavouriteHealthAdapter;
import net.sandi.luyeechon.data.persistence.LuYeeChonContract;
import net.sandi.luyeechon.data.vos.HealthVO;
import net.sandi.luyeechon.utils.LuYeeChonConstants;
import net.sandi.luyeechon.views.holders.HealthViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteHealthFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{


    private FavouriteHealthAdapter mHealthAdpater;
    private HealthViewHolder.ControllerHealthItem mControllerHealth;

    private List<HealthVO> healthVo;


    public FavouriteHealthFragment() {
        healthVo = new ArrayList<HealthVO>();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mControllerHealth = (HealthViewHolder.ControllerHealthItem) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_health, container, false);
        mHealthAdpater = new FavouriteHealthAdapter(healthVo,mControllerHealth);
        RecyclerView rvHealth = (RecyclerView)view.findViewById(R.id.rv_fav_healths);
        rvHealth.setAdapter(mHealthAdpater);

        int columnSpanCount = getResources().getInteger(R.integer.health_list_grid);
        rvHealth.setLayoutManager(new GridLayoutManager(getContext(), columnSpanCount));

        return view;
    }

    public static FavouriteHealthFragment newInstance() {
        return new FavouriteHealthFragment();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                LuYeeChonContract.FavouriteHealthsEntry.CONTENT_URI,
                null,
                null,
                null,
                LuYeeChonContract.FavouriteHealthsEntry._ID + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<HealthVO> healthList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                HealthVO healthVO = HealthVO.parseFromCursorFavourite(data);
                healthList.add(healthVO);
            } while (data.moveToNext());
        }

        Log.d(LuYeeChonApp.TAG, "Retrieved jokes DESC : " + healthList.size());
        mHealthAdpater.setNewData(healthList);


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(LuYeeChonConstants.FAV_HEALTH_LIST_LOADER, null, this);
    }

}
