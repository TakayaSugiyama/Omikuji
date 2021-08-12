package jp.wings.nikkeibp.omikuji

import java.util.*

class OmikujiBox {
    val number : Int
    get(){
        val rnd = Random()
        return rnd.nextInt(20)
    }
}