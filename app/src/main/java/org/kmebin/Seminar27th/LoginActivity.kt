package org.kmebin.Seminar27th

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.ResponseBody
import org.json.JSONObject
import org.kmebin.Seminar27th.api.SoptServiceImpl
import org.kmebin.Seminar27th.data.RequestLoginData
import org.kmebin.Seminar27th.data.ResponseLoginData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPref: SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val sharedEdit = sharedPref.edit()

        val idValue = sharedPref.getString("email", "")
        val pwValue = sharedPref.getString("password", "")
        editText_id.setText(idValue)
        editText_pw.setText(pwValue)

        if (idValue.toString().isNotBlank() && pwValue.toString().isNotBlank()) {
            Toast.makeText(this, "자동 로그인 되었습니다.", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener {
            val email = editText_id.text.toString()
            val password = editText_pw.text.toString()

            // 로그인 통신
            val call : Call<ResponseLoginData> = SoptServiceImpl.baseService.postLogin(
                    RequestLoginData(email = email, password = password)
            )
            call.enqueue(object : Callback<ResponseLoginData> {
                override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                }

                override fun onResponse(
                        call: Call<ResponseLoginData>,
                        response: Response<ResponseLoginData>
                ) {
                    response.takeIf { it.isSuccessful }
                            ?.body()
                            ?.let {
                                it.data.let{data ->
                                    Toast.makeText(this@LoginActivity, "${data.userName}님 환영합니다.", Toast.LENGTH_SHORT).show()
                                }
                                it.data.email
                            } ?: showError(response.errorBody())
                }
            })

            // SharedPreferences에 데이터 저장
            sharedEdit.putString("email", editText_id.text.toString())
            sharedEdit.putString("password", editText_pw.text.toString())
            sharedEdit.apply()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        tv_sign_up.setOnClickListener {
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

    private fun showError(error : ResponseBody?) {
        val e = error ?: return
        val ob = JSONObject(e.string())
        Toast.makeText(this, ob.getString("message"), Toast.LENGTH_LONG).show()
    }
}