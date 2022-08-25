package daniel.lop.io.marvelappstarter.iu.base.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import daniel.lop.io.marvelappstarter.R
import daniel.lop.io.marvelappstarter.databinding.FragmentDetailsCharacterBinding
import daniel.lop.io.marvelappstarter.iu.base.BaseFragment
import daniel.lop.io.marvelappstarter.iu.base.adapter.ComicAdapter
import daniel.lop.io.marvelappstarter.model.CaracterModel
import daniel.lop.io.marvelappstarter.util.limitDescription

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
    }

    private fun onLoadCharacter(caracterModel: CaracterModel) = with(binding) {
        tvNameCharacterDetails.text = caracterModel.name
        if(caracterModel.description.isEmpty()){
            tvDescriptionCharacterDetails.text =
                requireArguments().getString(R.string.text_description_empty.toString()
                    .limitDescription(100))
        }
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