package com.example.mycity.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Category(
    val categoryId: Int,
    @DrawableRes val imageId: Int,
    @StringRes val desId: Int
)
