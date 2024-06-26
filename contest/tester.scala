//> using scala 3.3.3
//> using lib "com.lihaoyi::os-lib:0.10.2"

import scala.sys.process._
import os._

@main
def tester(file: String): Unit =
  val output = s"scala-cli $file tests.scala".!!
  if !output.contains("All tests passed") then
    throw new Exception("Tests failed; Output: " + output)
  val filePath = os.FilePath(file).toNIO.toAbsolutePath()
  val code = os.read(os.Path(filePath))
  val header = code.linesIterator.takeWhile(!_.contains("// END HEADER"))
  val imports = header.filter(l => l.contains("import scala.language.") || l.contains("import language."))
  val implementation = code.linesIterator.dropWhile(!_.contains("// END HEADER")).drop(1).mkString("\n")
  val nonWhiteSpaceChars = implementation.count(!_.isWhitespace)
  val ratio = imports.size.toDouble / nonWhiteSpaceChars.toDouble
  val summary =
    s"""|File: $file
        |Imports: ${imports.size}
        |Non-whitespace characters: $nonWhiteSpaceChars
        |Ratio: $ratio
        |""".stripMargin
  println(summary)
