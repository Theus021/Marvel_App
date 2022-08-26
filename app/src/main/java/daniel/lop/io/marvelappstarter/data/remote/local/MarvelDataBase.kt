package daniel.lop.io.marvelappstarter.data.remote.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import daniel.lop.io.marvelappstarter.model.CaracterModel

@Database(entities = [CaracterModel::class], version = 1, exportSchema = false)

@TypeConverters(MarvelDataBase::class)
abstract class MarvelDataBase: RoomDatabase() {
    abstract fun marvelDao(): MarvelDao
}