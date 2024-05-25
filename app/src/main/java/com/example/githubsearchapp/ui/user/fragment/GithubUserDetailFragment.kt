package com.example.githubsearchapp.ui.user.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.domain.model.GithubUserDetailData
import com.example.githubsearchapp.databinding.FragmentGithubUserDetailBinding
import com.example.githubsearchapp.ui.user.UserViewModel

class GithubUserDetailFragment : Fragment() {

    private val viewModel: UserViewModel by viewModels()
    private var _binding: FragmentGithubUserDetailBinding? = null
    private val binding get() = _binding!!

    private val args: GithubUserDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGithubUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = args.username

        if (username != null) {
            viewModel.fetchUserDetailCache(username)
        }
        viewModel.githubUserDetail.observe(viewLifecycleOwner) {
            it?.let { (it) }
        }

        viewModel.githubUserDetail.observe(viewLifecycleOwner) {
            it?.let { setUserDetail(it) }
        }
    }

    private fun setUserDetail(user: GithubUserDetailData) {
        Glide.with(binding.ivAvatar.context)
            .load(user.avatarUrl)
            .into(binding.ivAvatar)

        binding.apply {
            tvUsername.text = user.login
            tvBio.text = user.bio
            tvBlog.text = user.blog
            tvLocation.text = user.location
            tvName.text = user.name
            tvCompany.text = user.company
            tvTwitterUsername.text = StringBuilder("@${user.twitterUsername} on twitter")
            tvFollowers.text = StringBuilder("${user.followers} followers")
            tvFollowing.text = StringBuilder("${user.following} following")
            tvPublicGists.text = StringBuilder("${user.publicGists} public gists")
            tvPublicRepo.text = StringBuilder("${user.publicRepos} public repos")

            if (user.company?.isBlank() == true)
                tvCompany.visibility = View.GONE

            if (user.location?.isBlank() == true)
                tvLocation.visibility = View.GONE

            if (user.bio?.isBlank() == true)
                tvBio.visibility = View.GONE

            if (user.name?.isBlank() == true)
                tvName.visibility = View.GONE

            if (user.twitterUsername == null)
                tvTwitterUsername.visibility = View.GONE

            if (user.blog?.isBlank() == true)
                tvBlog.visibility = View.GONE
        }
    }
}