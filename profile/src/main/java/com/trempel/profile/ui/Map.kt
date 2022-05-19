package com.trempel.profile.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun UserLocation(profileViewModel: ProfileViewModel = viewModel()) {
    val userInfo by profileViewModel.userProfileInfo.observeAsState()
    val location = userInfo?.address?.geolocation?.lat?.let { lat ->
        userInfo?.address?.geolocation?.long?.let { lng ->
            LatLng(lat.toDouble(), lng.toDouble())
        }
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location!!, 10f)
    }
    GoogleMap(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .padding(start = 40.dp, end = 40.dp, top = 18.dp, bottom = 18.dp)
            .clip(shape = RoundedCornerShape(10.dp)),
        cameraPositionState = cameraPositionState,
        googleMapOptionsFactory = { GoogleMapOptions().mapId("75eba249e60b008f") }
    ) {
        location?.let { Marker(position = it) }
    }
}
