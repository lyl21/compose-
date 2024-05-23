package com.jrrzx.emergencyhelper.nav

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.jrrzx.emergencyhelper.helper.MediaItemInfo
import com.jrrzx.emergencyhelper.mNavController
import com.jrrzx.emergencyhelper.page.AboutUSPage
import com.jrrzx.emergencyhelper.page.HomePage
import com.jrrzx.emergencyhelper.page.MessageDetailPage
import com.jrrzx.emergencyhelper.page.MessagePage
import com.jrrzx.emergencyhelper.page.P1Page
import com.jrrzx.emergencyhelper.page.P2Page
import com.jrrzx.emergencyhelper.page.P3Page
import com.jrrzx.emergencyhelper.page.P4Page
import com.jrrzx.emergencyhelper.page.P5Page
import com.jrrzx.emergencyhelper.page.P6Page
import com.jrrzx.emergencyhelper.page.SettingPage
import com.jrrzx.emergencyhelper.page.SuggestionPage


@Composable
fun NavRouter() {
    NavHost(navController = mNavController, startDestination = PagesRouter.home) {
        composable(route = PagesRouter.setting) {
            SettingPage()
        }
        composable(route = PagesRouter.suggestion) {
            SuggestionPage()
        }
        composable(route = PagesRouter.about) {
            AboutUSPage()
        }
        composable(
            route = PagesRouter.message
        ) {
            MessagePage()
        }
        composable(
            route = PagesRouter.messageDetail
        ) {
            MessageDetailPage()
        }
        composable(
            route = PagesRouter.home
        ) {
            HomePage()
        }

        composable(
            route = PagesRouter.p1
        ) {
            P1Page()
        }
        composable(
            route = PagesRouter.p2
        ) {
            P2Page()
        }
        composable(
            route = PagesRouter.p3
        ) {
            P3Page()
        }
        composable(
            route = PagesRouter.p4
        ) {
            P4Page()
        }
        composable(
            route = PagesRouter.p5
        ) {
            P5Page()
        }
        composable(
            route = PagesRouter.p6
        ) {
            P6Page()
        }
    }


}


fun bottomBarNavigateTo(route: String) {
    mNavController.navigate(route) {
        popUpTo(mNavController.graph.findStartDestination().id) {
            saveState = true
            inclusive = true
        }
        launchSingleTop = true
        restoreState = true
    }
}


@Composable
fun currentPageInfo(): NavDestination? {
    val navBackStackEntry by mNavController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination
}
