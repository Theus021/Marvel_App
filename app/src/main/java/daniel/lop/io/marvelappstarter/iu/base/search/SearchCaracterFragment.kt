package daniel.lop.io.marvelappstarter.iu.base.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import daniel.lop.io.marvelappstarter.databinding.FragmentSearchCharacterBinding
import daniel.lop.io.marvelappstarter.iu.base.BaseFragment

class SearchCaracterFragment: BaseFragment<FragmentSearchCharacterBinding, SearchCaracterViewModel>() {
    override val viewModel: SearchCaracterViewModel by viewModels()


    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchCharacterBinding =
        FragmentSearchCharacterBinding.inflate(layoutInflater, container, false)
}