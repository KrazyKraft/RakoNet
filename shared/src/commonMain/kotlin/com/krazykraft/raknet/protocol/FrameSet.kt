package com.krazykraft.raknet.protocol

import com.krazykraft.raknet.readUInt24LE
import com.krazykraft.raknet.writeUByte
import com.krazykraft.raknet.writeUInt24LE
import com.krazykraft.raknet.writeUShort
import io.ktor.utils.io.*

class FrameSet(val sequenceNumber: UInt, val frames: List<Frame>) : Packet(0x80u) {
    override suspend fun encodePayload(output: ByteWriteChannel) {
        output.writeUInt24LE(sequenceNumber)
        frames.forEach {
            it.encode(output)
        }
    }
}

suspend fun FrameSet(input: ByteReadChannel): FrameSet {
    val sequenceNumber = input.readUInt24LE()
    TODO("Not yet implemented.")
}

class Frame(
    val reliability: Reliability,
    val isFragmented: Boolean,
    val reliableFrameIndex: UInt,
    val sequencedFrameIndex: UInt,
    val orderedFrameIndex: UInt,
    val orderChannel: UByte,
    val compoundSize: UInt,
    val compoundId: UShort,
    val index: UInt,
    val body: ByteArray
) {
    suspend fun encode(output: ByteWriteChannel) {
        output.apply {
            writeByte((reliability.flag shl 5 or if (isFragmented) 1 else 0 shl 4).toByte())
            writeShort(body.size shl 3)

            if (reliability.isReliable) {
                writeUInt24LE(reliableFrameIndex)
            }
            if (reliability.isSequenced) {
                writeUInt24LE(sequencedFrameIndex)
            }
            if (reliability.isOrdered) {
                writeUInt24LE(orderedFrameIndex)
                writeUByte(orderChannel)
            }

            if (isFragmented) {
                writeInt(compoundSize.toInt())
                writeUShort(compoundId)
                writeInt(index.toInt())
            }

            writeFully(body)
        }
    }
}

enum class Reliability(val flag: Int) {
    UNRELIABLE(0),
    UNRELIABLE_SEQUENCED(1),
    RELIABLE(2),
    RELIABLE_ORDERED(3),
    RELIABLE_SEQUENCED(4),
    UNRELIABLE_WITH_ACK_RECEIPT(5),
    RELIABLE_WITH_ACK_RECEIPT(6),
    RELIABLE_ORDERED_WITH_ACK_RECEIPT(7);

    val isReliable: Boolean
        get() = this in listOf(
            UNRELIABLE,
            RELIABLE_ORDERED,
            RELIABLE_SEQUENCED,
            RELIABLE_WITH_ACK_RECEIPT,
            RELIABLE_ORDERED_WITH_ACK_RECEIPT
        )

    val isOrdered: Boolean
        get() = this in listOf(
            UNRELIABLE_SEQUENCED,
            RELIABLE_ORDERED,
            RELIABLE_SEQUENCED,
            RELIABLE_ORDERED_WITH_ACK_RECEIPT
        )

    val isSequenced: Boolean
        get() = this in listOf(UNRELIABLE_SEQUENCED, RELIABLE_SEQUENCED)
}
