package pl.daftacademy.androidlevelup.view.home

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.children
import kotlinx.android.synthetic.main.activity_home.*
import pl.daftacademy.androidlevelup.R
import pl.daftacademy.androidlevelup.view.movies.MoviesFragment

class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        nav.setNavigationItemSelectedListener { changePage(item = it) }
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "All movies"
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                drawer.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


    private fun changePage(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.all_movies -> showMovies("All movies")
            R.id.action_movies -> showMovies("Action")
            R.id.comedy_movies -> showMovies("Comedy")
            R.id.crime_movies -> showMovies("Crime")
            R.id.horror_movies -> showMovies("Horror")
            R.id.romance_movies -> showMovies("Romance")
            else -> return false
        }
        nav.menu.children.find { it.isChecked }?.isChecked = false
        item.isChecked = true
        drawer.closeDrawers()
        return true
    }

    private fun showMovies(name: String) {
        supportActionBar?.title = name
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, MoviesFragment.create(name))
            .commit()
    }
}