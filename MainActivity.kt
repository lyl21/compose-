package com.jrrzx.emergencyhelper

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jrrzx.emergencyhelper.ext.SPManager
import com.jrrzx.emergencyhelper.ext.spSetStr
import com.jrrzx.emergencyhelper.nav.NavRouter
import com.jrrzx.emergencyhelper.nav.PagesRouter
import com.jrrzx.emergencyhelper.nav.bottomBarNavigateTo
import com.jrrzx.emergencyhelper.nav.currentPageInfo
import com.jrrzx.emergencyhelper.ui.theme.EmergencyHelperTheme
import com.jrrzx.emergencyhelper.ui.theme.MainColor255

lateinit var appVM: APPVM
lateinit var mContext: Context
lateinit var mNavController: NavHostController
class MainActivity : ComponentActivity(){
    private val appViewModel: APPVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        onBackPressedDispatcher.addCallback(this, mBackPressedCallback)

        setContent {
            mContext = LocalContext.current
            mNavController = rememberNavController()
            SPManager = mContext.getSharedPreferences(
                mContext.resources.getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
            appVM=appViewModel
            

            EmergencyHelperTheme{
//                WindowCompat.setDecorFitsSystemWindows((mContext as MainActivity).window, false)

                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(MainColor_2_255)

                val currentPageInfo = currentPageInfo()
                Scaffold(
                    bottomBar = {
                        if (currentPageInfo?.hierarchy?.any {
                                BottomItemDataList.map { navBarItem -> navBarItem.route }
                                    .contains(it.route)
                            } == true) {
                            BottomNavigationBar( currentPageInfo  )
                        }
                    }
                ) { _ ->
                    NavRouter()
                }
            }

        }
    }
    private var lastBackMills: Long = 0
    private val mBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (System.currentTimeMillis() - lastBackMills > 2000) {
                lastBackMills = System.currentTimeMillis()
                Toast.makeText(
                    this@MainActivity,
                    "Press again to exit the program.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                finish()
            }
        }
    }


}

data class BottomItemData(val route: String, val label: String, val icon: ImageVector)
private val BottomItemDataList = listOf(
    BottomItemData(PagesRouter.home, "Function", Icons.Filled.Menu),
    BottomItemData(PagesRouter.message, "Information", Icons.Filled.MailOutline),
    BottomItemData(PagesRouter.setting, "Setting", Icons.Filled.Settings)
)
@Composable
fun BottomNavigationBar(currentPageInfo: NavDestination) {
    NavigationBar {
        BottomItemDataList.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = currentPageInfo.hierarchy.any { it.route == item.route },
                onClick = {
                    bottomBarNavigateTo(item.route)
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = ""
                    )
                },
                label = {
                    Text(
                        text = (item.label)
                    )
                },
            )
        }
    }
}