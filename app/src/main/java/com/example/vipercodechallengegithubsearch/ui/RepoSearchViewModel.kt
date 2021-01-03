package com.example.vipercodechallengegithubsearch.ui


import androidx.lifecycle.*
import androidx.paging.PagingData
import com.example.vipercodechallengegithubsearch.model.Repo
import com.example.vipercodechallengegithubsearch.repository.RepoSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class RepoSearchViewModel( val repository : RepoSearchRepository) : ViewModel() {
    private var currentSearch: String? = null
    private var currentResult: Flow<PagingData<Repo>>? = null

    fun searchRepo(query: String): Flow<PagingData<Repo>>? {
        // only do search if previous text is different
        val preResult = currentResult
        var newResult : Flow<PagingData<Repo>>? = null
        preResult?.let {
            if (currentSearch==query) return preResult
        }
        currentSearch = query

        viewModelScope.launch {
            newResult = repository.getSearchResultStream(query)
            currentResult = newResult
        }
        return newResult
    }

}