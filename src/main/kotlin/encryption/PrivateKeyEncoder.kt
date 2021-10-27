package encryption

import java.security.KeyFactory
import java.security.PrivateKey
import java.security.spec.PKCS8EncodedKeySpec

class PrivateKeyEncoder : KeyEncoder<PrivateKey> {
    private fun getFileName(path: String) = "$path/private.key"

    override fun storeKey(path: String, key: PrivateKey) =
        writeKeyFile(getFileName(path), PKCS8EncodedKeySpec(key.encoded).encoded)

    override fun loadKey(keyFactory: KeyFactory, path: String): PrivateKey? =
        readKeyfile(getFileName(path))?.let { keyFactory.generatePrivate(PKCS8EncodedKeySpec(it)) }
}