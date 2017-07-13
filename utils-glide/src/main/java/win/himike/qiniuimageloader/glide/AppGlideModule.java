package win.himike.qiniuimageloader.glide;

import android.content.Context;

import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;

import java.io.InputStream;

/**
 * Created by HiMike on 2017/7/10.
 */
@GlideModule
public class AppGlideModule extends com.bumptech.glide.module.AppGlideModule {
    @Override
    public void registerComponents(Context context, Registry registry) {
        super.registerComponents(context, registry);
        registry.append(GlideImageLoader.class, InputStream.class, new ImageSizeUrlLoader.Factory());
    }
}
