package com.yyhuang.imagepicker;

import com.yyhuang.imagepicker.domain.ImageItem;
import java.util.List;

public class PickerConfig {
    public int maxSelectedCount = 1;

    private PickerConfig(){}

    public static PickerConfig mPickerConfig;

    public static PickerConfig getInstance(){
        if (mPickerConfig == null) {
            mPickerConfig = new PickerConfig();
        }
        return mPickerConfig;
    }

    private OnImageSelectedFinishListener mImageSelectedFinishListener;

    public int getMaxSelectedCount() {

        return maxSelectedCount;
    }

    public void setMaxSelectedCount(int maxSelectedCount) {
        this.maxSelectedCount = maxSelectedCount;
    }

    public interface OnImageSelectedFinishListener{
        void onSelected(List<ImageItem> result);
    }

    public OnImageSelectedFinishListener getImageSelectedFinishListener(){
        return mImageSelectedFinishListener;
    }

    public void setOnImageSelectedFinishListener(OnImageSelectedFinishListener listener){
        this.mImageSelectedFinishListener = listener;
    }


}
