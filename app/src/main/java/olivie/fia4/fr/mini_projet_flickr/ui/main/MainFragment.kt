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

        val nextImgButton = layout.findViewById<Button>(R.id.nextImage)
        val allImgButton = layout.findViewById<Button>(R.id.allImages)
        val photoTitle = layout.findViewById<TextView>(R.id.photoTitle)
        val photoView = layout.findViewById<ImageView>(R.id.photo)

        viewModel.photos.observe(this, Observer<Photo> { photo ->
            val url = "https://farm" + photo.farm + ".staticflickr.com/" + photo.server + "/" + photo.id+"_"+photo.secret + ".jpg"
            photoTitle.text = photo.title

            Glide.with(layout).load(url).into(photoView);
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