package com.example.myganesha.APICalling


import com.example.myganesha.APICalling.Aarti.AartiPojo
import com.example.myganesha.APICalling.Decoration.DecorationPojo
import com.example.myganesha.APICalling.Mandal.MandalPojo
import com.example.myganesha.APICalling.Murtikar.MurtikarPojo
import com.example.myganesha.APICalling.Stories.StoriesPojo
import com.example.myganesha.APICalling.Stories.StoriesPojoItem
import com.example.myganesha.APICalling.Stories.StoryPojo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/v3/ganesha/murtikar")
    fun getData(@Query("category_id") category_id: Int ): Call<MurtikarPojo>



    @GET("/v3/ganesha/famous_pandals")
    fun getData2(): Call<MandalPojo>


    @GET("/v3/ganesha/decoration_ideas")
    fun getData3(): Call<DecorationPojo>

    @GET("/v3/ganesha/stories_categories")
    fun stories() : Call<StoriesPojo>

    @GET("/v3/ganesha/stories")
    fun story(@Query("category_id") category_id: String) : Call<StoryPojo>

    @GET("/v3/ganesha/aarti")
    fun music(): Call<AartiPojo>

}