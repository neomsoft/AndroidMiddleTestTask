package com.youarelaunched.challenge.ui.screen.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import com.youarelaunched.challenge.ui.screen.view.components.*
import com.youarelaunched.challenge.ui.theme.VendorAppTheme

const val VENDORS_SCREEN_LIST_TEST_TAG = "vendors_screen_list_test_tag"
const val VENDORS_SCREEN_SEARCH_FIELD_TEST_TAG = "vendors_screen_search_field_test_tag"
const val VENDORS_SCREEN_SEARCH_BUTTON_TEST_TAG = "vendors_screen_search_button_test_tag"

@Composable
fun VendorsRoute(
    viewModel: VendorsVM
) {
    val uiState by viewModel.uiState.collectAsState()

    VendorsScreen(
        uiCallBack = viewModel,
        uiState = uiState
    )
}

@Composable
fun VendorsScreen(
    uiCallBack: UiCallBack,
    uiState: VendorsScreenUiState
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = VendorAppTheme.colors.background,
        snackbarHost = { ChatsumerSnackbar(it) }
    ) { paddings ->
        if (!uiState.vendors.isNullOrEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .testTag(VENDORS_SCREEN_LIST_TEST_TAG)
                    .padding(paddings)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(
                    top = (56 + 24 * 2).dp,
                    bottom = 24.dp,
                    start = 16.dp,
                    end = 16.dp,
                )
            ) {
                items(uiState.vendors) { vendor ->
                    VendorItem(
                        vendor = vendor
                    )
                }

            }
        } else {
            NoVendors()
        }

        SearchBar(
            text = uiState.searchText,
            callBack = uiCallBack
        )
    }
}

interface UiCallBack: SearchBarCallBack {

    companion object EMPTY: UiCallBack {

        override fun onTextChange(value: String) {}

        override fun onSearchClicked(value: String) {}
    }
}