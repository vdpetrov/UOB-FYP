package com.example.vdpetrov.pleasefinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class CatalogActivity extends AppCompatActivity {

    private String categoty;
    private static final String BASE_URL = "http://lamp.scim.brad.ac.uk:50857/Catalog.php?category=";
    private RecyclerView catalogRecyclerView;
    private RecyclerView.Adapter catalogAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        categoty = getIntent().getStringExtra("category");
        catalogRecyclerView = findViewById(R.id.catalog_recycler_view);
        catalogRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        catalogRecyclerView.setLayoutManager(layoutManager);
        fetchProducts();
    }

    private void fetchProducts() {
        //Fetches the products from the API as a JSON array and creates the adapter
        String url = BASE_URL + categoty;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                catalogAdapter = new ProductsAdapter(response);
                catalogRecyclerView.setAdapter(catalogAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(request);
    }
}
