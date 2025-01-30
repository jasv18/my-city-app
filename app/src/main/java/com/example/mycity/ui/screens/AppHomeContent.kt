package com.example.mycity.ui.screens

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycity.R
import com.example.mycity.data.model.Category
import com.example.mycity.data.model.Recommendation
import com.example.mycity.ui.AppUiState
import com.example.mycity.ui.components.AppBar
import com.example.mycity.ui.components.CategoryItem
import com.example.mycity.ui.components.RecommendationItem

@Composable
fun AppNavigationOnlyScreen(
    appUiState: AppUiState,
    onCategoryPressed: (Category) -> Unit,
    onRecommendationPressed: (Recommendation) -> Unit,
    modifier: Modifier = Modifier
){
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    Scaffold (
        topBar = {
            val context = LocalContext.current
            val currentScreen = remember (
                backStackEntry?.destination?.route, appUiState.selectedCategory
            ) {
                getCurrentScreen(
                    context = context,
                    category = appUiState.selectedCategory,
                    route = backStackEntry?.destination?.route
                )
            }
            AppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            AppNavigationContent(
                navController = navController,
                appUiState = appUiState,
                onCategoryPressed = onCategoryPressed,
                onRecommendationPressed = onRecommendationPressed,
                modifier = modifier
            )
        }
    }
}

@Composable
fun AppNavigationContent(
    navController: NavHostController,
    appUiState: AppUiState,
    onCategoryPressed: (Category) -> Unit,
    onRecommendationPressed: (Recommendation) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        startDestination = NavigationDestinations.CATEGORIES.name,
        navController = navController,
        modifier = modifier
    ) {
        composable(route = NavigationDestinations.CATEGORIES.name) {
            CategoryList(
                categories = appUiState.categories,
                onClick = {
                    onCategoryPressed(it)
                    navController.navigate(route = NavigationDestinations.RECOMMENDATIONS.name)
                },
            )
        }
        composable(route = NavigationDestinations.RECOMMENDATIONS.name) {
            RecommendationList(
                uiState = appUiState,
                onClick = {
                    onRecommendationPressed(it)
                    navController.navigate(route = NavigationDestinations.RECOMMENDATION_DETAIL.name)
                },
            )
        }
        composable(route = NavigationDestinations.RECOMMENDATION_DETAIL.name) {
            RecommendationDetail(
                recommendation = appUiState.selectedRecommendation!!,
                textStyle = MaterialTheme.typography.bodyMedium,
                onBackPressed = { navController.navigateUp() }
            )
        }
    }
}

@Composable
fun AppMediumNavigationContent(
    appUiState: AppUiState,
    onCategoryPressed: (Category) -> Unit,
    onRecommendationPressed: (Recommendation) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Row {
            if (appUiState.isShowingHomepage) {
                AppNavigationRail(
                    uiState = appUiState,
                    onClick = onCategoryPressed,
                )
                RecommendationList(
                    uiState = appUiState,
                    onClick = onRecommendationPressed,
                )
            } else {
                RecommendationDetail(
                    recommendation = appUiState.selectedRecommendation!!,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    onBackPressed = onBack,
                    isFullScreen = true,
                    modifier = Modifier,
                )
            }
        }
    }
}

@Composable
fun AppExpandedNavigationContent(
    appUiState: AppUiState,
    onCategoryPressed: (Category) -> Unit,
    onRecommendationPressed: (Recommendation) -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box (modifier = modifier) {
        AppPermanentNavigationDrawer(
            uiState = appUiState,
            onCategoryPressed = onCategoryPressed,
            content = {
                Row {
                    RecommendationList(
                        uiState = appUiState,
                        onClick = onRecommendationPressed,
                        showSelection = true,
                        modifier = Modifier.weight(1.5f)
                    )
                    RecommendationDetail(
                        recommendation = appUiState.selectedRecommendation!!,
                        onBackPressed = onBackPressed,
                        isFullScreen = false,
                        textStyle = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        )
    }
}

@Composable
fun CategoryList(
    categories: List<Category>,
    onClick: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn (modifier = modifier) {
        items(items = categories, key = { it.categoryId }) {
            CategoryItem(
                category = it,
                onClick = { onClick(it) }
            )
        }
    }
}

@Composable
fun RecommendationList(
    uiState: AppUiState,
    onClick: (Recommendation) -> Unit,
    showSelection: Boolean = false,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        LazyColumn (
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding)),
            state = LazyListState(),
            contentPadding = PaddingValues(dimensionResource(R.dimen.medium_padding)),
        ) {
            items(items = uiState.recommendationsOfSelectedCategory) {
                RecommendationItem(
                    recommendation = it,
                    onClick = { onClick(it) },
                    selected = showSelection && it == uiState.selectedRecommendation
                )
            }
        }
    }
}

@Composable
fun RecommendationDetail(
    recommendation: Recommendation,
    textStyle: TextStyle,
    onBackPressed: () -> Unit,
    isFullScreen: Boolean = false,
    modifier: Modifier = Modifier
) {
    BackHandler { onBackPressed() }
    Box (modifier = modifier.fillMaxSize()) {
        Column (
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(R.dimen.medium_padding))
            ) {
                if (isFullScreen) {
                    IconButton(
                        onClick = { onBackPressed() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                }
                Text(
                    text = stringResource(recommendation.titleId),
                    style = textStyle.copy(fontSize = 24.sp, fontWeight = FontWeight.Medium, fontStyle = FontStyle.Italic),
                    modifier = Modifier.padding(dimensionResource(R.dimen.small_padding))
                )
            }
            Row (modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.medium_padding))
            ) {
                Column (
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding)),
                ) {
                    Image(
                        painter = painterResource(recommendation.imageId),
                        contentDescription = stringResource(recommendation.titleId),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(dimensionResource(R.dimen.small_rounded_corner)))
                    )
                    Text(
                        text = stringResource(recommendation.desId),
                        style = textStyle
                    )
                }
            }
        }
    }
}

enum class NavigationDestinations {
    CATEGORIES, RECOMMENDATIONS, RECOMMENDATION_DETAIL
}

private fun getCurrentScreen(
    context: Context,
    category: Category? = null,
    route: String?
): String = when (route) {
    null, NavigationDestinations.CATEGORIES.name -> context.getString(R.string.state_name)
    NavigationDestinations.RECOMMENDATION_DETAIL.name -> context.getString(
        R.string.recommended_place
    )
    NavigationDestinations.RECOMMENDATIONS.name -> category?.let {
        context.getString(it.desId)
    } ?: context.getString(R.string.recommendations_text)
    else -> context.getString(R.string.state_name)
}