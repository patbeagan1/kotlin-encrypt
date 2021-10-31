package dev.patbeagan1.ui

import org.junit.Assert
import org.junit.Test

internal class RandomArtTest {
    @Test
    fun `random art prints correctly - key 1`() {

        val fingerprintRandomart = RandomArt().fingerprintRandomArt(
            KEY1.map { it.code }
                .also {
                    println()
                }.toIntArray(),
            PrintConfig(
                """ .o+=*BOX@%&#/^SE""",
                """test""",
                """"""
            )
        )

        val expected = """
            +------test-------+
            |E+Oo+oo.oo   o..+|
            |^*.*..oo. + + . O|
            |o o.oo.+...* +.+%|
            |.. o.ooo+o=o+o*O^|
            |  . . .oS+=o=BX#^|
            |        oo *+B&^^|
            |       .  . +X&^^|
            |            +*^^^|
            |             *&^^|
            +-----------------+
        """.trimIndent()

        Assert.assertEquals(expected, fingerprintRandomart)
    }

    @Test
    fun `random art prints correctly - key 2`() {

        val fingerprintRandomart = RandomArt().fingerprintRandomArt(
            KEY2.map { it.code }
                .also {
                    println()
                }.toIntArray(),
            PrintConfig(
                """ .o+=*BOX@%&#/^SE""",
                """test""",
                """"""
            )
        )

        val expected = """
            +------test-------+
            |        .ooo*o*+o|
            |      . +B.@.@=+.|
            |       += @o&+X+B|
            |       Eo#+Xo@+B.|
            |      ..S=B oo= o|
            |       =*%*Xoo + |
            |      o+**%+. o  |
            |        *o o .   |
            |       +.o.      |
            +-----------------+
        """.trimIndent()

        Assert.assertEquals(expected, fingerprintRandomart)
    }

    companion object {
        const val KEY1 = """�0�0    *�H��""" +
                """��<��{������'�9:��ѦH��,����ä����ti�ov��t��L�Ɏ��ո�r�iV��w""" +
                """                                                        5v�7U(*E��|�g%D����6Q3���y�@�>-lm����8��c��t�0��|ku2�${"$"}p�����戎�����/�^�QȮf��9q\\����lY��UB+7��������e'�q�^;�k��e�O�g���Se�""" +
                """��!�����|��*�""" +
                """             ��V��,R)������ƋD�%m>��vuD�V�#?%                                    """
        const val KEY2 = "oiaubl4ealkjbcjkcavhebpiuo;nadoeflhbuakhbalifjaoielkjfnabhgldalkjelkab"
    }
}
