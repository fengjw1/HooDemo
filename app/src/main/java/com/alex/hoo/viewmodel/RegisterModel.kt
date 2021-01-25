package com.alex.hoo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.hoo.db.repository.UserRepository
import kotlinx.coroutines.launch

class RegisterModel constructor(
    private val repository: UserRepository
) : ViewModel(){

    val n = MutableLiveData("")
    val p = MutableLiveData("")
    val mail = MutableLiveData("")
    val enable = MutableLiveData(false)

    /**
     * 用户名改变的回调函数
     */
    fun onNameChange(s: CharSequence){
        n.value = s.toString()
        judgeEnable()
    }

    /**
     * 邮箱改变的回调函数
     */
    fun onEmailChange(s: CharSequence){
        mail.value = s.toString()
        judgeEnable()
    }

    /**
     * 密码改变的回调函数
     */
    fun onPwdChange(s: CharSequence){
        p.value = s.toString()
        judgeEnable()
    }

    private fun judgeEnable(){
        enable.value = p.value!!.isNotEmpty() &&
                n.value!!.isNotEmpty() &&
                mail.value!!.isNotEmpty();
    }

    fun register(){
        viewModelScope.launch {
            repository.register(mail.value!!, n.value!!, p.value!!)
        }
    }

}