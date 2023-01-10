package com.youarelaunched.challenge.data.network.api

import com.youarelaunched.challenge.data.network.models.NetworkVendor

interface ApiVendors {

    suspend fun getVendors(
        companyName: String? = null,
    ): List<NetworkVendor>
}