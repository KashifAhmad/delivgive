package com.techease.delivgive.activities;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.techease.delivgive.R;
import com.techease.delivgive.adapters.RecentlySentAdapters;
import com.techease.delivgive.models.getUserBuckets.Datum;
import com.techease.delivgive.models.getUserBuckets.GetUserBucketsResponse;
import com.techease.delivgive.models.getUserProfileModel.GetUserProfileResponse;
import com.techease.delivgive.models.profilePicUpdateModels.ProfilePicUpdateResponse;
import com.techease.delivgive.utils.AppRepository;
import com.techease.delivgive.utils.networking.BaseNetworking;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener, ActivityCompat.OnRequestPermissionsResultCallback {
    final int CAMERA_CAPTURE = 1001;
    final int RESULT_LOAD_IMAGE = 1002;
    final int PERMISSIONS = 1003;

    @BindView(R.id.ivSettings)
    ImageView ivSetting;
    @BindView(R.id.civProfile)
    CircleImageView civProfile;
    @BindView(R.id.ivChangeProfile)
    ImageView ivChangeProfile;
    @BindView(R.id.tvUserName)
    TextView tvUserName;
    @BindView(R.id.tvLocation)
    TextView tvLocation;
    @BindView(R.id.rvRecentlyUsed)
    RecyclerView rvRecentlySent;
    private View view;
    private File sourceFile;
    List<Datum> bucketsList = new ArrayList<>();
    RecentlySentAdapters adapters;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        ButterKnife.bind(this, view);
        ivSetting.setOnClickListener(this);
        ivChangeProfile.setOnClickListener(this);
        tvUserName.setText(AppRepository.mUserName(getActivity()));
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.INTERNET},
                PERMISSIONS);
        if (AppRepository.mUserProfilePic(getActivity()).length() != 0) {
            Picasso.get().load(AppRepository.mUserProfilePic(getActivity())).into(civProfile);
        }
        getUserProfile();

        rvRecentlySent.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapters = new RecentlySentAdapters(getActivity(), bucketsList);
        rvRecentlySent.setAdapter(adapters);
        getBuckets();
    }

    private void getBuckets() {
        Call<GetUserBucketsResponse> buckets = BaseNetworking.apiServices().userBuckets(AppRepository.mUserID(getActivity()));
        buckets.enqueue(new Callback<GetUserBucketsResponse>() {
            @Override
            public void onResponse(Call<GetUserBucketsResponse> call, Response<GetUserBucketsResponse> response) {
                if (response.isSuccessful()) {
                    bucketsList.addAll(response.body().getData());
                    adapters.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<GetUserBucketsResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {


        switch (requestCode) {
            case PERMISSIONS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivSettings:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
            case R.id.ivChangeProfile:
                cameraBuilder();
        }
    }

    public void cameraIntent() {
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(captureIntent, CAMERA_CAPTURE);
    }

    public void galleryIntent() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    public void cameraBuilder() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getActivity());
        pictureDialog.setTitle("Open");
        String[] pictureDialogItems = {
                "\tGallery",
                "\tCamera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                galleryIntent();

                                break;
                            case 1:
                                cameraIntent();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    @SuppressLint("SetTextI18n")
    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(projection[0]);
        String filePath = cursor.getString(columnIndex);
        civProfile.setImageBitmap(BitmapFactory.decodeFile(filePath));

        return cursor.getString(column_index);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && null != data) {
            Uri selectedImageUri = data.getData();
            String imagepath = getPath(selectedImageUri);
            sourceFile = new File(imagepath);
//            try {
//                sourceFile = new Compressor(getActivity()).compressToFile(sourceFile);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

        } else if (resultCode == RESULT_OK && requestCode == CAMERA_CAPTURE && data != null) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();

            thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
            sourceFile = new File(Environment.getExternalStorageDirectory(),
                    System.currentTimeMillis() + ".jpg");
            FileOutputStream fo;
            try {
                sourceFile.createNewFile();
//                sourceFile = new Compressor(getActivity()).compressToFile(sourceFile);
                fo = new FileOutputStream(sourceFile);
                fo.write(bytes.toByteArray());
                fo.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            civProfile.setImageBitmap(thumbnail);
        }
        updateProfilePic();

    }

    private void updateProfilePic() {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), sourceFile);
        final MultipartBody.Part mImageFile = MultipartBody.Part.createFormData("profile_picture", sourceFile.getName(), requestFile);
        RequestBody mTextImage = RequestBody.create(MediaType.parse("text/plain"), "upload_test");
        RequestBody mUserID = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(AppRepository.mUserID(getActivity())));
        Call<ProfilePicUpdateResponse> sendBouquets = BaseNetworking.apiServices().profilePicUpdate(mUserID, mImageFile, mTextImage);
        sendBouquets.enqueue(new Callback<ProfilePicUpdateResponse>() {
            @Override
            public void onResponse(Call<ProfilePicUpdateResponse> call, Response<ProfilePicUpdateResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    AppRepository.mPutValue(getActivity()).putString("profilePic", response.body().getData().getProfilePicture()).commit();
                }
            }

            @Override
            public void onFailure(Call<ProfilePicUpdateResponse> call, Throwable t) {

            }
        });

    }

    private void getUserProfile() {
        Call<GetUserProfileResponse> userProfile = BaseNetworking.apiServices().getUserProfile(AppRepository.mUserID(getActivity()));
        userProfile.enqueue(new Callback<GetUserProfileResponse>() {
            @Override
            public void onResponse(Call<GetUserProfileResponse> call, Response<GetUserProfileResponse> response) {
                if (response.isSuccessful()) {
                    AppRepository.mPutValue(getActivity()).putString("mUserName", response.body().getData().get(0).getFullname()).commit();
                    AppRepository.mPutValue(getActivity()).putString("mUserEmail", response.body().getData().get(0).getEmail()).commit();
                    AppRepository.mPutValue(getActivity()).putString("mUserPhoneNumber", response.body().getData().get(0).getPhoneNumber()).commit();
                    AppRepository.mPutValue(getActivity()).putString("mUserDoB", response.body().getData().get(0).getDob()).commit();


                    tvUserName.setText(response.body().getData().get(0).getFullname());
                    Picasso.get().load(response.body().getData().get(0).getProfilePicture()).into(civProfile);
                }
            }

            @Override
            public void onFailure(Call<GetUserProfileResponse> call, Throwable t) {

            }
        });
    }
}
