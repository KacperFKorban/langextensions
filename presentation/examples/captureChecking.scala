//> using scala 3.nightly
//> using options -feature -Xfatal-warnings
//> using options -source:future

import scala.language.experimental.captureChecking

class FileOutputStream(name: String):
  def close(): Unit = println(s"closing $name")
  def write(b: Int): Unit = println(s"writing $b to $name")

def usingLogFile[T](op: FileOutputStream^ => T): T =
  val logFile = FileOutputStream("log")
  val result = op(logFile)
  logFile.close()
  result

def usg6 =
  val later = usingLogFile { file => () => file.write(0) }
  later() // crash
