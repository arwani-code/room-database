package com.picodiploma.noteapps.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.picodiploma.noteapps.MainViewModel
import com.picodiploma.noteapps.R
import com.picodiploma.noteapps.fragments.adapter.ListAdapter
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        val listAdapter = ListAdapter()
        val rvUser = view?.rv_users
        rvUser?.adapter = listAdapter
        rvUser?.layoutManager = LinearLayoutManager(requireContext())
        rvUser?.setHasFixedSize(true)

        setHasOptionsMenu(true)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getUsers.observe(viewLifecycleOwner){
            listAdapter.setData(it)
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_delete){
            deleteAllUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            viewModel.deleteAllUser()
            Toast.makeText(requireContext(), "Successfully delete everything user", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Delete everything user")
        builder.setMessage("Are you sure you want to delete everything user")
        builder.create().show()
    }
}