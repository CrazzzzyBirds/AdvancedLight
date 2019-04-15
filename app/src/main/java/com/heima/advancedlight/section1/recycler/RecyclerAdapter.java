package com.heima.advancedlight.section1.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heima.advancedlight.R;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * adapter-ViewHolder-ButterKnife
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    @BindView(R.id.tv_item)
    TextView tvItem;
    private List<String> mList;


    public RecyclerAdapter(List<String> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        //设置瀑布流-随机高度
        ViewGroup.LayoutParams layoutParams=myViewHolder.tv_item.getLayoutParams();
        layoutParams.height=new Random().nextInt(20)+50;
       myViewHolder.tv_item.setLayoutParams(layoutParams);

        myViewHolder.tv_item.setText(mList.get(i));
        myViewHolder.tv_item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener!=null){
                    mOnItemClickListener.onItemLongClick(v,i);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item)
        TextView tv_item;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.tv_item)
        void onclick() {
            if (mOnItemClickListener!=null){
                mOnItemClickListener.onItemClick(tv_item,111);
            }

        }

    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
