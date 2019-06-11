package com.nirajsarode.inventory;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    StorageReference ref;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    ArrayList<Product> alist;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseAuth mAuth;
    private FirebaseFirestore mFireStore;

    MyAdapter adapter;

    private static final String TAG = "MyActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mFireStore = FirebaseFirestore.getInstance();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        alist = new ArrayList<Product>();


}
    @Override
    protected void onStart() {
        super.onStart();

        mAuth = FirebaseAuth.getInstance();
        mFireStore = FirebaseFirestore.getInstance();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        alist = new ArrayList<Product>();

        mFireStore.collection("products").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                if(!documentSnapshots.isEmpty()){
                    List<DocumentSnapshot> list = documentSnapshots.getDocuments();
                    for (DocumentSnapshot d: list){
                        Log.v(TAG,"Inside");
                        Product p = d.toObject(Product.class);
                        alist.add(p);
                    }
                    adapter = new MyAdapter(MainActivity.this,alist);
                    recyclerView.setAdapter(adapter);

                }
            }
        });
    }


 /*   public void newProduct(View view) {
        Intent ad = new Intent(MainActivity.this, NewProductActivity.class);
        startActivity(ad);
    }
    public void newDialog(View view)
    {
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(MainActivity.this);
        View mView=getLayoutInflater().inflate(R.layout.dialogbox,null);
        final TextView name=mView.findViewById(R.id.product_name);
        final TextView cost=mView.findViewById(R.id.product_cost);
        final TextView type=mView.findViewById(R.id.product_type);
        final TextView quantity=mView.findViewById(R.id.product_quantity);
        mBuilder.setPositiveButton()
    }*/
}
