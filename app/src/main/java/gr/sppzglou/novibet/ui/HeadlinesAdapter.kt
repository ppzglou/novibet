package gr.sppzglou.novibet.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import gr.sppzglou.novibet.data.BetViewHeadline
import gr.sppzglou.novibet.databinding.ItemHeadlineBinding

class HeadlinesAdapter(
) : ListAdapter<BetViewHeadline, HeadlinesAdapter.ViewHolder>(HEADLINES_DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemViewBinding = ItemHeadlineBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(itemViewBinding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemHeadlineBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BetViewHeadline) {
            with(binding) {
                competitor1Caption.text = item.competitor1Caption
                competitor2Caption.text = item.competitor2Caption
                startTime.text = item.startTime
            }
        }
    }

}

val HEADLINES_DIFF_UTIL = object : DiffUtil.ItemCallback<BetViewHeadline>() {
    override fun areItemsTheSame(oldItem: BetViewHeadline, newItem: BetViewHeadline): Boolean =
        oldItem.competitor1Caption == newItem.competitor1Caption

    override fun areContentsTheSame(oldItem: BetViewHeadline, newItem: BetViewHeadline): Boolean =
        oldItem == newItem
}
