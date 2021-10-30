package dev.patbeagan.ui

internal class RandomArt(private val randomArtPrinter: IRandomArtPrinter = RandomArtPrinter()) {

    fun fingerprintRandomArt(
        digestRaw: IntArray,
        augmentationString: String = """ .o+=*BOX@%&#/^SE""",
        title: String,
        hash: String
    ): String = randomArtPrinter.format(
        fillField(augmentationString, digestRaw, FieldConfig()),
        title,
        augmentationString,
        hash
    )

    /**
     * Adapted from C code in
     * https://github.com/openssh/openssh-portable/blob/ef5916b8acd9b1d2f39fad4951dae03b00dbe390/sshkey.c#L1157
     * licensed under https://choosealicense.com/licenses/bsd-2-clause/
     */
    private fun fillField(
        augmentationString: String,
        digestRaw: IntArray,
        fieldConfig: FieldConfig
    ): Array<IntArray> {

        val field = Array(fieldConfig.y) { IntArray(fieldConfig.x) }

        var y = fieldConfig.y / 2
        var x = fieldConfig.x / 2
        val len = augmentationString.length - 1

        /* process raw key */
        for (element in digestRaw) {
            var input: Int
            /* each byte conveys four 2-bit move commands */
            input = element
            for (b in 0 until 4) {
                /* evaluate 2 bit, rest is shifted later */
                y += if (input and 0x1 != 0) 1 else -1
                x += if (input and 0x2 != 0) 1 else -1

                /* assure we are still in bounds */
                y = y.coerceAtLeast(0)
                x = x.coerceAtLeast(0)
                y = y.coerceAtMost(fieldConfig.y - 1)
                x = x.coerceAtMost(fieldConfig.x - 1)

                /* augment the field */
                if (field[y][x] < len - 2) {
                    field[y][x]++
                }
                input = input shr 2
            }
        }

        /* mark starting point and end point*/
        field[fieldConfig.y / 2][fieldConfig.x / 2] = len - 1
        field[y][x] = len
        return field
    }
}