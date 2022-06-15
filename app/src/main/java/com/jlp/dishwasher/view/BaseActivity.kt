package com.jlp.dishwasher.view

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    private var dialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = ProgressDialog(this)
    }

    fun showProgress() {
        dialog?.show()
    }

    fun cancelProgress() {
        if (dialog!!.isShowing) dialog?.cancel()
    }


}