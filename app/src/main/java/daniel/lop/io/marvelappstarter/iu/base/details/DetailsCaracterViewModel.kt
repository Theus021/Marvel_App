package daniel.lop.io.marvelappstarter.iu.base.details

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import daniel.lop.io.marvelappstarter.repository.MarvelRepository
import javax.inject.Inject

@HiltViewModel
class DetailsCaracterViewModel @Inject constructor(
    private val repository: MarvelRepository
): ViewModel() {
}