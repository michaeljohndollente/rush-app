package com.mjapp.rush.presentation.screen.home

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.mjapp.rush.core.common.DataState
import com.mjapp.rush.domain.model.category.CategoryItem

@Composable
fun CategoryDropdown(
    categoriesState: DataState<List<CategoryItem>>,
    onCategorySelected: (CategoryItem?) -> Unit,
) {
    val defaultText = "What's New"
    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf<CategoryItem?>(null) }
    Row(
        modifier = Modifier
            .clickable {
                expanded = !expanded
            }

    ) {
        Text(
            text = selectedCategory?.name ?: defaultText,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Icon(Icons.Filled.ArrowDropDown, contentDescription = "View Categories")
    }
    DropdownMenu(
        offset = DpOffset(10.dp, 0.dp),
        expanded = expanded,
        onDismissRequest = { expanded = false },
    ) {
        DropdownMenuItem(
            onClick = {
                selectedCategory = null
                expanded = false
                onCategorySelected(null)
            },
            text = { Text("What's New") },
        )
        if (categoriesState is DataState.Success) {
            val categories = categoriesState.data
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

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = selectedCategory?.name ?: defaultText,
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}
