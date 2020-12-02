package org.kmebin.Seminar27th

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_up.*
import okhttp3.ResponseBody
import org.json.JSONObject
import org.kmebin.Seminar27th.api.SoptServiceImpl
import org.kmebin.Seminar27th.data.RequestSignUpData
import org.kmebin.Seminar27th.data.ResponseSignUpData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btn_sign_up.setOnClickListener {

            val userName = et_name.text.toString()
            val email = et_id.text.toString()
            val password = et_pw.text.toString()

            if (userName.isEmpty() || email.isEmpty() || password.isEmpty())
                Toast.makeText(this, "빈칸이 있습니다.", Toast.LENGTH_SHORT).show()
            else {
                Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()

                val intent = Intent()
                intent.putExtra("id", et_id.text.toString())
                intent.putExtra("password", et_pw.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }

            // 회원가입 통신
            val call : Call<ResponseSignUpData> = SoptServiceImpl.service.postSignUp(
                    RequestSignUpData(email = email, password = password, userName = userName)
            )
            call.enqueue(object : Callback<ResponseSignUpData>{
                override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
                    Log.d("tag", t.localizedMessage)
                }

                override fun onResponse(call: Call<ResponseSignUpData>, response: Response<ResponseSignUpData>) {
                    response.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let { it ->

                        } ?: showError(response.errorBody())
                }
            })
        }

    }

    private fun showError(error : ResponseBody?) {
        val e = error ?: return
        val ob = JSONObject(e.string())
        Toast.makeText(this, ob.getString("message"), Toast.LENGTH_LONG).show()
    }
}