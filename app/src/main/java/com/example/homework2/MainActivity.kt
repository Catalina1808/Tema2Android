package com.example.homework2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.homework2.Constants.Companion.ADDRESS
import com.example.homework2.Constants.Companion.ALBUM_ID
import com.example.homework2.Constants.Companion.BASE_URL
import com.example.homework2.Constants.Companion.BODY
import com.example.homework2.Constants.Companion.BS
import com.example.homework2.Constants.Companion.CATCHPHRASE
import com.example.homework2.Constants.Companion.CITY
import com.example.homework2.Constants.Companion.COMPANY
import com.example.homework2.Constants.Companion.COMPANYNAME
import com.example.homework2.Constants.Companion.EMAIL
import com.example.homework2.Constants.Companion.GEO
import com.example.homework2.Constants.Companion.ID
import com.example.homework2.Constants.Companion.LAT
import com.example.homework2.Constants.Companion.LNG
import com.example.homework2.Constants.Companion.NAME
import com.example.homework2.Constants.Companion.PHONE
import com.example.homework2.Constants.Companion.STREET
import com.example.homework2.Constants.Companion.SUITE
import com.example.homework2.Constants.Companion.THUMBNAILURL
import com.example.homework2.Constants.Companion.TITLE
import com.example.homework2.Constants.Companion.URL
import com.example.homework2.Constants.Companion.USERNAME
import com.example.homework2.Constants.Companion.USER_ID
import com.example.homework2.Constants.Companion.WEBSITE
import com.example.homework2.Constants.Companion.ZIPCODE
import com.example.homework2.adapters.MyAdapter
import com.example.homework2.interfaces.OnAlbumItemClick
import com.example.homework2.interfaces.OnButtonClick
import com.example.homework2.interfaces.OnUserItemClick
import com.example.homework2.models.Entertainment
import com.example.homework2.models.Photo
import com.example.homework2.models.Post
import com.example.homework2.models.User
import com.example.myapplication.`interface`.ActivityFragmentComunication
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity(), ActivityFragmentComunication {

    val users: ArrayList<Entertainment> = ArrayList<Entertainment>()
    val albums: ArrayList<Entertainment> = ArrayList<Entertainment>()
    val photos:ArrayList<Entertainment> = ArrayList<Entertainment>()
    var adapter: MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFirstFragment()


    }

    fun addFirstFragment() {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val tag = Fragment1::class.java.name
        val addTransaction = transaction.add(
                R.id.frame_layout, Fragment1.newInstance(), tag)

        addTransaction.addToBackStack(tag)
        addTransaction.commit()
    }

    fun replaceWithSecondFragment() {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val tag = Fragment2::class.java.name
        val replaceTransaction = transaction.replace(
                R.id.frame_layout, Fragment2.newInstance(), tag)
        replaceTransaction.addToBackStack(tag)
        replaceTransaction.commit()
    }

    fun replaceWithThirdFragment() {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val tag = Fragment3::class.java.name
        val replaceTransaction = transaction.replace(
                R.id.frame_layout, Fragment3.newInstance(), tag)
        replaceTransaction.addToBackStack(tag)
        replaceTransaction.commit()
    }

    override fun setUpRecyclerView1() {
        val recyclerView: RecyclerView = findViewById(R.id.list)
        val layoutManager: LinearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        users.clear()
        albums.clear()
        adapter = MyAdapter(users, OnUserItemClick { user -> replaceWithSecondFragment(); getAlbums(user) }, OnButtonClick { user -> getPosts(user) })

        recyclerView.adapter = adapter

    }

    override fun setUpRecyclerView2() {
        val recyclerView: RecyclerView = findViewById(R.id.list2)
        val layoutManager: LinearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
       // albums.clear()
        adapter = MyAdapter(albums, OnAlbumItemClick { post->replaceWithThirdFragment(); getPhotos(post)})

        recyclerView.adapter = adapter

    }

    override fun setUpRecyclerView3() {
        val recyclerView: RecyclerView = findViewById(R.id.list3)
        val layoutManager: GridLayoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        photos.clear()
        adapter = MyAdapter(photos)

        recyclerView.adapter = adapter

    }

    fun getPhotos(album: Post) {
        val volleyConfigSingleton = VolleyConfigSingleton.getInstance(this.applicationContext)
        val queue = volleyConfigSingleton.requestQueue
        val url = "$BASE_URL/photos"
        val getPhotosRequest = StringRequest(
                Request.Method.GET,
                url,
                Response.Listener<String> { responseJson ->
                    handlePhotosResponse(responseJson, album)
                },
                Response.ErrorListener { error ->
                    Toast.makeText(
                            this,
                            "ERROR: get users failed with error: ${error.message}",
                            Toast.LENGTH_SHORT
                    ).show()
                }

        )
        queue.add(getPhotosRequest)
    }


    fun handlePhotosResponse(response: String, album: Post) {
        val photosJSONArray = JSONArray(response)
        for (index in 0 until photosJSONArray.length()) {
            val photoJSON: JSONObject? = photosJSONArray[index] as? JSONObject
           photoJSON?.let {
                val id = photoJSON.getString(ID)
                val albumId = photoJSON.getString(ALBUM_ID)
                val title = photoJSON.getString(TITLE)
                val url= photoJSON.getString(URL)
                val thumbnailUrl= photoJSON.getString(THUMBNAILURL)

                val photo: Photo = Photo(id, albumId, title, url, thumbnailUrl)
                if (albumId == album.id ) {
                    photos.add(photo)

                }

            }
        }
        adapter?.notifyDataSetChanged()
    }


    fun getAlbums(user: User) {
        val volleyConfigSingleton = VolleyConfigSingleton.getInstance(this.applicationContext)
        val queue = volleyConfigSingleton.requestQueue
        val url = "$BASE_URL/albums"
        val getAlbumsRequest = StringRequest(
                Request.Method.GET,
                url,
                Response.Listener<String> { responseJson ->
                    handleAlbumsResponse(responseJson, user)
                },
                Response.ErrorListener { error ->
                    Toast.makeText(
                            this,
                            "ERROR: get users failed with error: ${error.message}",
                            Toast.LENGTH_SHORT
                    ).show()
                }

        )
        queue.add(getAlbumsRequest)
    }


    fun handleAlbumsResponse(response: String, user: User) {
        val postsJSONArray = JSONArray(response)
        for (index in 0 until postsJSONArray.length()) {
            val userPostJSON: JSONObject? = postsJSONArray[index] as? JSONObject
            userPostJSON?.let {
                val id = userPostJSON.getString(ID)
                val userID = userPostJSON.getString(USER_ID)
                val title = userPostJSON.getString(TITLE)

                val post: Post = Post(id, userID, title)
                if (userID == user.id && !albums.contains(post)) {
                    albums.add(post)

                }

            }
        }
        adapter?.notifyDataSetChanged()
    }


    override fun getUsers() {
        val volleyConfigSingleton = VolleyConfigSingleton.getInstance(this.applicationContext)
        val queue = volleyConfigSingleton.requestQueue
        val url = "$BASE_URL/users"
        val getUsersRequest = StringRequest(
                Request.Method.GET,
                url,
                { responseJson ->
                    handleUsersResponse(responseJson)
                },
                { error ->
                    Toast.makeText(
                            this,
                            "ERROR: get users failed with error: ${error.message}",
                            Toast.LENGTH_SHORT
                    ).show()
                }
        )
        queue.add(getUsersRequest)
    }

    fun handleUsersResponse(response: String) {
        val usersJSONArray = JSONArray(response)
        for (index in 0 until usersJSONArray.length()) {
            val usersJSON: JSONObject? = usersJSONArray[index] as? JSONObject
            usersJSON?.let {
                val id = usersJSON.getString(ID)
                val name = usersJSON.getString(NAME)
                val username = usersJSON.getString(USERNAME)
                val email = usersJSON.getString(EMAIL)
                val street = usersJSON.getJSONObject(ADDRESS).getString(STREET)
                val suite = usersJSON.getJSONObject(ADDRESS).getString(SUITE)
                val city = usersJSON.getJSONObject(ADDRESS).getString(CITY)
                val zipcode = usersJSON.getJSONObject(ADDRESS).getString(ZIPCODE)

                val lat = usersJSON.getJSONObject(ADDRESS).getJSONObject(GEO).getString(LAT)
                val lng = usersJSON.getJSONObject(ADDRESS).getJSONObject(GEO).getString(LNG)


                val phone = usersJSON.getString(PHONE)
                val website = usersJSON.getString(WEBSITE)

                val companyName = usersJSON.getJSONObject(COMPANY).getString(COMPANYNAME)
                val catchPhrase = usersJSON.getJSONObject(COMPANY).getString(CATCHPHRASE)
                val bs = usersJSON.getJSONObject(COMPANY).getString(BS)


                val user: User = User(name, id, username, email, street, suite, city, zipcode, lat, lng, phone, website, companyName, catchPhrase, bs)
                users.add(user)

                adapter?.notifyDataSetChanged()
            }
        }

    }

    fun getPosts(user: User) {
        val volleyConfigSingleton = VolleyConfigSingleton.getInstance(this.applicationContext)
        val queue = volleyConfigSingleton.requestQueue
        val url = "$BASE_URL/posts"
        val getPostsRequest = StringRequest(
                Request.Method.GET,
                url,
                Response.Listener<String> { responseJson ->
                    handlePostsResponse(responseJson, user)
                },
                Response.ErrorListener { error ->
                    Toast.makeText(
                            this,
                            "ERROR: get users failed with error: ${error.message}",
                            Toast.LENGTH_SHORT
                    ).show()
                }

        )
        queue.add(getPostsRequest)
    }


    fun handlePostsResponse(response: String, user: User) {
        val postsJSONArray = JSONArray(response)
        var indexList: Int = users.indexOf(user) + 1;
        for (index in 0 until postsJSONArray.length()) {
            val userPostJSON: JSONObject? = postsJSONArray[index] as? JSONObject
            userPostJSON?.let {
                val id = userPostJSON.getString(ID)
                val userID = userPostJSON.getString(USER_ID)
                val body = userPostJSON.getString(BODY)
                val title = userPostJSON.getString(TITLE)

                val post: Post = Post(id, userID, title, body)


                if (userID == user.id && !users.contains(post)) {     //DC APAR DUPLICATE???
                    users.add(indexList, post)
                    indexList++
                }

            }
        }
        adapter?.notifyDataSetChanged()
    }


}