package com.app.store.shared.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseVHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun binding(T: T)
}

abstract class BaseRecyclerViewAdapter<T> : RecyclerView.Adapter<BaseVHolder<T>>(), BaseRecyclerViewInterface<T> {

    private var items = mutableListOf<T>()

    fun getView(parent: ViewGroup, layoutId: Int): View = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

    fun getInflater(parent: ViewGroup): LayoutInflater = LayoutInflater.from(parent.context)

    override fun onBindViewHolder(holder: BaseVHolder<T>, position: Int) {
        holder.binding(items[position])
    }

    override fun getItemCount(): Int = items.size

    override fun setItem(value: MutableList<T>) {
        clearItem()
        items.addAll(value)
        notifyDataSetChanged()
    }

    override fun addItem(value: MutableList<T>) {
        addOrUpdate(value)
        notifyDataSetChanged()
    }

    override fun addItem(value: T) {
        addOrUpdate(value)
        notifyItemChanged(items.lastIndexOf(value))
    }

    override fun clearItem() {
        items.clear()
    }

    override fun getItem(): MutableList<T> = items

    override fun getItem(position: Int): T = items[position]

    private fun addOrUpdate(value: MutableList<T>) {
        value.forEach { addOrUpdate(it) }
    }

    private fun addOrUpdate(value: T) {
        val index = findPosition(value)
        if (index == -1) items.add(value)
        else items[index] = value
    }

    private fun findPosition(item: T): Int {
        val size = items.size - 1
        for (i in size downTo 0) {
            if (items[i] == item) return i
        }
        return -1
    }

}

interface BaseRecyclerViewInterface<T>  {
    fun getItem(): MutableList<T>
    fun getItem(position: Int): T
    fun setItem(value: MutableList<T>)
    fun addItem(value: MutableList<T>)
    fun addItem(value: T)
    fun clearItem()
}