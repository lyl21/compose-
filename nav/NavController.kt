package com.mhtyuk.pionan.nav

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mhtyuk.pionan.*
import com.mhtyuk.pionan.nav.*
import com.sanzes.dwhj.*


val LocalNavController = staticCompositionLocalOf<NavHostController> {
    error("No NavController  provided!")
}

@Composable
fun NavController() {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavController provides navController) {
        NavRouter(navController)
    }
}

@Composable
fun NavRouter(nav: NavHostController) {
    val current = LocalContext.current
    NavHost(navController = nav, startDestination =  PagesRouter.home.name) {
        composable(route = PagesRouter.home.name) {
            StocksPage(

            )
        }
        composable(route = PagesRouter.my.name) {
            MorePage(

            )
        }

    }

}


//a->b   从栈中弹出a
fun NavController.clearAWhenAToB(bRoute: String, aRoute: String) {
    navigate(bRoute) {
        popUpTo(aRoute) {
            inclusive = true
        }
    }
}

fun NavController.bottomBarNavigateTo(route: PagesRouter) {
    navigate(route.name) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
            inclusive = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavController.clearBackStackAndNavigate(destination: String) {
    // 清空路由栈
    while (currentBackStackEntry?.destination?.route != destination) {
        popBackStack()
    }
    // 导航到新目的地
    navigate(destination) {
        // 为避免重复添加相同的条目
        launchSingleTop = true
    }
}


@Composable
fun currentPageInfo(mNav: NavHostController): NavDestination? {
    val navBackStackEntry by mNav.currentBackStackEntryAsState()
    return navBackStackEntry?.destination
}
