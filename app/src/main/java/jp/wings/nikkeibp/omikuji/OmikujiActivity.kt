package jp.wings.nikkeibp.omikuji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.main.*
import java.util.*

class OmikujiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        //文字を表示する。
        var str = "大吉"
        val rnd = Random()
        val number = rnd.nextInt(3)
        if (number == 0){
            str = "吉"
        }else if (number == 1){
            str = "凶"
        }
        hello_view.text = str
    }
}