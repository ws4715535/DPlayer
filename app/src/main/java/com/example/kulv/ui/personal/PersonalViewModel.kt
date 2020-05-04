package com.example.kulv.ui.personal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PersonalViewModel : ViewModel() {

    private val personalModel = PersonalModel()

    fun getPersonalModel(): PersonalModel{
        return personalModel
    }
}