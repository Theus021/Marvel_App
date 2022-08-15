package daniel.lop.io.marvelappstarter.iu.base.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import daniel.lop.io.marvelappstarter.databinding.FragmentDetailsCharacterBinding
import daniel.lop.io.marvelappstarter.iu.base.BaseFragment

@AndroidEntryPoint
class DetailsCaracterFragment: BaseFragment<FragmentDetailsCharacterBinding, DetailsCaracterViewModel>() {
    override val viewModel: DetailsCaracterViewModel by viewModels()


    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ):  FragmentDetailsCharacterBinding = FragmentDetailsCharacterBinding.inflate(layoutInflater, container, false)
}