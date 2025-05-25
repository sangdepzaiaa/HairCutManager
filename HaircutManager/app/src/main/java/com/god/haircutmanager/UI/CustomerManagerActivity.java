package com.god.haircutmanager.UI;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.god.haircutmanager.Adapter.BaseAdapter;
import com.god.haircutmanager.Adapter.CustomerAdapter;
import com.god.haircutmanager.Model.Customer;
import com.god.haircutmanager.R;

import java.util.List;

public class CustomerManagerActivity extends BaseManagerActivity<Customer> {
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_manager;
    }

    @Override
    protected Class<?> getAddItemActivityClass() {
        return AddCustomerActivity.class;
    }

    @Override
    protected List<Customer> getItems() {
        return Customer.listAll(Customer.class);
    }

    @Override
    protected BaseAdapter<Customer> createAdapter(List<Customer> itemList) {
        return new CustomerAdapter(itemList);
    }

    @Override
    protected List<Customer> performSearch(String query) {
        return Customer.findWithQuery(Customer.class, "SELECT * FROM Customer WHERE LOWER(name) LIKE ?", "%" + query.toLowerCase() + "%");
    }
    @Override
    protected void handleEditItemByPosition(int selectedPosition) {
        Customer customer = itemList.get(selectedPosition);
        if(customer!=null){
            Intent editIntent = new Intent(CustomerManagerActivity.this, AddCustomerActivity.class);
            editIntent.putExtra("customer_id", customer.getId());
            startActivity(editIntent);
        }
    }

    @Override
    protected void handleDeleteItemByPosition(int selectedPosition) {
        Customer customer = itemList.get(selectedPosition);
        if(customer!=null) {
            customer.delete();
        }
    }
}
