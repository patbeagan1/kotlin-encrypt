package dev.patbeagan.encryption

import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.Provider
import java.security.Provider.Service
import java.security.Security
import javax.crypto.Cipher

class Encryptor(
    private val path: String = "/tmp/",
    private val keySize: Int = 2048,
    private val algorithm: String = "RSA",
    private val cipher: Cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
) {

    private var keyPairEncoder: KeyPairEncoder = KeyPairEncoder()

    private val pair: KeyPair = keyPairEncoder.loadKeyPair(path, algorithm) ?: kotlin.run {
        KeyPairGenerator.getInstance(algorithm)
            .apply { initialize(keySize) }
            .generateKeyPair()
            .also { keyPairEncoder.saveKeyPair(path, it) }
    }

    fun encrypt(corpus: ByteArray): ByteArray = cipher.apply {
        init(Cipher.ENCRYPT_MODE, pair.public)
        update(corpus)
    }.doFinal()

    fun decrypt(corpus: ByteArray): ByteArray = cipher.apply {
        init(Cipher.DECRYPT_MODE, pair.private)
    }.doFinal(corpus)

    fun getSupportedEncryptionServices() =
        Security.getProviders().joinToString("\n") { provider ->
            val algorithm = provider.services
                .sortedBy { it.algorithm }
                .sortedBy { it.type }
                .joinToString("\n\t") { "${it.type} ${it.algorithm}" }
            "${provider.name}\n\t$algorithm"
        }

    fun getSupportedCiphers() = Security.getProviders()
        .flatMap { provider: Provider -> provider.services }
        .filter { service: Service -> "Cipher" == service.type }
        .sortedBy { it.algorithm }
        .joinToString("\n") { it.algorithm }
}
