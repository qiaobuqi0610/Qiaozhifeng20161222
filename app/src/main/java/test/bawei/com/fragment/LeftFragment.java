package test.bawei.com.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import test.bawei.com.adapter.LeftAdapter;
import test.bawei.com.adapter.RightAdapter;
import test.bawei.com.bean.MyBean;
import test.bawei.com.qiaozhifeng20161222.R;

/**
 * Created by lenovo-pc on 2016/12/22.
 */

public class LeftFragment extends Fragment {

    private ListView left_lv;
    ArrayList<String> strlist = new ArrayList<>();
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                list = (List<MyBean.RsEntity>) msg.obj;
                for (int i = 0; i < list.size(); i++) {
                    strlist.add(list.get(i).getDirName());
                }
                //设置适配器
                left_lv.setAdapter(new MyLeftAdapter(getActivity(), strlist));
                //设置带点击事件
                left_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getActivity(), "点击了" + strlist.get(position), Toast.LENGTH_SHORT).show();
                        //得到右边的空间
                        RecyclerView right_recycler = (RecyclerView) getActivity().findViewById(R.id.right_recycler);
                        List<MyBean.RsEntity> rs = myBean.getRs();

                        List<MyBean.RsEntity.ChildrenEntityX> children = rs.get(position).getChildren();
                        //设置管理
                        right_recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        //设置适配器
                        right_recycler.setAdapter(new RightAdapter(children, getActivity()));

                    }
                });
            }
        }
    };
    private List<MyBean.RsEntity> list;
    private LeftAdapter leftAdapter;
    private MyBean myBean;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.left_fragment, null);
        left_lv = (ListView) view.findViewById(R.id.left_lv);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new Thread() {
            @Override
            public void run() {
                //请求网络得到值
                OkHttpClient mOkHttpClient = new OkHttpClient();
                //创建一个Request
                final Request request = new Request.Builder()
                        .url("http://mock.eoapi.cn/success/4q69ckcRaBdxhdHySqp2Mnxdju5Z8Yr4")
                        .build();
                //new call
                Call call = mOkHttpClient.newCall(request);
                //请求加入调度
                call.enqueue(new Callback() {

                    @Override
                    public void onFailure(Request request, IOException e) {
                    }

                    @Override
                    public void onResponse(final Response response) throws IOException {
                        String htmlStr = response.body().string();
                        //进行解析
                        Gson gson = new Gson();
                        myBean = gson.fromJson(htmlStr, MyBean.class);
                        List<MyBean.RsEntity> rs = myBean.getRs();
                        //发送到handler
                        handler.obtainMessage(0, rs).sendToTarget();
                    }
                });
            }
        }.start();

    }
}
