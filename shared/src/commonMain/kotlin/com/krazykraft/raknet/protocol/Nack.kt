package com.krazykraft.raknet.protocol

import io.ktor.utils.io.*

class Nack(records: List<Record>) : AcknowledgePacket(0xa0u, records)

suspend fun Nack(input: ByteReadChannel): Nack {
    return Nack(AcknowledgePacket.decodePayload(input))
}
