package dev.patbeagan1.ui

import dev.patbeagan1.ext.height
import dev.patbeagan1.ext.width

internal class RandomArtPrinter : IRandomArtPrinter {
    override fun format(
        field: Array<IntArray>,
        augmentationString: String,
        header: String,
        footer: String
    ): String {
        val pp = StringBuilder()
        pp.appendTextRow(field, header)
        pp.append('\n')
        pp.appendContent(augmentationString, field)
        pp.appendTextRow(field, footer)
        return pp.toString()
    }

    private fun StringBuilder.appendContent(
        augmentationString: String,
        field: Array<IntArray>
    ) {
        for (x in 0 until field.width) {
            append('|')
            for (y in 0 until field.height) {
                append(augmentationString[field[y][x].coerceAtMost(augmentationString.length - 1)])
            }
            append('|')
            append('\n')
        }
    }

    private fun StringBuilder.appendTextRow(field: Array<IntArray>, string: String) {
        val stringStart = (field.height - string.length) / 2
        val beforeTitle = 0 until stringStart
        val afterTitle = (stringStart + string.length) until field.height

        append('+')
        repeat(beforeTitle.count()) { append('-') }
        append(string)
        repeat(afterTitle.count()) { append('-') }
        append('+')
    }
}