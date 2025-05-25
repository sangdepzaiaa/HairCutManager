package com.god.haircutmanager.Adapter;

import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;
import com.god.haircutmanager.Model.User;
import com.god.haircutmanager.R;

import java.util.List;

public class UserAdapter extends BaseAdapter<User> {

    public UserAdapter(List<User> itemList) {
        super(itemList);
    }

    @Override
    protected int getItemLayoutResourceId() {
        return R.layout.item_user;
    }

    @Override
    protected ViewHolder<User> createViewHolder(View view) {
        return new UserViewHolder(view);
    }

    @Override
    protected void bindViewHolder(ViewHolder<User> holder, User user) {
        UserViewHolder userViewHolder = (UserViewHolder) holder;
        userViewHolder.usernameTextView.setText(user.getUsername());
        holder.itemView.setOnCreateContextMenuListener((ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) -> {
            menu.setHeaderTitle("Options");
            setSelectedPosition( holder.getBindingAdapterPosition());
        });
    }

    static class UserViewHolder extends ViewHolder<User> {
        TextView usernameTextView;
        TextView roleTextView;

        UserViewHolder(View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            roleTextView = itemView.findViewById(R.id.roleEditText);
        }
    }
}

