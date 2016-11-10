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
import net.sandi.luyeechon.adapter.JokeAdapter;
import net.sandi.luyeechon.data.models.JokeModel;
import net.sandi.luyeechon.data.persistence.LuYeeChonContract;
import net.sandi.luyeechon.data.vos.JokeVO;
import net.sandi.luyeechon.utils.LuYeeChonConstants;
import net.sandi.luyeechon.views.holders.JokeViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class JokeFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{


    private JokeAdapter mJokeAdpater;
    private JokeViewHolder.ControllerJokeItem mControllerJoke;

    private List<JokeVO> jokeVOs;


    public JokeFragment() {
        jokeVOs= JokeModel.getInstance().getJokeVOList();
        int size = jokeVOs.size();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mControllerJoke = (JokeViewHolder.ControllerJokeItem) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joke, container, false);
        mJokeAdpater = new JokeAdapter(jokeVOs,mControllerJoke);
        RecyclerView rvJoke = (RecyclerView)view.findViewById(R.id.rv_jokes);
        rvJoke.setAdapter(mJokeAdpater);

        int columnSpanCount = getResources().getInteger(R.integer.joke_list_grid);
        rvJoke.setLayoutManager(new GridLayoutManager(getContext(),columnSpanCount));
//        rvJoke.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        return view;
    }

    public static JokeFragment newInstance() {
        return new JokeFragment();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                LuYeeChonContract.JokeEntry.CONTENT_URI,
                null,
                null,
                null,
                LuYeeChonContract.JokeEntry._ID + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<JokeVO> jokeList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                JokeVO joke = JokeVO.parseFromCursor(data);
                jokeList.add(joke);
            } while (data.moveToNext());
        }

        Log.d(LuYeeChonApp.TAG, "Retrieved jokes DESC : " + jokeList.size());
        mJokeAdpater.setNewData(jokeList);

        JokeModel.getInstance().setStoredData(jokeList);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(LuYeeChonConstants.JOKE_LIST_LOADER, null, this);
    }

}
