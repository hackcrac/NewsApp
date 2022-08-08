package com.regxl.news.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.regxl.news.NewsApplication
import com.regxl.news.R
import com.regxl.news.adapters.ArticleListAdapter
import com.regxl.news.databinding.FragmentSavedNewsBinding
import com.regxl.news.viewModels.NewsViewModel
import com.regxl.news.viewModels.NewsViewModelFactory
import com.regxl.news.network.Article
import com.regxl.news.repository.NewsRepository


class SavedNewsFragment : Fragment(),ArticleListAdapter.OnItemClickListener {

    private lateinit var binding: FragmentSavedNewsBinding
    private lateinit var newsAdapter:ArticleListAdapter
    private val viewModel: NewsViewModel by activityViewModels() {
        val newsRepository = NewsRepository((activity?.application as NewsApplication).dataBase)
        NewsViewModelFactory(newsRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_saved_news, container, false)
        binding.lifecycleOwner = this
        binding.newsViewModel = viewModel
        binding.recyclerView.adapter = ArticleListAdapter(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsAdapter = binding.recyclerView.adapter as ArticleListAdapter

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = newsAdapter.currentList[position]
                viewModel.deleteArticle(article)
                Snackbar.make(view, "Successfully deleted article", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.saveArticle(article)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.recyclerView)
        }
    }

    override fun onItemClick(article: Article) {
        val bundle = Bundle().apply { putSerializable("article", article) }
        findNavController().navigate(R.id.action_savedNewsFragment_to_articleFragment, bundle)
    }

}