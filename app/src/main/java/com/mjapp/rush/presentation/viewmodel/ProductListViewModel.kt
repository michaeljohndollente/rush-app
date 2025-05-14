package com.mjapp.rush.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mjapp.rush.core.common.DataState
import com.mjapp.rush.data.model.product.ProductDataResponse
import com.mjapp.rush.domain.model.Category
import com.mjapp.rush.domain.usecase.GetCategoriesUseCase
import com.mjapp.rush.domain.usecase.GetProductListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getProductListUseCase: GetProductListUseCase,
) : ViewModel() {

    private val _categories = MutableStateFlow<DataState<List<Category>>>(DataState.Loading)
    val categories: StateFlow<DataState<List<Category>>> = _categories

    private val _productList = MutableStateFlow<DataState<ProductDataResponse>>(DataState.Loading)
    val productList: StateFlow<DataState<ProductDataResponse>> = _productList

    private val _currentPage = mutableStateOf(1)
    val currentPage: State<Int> = _currentPage

    private val branchUuid = "f1c0ded3-ff8b-435e-b5b3-bd17cba34a51"
    private val brandUuid = "ef89d985-2e2a-4eb8-8553-84867c9affe7"

    init {
        loadCategories()
        loadProducts(currentPage.value)
    }

    private fun loadCategories() {
        viewModelScope.launch {
            val categories = getCategoriesUseCase.invoke(branchUuid)
            categories.onEach { dataState ->
                _categories.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private fun loadProducts(page: Int) {
        getProductListUseCase(page, branchUuid, brandUuid).onEach { dataState ->
            _productList.value = dataState
        }.launchIn(viewModelScope)
    }
}
