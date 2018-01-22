# TESTING UPDATES EVENTS 

from pyspark.sql import Row
from pyspark.sql import functions as F
from pymongo import MongoClient

def update_event(row):
    globalEventID = row['globalEventID']
    mentionTimeDate = row['mentionTimeDate']
    mentionType = row['mentionType']
    mentionSourceName = row['mentionSourceName']
    mentionIdentifier = row['mentionIdentifier']
    mentionDocTone = row['mentionDocTone']
    zips = zip(mentionTimeDate, mentionType, mentionSourceName, mentionIdentifier, mentionDocTone)
    mentions = []
    for timeDate, mtype, sourceName, identifier, doctone in zips:
        mentions.append({'id': identifier, 'timeDate': timeDate, 'type': mtype, 'sourceName': sourceName, 'doctone': doctone})
    db.events.update_one({'globalEventId': globalEventID}, {'$push': {'mentions': {'$each': mentions}}}, upsert=True)


client = MongoClient('54.166.108.196:27017')
db = client.gdelt


mentionsFiles = "201705*.mentions.CSV"

mentions_rdd = sc.textFile(mentionsFiles).map(lambda line: line.split("\t")).map(lambda p: Row(globalEventID=int(p[0]), eventTimeDate=p[1], mentionTimeDate=p[2], mentionType=int(p[3]), mentionSourceName=p[4], mentionIdentifier=p[5], mentionDocTone=float(p[13])))

mentions_rdd = mentions_rdd.toDF()

mentions_rdd_gby = mentions_rdd.groupBy('globalEventID').agg(F.collect_set("mentionTimeDate").alias("mentionTimeDate"), F.collect_set("mentionType").alias("mentionType"), F.collect_set("mentionSourceName").alias("mentionSourceName"), F.collect_set("mentionIdentifier").alias("mentionIdentifier"), F.collect_set("mentionDocTone").alias("mentionDocTone"))

for row in mentions_rdd_gby.collect():
    update_event(row)
        
        
