package com.nirajsarode.inventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NewProductActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseFirestore mFireStore;

    Button submitbt;
    Button plus;
    Button minus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);


        mFireStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        submitbt = findViewById(R.id.submitandsave);
        plus = findViewById(R.id.plusbt);
        minus = findViewById(R.id.minusbt);

/*
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quan = String.valueOf(increment());
            }
        });


        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quan = String.valueOf(decrement());
            }
        });
*/
        EditText nameET = (EditText) findViewById(R.id.detail_product_name);
        final String name = nameET.getText().toString();
        if (TextUtils.isEmpty(name)) {
            nameET.setError("Name Field Cannot be Empty");
            return;
        }

        EditText typeEt = (EditText) findViewById(R.id.detail_product_type);
        final String con = typeEt.getText().toString();
        if (TextUtils.isEmpty(con)) {
            typeEt.setError("Type Field Cannot be Empty");
            return;
        }
        EditText priceEt = (EditText) findViewById(R.id.detail_product_price);
        final String bt = priceEt.getText().toString();
        if (TextUtils.isEmpty(bt)) {
            priceEt.setError("Price Field Cannot be Empty");
            return;
        }
        final String user_id = mAuth.getCurrentUser().getUid();
        String uEmail = mAuth.getCurrentUser().getEmail();


        EditText quantityEt = (EditText) findViewById(R.id.detail_product_quantity);
        final String quan = quantityEt.getText().toString();
        if (TextUtils.isEmpty(quan)) {
            quantityEt.setError("Quantity Field Cannot be Empty");
            return;
        }



        final Map<String,Object> data  = new HashMap<>();
        data.put("name", name);
        data.put("type", con);
        data.put("price", bt);
        data.put("quantity", quan);


        submitbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFireStore.collection("products").document(name).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getBaseContext(), "Product Added", Toast.LENGTH_LONG).show();
                        Intent ad = new Intent(NewProductActivity.this, MainActivity.class);
                        finish();
                        startActivity(ad);
                    }
                });
            }
        });
    }
/*
    private int increment() {

        EditText quantityEt = (EditText) findViewById(R.id.detail_product_quantity);
        String qua = quantityEt.getText().toString().trim();

        int quantity = Integer.parseInt(qua);
        if (quantity > 0) {
            quantity = quantity + 1;
            quantityEt.setText(quantity);
        } else if (quantity == 0) {
            Toast.makeText(NewProductActivity.this, getString(R.string.detail_update_zero_quantity),
                    Toast.LENGTH_SHORT).show();
        }
        return quantity;
    }

    private int decrement() {

        EditText quantityEt = (EditText) findViewById(R.id.detail_product_quantity);
        String qua = quantityEt.getText().toString().trim();

        int quantity = Integer.parseInt(qua);
        if (quantity > 0) {
            quantity = quantity - 1;
            quantityEt.setText(quantity);
        } else if (quantity == 0) {
            Toast.makeText(NewProductActivity.this, getString(R.string.detail_update_zero_quantity),
                    Toast.LENGTH_SHORT).show();
        }
        return quantity;
    }*/
}
