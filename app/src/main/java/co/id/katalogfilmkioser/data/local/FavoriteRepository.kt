package co.id.katalogfilmkioser.data.local

import androidx.lifecycle.LiveData

/**
 * Created by Sainal Sultan on 6/20/2020.
 * sainalsultan@gmail.com | Hasanah Dev.
 */
class FavoriteRepository(private val favoriteDao: FavoriteDao) {

    val allFavorite : LiveData<List<Favorite>> = favoriteDao.getAllFavoriteMovie()

    /*fun getAllFavoriteMovie(){
        favoriteDao.getAllFavoriteMovie()
    }*/

    suspend fun insert(favorite: Favorite){
        favoriteDao.insert(favorite)
    }
}