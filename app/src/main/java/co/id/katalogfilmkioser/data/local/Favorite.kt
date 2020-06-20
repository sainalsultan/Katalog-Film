package co.id.katalogfilmkioser.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Sainal Sultan on 6/20/2020.
 * sainalsultan@gmail.com | Hasanah Dev.
 */
@Entity(tableName = "favorite_table")
class Favorite (

    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "id_movie") val id_movie : String,
    @ColumnInfo(name = "title") val title : String,
    @ColumnInfo(name = "poster_path") val poster_path : String,
    @ColumnInfo(name = "vote_average") val vote_average : Float,
    @ColumnInfo(name = "release_date") val release_date : String,
    @ColumnInfo(name = "status") val status : Boolean

)