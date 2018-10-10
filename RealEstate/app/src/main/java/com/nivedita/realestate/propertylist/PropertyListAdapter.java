package com.nivedita.realestate.propertylist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nivedita.realestate.R;
import com.nivedita.realestate.model.DummyContent;
import com.nivedita.realestate.model.property.Listing;


import java.util.LinkedList;
import java.util.List;

/**
 * PropertyList Adapter which we will bind to the recycler view.
 */

public class PropertyListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Listing> propertyAgencies;
    private Context context;
    private final List<DummyContent.DummyItem> mValues;
    private PropertyListClickListener propertyListClickListener;


    public PropertyListAdapter(Context context, PropertyListClickListener propertyListClickListener) {
        this.context = context;
        propertyAgencies = new LinkedList<>();
        mValues = DummyContent.ITEMS;
        this.propertyListClickListener = propertyListClickListener;
    }

    public interface PropertyListClickListener {

        void onClickListener(String itemPosition);
    }

    public class PropertyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView mIdView;
        final TextView mContentView;


        public PropertyViewHolder(View itemView) {
            super(itemView);
            mIdView = (TextView) itemView.findViewById(R.id.id_text);
            mContentView = (TextView) itemView.findViewById(R.id.content);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();
            PropertyListAdapter.this.propertyListClickListener.onClickListener(item.id);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        viewHolder = getViewHolder(parent, layoutInflater);

        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.property_list_content, parent, false);
        viewHolder = new PropertyViewHolder(v1);
        return viewHolder;
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        PropertyViewHolder propertyViewHolder = (PropertyViewHolder) viewHolder;

        propertyViewHolder.mIdView.setText(mValues.get(position).id);
        propertyViewHolder.mContentView.setText(mValues.get(position).content);

        viewHolder.itemView.setTag(mValues.get(position));
    }
}
