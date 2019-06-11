package com.nirajsarode.inventory;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Product> products;

    public MyAdapter(Context c, ArrayList<Product> p){
        context = c;
        products = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.product_list,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(products.get(i).getName());
        myViewHolder.type.setText(products.get(i).getType());
        myViewHolder.quantity.setText(String.valueOf(products.get(i).getQuantity()));
        myViewHolder.cost.setText(String.valueOf(products.get(i).getCost()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,type,quantity,cost;
        public MyViewHolder(View itemView){
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.product_name_card);
            type = (TextView) itemView.findViewById(R.id.product_type_card);
            quantity = (TextView) itemView.findViewById(R.id.product_quantity_card);
            cost = (TextView) itemView.findViewById(R.id.product_price_card);
        }
    }

}
