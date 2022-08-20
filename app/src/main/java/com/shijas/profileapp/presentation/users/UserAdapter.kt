package com.shijas.profileapp.presentation.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shijas.profileapp.databinding.ItemUserBinding
import com.shijas.profileapp.domain.model.User
import com.shijas.profileapp.domain.model.WeatherModel

class UserAdapter(private val listener: OnClick) : ListAdapter<User, RecyclerView.ViewHolder>(UserDiff) {

    interface OnClick {
        fun onWeatherShowClick()
        fun onSwipeDelete(user: User)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserViewHolder).bind(getItem(position))
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.apply {
                firstName.text = user.firstName
                lastName.text = user.lastName
                email.text = user.email

                root.setOnClickListener{
                    listener.onWeatherShowClick()
                }

                delete.setOnClickListener{
                    listener.onSwipeDelete(user)
                }
            }
        }
    }
}

object UserDiff : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}