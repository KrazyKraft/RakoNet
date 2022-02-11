@file:OptIn(DangerousInternalIoApi::class)

package com.krazykraft.raknet.protocol

import com.krazykraft.raknet.*
import io.ktor.utils.io.*
import io.ktor.utils.io.core.internal.*

class OpenConnectionRequest1(
    val magic: Magic,
    val protocolVersion: UByte,
    val utm: Short
) : Packet(0x06u) {
    override suspend fun encodePayload(output: ByteWriteChannel) {
        output.apply {
            writeMagic(magic)
            writeUByte(protocolVersion)
            writeShort(utm)
        }
    }
}

suspend fun OpenConnectionRequest1(input: ByteReadChannel): OpenConnectionRequest1 {
    val magic = input.readMagic()
    val protocolVersion = input.readUByte()
    val utm = input.readShort()
    return OpenConnectionRequest1(magic, protocolVersion, utm)
}
