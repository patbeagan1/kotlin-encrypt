package dev.patbeagan.encryption

import java.security.KeyFactory
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec

class PublicKeyEncoder(
    override val fileName: String = "public.key"
) : KeyEncoder<PublicKey> {

    override fun storeKey(path: String, key: PublicKey) =
        writeKeyFile(getFileName(path), X509EncodedKeySpec(key.encoded).encoded)

    override fun loadKey(keyFactory: KeyFactory, path: String): PublicKey? =
        readKeyfile(getFileName(path))?.let { keyFactory.generatePublic(X509EncodedKeySpec(it)) }
}