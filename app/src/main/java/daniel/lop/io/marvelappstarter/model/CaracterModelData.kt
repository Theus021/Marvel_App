package daniel.lop.io.marvelappstarter.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CaracterModelData(

    @SerializedName("results")
    val results: List<ComicModel>
): Serializable
