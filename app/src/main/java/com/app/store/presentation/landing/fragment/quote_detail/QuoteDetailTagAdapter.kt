package com.app.store.presentation.landing.fragment.quote_detail

import android.view.View
import android.view.ViewGroup
import com.app.store.R
import com.app.store.shared.view.adapter.BaseRecyclerViewAdapter
import com.app.store.shared.view.adapter.BaseVHolder
import kotlinx.android.synthetic.main.view_tag_item.view.*

class QuoteDetailTagAdapter(
    private val selectedListener: (String) -> Unit
) : BaseRecyclerViewAdapter<String>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVHolder<String> {
        val viewHolder = QuoteTagViewHolder(
            getView(
                parent,
                QuoteTagViewHolder.layout
            )
        )

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            selectedListener.invoke(getItem(position))
        }

        return viewHolder
    }

    class QuoteTagViewHolder(itemView: View) : BaseVHolder<String>(itemView) {

        companion object {
            const val layout = R.layout.view_tag_item
        }

        override fun binding(content: String) {
            with(itemView) { text_tag_detail.text = content }
        }

    }

}