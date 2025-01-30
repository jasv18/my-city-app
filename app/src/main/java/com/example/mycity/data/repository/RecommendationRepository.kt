package com.example.mycity.data.repository

import com.example.mycity.data.source.RecommendationDatasource
import com.example.mycity.data.model.Recommendation

interface IRecommendationRepository {
    fun getAllRecommendations(): List<Recommendation>
    fun getRecommendationByCategoryId(categoryId: Int): List<Recommendation>
}

class RecommendationRepository(private val dataSource: RecommendationDatasource):
    IRecommendationRepository
{
    override fun getAllRecommendations(): List<Recommendation> = dataSource.listOfRecommendations

    override fun getRecommendationByCategoryId(categoryId: Int): List<Recommendation> =
        dataSource.listOfRecommendations.filter {
            it.categoryId == categoryId
        }
}