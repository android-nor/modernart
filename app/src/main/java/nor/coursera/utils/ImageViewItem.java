package nor.coursera.utils;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by norman on 21/03/15.
 */
public class ImageViewItem implements View.OnClickListener {

    private static final String TAG = "ImageViewItem";

    int endColor;
    int startColor;

    int maxSeekProgress;

    //da controllare se serve float o int
    private float startRed;
    private float startBlue;
    private float startGreen;

    private float endRed;
    private float endGreen;
    private float endBlue;

    private ImageView mImageView;

    public ImageViewItem() {

    }

//    public ImageViewItem(ImageView mImageView) {
//        this.mImageView = mImageView;
//        setImageViewListener();
//    }


//    //This is not a good style? :)
//    private void setImageViewListener() {
//        mImageView.setOnClickListener(new View.OnClickListener() {
//            //@Override
//            public void onClick(View v) {
//                Toast.makeText(ModernArtResources.getContext(), "Click", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    public void setMaxSeekProgress(int maxSeekProgress) {
        this.maxSeekProgress = maxSeekProgress;
    }

    public int getStartColor() {
        return startColor;
    }

    public void setStartColor(int startColor) {
        this.startColor = startColor;

        startRed   =  Color.red(startColor);
        startBlue  =  Color.blue(startColor);
        startGreen =  Color.green(startColor);

    }

    public int getEndColor() {
        return endColor;
    }

    public void setEndColor(int endColor) {
        this.endColor = endColor;

        endRed   =  Color.red(endColor);
        endBlue  =  Color.blue(endColor);
        endGreen =  Color.green(endColor);
    }

    public void setmImageView(ImageView image1) {
        this.mImageView = image1;
    }

    public ImageView getmImageView() {
        return mImageView;
    }

    public Drawable getColorByProgress(int progress) {

        int r = getComponent(startRed, endRed, progress);
        int g = getComponent(startGreen, endGreen, progress);
        int b = getComponent(startBlue, endBlue, progress);

        return new ColorDrawable(Color.rgb(getComponent(startRed, endRed, progress),
                                           getComponent(startGreen, endGreen, progress),
                                           getComponent(startBlue, endBlue, progress)));

    }

    private int getComponent(float startComponentColor, float endComponentColor, int progress) {
        float mLenght = Math.abs(startComponentColor - endComponentColor);

        maxSeekProgress = ModernArtResources.getMaxSeekProgress();
        //Log.v(TAG, "ModernArtResources hashCode = " + ModernArtResources.class.hashCode());


        int a = (int) (startComponentColor + (mLenght / maxSeekProgress) * progress);
        int b = (int) (startComponentColor - (mLenght / maxSeekProgress) * progress);

        return (startComponentColor < endComponentColor) ?
                (int) (startComponentColor + (mLenght / maxSeekProgress) * progress):
                (int) (startComponentColor - (mLenght / maxSeekProgress) * progress);


    }

    @Override
    public void onClick(View v) {

    }
}
