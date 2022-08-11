package daniel.lop.io.marvelappstarter.iu.base.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import daniel.lop.io.marvelappstarter.databinding.FragmentListCharacterBinding
import daniel.lop.io.marvelappstarter.iu.base.BaseFragment

class ListCaracterFragment: BaseFragment<FragmentListCharacterBinding, ListCaracterViewModel>() {

    override val viewModel: ListCaracterViewModel by viewModels()


    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListCharacterBinding =
        FragmentListCharacterBinding.inflate(layoutInflater, container, false)
}