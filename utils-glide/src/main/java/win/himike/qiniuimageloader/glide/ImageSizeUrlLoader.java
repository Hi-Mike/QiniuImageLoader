package win.himike.qiniuimageloader.glide;

import android.support.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;

import java.io.InputStream;

public class ImageSizeUrlLoader extends BaseGlideUrlLoader<GlideImageLoader> {

    protected ImageSizeUrlLoader(ModelLoader<GlideUrl, InputStream> concreteLoader) {
        super(concreteLoader);
    }

    protected ImageSizeUrlLoader(ModelLoader<GlideUrl, InputStream> concreteLoader, @Nullable ModelCache<GlideImageLoader, GlideUrl> modelCache) {
        super(concreteLoader, modelCache);
    }

    @Override
    protected String getUrl(GlideImageLoader glideImageLoader, int width, int height, Options options) {
        if (glideImageLoader.isFillWidth()) {
            glideImageLoader.w(width).h(height);
        }
        return glideImageLoader.createQiniuUrl();
    }

    @Override
    public boolean handles(GlideImageLoader glideImageLoader) {
        return true;
    }

    public static class Factory implements ModelLoaderFactory<GlideImageLoader, InputStream> {

        @Override
        public ModelLoader<GlideImageLoader, InputStream> build(MultiModelLoaderFactory multiFactory) {
            return new ImageSizeUrlLoader(multiFactory.build(GlideUrl.class, InputStream.class));
        }

        @Override
        public void teardown() {
            // Do nothing.
        }
    }
}