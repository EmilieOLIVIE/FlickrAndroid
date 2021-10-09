package olivie.fia4.fr.mini_projet_flickr.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import olivie.fia4.fr.mini_projet_flickr.R
import olivie.fia4.fr.mini_projet_flickr.model.Photo

class MyAdapter(val photos : List<Photo>, val callback: (Int) -> Unit) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    //ViewHolder stocks each list item View
    class MyViewHolder(val v: GridLayout) : RecyclerView.ViewHolder(v) {
        var previousPos : Int? = null
    }


    //Called when ViewHolder is created
    //"photo" Layout is created and placed in ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.photo,parent,false)
        val holder = MyViewHolder(layout as GridLayout)
        return holder
    }

    //Called when RecyclerView needs to know the size of the list to display
    override fun getItemCount(): Int = photos.size

    //Called by RecyclerView to display the data at the specified position
    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position:Int) {
        val photoView = holder.v.findViewById<ImageView>(R.id.photo)
        //Retrieve photo information from currently considered photo
        val photo = photos[position]
        val url = "https://farm" + photo.farm + ".staticflickr.com/" + photo.server + "/" + photo.id+"_"+photo.secret + ".jpg"
        //Add listener to image so that click redirect to fullFragment
        photoView.setOnClickListener{
            //Action refers to corresponding nav graph link
            val action = ListFragmentDirections.toFullFragment(url)
            //Bind navigation action to view
            Navigation.findNavController(holder.v).navigate(action)
        }
        //Insert photo fetched from url
        Glide.with(holder.v).load(url).into(photoView);

    }

}