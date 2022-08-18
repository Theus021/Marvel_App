package daniel.lop.io.marvelappstarter.iu.base.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import daniel.lop.io.marvelappstarter.repository.MarvelRepository
import daniel.lop.io.marvelappstarter.model.CaracterModelResponse
import daniel.lop.io.marvelappstarter.state.ResourceState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel

class ListCaracterViewModel @Inject constructor(

    private val repository: MarvelRepository

): ViewModel() {

    private val _list = MutableStateFlow<ResourceState<CaracterModelResponse>>(ResourceState.Loading())
    val list: StateFlow<ResourceState<CaracterModelResponse>> = _list

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
       safeFetch()
    }

    private suspend fun safeFetch() {
        try {
            val response = repository.list()
            _list.value = handleRespository(response)
        }catch (t: Throwable){
            when(t){
                is IOException -> _list.value = ResourceState.Error("Erro de conexão com a Internet")
                else -> _list.value = ResourceState.Error("Falha ao carregar os dados")
            }
        }
    }

    private fun handleRespository(response: Response <CaracterModelResponse>):
            ResourceState<CaracterModelResponse> {

        if(response.isSuccessful){
            response.body()?.let { values ->
                return ResourceState.Success(values)
            }
        }
        return ResourceState.Error(response.message())
    }
}