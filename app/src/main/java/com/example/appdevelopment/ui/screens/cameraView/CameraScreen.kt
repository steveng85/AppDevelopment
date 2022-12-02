package com.example.appdevelopment.ui.screens.cameraView

import android.Manifest
import android.net.Uri
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Clear
import androidx.compose.material.icons.sharp.Done
import androidx.compose.material.icons.sharp.Lens
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.appdevelopment.mockData.dailyWord.pickDailyWord
import com.example.appdevelopment.ui.components.DefaultFieldBox
import com.example.appdevelopment.ui.layout.Scaffoldlayout
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

var displayCameraX: MutableState<Boolean> = mutableStateOf(true)
var displayImageTaken: MutableState<Boolean> = mutableStateOf(false)

lateinit var imageUri: Uri


@ExperimentalMaterial3Api
@Composable
fun CameraScreen(navController: NavController, uiState: CameraUIState, onEvent: (CameraEvent) -> Unit) {
    Scaffoldlayout(navController = navController, pickDailyWord(), Color.Black) { chooseDisplay( uiState, onEvent) }
}

@Composable
fun chooseDisplay(
    uiState: CameraUIState,
    onEvent: (CameraEvent) -> Unit
) {
    if (displayCameraX.value) {
        CameraX()
    } else if (displayImageTaken.value) {
        TakenImage( uiState, onEvent)
    }
}

/**
 * Implementation of the CameraX use cases:
 *
 * Image capture (Save images, including upload to Firebase Storage),
 * Image preview (View an image on the display),
 * Image Analysis (Access a buffer).
 *
 * https://developer.android.com/training/camerax#ease-of-use
 *
 * **/

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraX(viewModel: CameraViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var previewView: PreviewView

    val cameraManifestAccess = listOf(Manifest.permission.CAMERA)
    val permissionState = rememberMultiplePermissionsState(
        permissions = cameraManifestAccess)

    // Access not granted, we ask again... and again
    if (permissionState.allPermissionsGranted == false) {
        SideEffect {
            permissionState.launchMultiplePermissionRequest()
        }
    }

    // Access is granted, we'll display the previewView and IconButton
    if (permissionState.allPermissionsGranted){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
                .clip(shape = RoundedCornerShape(15.dp)),
            contentAlignment = Alignment.BottomCenter
        ) {
            AndroidView(
                factory = {
                    previewView = PreviewView(it)
                    viewModel.onImagePreview(previewView, lifecycleOwner)
                    previewView
                          },
                modifier = Modifier.fillMaxSize()
            )
            Box(contentAlignment = Alignment.BottomCenter) {
                IconButton(
                    onClick = { viewModel.onImageCapture(context, viewModel) },
                    content = {
                        Icon(
                            imageVector = Icons.Sharp.Lens,
                            contentDescription = "Take picture",
                            tint = Color.White,
                            modifier = Modifier
                                .size(125.dp)
                                .padding(30.dp)
                                .border(1.dp, Color.White, CircleShape)
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewTakenImage(){
    TakenImage( CameraUIState(),{})
}

@Composable
fun TextField(text: String, label: String, onEvent: (CameraEvent) -> Unit){
    DefaultFieldBox(
        currentValue = text,
        onEvent = {onEvent(CameraEvent.OnDescriptionChanged(it))},
        focusedColor = MaterialTheme.colorScheme.primary,
        unfocusedColor = Color.LightGray,
        label = label,
        type = "description",
        password = false
    )
}

@Composable
fun TakenImage(
    uiState: CameraUIState,
    onEvent: (CameraEvent) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.onPrimary)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 120.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(modifier = Modifier.height(400.dp), painter = rememberAsyncImagePainter(imageUri), contentDescription = null)
        }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        text = uiState.description,
                        label = "description",
                        onEvent = { onEvent(it) })

                    Box(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .padding(bottom = 15.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .wrapContentHeight()
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = { onEvent(CameraEvent.OnChangeDisplayToCameraX) },
                                content = {
                                    Icon(
                                        imageVector = Icons.Sharp.Clear,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier
                                            .size(40.dp)
                                    )
                                }
                            )
                            IconButton(
                                onClick = { onEvent(CameraEvent.OnImageUpload) },
                                content = {
                                    Icon(
                                        imageVector = Icons.Sharp.Done,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier
                                            .size(40.dp)
                                    )
                                }
                            )
                        }
                    }
                }
            }
    }
}