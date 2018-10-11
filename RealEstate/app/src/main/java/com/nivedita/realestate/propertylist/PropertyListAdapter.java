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
        if(propertyAgencies.get(position).getIsPremium() == 1)
            propertyViewHolder.premium.setVisibility(View.VISIBLE);
        propertyViewHolder.property_area.setText(propertyAgencies.get(position).getArea());
        propertyViewHolder.location.setText(propertyAgencies.get(position).getLocation().getAddress());
        propertyViewHolder.bathroom.setText(Integer.toString(propertyAgencies.get(position).getBathrooms()));
        propertyViewHolder.bedroom.setText(Integer.toString(propertyAgencies.get(position).getBedrooms()));
        propertyViewHolder.carSpace.setText(Integer.toString(propertyAgencies.get(position).getCarspaces()));
        Glide.with(context)
                .load(propertyAgencies.get(position).getImageUrls().get(2))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .crossFade()
                .into(propertyViewHolder.propertyImageView);
        // viewHolder.itemView.setTag(mValues.get(position));
    }

    public List<Listing> getProperties() {
        return propertyAgencies;
    }

    public void setPropertyAgencies(List<Listing> propertyAgencies) {
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

    public interface PropertyListClickListener {

        void onClickListener(String itemPosition);
    }

    public class PropertyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView premium;
        final TextView property_area;
        final TextView location;
        final TextView bathroom;
        final TextView bedroom;
        final TextView carSpace;
        final ImageView propertyImageView;

        public PropertyViewHolder(View itemView) {
            super(itemView);
            premium = (TextView) itemView.findViewById(R.id.premium_flag);
            property_area = (TextView) itemView.findViewById(R.id.getArea_Of_Property);
            location = (TextView) itemView.findViewById(R.id.location_address);
            bathroom = (TextView) itemView.findViewById(R.id.bathroom);
            bedroom = (TextView) itemView.findViewById(R.id.bedrooms);
            carSpace = (TextView) itemView.findViewById(R.id.car_space);
            propertyImageView = (ImageView) itemView.findViewById(R.id.media_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            // DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();
            PropertyListAdapter.this.propertyListClickListener.onClickListener("");

        }
    }

}
