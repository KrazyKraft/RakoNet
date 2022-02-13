@file:OptIn(DangerousInternalIoApi::class)

package com.krazykraft.raknet

import io.ktor.utils.io.*
import io.ktor.utils.io.core.internal.*

typealias Magic = Array<UByte>

sealed class Address

class AddressV4(val address: String, val port: UShort) : Address()

class AddressV6(val address: String, val port: UShort) : Address()

suspend fun ByteWriteChannel.writeMagic(magic: Magic) {
}

suspend fun ByteWriteChannel.writeAddress(address: Address) {
}

suspend fun ByteWriteChannel.writeUByte(ubyte: UByte) {
    writeByte(ubyte.toByte())
}

suspend fun ByteWriteChannel.writeUShort(ushort: UShort) {
    writeShort(ushort.toShort())
}

suspend fun ByteWriteChannel.writeUInt24LE(uInt: UInt) {
    val first = (uInt shr 4 and 0xFFu).toByte()
    val second = (uInt shr 2 and 0xFFu).toByte()
    val third = (uInt and 0xFFu).toByte()
    writeByte(third)
    writeByte(second)
    writeByte(first)
}

suspend fun ByteReadChannel.readMagic(): Magic {
    TODO("Not yet implemented.")
}

suspend fun ByteReadChannel.readAddress(): Address {
    TODO("Not yet implemented.")
}

suspend fun ByteReadChannel.readUByte(): UByte {
    return readByte().toUByte()
}

suspend fun ByteReadChannel.readUShort(): UShort {
    return readShort().toUShort()
}

suspend fun ByteReadChannel.readUInt24LE(): UInt {
    val first = readByte().toInt()
    val second = readByte().toInt()
    val third = readUByte().toInt()
    return (third shl 4 or second shl 2 or first and 0xFFFFFF).toUInt()
}
