package com.krazykraft.raknet.protocol

import com.krazykraft.raknet.Address
import com.krazykraft.raknet.readAddress
import com.krazykraft.raknet.writeAddress
import io.ktor.utils.io.*

class ConnectionRequestAccepted(
    val clientAddress: Address,
    val systemIndex: Short,
    val internalIds: Address,
    val requestTime: Long,
    val time: Long
) : Packet(0x10u) {
    override suspend fun encodePayload(output: ByteWriteChannel) {
        output.apply {
            writeAddress(clientAddress)
            writeShort(systemIndex)
            writeAddress(internalIds)
            writeLong(requestTime)
            writeLong(time)
        }
    }
}

suspend fun ConnectionRequestAccepted(input: ByteReadChannel): ConnectionRequestAccepted {
    val clientAddress = input.readAddress()
    val systemIndex = input.readShort()
    val internalIds = input.readAddress()
    val requestTime = input.readLong()
    val time = input.readLong()

    return ConnectionRequestAccepted(clientAddress, systemIndex, internalIds, requestTime, time)
}
