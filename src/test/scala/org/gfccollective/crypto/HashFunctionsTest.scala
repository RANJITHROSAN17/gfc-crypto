package org.gfccollective.crypto

import org.scalatestplus.scalacheck.Checkers
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/**
 * Tests to confirm some basic hash scenarios are working.
 */
class HashFunctionsTest extends AnyFunSuite with Checkers with Matchers {

  import HashFunctions._

  test("basics") {
    check {
      (str: String) =>
          sha256(str) == sha256(str, "UTF-8") &&
            sha512(str) == sha512(str, "UTF-8") &&
            sha256(str) != sha512(str)
    }
  }

  test("known hashes") {
    // some known hashes from http://en.wikipedia.org/wiki/SHA-2
    assert(sha256Str("") == "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855")
    assert(sha256Str("", "ISO-8859-1") == "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855")
    assert(sha256Str("", "US-ASCII") == "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855")

    assert(sha256Str("The quick brown fox jumps over the lazy dog") == "d7a8fbb307d7809469ca9abcb0082e4f8d5651e46d3cdb762d02d0bf37c9e592")
    assert(sha256Str("The quick brown fox jumps over the lazy dog", "US-ASCII") == "d7a8fbb307d7809469ca9abcb0082e4f8d5651e46d3cdb762d02d0bf37c9e592")
    assert(sha256Str("The quick brown fox jumps over the lazy dog", "ISO-8859-1") == "d7a8fbb307d7809469ca9abcb0082e4f8d5651e46d3cdb762d02d0bf37c9e592")

    assert(sha512Str("") == "cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e")
    assert(sha512Str("", "ISO-8859-15") == "cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e")

    sha512Str("The quick brown fox jumps over the lazy dog") should equal("07e547d9586f6a73f73fbac0435ed76951218fb7d0c8d788a309d785436bbb642e93a252a954f23912547d1e8a3b5ed6e1bfd7097821233fa0538f3db854fee6")
    sha512Str("The quick brown fox jumps over the lazy dog", "ISO-8859-1") should equal("07e547d9586f6a73f73fbac0435ed76951218fb7d0c8d788a309d785436bbb642e93a252a954f23912547d1e8a3b5ed6e1bfd7097821233fa0538f3db854fee6")
  }
}
