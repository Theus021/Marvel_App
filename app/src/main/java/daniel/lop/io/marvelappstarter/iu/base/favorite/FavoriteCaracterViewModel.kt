package daniel.lop.io.marvelappstarter.iu.base.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import daniel.lop.io.marvelappstarter.model.CaracterModel
import daniel.lop.io.marvelappstarter.repository.MarvelRepository
import daniel.lop.io.marvelappstarter.state.ResourceState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteCaracterViewModel @Inject constructor(
    private val repository: MarvelRepository

): ViewModel() {

    private val _favorite = MutableStateFlow<ResourceState<List<CaracterModel>>>(ResourceState.Empty())

    var favorite: StateFlow<ResourceState<List<CaracterModel>>> = _favorite

    init {
         fetch()
    }

    private fun fetch() = viewModelScope.launch {
        repository.getAll().collectLatest { characters ->
            if(characters.isNullOrEmpty()){
                _favorite.value = ResourceState.Empty()
            }else{
                _favorite.value = ResourceState.Success(characters)
            }
        }

    }
    fun delete(caracterModel: CaracterModel) = viewModelScope.launch {
        repository.delete(caracterModel)
    }
}