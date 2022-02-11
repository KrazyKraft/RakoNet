@file:OptIn(DangerousInternalIoApi::class)

package com.krazykraft.raknet.protocol

import com.krazykraft.raknet.writeUByte
import io.ktor.utils.io.*
import io.ktor.utils.io.core.internal.*

abstract class Packet(val id: UByte) {
    suspend fun encode(output: ByteWriteChannel) {
        encodeHeader(output)
        encodePayload(output)
    }

    suspend fun encodeHeader(output: ByteWriteChannel) {
        output.writeUByte(id)
    }

    abstract suspend fun encodePayload(output: ByteWriteChannel)
}
