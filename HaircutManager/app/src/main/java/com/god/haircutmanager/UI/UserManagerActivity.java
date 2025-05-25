package com.god.haircutmanager.UI;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.AdapterView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import com.god.haircutmanager.Adapter.BaseAdapter;
import com.god.haircutmanager.Adapter.UserAdapter;
import com.god.haircutmanager.Model.HairStyle;
import com.god.haircutmanager.Model.User;
import com.god.haircutmanager.R;

import java.util.List;

public class UserManagerActivity extends BaseManagerActivity<User> {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_manager;
    }

    @Override
    protected Class<?> getAddItemActivityClass() {
        return AddUserActivity.class;
    }

    @Override
    protected List<User> getItems() {
        return User.listAll(User.class);
    }

    @Override
    protected BaseAdapter<User> createAdapter(List<User> itemList) {
        return new UserAdapter(itemList);
    }

    @Override
    protected List<User> performSearch(String query) {
        return User.findWithQuery(User.class, "SELECT * FROM User WHERE LOWER(username) LIKE ?", "%" + query.toLowerCase() + "%");
    }

    @Override
    protected void handleEditItemByPosition(int selectedPosition) {
        User user = itemList.get(selectedPosition);
        if(user!=null){
            Intent editIntent = new Intent(UserManagerActivity.this, AddUserActivity.class);
            editIntent.putExtra("user_id", user.getId());
            startActivity(editIntent);
        }
    }

    @Override
    protected void handleDeleteItemByPosition(int selectedPosition) {
        User user = itemList.get(selectedPosition);
        if(user!=null) {
            user.delete();
        }
    }
}

