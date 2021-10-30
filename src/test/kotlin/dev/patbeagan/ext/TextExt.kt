package dev.patbeagan.ext

import dev.patbeagan.ui.RandomArt
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
            title = "[${key.algorithm} ${key.encoded.size}]",
            hash = ""
        )
    }.let { println(it) }
}