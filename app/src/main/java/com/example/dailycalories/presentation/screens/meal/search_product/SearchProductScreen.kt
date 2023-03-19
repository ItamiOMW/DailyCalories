package com.example.dailycalories.presentation.screens.meal.search_product

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dailycalories.R
import com.example.dailycalories.domain.model.meal.MealFoodProduct
import com.example.dailycalories.presentation.components.AddProductDialog
import com.example.dailycalories.presentation.components.ProductInfoCard
import com.example.dailycalories.utils.EMPTY_STRING


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchProductScreen(
    viewModel: SearchProductViewModel = hiltViewModel(),
    onConfirm: (MealFoodProduct) -> Unit,
    onNavigateBack: () -> Unit,
) {

    val state = viewModel.state

    if (state.showAddProductDialog) {
        state.selectedProduct?.let {
            AddProductDialog(
                productInfo = it,
                onConfirm = { mealFoodProduct ->
                    viewModel.onEvent(SearchProductEvent.HideAddProductDialog)
                    onConfirm(mealFoodProduct)
                },
                onDismiss = {
                    viewModel.onEvent(SearchProductEvent.HideAddProductDialog)
                }
            )
        }
    }

    Scaffold(
        topBar = {
            HeaderSection(
                onNavigateBack = onNavigateBack,
                query = state.query,
                onQueryChanged = { query ->
                    viewModel.onEvent(SearchProductEvent.ChangeQuery(query))
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LazyColumn(
                modifier = Modifier.padding(it),
                contentPadding = PaddingValues(
                    start = 10.dp,
                    end = 10.dp,
                    top = 20.dp,
                    bottom = 10.dp
                ),
                verticalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                items(state.products) { product ->
                    ProductInfoCard(
                        modifier = Modifier
                            .animateItemPlacement(tween(300)),
                        product = product,
                        onProductClicked = {
                            viewModel.onEvent(
                                SearchProductEvent.ShowAddProductDialog(product)
                            )
                        }
                    )
                }
            }
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

    }

}

@Composable
fun HeaderSection(
    onNavigateBack: () -> Unit,
    query: String,
    onQueryChanged: (query: String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colors.secondary,
                shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            IconButton(
                onClick = {
                    onNavigateBack()
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowLeft,
                    contentDescription = stringResource(id = R.string.desc_navigate_to_previous),
                    tint = MaterialTheme.colors.onSecondary
                )
            }
            Text(
                text = stringResource(R.string.title_search_products),
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onSecondary,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.onSecondary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.onSecondary
            ),
            value = query,
            onValueChange = { newQuery ->
                onQueryChanged(newQuery)
            },
            shape = RoundedCornerShape(15.dp),
            singleLine = true,
            placeholder = {
                Text(
                    text = stringResource(R.string.text_find_food),
                    color = MaterialTheme.colors.onSecondary.copy(alpha = 0.5f)
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = stringResource(R.string.desc_clean_textfield),
                    tint = MaterialTheme.colors.onSecondary,
                )
            },
            trailingIcon = {
                IconButton(onClick = {
                    onQueryChanged(EMPTY_STRING)
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = stringResource(R.string.desc_find_product),
                        tint = MaterialTheme.colors.onSecondary,
                    )
                }

            }
        )
        Spacer(modifier = Modifier.height(15.dp))
    }
}