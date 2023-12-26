package com.google.ar.core.examples.java.helloar;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    GridView gridView;
    ImageView img1, img2, img3;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        List<Product> productList = new ArrayList<>();
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("products");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Product p = new Product(d.child("Name").getValue().toString(), d.child("img").getValue().toString(), d.child("img1").getValue().toString(), d.child("img2").getValue().toString(), d.child("img3").getValue().toString(), 0);
                    productList.add(p);
                }
                gridView = rootView.findViewById(R.id.gridView);
                ProductAdapter adapter = new ProductAdapter(productList);
                gridView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        rootView.findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                FragmentNavigation navRegister = (FragmentNavigation) getActivity();
                navRegister.navigate(new loginFragment(), false);
            }
        });

     /*   productList.add(new Product("Product 1", R.drawable.image1));
        productList.add(new Product("Product 2", R.drawable.image2));
        productList.add(new Product("Product 2", R.drawable.image3));
        productList.add(new Product("Product 2", R.drawable.image4));
        productList.add(new Product("Product 2", R.drawable.image8));
        productList.add(new Product("Product 2", R.drawable.image5));
        productList.add(new Product("Product 2", R.drawable.image6));
        productList.add(new Product("Product 2", R.drawable.image7));
        productList.add(new Product("Product 2", R.drawable.image7));
        productList.add(new Product("Product 2", R.drawable.image7));
        productList.add(new Product("Product 2", R.drawable.image2));
        productList.add(new Product("Product 2", R.drawable.image3));
        productList.add(new Product("Product 2", R.drawable.image7));*/
        // ... Ajoutez d'autres produits

        // Créez et configurez l'adaptateur du RecyclerView
        GridView gridView = rootView.findViewById(R.id.gridView);
        ProductAdapter adapter = new ProductAdapter(productList);
        gridView.setAdapter(adapter);
        Log.d("vvvv", "vvvv" + productList);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product selectedProduct = productList.get(position);

                // Passer les données au fragment de détails
                DetailsFragment detailsFragment = new DetailsFragment();
                Bundle bundle = new Bundle();

                bundle.putString("Image1", selectedProduct.getImg1());
                bundle.putString("Image2", selectedProduct.getImg2());
                bundle.putString("Image3", selectedProduct.getImg3());
                bundle.putString("productName", selectedProduct.getName());
                bundle.putString("productImage", selectedProduct.getImageResourceId());
                bundle.putFloat("price", selectedProduct.getPrice());
                detailsFragment.setArguments(bundle);
                Log.d("values:", "product" + selectedProduct.toString());
                int commit = getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, detailsFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return rootView;
    }
}
