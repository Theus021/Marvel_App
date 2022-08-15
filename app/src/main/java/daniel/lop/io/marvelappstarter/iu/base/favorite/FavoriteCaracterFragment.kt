package daniel.lop.io.marvelappstarter.iu.base.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import daniel.lop.io.marvelappstarter.databinding.FragmentFavoriteCharacterBinding
import daniel.lop.io.marvelappstarter.iu.base.BaseFragment

@AndroidEntryPoint
class FavoriteCaracterFragment: BaseFragment<FragmentFavoriteCharacterBinding, FavoriteCaracterViewModel>() {
    override val viewModel: FavoriteCaracterViewModel by viewModels()

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ):  FragmentFavoriteCharacterBinding =
        FragmentFavoriteCharacterBinding.inflate(layoutInflater, container, false)
}