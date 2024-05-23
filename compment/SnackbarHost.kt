package com.jrrzx.emergencyhelper.compment


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Preview
@Composable
fun LLLSnackbarHostPreview() {
//    LLLSnackbarHost()
    MyScreen()
}

@Composable
fun LLLSnackbarSimple(snackbarHostState:SnackbarHostState){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        LLLSnackbarHost(snackbarHostState) {
            Snackbar(
                snackbarData = it,
            )
        }
    }
}

@Composable
fun LLLSnackbarHost(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    snackbarContent: @Composable (SnackbarData) -> Unit
) {
    SnackbarHost(
        hostState = snackbarHostState,
        modifier = modifier,
        snackbar = snackbarContent
    )
}


@Composable
fun MyScreen() {
    val rememberCoroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                rememberCoroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Snackbar message",
                        actionLabel = "Dismiss"
                    )
                }
            }
        ) {
            Text("Show Snackbar")
        }
        Spacer(modifier = Modifier.weight(1f))
        SnackbarHost(
            hostState = snackbarHostState,
//            modifier = Modifier.padding(16.dp)
        )
    }
}

