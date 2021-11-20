package gr.sppzglou.novibet.data

data class GameItem(
    val betViews: MutableList<BetViewGame>,
)

data class BetViewGame(
    val competitions: MutableList<Competition>,
)

data class Competition(
    val events: MutableList<Event>,
)

data class Event(
    val additionalCaptions: AdditionalCaptions,
    val liveData: LiveData
)

data class AdditionalCaptions(
    val competitor1: String,
    val competitor2: String
)

data class LiveData(
    val elapsed: String
)