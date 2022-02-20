package com.krazykraft.raknet

import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.util.*
import io.ktor.util.network.*
import io.ktor.utils.io.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RakNetSocket(val networkAddress: NetworkAddress, coroutineContext: CoroutineContext) : CoroutineScope by CoroutineScope(coroutineContext) {
    @OptIn(InternalAPI::class)
    val socketBuilder = aSocket(SelectorManager(Dispatchers.Default)).udp()

    var _readChannel: ByteReadChannel? = null
    val readChannel: ByteReadChannel
        get() {
            _readChannel ?: throw IllegalStateException("")
        }

    var _writeChannel: ByteWriteChannel? = null
    val writeChannel: ByteWriteChannel
        get() {
            _writeChannel ?: throw IllegalStateException("")
        }

    fun start() {
        val socket = socketBuilder.bind(networkAddress)
        readChannel = socket.openReadChannel()
        launch {
            while (!socket.isClosed) {

            }
        }
        val datagram =
    }

    suspend fun
}
