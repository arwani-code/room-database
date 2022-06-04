package com.picodiploma.noteapps.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.picodiploma.noteapps.MainViewModel
import com.picodiploma.noteapps.R
import com.picodiploma.noteapps.data.local.entity.UserEntity
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        setHasOptionsMenu(true)

        view.up_firstName.setText(args.currentUser.firstName)
        view.up_lastName.setText(args.currentUser.lastName)
        view.up_age.setText(args.currentUser.age.toString())

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        view.button.setOnClickListener {
            updateUserToDatabase()
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_delete){
            deleteUserToDatabase()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUserToDatabase() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            mainViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),
                "Successfully delete ${args.currentUser.firstName}",
            Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Delete ${args.currentUser.firstName}")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}")
        builder.create().show()
    }

    private fun updateUserToDatabase(){
        val firstName = up_firstName.text.toString()
        val lastName = up_lastName.text.toString()
        val age = up_age.text

        if(inputCheck(firstName, lastName, age)){
            mainViewModel.updateUser(UserEntity(args.currentUser.id, firstName, lastName, Integer.parseInt(age.toString())))
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean =
        !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())


}