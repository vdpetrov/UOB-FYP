package com.example.vdpetrov.pleasefinal;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class ProductsAdapter extends RecyclerView.Adapter {
    private static final String BASE_IMAGE_URL = "http://lamp.scim.brad.ac.uk:50857/images/";
    private JSONArray products;

    public ProductsAdapter(JSONArray products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Create product placeholder
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.catalog_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Populate the placeholder with product data
        final ProductsAdapter.ViewHolder vh = (ProductsAdapter.ViewHolder) holder;
        JSONObject product = null;
        try {
            // Get the current product
            product = products.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            //Populate the product placeholder with name, description and price
            vh.producNameTV.setText(product.getString("name"));
            vh.producDescriptionTV.setText(product.getString("description"));
            vh.producPriceTV.setText("£" + product.getString("price"));
            // Download the image from the appropriate URL
            final String imageURL = BASE_IMAGE_URL + product.getString("image");
            ImageRequest request = new ImageRequest(imageURL, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    // Set the image on the placeholder when download is completed
                    vh.getProductImageView().setImageBitmap(response);
                }
            }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Image cannot load", imageURL);
                }
            });
            //Start the request
            Volley.newRequestQueue(vh.getParent().getContext()).add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return products.length();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //Wrapper around the product placeholder
        private View parent;
        private TextView producNameTV;
        private TextView producDescriptionTV;
        private TextView producPriceTV;
        private ImageView productImageView;
        private Button productPurchBtn;

        public ViewHolder(View v) {
            super(v);
            parent = v;
            producNameTV = v.findViewById(R.id.product_name);
            producDescriptionTV = v.findViewById(R.id.product_description);
            producPriceTV = v.findViewById(R.id.product_price);
            productImageView = v.findViewById(R.id.product_image);
            productPurchBtn = v.findViewById(R.id.product_purch_btn);
        }

        public TextView getProducDescriptionTV() {
            return producDescriptionTV;
        }

        public TextView getProducNameTV() {
            return producNameTV;
        }

        public TextView getProducPriceTV() {
            return producPriceTV;
        }

        public Button getProductPurchBtn() {
            return productPurchBtn;
        }

        public ImageView getProductImageView() {
            return productImageView;
        }

        public View getParent() {
            return parent;
        }
    }
}
