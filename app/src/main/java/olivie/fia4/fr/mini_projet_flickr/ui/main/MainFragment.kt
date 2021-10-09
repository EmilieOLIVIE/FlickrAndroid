package olivie.fia4.fr.mini_projet_flickr.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import olivie.fia4.fr.mini_projet_flickr.R
import olivie.fia4.fr.mini_projet_flickr.model.Photo

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layout = inflater.inflate(R.layout.main_fragment, container, false)
        //Initialise View Model on view creation
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val nextImgButton = layout.findViewById<Button>(R.id.nextImage)
        val allImgButton = layout.findViewById<Button>(R.id.allImages)
        val photoTitle = layout.findViewById<TextView>(R.id.photoTitle)
        val photoView = layout.findViewById<ImageView>(R.id.photo)

        //Observe changes in View Model
        viewModel.photo.observe(this, Observer<Photo> { photo ->
            val url = "https://farm" + photo.farm + ".staticflickr.com/" + photo.server + "/" + photo.id+"_"+photo.secret + ".jpg"
            //Set photo title in TextView
            photoTitle.text = photo.title
            //Insert photo fetched from url into layout
            Glide.with(layout).load(url).into(photoView);
            //Redirect to ListFragment upon clicking on "All images"
            allImgButton.setOnClickListener {
                Navigation.findNavController(layout).navigate(R.id.toListFragment);
            }
            //Redirect to FullFragment upon clicking on photo
            photoView.setOnClickListener{
                //Action refers to corresponding nav graph link
                val action = MainFragmentDirections.toFullFragment(url)
                //Bind navigation action to view
                Navigation.findNavController(layout).navigate(action)
            }
        })

        nextImgButton.setOnClickListener {
            viewModel.nextPhoto()
        }

        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}