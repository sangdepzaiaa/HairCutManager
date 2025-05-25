package com.god.haircutmanager.Adapter;

import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.god.haircutmanager.Model.HairStyle;
import com.god.haircutmanager.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HairStyleAdapter extends BaseAdapter<HairStyle> {

    public HairStyleAdapter(List<HairStyle> itemList) {
        super(itemList);
    }

    @Override
    protected int getItemLayoutResourceId() {
        return R.layout.item_hairstyle;
    }

    @Override
    protected ViewHolder<HairStyle> createViewHolder(View view) {
        return new HairStyleViewHolder(view);
    }

    @Override
    protected void bindViewHolder(ViewHolder<HairStyle> holder, HairStyle hairStyle) {
        HairStyleViewHolder hairStyleViewHolder = (HairStyleViewHolder) holder;
        hairStyleViewHolder.nameTextView.setText(hairStyle.getName());
        hairStyleViewHolder.priceTextView.setText("Price: " + String.valueOf(hairStyle.getPrice()));
        hairStyleViewHolder.descriptionTextView.setText(hairStyle.getDescription());

        Picasso.get().load(hairStyle.getImagePath())
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(hairStyleViewHolder.imageView);
        holder.itemView.setOnCreateContextMenuListener((ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) -> {
            menu.setHeaderTitle("Options");
            setSelectedPosition(holder.getBindingAdapterPosition());
        });
    }

    static class HairStyleViewHolder extends ViewHolder<HairStyle> {
        ImageView imageView;
        TextView nameTextView;
        TextView priceTextView;
        TextView descriptionTextView;

        HairStyleViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }
}

