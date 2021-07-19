package com.ferum_bot.quotesapi.util

import java.time.LocalDateTime
import kotlin.random.Random

object RandomUtil {

    /**
     * Calculates random Ints between start and end inclusive.
     */
    fun getRandomIntsBetween(start: Int, end: Int, count: Int): List<Int> {
        val segmentLength = end - start + 1
        if (segmentLength <= count) {
            return List(segmentLength) { it }
        }

        val availableInts = mutableSetOf<Int>()
        val seed = System.currentTimeMillis()
        val random = Random(seed)

        while (availableInts.size != count) {
            val randomInt = random.nextInt(start, end + 1)
            if (availableInts.contains(randomInt)) {
                continue
            } else {
                availableInts.add(randomInt)
            }
        }

        return availableInts.toList()
    }

}