package com.learn.mysound

import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var sp: SoundPool
    private var soundId: Int = 0
    private var spLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //tombol sounpool
        val btnSound = findViewById<Button>(R.id.btn_sound_pool)

        //unutk sounpool
        sp = SoundPool.Builder()
            .setMaxStreams(1)//<- untuk menentukan jumlah streams secara simultan yang dapat diputar secara bersamaan.
            .build()

        //fungsi button untuk mendengarkan soundpool
        sp.setOnLoadCompleteListener { _, _, status ->
            if (status == 0) {
                spLoaded = true  //<- dengan spLoaded ini  untuk memastikan proses pemutaran telah selesai
            } else {
                Toast.makeText(this@MainActivity, "Gagal load", Toast.LENGTH_SHORT).show()
            }
        }
        soundId = sp.load(this, R.raw.audio_sound_pool_test, 1)

        //fungsi button soundpool
        btnSound.setOnClickListener {
            if (spLoaded) {
                sp.play(soundId, 1f, 1f, 0, 0, 1f)
            }
        }
    }
}