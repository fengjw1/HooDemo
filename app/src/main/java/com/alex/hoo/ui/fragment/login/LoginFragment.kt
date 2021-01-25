package com.alex.hoo.ui.fragment.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.hoo.R
import com.alex.hoo.common.BaseConstant
import com.alex.hoo.databinding.LoginFragmentBinding
import com.alex.hoo.ui.activity.MainActivity
import com.alex.hoo.utils.AppPrefsUtils
import com.alex.hoo.viewmodel.CustomViewModelProvider
import com.alex.hoo.viewmodel.LoginModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment : Fragment() {

    private val loginModel : LoginModel by viewModels {
        CustomViewModelProvider.providerLoginModel(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: LoginFragmentBinding = DataBindingUtil.inflate(
            inflater
            , R.layout.login_fragment
            , container
            , false
        )

        onSubscribeUi(binding)

        // 判断当前是否是第一次登陆
        val isFirstLaunch = AppPrefsUtils.getBoolean(BaseConstant.IS_FIRST_LAUNCH)
        if (isFirstLaunch){
            onFirstLaunch()
        }

        return binding.root
    }

    private fun onSubscribeUi(binding: LoginFragmentBinding){
        binding.model = loginModel
        binding.activity = activity
        // 如果使用LiveData下面这句必须加上 ！！！
        binding.lifecycleOwner = this

        binding.btnLogin.setOnClickListener {
            loginModel.login()?.observe(viewLifecycleOwner, Observer {user ->
                user?.let {
                    AppPrefsUtils.putLong(BaseConstant.SP_USER_ID, it.id)
                    AppPrefsUtils.putString(BaseConstant.SP_USER_NAME, it.account)
                    val intent = Intent(context, MainActivity::class.java)
                    context?.startActivity(intent)
                    Toast.makeText(context, "登录成功！", Toast.LENGTH_SHORT).show()
                }
            })
        }

        arguments?.getString(BaseConstant.ARGS_NAME)?.apply {
            loginModel.n.value = this
        }
    }

    private fun onFirstLaunch(){
        loginModel.viewModelScope.launch(Dispatchers.Main) {
            val str = withContext(Dispatchers.IO){
                loginModel.onFirstLaunch()
            }
            Toast.makeText(requireContext(),str,Toast.LENGTH_SHORT).show()
            AppPrefsUtils.putBoolean(BaseConstant.IS_FIRST_LAUNCH,false)
        }
    }

}