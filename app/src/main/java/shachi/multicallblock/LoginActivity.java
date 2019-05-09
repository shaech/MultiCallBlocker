package shachi.multicallblock;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInAccount account;
    GoogleSignInOptions gso;
    String TAG = "MainActivity";
    int RC_SIGN_IN = 0;

    ConstraintLayout mConstraintLayout;
    TextView mHeader, mMessage, mforgotPass, mOtpLogin, mSkipSignIn;
    Button mSignin, mSignUp;
    SignInButton mGoogleSignInButton;
    EditText mPhoneNumber, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mConstraintLayout = findViewById(R.id.constraintLayout);

        mHeader = findViewById(R.id.tv_label);
        mMessage = findViewById(R.id.tv_msg);
        mSkipSignIn = findViewById(R.id.skipLogin);
        mforgotPass = findViewById(R.id.tv_forgot_pass);
        mOtpLogin = findViewById(R.id.otp_login);
        mGoogleSignInButton = findViewById(R.id.googleSignInButton);

        mSignin = findViewById(R.id.signinButton);
        mSignUp = findViewById(R.id.signupButton);

        mPhoneNumber = findViewById(R.id.phoneNumber);
        mPassword = findViewById(R.id.password);


        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        account = GoogleSignIn.getLastSignedInAccount(this);
        // updateUI(account);   Update UI accordingly  Shachi


        mforgotPass.setOnClickListener(this);
        mOtpLogin.setOnClickListener(this);
        mSkipSignIn.setOnClickListener(this);

        mSignin.setOnClickListener(this);
        mSignUp.setOnClickListener(this);

        mGoogleSignInButton.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_forgot_pass:
                 Snackbar snackbarFP = Snackbar.make(mConstraintLayout,"Forget Password",Snackbar.LENGTH_LONG);
                 snackbarFP.show();
                //Shachi
                break;
            case R.id.otp_login:
                Snackbar snackbarOTP = Snackbar.make(mConstraintLayout,"OTP Login",Snackbar.LENGTH_LONG);
                snackbarOTP.show();
                //Shachi
                break;
            case R.id.skipLogin:
                Snackbar snackbarskip = Snackbar.make(mConstraintLayout,"skipLogin",Snackbar.LENGTH_LONG);
                snackbarskip.show();
                //Shachi
                break;
            case R.id.signinButton:
                Snackbar snackbarsignin = Snackbar.make(mConstraintLayout,"signinButton",Snackbar.LENGTH_LONG);
                snackbarsignin.show();
                //Shachi
                break;
            case R.id.signupButton:
                Snackbar snackbarsignup = Snackbar.make(mConstraintLayout,"signupButton",Snackbar.LENGTH_LONG);
                snackbarsignup.show();
                //Shachi
                break;
            case R.id.googleSignInButton:
                signIn();  //Shachi
                break;

        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e(TAG, "Request" + requestCode + "\t Response: " + resultCode + "\t data"  + data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }


    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {

        Log.e(TAG,completedTask + "");
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            Log.e("Shachi...","" + account);
            // Signed in successfully, show authenticated UI.
            //updateUI(account); Shachi
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e(TAG, "signInResult:failed code=" + e.getStatusCode());
            // updateUI(null);   Shachi
        }
    }

}
