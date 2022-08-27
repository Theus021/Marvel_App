package daniel.lop.io.marvelappstarter.iu.base.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import daniel.lop.io.marvelappstarter.comic.ComicModelResponse
import daniel.lop.io.marvelappstarter.model.CaracterModel
import daniel.lop.io.marvelappstarter.model.CaracterModelResponse
import daniel.lop.io.marvelappstarter.repository.MarvelRepository
import daniel.lop.io.marvelappstarter.state.ResourceState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DetailsCaracterViewModel @Inject constructor(
    private val repository: MarvelRepository
) : ViewModel() {

    private val _details =
        MutableStateFlow<ResourceState<ComicModelResponse>>(ResourceState.Loading())
    val details: StateFlow<ResourceState<ComicModelResponse>> = _details

    fun fetch(characterId: Int) = viewModelScope.launch {
        safeFetch(characterId)
    }

    private suspend fun safeFetch(characterId: Int) {
        _details.value = ResourceState.Loading()
        try {
            val response = repository.getComics(characterId)
            _details.value = handleResponse(response)
        } catch (t: Throwable){
            when(t){
                is IOException -> _details.value =
                    ResourceState.Error("Erro na conexão de internet")
                else -> _details.value = ResourceState.Error("Erro na conversão de dados")
            }
        }
    }

    private fun handleResponse(response: Response<ComicModelResponse>): ResourceState<ComicModelResponse> {
        if (response.isSuccessful) {
            response.body()?.let { value ->
                return ResourceState.Success(value)
            }
        }
        return ResourceState.Error(response.message())

    }

    fun insert(caracterModel: CaracterModel) = viewModelScope.launch {
        repository.insert(caracterModel)
    }
}