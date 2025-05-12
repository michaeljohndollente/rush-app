package com.mjapp.rush.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mjapp.rush.data.model.entities.Category
import com.mjapp.rush.data.model.entities.Product
import com.mjapp.rush.domain.usecase.GetCachedCategoriesUseCase
import com.mjapp.rush.domain.usecase.GetCachedProductListUseCase
import com.mjapp.rush.domain.usecase.GetCategoriesUseCase
import com.mjapp.rush.domain.usecase.GetProductListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductListViewModel(
    val getCategoriesUseCase: GetCategoriesUseCase,
    val getProductListUseCase: GetProductListUseCase,
    val getCachedCategoriesUseCase: GetCachedCategoriesUseCase,
    val getCachedProductListUseCase: GetCachedProductListUseCase
) : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories.asStateFlow()

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    var currentPage = 1
    var maxPage: Int? = null
    var isLoadMoreLoading = false

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _isLoading.value = true
            // Load categories (can be done in parallel if needed)
            getCategoriesUseCase()
                .onSuccess { _categories.value = it }
                .onFailure { _errorMessage.value = it.message }

            // Load initial product list
            getProductList(1)
            _isLoading.value = false
        }
    }

    private fun getProductList(page: Int) {
        viewModelScope.launch {
            getProductListUseCase(page)
                .onSuccess { (newProducts, newMaxPage) ->
                    if (page == 1) {
                        _products.value = newProducts
                    } else {
                        _products.value = _products.value + newProducts
                    }
                    maxPage = newMaxPage
                }
                .onFailure { _errorMessage.value = it.message }
        }
    }

    fun loadMoreProducts() {
        if (!isLoadMoreLoading && (maxPage == null || currentPage < maxPage!!)) {
            isLoadMoreLoading = true
            currentPage++
            viewModelScope.launch {
                getProductListUseCase(currentPage)
                    .onSuccess { (newProducts, newMaxPage) ->
                        _products.value = _products.value + newProducts
                        maxPage = newMaxPage
                    }
                    .onFailure { _errorMessage.value = it.message }
                isLoadMoreLoading = false
            }
        }
    }

    // Function to get cached data if needed (e.g., on app restart)
    suspend fun loadCachedData() {
        _categories.value = getCachedCategoriesUseCase()
        _products.value = getCachedProductListUseCase()
    }
}