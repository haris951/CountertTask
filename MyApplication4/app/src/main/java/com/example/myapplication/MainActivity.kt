package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

/**
 * MainActivity is the main entry point of the application which maintains a simple counter
 * that increments each time a button is clicked. The counter value is preserved across
 * configuration changes like screen rotations.
 */
class MainActivity : AppCompatActivity() {
    private var count = 0
    private val counter = "counter_key"

    /**
     * Called when the activity is first created. This is where you should do all of your normal
     * static set up: create views, bind data to lists, etc.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being
     * shut down then this Bundle contains the data it most recently supplied in
     * `onSaveInstanceState(Bundle)`. Otherwise it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Restore state if available
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt(counter, 0)
        }

        binding.apply {
            appCompatTextView.text = count.toString()
            appCompatButton.setOnClickListener {
                count++
                appCompatTextView.text = count.toString()
            }
        }
    }

    /**
     * Called to retrieve per-instance state from an activity before being killed so that the
     * state can be restored in `onCreate(Bundle)` or `onRestoreInstanceState(Bundle)` (the Bundle
     * populated by this method will be passed to both).
     *
     * @param outState Bundle in which to place your saved state.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(counter, count)
    }

    /**
     * This method is called after `onStart()` when the activity is being re-initialized from a
     * previously saved state, given here in `savedInstanceState`.
     *
     * @param savedInstanceState the data most recently supplied in `onSaveInstanceState(Bundle)`.
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        count = savedInstanceState.getInt(counter, 0)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        binding.appCompatTextView.text = count.toString()
    }
}
