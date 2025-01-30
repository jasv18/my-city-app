package com.example.mycity.data.source

import com.example.mycity.R
import com.example.mycity.data.model.Category

class CategoryDatasource {
    val listOfCategories = listOf(
        Category(
            categoryId = 1,
            imageId = R.drawable.coffee_shop,
            desId = R.string.coffee_shops
        ),
        Category(
            categoryId = 2,
            imageId = R.drawable.restaurant,
            desId = R.string.restaurants
        ),
        Category(
            categoryId = 3,
            imageId = R.drawable.kid,
            desId = R.string.kid_places
        ),
        Category(
            categoryId = 4,
            imageId = R.drawable.park,
            desId = R.string.parks
        ),
        Category(
            categoryId = 5,
            imageId = R.drawable.shopping_center,
            desId = R.string.shopping_centers
        ),
    )
}