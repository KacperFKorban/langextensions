TODO: check which ones work in Scala 3 and only keep those

List of extensions:
- dynamics
- implicitConversions
- postfixOps
- noAutoTupling
- strictEquality
- adhocExtensions

Check these:
- unsafeNulls
- reflectiveCalls

Experimental:
- namedTypeArguments
- genericNumberLiterals
- erasedDefinitions
- saferExceptions
- clauseInterleaving
- pureFunctions
- captureChecking
- into
- namedTuples
- modularity // might add an example about new type classes
- betterMatchTypeExtractors
- betterFors

### Chosen

Short:
- namedTypeArguments
- genericNumberLiterals
- erasedDefinitions
- clauseInterleaving
- pureFunctions (mention captureChecking)

Medium:
- into (requires introducing implicitConversions, since it's supposed to be an alternative to it)

Long:
- namedTuples
- modularity

Future:
- betterFors
- alternativeBindPatterns
- multipleAssignments

Bonus:
- unary assign



Template slide:

\begin{frame}[fragile]
  \frametitle{namedTypeArguments}
  \begin{itemize}
    \item \textbf{Feature Name:} namedTypeArguments
    \item \textbf{Import:} \texttt{import scala.language.experimental.namedTypeArguments}
    \item \textbf{TLDR:} This feature allows you to specify type arguments of functions by name.
  \end{itemize}
\end{frame}

\begin{frame}[fragile]
  \frametitle{namedTypeArguments --- Motivation}

  Motivation code/problem description
  
\end{frame}
