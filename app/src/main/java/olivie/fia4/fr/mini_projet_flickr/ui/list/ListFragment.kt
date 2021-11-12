package olivie.fia4.fr.mini_projet_flickr.ui.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import olivie.fia4.fr.mini_projet_flickr.R

class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layout = inflater.inflate(R.layout.list_fragment, container, false)
        //Initialise View Model on view creation
        viewModel = ListViewModel()
        //Observe changes in View Model
        viewModel.photos.observe(viewLifecycleOwner, { photos ->
            run {
                val recycler = layout.findViewById<RecyclerView>(R.id.recyclerview)
                recycler.layoutManager = GridLayoutManager(requireActivity(), 2)
                recycler.adapter = MyAdapter(photos)
            }
        })
        return layout
    }

}