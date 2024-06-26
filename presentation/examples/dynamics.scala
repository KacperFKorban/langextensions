//> using scala 3.nightly

import scala.language.dynamics

class MyDynamic extends Dynamic:
  def selectDynamic(name: String): String = name
