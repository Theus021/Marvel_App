package daniel.lop.io.marvelappstarter.iu.base.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import daniel.lop.io.marvelappstarter.R
import daniel.lop.io.marvelappstarter.databinding.FragmentFavoriteCharacterBinding
import daniel.lop.io.marvelappstarter.iu.base.BaseFragment
import daniel.lop.io.marvelappstarter.iu.base.adapter.CharacterAdapter
import daniel.lop.io.marvelappstarter.state.ResourceState
import daniel.lop.io.marvelappstarter.util.hide
import daniel.lop.io.marvelappstarter.util.show
import daniel.lop.io.marvelappstarter.util.toast
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteCaracterFragment: BaseFragment<FragmentFavoriteCharacterBinding, FavoriteCaracterViewModel>() {

    override val viewModel: FavoriteCaracterViewModel by viewModels()
    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoriteCharacterBinding? {
        TODO("Not yet implemented")
    }

}