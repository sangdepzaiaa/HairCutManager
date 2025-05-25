package com.god.haircutmanager.Adapter;

import android.view.*;
import android.widget.AdapterView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.god.haircutmanager.R;
import com.god.haircutmanager.UI.LoginActivity;
import com.orm.SugarRecord;

import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.ViewHolder<T>> {

    protected List<T> itemList;
    protected int selectedPosition = -1;

    public BaseAdapter(List<T> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getItemLayoutResourceId(), parent, false);

        return createViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder<T> holder, int position) {
        T item = itemList.get(position);
        bindViewHolder(holder, item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void updateData(List<T> updatedList) {
        itemList = updatedList;
        notifyDataSetChanged();
    }

    public int getSelectedPosition(){
        return  selectedPosition;
    }
    public void setSelectedPosition(int i){
        selectedPosition = i;
    }

    protected abstract int getItemLayoutResourceId();

    protected abstract ViewHolder<T> createViewHolder(View view);

    protected abstract void bindViewHolder(ViewHolder<T> holder, T item);

    static class ViewHolder<T> extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
