package com.nivedita.realestate.propertylist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
    //private final List<DummyContent.DummyItem> mValues;
    private PropertyListClickListener propertyListClickListener;


    public PropertyListAdapter(Context context, PropertyListClickListener propertyListClickListener) {
        this.context = context;
        propertyAgencies = new LinkedList<>();
       // mValues = DummyContent.ITEMS;
        this.propertyListClickListener = propertyListClickListener;
        propertyAgencies = new LinkedList<>();
    }

    public interface PropertyListClickListener {

        void onClickListener(String itemPosition);
    }

    public class PropertyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView mIdView;
        final TextView mContentView;
        final ImageView propertyImageView;

        public PropertyViewHolder(View itemView) {
            super(itemView);
            mIdView = (TextView) itemView.findViewById(R.id.id_text);
            mContentView = (TextView) itemView.findViewById(R.id.id_content);
            propertyImageView = (ImageView)itemView.findViewById(R.id.media_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

           // DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();
            PropertyListAdapter.this.propertyListClickListener.onClickListener("");

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
        return propertyAgencies.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        PropertyViewHolder propertyViewHolder = (PropertyViewHolder) viewHolder;

        propertyViewHolder.mIdView.setText(propertyAgencies.get(position).getOwner().getName());
        propertyViewHolder.mContentView.setText(propertyAgencies.get(position).getOwner().getLastName());
        Glide.with(context)
                .load(propertyAgencies.get(position).getImageUrls().get(2))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .crossFade()
                .into(propertyViewHolder.propertyImageView);
       // viewHolder.itemView.setTag(mValues.get(position));
    }

    public List<Listing> getProperties(){
        return propertyAgencies;
    }

    public void setPropertyAgencies(List<Listing> propertyAgencies){
        this.propertyAgencies = propertyAgencies;
    }

    public void add(Listing r) {
        propertyAgencies.add(r);
        notifyItemInserted(propertyAgencies.size() - 1);
    }

    public void addAll(List<Listing> moveResults) {
        for (Listing result : moveResults) {
            add(result);
        }
    }

}
