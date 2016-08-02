/**

 You will find a lot of junk code but that's because the
 main the idea is to create a dialog for for choosing colors.
 The start and the end color of the image rectangle box
 I hope you'll finish this idea and will post it on you tube

 as soon as possible I'll post my code on github with the choice diaolog completed





 * Follow Field Naming Conventions where possible :)
 Non-public, non-static field names start with m.
 Static field names start with s.
 Other fields start with a lower case letter.
 Public static final fields (constants) are ALL_CAPS_WITH_UNDERSCORES.
 *
 * */



package nor.coursera.modernart;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nor.coursera.utils.ImageAdapter;
import nor.coursera.utils.ImageViewItem;
import nor.coursera.utils.ModernArtResources;

//import static nor.coursera.modernart.R.menu.menu_main;


public class MainActivity extends Activity {



    static private final String TAG = "MainActivity";

    static private final String URL_MOMA_PAGE = "http://www.moma.org";
    // For use with another browser
    static private final String CHOOSER_TEXT = "Load " + URL_MOMA_PAGE + " with:";

    // Identifier for each type of Dialog
    private static final int ALERTTAG = 0;
    private static final int COLORTAG = 1;


    private SeekBar        mSeekBar;
    private DialogFragment mDialog;
    //private DialogFragment mColorChoiceDialog;

    private List<ImageViewItem> mImageViewItemList = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ModernArtResources mResources = ModernArtResources.getInstance(getBaseContext());

        //set ImageView and populate list
        populateItemList();

        setImageViewListener();


        //create custom action bar
        createActionBar();

        mSeekBar = (SeekBar)  findViewById(R.id.seekBar);
        //set seek listener
        setSeekBarChangeListener();

    }


    private void createActionBar() {
        ActionBar mActionBar = getActionBar();
        mActionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(253, 227, 37)));
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }

    private void setSeekBarChangeListener() {
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                //mTextView.setText("Covered: " + progress + "/" + seekBar.getMax());

                changeImageViewBackgroundColor();

/*                seekBar.setThumb(getResources().getDrawable(
                        R.drawable.thumb1));*/

                //Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
            }

            @Override

            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //mTextView.setText("Covered: " + progress + "/" + seekBar.getMax());
                //Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void populateItemList() {

        ColorDrawable b = new ColorDrawable();
        AddOjectToList1((ImageView)findViewById(R.id.imageView1),
                                                getResources().getInteger(R.integer.color_1s),
                                                getResources().getInteger(R.integer.color_1e));

        AddOjectToList1((ImageView)findViewById(R.id.imageView2),
                                                getResources().getInteger(R.integer.color_2s),
                                                getResources().getInteger(R.integer.color_2e));

        AddOjectToList1((ImageView)findViewById(R.id.imageView3),
                                                getResources().getInteger(R.integer.color_3s),
                                                getResources().getInteger(R.integer.color_3e));

        AddOjectToList1((ImageView)findViewById(R.id.imageView4),
                getResources().getInteger(R.integer.color_4s),
                getResources().getInteger(R.integer.color_4e));

        AddOjectToList1((ImageView)findViewById(R.id.imageView5),
                getResources().getInteger(R.integer.color_5s),
                getResources().getInteger(R.integer.color_5e));

        AddOjectToList1((ImageView)findViewById(R.id.imageView6),
                getResources().getInteger(R.integer.color_6s),
                getResources().getInteger(R.integer.color_6e));
        AddOjectToList1((ImageView)findViewById(R.id.imageView7),
                getResources().getInteger(R.integer.color_7s),
                getResources().getInteger(R.integer.color_7e));
        AddOjectToList1((ImageView)findViewById(R.id.imageView9),
                getResources().getInteger(R.integer.color_9s),
                getResources().getInteger(R.integer.color_9e));
        AddOjectToList1((ImageView)findViewById(R.id.imageView8), Color.WHITE, Color.WHITE);

    }

    private void setImageViewListener() {
        for (ImageViewItem mImageViewItem : mImageViewItemList) {

            mImageViewItem.getmImageView().setOnClickListener(new View.OnClickListener() {
                //@Override
                public void onClick(View v) {
                    showDialogFragment(COLORTAG);
                }
            });
        }
    }



    private void AddOjectToList1(ImageView imageView, int startColor, int endColor) {
        ImageViewItem n = new ImageViewItem();
        n.setmImageView(imageView);
        n.setStartColor(startColor);
        n.setEndColor(endColor);
        mImageViewItemList.add(n);
    }


    private void changeImageViewBackgroundColor() {
        for (ImageViewItem mImageViewItem : mImageViewItemList) {

            mImageViewItem.getmImageView().setBackground(mImageViewItem.getColorByProgress(mSeekBar.getProgress()));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_more:
                showDialogFragment(ALERTTAG);
                return true;
            case R.id.action_about:
                //showHelp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void showDialogFragment(int dialogID) {
        switch (dialogID) {

            // Show AlertDialog
            case ALERTTAG:

                // Create a new AlertDialogFragment
                mDialog = AlertDialogFragment.newInstance();

                // Show AlertDialogFragment
                mDialog.show(getFragmentManager(), "Alert");

                break;
            // Show ProgressDialog
            case COLORTAG:

                // Create a new ColorChoiceDialogFragment
                mDialog = ColorChoiceDialogFragment.newInstance();

                // Show new ProgressDialogFragment
                mDialog.show(getFragmentManager(), "Color");
                break;
        }
    }


    // Abort or go to MOMA page based on value of shouldContinue
    private void goToMomaPage(boolean shouldContinue) {
        if (shouldContinue) {

            Log.i(TAG, "Entered startImplicitActivation()");

            // TODO - Create a base intent for viewing a URL
            // (HINT:  second parameter uses Uri.parse())
            Uri baseSite = Uri.parse(URL_MOMA_PAGE);
            Intent baseIntent = new Intent(Intent.ACTION_VIEW, baseSite);

            // TODO - Create a chooser intent, for choosing which Activity
            // will carry out the baseIntent
            // (HINT: Use the Intent class' createChooser() method)
            Intent chooserIntent = Intent.createChooser(baseIntent, CHOOSER_TEXT);

            Log.i(TAG,"Chooser Intent Action:" + chooserIntent.getAction());

            startActivity(chooserIntent);

        } else {

            // Abort ShutDown and dismiss dialog
            mDialog.dismiss();
        }
    }


    // Class that creates the AlertDialog for ColorChoiceDialogFragment
    public static class ColorChoiceDialogFragment extends DialogFragment {

        public static ColorChoiceDialogFragment newInstance() {
            return new ColorChoiceDialogFragment();
        }

        // Build AlertDialog using AlertDialog.Builder
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            ImageAdapter mColorItemAdapter = new ImageAdapter(getActivity());


//            mColorItemAdapter.add(new ColorItem(R.color.Azure));
//            mColorItemAdapter.add(new ColorItem(R.color.Bisque));
//            mColorItemAdapter.add(new ColorItem(R.color.Azure));
//            mColorItemAdapter.add(new ColorItem(R.color.Bisque));
//            mColorItemAdapter.add(new ColorItem(R.color.Azure));
//            mColorItemAdapter.add(new ColorItem(R.color.Bisque));
//            mColorItemAdapter.add(new ColorItem(R.color.Azure));
//            mColorItemAdapter.add(new ColorItem(R.color.Bisque));



            return new AlertDialog.Builder(getActivity())
                    .setTitle("Choose your Color")
                    //.setView(getActivity().getLayoutInflater().inflate(R.layout.list_color, null))
                    .setAdapter(mColorItemAdapter, new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int item)
                                {
                                }
                            }
                    )
                    .setCancelable(false)

                            // Set up No Button
                    .setNegativeButton("ToDo",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    Toast.makeText(ModernArtResources.getContext(), "No", Toast.LENGTH_SHORT).show();
                                    //((MainActivity) getActivity()).getBaseContext()
                                }
                            })

                            // Set up Yes Button
                    .setPositiveButton("Bye Bye",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        final DialogInterface dialog, int id) {
                                    Toast.makeText(ModernArtResources.getContext(), "Si", Toast.LENGTH_SHORT).show();
                                }
                            })
                    .create();
        }
    }






    // Class that creates the AlertDialog
    public static class AlertDialogFragment extends DialogFragment {

        public static AlertDialogFragment newInstance() {
            return new AlertDialogFragment();
        }

        // Build AlertDialog using AlertDialog.Builder
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setMessage(getResources().getString(R.string.dialog_message))

                            // User cannot dismiss dialog by hitting back button
                    .setCancelable(false)

                            // Set up No Button
                    .setNegativeButton(getResources().getString(R.string.dialog_no),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    //Toast.makeText(ModernArtResources.getContext(), "No", Toast.LENGTH_SHORT).show();
                                    //((MainActivity) getActivity()).getBaseContext()



                                    ((MainActivity) getActivity())
                                            .goToMomaPage(false);
                                }
                            })

                            // Set up Yes Button
                    .setPositiveButton(getResources().getString(R.string.dialog_yes),
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        final DialogInterface dialog, int id) {
//                                  Toast.makeText(ModernArtResources.getContext(), "Si", Toast.LENGTH_SHORT).show();
                                    ((MainActivity) getActivity())
                                            .goToMomaPage(true);
                                }
                            }).create();
        }
    }


}
