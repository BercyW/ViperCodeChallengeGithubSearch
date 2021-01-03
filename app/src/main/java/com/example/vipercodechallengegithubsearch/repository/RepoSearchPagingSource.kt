package com.example.vipercodechallengegithubsearch.repository

import androidx.paging.PagingSource
import com.example.vipercodechallengegithubsearch.api.RepoSearchService
import com.example.vipercodechallengegithubsearch.model.Repo
import java.io.IOException


/**
 * for paging 3.0
 */
class RepoSearchPagingSource(val service : RepoSearchService, val query : String) : PagingSource<Int, Repo>() {

    /**
     * create page result
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        return try {
            val position = params.key ?: 1
            val q = query + "in:name,description"  // for search based on name and desc
            val response = service.searchRepos(q, position, params.loadSize)
            val repos = response.items
            val previewKey = if (position ==1) null else position - 1
            val nextKey = if (repos.isEmpty()) null else position + 1
            LoadResult.Page(repos, previewKey, nextKey)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}