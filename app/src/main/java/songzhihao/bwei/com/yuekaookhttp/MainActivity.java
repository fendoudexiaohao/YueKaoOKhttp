package songzhihao.bwei.com.yuekaookhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView leftRecyclerView;
    private RecyclerView rightRecylerView;
    private String url = "http://mock.eoapi.cn/success/4q69ckcRaBdxhdHySqp2Mnxdju5Z8Yr4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //请求数据的方法
        getData();
    }
    public void getData() {
        //调用封装的方法
        OkHttpUtils httpUtils = OkHttpUtils.getHttpUtils();
        httpUtils.loadDataFromNet(url,Bean.class, new OkHttpUtils.CallBackListener<Bean>() {
            //请求成功的方法
            @Override
            public void onSuccess(Bean result) {
                ArrayList<String> titleList = new ArrayList<>();
                List<Bean.RsBean> rs = result.getRs();
                if (rs != null && rs.size()>0){
                    for (int i = 0; i < rs.size(); i++) {
                        titleList.add(rs.get(i).getDirName());
                    }
                }
                //左边的标题类表
                leftRecy(titleList,rs);
            }

            @Override
            public void onFail() {

            }
        });
    }

    private void leftRecy(List<String> mList, final List<Bean.RsBean> rs){
        leftRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final LeftRecyAdapter leftRecy = new LeftRecyAdapter(mList, MainActivity.this);
        leftRecyclerView.setAdapter(leftRecy);

        rightRecyclerView(rs.get(0));
        leftRecy.setRecyOnItemClickListener(new LeftRecyAdapter.LeftRecyOnItemClickListener() {
            @Override
            public void leftRecyItemClickListener(int pos) {
                App.pos = pos;
                //右边的
                rightRecyclerView(rs.get(pos));
                leftRecy.notifyDataSetChanged();
            }
        });
    }

    //右边的RecylerView
    private void rightRecyclerView(Bean.RsBean rs){
        rightRecylerView.setLayoutManager(new LinearLayoutManager(this));
        RightRecyAdapter rightRecyAdapter = new RightRecyAdapter(rs, MainActivity.this);
        rightRecylerView.setAdapter(rightRecyAdapter);
    }

    //初始化控件的方法
    private void initView() {
        leftRecyclerView = (RecyclerView) findViewById(R.id.leftRecyclerView);
        rightRecylerView = (RecyclerView) findViewById(R.id.rightRecylerView);
    }



}
