package win.himike.qiniuimageloader.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import win.himike.qiniuimageloader.glide.GlideLoader;


public class MainActivity extends AppCompatActivity {
    private static String MOCK_DATA_URL = "http://7xjww9.com1.z0.glb.clouddn.com/20130221114001385.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();

        GlideLoader.setGlobalPlaceHolder(0, R.drawable.emo_im_crying);
        GlideLoader.setDefaultAnimPlaceHolder(R.drawable.anim_place_holder);

        GlideLoader.createLoader(image1, MOCK_DATA_URL)
                .scaleType(ImageView.ScaleType.CENTER_CROP)
                .fillWidth()
                .clickReload()
                .attach();

        GlideLoader.createLoader(image2, MOCK_DATA_URL)
                .scaleType(ImageView.ScaleType.CENTER_CROP)
                .wR(R.dimen.image_2_width)
                .attach();

        GlideLoader.createLoader(image3, MOCK_DATA_URL)
                .size(dp2px(250))
                .addOpBlur(40, 20)
                .attach();

        GlideLoader.createLoader(image4, MOCK_DATA_URL)
                .w(dp2px(270))
                .addOpRotate(30)
                .attach();

        GlideLoader.createLoader(image5, MOCK_DATA_URL)
                .halfScreenW()
                .attach();
    }

    public int dp2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView image5;

    private void assignViews() {
        image1 = (ImageView) findViewById(R.id.image_1);
        image2 = (ImageView) findViewById(R.id.image_2);
        image3 = (ImageView) findViewById(R.id.image_3);
        image4 = (ImageView) findViewById(R.id.image_4);
        image5 = (ImageView) findViewById(R.id.image_5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.recycler) {
            startActivity(new Intent(MainActivity.this, RecyclerActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}