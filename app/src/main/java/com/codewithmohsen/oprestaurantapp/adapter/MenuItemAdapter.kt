package com.codewithmohsen.oprestaurantapp.adapter

import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.codewithmohsen.oprestaurantapp.AppCoroutineDispatchers
import com.codewithmohsen.oprestaurantapp.R
import com.codewithmohsen.oprestaurantapp.databinding.ItemFoodBinding
import com.codewithmohsen.oprestaurantapp.model.MenuItem

/**
 * A RecyclerView adapter for [Item List] class.
 */
class MenuItemAdapter(
    appCoroutineDispatchers: AppCoroutineDispatchers,
    private val dataBindingComponent: DataBindingComponent,
    private val itemClickCallback: ((MenuItem) -> Unit)?
) : DataBoundListAdapter<MenuItem, ItemFoodBinding>(
    appCoroutineDispatchers = appCoroutineDispatchers,
    diffCallback = object : DiffUtil.ItemCallback<MenuItem>() {
        override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem.id == newItem.id &&
            return oldItem.title == newItem.title &&
            return oldItem.subTitle == newItem.subTitle &&
            return oldItem.minimumCalorie == newItem.minimumCalorie &&
            return oldItem.maximumCalorie == newItem.maximumCalorie &&
            return oldItem.image == newItem.image &&
            return oldItem.price == newItem.price &&
            return oldItem.fee == newItem.fee &&
            return oldItem.rate == newItem.rate

        }
    }
) {

    override fun createBinding(parent: ViewGroup): ItemFoodBinding {

        val binding = DataBindingUtil.inflate<ItemFoodBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_food,
            parent,
            false,
            dataBindingComponent
        )
        binding.root.setOnClickListener { _ ->
            binding.item.let {
                if(it != null) itemClickCallback?.invoke(it)
            }
        }

        return binding
    }

    override fun bind(binding: ItemFoodBinding, item: MenuItem) {
        binding.item = item
    }
}
