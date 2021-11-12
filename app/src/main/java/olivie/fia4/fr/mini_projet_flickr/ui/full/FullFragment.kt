package olivie.fia4.fr.mini_projet_flickr.ui.full

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import olivie.fia4.fr.mini_projet_flickr.R

class FullFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.full_fragment, container, false)
        val photoView = layout.findViewById<ImageView>(R.id.photo)
        //Fetch arguments from previous navigation fragment
        val url = FullFragmentArgs.fromBundle(requireArguments()).url
        //Insert photo fetched from url into layout
        Glide.with(layout).load(url).into(photoView)
        return layout
    }

}