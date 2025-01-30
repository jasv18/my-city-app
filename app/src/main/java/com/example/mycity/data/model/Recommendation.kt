package com.example.mycity.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Recommendation(
    @DrawableRes val imageId: Int,
    @StringRes val titleId: Int,
    @StringRes val desId: Int,
    val categoryId: Int
)
