package dev.patbeagan.ui

interface IRandomArtPrinter {
    fun format(
        field: Array<IntArray>,
        title: String,
        augmentationString: String,
        hash: String
    ): String
}