package com.ferum_bot.quotesapi.handlers.security.impl

import com.ferum_bot.quotesapi.handlers.security.SecurityHandler
import com.ferum_bot.quotesapi.util.SecretKeys

class BaseSecurityHandler: SecurityHandler{

    override fun checkTheGetSecretKey(key: String): Boolean {
        if (key == SecretKeys.DEBUG_KEY) {
            return true
        }
        if (key == SecretKeys.PROD_KEY) {
            return true
        }
        return false
    }

}