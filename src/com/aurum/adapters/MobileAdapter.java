package com.aurum.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aurum.R;
import com.aurum.models.Mobile;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 2/3/16.
 */
public class MobileAdapter extends RecyclerView.Adapter<MobileAdapter.ViewHolder> {

    private Context context;
    private List<Mobile> mobiles = new ArrayList<Mobile>();

    public MobileAdapter(Context context, List<Mobile> mobiles) {
        this.context = context;
        this.mobiles = mobiles;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View cardView = inflater.inflate(R.layout.item_child, null, false);
        ViewHolder viewHolder = new ViewHolder(cardView);
        viewHolder.mobileImage = (ImageView) cardView.findViewById(R.id.image_mobile);
        viewHolder.modelName = (TextView) cardView.findViewById(R.id.text_mobile_model);
        viewHolder.price = (TextView) cardView.findViewById(R.id.text_mobile_price);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ImageView mobileImageView = (ImageView) holder.mobileImage;
        mobileImageView.setImageResource(mobiles.get(position).imageResource);

        TextView modelTextView = (TextView) holder.modelName;
        modelTextView.setText(mobiles.get(position).modelName);

        TextView priceTextView = (TextView) holder.price;
        priceTextView.setText(mobiles.get(position).price);

    }

    @Override
    public int getItemCount() {
        return mobiles.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mobileImage;
        TextView modelName;
        TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            mobileImage = (ImageView) itemView.findViewById(R.id.image_mobile);
            modelName = (TextView) itemView.findViewById(R.id.text_mobile_model);
            price = (TextView) itemView.findViewById(R.id.text_mobile_price);
        }
    }

}
