package org.wordpress.android.ui.posts;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import org.wordpress.android.R;
import org.wordpress.android.models.CategoryNode;

import java.util.List;

public class CategoryArrayAdapter extends ArrayAdapter<CategoryNode> {
<<<<<<< HEAD
    int mResourceId;

    public CategoryArrayAdapter(Context context, int resource, List<CategoryNode> objects) {
        super(context, resource, objects);
        mResourceId = resource;
=======

    public CategoryArrayAdapter(Context context, int resource, List<CategoryNode> objects) {
        super(context, resource, objects);
>>>>>>> origin/master
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
<<<<<<< HEAD
        View rowView = inflater.inflate(mResourceId, parent, false);
=======
        View rowView = inflater.inflate(R.layout.categories_row, parent, false);
>>>>>>> origin/master
        TextView textView = (TextView) rowView.findViewById(R.id.categoryRowText);
        ImageView levelIndicatorView = (ImageView) rowView.findViewById(R.id.categoryRowLevelIndicator);
        textView.setText(Html.fromHtml(getItem(position).getName()));
        int level = getItem(position).getLevel();
        if (level == 1) { // hide ImageView
            levelIndicatorView.setVisibility(View.GONE);
        } else {
            ViewGroup.LayoutParams params = levelIndicatorView.getLayoutParams();
            params.width = (params.width / 2) * level;
            levelIndicatorView.setLayoutParams(params);
        }
        return rowView;
    }
}
