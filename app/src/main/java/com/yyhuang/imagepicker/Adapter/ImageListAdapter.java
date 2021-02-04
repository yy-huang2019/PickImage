package com.yyhuang.imagepicker.Adapter;

import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.yyhuang.imagepicker.R;
import com.yyhuang.imagepicker.domain.ImageItem;
import java.util.ArrayList;
import java.util.List;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.InnerHolder> {

    private List<ImageItem> mImageItems = new ArrayList<>();

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载图片
        View itemView = View.inflate(parent.getContext(), R.layout.item_image, null);
        //设置图片长宽
        Point screenSize = SizeUtils.getScreenSize(itemView.getContext());
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(screenSize.x/3,screenSize.y/3);
        itemView.setLayoutParams(layoutParams);
        return new InnerHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        //绑定数据
        ImageView imageView = holder.itemView.findViewById(R.id.image_iv);
        Glide.with(imageView.getContext()).load(mImageItems.get(position).getPath()).into(imageView);
    }

    @Override
    public int getItemCount() {
        return mImageItems.size();
    }

    public void setData(List<ImageItem> ImageItems) {
        mImageItems.clear();
        mImageItems.addAll(ImageItems);
        //跟新UI
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
