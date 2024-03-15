package com.dafay.demo.widgets

import org.junit.Test
import com.dafay.demo.lib.base.utils.println

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_runCatching(){
        kotlin.runCatching {
            var b=0
            var c=100/b
        }.let {
            if(it.isFailure){
                println("it ${it}")
            }
        }
    }
}