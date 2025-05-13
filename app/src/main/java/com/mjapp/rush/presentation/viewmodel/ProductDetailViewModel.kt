package com.mjapp.rush.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mjapp.rush.data.model.product.ProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val serializedProduct: String? = savedStateHandle.get<String>("product")
    private val _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState: StateFlow<ProductDetailUiState> = _uiState

    init {
        serializedProduct?.let { json ->
            try {
                val product = Json.decodeFromString<ProductItem>(json)
                _uiState.update { it.copy(name = product, variations = product.toDrinkVariations()) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Failed to parse product details.") }
            }
        } ?: run {
            _uiState.update { it.copy(error = "Product data is missing.") }
        }
    }

    private val _selectedVariations = MutableStateFlow<Map<String, String>>(emptyMap())
    val selectedVariations: StateFlow<Map<String, String>> = _selectedVariations

    fun selectVariation(groupName: String, optionName: String) {
        _selectedVariations.update {
            it.toMutableMap().apply { this[groupName] = optionName }
        }
    }

    private fun ProductItem.toDrinkVariations(): List<String> {
        return when (this.category?.name?.lowercase()) {
            "drinks", "shake", "frappe" -> listOf(

            )
            else -> emptyList()
        }
    }
}

data class ProductDetailUiState(
    val product: ProductItem? = null,
    val selectedVariations: Map<String, String> = emptyMap(), // GroupName -> SelectedOptionName
    val error: String? = null
)
