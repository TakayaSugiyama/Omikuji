package jp.wings.nikkeibp.omikuji

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.fortune.*
import kotlinx.android.synthetic.main.omikuji.*
import java.util.prefs.PreferenceChangeEvent

class OmikujiActivity : AppCompatActivity(), SensorEventListener {
    lateinit var manager: SensorManager
    lateinit var vibrator: Vibrator

    override fun onResume() {
        super.onResume()
        var sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        manager.unregisterListener(this)
    }

    //おみくじ棚の配列
    val omikujiShelf = Array<OmikujiParts>(20){ OmikujiParts(R.drawable.result2, R.string.content1) }
    //おみくじ番号保管用
    var omikujiNumber = -1
    val omikujiBox =  OmikujiBox()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.omikuji)

        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val value = pref.getBoolean("button", false)
        button.visibility = if (value) View.VISIBLE else View.INVISIBLE

        omikujiBox.omikujiView = imageView

        //おみくじ棚の準備
        omikujiShelf[0].drawID = R.drawable.result1
        omikujiShelf[1].fortuneId = R.string.content2

        omikujiShelf[1].drawID = R.drawable.result3
        omikujiShelf[1].fortuneId = R.string.content9

        omikujiShelf[2].fortuneId = R.string.content3
        omikujiShelf[3].fortuneId = R.string.content4
        omikujiShelf[4].fortuneId = R.string.content5
        omikujiShelf[5].fortuneId = R.string.content6
        omikujiShelf[6].fortuneId = R.string.content7
        omikujiShelf[7].fortuneId = R.string.content8
        omikujiShelf[8].fortuneId = R.string.content9
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item1){
            val intent = Intent(this, OmikujiPreferenceActivity::class.java)
            startActivity(intent)
        }else{
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun onButtonClick(view: View){
        omikujiBox.shake()
    }

    fun drawResult(){
        omikujiNumber = omikujiBox.number
        val op = omikujiShelf[omikujiNumber]
        setContentView(R.layout.fortune)

        //画像とテキストを変更する
        imageView3.setImageResource(op.drawID)
        textView.setText(op.fortuneId)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event?.action == MotionEvent.ACTION_DOWN){
            if(omikujiNumber < 0 && omikujiBox.finish){

                val pref = PreferenceManager.getDefaultSharedPreferences(this)
                if(pref.getBoolean("vibration", false)){
                    vibrator.vibrate(100L)
                }
                drawResult()
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onSensorChanged(event: SensorEvent?) {
//        val value = event?.values?.get(0)
//        if (value != null && 10 < value){
//            val toast = Toast.makeText(this, "加速度: ${value}", Toast.LENGTH_LONG)
//            toast.show()
//        }

        if(omikujiBox.chkShake(event)){
            if(omikujiNumber < 0){
                omikujiBox.shake()
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }
}