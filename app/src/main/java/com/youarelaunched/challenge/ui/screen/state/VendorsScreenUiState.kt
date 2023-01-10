package com.youarelaunched.challenge.ui.screen.state

import com.youarelaunched.challenge.data.repository.model.Vendor

data class VendorsScreenUiState(
    val searchText: String = "",
    val vendors: List<Vendor>?
)
