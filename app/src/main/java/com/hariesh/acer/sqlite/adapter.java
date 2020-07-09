package com.hariesh.acer.sqlite;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;



public class adapter extends RecyclerView.Adapter<adapter.ProductViewHolder> {
    private Context mCtx;


    public List<Todo> productList;
    public adapter(){

    }

    public Todo getProductList() {
        return productList.get(0);
    }

    public adapter(Context mCtx, List<Todo> list) {
        this.mCtx = mCtx;
        this.productList = list;
    }
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_view, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        final Todo product = productList.get(position);
        final DisplayAll dall=new DisplayAll();
        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getHead());
        holder.textViewShortDesc.setText(product.getDescription());
        holder.textViewstatus.setText(String.valueOf(product.getStatus()));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(product.getStatus()==0) {
                    Intent intent = new Intent(mCtx, selected.class);
                    intent.putExtra("Key", product.getId());
                    intent.putExtra("Head", product.getHead());
                    intent.putExtra("Des", product.getDescription());
                    intent.putExtra("Status", String.valueOf(product.getStatus()));
                    startActivity(mCtx, intent, null);
                }
                else{
                    Intent intent = new Intent(mCtx, completedstatus.class);
                    intent.putExtra("Key", product.getId());
                    intent.putExtra("Head", product.getHead());
                    intent.putExtra("Des", product.getDescription());
                    intent.putExtra("Status", String.valueOf(product.getStatus()));
                    startActivity(mCtx, intent, null);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewstatus;
        View mView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.idhead);
            textViewShortDesc = itemView.findViewById(R.id.deschead);
            textViewstatus = itemView.findViewById(R.id.status);
            mView=itemView;
        }
    }


}
