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
        //Call repository function to fetch Flickr photos from API
        Repository().getPhotos(object: Callback<SearchResult> {
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                //Retrieve photos fetched from API
                val result = response.body()?.photos?.photo
                //Add each photo of the result of the call to our photosList
                result?.forEach{ photo -> photosList.add(photo) }
                //Display the first photo
                nextPhoto()
            }

            override fun onFailure(call: Call<SearchResult>, t: Throwable) { }
        })
    }

    /**
     * Display the next photo in photosList
     */
    fun nextPhoto() {
        //Get index of currently displayed photo (index will be -1 if photo.value is null)
        var index = photosList.indexOf(photo.value)
        //If at the end of the list, start from the beginning
        if(index === photosList.size - 1) index = 0
        //Else, increment index
        else index++
        //Set displayed photo value to next photo in list
        photo.value = photosList[index]
    }

}
