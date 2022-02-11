package com.krazykraft.raknet.protocol

import io.ktor.utils.io.*

class Disconnect : Packet(0x15u) {
    override suspend fun encodePayload(output: ByteWriteChannel) {
    }
}
