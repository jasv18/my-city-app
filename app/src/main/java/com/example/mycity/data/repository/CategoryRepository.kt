package com.example.mycity.data.repository

import com.example.mycity.data.source.CategoryDatasource
import com.example.mycity.data.model.Category

interface ICategoryRepository {
    fun getAllCategories(): List<Category>
}

class CategoryRepository(private val dataSource: CategoryDatasource): ICategoryRepository {
    override fun getAllCategories(): List<Category> = dataSource.listOfCategories
}