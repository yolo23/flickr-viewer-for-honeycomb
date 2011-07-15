/**
 * 
 */
package com.charles.task;

import android.app.Activity;
import android.widget.Toast;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.favorites.FavoritesInterface;
import com.charles.FlickrViewerApplication;
import com.charles.utils.FlickrHelper;

/**
 * Represents the task to add a photo to my favorite photo list.
 * 
 * @author charles
 * 
 */
public class AddPhotoAsFavoriteTask extends
		ProgressDialogAsyncTask<String, Integer, Boolean> {

	/**
	 * Constructor.
	 */
	public AddPhotoAsFavoriteTask(Activity activity) {
		super(activity, "Adding photo as my favorite...");
	}

	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		String msg = "This photo was added to favorites successfully.";
		if (!result) {
			msg = "Error to add this photo to favorites.";
		}
		Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected Boolean doInBackground(String... params) {
		String photoId = params[0];

		FlickrViewerApplication app = (FlickrViewerApplication) mActivity
				.getApplication();
		String token = app.getFlickrToken();

		Flickr f = FlickrHelper.getInstance().getFlickrAuthed(token);
		FavoritesInterface fi = f.getFavoritesInterface();
		try {
			fi.add(photoId);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}