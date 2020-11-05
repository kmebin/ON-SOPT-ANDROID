package org.kmebin.Seminar27th

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPref: SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val sharedEdit = sharedPref.edit()

        val idValue = sharedPref.getString("Id", "")
        val pwValue = sharedPref.getString("Password", "")
        editText_id.setText(idValue)
        editText_pw.setText(pwValue)

        if (idValue.toString().isNotBlank() && pwValue.toString().isNotBlank()) {
            Toast.makeText(this, "${idValue.toString()}님 자동 로그인 되었습니다.", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener {
            Toast.makeText(this, "반갑습니다.", Toast.LENGTH_SHORT).show()

            sharedEdit.putString("Id", editText_id.text.toString())
            sharedEdit.putString("Password", editText_pw.text.toString())
            sharedEdit.apply()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btn_sign_up.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, 111)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 111) {
            val userId = data!!.getStringExtra("id")
            val userPw = data.getStringExtra("password")

            editText_id.setText(userId)
            editText_pw.setText(userPw)
        }
    }
}