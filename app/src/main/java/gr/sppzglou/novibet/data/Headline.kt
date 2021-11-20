package gr.sppzglou.novibet.data

data class HeadlineItem(
    val betViews: MutableList<BetViewHeadline>
)

data class BetViewHeadline(
    val competitor1Caption: String,
    val competitor2Caption: String,
    val startTime: String
)

