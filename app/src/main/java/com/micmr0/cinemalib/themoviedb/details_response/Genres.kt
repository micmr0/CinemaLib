package com.micmr0.cinemalib.themoviedb.details_response

import com.google.gson.annotations.SerializedName

data class Genres (
  @SerializedName("id"   ) var id   : Int?    = null,
  @SerializedName("name" ) var name : String? = null

)