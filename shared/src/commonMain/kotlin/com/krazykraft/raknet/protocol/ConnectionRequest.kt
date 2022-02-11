package com.krazykraft.raknet.protocol

import io.ktor.utils.io.*

class ConnectionRequest(val guid: Long, val time: Long) : Packet(0x09u) {
    override suspend fun encodePayload(output: ByteWriteChannel) {
        output.apply {
            writeLong(guid)
            writeLong(time)
        }
    }
}

suspend fun ConnectionRequest(input: ByteReadChannel): ConnectionRequest {
    val guid = input.readLong()
    val time = input.readLong()

    return ConnectionRequest(guid, time)
}
