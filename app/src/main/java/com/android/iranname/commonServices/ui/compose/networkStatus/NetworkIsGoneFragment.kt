package com.abtinandroidstdio.uritect.commonServices.ui.screen.networkStatus

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.abtinandroidstdio.uritect.homePage.ui.activity.homeActivity.FirstPageActivity
import com.abtinandroidstdio.uritect.databinding.FragmentNetworkIsGoneBinding

class NetworkIsGoneFragment : Fragment() {

    private lateinit var binding : FragmentNetworkIsGoneBinding
    private lateinit var intentKey : String //will help us to know where we can go in our try again selection
    //email , password
    private lateinit var email : String
    private lateinit var password : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNetworkIsGoneBinding.inflate(inflater)

        arguments?.let {
            intentKey = NetworkIsGoneFragmentArgs.fromBundle(it).intentKey
            if(intentKey == "please fill username field"){
                /*
                 * in second sign up we have to save email and password that we have from first sign up list
                 */
                try{
                    /*email = NetworkIsGoneFragmentArgs.fromBundle(it).email.toString()
                    password = NetworkIsGoneFragmentArgs.fromBundle(it).password.toString()*/
                }catch(e:Exception){
                    e.printStackTrace()
                }
            }
        }


        //try again
        binding.tryAgainLink.setOnClickListener {
            intentFunc(it)
        }

        //go home
        binding.goToTheHome.setOnClickListener {
            val intent = Intent(activity, FirstPageActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        return binding.root
    }


    /*
     * intentFunc will help us to set our navigation settings for back movement in network is gone
     */
    private fun intentFunc(it:View){

        when (intentKey) {
            "Home" -> {
                val intent = Intent(activity, FirstPageActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            "SignUp" -> {
                Navigation.findNavController(it).navigate(NetworkIsGoneFragmentDirections.actionNetworkIsGoneFragmentToSignUpFragment())
            }
            "ForgotPassword" -> {
                Navigation.findNavController(it).navigate(NetworkIsGoneFragmentDirections.actionNetworkIsGoneFragmentToForgotPasswordFragment())
            }
            "LogIn" -> {
                Navigation.findNavController(it).navigate(NetworkIsGoneFragmentDirections.actionNetworkIsGoneFragmentToLogInFragment())
            }
            "please fill username field" -> {
               // Navigation.findNavController(it).navigate(NetworkIsGoneFragmentDirections.actionNetworkIsGoneFragmentToSignUpSecondStepFragment(email , password))

            }
        }


    }

}