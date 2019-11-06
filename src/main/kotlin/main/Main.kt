package main

import com.google.zxing.qrcode.QRCodeWriter
import java.nio.file.FileSystems
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.client.j2se.MatrixToImageWriter



fun main(args: Array<String>) {
    testEncodingDecoding()
    generateQrCode()
}


fun testEncodingDecoding() {
    val stringToEncode = "ACBD"
    val sixBitArray: String = stringToEncode.toSixBitAsciiBinaryString(CHARSET.toList())
    val byteArray = sixBitArray.fromBinaryStringRepresentationToByteArray()
    val stringRepresentation: String = byteArray.toSixBitString(CHARSET.toList())
    println("SixbitArrayLength ${sixBitArray.length}")
    println("Original: $stringToEncode  encoded: $stringRepresentation")
    "11011001".fromBinaryStringRepresentationToByteArray()
    "11011001".fromBinaryStringRepresentationToByteArray().forEach { print(" ${it.toUByte().toString(2)} ")}
    println(sixBitArray)
    println()
    "11011001".fromBinaryStringRepresentationToByteArray().forEach { print(" ${it.toString(2)} ")}


    println()
    val decodedBinary = TEST_DATA.padEnd(216, '0').fromBinaryStringRepresentationToByteArray().toSixBitString(CHARSET.toList())
    val encodedBinary = decodedBinary.toSixBitAsciiBinaryString(CHARSET.toList())
    println(decodedBinary)
    println(encodedBinary)
    val big: Byte = 0b11111110.toByte()
    val small: Byte = -126
    val average: Byte = -6


    println("${big.toString(2)} ${small.toString(2)} ${average.toString(2)}")
    println("${big.toUByte().toString(2)} ${small.toUByte().toString(2)} ${average.toUByte().toString(2)}")


    val header = "339c4c40".toByteArray()
}

fun generateQrCode(){
    val input = "339c4f0040020000000000000000b405d94b8020280116b14002a4db8001b3a" +
            "8a48a51ba92cca0000000000000000000000000000000000029506EFF783EEB9710C4C2DBD320C84E65024377F0270849FA8E179E2FD8F17B354301F4BD1EEE3CA1243885EF599CD4A8719A188C1AAF26770B"
    val bytes = input.chunked(2).map { it.toInt(16).toByte() }
    val stringToWrite = String(bytes.toByteArray(), Charsets.ISO_8859_1)
    val bitMatrix = QRCodeWriter().encode( stringToWrite, BarcodeFormat.QR_CODE, 300, 300, mapOf(EncodeHintType.ERROR_CORRECTION to "Q"))
    val path = FileSystems.getDefault().getPath("/Users/okalman/Documents/generated_image.png")
    MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path)
}