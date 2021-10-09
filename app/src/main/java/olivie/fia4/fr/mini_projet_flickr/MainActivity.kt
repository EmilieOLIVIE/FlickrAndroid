package olivie.fia4.fr.mini_projet_flickr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import olivie.fia4.fr.mini_projet_flickr.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        /* Unrelevant with navigation component
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        */
    }
}