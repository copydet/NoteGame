package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BaseScreenshotsResponse<T>(
	@SerializedName("results")
	val results: List<T>?
)
