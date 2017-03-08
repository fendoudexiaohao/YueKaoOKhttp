package songzhihao.bwei.com.yuekaookhttp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 作者： 宋智豪
 * * 时间： 2017/3/8 20:04
 * * 描述： 尚未编写描述
 */

public class MyGridAdapter extends BaseAdapter{
    private List<Bean.RsBean.ChildrenBeanX.ChildrenBean> children;
    private final Context context;
    public MyGridAdapter(List<Bean.RsBean.ChildrenBeanX.ChildrenBean> children, Context context) {
        this.children = children;
        this.context = context;
    }

    @Override
    public int getCount() {
        return children == null && children.size()>0 ? 0 : children.size();
    }

    @Override
    public Object getItem(int i) {
        return children.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.grid_item_layout, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.textView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(children.get(position).getImgApp()).into(holder.imageView);
        holder.textView.setText(children.get(position).getDirName());
        notifyDataSetChanged();
        return convertView;
    }

    class ViewHolder {
        TextView textView;
        ImageView imageView;
    }
}
