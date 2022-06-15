package com.jlp.dishwasher.injection

import com.jlp.dishwasher.repository.NetworkRepository
import org.koin.dsl.module


val repositoryModule = module {
    single {
        NetworkRepository()
    }

}
