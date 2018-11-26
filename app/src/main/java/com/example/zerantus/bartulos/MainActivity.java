package com.example.zerantus.bartulos;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = ""+R.string.mainTAG;
    private FirebaseAuth mAuth;
    private EditText mEmailField,mPasswordField, mConfirmField;
    private Button mBtnSignIn, mBtnRegist;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mEmailField = (EditText) findViewById(R.id.mEmailField);
        mPasswordField = (EditText) findViewById(R.id.mPasswordField);
        mConfirmField = (EditText) findViewById(R.id.mConfirmPasswordField);
        mAuth = FirebaseAuth.getInstance();
        mBtnSignIn = (Button) findViewById(R.id.mBtnSignIn);
        mBtnRegist = (Button) findViewById(R.id.mBtnRegist);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    //startActivity(new Intent(MainActivity.this,Inicio.class));
                    //finish();//Esto no ira comentado en estado final, solo para muestra de loggin.
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    Toast.makeText(MainActivity.this, ""+R.string.sesionCaducada, Toast.LENGTH_SHORT).show();
                }
                // ...
            }
        };
        
        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn();
            }
        });
        
        mBtnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Resgistrar()){
                    Toast.makeText(MainActivity.this, ""+R.string.errorContraseña, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean Resgistrar() {
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        String confirmPassword = mConfirmField.getText().toString();
        try{
            if(password.equals(confirmPassword)){
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, ""+R.string.errorRegistro,Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                return true;
            }else{
                return false;
            }
        }catch(Exception ex){
            return false;
        }


    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void SignIn() {
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(MainActivity.this, ""+R.string.error,Toast.LENGTH_SHORT).show();
                        }else{
                            startActivity(new Intent(MainActivity.this,Inicio.class));
                            finish();
                        }
                    }
                });
    }
}
