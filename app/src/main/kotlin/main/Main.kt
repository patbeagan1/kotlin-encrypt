package main

import dev.patbeagan.encryption.Encryptor
import dev.patbeagan.ui.PrintConfig
import dev.patbeagan.ui.RandomArt

fun main() {
    val message = "Hello world"

    println(message)
    val encryptor = Encryptor()

    RandomArt().fingerprintRandomArt(
        digestRaw = "test message".toByteArray().map { it.toInt() }.toIntArray(),
        PrintConfig(
            footer = "Footer",
            header = "Header"
        )
    ).let { println(it) }

    val a = encryptor.decrypt(encryptor.encrypt(message.toByteArray()))
    println(a.toString(Charsets.UTF_8))
}