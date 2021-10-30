package dev.patbeagan.encryption

import org.junit.Assert
import org.junit.Test

internal class EncryptorTest {
    @Test
    fun `get encryption details`() {
        Encryptor.apply {
            println(getSupportedCiphers())
            println(getSupportedEncryptionServices())
        }
    }

    @Test
    fun `test default encryption works as expected`() {
        val inputString = "your string here"

        val encryptor = Encryptor()
        val encrypt: ByteArray = encryptor.encrypt(inputString.toByteArray())
        val decrypt: ByteArray = encryptor.decrypt(encrypt)
        Assert.assertEquals(inputString, decrypt.toString(Charsets.UTF_8))
    }
}