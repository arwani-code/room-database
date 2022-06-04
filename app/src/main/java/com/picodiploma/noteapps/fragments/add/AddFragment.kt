package com.picodiploma.noteapps.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.picodiploma.noteapps.MainViewModel
import com.picodiploma.noteapps.R
import com.picodiploma.noteapps.data.local.entity.UserEntity
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        view.button.setOnClickListener {
            insertUserToDatabase()
        }

        return view
    }

    private fun insertUserToDatabase() {
        val firstName = ed_firstName.text.toString()
        val lastName = ed_lastName.text.toString()
        val age = ed_age.text

        if(inputCheck(firstName, lastName, age)){
            val user = UserEntity(0, firstName, lastName, Integer.parseInt(age.toString()))
            mainViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else {
            Toast.makeText(requireContext(), "Fail added", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean =
        !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())

}