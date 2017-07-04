import scala.io.Source
import edu.holycross.shot.scm._
import java.io.File


val v1dir  = "../../libraries/CEX_1.0/"
// collect all .cex files in libraries directory:
val libraryDir = new File(v1dir)
val fileVector = libraryDir.listFiles.filter(_.isFile).toVector
val cexFiles = fileVector.filter(_.getName.endsWith("cex"))

var count = 0
println(s"""\n\nfiles to validate: ${cexFiles.size}\n""")

for (cex <- cexFiles) {
  count = count + 1
  try {
    val cexData = Source.fromFile(v1dir + cex.getName).getLines.mkString("\n")
    val citeLib = CiteLibrary(cexData,"#",",")
    println(s"""${count}. ${cex.getName} validates.""")
  } catch {
    case exc : Throwable => println(cex.getName + " fails:  " + exc.getMessage() + "\n\n")
  }
}