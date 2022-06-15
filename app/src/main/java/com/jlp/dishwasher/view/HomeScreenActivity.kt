package com.jlp.dishwasher.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jlp.dishwasher.R
import com.jlp.dishwasher.adapter.ProductListAdapter
import com.jlp.dishwasher.model.CustomProduct
import com.jlp.dishwasher.viewmodel.HomeScreenViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent


class HomeScreenActivity : BaseActivity(), KoinComponent, ProductListAdapter.ItemClickListener {

    private val homeScreenViewModel: HomeScreenViewModel by viewModel()
    private var adapter: ProductListAdapter? = null
    private var recyclerView: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }


    private fun init() {
        homeScreenViewModel.getProducts()
        recyclerView = findViewById(R.id.productList)

        recyclerView?.layoutManager = GridLayoutManager(this, 3)



        homeScreenViewModel.productsLiveData.observe(this) {
            setUpListView(it)
        }

        homeScreenViewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        homeScreenViewModel.loading.observe(this) {
            if (it) {
                showProgress()
            } else {
                cancelProgress()
            }
        }
    }


    private fun setUpListView(productList: List<CustomProduct>) {

        adapter = ProductListAdapter(productList, this, this)
        recyclerView?.adapter = adapter
    }

    override fun onItemClick(product: CustomProduct) {
        val intent = Intent(this, ProductDetailsActivity::class.java)
        intent.apply {
            putExtra("product", product)
            startActivity(intent)
        }
    }
}

