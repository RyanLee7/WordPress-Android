package org.wordpress.android.ui.notifications;

<<<<<<< HEAD
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
=======
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.TypedValue;
>>>>>>> origin/master
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
<<<<<<< HEAD
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
=======
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

>>>>>>> origin/master
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.models.Note;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class NotificationsListFragment extends ListFragment {
    private static final int LOAD_MORE_WITHIN_X_ROWS = 5;
=======
public class NotificationsListFragment extends ListFragment {
    private static final int LOAD_MORE_WITHIN_X_ROWS=5;
>>>>>>> origin/master
    private NoteProvider mNoteProvider;
    private NotesAdapter mNotesAdapter;
    private OnNoteClickListener mNoteClickListener;
    private View mProgressFooterView;
    private boolean mAllNotesLoaded;
<<<<<<< HEAD

=======
>>>>>>> origin/master
    /**
     * For responding to tapping of notes
     */
    public interface OnNoteClickListener {
        public void onClickNote(Note note);
    }
<<<<<<< HEAD

=======
>>>>>>> origin/master
    /**
     * For providing more notes data when getting to the end of the list
     */
    public interface NoteProvider {
        public void onRequestMoreNotifications(ListView listView, ListAdapter adapter);
    }
<<<<<<< HEAD

    @Override
    public void onCreate(Bundle bundle) {
=======
    @Override
    public void onCreate(Bundle bundle){
>>>>>>> origin/master
        super.onCreate(bundle);
        // setup the initial notes adapter
        mNotesAdapter = new NotesAdapter();
    }
<<<<<<< HEAD

    @Override
    public void onActivityCreated(Bundle bundle) {
=======
    @Override
    public void onActivityCreated(Bundle bundle){
>>>>>>> origin/master
        super.onActivityCreated(bundle);
        mProgressFooterView = View.inflate(getActivity(), R.layout.list_footer_progress, null);
        ListView listView = getListView();
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnScrollListener(new ListScrollListener());
        listView.setDivider(getResources().getDrawable(R.drawable.list_divider));
        listView.setDividerHeight(1);
        listView.addFooterView(mProgressFooterView, null, false);
        setListAdapter(mNotesAdapter);
    }
<<<<<<< HEAD

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Note note = mNotesAdapter.getItem(position);
        l.setItemChecked(position, true);
        if (note != null && !note.isPlaceholder() && mNoteClickListener != null) {
            mNoteClickListener.onClickNote(note);
        }
    }

=======
    @Override
    public void onListItemClick (ListView l, View v, int position, long id) {
        Note note = mNotesAdapter.getItem(position);
        l.setItemChecked(position, true);
        if (note != null && mNoteClickListener != null) {
            mNoteClickListener.onClickNote(note);
        }
    }
>>>>>>> origin/master
    @Override
    public void setListAdapter(ListAdapter adapter) {
        super.setListAdapter(adapter);
    }
<<<<<<< HEAD

    public void setNotesAdapter(NotesAdapter adapter) {
        mNotesAdapter = adapter;
        this.setListAdapter(adapter);
    }

    public NotesAdapter getNotesAdapter() {
        return mNotesAdapter;
    }

    public void setNoteProvider(NoteProvider provider) {
        mNoteProvider = provider;
    }

    public void setOnNoteClickListener(OnNoteClickListener listener) {
        mNoteClickListener = listener;
    }

    protected void requestMoreNotifications() {
=======
    public void setNotesAdapter(NotesAdapter adapter){
        mNotesAdapter = adapter;
        this.setListAdapter(adapter);
    }
    public NotesAdapter getNotesAdapter(){
        return mNotesAdapter;
    }
    public void setNoteProvider(NoteProvider provider){
        mNoteProvider = provider;
    }
    public void setOnNoteClickListener(OnNoteClickListener listener){
        mNoteClickListener = listener;
    }
    protected void requestMoreNotifications(){
>>>>>>> origin/master
        if (mNoteProvider != null) {
            mNoteProvider.onRequestMoreNotifications(getListView(), getListAdapter());
        }
    }

    class NotesAdapter extends ArrayAdapter<Note> {
<<<<<<< HEAD
        NotesAdapter() {
            this(getActivity());
        }

        NotesAdapter(Context context) {
            this(context, new ArrayList<Note>());
        }

        NotesAdapter(Context context, List<Note> notes) {
            super(context, R.layout.note_list_item, R.id.note_label, notes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            final Note note = getItem(position);
            TextView detailText = (TextView) view.findViewById(R.id.note_detail);
            ProgressBar placeholderLoading = (ProgressBar) view.findViewById(R.id.placeholder_loading);
=======
        NotesAdapter(){
            this(getActivity());
        }
        NotesAdapter(Context context){
            this(context, new ArrayList<Note>());
        }
        NotesAdapter(Context context, List<Note> notes){
            super(context, R.layout.note_list_item, R.id.note_label, notes);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View view = super.getView(position, convertView, parent);
            final Note note = getItem(position);
            TextView detailText = (TextView) view.findViewById(R.id.note_detail);
>>>>>>> origin/master
            if (note.isCommentType()) {
                detailText.setText(note.getCommentPreview());
                detailText.setVisibility(View.VISIBLE);
            } else {
                detailText.setVisibility(View.GONE);
            }
            final NetworkImageView avatarView = (NetworkImageView) view.findViewById(R.id.note_avatar);
            avatarView.setDefaultImageResId(R.drawable.placeholder);
            avatarView.setImageUrl(note.getIconURL(), WordPress.imageLoader);
<<<<<<< HEAD

            int imageID = getResources().getIdentifier("note_icon_" + note.getType(), "drawable", getActivity().getPackageName());
=======
            
            int imageID = getResources().getIdentifier("note_icon_" + note.getType(),"drawable", getActivity().getPackageName());
>>>>>>> origin/master
            if (imageID > 0) {
                final ImageView iconView = (ImageView) view.findViewById(R.id.note_icon);
                iconView.setImageResource(imageID);
            }
<<<<<<< HEAD

            final TextView unreadIndicator = (TextView) view.findViewById(R.id.unread_indicator);
            if (note.isUnread()) {
                unreadIndicator.setVisibility(View.VISIBLE);
            } else {
                unreadIndicator.setVisibility(View.GONE);
            }
            if (note.isPlaceholder()) {
                placeholderLoading.setVisibility(View.VISIBLE);
            } else {
                placeholderLoading.setVisibility(View.GONE);
            }

            return view;
        }

        public Note getLastNote() {
            return getItem(getCount() - 1);
        }

        public void addAll(List<Note> notes) {
            Collections.sort(notes, new Note.TimeStampComparator());
            if (notes.size() == 0) {
                // No more notes available
                mAllNotesLoaded = true;
                if (mProgressFooterView != null)
                    mProgressFooterView.setVisibility(View.GONE);
            } else {
                Iterator<Note> noteIterator = notes.iterator();
                while (noteIterator.hasNext()) {
=======
            
            final TextView unreadIndicator = (TextView) view.findViewById(R.id.unread_indicator);
            if (note.isUnread()) {
                unreadIndicator.setVisibility(View.VISIBLE);
            }
            else {
                unreadIndicator.setVisibility(View.GONE);
            }
            
            return view;
        }
        public Note getLastNote(){
            return getItem(getCount()-1);
        }
        public void addAll(List<Note> notes){

            if (notes.size() == 0) {
                // No more notes available
                mAllNotesLoaded = true;
                if(mProgressFooterView != null)
                    mProgressFooterView.setVisibility(View.GONE);
            } else {
                Iterator<Note> noteIterator = notes.iterator();
                while(noteIterator.hasNext()){
>>>>>>> origin/master
                    add(noteIterator.next());
                }
            }
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            if (mProgressFooterView != null)
                mProgressFooterView.setVisibility(View.GONE);
        }
    }

    private class ListScrollListener implements AbsListView.OnScrollListener {
        @Override
<<<<<<< HEAD
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
=======
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount){
>>>>>>> origin/master
            if (mAllNotesLoaded)
                return;

            // if we're within 5 from the last item we should ask for more items
            if (firstVisibleItem + visibleItemCount >= totalItemCount - LOAD_MORE_WITHIN_X_ROWS) {
                if (totalItemCount <= 1)
                    mProgressFooterView.setVisibility(View.GONE);
                else
                    mProgressFooterView.setVisibility(View.VISIBLE);

                requestMoreNotifications();
            }
        }
<<<<<<< HEAD

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
=======
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState){
>>>>>>> origin/master
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (outState.isEmpty()) {
            outState.putBoolean("bug_19917_fix", true);
        }
        super.onSaveInstanceState(outState);
    }
<<<<<<< HEAD

=======
    
>>>>>>> origin/master
}