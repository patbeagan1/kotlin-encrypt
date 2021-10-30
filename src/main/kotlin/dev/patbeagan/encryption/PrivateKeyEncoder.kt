package dev.patbeagan.encryption

import java.security.KeyFactory
import java.security.PrivateKey
import java.security.spec.PKCS8EncodedKeySpec

class PrivateKeyEncoder(
    override val fileName: String = "private.key"
) : KeyEncoder<PrivateKey> {

    override fun storeKey(path: String, key: PrivateKey) =
        writeKeyFile(getFileName(path), PKCS8EncodedKeySpec(key.encoded).encoded)

    override fun loadKey(keyFactory: KeyFactory, path: String): PrivateKey? =
        readKeyfile(getFileName(path))?.let { keyFactory.generatePrivate(PKCS8EncodedKeySpec(it)) }
}