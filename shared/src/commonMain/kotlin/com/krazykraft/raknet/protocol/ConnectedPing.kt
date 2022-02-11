@file:OptIn(DangerousInternalIoApi::class)

package com.krazykraft.raknet.protocol

import io.ktor.utils.io.*
import io.ktor.utils.io.core.internal.*

class ConnectedPing(val time: Long) : Packet(0x00u) {
    companion object {
        suspend fun decode(input: ByteReadChannel): ConnectedPing {
            return ConnectedPing(input.readLong())
        }
    }

    override suspend fun encodePayload(output: ByteWriteChannel) {
        output.writeLong(time)
    }
}
