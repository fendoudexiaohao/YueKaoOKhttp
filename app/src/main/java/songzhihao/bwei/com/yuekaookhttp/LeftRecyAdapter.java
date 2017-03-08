package songzhihao.bwei.com.yuekaookhttp;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * 作者： 宋智豪
 * * 时间： 2017/3/8 19:20
 * * 描述： 尚未编写描述
 */

public class LeftRecyAdapter extends RecyclerView.Adapter<LeftRecyAdapter.MyViewHolder>{


    private LeftRecyOnItemClickListener recyOnItemClickListener;
    public void setRecyOnItemClickListener(LeftRecyOnItemClickListener recyOnItemClickListener) {
        this.recyOnItemClickListener = recyOnItemClickListener;
    }

    private List<String> mList;
    private  Context context;
    public LeftRecyAdapter(List<String> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        //赋值
        holder.textView.setText(mList.get(position).toString());
        //点击改变背景颜色
        if (position == App.pos){
            holder.textView.setBackgroundColor(Color.WHITE);
        }else{
            holder.textView.setBackgroundColor(context.getResources().getColor(R.color.defaultColor));
        }
        //点击事件
        if (recyOnItemClickListener!=null){
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int layoutPosition = holder.getLayoutPosition();
                    recyOnItemClickListener.leftRecyItemClickListener(layoutPosition);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }

    //定义一个接口
        public interface LeftRecyOnItemClickListener{
        void leftRecyItemClickListener(int pos);
    }
}
