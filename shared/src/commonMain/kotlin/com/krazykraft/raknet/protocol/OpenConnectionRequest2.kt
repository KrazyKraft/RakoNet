@file:OptIn(DangerousInternalIoApi::class)

package com.krazykraft.raknet.protocol

import com.krazykraft.raknet.*
import io.ktor.utils.io.*
import io.ktor.utils.io.core.internal.*

class OpenConnectionRequest2(
    val magic: Magic,
    val serverAddress: Address,
    val utm: Short,
    val clientGuid: Long
) : Packet(0x07u) {
    override suspend fun encodePayload(output: ByteWriteChannel) {
        output.apply {
            writeMagic(magic)
            writeAddress(serverAddress)
            writeShort(utm)
        }
    }
}

suspend fun OpenConnectionRequest2(input: ByteReadChannel): OpenConnectionRequest2 {
    val magic = input.readMagic()
    val serverAddress = input.readAddress()
    val utm = input.readShort()
    val clientGuid = input.readLong()
    return OpenConnectionRequest2(magic, serverAddress, utm, clientGuid)
}
