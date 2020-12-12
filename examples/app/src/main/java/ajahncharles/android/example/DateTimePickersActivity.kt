package ajahncharles.android.example

import ajahncharles.android.helpers.setOnClickCoroutine
import ajahncharles.android.helpers.pickDateAndTime
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_date_time_pickers.*
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

class DateTimePickersActivity : AppCompatActivity() {

    private val dateFormatter = DateTimeFormatter.ofPattern("MMM-dd, yyyy")
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    private var pickedDateTime: LocalDateTime = LocalDateTime.now().with(LocalTime.NOON)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_time_pickers)

        // [1] Standard onClick
        dateLabel.setOnClickListener {
            lifecycleScope.launch {
                pickedDateTime = pickDateAndTime(pickedDateTime)
                dateLabel.text = pickedDateTime.format(dateFormatter)
                timeLabel.text = pickedDateTime.format(timeFormatter)
            }
        }

        // [2] Coroutine click helper
        dateLabel.setOnClickCoroutine(this) {
            pickedDateTime = pickDateAndTime(pickedDateTime)
            dateLabel.text = pickedDateTime.format(dateFormatter)
            timeLabel.text = pickedDateTime.format(timeFormatter)
        }

        // [3] Shared listener
        val listener: suspend (view: View) -> Unit = {
            pickedDateTime = pickDateAndTime(pickedDateTime)
            dateLabel.text = pickedDateTime.format(dateFormatter)
            timeLabel.text = pickedDateTime.format(timeFormatter)
        }

        dateLabel.setOnClickCoroutine(this, listener)
        timeLabel.setOnClickCoroutine(this, listener)
    }

}
