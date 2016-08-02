package nor.coursera.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import nor.coursera.modernart.R;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	//private List<Integer> mThumbIds;

    private final List<ColorItem> mItems = new ArrayList<ColorItem>();



	// Store the list of image IDs
	public ImageAdapter(Context c) {
		mContext = c;
	}

	// Return the number of items in the Adapter
	@Override
	public int getCount() {
		return mItems.size();
	}

	// Return the data item at position
	@Override
	public Object getItem(int position) {
		return mItems.get(position);
	}

	// Will get called to provide the ID that
	// is passed to OnItemClickListener.onItemClick()
	@Override
	public long getItemId(int position) {
		return position;
	}



    // Add a ToDoItem to the adapter
    // Notify observers that the data set has changed
    public void add(ColorItem item) {

        mItems.add(item);
        notifyDataSetChanged();

    }


    static class ViewHolder {
        ImageView imageColor;

        ViewHolder(View v) {
            imageColor = (ImageView) v.findViewById(R.id.imageColor);
        }
    }

	// Return an ImageView for each item referenced by the Adapter
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		//ImageView imageView = (ImageView) convertView;
        final ViewHolder holder;// = new ViewHolder(convertView);
        //View returnedView = null;
        if (convertView == null) {
            LayoutInflater mInflater = LayoutInflater.from(mContext);
            convertView = mInflater.inflate(R.layout.list_color, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // TODO - Get the current ToDoItem
        final ColorItem colorItem = (ColorItem) getItem(position);
        holder.imageColor.setBackgroundColor(colorItem.getBackgrundColor());

        return convertView;
	}
}
