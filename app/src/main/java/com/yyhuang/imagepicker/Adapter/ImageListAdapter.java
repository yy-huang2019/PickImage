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

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.InnerHolder> {

    private List<ImageItem> mImageItems = new ArrayList<>();
    //选中的图片
    private List<ImageItem> mSelectedItem = new ArrayList<>();

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
        View itemView = holder.itemView;
        ImageView imageView = itemView.findViewById(R.id.image_iv);
        CheckBox checkBox = itemView.findViewById(R.id.checked);
        View image_cover = itemView.findViewById(R.id.image_cover);
        //点击选中的图片
        ImageItem imageItem = mImageItems.get(position);
        Glide.with(imageView.getContext()).load(imageItem.getPath()).into(imageView);

        //解决itemView复用
        //是否选中该view（图片）
        if (mSelectedItem.contains(imageItem)) {
            //没有选中，则选择该图片
            mSelectedItem.add(imageItem);
            //修改UI
            checkBox.setChecked(false);
            image_cover.setVisibility(View.VISIBLE);
            checkBox.setButtonDrawable(itemView.getContext().getDrawable(R.mipmap.image1));

        }else{
            //选中了则取消选择
            mSelectedItem.remove(imageItem);
            //修改UI
            checkBox.setChecked(true);
            image_cover.setVisibility(View.GONE);
            checkBox.setButtonDrawable(itemView.getContext().getDrawable(R.mipmap.image3));

        }


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //是否选中该view（图片）
                if (mSelectedItem.contains(imageItem)) {
                    //选中了则取消选择
                    mSelectedItem.remove(imageItem);
                    //修改UI
                    checkBox.setChecked(true);
                    image_cover.setVisibility(View.GONE);
                    checkBox.setButtonDrawable(itemView.getContext().getDrawable(R.mipmap.image3));
                }else{
                    //没有选中，则选择该图片
                    mSelectedItem.add(imageItem);
                    //修改UI
                    checkBox.setChecked(false);
                    image_cover.setVisibility(View.VISIBLE);
                    checkBox.setButtonDrawable(itemView.getContext().getDrawable(R.mipmap.image1));
                }
            }
        });
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
