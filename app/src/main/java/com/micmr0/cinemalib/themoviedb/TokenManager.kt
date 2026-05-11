package com.micmr0.cinemalib.themoviedb

class TokenManager : TokenInterface {
    private var token: String =
        "" // your token from TMDB website

    override fun getToken(): String {
        return token
    }

    override fun setToken(newToken: String) {
        token = newToken
    }

}

interface TokenInterface {

    fun getToken(): String

    fun setToken(newToken: String)
}