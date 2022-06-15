package com.jlp.dishwasher

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.clearAllMocks
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module

abstract class TestBase {


    @get:Rule
    val rule = InstantTaskExecutorRule()

    protected abstract val koinModule: Module

    private lateinit var koin: Koin

    @Before
    open fun setup() {
        clearAllMocks()
        koin = startKoin {
            modules(koinModule)
        }.koin
    }

    @After
    open fun tearDown() {
        stopKoin()
    }
}
