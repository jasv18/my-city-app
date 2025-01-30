package com.example.mycity.ui.screens

import android.app.Activity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycity.data.source.CategoryDatasource
import com.example.mycity.data.source.RecommendationDatasource
import com.example.mycity.ui.BaseViewModel
import com.example.mycity.ui.utils.AppNavigationType

@Composable
fun App(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val categoryDatasource = CategoryDatasource()
    val recommendationDatasource = RecommendationDatasource()
    val baseViewModel: BaseViewModel = viewModel(
        factory = BaseViewModel.Factory(
            categoryDatasource = categoryDatasource,
            recommendationDatasource = recommendationDatasource
        )

    )
    val appUiState = baseViewModel.uiState.collectAsState().value
    val navigationType = when(windowSize) {
        WindowWidthSizeClass.Expanded -> AppNavigationType.PERMANENT_NAVIGATION
        WindowWidthSizeClass.Medium -> AppNavigationType.NAVIGATION_RAIL
        else -> AppNavigationType.ONLY_NAVIGATION
    }
    AppHomeScreen(
        navigationType = navigationType,
        appUiState = appUiState,
        onCategoryPressed = { baseViewModel.selectCategory(it) },
        onRecommendationPressed = { baseViewModel.selectRecommendation(it) },
        onDetailRecommendationBackPressed = { baseViewModel.resetHomeScreenStates() },
        onExDetailRecommendationBackPressed = { (LocalContext as Activity).finish() },
        modifier = modifier
    )
}