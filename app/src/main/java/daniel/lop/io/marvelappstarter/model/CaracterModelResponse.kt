package daniel.lop.io.marvelappstarter.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CaracterModelResponse(

    @SerializedName("data")
    val data: CaracterModelData

):Serializable
