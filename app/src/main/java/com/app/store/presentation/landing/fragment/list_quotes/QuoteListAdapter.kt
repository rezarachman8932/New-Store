package com.app.store.presentation.landing.fragment.list_quotes

import android.view.View
import android.view.ViewGroup
import com.app.store.R
import com.app.store.data.model.QuoteOfTheDayDetail
import com.app.store.shared.view.adapter.BaseRecyclerViewAdapter
import com.app.store.shared.view.adapter.BaseVHolder
import kotlinx.android.synthetic.main.view_quote_item.view.*

class QuoteListAdapter(private val selectedListener: (QuoteOfTheDayDetail) -> Unit) : BaseRecyclerViewAdapter<QuoteOfTheDayDetail>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVHolder<QuoteOfTheDayDetail> {
        val viewHolder = QuoteListViewHolder(
            getView(
                parent,
                QuoteListViewHolder.layout
            )
        )

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            selectedListener.invoke(getItem(position))
        }

        return viewHolder
    }

    class QuoteListViewHolder(itemView: View) : BaseVHolder<QuoteOfTheDayDetail>(itemView) {

        companion object {
            const val layout = R.layout.view_quote_item
        }

        override fun binding(content: QuoteOfTheDayDetail) {
            with(itemView) {
                text_author.text = content.author
                text_body.text = content.body

                var tags = ""

                content.tags.forEach {
                    tags = "$it $tags"
                }

                text_tags.text = tags
            }
        }

    }
}