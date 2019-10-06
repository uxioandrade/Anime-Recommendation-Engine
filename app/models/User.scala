package model

import generated.tables.records.ViewerRecord

case class User(id: Long, 
username: String, 
userWatching: Int, 
userCompleted: Int,
userOnHold: Int,
userPlanToWatch: Int,
userDaysSpentWatching: Float,
gender: generated.enums.Gender,
location: String,
statsEpisodes: Long) {
	def this(ur: ViewerRecord) = this(
        ur.getId,
        ur.getUsername,
        ur.getUserwatching,
        ur.getUsercompleted,
        ur.getUseronhold,
        ur.getUserplantowatch,
        ur.getUserdaysspentwatching,
        ur.getGender,
        ur.getLocation,
        ur.getStatsepisodes
    )
}

