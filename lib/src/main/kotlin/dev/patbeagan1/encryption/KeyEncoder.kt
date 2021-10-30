package dev.patbeagan1.encryption

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.security.Key
import java.security.KeyFactory

interface KeyEncoder<T : Key> {
    val fileName: String
    fun loadKey(keyFactory: KeyFactory, path: String): T?
    fun storeKey(path: String, key: T)

    fun getFileName(path: String): String = "$path/$fileName"

    fun writeKeyFile(path: String, bytes: ByteArray) {
        FileOutputStream(path).use { it.write(bytes) }
    }

    fun readKeyfile(path: String): ByteArray? = try {
        val filePrivateKey = File(path)
        val encodedPrivateKey = ByteArray(filePrivateKey.length().toInt())
        FileInputStream(path).use {
            it.read(encodedPrivateKey)
        }
        encodedPrivateKey
    } catch (e: IOException) {
        null
    }
}