package com.youarelaunched.challenge.data.repository

import com.youarelaunched.challenge.data.repository.model.Vendor

interface VendorsRepository {

    suspend fun getVendors(
        companyName: String? = null,
    ): List<Vendor>
}