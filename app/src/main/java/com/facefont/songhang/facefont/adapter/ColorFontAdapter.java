package com.facefont.songhang.facefont.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.facefont.songhang.facefont.BaseApplication;
import com.facefont.songhang.facefont.util.FaceFontUtil;
import com.facefont.songhang.facefont.bean.FaceFontBean;
import com.facefont.songhang.facefont.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by songhang on 15/9/21.
 */
public class ColorFontAdapter extends BaseAdapter {
    private Context context;

    private List<ArrayList<FaceFontBean>> list = new ArrayList<>();

    public ColorFontAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<ArrayList<FaceFontBean>> list) {
        if (list == null) {
            return;
        }
        this.list.clear();
        this.list.addAll(list);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ArrayList<FaceFontBean> emojiBeans = (ArrayList<FaceFontBean>) getItem(position);
        if (convertView == null) {
            convertView = new LinearLayout(context);
            AbsListView.LayoutParams convertViewlayoutParams = new AbsListView.LayoutParams(FaceFontUtil.screenWidth, AbsListView.LayoutParams.WRAP_CONTENT);
            convertView.setLayoutParams(convertViewlayoutParams);
            ((LinearLayout) convertView).setGravity(Gravity.CENTER);
            int allWeight = 0;
            for (FaceFontBean emoji : emojiBeans) {
                allWeight += emoji.colorFontWeight;
            }
            for (int i = 0; i < emojiBeans.size(); i++) {
                final FaceFontBean emojibean = emojiBeans.get(i);
                View containerView = LayoutInflater.from(context).inflate(R.layout.color_font_item_layout, null);
                ((LinearLayout) convertView).addView(containerView);
                LinearLayout.LayoutParams layout = (LinearLayout.LayoutParams) containerView.getLayoutParams();
                layout.width = emojibean.colorFontWeight * (FaceFontUtil.screenWidth - FaceFontUtil.dipToPx(10f)) / allWeight;
                layout.height = FaceFontUtil.dipToPx(58f);
                containerView.findViewById(R.id.color_font_item_text_view).setVisibility(View.GONE);
                TextView textView = (TextView) containerView.findViewById(R.id.color_font_item_text_view_target);
                textView.setText(emojibean.message);
                textView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                textView.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
                textView.setOnClickListener(new View.OnClickListener() {

                                                @Override
                                                public void onClick(View v) {
                                                    Toast.makeText(BaseApplication.getInstance(), "curFaceFont: " + emojibean.message, Toast.LENGTH_SHORT).show();
                                                }
                                            }

                );
            }
        }
        return convertView;
    }
}
