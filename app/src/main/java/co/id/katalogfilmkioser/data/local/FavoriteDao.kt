package co.id.katalogfilmkioser.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by Sainal Sultan on 6/20/2020.
 * sainalsultan@gmail.com | Hasanah Dev.
 */
@Dao
interface FavoriteDao {

    @Query("SELECT * from favorite_table ORDER BY title ASC")
    fun getAllFavoriteMovie() : LiveData<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favorite: Favorite)

    @Delete
    suspend fun deleteFavoriteMovie(favorite: Favorite)

    @Query("DELETE From favorite_table")
    suspend fun deleteAllFavoriteMovie()
}