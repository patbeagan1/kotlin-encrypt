package main

import encryption.Encryptor
import encryption.KeyPairEncoder
import java.security.KeyPairGenerator

fun main(args: Array<String>) {
    testKeyPairEncoder()
    testEncryptor()
}

private fun testEncryptor() {
    val encryptor = Encryptor()
    val encrypt: ByteArray = encryptor.encrypt("testkjashgrlaghfhj".toByteArray())
    val decrypt: ByteArray = encryptor.decrypt(encrypt)
    println(decrypt.toString(Charsets.UTF_8))
}

private fun testKeyPairEncoder() {
    val keyPairEncoder = KeyPairEncoder()
    try {
        val path = "/tmp/ttt"
        val keyGen = KeyPairGenerator.getInstance("DSA").apply {
            initialize(1024)
        }.genKeyPair()

        keyPairEncoder.run {

            println("Generated Key Pair")
            dumpKeyPair(keyGen)

            saveKeyPair(path, keyGen)

            val loadedKeyPair = loadKeyPair(path, "DSA")

            println("Loaded Key Pair")
            dumpKeyPair(loadedKeyPair)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}