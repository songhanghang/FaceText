package com.facefont.songhang.emojitext.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.facefont.songhang.emojitext.R;
import com.facefont.songhang.emojitext.adapter.ColorFontAdapter;
import com.facefont.songhang.emojitext.bean.FaceFontBean;

import java.util.ArrayList;

/**
 * 颜文字Fragment
 * @author yanjuli
 *
 */
public class ColorFontFragment extends Fragment {
    public static ColorFontFragment newInstance(ArrayList<ArrayList<FaceFontBean>> colorFontList) {
		Bundle args = new Bundle();
		ColorFontFragment fragment = new ColorFontFragment();
		args.putSerializable("color_font", colorFontList);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.emoji_color_font, null);
        ListView listView = (ListView) view.findViewById(R.id.color_font_list_view);
        Bundle args = getArguments();
        ArrayList<ArrayList<FaceFontBean>> colorFontList = (ArrayList<ArrayList<FaceFontBean>>) args.getSerializable("color_font");
        ColorFontAdapter colorFontAdapter = new ColorFontAdapter(getActivity());
        colorFontAdapter.setList(colorFontList);
        listView.setAdapter(colorFontAdapter);
		return view;
	}
}
