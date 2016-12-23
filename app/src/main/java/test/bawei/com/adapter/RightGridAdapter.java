package test.bawei.com.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import test.bawei.com.bean.MyBean;
import test.bawei.com.qiaozhifeng20161222.R;

/**
 * Created by lenovo-pc on 2016/12/22.
 */
public class RightGridAdapter extends BaseAdapter {
    Context context;
    List<MyBean.RsEntity.ChildrenEntityX.ChildrenEntity> list;

    public RightGridAdapter(Context context, List<MyBean.RsEntity.ChildrenEntityX.ChildrenEntity> list) {
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.right_grid_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //进行赋值
        viewHolder.gridtv.setText(list.get(position).getDirName());
        ImageLoader.getInstance().displayImage(list.get(position).getImgApp(), viewHolder.gridimg);
        return convertView;
    }

    public class ViewHolder {
        public final ImageView gridimg;
        public final TextView gridtv;
        public final View root;

        public ViewHolder(View root) {
            gridimg = (ImageView) root.findViewById(R.id.grid_img);
            gridtv = (TextView) root.findViewById(R.id.grid_tv);
            this.root = root;
        }
    }
}
