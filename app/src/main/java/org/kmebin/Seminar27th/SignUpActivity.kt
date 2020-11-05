package org.kmebin.Seminar27th

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btn_sign_up.setOnClickListener {

            var inputName = et_name.text.toString()
            var inputId = et_id.text.toString()
            var inputPw = et_pw.text.toString()

            if (inputName.isEmpty() || inputId.isEmpty() || inputPw.isEmpty())
                Toast.makeText(this, "빈칸이 있습니다.", Toast.LENGTH_SHORT).show()
            else {
                Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()

                val intent = Intent()
                intent.putExtra("id", et_id.text.toString())
                intent.putExtra("password", et_pw.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }

    }
}