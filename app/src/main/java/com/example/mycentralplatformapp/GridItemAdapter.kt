package com.example.mycentralplatformapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

data class ShowcaseItem(
    val title: String,
    val subtitle: String,
    val appPackageName: String,
    val color: String = "#C62828"
)

class GridItemAdapter(
    private val items: List<ShowcaseItem>,
    private val onClick: (ShowcaseItem) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HEADER else TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_HEADER -> {
                val view = inflater.inflate(R.layout.item_header_showcase, parent, false)
                HeaderViewHolder(view)
            }
            else -> {
                val view = inflater.inflate(R.layout.item_grid_square, parent, false)
                GridItemViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is GridItemViewHolder) {
            val itemPosition = position - 1
            val item = items[itemPosition]
            holder.bind(item)
            holder.itemView.setOnClickListener { onClick(item) }
        }
    }

    override fun getItemCount(): Int = items.size + 1

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class GridItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemCard: MaterialCardView = itemView.findViewById(R.id.itemCard)
        private val itemTitleText: TextView = itemView.findViewById(R.id.itemTitleText)
        private val itemSubtitleText: TextView = itemView.findViewById(R.id.itemSubtitleText)

        fun bind(value: ShowcaseItem) {
            itemTitleText.text = value.title
            itemSubtitleText.text = value.subtitle
            itemCard.setCardBackgroundColor(resolveColor(value.color))
        }

        private fun resolveColor(colorValue: String): Int {
            return try {
                Color.parseColor(colorValue)
            } catch (_: IllegalArgumentException) {
                ContextCompat.getColor(itemView.context, R.color.splash_red)
            }
        }
    }
}
