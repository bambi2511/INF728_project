//
// Run In Spark-shell
//
import org.bson.Document
import org.bson.BsonArray
import org.bson.BsonString
import org.bson.BsonDouble
import com.mongodb.spark.MongoSpark
import org.apache.spark.input.PortableDataStream
import java.util.zip.ZipInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.File
import org.apache.commons.lang.StringUtils


def extractActor1(array: Array[String]) : Document = {
   var actor1 = new Document().append("code", array(5)).append("name", array(6)).append("countryCode", array(7))
                .append("knownGroupCode", array(8)).append("ethnicCode", array(9))
   
   if(StringUtils.isNotEmpty(array(10))) {
      var religionCodes = new BsonArray()
      religionCodes.add(new BsonString(array(10)))
      if(StringUtils.isNotEmpty(array(11))) religionCodes.add(new BsonString(array(11)))
      actor1.append("religionCodes", religionCodes)
   }

   if(StringUtils.isNotEmpty(array(12))) {
      var typeCodes = new BsonArray()
      typeCodes.add(new BsonString(array(12)))
      if(StringUtils.isNotEmpty(array(13))) typeCodes.add(new BsonString(array(13)))
      if(StringUtils.isNotEmpty(array(14))) typeCodes.add(new BsonString(array(14)))
      actor1.append("typeCode", typeCodes)
   }
   var geoActor1 = new Document()
   if(StringUtils.isNotEmpty(array(35))) {
      geoActor1.append("type", array(35).toInt)
   }
   geoActor1.append("fullname", array(36)).append("countryCode", array(37)).append("ADM1Code", array(38)).append("ADM2Code", array(39))
   if(StringUtils.isNotEmpty(array(40)) && StringUtils.isNotEmpty(array(41))) {
      var coordinates = new BsonArray()
      coordinates.add(new BsonDouble(array(40).toFloat))
      coordinates.add(new BsonDouble(array(41).toFloat))
      geoActor1.append("coordinates", coordinates)
   }
   
   return actor1.append("geography", geoActor1)
}

def extractActor2(array: Array[String]) : Document = {
   var actor2 = new Document().append("code", array(15)).append("name", array(16)).append("countryCode", array(17))
                .append("knownGroupCode", array(18)).append("knownGroupCode", array(19))

   if(StringUtils.isNotEmpty(array(20))) {
      var religionCodes = new BsonArray()
      religionCodes.add(new BsonString(array(20)))
      if(StringUtils.isNotEmpty(array(21))) religionCodes.add(new BsonString(array(21)))
      actor2.append("religionCodes", religionCodes)
   }
   if(StringUtils.isNotEmpty(array(22))) {
      var typeCodes = new BsonArray()
      typeCodes.add(new BsonString(array(22)))
      if(StringUtils.isNotEmpty(array(23))) typeCodes.add(new BsonString(array(23)))
      if(StringUtils.isNotEmpty(array(24))) typeCodes.add(new BsonString(array(24)))
      actor2.append("typeCode", typeCodes)
   }

   var geoActor2 = new Document()
   if(StringUtils.isNotEmpty(array(43))) {
      geoActor2.append("type", array(43).toInt)
   }
   geoActor2.append("fullname", array(44)).append("countryCode", array(45)).append("ADM1Code", array(46)).append("ADM2Code", array(47))
   if(StringUtils.isNotEmpty(array(48)) && StringUtils.isNotEmpty(array(49))) {
      var coordinates = new BsonArray()
      coordinates.add(new BsonDouble(array(48).toFloat))
      coordinates.add(new BsonDouble(array(49).toFloat))
      geoActor2.append("coordinates", coordinates)
   }

   return actor2.append("geography", geoActor2)
}

def extractActionGeo(array: Array[String]) : Document = {
   var actionGeo = new Document().append("type", array(51).toInt).append("fullname", array(52)).append("countryCode", array(53))
                .append("ADM1Code", array(54)).append("ADM2Code", array(55))
   if(StringUtils.isNotEmpty(array(56)) && StringUtils.isNotEmpty(array(57))) {
      var coordinates = new BsonArray()
      coordinates.add(new BsonDouble(array(56).toFloat))
      coordinates.add(new BsonDouble(array(57).toFloat))
      actionGeo.append("coordinates", coordinates)
   }
   return actionGeo
}

def convertExportRowToJSON (array: Array[String]) : Document = {
   var doc = new Document().append("globalEventId", array(0).toInt).append("day", array(1).toInt).append("monthyear", array(2).toInt).append("year", array(3).toInt)
   if(StringUtils.isNotEmpty(array(4))) {
         doc.append("fractionDate", array(4).toFloat)
   }
   doc.append("isRootEvent", array(25).toInt).append("eventCode", array(26)).append("eventBaseCode", array(27)).append("eventRootCode", array(28)).append("quadClass", array(29).toInt)
   if(StringUtils.isNotEmpty(array(30))) {
         doc.append("goldsteinScale", array(30).toFloat)
   }
   doc.append("numMentions", array(31).toInt).append("numSources", array(32).toInt).append("numArticles", array(33).toInt)
   if(StringUtils.isNotEmpty(array(34))) {
         doc.append("avgTone", array(34).toFloat)
   }
   doc.append("dateadded", array(59).toLong).append("sourceurl", array(60))
   if(StringUtils.isNotEmpty(array(5))) {
      doc.append("actor1", extractActor1(array))
   }
   if(StringUtils.isNotEmpty(array(15))) {
      doc.append("actor2", extractActor2(array))
   }             
                
   doc.append("actionGeo", extractActionGeo(array))
   return doc
}

// FOR TESTING - With Local Files
def getListOfFiles(dir: File, extensions: List[String]): List[File] = {
   dir.listFiles.filter(_.isFile).toList.filter { file =>
      extensions.exists(file.getName.endsWith(_))
   }
}

def loadEventsToMongo(filename: String) : Unit = {
   val events = sc.textFile(filename).map(line => line.split("\t")).map(array => convertExportRowToJSON(array))
   MongoSpark.save(events)
}

val files = getListOfFiles(new File("/mnt/data/export"), List("CSV"))
files.foreach(f => loadEventsToMongo(f.getAbsolutePath()))
// FOR TESTING - With Local Files



// FROM S3 BUCKETS
//val s3bucket = "s3://gdelt-open-data/v2/events/20170512151500.export.csv"
val s3Bucket = "s3a://telecom.gdelt/201705[0-9]*.export.CSV.zip"

// AWS Credential
sc.hadoopConfiguration.set("fs.s3a.access.key", "xxxxxxxxx")
sc.hadoopConfiguration.set("fs.s3a.secret.key", "xxxxxxxxx")

val records = sc.binaryFiles(s3Bucket).flatMap {
   case (name: String, content: PortableDataStream) =>
      val zis = new ZipInputStream(content.open)
      Stream.continually(zis.getNextEntry)
        .takeWhile(_ != null)
        .flatMap { _ =>
           val br = new BufferedReader(new InputStreamReader(zis))
           Stream.continually(br.readLine()).takeWhile(_ != null)
        }
}

val events = records.map(line => line.split("\t")).map(array => convertExportRowToJSON(array))
MongoSpark.save(events)
// FROM S3 BUCKETS

