package com.god.haircutmanager.Adapter;

import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.god.haircutmanager.Model.Customer;
import com.god.haircutmanager.R;

import java.util.List;

public class CustomerAdapter extends BaseAdapter<Customer> {

    private OnItemClickListener ;

    public CustomerAdapter(List<Customer> itemList) {
        super(itemList);
    }

    public CustomerAdapter(List<Customer> itemList, OnItemClickListener listener) {
        super(itemList);
        this.onItemClickListener = listener;
    }

    @Override
    protected int getItemLayoutResourceId() {
        return R.layout.item_customer;
    }

    @NonNull
    @Override
    public ViewHolder<Customer> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getItemLayoutResourceId(), parent, false);
        return createViewHolder(view);
    }
    @Override
    protected ViewHolder<Customer> createViewHolder(View view) {
        return new CustomerViewHolder(view);
    }

    @Override
    protected void bindViewHolder(ViewHolder<Customer> holder, Customer customer) {
        CustomerViewHolder customerViewHolder = (CustomerViewHolder) holder;
        customerViewHolder.nameTextView.setText(customer.getName());
        customerViewHolder.phoneTextView.setText(customer.getPhone());
        holder.itemView.setOnCreateContextMenuListener((ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) -> {
            menu.setHeaderTitle("Options");
            setSelectedPosition( holder.getBindingAdapterPosition());
        });
    }

    static class CustomerViewHolder extends ViewHolder<Customer>{
        TextView nameTextView;
        TextView phoneTextView;

        CustomerViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            phoneTextView = itemView.findViewById(R.id.phoneTextView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Customer customer);
    }
}
