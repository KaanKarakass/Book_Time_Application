package com.kaankarakas.booktime.loginSignup
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaankarakas.booktime.Activities.AppActivity
import com.kaankarakas.booktime.loginDatabase.UserInfo
import com.kaankarakas.booktime.loginDatabase.UserInfoDao
import kotlinx.coroutines.launch
class SignUpViewModel(val dao: UserInfoDao):ViewModel() {
    var newUserMail = ""
    var newUserPwd = ""
    var newUserPwd2 = ""

    fun addUser(context: Context?,activity: FragmentActivity?){
        val thread = Thread {
            val check = dao.signUp(newUserMail)
            val handler = Handler(Looper.getMainLooper())
            handler.post {
                if (check.equals(true)) {
                    Toast.makeText(context, "Email is already used", Toast.LENGTH_LONG).show()
                }else{
                    if(newUserPwd == newUserPwd2)
                    {
                        viewModelScope.launch{
                            val user = UserInfo()
                            user.userMail = newUserMail
                            user.userPsw = newUserPwd
                            dao.insert(user)
                        }
                        if(context!=null && activity!=null){
                            val intent  = Intent(activity, AppActivity::class.java)
                            intent.putExtra("key", newUserMail)
                            ContextCompat.startActivity(context, intent, null)
                        }
                    }
                    else{
                        Toast.makeText(context, "Not same Passwords", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        thread.start()

    }
}