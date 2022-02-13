package com.krazykraft.raknet.protocol

import io.ktor.utils.io.*

class GamePacket(body: ByteArray) : Packet(0xfeu) {
    override suspend fun encodePayload(output: ByteWriteChannel) {
        TODO("Not yet implemented")
    }
}
