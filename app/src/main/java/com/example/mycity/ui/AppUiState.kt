package com.example.mycity.ui

import com.example.mycity.data.model.Category
import com.example.mycity.data.model.Recommendation

data class AppUiState(
    val categories: List<Category> = emptyList(),
    val recommendationsOfSelectedCategory: List<Recommendation> =  emptyList(),
    val selectedCategory: Category? = null,
    val isCategorySelected: Boolean = false,
    val selectedRecommendation: Recommendation? = null,
    val isRecommendationSelected: Boolean = false,
    val isShowingHomepage: Boolean = true
)
