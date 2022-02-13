package com.krazykraft.raknet.protocol

import com.krazykraft.raknet.readUInt24LE
import com.krazykraft.raknet.writeUInt24LE
import io.ktor.utils.io.*

abstract class AcknowledgePacket(id: UByte, val records: List<Record>) : Packet(id) {
    companion object {
        suspend fun decodePayload(input: ByteReadChannel): List<Record> {
            val count = input.readShort()
            val mutableRecords = mutableListOf<Record>()
            repeat(count.toInt()) {
                val record = if (input.readBoolean()) {
                    NoRangeRecord(input)
                } else {
                    RangeRecord(input)
                }
                mutableRecords += record
            }
            return mutableRecords
        }
    }

    override suspend fun encodePayload(output: ByteWriteChannel) {
        output.apply {
            writeShort(records.size)
            records.forEach {
                it.encode(output)
            }
        }
    }
}

sealed class Record {
    abstract suspend fun encode(output: ByteWriteChannel)
}

class NoRangeRecord(val sequenceNumber: UInt) : Record() {
    override suspend fun encode(output: ByteWriteChannel) {
        output.apply {
            writeBoolean(false)
            writeUInt24LE(sequenceNumber)
        }
    }
}

suspend fun NoRangeRecord(input: ByteReadChannel): NoRangeRecord {
    val sequenceNumber = input.readUInt24LE()
    return NoRangeRecord(sequenceNumber)
}

class RangeRecord(val startSequenceNumber: UInt, val endSequenceNumber: UInt) : Record() {
    override suspend fun encode(output: ByteWriteChannel) {
        output.apply {
            writeBoolean(true)
            writeUInt24LE(startSequenceNumber)
            writeUInt24LE(endSequenceNumber)
        }
    }
}

suspend fun RangeRecord(input: ByteReadChannel): RangeRecord {
    val startSequenceNumber = input.readUInt24LE()
    val endSequenceNumber = input.readUInt24LE()
    return RangeRecord(startSequenceNumber, endSequenceNumber)
}
