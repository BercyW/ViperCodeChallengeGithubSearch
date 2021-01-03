package com.example.vipercodechallengegithubsearch.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.vipercodechallengegithubsearch.api.RepoSearchService
import com.example.vipercodechallengegithubsearch.model.Repo
import kotlinx.coroutines.flow.Flow

class RepoSearchRepository(val apiService: RepoSearchService) {

    /**
     * send the result to view model
     */
    fun getSearchResultStream(query: String): Flow<PagingData<Repo>> {
        return Pager(PagingConfig(30), pagingSourceFactory = { RepoSearchPagingSource(apiService, query) }).flow
    }

}