package com.niroshan.starwarsplanets.ui.planets.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.niroshan.starwarsplanets.data.model.PlanetData
import com.niroshan.starwarsplanets.databinding.ItemPlanetBinding
import com.niroshan.starwarsplanets.internal.setAvatarImage
import com.niroshan.starwarsplanets.ui.planets.PlanetsFragmentDirections

class PlanetsAdapter : PagingDataAdapter<PlanetData, PlanetsAdapter.ViewHolder>(PLANET_COMPARATOR) {

    companion object {

        private val PLANET_COMPARATOR = object : DiffUtil.ItemCallback<PlanetData>() {
            override fun areItemsTheSame(oldItem: PlanetData, newItem: PlanetData) =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: PlanetData, newItem: PlanetData) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPlanetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let { planet ->
            with(holder) {
                itemView.tag = planet
                if (planet != null) {
                    bind(createOnClickListener(binding, planet), planet)
                }
            }
        }
    }

    private fun createOnClickListener(
        binding: ItemPlanetBinding,
        planet: PlanetData
    ): View.OnClickListener {
        return View.OnClickListener {
            val directions = PlanetsFragmentDirections.actionPlanetsToDetails(planet)
            val extras = FragmentNavigatorExtras(
                binding.avatar to "avatar_${planet.name}"
            )
            it.findNavController().navigate(directions, extras)
        }
    }

    class ViewHolder(val binding: ItemPlanetBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, planet: PlanetData) {

            val imageURL = "https://picsum.photos/200/300?random=$position"

            binding.apply {

                planet.imageURL = imageURL

                avatar.setAvatarImage(itemView, imageURL)
                name.text = planet.name
                climate.text = planet.climate

                ViewCompat.setTransitionName(this.avatar, "avatar_${planet.name}")

                root.setOnClickListener(listener)
            }

        }
    }
}