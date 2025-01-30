package com.example.mycity.data.source

import com.example.mycity.R
import com.example.mycity.data.model.Recommendation

class RecommendationDatasource {
    val listOfRecommendations = listOf(
        /* -------------- Coffee Shops -----------------*/
        Recommendation(
            imageId = R.drawable.coffee_shops_1,
            titleId = R.string.coffe_shop_title_1,
            desId = R.string.coffe_shop_detail_1,
            categoryId = 1
        ),
        Recommendation(
            imageId = R.drawable.coffee_shops_2,
            titleId = R.string.coffe_shop_title_2,
            desId = R.string.coffe_shop_detail_2,
            categoryId = 1
        ),
        Recommendation(
            imageId = R.drawable.coffee_shops_3,
            titleId = R.string.coffe_shop_title_3,
            desId = R.string.coffe_shop_detail_3,
            categoryId = 1
        ),
        /* -------------- Restaurants -----------------*/
        Recommendation(
            imageId = R.drawable.restaurant_1,
            titleId = R.string.restaurant_title_1,
            desId = R.string.restaurant_detail_1,
            categoryId = 2
        ),
        Recommendation(
            imageId = R.drawable.restaurant_2,
            titleId = R.string.restaurant_title_2,
            desId = R.string.restaurant_detail_2,
            categoryId = 2
        ),
        Recommendation(
            imageId = R.drawable.restaurant_3,
            titleId = R.string.restaurant_title_3,
            desId = R.string.restaurant_detail_3,
            categoryId = 2
        ),
        /* ---------- Kid-Friendly Places -------------*/
        Recommendation(
            imageId = R.drawable.kid_place_1,
            titleId = R.string.kid_friendly_place_title_1,
            desId = R.string.kid_friendly_place_detail_1,
            categoryId = 3
        ),
        Recommendation(
            imageId = R.drawable.kid_place_2,
            titleId = R.string.kid_friendly_place_title_2,
            desId = R.string.kid_friendly_place_detail_2,
            categoryId = 3
        ),
        Recommendation(
            imageId = R.drawable.kid_place_3,
            titleId = R.string.kid_friendly_place_title_3,
            desId = R.string.kid_friendly_place_detail_3,
            categoryId = 3
        ),
        /* -------------- Parks -----------------*/
        Recommendation(
            imageId = R.drawable.park_1,
            titleId = R.string.park_title_1,
            desId = R.string.park_detail_1,
            categoryId = 4
        ),
        Recommendation(
            imageId = R.drawable.park_2,
            titleId = R.string.park_title_2,
            desId = R.string.park_detail_2,
            categoryId = 4
        ),
        Recommendation(
            imageId = R.drawable.park_3,
            titleId = R.string.park_title_3,
            desId = R.string.park_detail_3,
            categoryId = 4
        ),
        /* ---------- Shopping Centers -------------*/
        Recommendation(
            imageId = R.drawable.shopping_center_1,
            titleId = R.string.shopping_center_title_1,
            desId = R.string.shopping_center_detail_1,
            categoryId = 5
        ),
        Recommendation(
            imageId = R.drawable.shopping_center_2,
            titleId = R.string.shopping_center_title_2,
            desId = R.string.shopping_center_detail_2,
            categoryId = 5
        ),
        Recommendation(
            imageId = R.drawable.shopping_center_3,
            titleId = R.string.shopping_center_title_3,
            desId = R.string.shopping_center_detail_3,
            categoryId = 5
        ),
    )
}