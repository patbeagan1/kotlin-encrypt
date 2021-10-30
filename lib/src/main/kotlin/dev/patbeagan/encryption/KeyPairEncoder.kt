package dev.patbeagan.encryption

import java.io.IOException
import java.security.*
import java.security.spec.InvalidKeySpecException


class KeyPairEncoder(
    private val privateKeyEncoder: KeyEncoder<PrivateKey> = PrivateKeyEncoder(),
    private val publicKeyEncoder: KeyEncoder<PublicKey> = PublicKeyEncoder()
) {

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