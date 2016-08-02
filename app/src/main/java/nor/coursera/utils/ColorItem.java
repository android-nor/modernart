package nor.coursera.utils;

public class ColorItem {

	public static final String ITEM_SEP = System.getProperty("line.separator");

	private int mBackgrundColor;

	public ColorItem(int backgrundColor) {
		this.mBackgrundColor = backgrundColor;
	}

	public int getBackgrundColor() {
		return mBackgrundColor;
	}

	public void setBackgrundColor(int backgrundColor) {
		mBackgrundColor= backgrundColor;
	}

}
