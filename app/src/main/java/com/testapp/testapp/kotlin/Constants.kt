package com.testapp.testapp.kotlin

/**
 * Created by dmitriysmishnyi on 28.08.18.
 */
object Network {
    val BASE_URL = "https://api.foursquare.com/v2/"
    val CLIENT_ID_VALUE = "DA2YIVUIZ5TUFRLEXNWZR210TFJRNY2CVK5PEHEZZWS2PSSS"
    val CLIENT_SECRET_VALUE = "PP4ZMJF04X3GXHSTXGYYVKRA3PUPWI00ZUG2PLZS3JPSGJZJ"

    val DATE_VERSION_FIELD = "v"
    val LONGITUDE_AND_LATITUDE_FIELD = "ll"
    val API_INTENT_FIELD = "intent"
    val SEARCH_QUERY_FIELD = "query"
    val LIMIT_FIELD = "limit"
    val CLIENT_ID_FIELD = "client_id"
    val CLIENT_SECRET_FIELD = "client_secret"
}

object System {
    val INTENT_TITLE = "title"
    val INTENT_VENUE_ID = "venueId"
    val INTENT_LATITUDE = "latitude"
    val INTENT_LONGITUDE = "longitude"
}

object UI {
    val ICON_SIZE = 100
    val SCALE = 10
    val KILOMETER_IN_METERS = 1000
}