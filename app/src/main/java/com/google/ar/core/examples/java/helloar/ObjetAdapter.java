package com.google.ar.core.examples.java.helloar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ObjetAdapter extends RecyclerView.Adapter<ObjetAdapter.ObjetViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }

    private ArrayList<Product> productList;
    private OnItemClickListener listener;

    public ObjetAdapter(ArrayList<Product> productList, OnItemClickListener listener) {
        this.productList = productList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ObjetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list, parent, false);
        return new ObjetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ObjetViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product, listener);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ObjetViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView titleTextView;

        public ObjetViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.productImage);
            titleTextView = itemView.findViewById(R.id.productName);
        }

        public void bind(final Product product, final OnItemClickListener listener) {
            titleTextView.setText(product.getName());
            Picasso.get().load(product.getImageResourceId()).into(imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(product);
                }
            });
        }
    }
}
