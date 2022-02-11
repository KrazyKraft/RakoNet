@file:OptIn(DangerousInternalIoApi::class)

package com.krazykraft.raknet.protocol

import com.krazykraft.raknet.Magic
import com.krazykraft.raknet.writeMagic
import io.ktor.utils.io.*
import io.ktor.utils.io.core.internal.*

class UnconnectedPing(
    val time: Long,
    val magic: Magic,
    val clientGuid: Long,
) : Packet(0x01u) {
    override suspend fun encodePayload(output: ByteWriteChannel) {
        output.apply {
            writeLong(time)
            writeMagic(magic)
            writeLong(clientGuid)
        }
    }
}
