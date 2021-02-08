package com.yyhuang.imagepicker.Adapter;

import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yyhuang.imagepicker.R;
import com.yyhuang.imagepicker.domain.ImageItem;

import java.util.ArrayList;
import java.util.List;

public class ResultImageAdapter extends RecyclerView.Adapter<ResultImageAdapter.Innerholder> {

    private List<ImageItem> mImageItem = new ArrayList<>();
    private int mHorizontalCount = 1;
    private View itemView;

    @NonNull
    @Override
    public Innerholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载图片
        itemView = View.inflate(parent.getContext(), R.layout.item_image, null);
        CheckBox checkBox = itemView.findViewById(R.id.checked);
        checkBox.setVisibility(View.GONE);

        return new Innerholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Innerholder holder, int position) {
        //绑定数据
        View itemView = holder.itemView;
        Point point = SizeUtils.getScreenSize(itemView.getContext());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(point.x / mHorizontalCount, point.x / mHorizontalCount);
        itemView.setLayoutParams(layoutParams);
        //点击选中的图片
        ImageItem imageItem = mImageItem.get(position);
        ImageView imageView = itemView.findViewById(R.id.image_iv);
        Glide.with(imageView.getContext()).load(imageItem.getPath()).into(imageView);
    }

    @Override
    public int getItemCount() {
        return mImageItem.size();
    }

    public void setData(List<ImageItem> result, int horizontalCount) {
        this.mHorizontalCount = horizontalCount;
        mImageItem.clear();
        mImageItem.addAll(result);
        notifyDataSetChanged();
    }


    public class Innerholder extends RecyclerView.ViewHolder {
        public Innerholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
