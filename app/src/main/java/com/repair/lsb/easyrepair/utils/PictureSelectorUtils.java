package com.repair.lsb.easyrepair.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;


public class PictureSelectorUtils extends AppCompatActivity {


    public void takeCamera(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{
                    Manifest.permission.CAMERA
            },1);
        }else {
            PictureSelector.create(this).openCamera(PictureMimeType.ofImage());
        }
    }
}
