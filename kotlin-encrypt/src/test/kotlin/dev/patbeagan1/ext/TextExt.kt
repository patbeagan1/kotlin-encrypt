package dev.patbeagan1.ext

import dev.patbeagan1.ui.PrintConfig
import dev.patbeagan1.ui.RandomArt
import java.security.Key
import java.security.KeyPair

fun KeyPair.dumpKeyPair() {
    dumpKey("Public Key", public)
    dumpKey("Private Key", private)
}

private fun dumpKey(keyName: String, key: Key) {
    println("$keyName: " + key.encoded.let { it.contentToString() })
    val digestRaw = key.encoded?.map { it.toInt() }?.toIntArray()
    digestRaw?.let {
        RandomArt().fingerprintRandomArt(
            digestRaw = it,
            PrintConfig(
                header = "[${key.algorithm} ${key.encoded.size}]",
                footer = ""
            )
        )
    }.let { println(it) }
}