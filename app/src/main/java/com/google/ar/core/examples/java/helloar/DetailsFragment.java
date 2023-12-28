package com.google.ar.core.examples.java.helloar;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;


public class DetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        // Récupérer les données du Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            String productName = bundle.getString("productName", "");
            String productImage1 = bundle.getString("Image1", "");
            String productImage2 = bundle.getString("Image2", "");
            String productImage3 = bundle.getString("Image3", "");
            float productPrice = bundle.getFloat("price", 0.0f);

            // Utilisez ces données pour afficher dans votre interface utilisateur
            TextView textView1 = view.findViewById(R.id.textView1);
            TextView textView2 = view.findViewById(R.id.textView2);
            ImageSlider imageSlider = view.findViewById(R.id.imageSlider);
            ProgressBar loadingProgressBar = view.findViewById(R.id.loadingProgressBar);

            textView1.setText(productName);
            textView2.setText(String.valueOf(productPrice));

            List<SlideModel> imageList = new ArrayList<>();
            // Chargez la première image
            if (!TextUtils.isEmpty(productImage1)) {
                showProgressBar(loadingProgressBar);
                Picasso.get().load(productImage1).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        hideProgressBar(loadingProgressBar);
                        imageList.add(new SlideModel(productImage1, productName, ScaleTypes.CENTER_CROP));
                        updateSlider(imageList, imageSlider);
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }

                    // ... (autres méthodes de l'interface Target)
                });
            }

            // Chargez la deuxième image
            if (!TextUtils.isEmpty(productImage2)) {
                showProgressBar(loadingProgressBar);
                Picasso.get().load(productImage2).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        hideProgressBar(loadingProgressBar);
                        imageList.add(new SlideModel(productImage2, productName, ScaleTypes.CENTER_CROP));
                        updateSlider(imageList, imageSlider);
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }

                    // ... (autres méthodes de l'interface Target)
                });
            }

            // Chargez la troisième image
            if (!TextUtils.isEmpty(productImage3)) {
                showProgressBar(loadingProgressBar);
                Picasso.get().load(productImage3).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        hideProgressBar(loadingProgressBar);
                        imageList.add(new SlideModel(productImage3, productName, ScaleTypes.CENTER_CROP));
                        updateSlider(imageList, imageSlider);
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }

                    // ... (autres méthodes de l'interface Target)
                });
            }
        }

        return view;
    }
      private void showProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(View.GONE);
    }

    private void updateSlider(List<SlideModel> imageList, ImageSlider imageSlider) {
        // Mettre à jour le slider avec toutes les images
        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP);
    }
}