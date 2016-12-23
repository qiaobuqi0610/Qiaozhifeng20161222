package test.bawei.com.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import test.bawei.com.qiaozhifeng20161222.R;

/**
 * Created by lenovo-pc on 2016/12/22.
 */
public class MyLeftAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> list;

    public MyLeftAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.left_item, null);
        TextView left_tv = (TextView) view.findViewById(R.id.left_tv);
        left_tv.setText(list.get(position));
        return view;
    }
}
