package com.app.store.presentation.landing.fragment.list_quotes.tag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.store.R
import com.app.store.data.model.QuoteListResponse
import com.app.store.presentation.landing.fragment.list_quotes.QuoteListAdapter
import kotlinx.android.synthetic.main.frag_quote_list_search_tag.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuoteListTagSearchFragment : Fragment() {

    private val layoutId: Int = R.layout.frag_quote_list_search_tag
    private val viewModel: QuoteListTagSearchViewModel by viewModel()

    private lateinit var param: String

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

        if (arguments != null) {
            param = QuoteListTagSearchFragmentArgs.fromBundle(requireArguments()).tag
        }

        adapter = QuoteListAdapter {}

        val layoutManagerList = LinearLayoutManager(context)
        rv_quote_list_search_tag.layoutManager = layoutManagerList
        rv_quote_list_search_tag.adapter = adapter
        rv_quote_list_search_tag.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        viewModel.quoteTagSearchList.observe(viewLifecycleOwner) { constructList(it) }
        viewModel.getQuoteTagSearchList(param)
    }

    private fun constructList(result: QuoteListResponse) {
        if (result.quotes.size > 0) {
            adapter.setItem(result.quotes)
        }
    }

}