package com.quzy.coding.base.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.View;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * CreateDate:2023/2/14 11:27
 *
 * @author: zongyang qu
 * @Package： com.quzy.coding.base.fragment
 * @Description:
 */
public abstract class BaseAnalyticsFragment extends Fragment implements IPageParams, IStatisticsPage {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String PAGE_ID = "yh_pageId";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    protected boolean mFragmentVisible;
    protected boolean isHidden = true;
    protected boolean isUserVisible;
    protected boolean isResume;

    //支持记录页面进出事件，默认不支持
    protected boolean enablePageView = false;

    private boolean isPageViewRecorded;
    private static final String ASURON_NAME = "sauron_name";
    private static final String PRI_V = "pri_v";
    private String pageIdTime = "";

    @Override
    public boolean isEnablePageView(){
        return enablePageView;
    }

    protected void setEnablePageView(boolean enablePageView){
        this.enablePageView = enablePageView;
    }


    public BaseAnalyticsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageIdTime = "page_" + System.currentTimeMillis();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    @CallSuper
    public void onResume() {
        super.onResume();
        isResume = true;

        //从下一个activity回到此fragment只会触发onResume
        if(isCurrentFragment()) {
            onFragmentVisibilityChanged(true);
        }

    }

    private boolean isCurrentFragment(){
        //单个fragment resume即认为可见
        if(getActivity()!= null
                && getActivity().getSupportFragmentManager()!= null
                && getActivity().getSupportFragmentManager().getFragments() != null){
            if(getActivity().getSupportFragmentManager().getFragments().size() == 1){
                return true;
            }
        }

        return !isHidden || isUserVisible;
    }

    @Override
    public void onPause() {
        super.onPause();
        isResume = false;
        onFragmentVisibilityChanged(false);
    }

    @Override
    @CallSuper
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        isHidden = hidden;
        onFragmentVisibilityChanged(!hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isUserVisible = isVisibleToUser;
        onFragmentVisibilityChanged(isVisibleToUser);
    }

    /**
     * Must implement this method
     *
     * @return Analytics Tracking display name
     */
    @Override
    public String getAnalyticsDisplayName(){ return null;};

    @org.jetbrains.annotations.Nullable
    @Override
    public Activity getContainerActivity() {
        return getActivity();
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected void onFragmentVisibilityChanged(boolean visible){
        if(!isAdded()){
            return;
        }
        if(visible != mFragmentVisible){
            mFragmentVisible = visible;
        }
    }




}
