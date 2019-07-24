package com.study.gst.mmpapp.Intro;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;
import com.study.gst.mmpapp.NearInfo.NearPlaceActivity;
import com.study.gst.mmpapp.R;
import com.study.gst.mmpapp.model.Event;
import com.study.gst.mmpapp.model.NetworkService;
import com.study.gst.mmpapp.model.Tour;
import com.study.gst.mmpapp.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupActivity extends AppCompatActivity {

    private static final int PICK_FROM_ALBUM = 10;
    private EditText email;
    private EditText name;
    private EditText password;
    private Button signup;
    private Boolean stop_flag=false;

    private ImageView profile;
    private Uri imageUri;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        FirebaseRemoteConfig mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        String splash_background = mFirebaseRemoteConfig.getString(getString(R.string.rc_color));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor(splash_background));
        }

        profile = (ImageView) findViewById(R.id.signupActivity_imageview_profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent, PICK_FROM_ALBUM);
            }
        });
        email = (EditText) findViewById(R.id.signupActivity_edittext_email);
        name = (EditText) findViewById(R.id.signupActivity_edittext_name);
        password = (EditText) findViewById(R.id.signupActivity_edittext_password);
        signup = (Button) findViewById(R.id.signupActivity_button_signup);
        signup.setBackgroundColor(Color.parseColor(splash_background));
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (email.getText().toString() == null || name.getText().toString() == null || password.getText().toString() == null || imageUri == null) {
                    return;
                }

                FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                final String uid = task.getResult().getUser().getUid();
                                FirebaseStorage.getInstance().getReference().child("userImages").child(uid).putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                                        String imageUrl = task.getResult().getStorage().getDownloadUrl().toString();

                                        UserModel userModel = new UserModel();
                                        userModel.userName = name.getText().toString();
                                        userModel.profileImageUrl = imageUrl;

                                        FirebaseDatabase.getInstance().getReference().child("users").child(uid).setValue(userModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                new JSONTask().execute(email.getText().toString());
                                                while(true) {
                                                    if (stop_flag == true) {
                                                        SignupActivity.this.finish();
                                                    }
                                                    try {
                                                        Thread.sleep(1000);
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                        });


                                    }
                                });


                            }
                        });
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PICK_FROM_ALBUM && resultCode == RESULT_OK) {
            profile.setImageURI(data.getData()); // 가운데 뷰를 바꿈
            imageUri = data.getData();  // 이미지 경로 원본


        }
    }

    public class JSONTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... userid) {
            init();
            NetworkService service = retrofit.create(NetworkService.class);
            Log.i("tag", "lopal insertuser"+userid[0]);
            Call<Event> call = service.insert_user(userid[0]);
            Log.i("tag", "lopal insertuser"+userid[0]);
            return "done";
        }

        //doInBackground메소드가 끝나면 여기로 와서 텍스트뷰의 값을 바꿔준다.
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.i("result", "lopal onpostExecute" +"result");

            stop_flag = true;

        }

    }



    public void init() {
        // GSON 컨버터를 사용하는 REST 어댑터 생성
        retrofit = new Retrofit.Builder()
                .baseUrl("http://106.10.35.40:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }



}
