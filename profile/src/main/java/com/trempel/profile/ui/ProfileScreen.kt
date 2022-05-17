package com.trempel.profile.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.PermissionState
import com.trempel.core_ui.exceptions.TrempelException
import com.trempel.profile.R

@Composable
fun UserProfile(profileViewModel: ProfileViewModel = viewModel()) {
    val userInfo by profileViewModel.userProfileInfo.observeAsState()
    val progressBar by profileViewModel.isInProgressTemp.observeAsState()
    if (progressBar == true) {
        ProgressBar()
    } else {
        ErrorDialog(profileViewModel)
        Column(Modifier
                .padding(top = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            UserImage(profileViewModel)
            Column(
                Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    Modifier
                        .padding(start = 40.dp, top = 18.dp, end = 40.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    userInfo?.name?.let { name ->
                        UserInfo(
                            title = stringResource(id = R.string.user_name_title_profile),
                            description = stringResource(
                                id = R.string.user_name_format,
                                name.firstname.replaceFirstChar { it.uppercase() },
                                name.lastname.replaceFirstChar { it.uppercase() })
                        )
                    }
                    userInfo?.username?.let {
                        UserInfo(
                            stringResource(id = R.string.user_nickname_title_profile),
                            description = it
                        )
                    }
                    userInfo?.phone?.let {
                        UserInfo(
                            title = stringResource(id = R.string.user_phone_number_title_profile),
                            description = it
                        )
                    }
                    userInfo?.address?.let { address ->
                        UserInfo(title = stringResource(id = R.string.user_address_title_profile),
                            description = stringResource(
                                id = R.string.address_format,
                                address.street.replaceFirstChar { it.uppercase() },
                                address.number,
                                address.zipcode,
                                address.city.replaceFirstChar { it.uppercase() }
                            ))
                    }
                }
            }
            UserLocation(profileViewModel)
        }
    }
}

@Composable
fun UserInfo(title: String, description: String) {
    Text(
        modifier = Modifier.padding(top = 12.dp),
        text = title,
        fontStyle = FontStyle(R.font.montserrat_light),
        fontSize = 16.sp
    )
    Text(
        text = description,
        fontStyle = FontStyle(R.font.montserrat_regular),
        fontSize = 18.sp
    )
}

@Composable
fun UserImage(profileViewModel: ProfileViewModel = viewModel()) {
    val userImageIsSelected = remember { mutableStateOf(false) }
    val selectedImage = remember { mutableStateOf<Uri?>(null) }
    val permissionState =
        rememberPermissionState(permission = android.Manifest.permission.READ_EXTERNAL_STORAGE)
    selectedImage.value?.let { profileViewModel.saveUserImage(it) }
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        PermissionState(permissionState, selectedImage, userImageIsSelected)
        Image(
            painter = rememberImagePainter(data = profileViewModel.loadUserImage() ?: R.drawable.default_image_profile),
            contentDescription = "Profile image",
            Modifier
                .size(200.dp)
                .fillMaxWidth()
                .clip(CircleShape)
                .clickable(
                    onClick = {
                        permissionState.launchPermissionRequest()
                        userImageIsSelected.value = true
                    }
                ),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun ErrorDialog(profileViewModel: ProfileViewModel) {
    val openDialog = remember { mutableStateOf(true) }
    val trempelException by profileViewModel.errorLiveData.observeAsState()
    if (trempelException is TrempelException.Network && openDialog.value) {
        AlertDialog(
            shape = RoundedCornerShape(20.dp),
            onDismissRequest = {},
            title = {
                Text(
                    stringResource(id = R.string.title_network_excep_dialog),
                    fontStyle = FontStyle(R.font.montserrat_regular),
                    fontSize = 18.sp
                )
            },
            text = {
                Text(
                    stringResource(id = R.string.description_network_excep_dialog),
                    fontStyle = FontStyle(R.font.montserrat_light),
                    fontSize = 16.sp
                )
            },
            confirmButton = {
                OutlinedButton(
                    onClick = {
                        profileViewModel.getUserInfo()
                        openDialog.value = false
                    },
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    modifier = Modifier
                        .height(40.dp)
                        .width(97.dp)
                ) {
                    Text(
                        stringResource(id = R.string.btn_retry),
                        color = Color.White,
                        fontStyle = FontStyle(R.font.montserrat_regular),
                        fontSize = 18.sp
                    )
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { openDialog.value = false },
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    modifier = Modifier
                        .height(40.dp)
                        .width(97.dp)
                ) {
                    Text(
                        stringResource(id = R.string.btn_close_dialog),
                        color = Color.Black,
                        fontStyle = FontStyle(R.font.montserrat_regular),
                        fontSize = 18.sp
                    )
                }
            },
            modifier = Modifier.fillMaxHeight(0.55f)
        )
    } else if (trempelException is TrempelException.Service && openDialog.value) {
        AlertDialog(
            shape = RoundedCornerShape(20.dp),
            onDismissRequest = {},
            title = {
                Text(
                    stringResource(id = R.string.title_service_excep_dialog),
                    fontStyle = FontStyle(R.font.montserrat_regular),
                    fontSize = 18.sp
                )
            },
            text = {
                Text(
                    stringResource(id = R.string.description_service_excep_dialog),
                    fontStyle = FontStyle(R.font.montserrat_light),
                    fontSize = 16.sp
                )
            },
            confirmButton = {},
            dismissButton = {
                OutlinedButton(
                    onClick = { openDialog.value = false },
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    modifier = Modifier
                        .height(40.dp)
                        .width(97.dp)
                ) {
                    Text(
                        stringResource(id = R.string.btn_close_dialog),
                        color = Color.Black,
                        fontStyle = FontStyle(R.font.montserrat_regular),
                        fontSize = 18.sp
                    )
                }
            },
            modifier = Modifier.fillMaxHeight(0.55f)
        )
    }
}

@Composable
private fun PermissionState(
    permissionState: PermissionState,
    selectedImage: MutableState<Uri?>,
    userImageIsSelected: MutableState<Boolean>
) {
    if (permissionState.status == PermissionStatus.Granted) {
        val launcher =
            rememberLauncherForActivityResult(
                ActivityResultContracts.GetContent()
            ) {
                selectedImage.value = it
            }
        if (userImageIsSelected.value) {
            SideEffect {
                launcher.launch("image/*")
                userImageIsSelected.value = false
            }
        }
    }
}

@Composable
private fun ProgressBar() {
    Box(
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(50.dp),
            color = Color.Black,
            strokeWidth = 5.dp,
        )
    }
}

