from bottle import route, run
from pymongo import MongoClient
from pymongo import DESCENDING
from bson.json_util import dumps

mongoServer = 'localhost:27017'
#mongoServer = '54.166.108.196:27017'


# Execute a basic query with a sorted field and a limit result
def executeBasicQuery(query, sortedfield, limit=20):
    client = MongoClient(mongoServer)
    events = client.gdelt.events.find(query).sort(sortedfield, DESCENDING).limit(limit)
    client.close()
    return dumps(events)


@route('/events/actor1/countryCode/<countryCode>')
def getEventByActor1CountryCode(countryCode):
    return executeBasicQuery({'actor1.countryCode': countryCode, 'isRootEvent': 1}, 'dateadded')


@route('/events/date/<dateadded>')
def getEventByDate(dateadded):
    return executeBasicQuery({'dateadded': int(dateadded), 'isRootEvent': 1}, 'dateadded')


@route('/events/day/<day>')
def getEventByDay(day):
    return executeBasicQuery({'day': int(day), 'isRootEvent': 1}, 'day')


@route('/events/month/<month>')
def getEventByMonth(month):
    return executeBasicQuery({'month': int(month), 'isRootEvent': 1}, 'month')


@route('/events/year/<year>')
def getEventByYear(year):
    return executeBasicQuery({'year': int(year), 'isRootEvent': 1}, 'year')


run(host='localhost', port=8080)

