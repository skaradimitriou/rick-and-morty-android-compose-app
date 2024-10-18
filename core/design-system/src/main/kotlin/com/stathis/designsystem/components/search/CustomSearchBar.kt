package com.stathis.designsystem.components.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults.InputField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier,
    hint: String,
    suggestions: List<String>,
    onQuerySubmitted: (String) -> Unit,
    onSuggestionClick: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    SearchBar(
        modifier = modifier,
        inputField = {
            InputField(
                query = query,
                onQueryChange = { query = it },
                onSearch = {
                    onQuerySubmitted(query)
                    expanded = false
                },
                expanded = expanded,
                onExpandedChange = { expanded = it },
                placeholder = { Text(hint) },
                leadingIcon = {
                    if (expanded) {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
                        }
                    } else {
                        Icon(Icons.Default.Search, contentDescription = null)
                    }
                }
            )
        },
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        QueryList(
            suggestions = suggestions,
            onSuggestionClick = {
                expanded = !expanded
                query = it
                onSuggestionClick(it)
            }
        )
    }
}

@Composable
internal fun QueryList(
    suggestions: List<String>,
    onSuggestionClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        items(suggestions) { item ->
            Text(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .clickable { onSuggestionClick(item) },
                text = item
            )
        }
    }
}

@Preview
@Composable
internal fun CustomSearchBarPreview() {
    CustomSearchBar(
        modifier = Modifier.fillMaxWidth(),
        hint = "My hint here",
        suggestions = listOf("Query 1"),
        onQuerySubmitted = {},
        onSuggestionClick = {}
    )
}

@Preview(showBackground = true)
@Composable
internal fun QueryListPreview() {
    QueryList(
        suggestions = listOf("Query 1", "Query 2", "Query 3"),
        onSuggestionClick = {}
    )
}
