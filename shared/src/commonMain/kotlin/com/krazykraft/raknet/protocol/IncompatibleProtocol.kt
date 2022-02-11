package com.krazykraft.raknet.protocol

import com.krazykraft.raknet.*
import io.ktor.utils.io.*

class IncompatibleProtocol(
    val protocol: UByte,
    val magic: Magic,
    val serverGuid: Long
) : Packet(0x19u) {
    override suspend fun encodePayload(output: ByteWriteChannel) {
        output.apply {
            writeUByte(protocol)
            writeMagic(magic)
            writeLong(serverGuid)
        }
    }
}

suspend fun IncompatibleProtocol(input: ByteReadChannel): IncompatibleProtocol {
    val protocol = input.readUByte()
    val magic = input.readMagic()
    val serverGuid = input.readLong()

    return IncompatibleProtocol(protocol, magic, serverGuid)
}
