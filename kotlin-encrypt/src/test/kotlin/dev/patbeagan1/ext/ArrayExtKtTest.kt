package dev.patbeagan1.ext

import org.junit.Test
import kotlin.test.assertEquals

internal class ArrayExtKtTest {
    val subject = arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6))

    @Test
    fun `width works correctly`() {
        assertEquals(3, subject.width)
    }

    @Test
    fun `height works correctly`() {
        assertEquals(2, subject.height)
    }
}