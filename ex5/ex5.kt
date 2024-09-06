package ex5

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun main() {
    println("Requesting current time, please wait...")

    val url = "http://worldclockapi.com/api/json/utc/now"
    val request = HttpRequest.newBuilder()
        .uri(URI(url))
        .GET()
        .build()
    val response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString())

    val status = response.statusCode()
    if (status == 200) {
        val regDate = Regex(""""currentDateTime":"([^"]*)"""")
        val dateTimeStr = regDate.find(response.body())!!.groupValues[1].removeSuffix("Z")
        val utcDateTime = LocalDateTime.parse(dateTimeStr).atOffset(ZoneOffset.UTC)
        val localDateTime = utcDateTime.atZoneSameInstant(ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

        println("  UTC time: ${utcDateTime.format(formatter)}")
        println("Local time: ${localDateTime.format(formatter)}")
    } else
        println("[!] Error (status code $status). Please try again later")
}