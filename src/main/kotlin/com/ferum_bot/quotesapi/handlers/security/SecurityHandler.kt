package com.ferum_bot.quotesapi.handlers.security

interface SecurityHandler {

    /**
     * Returns true if secret key is valid.
     */
    fun checkTheSecretKey(key: String): Boolean

}