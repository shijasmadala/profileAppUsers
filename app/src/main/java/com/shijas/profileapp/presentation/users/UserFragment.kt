package com.shijas.profileapp.presentation.users

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.shijas.profileapp.R
import com.shijas.profileapp.databinding.FragmentUserBinding
import com.shijas.profileapp.domain.model.User
import com.shijas.profileapp.domain.model.WeatherModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserFragment : Fragment(R.layout.fragment_user), UserAdapter.OnClick {
    private lateinit var binding: FragmentUserBinding
    private val viewModel by viewModels<UserViewModel>()
    private val userAdapter by lazy { UserAdapter(this) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserBinding.bind(view)
        binding.recyclerView.adapter = userAdapter
        loadUsers()
        setListeners()

        binding.menuPopUp.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(requireContext(), binding.menuPopUp)
            popupMenu.menuInflater.inflate(R.menu.pop_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.logout -> {
                        findNavController().navigateUp()
                    }
                }
                true
            })
            popupMenu.show()
        }


    }

    private fun setListeners() {
        binding.apply {
            addUser.setOnClickListener {
                findNavController().navigate(UserFragmentDirections.actionUserFragmentToAddUserFragment())
            }
        }
    }

    private fun loadUsers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.users.collectLatest { users ->
                    userAdapter.submitList(users)


                }
            }
        }
    }

    private fun swipeDelete() {

    }

    override fun onWeatherShowClick() {

        findNavController().navigate(UserFragmentDirections.actionUserFragmentToWeatherFragment())
    }

    override fun onSwipeDelete(user: User) {
        viewModel.userDelete(user)
    }


}