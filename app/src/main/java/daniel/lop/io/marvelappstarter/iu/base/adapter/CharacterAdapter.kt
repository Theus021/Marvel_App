package daniel.lop.io.marvelappstarter.iu.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import daniel.lop.io.marvelappstarter.R
import daniel.lop.io.marvelappstarter.databinding.ItemCharacterBinding
import daniel.lop.io.marvelappstarter.model.CaracterModel
import daniel.lop.io.marvelappstarter.util.limitDescription


class CharacterAdapter: RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    inner class CharacterViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object: DiffUtil.ItemCallback<CaracterModel>(){

        override fun areItemsTheSame(oldItem: CaracterModel, newItem: CaracterModel): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: CaracterModel, newItem: CaracterModel): Boolean {
            return  oldItem.id == newItem.id && oldItem.name == newItem.name &&
                    oldItem.description == newItem.description &&
                    oldItem.thumbnailModel.patch == newItem.thumbnailModel.patch &&
                    oldItem.thumbnailModel.extension == newItem.thumbnailModel.extension

        }

    }

    private val difer = AsyncListDiffer(this, differCallBack )

    var characters: List<CaracterModel>
    get()= difer.currentList
    set(value)= difer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {

        val character = characters[position]
            holder.binding.apply {
                tvNameCharacter.text = character.name
                if (character.description == "") {
                    tvNameCharacter.text =
                    holder.itemView.context.getString(R.string.text_description_empty)
                }else{
                    tvDescriptionCharacter.text = character.description.limitDescription(100)
                }
                Glide.with(holder.itemView.context)
                    .load(character.thumbnailModel.patch + "." + character.thumbnailModel.extension)
                    .into(imgCharacter)
            }

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(character)
            }
        }
    }

    private var onItemClickListener:((CaracterModel) -> Unit)? = null

    fun setOnClickListener(listener: (CaracterModel) -> Unit){
            onItemClickListener = listener
    }

    fun getCharacterPosition(position: Int): CaracterModel {
        return characters [position]
    }

}