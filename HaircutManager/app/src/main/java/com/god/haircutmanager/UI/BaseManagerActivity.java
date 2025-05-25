package com.god.haircutmanager.UI;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.god.haircutmanager.Adapter.BaseAdapter;
import com.god.haircutmanager.Model.Customer;
import com.god.haircutmanager.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orm.SugarRecord;

import java.util.List;

public abstract class BaseManagerActivity<T> extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BaseAdapter<T> adapter;
    protected List<T> itemList;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        registerForContextMenu(recyclerView);

        FloatingActionButton addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(view -> {
            startActivity(new Intent(BaseManagerActivity.this, getAddItemActivityClass()));
        });

        loadItems();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadItems();
        adapter.setSelectedPosition(-1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchItems(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        return true;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                if (searchView != null) {
                    searchView.setIconified(false);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int selectedPosition = adapter.getSelectedPosition();
        if(selectedPosition>-1){
            switch (item.getItemId()) {
                case R.id.action_edit:
                    handleEditItemByPosition(selectedPosition);
                    return true;
                case R.id.action_delete:
                    showDeleteConfirmationDialog(selectedPosition);
                    return true;
                default:
                    return super.onContextItemSelected(item);
            }
        }
        return false;
    }

    private void showDeleteConfirmationDialog(int selectedPosition) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Item")
                .setMessage("Are you sure you want to delete this?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    handleDeleteItemByPosition(selectedPosition);
                    loadItems();
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    protected abstract void handleEditItemByPosition(int selectedPosition);
    protected abstract void handleDeleteItemByPosition(int selectedPosition);

    protected void loadItems() {
        itemList = getItems();

        if (adapter == null) {
            adapter = createAdapter(itemList);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.updateData(itemList);
        }
    }

    private void searchItems(String query) {
        List<T> searchResults = performSearch(query);
        adapter.updateData(searchResults);
    }

    protected abstract int getLayoutResourceId();

    protected abstract Class<?> getAddItemActivityClass();

    protected abstract List<T> getItems();

    protected abstract BaseAdapter<T> createAdapter(List<T> itemList);

    protected abstract List<T> performSearch(String query);
}
