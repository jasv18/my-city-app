package com.example.mycity.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycity.data.model.Category
import com.example.mycity.data.model.Recommendation
import com.example.mycity.data.repository.CategoryRepository
import com.example.mycity.data.repository.RecommendationRepository
import com.example.mycity.data.source.CategoryDatasource
import com.example.mycity.data.source.RecommendationDatasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class BaseViewModel private constructor (
    private val categoryDatasource: CategoryDatasource,
    private val recommendationDatasource: RecommendationDatasource
): ViewModel() {
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState

    class Factory(
        private val categoryDatasource: CategoryDatasource,
        private val recommendationDatasource: RecommendationDatasource
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BaseViewModel::class.java)) {
                return BaseViewModel(
                    categoryDatasource, recommendationDatasource
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    init {
        loadInitialData()
        _uiState.update {
            it.copy(isShowingHomepage = true)
        }
    }

    private fun loadInitialData() {
        _uiState.update {
            val loadedCategories = loadCategories()
            val firstCategory = loadedCategories.firstOrNull()
            val loadedRecommendations = loadRecommendations(firstCategory?.categoryId)
            val firstRecommendation = loadedRecommendations.firstOrNull()
            it.copy(
                categories = loadedCategories,
                selectedCategory = firstCategory,
                isCategorySelected = firstCategory != null,
                recommendationsOfSelectedCategory = loadedRecommendations,
                selectedRecommendation = firstRecommendation,
                isShowingHomepage = false,
                isRecommendationSelected = firstRecommendation != null
            )
        }
    }

    private fun loadCategories() = CategoryRepository(categoryDatasource).getAllCategories()

    private fun loadRecommendations(categoryId: Int?)  = when(categoryId) {
        null -> emptyList()
        else -> RecommendationRepository(recommendationDatasource)
            .getRecommendationByCategoryId(categoryId)
    }

    fun resetHomeScreenStates() {
        _uiState.update {
            it.copy(
                isShowingHomepage = true
            )
        }
    }

    fun selectCategory(category: Category?) {
        val loadedRecommendations = loadRecommendations(category?.categoryId)
        val firstRecommendation = loadedRecommendations.firstOrNull()
        _uiState.update {
            it.copy(
                selectedCategory = category,
                isCategorySelected = category != null,
                recommendationsOfSelectedCategory = loadedRecommendations,
                selectedRecommendation = firstRecommendation,
                isRecommendationSelected = firstRecommendation != null
            )
        }
    }

    fun selectRecommendation(recommendation: Recommendation?) {
        _uiState.update {
            it.copy(
                selectedRecommendation = recommendation,
                isRecommendationSelected = recommendation != null,
                isShowingHomepage = false
            )
        }
    }

}