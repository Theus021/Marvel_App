package daniel.lop.io.marvelappstarter.data.remote.local

import androidx.room.*
import daniel.lop.io.marvelappstarter.model.CaracterModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(caracterModel: CaracterModel) : Long

    @Query("SELECT * FROM caractermodel ORDER BY id")
    fun getAll(): Flow<List<CaracterModel>>

    @Delete
    suspend fun delete(caracterModel: CaracterModel)
}