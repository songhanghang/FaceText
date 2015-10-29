package com.facefont.songhang.facefont.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facefont.songhang.facefont.R;
import com.facefont.songhang.facefont.View.CirclePageIndicator;
import com.facefont.songhang.facefont.adapter.SmiliesViewPagerAdapter;
import com.facefont.songhang.facefont.util.FaceFontUtil;

import java.util.ArrayList;


public class ViewPagerFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ViewPager mEmojiViewPager;

    private CirclePageIndicator mCirclePageIndicator;

    private SmiliesViewPagerAdapter mEmojiViewPagerAdapter;

    public static ViewPagerFragment newInstance(String param1, String param2) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ViewPagerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mEmojiViewPager = (ViewPager) view.findViewById(R.id.emoji_viewPager);
        mCirclePageIndicator = (CirclePageIndicator) view.findViewById(R.id.emoji_circlepageIndicator);
        mEmojiViewPagerAdapter = new SmiliesViewPagerAdapter(getChildFragmentManager());
        updateEmojiViewPager(FaceFontUtil.getArrayFragment());
        mCirclePageIndicator.setViewPager(mEmojiViewPager);
        super.onViewCreated(view, savedInstanceState);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public void updateEmojiViewPager(ArrayList< ? extends Fragment> arrayFragment){
        if (arrayFragment == null) {
            return;
        }
        mEmojiViewPagerAdapter.setArrayFrament(arrayFragment);
        mEmojiViewPager.setAdapter(mEmojiViewPagerAdapter);
        mCirclePageIndicator.notifyDataSetChanged2();

    }

}
