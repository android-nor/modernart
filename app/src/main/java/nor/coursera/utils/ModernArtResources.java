package nor.coursera.utils;

import android.content.Context;

import nor.coursera.modernart.R;

/**
 * Created by norman on 22/03/15.
 */
public class ModernArtResources {
    private static Context mContext;
    private static int maxSeekProgress;

    private ModernArtResources(){}


    private static class SingletonHelper{
        private static final ModernArtResources INSTANCE = new ModernArtResources();
    }

    public static ModernArtResources getInstance(Context c){

        mContext = c;
        maxSeekProgress = mContext.getResources().getInteger(R.integer.max_seek_progress);

        return SingletonHelper.INSTANCE;
    }

    public static int getMaxSeekProgress() {
        return maxSeekProgress;
    }

    public static Context getContext() {
        return mContext;
    }

}
