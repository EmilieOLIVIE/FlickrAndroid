package olivie.fia4.fr.mini_projet_flickr.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import olivie.fia4.fr.mini_projet_flickr.model.Photo
import olivie.fia4.fr.mini_projet_flickr.model.SearchResult
import olivie.fia4.fr.mini_projet_flickr.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel : ViewModel() {

    val photos = MutableLiveData<List<Photo>>()
    val photosList = mutableListOf<Photo>()

    init {
        Repository().getPhotos(object: Callback<SearchResult> {
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                //Retrieve photos fetched from API
                val res = response.body()?.photos
                for (photo in res?.photo!!) {
                    //Add photo to the list
                    photosList.add(photo)
                }
                //Display photos list
                photos.value = photosList
            }

            override fun onFailure(call: Call<SearchResult>, t: Throwable) { }
        })
    }

}