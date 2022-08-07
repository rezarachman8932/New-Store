package com.app.store.presentation.landing.fragment.list_quotes.keyword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.store.R
import com.app.store.data.model.QuoteListResponse
import com.app.store.presentation.landing.fragment.list_quotes.QuoteListAdapter
import kotlinx.android.synthetic.main.frag_quote_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuoteListFragment : Fragment() {

    private val layoutId: Int = R.layout.frag_quote_list
    private val viewModel: QuoteListViewModel by viewModel()

    private lateinit var adapter: QuoteListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = QuoteListAdapter {
            activity?.let { activity ->
                val navController: NavController = Navigation.findNavController(activity, R.id.nav_host_fragment)
                val bundle = Bundle()
                bundle.putString("quoteId", it.id.toString())
                navController.navigate(R.id.action_quoteListFragment_to_quoteDetailFragment, bundle)
            }
        }

        val layoutManagerList = LinearLayoutManager(context)
        rv_quote_list.layoutManager = layoutManagerList
        rv_quote_list.adapter = adapter
        rv_quote_list.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        button_search_quote.setOnClickListener { searchQuote() }

        viewModel.quoteList.observe(viewLifecycleOwner) { constructList(it) }
        viewModel.getQuoteListKeyword(null)
    }

    private fun searchQuote() {
        val keyword = edit_text_search_quote.text.toString()
        viewModel.getQuoteListKeyword(keyword)
    }

    private fun constructList(result: QuoteListResponse) {
        if (viewModel.quotes.size > 0) {
            viewModel.quotes.clear()
        }
        viewModel.quotes.addAll(result.quotes)
        adapter.setItem(viewModel.quotes)
    }

}