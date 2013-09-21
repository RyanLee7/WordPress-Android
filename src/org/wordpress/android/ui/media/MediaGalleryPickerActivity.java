package org.wordpress.android.ui.media;

import java.util.ArrayList;
<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> origin/master

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
<<<<<<< HEAD
=======
import android.widget.Toast;
>>>>>>> origin/master

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.ActionMode.Callback;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.ui.MultiSelectGridView;
import org.wordpress.android.ui.MultiSelectGridView.MultiSelectListener;
<<<<<<< HEAD
=======
import org.xmlrpc.android.ApiHelper;
>>>>>>> origin/master

/**
 * An activity where the user can add new images to their media gallery or where the user 
 * can choose a single image to embed into their post.
 */
<<<<<<< HEAD
public class MediaGalleryPickerActivity extends SherlockActivity implements MultiSelectListener, Callback {

    private MultiSelectGridView mGridView;
    private MediaGridAdapter mAdapter;
=======
public class MediaGalleryPickerActivity extends SherlockActivity implements MultiSelectListener, Callback, MediaGridAdapter.MediaGridAdapterCallback {

    private MultiSelectGridView mGridView;
    private MediaGridAdapter mGridAdapter;
>>>>>>> origin/master
    private ActionMode mActionMode;

    private ArrayList<String> mFilteredItems;
    private boolean mIsSelectOneItem;
<<<<<<< HEAD
=======
    private boolean mIsRefreshing;
    private boolean mHasRetrievedAllMedia;
>>>>>>> origin/master
    
    private static final String STATE_FILTERED_ITEMS = "STATE_FILTERED_ITEMS";
    private static final String STATE_SELECTED_ITEMS = "STATE_SELECTED_ITEMS";
    private static final String STATE_IS_SELECT_ONE_ITEM = "STATE_IS_SELECT_ONE_ITEM";
    
    public static final int REQUEST_CODE = 4000;
    public static final String PARAM_SELECT_ONE_ITEM = "PARAM_SELECT_ONE_ITEM";
    public static final String PARAM_FILTERED_IDS = "PARAM_FILTERED_IDS";
    public static final String RESULT_IDS = "RESULT_IDS";
    public static final String TAG = MediaGalleryPickerActivity.class.getSimpleName();

<<<<<<< HEAD
=======
    private int mOldMediaSyncOffset = 0;

>>>>>>> origin/master
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<String> checkedItems = new ArrayList<String>();
        mFilteredItems = getIntent().getStringArrayListExtra(PARAM_FILTERED_IDS);
        mIsSelectOneItem = getIntent().getBooleanExtra(PARAM_SELECT_ONE_ITEM, false);
        if (savedInstanceState != null) {
            checkedItems.addAll(savedInstanceState.getStringArrayList(STATE_SELECTED_ITEMS));
            mFilteredItems = savedInstanceState.getStringArrayList(STATE_FILTERED_ITEMS);
            mIsSelectOneItem =  savedInstanceState.getBoolean(STATE_IS_SELECT_ONE_ITEM, mIsSelectOneItem);
        }
<<<<<<< HEAD
        
=======

        mActionMode = getSherlock().startActionMode(this);

>>>>>>> origin/master
        setContentView(R.layout.media_gallery_picker_layout);
        mGridView = (MultiSelectGridView) findViewById(R.id.media_gallery_picker_gridview);
        mGridView.setMultiSelectListener(this);
        if (mIsSelectOneItem) {
<<<<<<< HEAD
            mGridView.setHighlightSelectModeEnabled(true);
            mGridView.setMultiSelectModeEnabled(false);
        } else {
            mGridView.setMultiSelectModeActive(true);
        }
        mAdapter = new MediaGridAdapter(this, null, 0, checkedItems);
        mGridView.setAdapter(mAdapter);
        
        mActionMode = getSherlock().startActionMode(this);
        mActionMode.setTitle(checkedItems.size() + " selected");
=======
            mActionMode.setTitle("Select an image");
            mGridView.setHighlightSelectModeEnabled(true);
            mGridView.setMultiSelectModeEnabled(false);
        } else {
            mActionMode.setTitle(checkedItems.size() + " selected");
            mGridView.setMultiSelectModeActive(true);
        }
        mGridAdapter = new MediaGridAdapter(this, null, 0, checkedItems);
        mGridAdapter.setCallback(this);
        mGridView.setAdapter(mGridAdapter);
>>>>>>> origin/master
    }
    
    @Override
    public void onResume() {
        super.onResume();
        refereshViews();
    }
    
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
<<<<<<< HEAD
        outState.putStringArrayList(STATE_SELECTED_ITEMS, mAdapter.getCheckedItems());
=======
        outState.putStringArrayList(STATE_SELECTED_ITEMS, mGridAdapter.getCheckedItems());
>>>>>>> origin/master
        outState.putStringArrayList(STATE_FILTERED_ITEMS, mFilteredItems);
        outState.putBoolean(STATE_IS_SELECT_ONE_ITEM, mIsSelectOneItem);
    }

    private void refereshViews() {
        if (WordPress.getCurrentBlog() == null)
            return;
        
<<<<<<< HEAD
        String blogId = String.valueOf(WordPress.getCurrentBlog().getBlogId());
        
        Cursor cursor = WordPress.wpDB.getMediaImagesForBlog(blogId, mFilteredItems);
        mAdapter.swapCursor(cursor);
=======
        final String blogId = String.valueOf(WordPress.getCurrentBlog().getBlogId());
        
        Cursor cursor = WordPress.wpDB.getMediaImagesForBlog(blogId, mFilteredItems);
        if (cursor.getCount() == 0) {
            refreshMediaFromServer(0);
        } else {
            mGridAdapter.swapCursor(cursor);
        }

>>>>>>> origin/master
    }

    @Override
    public void onMultiSelectChange(int count) {
        mActionMode.setTitle(count + " selected");
        
        // stay always in multi-select mode, even when count reaches 0
        if (count == 0 && !mIsSelectOneItem)
            mGridView.setMultiSelectModeActive(true);
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        Intent intent = new Intent();
<<<<<<< HEAD
        intent.putExtra(RESULT_IDS, mAdapter.getCheckedItems());
        setResult(RESULT_OK, intent);
        finish();
    }
    
=======
        intent.putExtra(RESULT_IDS, mGridAdapter.getCheckedItems());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void fetchMoreData(int offset) {
        if (!mHasRetrievedAllMedia)
            refreshMediaFromServer(offset);
    }

    @Override
    public void onRetryUpload(String mediaId) {

    }

    @Override
    public boolean isInMultiSelect() {
        return false;
    }

    public void refreshMediaFromServer(int offset) {

        if(offset == 0 || !mIsRefreshing) {

            if (offset == mOldMediaSyncOffset) {
                // we're pulling the same data again for some reason. Pull from the beginning.
                offset = 0;
            }
            mOldMediaSyncOffset = offset;

            mIsRefreshing = true;
            mGridAdapter.setRefreshing(true);

            List<Object> apiArgs = new ArrayList<Object>();
            apiArgs.add(WordPress.getCurrentBlog());

            ApiHelper.SyncMediaLibraryTask.Callback callback = new ApiHelper.SyncMediaLibraryTask.Callback() {

                // refersh db from server. If returned count is 0, we've retrieved all the media.
                // stop retrieving until the user manually refreshes

                @Override
                public void onSuccess(int count) {
                    MediaGridAdapter adapter = (MediaGridAdapter) mGridView.getAdapter();
                    mHasRetrievedAllMedia = (count == 0);
                    adapter.setHasRetrieviedAll(mHasRetrievedAllMedia);

                    mIsRefreshing = false;

                    // the activity may be gone by the time this finishes, so check for it
                    if (!isFinishing()) {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                //mListener.onMediaItemListDownloaded();
                                mGridAdapter.setRefreshing(false);
                                String blogId = String.valueOf(WordPress.getCurrentBlog().getBlogId());
                                Cursor cursor = WordPress.wpDB.getMediaImagesForBlog(blogId, mFilteredItems);
                                mGridAdapter.swapCursor(cursor);

                            }
                        });
                    }
                }

                @Override
                public void onFailure(int errorCode) {

                    if (errorCode == ApiHelper.SyncMediaLibraryTask.NO_UPLOAD_FILES_CAP) {
                        Toast.makeText(MediaGalleryPickerActivity.this, "You do not have permission to view the media library", Toast.LENGTH_SHORT).show();
                        MediaGridAdapter adapter = (MediaGridAdapter) mGridView.getAdapter();
                        mHasRetrievedAllMedia = true;
                        adapter.setHasRetrieviedAll(mHasRetrievedAllMedia);
                    }

                    // the activity may be cone by the time we get this, so check for it
                    if (!isFinishing()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mIsRefreshing = false;
                                mGridAdapter.setRefreshing(false);
                            }
                        });
                    }

                }
            };

            ApiHelper.SyncMediaLibraryTask getMediaTask = new ApiHelper.SyncMediaLibraryTask(offset, MediaGridFragment.Filter.ALL, callback);
            getMediaTask.execute(apiArgs);
        }
    }
>>>>>>> origin/master
}
