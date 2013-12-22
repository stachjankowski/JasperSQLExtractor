package jaspersqlextractor

import net.sf.jasperreports.engine.util.JRLoader
import net.sf.jasperreports.engine.{JasperReport, JRException}

object Main {
  def main(args: Array[String]): Unit = {
    if ( args.size == 0 ) usage()

    val sourcePath = args{0}
    var report : JasperReport = null
    try {
      report = JRLoader.loadObjectFromFile(sourcePath).asInstanceOf[JasperReport]
    } catch {
      case e: JRException =>
        println("File " + sourcePath + " is not valid jasper!")
        sys.exit(1)
    }

    val query = report.getQuery()
    if (query != null) println(query.getText())
    sys.exit(0)
  }

  def usage(): Unit = {
    println("usage:")
    println("java -jar ./JasperSQLExtractor.jar some_report.jasper")
    println("author: Stanisław Jankowski <stach.jankowski@gmail.com>")
    sys.exit(1)
  }
}
