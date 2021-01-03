package com.example.vipercodechallengegithubsearch.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vipercodechallengegithubsearch.api.RepoSearchService
import com.example.vipercodechallengegithubsearch.api.RetrofitBuilder
import com.example.vipercodechallengegithubsearch.repository.RepoSearchRepository
import com.example.vipercodechallengegithubsearch.ui.RepoSearchViewModel

object RepoSearchInjection {
    private fun provideGithubRepository(): RepoSearchRepository {
        return RepoSearchRepository(RetrofitBuilder.apiService)
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(provideGithubRepository())
    }

    class ViewModelFactory(private val repository: RepoSearchRepository) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RepoSearchViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return RepoSearchViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
