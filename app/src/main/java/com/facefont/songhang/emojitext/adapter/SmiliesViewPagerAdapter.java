package com.facefont.songhang.emojitext.adapter;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class SmiliesViewPagerAdapter extends FragmentStatePagerAdapter {

	private ArrayList< ? extends Fragment> mArrayFrament;
	
	public SmiliesViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}
	
	public void setArrayFrament(ArrayList< ? extends Fragment> arrayFrament){
		mArrayFrament = arrayFrament;
	}
	
	@Override
	public Fragment getItem(int index) {
		return mArrayFrament == null ? null : mArrayFrament.get(index);
	}
	
	@Override
	public int getCount() {
		return mArrayFrament == null ? 0 : mArrayFrament.size();
	}

}
