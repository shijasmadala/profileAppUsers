package com.shijas.profileapp.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.shijas.profileapp.R
import com.shijas.profileapp.databinding.FragmentLoginBinding
import com.shijas.profileapp.databinding.FragmentUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    var userName : String =""
    var password : String =""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding=FragmentLoginBinding.bind(view)


        binding.btnLogin.setOnClickListener{
            with(binding){
                userName =edtUserName.text.toString()

                password = edtPassword.text.toString()

                when {
                    userName.isNullOrEmpty() ->{
                        Toast.makeText(requireContext(),"Please enter the user name",Toast.LENGTH_SHORT).show()
                    }

                    password.isNullOrEmpty() ->{
                        Toast.makeText(requireContext(),"Please enter the password",Toast.LENGTH_SHORT).show()
                    }

                    userName != "testapp@google.com" || password !="Test@123456" -> {
                        Toast.makeText(requireContext(),"User name or password incorrect ",Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToUserFragment())
                    }
                }
            }
        }


    }
}