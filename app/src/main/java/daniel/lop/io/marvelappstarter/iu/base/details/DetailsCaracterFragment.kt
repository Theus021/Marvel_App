package daniel.lop.io.marvelappstarter.iu.base.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import daniel.lop.io.marvelappstarter.R
import daniel.lop.io.marvelappstarter.databinding.FragmentDetailsCharacterBinding
import daniel.lop.io.marvelappstarter.iu.base.BaseFragment
import daniel.lop.io.marvelappstarter.iu.base.adapter.ComicAdapter
import daniel.lop.io.marvelappstarter.model.CaracterModel
import daniel.lop.io.marvelappstarter.state.ResourceState
import daniel.lop.io.marvelappstarter.util.hide
import daniel.lop.io.marvelappstarter.util.limitDescription
import daniel.lop.io.marvelappstarter.util.toast
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class DetailsCaracterFragment: BaseFragment<FragmentDetailsCharacterBinding, DetailsCaracterViewModel>() {
    override val viewModel: DetailsCaracterViewModel by viewModels()

    private val args: DetailsCaracterFragmentArgs by navArgs()
    private val comicAdapter by lazy { ComicAdapter() }
    private lateinit var caracterModel: CaracterModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        caracterModel = args.character
        viewModel.fetch(caracterModel.id)
        setupRecycleView()
        onLoadCharacter(caracterModel)
        collecObserver()
    }

    private fun collecObserver() = lifecycleScope.launch {
        viewModel.details.collect { result ->
            when (result) {
                is ResourceState.Success -> {
                    binding.progressBarDetail.hide()
                    result.data?.let { values ->
                        if (values.data.result.count() > 0) {
                            comicAdapter.comics = values.data.result.toList()
                        } else {
                            toast(getString(R.string.empty_list_comics))
                        }
                    }
                }

                is ResourceState.Error -> {
                    binding.progressBarDetail.hide()
                    result.message?.let { message ->
                        Timber.tag("detailsCharacter").e("Error -> $message" )
                        toast(getString(R.string.an_error_occurred))
                    }
                }
                is ResourceState.Loading -> {}
                else -> {}
            }
        }
    }


    private fun onLoadCharacter(caracterModel: CaracterModel) = with(binding) {
        tvNameCharacterDetails.text = caracterModel.name
        if(caracterModel.description.isEmpty()){
            tvDescriptionCharacterDetails.text =
                requireArguments().getString(R.string.text_description_empty.toString()
                    .limitDescription(100))
        }
        else{
            tvDescriptionCharacterDetails.text = caracterModel.description
        }
        Glide.with(requireContext())
            .load(caracterModel.thumbnailModel.patch + "." + caracterModel.thumbnailModel.extension)
            .into(imgCharacterDetails)
    }

    private fun setupRecycleView()= with(binding){
        rvComics.apply {
            adapter = comicAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ):  FragmentDetailsCharacterBinding = FragmentDetailsCharacterBinding.inflate(layoutInflater, container, false)
}