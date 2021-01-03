package com.example.vipercodechallengegithubsearch.api

import com.example.vipercodechallengegithubsearch.model.Repo
import com.google.gson.annotations.SerializedName

data class RepoSearchResponse(
    @SerializedName("total_count") val total: Int = 0,
    @SerializedName("incomplete_results") val incomplete_results : String = "false",
    @SerializedName("items") val items: List<Repo> = emptyList()
)