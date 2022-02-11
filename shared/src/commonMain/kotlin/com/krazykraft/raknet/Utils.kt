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
