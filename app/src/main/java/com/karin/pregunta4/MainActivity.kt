package com.karin.pregunta4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    private lateinit var etNameMovie: EditText
    private lateinit var btnSearch: Button

    private var listMovie = mutableListOf<Movie>()
    private var rvMovies: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMovies = findViewById(R.id.rvMovies)

        etNameMovie = findViewById(R.id.etNameMovie)
        btnSearch = findViewById(R.id.btnSearch)

        btnSearch.setOnClickListener {
            if (Network.existeRed(this)) {

                val name = etNameMovie.text
                val url = "https://www.omdbapi.com/?apikey=1c315882&s=$name"
                searchMovie(url)

                listMovie.clear()

            } else {
                Toast.makeText(this, "No hay red", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun searchMovie(url: String) {
        val queue = Volley.newRequestQueue(this)

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                val jsonArray = response.getJSONArray("Search")
               // Log.d("json", jsonArray.toString())

                for (i in 0 until jsonArray.length()) {

                    val jsonObj = jsonArray.getJSONObject(i)
                 //   Log.d("prueba", jsonObj.toString())

                    val movie = Movie(
                        jsonObj.getString("Title"),
                        jsonObj.getString("Year"),
                        jsonObj.getString("Poster")
                    )
                    listMovie.add(movie)
                }
                rvMovies?.layoutManager = GridLayoutManager(this, 2)
                rvMovies?.adapter = MovieAdapter(listMovie)

            }, { error ->
                Log.d("error", error.message.toString())
            })
        queue.add(request)
    }
}
