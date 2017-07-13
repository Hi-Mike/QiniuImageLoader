# QiniuImageLoader

> 在Android上结合七牛提供的API，根据需求加载合适的图片，让加载图片更有效、更节流、更简单、更可控、更酷

## About This Fork

QiniuImageLoader封装了七牛的高级图片处理API，这个Fork增加了一个使用Glide4的封装工具。

## 基本库（library）及参数说明

参见[QiniuImageLoader](https://github.com/Hi-Mike/QiniuImageLoader#v-说明)

## utils-glide

对Glide 4.0进行了简单的封装

```java

GlideLoader.setGlobalPlaceHolder(0, R.drawable.emo_im_crying);
GlideLoader.setDefaultAnimPlaceHolder(R.drawable.anim_place_holder);

GlideLoader.createLoader(image1, MOCK_DATA_URL)
           .scaleType(ImageView.ScaleType.CENTER_CROP)
           .fillWidth()
           .clickReload()
           .attach();

```

目前，实现了drawable加载动画，根据ImageView宽高请求适应大小的图片，加载失败点击重新加载。Glide的option设置只覆盖了RequestOptions，后续会逐渐加入其他option

接口

| 参数方法 | 说明
| --- | ---
| fillWidth(void) | 根据图片宽高获取图片
| animPlaceHolder(int) | 设置占位动画，帧动画
| scaleType(ImageView.ScaleType) | 由于GlideLoader会设置一次ScaleType.CENTER_INSIDE，这个必须设置
| clickReload(void) | 加载出错后，点击重试，这个会使原有的click失效
| applyRequestOption(RequestOptions) | 设置自己的RequestOptions

## 七牛高级图片处理规则

参见七牛[高级图片处理](https://developer.qiniu.com/dora/manual/1270/the-advanced-treatment-of-images-imagemogr2)

## Thanks

[QiniuImageLoader](https://github.com/lingochamp/QiniuImageLoader)

### License

```
Copyright 2017 Mike

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```