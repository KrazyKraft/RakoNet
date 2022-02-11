@file:OptIn(DangerousInternalIoApi::class)

package com.krazykraft.raknet.protocol

import com.krazykraft.raknet.Magic
import com.krazykraft.raknet.writeMagic
import io.ktor.utils.io.*
import io.ktor.utils.io.core.internal.*

class UnconnectedPong(
    val time: Long,
    val serverId: Long,
    val magic: Magic,
    val serverIdString: String
) : Packet(0x1cu) {
    override suspend fun encodePayload(output: ByteWriteChannel) {
        output.apply {
            writeLong(time)
            writeLong(serverId)
            writeMagic(magic)
            writeStringUtf8(serverIdString)
        }
    }
}
