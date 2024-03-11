package com.example.feature_joinclub.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.ClubInfo
import com.example.coroutine.IoDispatcher
import com.example.feature_joinclub.domain.usecase.SearchClubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClubSearchViewModel @Inject constructor(
    private val searchClubUseCase: SearchClubUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel(){
    private val _searchValue = MutableStateFlow("")
    val searchValue: StateFlow<String> =_searchValue

    private val _searchedClub = MutableStateFlow<List<ClubInfo>>(listOf())
    val searchedClub: StateFlow<List<ClubInfo>> = _searchedClub

    fun searchClub(clubName: String) {
        _searchValue.value = clubName
        viewModelScope.launch(ioDispatcher) {
            _searchedClub.value = searchClubUseCase(clubName)
        }
    }

}