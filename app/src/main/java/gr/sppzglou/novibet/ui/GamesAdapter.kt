package gr.sppzglou.novibet.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import gr.sppzglou.novibet.data.Competition
import gr.sppzglou.novibet.databinding.ItemGameBinding

class GamesAdapter(
) : ListAdapter<Competition, GamesAdapter.ViewHolder>(GAMES_DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemViewBinding = ItemGameBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(itemViewBinding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemGameBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Competition) {
            with(binding) {
                try {
                    item.events[0].let {
                        competitor1.text = it.additionalCaptions.competitor1
                        competitor2.text = it.additionalCaptions.competitor2
                        elapsed.text = it.liveData.elapsed
                    }
                } catch (e: Exception) {
                }
            }
        }
    }

}

val GAMES_DIFF_UTIL = object : DiffUtil.ItemCallback<Competition>() {
    override fun areItemsTheSame(oldItem: Competition, newItem: Competition): Boolean =
        oldItem.events.size == newItem.events.size

    override fun areContentsTheSame(oldItem: Competition, newItem: Competition): Boolean =
        oldItem == newItem
}
