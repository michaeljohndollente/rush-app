package com.mjapp.rush.presentation.screen.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.mjapp.rush.core.common.DataState
import com.mjapp.rush.domain.model.Category

@Composable
fun CategoryDropdown(
    categoriesState: DataState<List<Category>>,
    onCategorySelected: (Category?) -> Unit,
    defaultText: String = "What's New"
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf<Category?>(null) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { expanded = !expanded }
    ) {
        Text(
            text = selectedCategory?.name ?: defaultText,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
        )
        Icon(Icons.Filled.ArrowDropDown, contentDescription = "View Categories")
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            onClick = {
                selectedCategory = null
                expanded = false
                onCategorySelected(null)
            }, text = { Text("What's New") }
        )
        if (categoriesState is DataState.Success) {
            val categories = categoriesState.data
            Log.d("CategoryDropdown", "Current categoriesState: $categories")

            Log.d("CategoryDropdown", "Number of fetched categories: ${categories.size}")
            Log.d("CategoryDropdown", "Fetched categories: ${categories.map { it.name ?: "null" }}")
            categories.forEach { category ->
                DropdownMenuItem(
                    onClick = {
                        selectedCategory = category
                        expanded = false
                        onCategorySelected(category)
                    },
                    text = { Text(category.name ?: "Unknown Category") }
                )
            }
        } else if (categoriesState is DataState.Error) {
            Text("Error loading categories")
        } else {
            Text("Loading categories...")
        }
    }
}