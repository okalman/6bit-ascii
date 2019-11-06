package main

/**
 * This method converts byte array to binary string representation (eg."00101010")
 * Example: @param dictionary is: ' ','A','B','C','D','E'...
 * ByteArray is [4,48,-124]  in binary code :
 * result of this function "ABCD"
 */

fun ByteArray.toSixBitString(dictionary: List<Char>): String {
    require(dictionary.size == 64) { "Dictionary have to be 64 chars long" }

    // convert bytes to bits (eg."00101010") string representation
    val binaryStringRepresentation: String = joinToString("", "", "", -1, "") {
        // we need to align each byte to 8 bit string representation thus using padStart
        it.toUByte().toString(2).padStart(8, '0')
    }
    "".length

    // split to six bit(0-63) numbers and convert them to character in dictionary
    return binaryStringRepresentation.chunked(6).map { it.toUByte(2) }.map {
        dictionary[it.toInt()].toString()
    }.joinToString("", "", "", -1, "")
}

fun String.toSixBitAsciiBinaryString(dictionary: List<Char>): String {
    require(dictionary.size == 64) { "Dictionary have to be 64 chars long" }
    // convert every character in String to number representation based on its index in directory
    val byteStringRepresentation: List<Byte> = this.toCharArray().map {
        val byteRepresentation = dictionary.indexOf(it)
        check(byteRepresentation != -1) { "Char $it not in dictionary" }
        byteRepresentation.toByte()
    }

    // convert bytes to bits(eg."01010101") string representation

    return byteStringRepresentation
        .joinToString("", "", "", -1, "") {
            //we need to align each byte to 6 bit string representation thus using padStart
            it.toString(2).padStart(6, '0')
        }
}

fun String.fromBinaryStringRepresentationToByteArray(): ByteArray {
    check(this.length.rem(8) == 0) { "Not dividable by 8 remainder ${this.length.rem(8)}" }
    check(!this.matches("[01]+".toRegex()).not()) { "Not binaryString representation: $this" }
    return this.chunked(8).map { it.toUByte(2).toByte() }.toByteArray()
}

fun Int.toBinaryStringRepresentation(desiredBitLength: Int): String{
    // convert bytes to bits(eg."01010101") string representation
    val binaryStringRepresentation = this.toString(2).padStart(Int.SIZE_BITS, '0')
    return binaryStringRepresentation.slice(binaryStringRepresentation.length-desiredBitLength until binaryStringRepresentation.length)
}