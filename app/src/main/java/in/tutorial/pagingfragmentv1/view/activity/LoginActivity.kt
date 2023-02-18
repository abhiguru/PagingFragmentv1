package `in`.tutorial.pagingfragmentv1.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import `in`.tutorial.pagingfragmentv1.MyApplication
import `in`.tutorial.pagingfragmentv1.R
import `in`.tutorial.pagingfragmentv1.data.remote.Endpoint
import `in`.tutorial.pagingfragmentv1.data.remote.model.AuthToken
import `in`.tutorial.pagingfragmentv1.data.remote.repository.flow.FlowRepositoryImpl
import `in`.tutorial.pagingfragmentv1.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch
import java.io.IOException


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    var binding: ActivityLoginBinding? = null
    private var mGoogleSignInClient: GoogleSignInClient? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("960951383815-qgumjrbuqvnno78oo8o2hl3llgipaodo.apps.googleusercontent.com")
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        binding?.signInButton?.setOnClickListener(this)

    }
    override fun onStart() {
        super.onStart()
        val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)
        //updateUI(account)
    }
    override fun onClick(v: View?) {
        when(v?.id){
           R.id.sign_in_button -> signIn()
        }
    }
    val resLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK){
            val data: Intent? = it.data
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }else if(it.resultCode == Activity.RESULT_CANCELED){
            val data: Intent? = it.data
        }
    }
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)
            try {
                val networkService = (application as MyApplication).networkService
                var flowRepositoryImpl:FlowRepositoryImpl =
                                FlowRepositoryImpl(networkService)
                lifecycleScope.launch {
                    flowRepositoryImpl.postAuth("http://192.168.0.180:2828/auth/android/",
                        AuthToken(account.idToken.toString())
                    ).collect{
                        Log.e("Google", "Auth response "+it.authDetails.token)
                        Endpoint.AUTH_TOKEN = it.authDetails.token
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                }
            } catch (e: IOException) {
                Log.e("Google", "Error sending ID token to backend.", e)
            }

        } catch (e: ApiException) {
            Log.w("TAG", "signInResult:failed code=" + e.statusCode)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 1) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }
    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient!!.signInIntent
        //resLauncher.launch(signInIntent)
        startActivityForResult(signInIntent, 1);

    }
}