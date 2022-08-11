package daniel.lop.io.marvelappstarter.caracter

import com.google.gson.annotations.SerializedName

data class ThumbnailModel(

    @SerializedName("patch")
    val patch: String,

    @SerializedName("extension")
    val extension: String
)
