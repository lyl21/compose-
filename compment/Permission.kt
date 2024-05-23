package com.jrrzx.emergencyhelper.compment


import android.content.Intent
import android.net.Uri
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.PermissionsRequired
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.jrrzx.emergencyhelper.ext.showToast
import com.jrrzx.emergencyhelper.mContext
import com.jrrzx.emergencyhelper.mNavController



    
    implementation ("com.google.accompanist:accompanist-permissions:0.23.1")


@Preview
@Composable
fun PermissionPreview() {
    PermissionRequest()
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionRequest(
    permissionList: List<String> = listOf(),
    rationale: String = "This permission is important for this app. Please grant the permission.",
    content: @Composable () -> Unit = { },
) {
    val permissionsState = rememberMultiplePermissionsState(permissionList)
    PermissionsRequired(
        multiplePermissionsState = permissionsState,
        permissionsNotGrantedContent = {
            PermissionDialog(
                text = rationale,
                onRequestPermission = { permissionsState.launchMultiplePermissionRequest() }
            )
        },
        permissionsNotAvailableContent = {
            mNavController.popBackStack()
            showToast("This permission is important for this app. Please grant the permission.")
            val intent = Intent().apply {
                action = ACTION_APPLICATION_DETAILS_SETTINGS
                data = Uri.fromParts("package", mContext.packageName, null)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            mContext.startActivity(intent)

        },
        content = content
    )
}



@Composable
private fun PermissionDialog(
    text: String,
    onRequestPermission: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { },
        title = {
            Text(text = "Permission request")
        },
        text = {
            Text(text)
        },
        confirmButton = {
            Button(onClick = onRequestPermission) {
                Text("Ok")
            }
        }
    )
}