package com.trempel.profile.ui

import android.graphics.Bitmap
import android.net.Uri
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import com.trempel.core_ui.ImageSaver
import com.trempel.core_ui.SingleLiveEvent
import com.trempel.core_ui.exceptions.TrempelException
import com.trempel.profile.model.ProfileModel
import com.trempel.profile.repo.ProfileRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val imageSaver: ImageSaver,
) : ViewModel() {

    private var _userProfileInfo = MutableLiveData<ProfileModel>()
    val userProfileInfo: LiveData<ProfileModel> get() = _userProfileInfo
    private val _errorLiveData = SingleLiveEvent<TrempelException?>()
    val errorLiveData: LiveData<TrempelException?> get() = _errorLiveData
    val isInProgressTemp = MutableLiveData(true)

    init {
        getUserInfo()
    }

    fun getUserInfo() {
        viewModelScope.launch {
            runCatching {
                profileRepository.getUser()
            }.onFailure { error ->
                _errorLiveData.value = error as TrempelException
            }.onSuccess { response ->
                _userProfileInfo.value = response
            }
            isInProgressTemp.value = (false)
        }
    }

    fun saveUserImage(uri: Uri) {
        imageSaver
            .setFileName(USER_IMAGE_NAME)
            .save(uri)
    }

    fun loadUserImage(): Bitmap? {
        return imageSaver
            .setFileName(USER_IMAGE_NAME)
            .load()
    }

    companion object {
        const val USER_IMAGE_NAME = "userImage.png"
    }
}
