package com.facefont.songhang.emojitext.util;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.TextView;

import com.facefont.songhang.emojitext.BaseApplication;
import com.facefont.songhang.emojitext.bean.FaceFontBean;
import com.facefont.songhang.emojitext.R;
import com.facefont.songhang.emojitext.bean.FaceFontPool;
import com.facefont.songhang.emojitext.fragment.ColorFontFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songhang on 15/10/29.
 */
public class FaceFontUtil implements FaceFontPool {
    public static int screenWidth = ((WindowManager) BaseApplication.getInstance().getSystemService(Context.WINDOW_SERVICE))
            .getDefaultDisplay().getWidth();

    /**
     * 每页行数
     */
    private static final int onePageLine = 3;

    /**
     * 每行最大数量
     */
    private static final int oneLineMaxCount = 4;

    private static List<FaceFontBean> getFaceBeanList() {
        List<FaceFontBean> list = new ArrayList<>();
        for (String str : faceFontData) {
            FaceFontBean faceFontBean = new FaceFontBean();
            faceFontBean.message = str;
            list.add(faceFontBean);

        }
        return list;
    }

    private static ArrayList<ArrayList<ArrayList<FaceFontBean>>> getFaceBeanPage() {
        List<FaceFontBean> arrayList = getFaceBeanList();
        ArrayList<ArrayList<ArrayList<FaceFontBean>>> arrayListArrayList = new ArrayList<>();
        //targetView，换算每个View宽度
        //当前行数
        int curLine = 0;
        //行数量
        int lineNum = 0;
        int lineTotalWidth = 0;
        //每页list
        ArrayList<ArrayList<FaceFontBean>> pageList = new ArrayList<>();
        //每行list
        ArrayList<FaceFontBean> lineList = new ArrayList<>();
        pageList.add(lineList);
        arrayListArrayList.add(pageList);

        TextView targetView = getTargetTextView();
        for (int i = 0; i < arrayList.size(); i++) {
            FaceFontBean emojiBean = arrayList.get(i);
            int itemWidth = getTargetMeasureWidth(targetView, emojiBean);
            lineTotalWidth += itemWidth;
            lineNum++;
            //添加当前行
            if ((lineTotalWidth <= screenWidth && lineNum <= oneLineMaxCount) || (lineNum == 1 && lineTotalWidth > screenWidth)) {
                lineList.add(emojiBean);
            } else {
                //移动到下一行
                curLine++;
                //初始化当前总宽度
                lineTotalWidth = itemWidth;
                //重置
                lineNum = 1;
                //切换到下一屏幕
                if (curLine >= onePageLine) {
                    curLine = 0;
                    pageList = new ArrayList<>();
                    arrayListArrayList.add(pageList);
                }
                lineList = new ArrayList<>();
                pageList.add(lineList);
                lineList.add(emojiBean);
            }
        }
        if (arrayListArrayList.get(0).get(0).size() > 0) {
            return arrayListArrayList;
        }
        return null;
    }

    private static TextView getTargetTextView() {
        TextView targetView = (TextView) LayoutInflater.from(BaseApplication.getInstance()).inflate(R.layout.color_font_item_layout, null).findViewById(R.id.color_font_item_text_view);
        return targetView;
    }

    /**
     * 得到目标测量宽度权重
     *
     * @param targetView
     * @param emojiBean
     * @return
     */
    private static int getTargetMeasureWidth(TextView targetView, FaceFontBean emojiBean) {
        if (targetView == null || emojiBean == null) {
            return 0;
        }

        String str = emojiBean.message;
        Log.i("songhang", "targetView: " + targetView + "     str: " + str);
        targetView.setText(str);
        targetView.measure(0, 0);
        int w = targetView.getMeasuredWidth();
        if (w < screenWidth / 4.f) {
            w = (int) (screenWidth / 4.f);
            emojiBean.colorFontWeight = 1;
        } else if (w < screenWidth / 2.f) {
            w = (int) (screenWidth / 2.f);
            emojiBean.colorFontWeight = 2;
        } else if (w < screenWidth / 4.f * 3) {
            w = (int) (screenWidth / 4.f * 3);
            emojiBean.colorFontWeight = 3;
        } else {
            w = screenWidth;
            emojiBean.colorFontWeight = 4;
        }
        return w;
    }


    public static ArrayList<? extends Fragment> getArrayFragment() {
        ArrayList<ArrayList<ArrayList<FaceFontBean>>> faceFonListList = getFaceBeanPage();
        ArrayList<ColorFontFragment> mArrayList = new ArrayList<ColorFontFragment>();
        for (int i = 0; i < faceFonListList.size(); i++) {
            ColorFontFragment colorFontFragment = ColorFontFragment.newInstance(faceFonListList.get(i));
            mArrayList.add(colorFontFragment);
        }
        return mArrayList;
    }

    public static int dipToPx(float dipValue) {
        final float scale = BaseApplication.getInstance().getResources().getDisplayMetrics().density;
        int pxValue = (int) (dipValue * scale + 0.5f);

        return pxValue;
    }

}
