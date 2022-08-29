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
    private val caracterAdapter by lazy {CharacterAdapter()}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        clickAdapter()
        observer()
    }

    private fun observer() = lifecycleScope.launch {
        viewModel.favorite.collect { resource ->

            when (resource) {
                is ResourceState.Success -> {
                    resource.data?.let {
                        binding.tvEmptyList.hide()
                        caracterAdapter.characters = it.toList()
                    }
                }
                is ResourceState.Empty -> {
                    binding.tvEmptyList.show()

                }
                else -> {}
            }
        }
    }

    private fun clickAdapter() {
        caracterAdapter.setOnClickListener { caracterModel ->
            val action = FavoriteCaracterFragmentDirections
                .actionFavoriteCaracterFragmentToDetailsCaracterFragment(caracterModel)
            findNavController().navigate(action)
        }
    }

    private fun setupRecycleView() = with(binding){
        rvFavoriteCharacter.apply {
            adapter = caracterAdapter
            layoutManager = LinearLayoutManager(context)
        }
        ItemTouchHelper(itemTochHelperCallBack()).attachToRecyclerView(rvFavoriteCharacter)
    }

    private fun itemTochHelperCallBack(): ItemTouchHelper.Callback{
        return object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val character = caracterAdapter.getCharacterPosition(viewHolder.adapterPosition)
                viewModel.delete(character).also {
                    toast(getString(R.string.message_delete_character))
                }
            }

        }
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ):  FragmentFavoriteCharacterBinding =
        FragmentFavoriteCharacterBinding.inflate(layoutInflater, container, false)
}