package com.example.mycity.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mycity.R
import com.example.mycity.data.model.Category
import com.example.mycity.ui.AppUiState
import com.example.mycity.ui.components.CategoryImage

@Composable
fun AppNavigationRail(
    uiState: AppUiState,
    onClick: (Category) -> Unit,
    modifier: Modifier = Modifier
){
    NavigationRail (
        modifier = modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        Column {for (category in uiState.categories) {
            NavigationRailItem(
                selected = category.categoryId == uiState.selectedCategory?.categoryId,
                onClick = { onClick(category) },
                icon = {
                    CategoryImage(category = category, size = 40.dp)
                }
            )
        }
        }
    }
}

@Composable
fun AppPermanentNavigationDrawer(
    uiState: AppUiState,
    content: @Composable () -> Unit,
    onCategoryPressed: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    PermanentNavigationDrawer(
        drawerContent = {
            PermanentDrawerSheet (
                drawerTonalElevation = 1.dp,
                modifier = Modifier
                    .width(225.dp)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
            ){
                AppNavigationDrawerHeader(modifier = Modifier
                    .padding(dimensionResource(R.dimen.small_padding))
                    .fillMaxWidth()
                )
                AppNavigationDrawerContent(
                    uiState = uiState,
                    onCategoryPressed = onCategoryPressed
                )
            }
        },
        content = content,
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun AppNavigationDrawerContent(
    uiState: AppUiState,
    onCategoryPressed: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier.padding(20.dp)
    ){
        for (category in uiState.categories) {
            NavigationDrawerItem(
                selected = category.categoryId == uiState.selectedCategory?.categoryId,
                icon = { CategoryImage(category, 35.dp) },
                label = {
                    Text(
                        text = stringResource(category.desId),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold
                    )
                },
                shape = ShapeDefaults.Medium,
                onClick = { onCategoryPressed(category) }
            )
        }
    }
}

@Composable
fun AppNavigationDrawerHeader(
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(R.string.categories_text),
        style = MaterialTheme.typography.headlineMedium,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
    HorizontalDivider()
}