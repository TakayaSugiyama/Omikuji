package jp.wings.nikkeibp.omikuji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import android.view.View
import kotlinx.android.synthetic.main.omikuji.*

class OmikujiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.omikuji)
//        imageView.setImageResource(R.drawable.result1)
//        //くじ番号の取得
//        val rnd = Random()
//        val number = rnd.nextInt(20)
//
//        //おみくじの準備
//        val omikujiShelf = Array(20) { "吉"}
//        omikujiShelf[0] = "大吉"
//        omikujiShelf[19] = "凶"
//
//        //おみくじ棚から取得
//        val str: String = omikujiShelf[number]
//        hello_view.text = str
    }

    fun onButtonClick(view: View){
        imageView.setImageResource(R.drawable.result1)
    }
}