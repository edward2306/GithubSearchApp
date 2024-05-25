package com.example.githubsearchapp.ui.user.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubsearchapp.R
import com.example.githubsearchapp.databinding.FragmentSearchUserBinding
import com.example.githubsearchapp.navigation.navigate
import com.example.githubsearchapp.ui.user.UserViewModel
import com.example.githubsearchapp.ui.user.adapter.GithubUserListAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchGithubUserFragment : Fragment(), SearchView.OnQueryTextListener, GithubUserListAdapter.OnItemClickListener {

    private val githubUserViewModel: UserViewModel by viewModel()
    private var _binding: FragmentSearchUserBinding? = null
    private val binding get() = _binding!!
    private val userListAdapter: GithubUserListAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSearchView()
        setAdapter()
        userListAdapter.setListener(this)
    }

    private fun setSearchView() {
        binding.svUsername.setOnQueryTextListener(this)
        binding.svUsername.onActionViewExpanded()
        binding.svUsername.isIconifiedByDefault = true
        binding.svUsername.isFocusable = true
        binding.svUsername.isIconified = false
        binding.svUsername.requestFocusFromTouch()
        binding.svUsername.queryHint = resources.getString(R.string.text_username)
    }

    private fun setAdapter() {
        binding.rvUser.apply {
            adapter = userListAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            visibility = View.VISIBLE
        }
    }

    override fun onQueryTextSubmit(textSearch: String?): Boolean {
        textSearch?.let {
            githubUserViewModel.userSearch(textSearch, 1, 50)
            githubUserViewModel.githubUserSearchList.observe(viewLifecycleOwner) { searchResult ->
                searchResult?.let {
                    if (it.totalCount > 0) {
                        binding.tvError.visibility = View.GONE
                        userListAdapter.submitData(it.items)
                    } else {
                        binding.tvError.visibility = View.VISIBLE
                        binding.rvUser.visibility = View.GONE
                    }
                }
            }
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun onItemClicked(username: String) {
        navigate(SearchGithubUserFragmentDirections.actionSearchGithubUserFragmentToGithubUserDetailFragment(username))
    }
}