package co.id.katalogfilmkioser.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import co.id.katalogfilmkioser.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.fragment)
        bottomNav.setupWithNavController(navController)

//
//        val repository = MoviesRepository(MoviesApi())
//        GlobalScope.launch (Dispatchers.Main){
//            val movies = repository.getMovies()
//            Toast.makeText(this@MainActivity, movies.toString(),Toast.LENGTH_LONG).show()
//        }
    }
}
