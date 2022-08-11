package daniel.lop.io.marvelappstarter.comic

import com.google.gson.annotations.SerializedName
import daniel.lop.io.marvelappstarter.caracter.ThumbnailModel
import java.io.Serializable

data class ComicModel(

    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnailModel: ThumbnailModel

): Serializable
