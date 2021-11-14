package com.niroshan.starwarsplanets.ui.planets

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.niroshan.starwarsplanets.data.StarWarsRepository

class PlanetsViewModel @ViewModelInject constructor(repository: StarWarsRepository) : ViewModel() {

    val planets = repository.getSearchResults().cachedIn(viewModelScope)

}