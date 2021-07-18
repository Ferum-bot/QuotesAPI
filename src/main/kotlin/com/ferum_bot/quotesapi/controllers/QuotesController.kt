package com.ferum_bot.quotesapi.controllers

import com.ferum_bot.quotesapi.util.PathConstants
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("${PathConstants.BASE_PATH}${PathConstants.QUOTES_BASE_PATH}")
class QuotesController {

    fun getMainScreenModel(

    ): ResponseEntity {

    }

}