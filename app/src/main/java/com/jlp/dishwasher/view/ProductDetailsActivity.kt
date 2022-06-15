package com.jlp.dishwasher.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.jlp.dishwasher.R
import com.jlp.dishwasher.adapter.ViewPagerAdapter
import com.jlp.dishwasher.model.CustomProduct
import kotlinx.android.synthetic.main.activity_product_details.*

class ProductDetailsActivity : AppCompatActivity() {
    var product: CustomProduct? = null

    private lateinit var title: TextView
    private lateinit var price: TextView
    private lateinit var productInfo: TextView
    private lateinit var productCode: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        intent?.let {
            product = it.getSerializableExtra("product") as CustomProduct?
        }
        init()
        setData()
    }

    private fun init() {
        title = findViewById(R.id.title)
        price = findViewById(R.id.price)
        productInfo = findViewById(R.id.productInformation)
        productCode = findViewById(R.id.productCode)


    }

    private fun setData() {
        val viewPagerAdapter = product?.skus?.get(0)?.media?.images?.let {
            ViewPagerAdapter(
                it.urls,
                this
            )
        }
        viewPager.adapter = viewPagerAdapter

        title.text = product?.title
        price.text = "£" + product?.price?.now
        productInfo.text = product?.details?.productInformation
        productCode.text = product?.code
    }
}