package com.god.haircutmanager.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.god.haircutmanager.Model.Customer;
import com.god.haircutmanager.R;

public class AddCustomerActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText phoneEditText;
    private Button saveButton;

    private long customerId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener((View view) -> {
            saveCustomer();
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("customer_id")) {
            customerId = extras.getLong("customer_id");
            loadCustomerData();
        }
    }
    private void loadCustomerData() {
        if (customerId != -1) {
            Customer customer = Customer.findById(Customer.class, customerId);
            if (customer != null) {
                nameEditText.setText(customer.getName());
                phoneEditText.setText(customer.getPhone());
            }
        }
    }
    private void saveCustomer() {
        String name = nameEditText.getText().toString();
        String phone = phoneEditText.getText().toString();

        if (!name.isEmpty() && !phone.isEmpty()) {
            Customer customer;
            if (customerId != -1) {
                customer = Customer.findById(Customer.class, customerId);
            } else {
                customer = new Customer();
            }
            customer.setName(name);
            customer.setPhone(phone);
            customer.save();
            Toast.makeText(this, "Customer saved successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }
}
