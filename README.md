# kotlin-encrypt
An easy keystore wrapper written in kotlin - use for encrypting files

Still alpha level - don't use in production!

Simple example:
```kotlin
    val encryptor = Encryptor()
    val encrypt: ByteArray = encryptor.encrypt("your string here".toByteArray())
    val decrypt: ByteArray = encryptor.decrypt(encrypt)
    println(decrypt.toString(Charsets.UTF_8)) // "your string here"
```
