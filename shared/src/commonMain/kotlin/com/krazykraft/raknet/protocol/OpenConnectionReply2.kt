@file:OptIn(DangerousInternalIoApi::class)

package com.krazykraft.raknet.protocol

import com.krazykraft.raknet.Address
import com.krazykraft.raknet.Magic
import com.krazykraft.raknet.writeAddress
import com.krazykraft.raknet.writeMagic
import io.ktor.utils.io.*
import io.ktor.utils.io.core.internal.*

class OpenConnectionReply2(
    val magic: Magic,
    val serverId: Long,
    val clientAddress: Address,
    val mtu: Short,
    val security: Boolean
) : Packet(0x08u) {
    override suspend fun encodePayload(output: ByteWriteChannel) {
        output.apply {
            writeMagic(magic)
            writeLong(serverId)
            writeAddress(clientAddress)
            writeShort(mtu)
            writeBoolean(security)
        }
    }
}
