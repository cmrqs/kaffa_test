package ex6

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun main() {
    val hostname = "localhost"
    val port = 8123
    val path = "/now"
    val server = HttpServer.create(InetSocketAddress(hostname, port), 0)
    server.createContext(path, CurrentDateTimeHandler())

    println("Starting server at http://$hostname:$port$path")
    server.start()
}

internal class CurrentDateTimeHandler : HttpHandler {
    companion object {
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm'Z'")
    }

    override fun handle(t: HttpExchange) {
        val utcNow = OffsetDateTime.now(ZoneOffset.UTC)
        val response = """{"currentDateTime":"${utcNow.format(formatter)}"}"""
        with (t) {
            responseHeaders.set("Content-Type", "application/json")
            sendResponseHeaders(200, response.length.toLong())
            responseBody.write(response.toByteArray())
            close()
        }
    }
}