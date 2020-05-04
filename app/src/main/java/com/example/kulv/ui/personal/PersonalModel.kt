package com.example.kulv.ui.personal

import androidx.lifecycle.LiveData

class PersonalModel : LiveData<PersonalModel?>() {
    var name: String? = null
    var describe: String? = null
    var image: String? = null

}