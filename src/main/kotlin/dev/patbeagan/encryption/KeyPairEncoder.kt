package dev.patbeagan.encryption

import java.io.IOException
import java.security.KeyFactory
import java.security.KeyPair
import java.security.NoSuchAlgorithmException
import java.security.spec.InvalidKeySpecException

class KeyPairEncoder {
    private val publicKeyEncoder = PublicKeyEncoder()
    private val privateKeyEncoder = PrivateKeyEncoder()

    @Throws(IOException::class)
    fun saveKeyPair(path: String, keyPair: KeyPair) {
        publicKeyEncoder.storeKey(path, keyPair.public)
        privateKeyEncoder.storeKey(path, keyPair.private)
    }

    @Throws(IOException::class, NoSuchAlgorithmException::class, InvalidKeySpecException::class)
    fun loadKeyPair(path: String, algorithm: String?): KeyPair? {
        val keyFactory = KeyFactory.getInstance(algorithm)
        val publicKey = publicKeyEncoder.loadKey(keyFactory, path)
        val privateKey = privateKeyEncoder.loadKey(keyFactory, path)
        return if (publicKey != null && privateKey != null) {
            KeyPair(publicKey, privateKey)
        } else {
            null
        }
    }
}