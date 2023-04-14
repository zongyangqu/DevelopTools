package com.quzy.coding.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.quzy.coding.R;

/**
 * CreateDate:2023/4/14 17:03
 *
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.fragment
 * @Description:
 */
public class ViewPagerFragment extends Fragment {


    public ViewPagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.view_pager_fragment, container, false);
        TextView viewById = (TextView) root.findViewById(R.id.textview1);
        String key = getArguments().getString("key");
        viewById.setText(key);
        if("blue".equals(key)){
            viewById.setBackgroundColor(getResources().getColor(R.color.color_orange));
        }else if("red".equals(key)){
            viewById.setBackgroundColor(getResources().getColor(R.color.text_blue));
        }else {
            viewById.setBackgroundColor(getResources().getColor(R.color.progress_color_2));
        }
        return root;
    }

}
