package dev.patbeagan1.ext

val Array<IntArray>.height: Int
    get() = this.size

val Array<IntArray>.width: Int
    get() = this.firstOrNull()?.size ?: 0