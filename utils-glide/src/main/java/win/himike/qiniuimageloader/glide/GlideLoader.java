package win.himike.qiniuimageloader.glide;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

/**
 * Created by HiMike on 2017/7/9.
 */

public class GlideLoader {
    private static int DEFAULT_PLACE_HOLDER = 0;
    private static int DEFAULT_ERROR_PLACE_HOLDER = 0;
    private static int DEFAULT_ANIM_PLACE_HOLDER = 0;

    public static GlideImageLoader createLoader(final ImageView imageView, final String oriUrl) {
        return new GlideImageLoader(imageView, oriUrl);
    }

    public static void setGlobalPlaceHolder(final int defaultPlaceHolder, final int defaultAvatarPlaceHolder) {
        DEFAULT_PLACE_HOLDER = defaultPlaceHolder;
        DEFAULT_ERROR_PLACE_HOLDER = defaultAvatarPlaceHolder;
    }

    public static void setDefaultAnimPlaceHolder(final int defaultPlaceHolder) {
        DEFAULT_ANIM_PLACE_HOLDER = defaultPlaceHolder;
        DEFAULT_PLACE_HOLDER = 0;
    }

    public static void into(final Context context, final ImageView imageView, final GlideImageLoader loader) {
        final ImageView.ScaleType originScaleType = imageView.getScaleType();
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        RequestManager manager = Glide.with(context);
        RequestBuilder request = manager
                .load(loader);

        RequestOptions requestOptions = new RequestOptions();
        if (loader.getRequestOptions() != null) {
            requestOptions.apply(loader.getRequestOptions());
        }

        if (loader.getAnimDrawable() != 0 || DEFAULT_ANIM_PLACE_HOLDER != 0) {
            final AnimationDrawable animationDrawable = (AnimationDrawable) ContextCompat.getDrawable(context,
                    loader.getAnimDrawable() != 0 ? loader.getAnimDrawable() : DEFAULT_ANIM_PLACE_HOLDER);
            requestOptions.placeholder(animationDrawable);
            if (requestOptions.getErrorId() == 0 && requestOptions.getErrorPlaceholder() == null) {
                requestOptions.error(DEFAULT_ERROR_PLACE_HOLDER);
            }
            imageView.post(new Runnable() {
                @Override
                public void run() {
                    animationDrawable.start();
                }
            });
        } else {
            if (requestOptions.getPlaceholderId() == 0 && requestOptions.getPlaceholderDrawable() == null) {
                requestOptions.placeholder(DEFAULT_PLACE_HOLDER);
            }
            if (requestOptions.getErrorId() == 0 && requestOptions.getErrorPlaceholder() == null) {
                requestOptions.error(DEFAULT_ERROR_PLACE_HOLDER);
            }
        }
        request.apply(requestOptions)
                .listener(new RequestListener() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                        if (loader.isClickReload()) {
                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    into(context, imageView, loader);
                                    imageView.setOnClickListener(null);
                                }
                            });
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
                        imageView.setImageResource(0);
                        imageView.setScaleType(originScaleType);
                        loader.clear();
                        return false;
                    }
                })
                .into(imageView);
    }
}
