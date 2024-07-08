import scala.quoted.*

def fromDigitsImpl(digits: Expr[String])(using Quotes): Expr[MyInt] =
  import quotes.reflect.*
  digits.value match
    case Some(ds) if ds.contains('0') =>
      report.errorAndAbort("MyInt does not support digits with 0")
    case Some(ds) =>
      '{MyInt(${Expr(ds.toInt)})}
    case None =>
      '{MyInt($digits)}