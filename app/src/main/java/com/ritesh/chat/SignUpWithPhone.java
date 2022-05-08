//package com.ritesh.chat;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import com.google.firebase.FirebaseException;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.PhoneAuthCredential;
//import com.google.firebase.auth.PhoneAuthOptions;
//import com.google.firebase.auth.PhoneAuthProvider;
//import com.hbb20.CountryCodePicker;
//
//import java.util.concurrent.TimeUnit;
//
//public class SignUpWithPhone extends AppCompatActivity {
//
//    EditText mgetphonenumber;
//    android.widget.Button msendotp;
//    CountryCodePicker mcountrycodepicker;
//
//    String countrycode;
//    String phonenumber;
//
//    FirebaseAuth firebaseAuth;
//    ProgressBar mprogressbarofmainactivity;
//
//
//    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
//    String codesent;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up_with_phone);
//
//        mcountrycodepicker = findViewById(R.id.countrycodepicker);
//        msendotp = findViewById(R.id.sendotpbutton);
//        mgetphonenumber = findViewById(R.id.getphonenumber);
//        mprogressbarofmainactivity = findViewById(R.id.progressbarofmainactivity);
//
//        firebaseAuth = FirebaseAuth.getInstance();
//
//        countrycode = mcountrycodepicker.getDefaultCountryCodeWithPlus();
//
//        mcountrycodepicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
//            @Override
//            public void onCountrySelected() {
//                countrycode = mcountrycodepicker.getDefaultCountryCodeWithPlus();
//            }
//        });
//
//        msendotp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String number;
//                number = mgetphonenumber.getText().toString();
//                if (number.isEmpty()){
//                    Toast.makeText(getApplicationContext(),"Please Enter your number", Toast.LENGTH_SHORT).show();
//
//                }
//                else if (number.length()<10){
//                    Toast.makeText(getApplicationContext(),"Please Enter correct number", Toast.LENGTH_SHORT).show();
//                }
//                else {
//
//                    mprogressbarofmainactivity.setVisibility(view.VISIBLE);
//                    phonenumber = countrycode+number;
//                    //        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(firebaseAuth)
////                            .setPhoneNumber(phonenumber)
////                            .set
//
//                    PhoneAuthOptions options = PhoneAuthOptions.newBuilder(firebaseAuth)
//                            .setPhoneNumber(phonenumber)       // Phone number to verify
//                            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//                            .setActivity(SignUpWithPhone.this)                 // Activity (for callback binding)
//                            .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
//                            .build();
//
//                    PhoneAuthProvider.verifyPhoneNumber(options);
//
//                }
//
//
//            }
//        });
//
//        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//            @Override
//            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//                // automatically fetch code here
//
//
//            }
//
//            @Override
//            public void onVerificationFailed(@NonNull FirebaseException e) {
//
//            }
//
//            @Override
//            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                super.onCodeSent(s, forceResendingToken);// if this function will run then its means the code is sent successfully to the user by the firebase
//                Toast.makeText(getApplicationContext(),"OTP is sent",Toast.LENGTH_SHORT).show();
//                mprogressbarofmainactivity.setVisibility(View.INVISIBLE);
//                codesent = s;
//                Intent intent = new Intent(SignUpWithPhone.this,optVerification.class);
//                intent.putExtra("otp",codesent);
//                startActivity(intent);
//
//
//            }
//        };
//
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (FirebaseAuth.getInstance().getCurrentUser()!= null){
//
//            Intent intent = new Intent(SignUpWithPhone.this,MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//
//        }
//
//    }
//
//}