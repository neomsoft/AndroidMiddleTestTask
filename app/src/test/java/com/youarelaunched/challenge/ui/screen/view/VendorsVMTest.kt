package com.youarelaunched.challenge.ui.screen.view

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class VendorsVMTest {

    private val model = VendorsVM(MockedVendorsRepository())

    @Test
    fun getVendors_Successfully() = runTest {
        val res = model.getVendors()
        Assert.assertEquals(res.isNotEmpty(), true)
    }

    @Test
    fun getVendors_Error() = runTest {
        val res = model.getVendors("cafe")
        Assert.assertEquals(res.isNotEmpty(), false)
    }
}