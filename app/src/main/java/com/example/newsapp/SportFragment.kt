package com.example.newsapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.JsonObjectRequest
import java.util.*
import com.android.volley.Request
import com.android.volley.Response
import org.json.JSONException

class SportFragment : Fragment() {

    lateinit var recyclerSport: RecyclerView

    lateinit var sportAdapter: SportRecyclerAdapter

    lateinit var progressBar : ProgressBar

    lateinit var progressLayout : RelativeLayout

    lateinit var search : EditText

    lateinit var searchButton : ImageButton

    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sport, container, false)

        progressBar = view.findViewById(R.id.progressBar)
        progressLayout = view.findViewById(R.id.progressLayout)

        search = view.findViewById(R.id.search)
        searchButton = view.findViewById(R.id.searchButton)

        progressLayout.visibility = View.VISIBLE

        recyclerSport = view.findViewById(R.id.recyclerSport)
        layoutManager = LinearLayoutManager(activity)

        search()

        searchButton.setOnClickListener {
            search()
        }

        return view
    }

    fun search() {
        val country = search.text.toString()
        val url = "https://newsapi.org/v2/top-headlines?country=in&category=sports&apiKey=add50efe9b24406e8ada185d56888bb2"
        val link = "https://newsapi.org/v2/top-headlines?country=$country&category=sports&apiKey=add50efe9b24406e8ada185d56888bb2"

        if(country.isEmpty()) {
            if(ConnectionManager().checkConnectivity(activity as Context)) {

                val jsonObjectRequest = object : JsonObjectRequest(
                    Request.Method.GET, link, null,

                    Response.Listener {

                        try{

                            progressLayout.visibility = View.GONE
                            println("Response are $it")
                            val lists = arrayListOf<Sport>()
                            val data = it.getJSONArray("articles")
                            for(i in 0 until data.length()) {

                                val jsonObject = data.getJSONObject(i)
                                val enterObject = Sport(

                                    jsonObject.getString("title"),
                                    jsonObject.getString("author"),
                                    jsonObject.getString("url"),
                                    jsonObject.getString("urlToImage")
                                )
                                lists.add(enterObject)
                                sportAdapter = SportRecyclerAdapter(activity as Context,lists)

                                recyclerSport.adapter = sportAdapter

                                recyclerSport.layoutManager = layoutManager

                                recyclerSport.addItemDecoration(DividerItemDecoration(recyclerSport.context,(layoutManager as LinearLayoutManager).orientation))


                            }
                        }
                        catch (e: JSONException) {
                            Toast.makeText(activity as Context,"JSON Error", Toast.LENGTH_SHORT).show()
                        }


                    },

                    Response.ErrorListener {
                        println("Error are $it")

                    }
                ) {
                    override fun getHeaders(): MutableMap<String, String> {
                        val headers = HashMap<String, String>()
                        headers["User-Agent"] = "Mozilla/5.0"
                        return headers
                    }
                }
                MySingleton.getInstance(activity as Context).addToRequestQueue(jsonObjectRequest)
            }
            else {
                val dialog = AlertDialog.Builder(activity as Context)
                dialog.setTitle("Error")
                dialog.setMessage("Internet Connection not Found")
                dialog.setPositiveButton("Open Settings") {text,listerner ->
                    val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                    startActivity(intent)
                    activity?.finish()
                }
                dialog.setNegativeButton("Exit"){text,listerner ->
                    ActivityCompat.finishAffinity(activity as Activity)
                }
                dialog.create()
                dialog.show()
            }

        } else {
            if(ConnectionManager().checkConnectivity(activity as Context)) {

                val jsonObjectRequest = object : JsonObjectRequest(
                    Request.Method.GET, url, null,

                    Response.Listener {

                        try{

                            progressLayout.visibility = View.GONE
                            println("Response are $it")
                            val lists = arrayListOf<Sport>()
                            val data = it.getJSONArray("articles")
                            for(i in 0 until data.length()) {

                                val jsonObject = data.getJSONObject(i)
                                val enterObject = Sport(

                                    jsonObject.getString("title"),
                                    jsonObject.getString("author"),
                                    jsonObject.getString("url"),
                                    jsonObject.getString("urlToImage")
                                )
                                lists.add(enterObject)
                                sportAdapter = SportRecyclerAdapter(activity as Context,lists)

                                recyclerSport.adapter = sportAdapter

                                recyclerSport.layoutManager = layoutManager

                                recyclerSport.addItemDecoration(DividerItemDecoration(recyclerSport.context,(layoutManager as LinearLayoutManager).orientation))


                            }
                        }
                        catch (e: JSONException) {
                            Toast.makeText(activity as Context,"JSON Error", Toast.LENGTH_SHORT).show()
                        }


                    },

                    Response.ErrorListener {
                        println("Error are $it")

                    }
                ) {
                    override fun getHeaders(): MutableMap<String, String> {
                        val headers = HashMap<String, String>()
                        headers["User-Agent"] = "Mozilla/5.0"
                        return headers
                    }
                }
                MySingleton.getInstance(activity as Context).addToRequestQueue(jsonObjectRequest)
            }
            else {
                val dialog = AlertDialog.Builder(activity as Context)
                dialog.setTitle("Error")
                dialog.setMessage("Internet Connection not Found")
                dialog.setPositiveButton("Open Settings") {text,listerner ->
                    val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                    startActivity(intent)
                    activity?.finish()
                }
                dialog.setNegativeButton("Exit"){text,listerner ->
                    ActivityCompat.finishAffinity(activity as Activity)
                }
                dialog.create()
                dialog.show()
            }

        }

    }
}