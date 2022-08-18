package daniel.lop.io.marvelappstarter.data.remote

import daniel.lop.io.marvelappstarter.comic.ComicModelResponse
import daniel.lop.io.marvelappstarter.model.CaracterModelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {

    @GET("Characters")
    suspend fun list(
        @Query("nameStartsWith") nameStartsWith: String? = null
    ):Response<CaracterModelResponse>

    @GET("characters/{characterId}/comics")
    suspend fun getComics(

        @Path(
            value = "characterId",
            encoded = true
        )characterId: Int

    ): Response<CaracterModelResponse>

}