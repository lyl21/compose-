package com.mtfe.rememberwidget.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.mtfe.rememberwidget.R
import com.mtfe.rememberwidget.mNavController
import com.mtfe.rememberwidget.nav.PagesRouter


@Composable
fun BottomNavigationBar(currentPageInfo: NavDestination) {
    NavigationBar (containerColor= Color.White){
        BottomItemDataList.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = currentPageInfo.hierarchy.any { it.route == item.route },
                onClick = {
                    bottomBarNavigateTo(item.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(id =  item.icon),
                        contentDescription = "",
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = (item.label)
                    )
                },
                colors= NavigationBarItemDefaults.colors(
//                    indicatorColor= Color.White
                )
            )
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
val BottomItemDataList = listOf(
    BottomItemData(PagesRouter.my, "My", R.drawable.my),
    BottomItemData(PagesRouter.today, "Collect", R.drawable.today_24),
)
data class BottomItemData(val route: String, val label: String, @DrawableRes val icon: Int)
