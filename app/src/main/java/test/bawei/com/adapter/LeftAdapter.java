package test.bawei.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import test.bawei.com.qiaozhifeng20161222.R;

/**
 * Created by lenovo-pc on 2016/12/22.
 */
public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder> {
    Context context;
    ArrayList<String> list;
    private ViewHolder viewHolder;
    private OnItemClickLitener mOnItemClickLitener;

    /**
     * ItemClick的回调接口
     */
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public LeftAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.left_item, null);
        viewHolder = new ViewHolder(view);
        viewHolder.left_tv = (TextView) view.findViewById(R.id.left_tv);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.left_tv.setText(list.get(position));
        //判断点击事件
        if (mOnItemClickLitener != null) {
            //点击事件
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

        TextView left_tv;
    }

}
