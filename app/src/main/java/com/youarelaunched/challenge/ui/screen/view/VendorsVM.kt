package com.youarelaunched.challenge.ui.screen.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.data.repository.model.Vendor
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class VendorsVM @Inject constructor(
    private val repository: VendorsRepository
) : ViewModel(), UiCallBack {

    private val searchButtonState = MutableStateFlow(value = "")
    private val searchFieldTextState = MutableStateFlow(value = "")

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private val vendorsState = merge(
        searchFieldTextState
            .filter { it.isEmpty() || it.length >= CHARS_COUNT_FOR_AUTOMATICALLY_SEARCH_START }
            .debounce(INPUT_DEBOUNCE_IN_MILLISECONDS),
        searchButtonState
    )
        .distinctUntilChanged()
        .flatMapLatest { flowOf(getVendors(it)) }
        .stateIn(listOf())

    val uiState = vendorsState
        .combine(searchFieldTextState) { vendors, searchText -> VendorsScreenUiState(
            vendors = vendors,
            searchText = searchText
        ) }
        .stateIn(initialValue = VendorsScreenUiState(vendors = null))

    @JvmOverloads
    suspend fun getVendors(companyName: String? = null): List<Vendor> {
        return repository.getVendors(companyName.takeIf { it.isNullOrEmpty().not() })
    }

    override fun onTextChange(value: String) {
        searchFieldTextState.value = value
    }

    override fun onSearchClicked(value: String) {
        searchButtonState.value = value
    }

    private fun <T> Flow<T>.stateIn(
        initialValue: T
    ): StateFlow<T> = this.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = initialValue
    )

    private companion object {
        private const val CHARS_COUNT_FOR_AUTOMATICALLY_SEARCH_START = 3
        private const val INPUT_DEBOUNCE_IN_MILLISECONDS = 500L
    }
}