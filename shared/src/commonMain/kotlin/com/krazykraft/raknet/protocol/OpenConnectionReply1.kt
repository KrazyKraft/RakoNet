package com.krazykraft.raknet.protocol

import com.krazykraft.raknet.Magic
import com.krazykraft.raknet.readMagic
import com.krazykraft.raknet.writeMagic
import io.ktor.utils.io.*

class OpenConnectionReply1(
    val magic: Magic,
    val serverGuid: Long,
    val useSecurity: Boolean,
    val mtu: Short
) : Packet(0x06u) {
    override suspend fun encodePayload(output: ByteWriteChannel) {
        output.apply {
            writeMagic(magic)
            writeLong(serverGuid.toLong())
            writeBoolean(useSecurity)
            writeShort(mtu.toShort())
        }
    }
}

suspend fun OpenConnectionReply1(input: ByteReadChannel): OpenConnectionReply1 {
    val magic = input.readMagic()
    val serverGuid = input.readLong()
    val useSecurity = input.readBoolean()
    val mtu = input.readShort()
    return OpenConnectionReply1(
        magic, serverGuid, useSecurity, mtu
    )
}
