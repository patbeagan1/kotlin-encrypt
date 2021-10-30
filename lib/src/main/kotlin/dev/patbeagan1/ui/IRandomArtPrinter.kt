package dev.patbeagan1.ui

interface IRandomArtPrinter {
    fun format(
        field: Array<IntArray>,
        augmentationString: String,
        header: String,
        footer: String
    ): String

    fun getFieldConfig(): FieldConfig = FieldConfig()
}