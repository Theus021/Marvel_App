package daniel.lop.io.marvelappstarter.model

import com.google.gson.annotations.SerializedName
import daniel.lop.io.marvelappstarter.character.ThumbnailModel
import java.io.Serializable

data class CaracterModel(

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnailModel: ThumbnailModel
): Serializable