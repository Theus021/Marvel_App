package daniel.lop.io.marvelappstarter.model

import com.google.gson.annotations.SerializedName
import daniel.lop.io.marvelappstarter.caracter.ThumbnailModel
import java.io.Serializable

data class CaracterModel(

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("descripton")
    val descripton: String,
    @SerializedName("thumbnail")
    val thumbnailModel: ThumbnailModel
): Serializable