package com.krazykraft.raknet.protocol

import io.ktor.utils.io.*

class ConnectedPong(val pingTime: Long, val pongTime: Long) : Packet(0x03u) {
    override suspend fun encodePayload(output: ByteWriteChannel) {
        output.apply {
            writeLong(pingTime)
            writeLong(pongTime)
        }
    }
}

suspend fun ConnectedPong(input: ByteReadChannel): ConnectedPong {
    return ConnectedPong(input.readLong(), input.readLong())
}
