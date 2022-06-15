package com.jlp.dishwasher.injection

import com.jlp.dishwasher.viewmodel.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {

    viewModel {
        HomeScreenViewModel(get())
    }
}


