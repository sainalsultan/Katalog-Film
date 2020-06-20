package co.id.katalogfilmkioser.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


/**
 * Created by Sainal Sultan on 6/20/2020.
 * sainalsultan@gmail.com | Hasanah Dev.
 */
@Database(entities = arrayOf(Favorite::class),version = 2,exportSchema = false)
abstract class FavoriteRoomDatabase : RoomDatabase() {

    abstract fun favoriteDao() : FavoriteDao

    companion object{

        val MIGRATION_1_2 : Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE favorite_table "
                            + " ADD COLUMN id_movie TEXT"
                )
            }
        }

        @Volatile
        private var INSTANCE : FavoriteRoomDatabase?=null

        fun getDataBase(context: Context) : FavoriteRoomDatabase{
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteRoomDatabase::class.java,
                    "favorite_database"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}