package com.ferum_bot.quotesapi.controllers

import com.ferum_bot.quotesapi.handlers.errors.ErrorHandler
import com.ferum_bot.quotesapi.handlers.security.SecurityHandler
import com.ferum_bot.quotesapi.interactors.AdminControllerInteractor
import com.ferum_bot.quotesapi.models.create_entities.CreateAuthorModel
import com.ferum_bot.quotesapi.models.create_entities.CreateQuoteModel
import com.ferum_bot.quotesapi.models.create_entities.CreateTagModel
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

    @PostMapping("/create/tag")
    fun createNewTag(
        @RequestHeader(value = "Secret-Key", required = true)
        secretKey: String,

        @RequestBody(required = true)
        tagToCreate: CreateTagModel,
    ): ResponseEntity<*> {
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
        val keyIsValid = securityHandler.checkTheAdminSecretKey(secretKey)
        if (!keyIsValid) {
            return errorHandler.handleInvalidAdminKey()
        }

        val response = interactor.createNewQuote(quoteToCreate)
        return ResponseEntity.ok(response)
    }

    @ExceptionHandler
    fun handleError(ex: Exception): ResponseEntity<*> {
        return errorHandler.handleBadRequest(ex)
    }
}