package com.youarelaunched.challenge.ui.screen.view

import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.data.repository.model.Vendor

class MockedVendorsRepository: VendorsRepository {

    override suspend fun getVendors(companyName: String?): List<Vendor> {
        if (companyName == null) {
            return listOf(
                Vendor(
                    id = 1,
                    companyName = "Test",
                    coverPhoto = "",
                    area = "",
                    favorite = false,
                    categories = null,
                    tags = null,
                )
            )
        }

        return emptyList()
    }
}