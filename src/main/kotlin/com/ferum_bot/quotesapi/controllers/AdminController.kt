package com.ferum_bot.quotesapi.controllers

import com.ferum_bot.quotesapi.handlers.errors.ErrorHandler
import com.ferum_bot.quotesapi.handlers.security.SecurityHandler
import com.ferum_bot.quotesapi.interactors.AdminControllerInteractor
import com.ferum_bot.quotesapi.models.create_entities.CreateAuthorModel
import com.ferum_bot.quotesapi.models.create_entities.CreateQuoteModel
import com.ferum_bot.quotesapi.models.create_entities.CreateTagModel
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import kotlin.Exception

@RestController
@RequestMapping("/admin/console")
class AdminController {

    @Autowired
    private lateinit var securityHandler: SecurityHandler

    @Autowired
    private lateinit var errorHandler: ErrorHandler

    @Autowired
    private lateinit var interactor: AdminControllerInteractor

    private val logger = LoggerFactory.getLogger(AdminController::class.java)

    @PostMapping("/create/tag")
    fun createNewTag(
        @RequestHeader(value = "Secret-Key", required = true)
        secretKey: String,

        @RequestBody(required = true)
        tagToCreate: CreateTagModel,
    ): ResponseEntity<*> {
        logger.info("Create new tag called with: $tagToCreate")

        val keyIsValid = securityHandler.checkTheAdminSecretKey(secretKey)
        if (!keyIsValid) {
            return errorHandler.handleInvalidAdminKey()
        }

        val response = interactor.createNewTag(tagToCreate)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/create/author")
    fun createNewAuthor(
        @RequestHeader(value = "Secret-Key", required = true)
        secretKey: String,

        @RequestBody(required = true)
        authorToCreate: CreateAuthorModel,
    ): ResponseEntity<*> {
        logger.info("Create new author called with: $authorToCreate")

        val keyIsValid = securityHandler.checkTheAdminSecretKey(secretKey)
        if (!keyIsValid) {
            return errorHandler.handleInvalidAdminKey()
        }

        val response = interactor.createNewAuthor(authorToCreate)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/create/quote")
    fun createNewQuote(
        @RequestHeader(value = "Secret-Key", required = true)
        secretKey: String,

        @RequestBody(required = true)
        quoteToCreate: CreateQuoteModel,
    ): ResponseEntity<*> {
        logger.info("Create new quote called with: $quoteToCreate")

        val keyIsValid = securityHandler.checkTheAdminSecretKey(secretKey)
        if (!keyIsValid) {
            return errorHandler.handleInvalidAdminKey()
        }

        val response = interactor.createNewQuote(quoteToCreate)
        return ResponseEntity.ok(response)
    }

    @ExceptionHandler
    fun handleError(ex: Exception): ResponseEntity<*> {
        logger.error("Handled error: $ex")

        return errorHandler.handleBadRequest(ex)
    }
}