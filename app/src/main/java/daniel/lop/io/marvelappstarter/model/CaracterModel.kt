package daniel.lop.io.marvelappstarter.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import daniel.lop.io.marvelappstarter.character.ThumbnailModel
import java.io.Serializable

@Entity(tableName = "caracterModel")
data class CaracterModel(

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnailModel: ThumbnailModel
) : Serializable