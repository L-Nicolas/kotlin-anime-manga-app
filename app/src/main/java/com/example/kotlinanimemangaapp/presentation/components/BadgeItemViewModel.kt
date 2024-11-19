package com.example.kotlinanimemangaapp.presentation.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BadgeItemViewModel @Inject constructor() : ViewModel() {
    private val _selected = mutableStateOf(false)
    val selected: State<Boolean> get() = _selected

    fun toggleSelected() {
        _selected.value = !_selected.value
    }
}
