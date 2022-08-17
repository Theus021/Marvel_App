package daniel.lop.io.marvelappstarter.model

import com.google.gson.annotations.SerializedName
import daniel.lop.io.marvelappstarter.character.ThumbnailModel
import java.io.Serializable

data class ComicModel(

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val title: String,
    @SerializedName("descripton")
    val descripton: String,
    @SerializedName("thumbnail")
    val thumbnailModel: ThumbnailModel
): Serializable