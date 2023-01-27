package com.kaankarakas.booktime.loginSignup
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.kaankarakas.booktime.Activities.AppActivity
import com.kaankarakas.booktime.loginDatabase.UserInfoDao


class LoginViewModel(val dao: UserInfoDao):ViewModel() {
    var userMail = ""
    var userPwd = ""



    fun checkUser(context: Context?, activity: FragmentActivity?){
        val thread = Thread {
            val user = dao.login(userMail, userPwd)
            val handler = Handler(Looper.getMainLooper())
            handler.post {
                if (user.equals(false)) {
                    Toast.makeText(context, "User Is not found", Toast.LENGTH_LONG).show()
                } else {
                    if(context!=null && activity!=null){
                        val intent  =Intent(activity, AppActivity::class.java)
                        intent.putExtra("key", userMail)
                        startActivity(context, intent,null)
                    }
                }
            }
        }
        thread.start()
    }
}