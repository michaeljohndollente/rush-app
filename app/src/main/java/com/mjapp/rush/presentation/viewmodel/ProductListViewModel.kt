package com.mjapp.rush.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mjapp.rush.core.common.DataState
import com.mjapp.rush.data.model.product.ProductDataResponse
import com.mjapp.rush.data.model.product.ProductItem
import com.mjapp.rush.domain.model.Category
import com.mjapp.rush.domain.model.Product
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
import kotlin.concurrent.thread

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getProductListUseCase: GetProductListUseCase,
) : ViewModel() {

    private val _featuredProducts = MutableStateFlow<DataState<List<ProductItem>>>(DataState.Loading)
    val featuredProducts: StateFlow<DataState<List<ProductItem>>> = _featuredProducts

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
        viewModelScope.launch {
            delay(3000)
        }
        loadProducts(_currentPage.value)
    }

    private fun loadCategories() {
        viewModelScope.launch {
            Log.d("ProductListViewModel", "Loading categories...")
            getCategoriesUseCase(branchUuid)
                .onEach { dataState ->
                    _categories.value = dataState
                    if (dataState is DataState.Success) {
                        Log.d("ProductListViewModel", "Categories loaded successfully: ${dataState.data.map { it.name }}")
                    } else if (dataState is DataState.Error) {
                        Log.e("ProductListViewModel", "Error loading categories: ${dataState.message}")
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    fun loadFeaturedProducts() {
        getProductListUseCase(1, branchUuid, brandUuid) // Returns Flow<DataState<ProductDataResponse>>
            .onEach { dataState ->
                if (dataState is DataState.Success) {
                    val whatsNewProducts = dataState.data?.list?.filter { product ->
                        product.category?.name?.equals("What's New", ignoreCase = true) == true
                    }?.take(4) ?: emptyList()
                    _featuredProducts.value = DataState.Success(whatsNewProducts)
                } else if (dataState is DataState.Loading) {
                    _featuredProducts.value = DataState.Loading // Create Loading with the correct type
                } else if (dataState is DataState.Error) {
                    _featuredProducts.value = DataState.Error(dataState.message) // Create Error with the correct type
                }
            }.launchIn(viewModelScope)
    }


    fun loadMoreProducts() {
        val nextPage = _currentPage.value + 1
        if (_productList.value is DataState.Success<*> && (_productList.value as DataState.Success<ProductDataResponse>).data.next_page != null) {
            loadProducts(nextPage)
        }
    }

    private fun loadProducts(page: Int) {
        getProductListUseCase(page, branchUuid, brandUuid).onEach { dataState ->
            _productList.value = dataState
        }.launchIn(viewModelScope)
    }

    fun onProductClicked(product: Product) {
        // TODO: Implement navigation to Part 2
        println("Product clicked: ${product.name}")
    }
}