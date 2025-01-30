package com.example.mycity.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mycity.data.model.Category
import com.example.mycity.data.model.Recommendation
import com.example.mycity.ui.AppUiState
import com.example.mycity.ui.utils.AppNavigationType

@Composable
fun AppHomeScreen(
    navigationType: AppNavigationType,
    appUiState: AppUiState,
    onCategoryPressed: (Category) -> Unit,
    onRecommendationPressed: (Recommendation) -> Unit,
    onDetailRecommendationBackPressed: () -> Unit = { },
    onExDetailRecommendationBackPressed: () -> Unit = { },
    modifier: Modifier = Modifier
) {
    when (navigationType) {
        AppNavigationType.PERMANENT_NAVIGATION ->
            AppExpandedNavigationContent(
                appUiState = appUiState,
                onCategoryPressed = onCategoryPressed,
                onRecommendationPressed = onRecommendationPressed,
                onBackPressed = onExDetailRecommendationBackPressed,
                modifier = modifier
                    .padding(
                        top = WindowInsets.safeDrawing.asPaddingValues()
                            .calculateTopPadding(),
                        bottom = WindowInsets.safeDrawing.asPaddingValues()
                            .calculateBottomPadding()
                    )
                    .background(color = MaterialTheme.colorScheme.inverseOnSurface)
            )
        AppNavigationType.NAVIGATION_RAIL ->
            AppMediumNavigationContent(
                appUiState = appUiState,
                onCategoryPressed = onCategoryPressed,
                onRecommendationPressed = onRecommendationPressed,
                onBack = onDetailRecommendationBackPressed,
                modifier = modifier
                    .padding(
                        top = WindowInsets.safeDrawing.asPaddingValues()
                            .calculateTopPadding(),
                        bottom = WindowInsets.safeDrawing.asPaddingValues()
                            .calculateBottomPadding()
                    )
                    .background(color = MaterialTheme.colorScheme.inverseOnSurface)
            )
        else -> {
            AppNavigationOnlyScreen(
                appUiState = appUiState,
                onCategoryPressed = onCategoryPressed,
                onRecommendationPressed = onRecommendationPressed,
                modifier = modifier
            )
        }
    }
}