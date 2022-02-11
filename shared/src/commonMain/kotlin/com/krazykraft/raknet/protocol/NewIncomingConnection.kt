package com.krazykraft.raknet.protocol

import com.krazykraft.raknet.Address
import com.krazykraft.raknet.readAddress
import com.krazykraft.raknet.writeAddress
import io.ktor.utils.io.*

class NewIncomingConnection(
    val serverAddress: Address,
    val internalAddress: Address
) : Packet(0x13u) {
    override suspend fun encodePayload(output: ByteWriteChannel) {
        output.apply {
            writeAddress(serverAddress)
            writeAddress(internalAddress)
        }
    }
}

suspend fun NewIncomingConnection(input: ByteReadChannel): NewIncomingConnection {
    val serverAddress = input.readAddress()
    val internalAddress = input.readAddress()
    return NewIncomingConnection(serverAddress, internalAddress)
}
