package com.google.ar.core.examples.java.helloar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

    private List<Product> productList;


    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ProductViewHolder holder;

        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list, parent, false);
            holder = new ProductViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ProductViewHolder) view.getTag();
        }

        Product product = productList.get(position);
        holder.bind(product);

        return view;
    }

    public static class ProductViewHolder {
        private final TextView productName;
        private final ImageView productImage;
        public ProductViewHolder(View itemView) {
            productName = itemView.findViewById(R.id.productName);
            productImage = itemView.findViewById(R.id.productImage);
        }

        public void bind(Product product) {
            productName.setText(product.getName());
            Picasso.get().load(product.getImageResourceId()).into(productImage);
        }
    }
}
