package com.example.kulv.ui.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.kulv.R

class PersonalFragment : Fragment() {

//    private lateinit var personalViewModel: PersonalViewModel
//    private lateinit var binding: FragmentPersonalBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
//        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_personal)
//        personalViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(requireActivity().application))
//            .get(PersonalViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_personal, container, false)

//        personalViewModel.getPersonalModel().observe(viewLifecycleOwner, Observer {
//            binding.person = it
//        })
        return root
    }
}
