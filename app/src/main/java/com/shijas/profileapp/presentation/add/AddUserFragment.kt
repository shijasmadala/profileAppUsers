package com.shijas.profileapp.presentation.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.shijas.profileapp.R
import com.shijas.profileapp.databinding.FragmentAddUserBinding
import com.shijas.profileapp.domain.model.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddUserFragment : Fragment(R.layout.fragment_add_user) {
    private lateinit var binding: FragmentAddUserBinding
    private val viewModel by viewModels<AddUserViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddUserBinding.bind(view)

       observeSaveUser()
        setListeners()
    }

    private fun setListeners(){
        binding.confirm.setOnClickListener {
            viewModel.saveUser(
                User(
                    id = 0,
                    firstName = binding.firstnameEt.text.toString(),
                    lastName = binding.lastNameEt.text.toString(),
                    email = binding.lastNameEt.text.toString()
                )
            )
        }
    }


    private fun observeSaveUser() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.saveUser.collectLatest {
                    when(it){
                        AddUserState.Empty -> {}
                        is AddUserState.Error -> {
                            Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()

                        }
                        AddUserState.Loading -> {}
                        is AddUserState.Success -> {
                           Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
                            findNavController().navigateUp()
                        }
                    }
                }
            }
        }
    }

}