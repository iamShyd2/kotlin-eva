package com.example.kotlin_eva.activities

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_eva.adapters.ProductsAdapter
import com.example.kotlin_eva.R
import com.example.kotlin_eva.interfaces.AuthApiListener
import com.example.kotlin_eva.interfaces.ProductsApiListener
import com.example.kotlin_eva.models.AppContext
import com.example.kotlin_eva.models.Product
import com.example.kotlin_eva.services.AuthApi
import com.example.kotlin_eva.services.ProductsApi
import com.example.kotlin_eva.services.Statusbar


class MainActivity : AppCompatActivity(), ProductsApiListener, AuthApiListener {

    val products = ArrayList<Product>()
    lateinit var productsRV: RecyclerView
    lateinit var productsAdapter: ProductsAdapter
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Statusbar.makeWhite(this)
        AuthApi.validateToken(this)
            .start()
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
    }

    override fun onResume() {
        super.onResume()
        AppContext.setCartCount(AppContext.cartCount, this)
    }

    override fun onFinishFetchProducts(products: ArrayList<Product>) {
        productsRV = findViewById<RecyclerView>(R.id.products)
        progressBar.visibility = View.GONE
        productsRV.visibility = View.VISIBLE
        productsAdapter = ProductsAdapter(applicationContext, products)
        productsRV.adapter = productsAdapter
        productsRV.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onFinishFetchProduct(product: Product) {
    }

    override fun onFinishValidateToken() {
        ProductsApi.onFetchProducts(this, this)
            .start()
    }


}