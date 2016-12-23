package test.bawei.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import test.bawei.com.bean.MyBean;
import test.bawei.com.qiaozhifeng20161222.R;

/**
 * Created by lenovo-pc on 2016/12/22.
 */
public class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder> {

    List<MyBean.RsEntity.ChildrenEntityX> list;
    Context context;

    public RightAdapter(List<MyBean.RsEntity.ChildrenEntityX> list, Context context) {
        this.list = list;
        this.context = context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.right_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.right_tv = (TextView) view.findViewById(R.id.right_tv);
        viewHolder.right_rv = (GridView) view.findViewById(R.id.right_rv);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.right_tv.setText(list.get(position).getDirName());
        //holder.right_rv.setLayoutManager(new GridLayoutManager(context, 4, LinearLayoutManager.VERTICAL, false));
        List<MyBean.RsEntity.ChildrenEntityX.ChildrenEntity> children = list.get(position).getChildren();
        RightGridAdapter rightGridAdapter = new RightGridAdapter(context, children);
        //设置适配器
        holder.right_rv.setAdapter(rightGridAdapter);
        //设置点击事件
        holder.right_rv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "点击了" + list.get(position).getDirName(), Toast.LENGTH_SHORT).show();
            }
        });
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        TextView right_tv;
        GridView right_rv;
    }

    /**
     * ItemClick的回调接口
     *
     * @author zhy
     */
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private LeftAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(LeftAdapter.OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

}
