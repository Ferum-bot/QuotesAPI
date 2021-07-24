package com.ferum_bot.quotesapi.util.logging

import com.ferum_bot.quotesapi.models.response.QuotesResponse
import org.slf4j.LoggerFactory

class DefaultApiLogger: ApiLogger {

    private val logger = LoggerFactory.getLogger(DefaultApiLogger::class.java)

    override fun <T : Any> logAll(
        response: QuotesResponse<T>, extraMessage: () -> String
    ) {
        val data = response.data
        val error = response.error
        val message = extraMessage()

        data?.let {
            logger.info(
                "Message: $message model: $it"
            )
        }

        error?.let {
            logger.error(
                "Message: $message model: $it"
            )
        }
    }

    override fun <T : Any> logError(
        response: QuotesResponse<T>, extraMessage: () -> String
    ) {
        val error = response.error ?: return
        val message = extraMessage()
        logger.error(
            "Message: $message model: $error"
        )
    }

    override fun <T : Any> logSuccess(
        response: QuotesResponse<T>, extraMessage: () -> String
    ) {
        val data = response.data ?: return
        val message = extraMessage()
        logger.info(
            "Message: $message model: $data"
        )
    }

    override fun logDebug(getMessage: () -> String) {
        val message = getMessage()
        logger.debug(message)
    }

    override fun logError(getMessage: () -> String) {
        val message = getMessage()
        logger.error(message)
    }

    override fun logInfo(getMessage: () -> String) {
        val message = getMessage()
        logger.info(message)
    }

    override fun logWarn(getMessage: () -> String) {
        val message = getMessage()
        logger.warn(message)
    }

    override fun logInvalidAccessKey(invalidKey: String) {
        logger.warn("Received invalid access key: $invalidKey")
    }

    override fun logInvalidAdminKey(invalidKey: String) {
        logger.warn("Received invalid admin key: $invalidKey")
    }
}