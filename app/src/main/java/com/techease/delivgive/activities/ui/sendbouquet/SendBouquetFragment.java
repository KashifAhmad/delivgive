package com.techease.delivgive.activities.ui.sendbouquet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.squareup.picasso.Picasso;
import com.techease.delivgive.R;
import com.techease.delivgive.activities.MakeAccountPremiumActivity;
import com.techease.delivgive.activities.PaymentOptionActivity;
import com.techease.delivgive.adapters.PlansAdapter;
import com.techease.delivgive.models.sendBouquetModels.SendBouquetResponse;
import com.techease.delivgive.utils.AppRepository;
import com.techease.delivgive.utils.Connectivity;
import com.techease.delivgive.utils.ProgressView;
import com.techease.delivgive.utils.networking.BaseNetworking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SendBouquetFragment extends Fragment implements View.OnClickListener {

    private SendBouquetViewModel mViewModel;
    private View view;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.ivBouquet)
    ImageView ivBouquet;
    @BindView(R.id.etFrom)
    EditText etFrom;
    @BindView(R.id.etFromPhoneNo)
    EditText etFromPhoneNumber;
    @BindView(R.id.etTo)
    EditText etTo;
    @BindView(R.id.etToPhoneNo)
    EditText etToPhoneNumber;
    @BindView(R.id.etTextHere)
    EditText etDescription;
    @BindView(R.id.btnSendNow)
    Button btnSendNow;
    @BindView(R.id.tvLetsCreate)
    TextView tvLetsCreate;
    private String fromName, fromPhone, toName, toPhone, description, freeFilePath;
    private File imgFile, freeImageFile;
    private boolean valid = false;

    public static SendBouquetFragment newInstance() {
        return new SendBouquetFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.send_bouquet_fragment, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        ButterKnife.bind(this, view);
        btnSendNow.setOnClickListener(this);
        tvLetsCreate.setText(AppRepository.mBouquetSendingTitle(getActivity()));
        ivBack.setOnClickListener(this);
        if (AppRepository.isFree(getActivity()) && ivBouquet.getDrawable() != null) {
            Picasso.get().load(AppRepository.mGetValue(getActivity()).getString("picLink", "")).into(ivBouquet);
            saveImage(ivBouquet);
//            saveBitMap(getActivity(), ivBouquet);
            AppRepository.mPutValue(getActivity()).putBoolean("fromFree", false).commit();
        } else {
            imgFile = new File(AppRepository.mGetValue(getActivity()).getString("picPath", ""));
        }
        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            ivBouquet.setImageBitmap(myBitmap);


        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SendBouquetViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSendNow:
                if (isValid()) {
                    if (Connectivity.isConnected(getActivity())) {
                        ProgressView.loader(getActivity());
                        sendBouquet();
                    }
                }
                break;
            case R.id.ivBack:
                getActivity().onBackPressed();

        }
    }

    private void saveImage(ImageView ivInput) {
        BitmapDrawable draw = (BitmapDrawable) ivInput.getDrawable();
        Bitmap bitmap = draw.getBitmap();
        FileOutputStream outStream = null;
        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File(sdCard.getAbsolutePath() + "/" + getString(R.string.app_name));
//        String filename = dir.getPath() + File.separator + System.currentTimeMillis() + ".jpg";


        dir.mkdirs();
        String fileName = String.format("%d.jpg", System.currentTimeMillis());

        freeImageFile = new File(dir, fileName);
        Log.d("zma fileName", freeImageFile.getAbsolutePath());
        imgFile = freeImageFile;


        try {
            outStream = new FileOutputStream(freeImageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        try {
////            outStream.flush();
////            outStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    private void sendBouquet() {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imgFile);
        final MultipartBody.Part mImageFile = MultipartBody.Part.createFormData("image", imgFile.getName(), requestFile);
        RequestBody mTextImage = RequestBody.create(MediaType.parse("text/plain"), "upload_test");
        RequestBody mFromName = RequestBody.create(MediaType.parse("multipart/form-data"), fromName);
        RequestBody mFromPhone = RequestBody.create(MediaType.parse("multipart/form-data"), fromPhone);
        RequestBody mToName = RequestBody.create(MediaType.parse("multipart/form-data"), toName);
        RequestBody mToPhone = RequestBody.create(MediaType.parse("multipart/form-data"), toPhone);
        RequestBody mDescription = RequestBody.create(MediaType.parse("multipart/form-data"), description);
        RequestBody mUserID = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(AppRepository.mUserID(getActivity())));
        Call<SendBouquetResponse> sendBouquets = BaseNetworking.apiServices().sendBouquet(mUserID, mImageFile, mTextImage,
                mFromName, mFromPhone, mToName, mToPhone, mDescription);
        sendBouquets.enqueue(new Callback<SendBouquetResponse>() {
            @Override
            public void onResponse(Call<SendBouquetResponse> call, Response<SendBouquetResponse> response) {
                Log.d("zma response", response.body().getMessage());
                ProgressView.mDialog.dismiss();
                if (response.body().getStatus()) {
                    getActivity().finish();
                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                    sendIntent.setData(Uri.parse("smsto:" + toPhone));
                    sendIntent.putExtra("sms_body", response.body().getData().getPageUrl());
                    startActivity(sendIntent);
                    AppRepository.mPutValue(getActivity()).putString("mBouquetLink", response.body().getData().getPageUrl()).commit();
                } else {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    if (response.body().getMessage().contains("No Subscription")) {
                        startActivity(new Intent(getActivity(), MakeAccountPremiumActivity.class));
                        getActivity().finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<SendBouquetResponse> call, Throwable t) {
                ProgressView.mDialog.dismiss();

            }
        });

    }

    private boolean isValid() {
        valid = true;
        fromName = etFrom.getText().toString();
        fromPhone = etFromPhoneNumber.getText().toString();
        toName = etTo.getText().toString();
        toPhone = etToPhoneNumber.getText().toString();
        description = etDescription.getText().toString();
        if (fromName.isEmpty()) {
            etFrom.setError("Please provide sender name");
            valid = false;
        } else {
            etFrom.setError(null);
        }
        if (fromPhone.isEmpty()) {
            etFromPhoneNumber.setError("Invalid phone number");
            valid = false;
        } else {
            etFromPhoneNumber.setError(null);
        }
        if (toName.isEmpty()) {
            etTo.setError("Please provide receiver name");
            valid = false;
        } else {
            etTo.setError(null);
        }
        if (toPhone.isEmpty()) {
            etToPhoneNumber.setError("Invalid phone number");
            valid = false;
        } else {
            etToPhoneNumber.setError(null);
        }
        if (description.isEmpty() || description.length() < 10) {
            etDescription.setError("Please write a valid description");
            valid = false;
        } else {
            etDescription.setError(null);
        }
        return valid;
    }
}
