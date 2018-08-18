package com.wxm.appbackend

import java.util.*

object TestUtil {
    private val STR_ARR = "abcdefghigklmnopqrsguvwhyzABCDEFGHIGKLMNOPQRSGUVWHYZ0123456789"
    private val STR_ARR_LEN = STR_ARR.length

    /**
     * get random int in range [a, b]
     */
    fun getRandomNum(a:Int, b:Int): Int {
        val max = Math.max(a, b)
        val min = Math.min(a, b)
        return Random().let {
            it.nextInt(max) % (max - min + 1) + min
        }
    }

    /**
     * get random string with length [sz]
     */
    fun getRandomStr(sz:Int): String    {
        return StringBuffer().apply {
            for (i in 1..sz) {
                append(STR_ARR[getRandomNum(0, STR_ARR_LEN - 1)])
            }
        }.toString()
    }


}