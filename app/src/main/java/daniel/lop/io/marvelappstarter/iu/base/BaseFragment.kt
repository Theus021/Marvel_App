package daniel.lop.io.marvelappstarter.iu.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB: ViewBinding, VM: ViewBinding>: Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!
    protected abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    abstract fun getViewBinding(layoutInflater: LayoutInflater, container: ViewGroup?): VB?

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}