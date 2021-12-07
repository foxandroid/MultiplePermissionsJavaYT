package com.example.multiplepermissionsjavayt;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher<String[]> mPermissionResultLauncher;
    private boolean isReadPermissionGranted = false;
    private boolean isLocationPermissionGranted = false;
    private boolean isRecordPermissionGranted = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPermissionResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
            @Override
            public void onActivityResult(Map<String, Boolean> result) {

                if (result.get(Manifest.permission.READ_EXTERNAL_STORAGE) != null){

                    isReadPermissionGranted = result.get(Manifest.permission.READ_EXTERNAL_STORAGE);

                }

                if (result.get(Manifest.permission.ACCESS_FINE_LOCATION) != null){

                    isReadPermissionGranted = result.get(Manifest.permission.ACCESS_FINE_LOCATION);

                }

                if (result.get(Manifest.permission.RECORD_AUDIO) != null){

                    isReadPermissionGranted = result.get(Manifest.permission.RECORD_AUDIO);

                }

            }
        });

        requestPermission();

    }

    private void requestPermission(){

        isReadPermissionGranted = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED;

        isLocationPermissionGranted = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED;

        isRecordPermissionGranted = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED;

        List<String> permissionRequest = new ArrayList<String>();

        if (!isReadPermissionGranted){

            permissionRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        }

        if (!isLocationPermissionGranted){

            permissionRequest.add(Manifest.permission.ACCESS_FINE_LOCATION);

        }

        if (!isRecordPermissionGranted){

            permissionRequest.add(Manifest.permission.RECORD_AUDIO);

        }

        if (!permissionRequest.isEmpty()){

            mPermissionResultLauncher.launch(permissionRequest.toArray(new String[0]));

        }


    }
}