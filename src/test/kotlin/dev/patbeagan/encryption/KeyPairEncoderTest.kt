package dev.patbeagan.encryption

import dev.patbeagan.ext.dumpKeyPair
import dev.patbeagan.ui.RandomArt
import org.junit.Assert
import org.junit.Test
import java.nio.file.FileAlreadyExistsException
import java.security.KeyPairGenerator
import kotlin.io.path.Path
import kotlin.io.path.createDirectory

internal class KeyPairEncoderTest {
    @Test
    fun `test load`() {
        val keyPairEncoder = KeyPairEncoder()
        val keyPair = keyPairEncoder.loadKeyPair("/tmp", "RSA")!!
        val actual = RandomArt().fingerprintRandomArt(
            keyPair.public.encoded.map { it.toInt() }.toIntArray(),
            """ .o+=*BOX@%&#/^SE""",
            "[${keyPair.public.algorithm} ${keyPair.public.encoded.size}]",
            """"""
        )

        val expected = """
            +----[RSA 294]----+
            |^EXBB*o+BX#^OX@@X|
            |^^*OBB+*+=^@&B#**|
            |%#^=OOB==&%^O%*&O|
            |@B/=BO+BOo^&@X@&/|
            |*%=%.oXoS@oBBX#&%|
            |=+B +Bo*XO*.OOX^O|
            |=o.*/o@B*++=*B&*^|
            |*X%#@#o%o+.*oX=^X|
            |@%&/#X@*Xoo o OB^|
            +-----------------+
        """.trimIndent()

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test keypair encoder can save to file and reload the key`() {
        val keyPairEncoder = KeyPairEncoder()
        val path = "/tmp/kotlin-encrypt-keypairencoder"
        ensureDirectoryExists(path)

        val keyGen = KeyPairGenerator.getInstance("DSA").apply {
            initialize(1024)
        }.genKeyPair()

        keyPairEncoder.run {

            println("Generated Key Pair")
            keyGen.dumpKeyPair()

            saveKeyPair(path, keyGen)

            val loadedKeyPair = loadKeyPair(path, "DSA")

            println("Loaded Key Pair")
            loadedKeyPair!!.dumpKeyPair()
        }
    }

    private fun ensureDirectoryExists(path: String) {
        try {
            Path(path).createDirectory()
        } catch (e: FileAlreadyExistsException) {
            println("The directory already exists")
        }
    }
}