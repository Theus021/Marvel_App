package daniel.lop.io.marvelappstarter.data.model

import com.google.gson.annotations.SerializedName
import daniel.lop.io.marvelappstarter.character.ThumbnailModel
import java.io.Serializable

data class CharacterModel(

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("descripton")
    val descripton: String,
    @SerializedName("thumbnail")
    val thumbnailModel: ThumbnailModel
): Serializable