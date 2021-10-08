package olivie.fia4.fr.mini_projet_flickr.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import olivie.fia4.fr.mini_projet_flickr.model.Photo
import olivie.fia4.fr.mini_projet_flickr.model.SearchResult
import olivie.fia4.fr.mini_projet_flickr.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val photo = MutableLiveData<Photo>()
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
                nextPhoto()
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun nextPhoto() {
        var nextIndex = 0

        //Get index of currently displayed photo
        val currentIndex = photosList.indexOf(photo.value)
        nextIndex = currentIndex + 1
        //If at the end of the list, start to the beginning
        if(currentIndex === photosList.size - 1) {
            nextIndex = 0
        }
        //Set displayed photo value to next photo in list
        photo.value = photosList[nextIndex]
    }

}