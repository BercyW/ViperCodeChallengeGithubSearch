package com.example.vipercodechallengegithubsearch.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repo(
    @Expose
    @SerializedName("id")
    val id: Long,

    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("full_name")
    val fullName: String,

    @Expose
    @field:SerializedName("description")
    val description: String?,

    @Expose
    @field:SerializedName("forks_count")
    val forks: Int,

    @Expose
    @field:SerializedName("stargazers_count")
    val stars: Int,

    @Expose
    @field:SerializedName("html_url")
    val url: String,
) : Parcelable