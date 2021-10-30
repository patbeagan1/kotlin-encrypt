package dev.patbeagan.encryption

import java.security.KeyFactory
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec

class PublicKeyEncoder : KeyEncoder<PublicKey> {
    private fun getFileName(path: String) = "$path/public.key"

    override fun storeKey(path: String, key: PublicKey) =
        writeKeyFile(getFileName(path), X509EncodedKeySpec(key.encoded).encoded)

    override fun loadKey(keyFactory: KeyFactory, path: String): PublicKey? =
        readKeyfile(getFileName(path))?.let { keyFactory.generatePublic(X509EncodedKeySpec(it)) }
}