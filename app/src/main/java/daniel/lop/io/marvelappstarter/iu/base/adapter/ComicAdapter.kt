package daniel.lop.io.marvelappstarter.iu.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import daniel.lop.io.marvelappstarter.comic.ComicModel
import daniel.lop.io.marvelappstarter.databinding.ItemComicBinding


class ComicAdapter: RecyclerView.Adapter<ComicAdapter.ComicViewHolder>() {

    inner class ComicViewHolder(val binding: ItemComicBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object: DiffUtil.ItemCallback<ComicModel>(){

        override fun areItemsTheSame(oldItem: ComicModel, newItem: ComicModel): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: ComicModel, newItem: ComicModel): Boolean {
            return  oldItem.id == newItem.id && oldItem.title == newItem.title &&
                    oldItem.description == newItem.description &&
                    oldItem.thumbnailModel.patch == newItem.thumbnailModel.patch &&
                    oldItem.thumbnailModel.extension == newItem.thumbnailModel.extension
        }
    }

    private val difer = AsyncListDiffer(this, differCallBack )

    var comics: List<ComicModel>
    get()= difer.currentList
    set(value)= difer.submitList(value)
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        return ComicViewHolder(
            ItemComicBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = comics.size

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {

        val comic = comics[position]
            holder.binding.apply {
                tvNameComic.text = comic.title
                tvDescriptionComic.text = comic.description

                Glide.with(holder.itemView.context)
                    .load(comic.thumbnailModel.patch + "." + comic.thumbnailModel.extension)
                    .into(imgComic)
            }
     }

}