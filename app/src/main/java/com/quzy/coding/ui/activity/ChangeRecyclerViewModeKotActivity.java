package com.quzy.coding.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;
import com.quzy.coding.bean.Ware;
import com.quzy.coding.bean.WareEntity;
import com.quzy.coding.bean.dummy.DummyContent;
import com.quzy.coding.ui.adapter.AssortShowAdapter;
import com.quzy.coding.ui.adapter.HotAdapter;
import com.quzy.coding.ui.adapter.MyItemRecyclerViewAdapter;
import com.quzy.coding.util.JsonUtils;
import com.quzy.coding.util.widget.ChangeModeRecyclerView;
import com.quzy.coding.util.widget.CnToolbar;
import com.quzy.coding.util.widget.MyDivider;

import java.util.List;

/**
 * CreateDate:2021/11/10 10:14
 *
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description:
 */
public class ChangeRecyclerViewModeKotActivity extends BaseActivity {

    RecyclerView recyclerView ;

    CnToolbar cnToolbar ;
    TextView showWaresNum ;
    public static final int SHOW_TYPE_LINER = 0 ;
    public static final int SHOW_TYPE_GRID = 1 ;
    private HotAdapter hotAdapter ;
    private AssortShowAdapter classifyWaresAdapter ;
   // private AssortShowAdapter classifyWaresAdapter ;
    public int showType = SHOW_TYPE_LINER ;
    private StaggeredGridLayoutManager recyclerViewLayoutManager;

    @Override
    protected void onViewCreated() {
        recyclerView = (RecyclerView) findViewById(R.id.recycleListView);
        cnToolbar = (CnToolbar) findViewById(R.id.toolBar);
        addShowStyleChengeListener();
        try {
            InitShowOrder() ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {


            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstVisiblePosition = getFirstVisiblePosition();
                Log.d("position---->", firstVisiblePosition +"");
            }
        });
    }

    private int firstVisiblePosition;
    private List<Ware> data;
    private void InitShowOrder() throws Exception {
        WareEntity wareEntity = JsonUtils.analysisWareJsonFile(this,"ware");
        data = wareEntity.wareList;
        //initMaterialRefrshLayoutListener() ;
        initGridMaterialRefrshLayoutListener();
    }


    public void initMaterialRefrshLayoutListener () throws Exception {
        if(data != null && data.size()>0){
            if(hotAdapter == null) {
                hotAdapter = new HotAdapter();
                hotAdapter.refresh(data);
            }
            recyclerView.setAdapter(hotAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(ChangeRecyclerViewModeKotActivity.this));
            recyclerView.getLayoutManager().scrollToPosition(firstVisiblePosition);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_mode_recyclerview2;
    }

    @Override
    protected View getLayoutView() {
        return null;
    }

    /**
     * 展示格式切换
     */
    private void addShowStyleChengeListener(){
        cnToolbar.setRightImgeButtonIcOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                String style = showType == SHOW_TYPE_LINER ?"列表模式" : "瀑布流模式";
                Toast.makeText(ChangeRecyclerViewModeKotActivity.this , "切换格式："+style , Toast.LENGTH_SHORT).show();
                try {
                    changeShowType();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * Grid布局展示商品
     * @throws Exception
     */
    public void initGridMaterialRefrshLayoutListener () throws Exception {
        if(data != null && data.size()>0){
            if(classifyWaresAdapter == null){
                recyclerViewLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
               // recyclerViewLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
                classifyWaresAdapter = new AssortShowAdapter();
                recyclerView.setAdapter(classifyWaresAdapter);
                recyclerView.setLayoutManager(recyclerViewLayoutManager);
                recyclerView.addItemDecoration(new MyDivider());
                classifyWaresAdapter.refresh(data);
            }else{
                recyclerView.setAdapter(classifyWaresAdapter);
                recyclerView.setLayoutManager(recyclerViewLayoutManager);
//                classifyWaresAdapter.refresh(data);

            }
            recyclerView.getLayoutManager().scrollToPosition(firstVisiblePosition);
        }else {
            Toast.makeText(ChangeRecyclerViewModeKotActivity.this , "该类别暂无商品" , Toast.LENGTH_SHORT) .show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void changeShowType() throws Exception {
        switch (showType){
            case SHOW_TYPE_LINER :
                initMaterialRefrshLayoutListener() ;
                cnToolbar.setRightButtonIcon(R.drawable.icon_grid_32);
                showType = SHOW_TYPE_GRID ;
                break;
            case SHOW_TYPE_GRID :
                initGridMaterialRefrshLayoutListener() ;
                cnToolbar.setRightButtonIcon(R.drawable.icon_list_32);
                showType = SHOW_TYPE_LINER ;
                break;
        }
    }


    /**
     * 获取第一条展示的位置
     *
     * @return
     */
    private int getFirstVisiblePosition() {
        int position;
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        } else if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            position = ((GridLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        } else if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
            int[] lastPositions = layoutManager.findFirstVisibleItemPositions(new int[layoutManager.getSpanCount()]);
            position = getMinPositions(lastPositions);
        } else {
            position = 0;
        }
        return position;
    }

    /**
     * 获得当前展示最小的position
     *
     * @param positions
     * @return
     */
    private int getMinPositions(int[] positions) {
        int size = positions.length;
        int minPosition = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            minPosition = Math.min(minPosition, positions[i]);
        }
        return minPosition;
    }
}
