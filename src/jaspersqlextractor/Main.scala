package pl.stachjankowski.jaspersqlextractor

import net.sf.jasperreports.engine.JasperReport
import java.io._

object Main {
  def main(args: Array[String]): Unit = {
    if ( args.size == 0 ) usage()

    val sourcePath = args{0}
    try {
      println(getQuery(getJasperReport(sourcePath)))
    } catch {
      case e: java.io.FileNotFoundException =>
        println("Error: File " + sourcePath + " not found")
        sys.exit(1)
      case e: java.lang.NullPointerException =>
        println("Error: No sql in " + sourcePath)
        sys.exit(2)
    }
  }

  def getJasperReport(sourcePath: String): JasperReport = {
    val fileIn = new FileInputStream(sourcePath)
    val in = new ObjectInputStream(fileIn)
    val report = in.readObject().asInstanceOf[JasperReport]
    in.close()
    fileIn.close()
    report
  }

  def getQuery(report: JasperReport) = report.getQuery().getText()

  def usage(): Unit = {
    println("usage:")
    println("java -jar ./JasperSQLExtractor.jar some_report.jasper")
    println("author: Stanisław Jankowski <stach.jankowski@gmail.com>")
    sys.exit(1)
  }
}
