import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mfxf.fuelcc.ext.SPManager
import com.mfxf.fuelcc.ext.spGetStr
import com.mfxf.fuelcc.ext.spSetStr
import com.mfxf.fuelcc.ext.toJson
import com.mfxf.fuelcc.nav.NavRouter
import com.mfxf.fuelcc.nav.PagesRouter
import com.mfxf.fuelcc.nav.bottomBarNavigateTo
import com.mfxf.fuelcc.nav.currentPageInfo
import com.mfxf.fuelcc.ui.theme.Blue_100
import com.mfxf.fuelcc.ui.theme.Blue_255
import com.mfxf.fuelcc.ui.theme.FuelCCTheme
import com.mfxf.fuelcc.ui.theme.MainColor_255
import com.mfxf.fuelcc.ui.theme.Red_100
import com.mfxf.fuelcc.ui.theme.Red_255

lateinit var mNavController: NavHostController
lateinit var mContext: Context

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this, mBackPressedCallback)

        setContent {
            mContext = LocalContext.current
            SPManager = mContext.getSharedPreferences(
                mContext.resources.getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
        
            mNavController = rememberNavController()

            WeddingNotepadTheme {
//                WindowCompat.setDecorFitsSystemWindows((mContext as MainActivity).window, false)

                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setSystemBarsColor(MainColor_255)
                }


                val currentPageInfo = currentPageInfo()

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                    ,
                    bottomBar = {
                        if (currentPageInfo?.hierarchy?.any {
                                BottomItemDataList.map { navBarItem -> navBarItem.route }
                                    .contains(it.route)
                            } == true) {
                            BottomNavigationBar(currentPageInfo)
                        }
                    }
                ) { _ ->
                    NavRouter()
                }

            }

        }
    }

    data class BottomItemData(val route: String, val label: String, @DrawableRes val icon: Int)
    private val BottomItemDataList = listOf(
        BottomItemData(PagesRouter.home, "Note", R.drawable.ic1),
        BottomItemData(PagesRouter.mine, "Mine", R.drawable.ic2)
    )
    @Composable
    fun BottomNavigationBar(currentPageInfo: NavDestination?) {

        Column(modifier = Modifier.fillMaxWidth().padding(20.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MainColor_255, RoundedCornerShape(10.dp))
                    .padding(6.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)
                        .background(if (currentPageInfo?.hierarchy?.any { it.route == BottomItemDataList[0].route } == true) Red_100 else Color.Transparent,
                            RoundedCornerShape(10.dp))
                        .clickable {
                            bottomBarNavigateTo(BottomItemDataList[0].route)
                        }
                        .padding(10.dp)
                ) {
                    Text(
                        text = BottomItemDataList[0].label,
                        color = if (currentPageInfo?.hierarchy?.any { it.route == BottomItemDataList[0].route } == true) Red_255 else Black_120
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Image(
                        painter = painterResource(id = BottomItemDataList[0].icon),
                        contentDescription = null,
                        modifier = Modifier.size(26.dp)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)
                        .background(if (currentPageInfo?.hierarchy?.any { it.route == BottomItemDataList[1].route } == true) Blue_100 else Color.Transparent,
                            RoundedCornerShape(10.dp))
                        .clickable {
                            bottomBarNavigateTo(BottomItemDataList[1].route)
                        }
                        .padding(10.dp)
                ) {
                    Text(
                        text = BottomItemDataList[1].label,
                        color = if (currentPageInfo?.hierarchy?.any { it.route == BottomItemDataList[1].route } == true) Blue_255 else Black_120
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Image(
                        painter = painterResource(id = BottomItemDataList[1].icon),
                        contentDescription = null,
                        modifier = Modifier.size(26.dp)
                    )
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