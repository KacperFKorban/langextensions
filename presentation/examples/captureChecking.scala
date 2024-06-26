//> using scala 3.nightly
//> using options -feature -Xfatal-warnings
//> using options -source:future

import scala.language.experimental.captureChecking

class FileOutputStream(file: String) {
  def close(): Unit = ???
  def write[A](a: A): Unit = ???
}

def usingLogFile[T](op: FileOutputStream^ => T): T =
  val logFile = FileOutputStream("log")
  val result = op(logFile)
  logFile.close()
  result

def logFileUsage = usingLogFile { f => () => f.write(0) }
