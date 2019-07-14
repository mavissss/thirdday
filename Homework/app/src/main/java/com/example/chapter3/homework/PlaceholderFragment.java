package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        return inflater.inflate(R.layout.fragment_placeholder, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final LottieAnimationView animationView=getActivity().findViewById(R.id.animation_view2);
        final ListView listView=getActivity().findViewById(R.id.lv_buddy);
        listView.setAlpha(0f);
        animationView.playAnimation();
        listView.setAdapter(new ListViewBaseAdapter(getActivity()));
        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                ObjectAnimator lottiealphaAnimation=ObjectAnimator.ofFloat(animationView,"alpha",1f,0f);
                lottiealphaAnimation.setDuration(1000);
                lottiealphaAnimation.setInterpolator(new LinearInterpolator());
                ObjectAnimator listalphaAnimation=ObjectAnimator.ofFloat(listView,"alpha",0f,1f);
                listalphaAnimation.setDuration(1000);

                listalphaAnimation.setInterpolator(new LinearInterpolator());
                // TODO ex2-3: 将上面创建的其他 ObjectAnimator 都添加到 AnimatorSet 中
                AnimatorSet animatorSet= new AnimatorSet();

                animatorSet.playTogether(lottiealphaAnimation,listalphaAnimation);
                animatorSet.start();

            }
        }, 5000);
    }
    //自定义适配器
    public class ListViewBaseAdapter extends BaseAdapter {

        private int[] args =
                new int[] {1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 1, 2, 3, 4, 5, 7, 8, 9, 10,
                        11, 12, 13, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 1, 2, 3, 4, 5, 7,
                        8, 9, 10, 11, 12, 13, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13};

        private Context mContext;

        public ListViewBaseAdapter(Context context) {
            mContext = context;
        }

        @Override public int getCount() {
            return args.length;
        }

        @Override public Object getItem(int i) {
            return null;
        }

        @Override public long getItemId(int i) {
            return 0;
        }

        @Override public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView;
            if (convertView == null) {
                textView = new TextView(mContext);
            } else {
                textView = (TextView) convertView;
            }

            textView.setText(" " + args[position]);
            textView.setTextSize(18);
            return textView;
        }
    }
}
