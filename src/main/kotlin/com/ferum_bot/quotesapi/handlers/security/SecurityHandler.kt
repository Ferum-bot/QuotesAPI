package com.ferum_bot.quotesapi.handlers.security

interface SecurityHandler {

    /**
     * Returns true if secret key is valid.
     */
    fun checkTheGetSecretKey(key: String): Boolean

    /**
     * Returns true if admin secret key is valid.
     */
    fun checkTheAdminSecretKey(key: String): Boolean
}