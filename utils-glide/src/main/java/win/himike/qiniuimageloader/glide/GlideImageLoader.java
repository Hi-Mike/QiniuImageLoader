package win.himike.qiniuimageloader.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;
import com.liulishuo.qiniuimageloader.QiniuImageLoader;

/**
 * Created by HiMike on 2017/7/9.
 */

public class GlideImageLoader extends QiniuImageLoader<GlideImageLoader> {
    private boolean fillWidth = false;
    private int animDrawable;
    private boolean clickReload;
    private ImageView.ScaleType mScaleType = ImageView.ScaleType.FIT_CENTER;
    private RequestOptions mRequestOptions;

    public GlideImageLoader(Context context, String oriUrl) {
        super(context, oriUrl);
    }

    public GlideImageLoader(ImageView imageView, String oriUrl) {
        super(imageView, oriUrl);
    }

    public GlideImageLoader fillWidth() {
        fillWidth = true;
        return this;
    }

    public GlideImageLoader applyRequestOption(RequestOptions options) {
        this.mRequestOptions = options;
        return this;
    }

    public GlideImageLoader animPlaceHolder(int animDrawable) {
        this.animDrawable = animDrawable;
        return this;
    }

    /**
     * 强制设置？
     *
     * @param scaleType
     * @return
     */
    public GlideImageLoader scaleType(ImageView.ScaleType scaleType) {
        mScaleType = scaleType;
        return this;
    }

    /**
     * it will let your OnClickListener invalid
     *
     * @return
     */
    public GlideImageLoader clickReload() {
        clickReload = true;
        return this;
    }


    @Override
    public void attach() {
        attachWithNoClear();
//        super.attach();
    }

    @Override
    public void attachWithNoClear() {
        GlideLoader.into(getContext(), getImageView(), this);
    }

    boolean isFillWidth() {
        return fillWidth;
    }

    int getAnimDrawable() {
        return animDrawable;
    }

    RequestOptions getRequestOptions() {
        return mRequestOptions;
    }

    ImageView.ScaleType getScaleType() {
        return mScaleType;
    }

    boolean isClickReload() {
        return clickReload;
    }

    @Override
    public void clear() {
        super.clear();
        mRequestOptions = null;
    }
}
