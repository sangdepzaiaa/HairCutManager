package com.god.haircutmanager.UI;


import java.util.List;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.AdapterView;
import androidx.appcompat.app.AlertDialog;
import com.god.haircutmanager.Adapter.BaseAdapter;
import com.god.haircutmanager.Adapter.HairStyleAdapter;
import com.god.haircutmanager.Model.Customer;
import com.god.haircutmanager.Model.HairStyle;
import com.god.haircutmanager.R;

public class HairStyleManagerActivity extends BaseManagerActivity<HairStyle> {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_manager;
    }

    @Override
    protected Class<?> getAddItemActivityClass() {
        return AddHairStyleActivity.class;
    }

    @Override
    protected List<HairStyle> getItems() {
        return HairStyle.listAll(HairStyle.class);
    }

    @Override
    protected BaseAdapter<HairStyle> createAdapter(List<HairStyle> itemList) {
        return new HairStyleAdapter(itemList);
    }

    @Override
    protected List<HairStyle> performSearch(String query) {
        return HairStyle.findWithQuery(HairStyle.class, "SELECT * FROM HairStyle WHERE LOWER(name) LIKE ?", "%" + query.toLowerCase() + "%");
    }
    @Override
    protected void handleEditItemByPosition(int selectedPosition) {
        HairStyle hairStyle = itemList.get(selectedPosition);
        Intent editIntent = new Intent(HairStyleManagerActivity.this, AddHairStyleActivity.class);
        editIntent.putExtra("hairstyle_id", hairStyle.getId());
        startActivity(editIntent);
    }

    @Override
    protected void handleDeleteItemByPosition(int selectedPosition) {
        HairStyle hairStyle = itemList.get(selectedPosition);
        hairStyle.delete();
    }
}
