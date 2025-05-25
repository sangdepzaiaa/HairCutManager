package com.god.haircutmanager.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.god.haircutmanager.Model.HairStyle;
import com.god.haircutmanager.R;

public class AddHairStyleActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText imagePathEditText;
    private EditText priceEditText;
    private EditText descriptionEditText;
    private Button saveButton;
    private long hairStyleId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hairstyle);

        nameEditText = findViewById(R.id.nameEditText);
        imagePathEditText = findViewById(R.id.imagePathEditText);
        priceEditText = findViewById(R.id.priceEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(view-> {
            saveHairStyle();
        });
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("hairstyle_id")) {
            hairStyleId = extras.getLong("hairstyle_id");
            loadHairStyleData();
        }
    }
    private void loadHairStyleData() {
        if (hairStyleId != -1) {
            HairStyle hairStyle = HairStyle.findById(HairStyle.class, hairStyleId);
            if (hairStyle != null) {
                nameEditText.setText(hairStyle.getName());
                imagePathEditText.setText(hairStyle.getImagePath());
                priceEditText.setText(String.valueOf(hairStyle.getPrice()));
                descriptionEditText.setText(hairStyle.getDescription());
            }
        }
    }

    private void saveHairStyle() {
        String name = nameEditText.getText().toString();
        String imagePath = imagePathEditText.getText().toString();
        double price = Double.parseDouble(priceEditText.getText().toString());
        String description = descriptionEditText.getText().toString();

        if (!name.isEmpty() && !imagePath.isEmpty() && !description.isEmpty()) {
            HairStyle hairStyle;

            if (hairStyleId != -1) {
                hairStyle = HairStyle.findById(HairStyle.class, hairStyleId);
            } else {
                hairStyle = new HairStyle();
            }

            hairStyle.setName(name);
            hairStyle.setImagePath(imagePath);
            hairStyle.setPrice(price);
            hairStyle.setDescription(description);
            hairStyle.save();

            Toast.makeText(this, "HairStyle saved successfully", Toast.LENGTH_SHORT).show();

            finish();
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }
}
