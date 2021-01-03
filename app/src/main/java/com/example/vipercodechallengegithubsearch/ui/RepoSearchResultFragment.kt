package com.example.vipercodechallengegithubsearch.ui


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.LoadState.*
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.vipercodechallengegithubsearch.R
import com.example.vipercodechallengegithubsearch.databinding.FragmentResultBinding
import com.example.vipercodechallengegithubsearch.di.RepoSearchInjection
import com.example.vipercodechallengegithubsearch.model.Repo
import com.example.vipercodechallengegithubsearch.ui.adpater.RepoSearchAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoSearchResultFragment : Fragment(),RepoSearchAdapter.Interaction {


    private val adapter = RepoSearchAdapter(this@RepoSearchResultFragment)
    private lateinit var binding: FragmentResultBinding
    private var searchJob: Job? = null
    private lateinit var viewModel : RepoSearchViewModel
    private lateinit var dataHandleListener: DataHandleListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            dataHandleListener = activity as DataHandleListener
        }catch(e: ClassCastException){
            println("$context must implement DataHandleListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(this, RepoSearchInjection.provideViewModelFactory())
            .get(RepoSearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        binding.rvResults.addItemDecoration(decoration)
        binding.rvResults.adapter =adapter

        adapter.addLoadStateListener {
            binding.progressBar.isVisible = it.source.refresh is Loading
            binding.rvResults.isVisible = it.source.refresh !is Loading
        }
    }


    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val mSearchMenuItem = menu.findItem(R.id.search_icon)
        val searchView = mSearchMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                prepareQuery(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

    }

    private fun prepareQuery(query: String?) {
        query?.trim()?.let {
            if(it.isNotEmpty()) {
                search(it)
            }
        }
    }

    private fun search(query: String) {

        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchRepo(query)?.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun onItemSelected(position: Int, item: Repo?) {
        Log.d("bercy", "onItemSelected: 1")
        dataHandleListener.passRepo(item)

    }

    interface DataHandleListener {
        fun passRepo(repo:Repo?)
    }
}