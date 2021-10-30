package dev.patbeagan.ui

data class FieldConfig(
    val fieldBaseNumber: Int = 8
) {
    val x = fieldBaseNumber + 1
    val y = fieldBaseNumber * 2 + 1
}