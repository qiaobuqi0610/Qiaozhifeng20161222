package test.bawei.com.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import test.bawei.com.bean.MyBean;
import test.bawei.com.qiaozhifeng20161222.R;

/**
 * Created by lenovo-pc on 2016/12/22.
 */

public class RightFragment extends Fragment {

    //private String title;
    private RecyclerView right_recycler;
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                list = (List<MyBean.RsEntity>) msg.obj;
            }
        }
    };
    private List<MyBean.RsEntity> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.right_fragment, null);
        right_recycler = (RecyclerView) view.findViewById(R.id.right_recycler);
        // this.title = RightFragment.this.getArguments().getString("title", "宝宝奶粉");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new Thread() {
            @Override
            public void run() {
                //请求数据
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
                        MyBean myBean = gson.fromJson(htmlStr, MyBean.class);
                        List<MyBean.RsEntity> rs = myBean.getRs();
                        handler.obtainMessage(0, rs).sendToTarget();
                    }
                });
            }
        }.start();
    }
}
