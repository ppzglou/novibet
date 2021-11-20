package gr.sppzglou.novibet.ui

import android.annotation.SuppressLint
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import gr.sppzglou.novibet.base.BaseActivity
import gr.sppzglou.novibet.data.BetViewHeadline
import gr.sppzglou.novibet.data.Competition
import gr.sppzglou.novibet.databinding.ActivityDashboardBinding


@AndroidEntryPoint
class DashboardActivity :
    BaseActivity<ActivityDashboardBinding, DashboardViewModel>(DashboardViewModel::class.java) {
    private val headlinesAdapter = HeadlinesAdapter()
    private val gamesAdapter = GamesAdapter()
    private var headlinesList = mutableListOf<BetViewHeadline>()
    private var gamesList = mutableListOf<Competition>()

    override fun getViewBinding(): ActivityDashboardBinding {
        return ActivityDashboardBinding.inflate(layoutInflater)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setupObservers() {
        with(viewModel) {
            with(binding) {
                successHeadlines.observe(this@DashboardActivity) {
                    headlinesList = it.betViews
                    headlinesAdapter.submitList(headlinesList)

                    TabLayoutMediator(intoTabLayout, pagerHeadLines)
                    { _, _ -> }.attach()
                    pager()
                }
                successGames.observe(this@DashboardActivity) {
                    try {
                        it.betViews[0].let {
                            gamesList = it.competitions
                            gamesAdapter.submitList(gamesList)
                        }
                    } catch (e: Exception) {
                    }
                }
                successUpdateHeadlines.observe(this@DashboardActivity) {
                    headlinesList.addAll(it.betViews)
                    headlinesAdapter.submitList(headlinesList)
                    headlinesAdapter.notifyDataSetChanged()


                    TabLayoutMediator(intoTabLayout, pagerHeadLines)
                    { _, _ -> }.attach()
                }
                successUpdateGames.observe(this@DashboardActivity) {
                    try {
                        it.betViews[0].let {
                            gamesList.addAll(it.competitions)
                            gamesAdapter.submitList(gamesList)
                            gamesAdapter.notifyDataSetChanged()
                        }
                    } catch (e: Exception) {
                    }
                }
                loadError.observe(this@DashboardActivity) {
                    if (!it) {
                        if (gamesList.isEmpty()) fetchData()
                        else update()
                    }
                }
                page.observe(this@DashboardActivity) {
                    if (pagerHeadLines.currentItem < headlinesList.size - 1)
                        pagerHeadLines.currentItem = pagerHeadLines.currentItem + 1
                    else if (pagerHeadLines.currentItem == headlinesList.size - 1)
                        pagerHeadLines.currentItem = 0
                }
            }
        }
    }

    override fun setupViews() {
        viewModel.fetchData()
        with(binding) {
            pagerHeadLines.adapter = headlinesAdapter
            recyclerGames.adapter = gamesAdapter
        }
    }


    override fun setupListeners() {}

    override fun onStop() {
        viewModel.clearToken()
        super.onStop()
    }
}