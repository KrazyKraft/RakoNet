package com.krazykraft.raknet.protocol

import io.ktor.utils.io.*

class Ack(records: List<Record>) : AcknowledgePacket(0xc0u, records)

suspend fun Ack(input: ByteReadChannel): Ack {
    return Ack(AcknowledgePacket.decodePayload(input))
}
