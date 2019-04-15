package com.heima.advancedlight.section1;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.heima.advancedlight.R;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

public class PermissionActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    //string[]数组设置需要的权限
    String[] mPermissions = new String[]{Manifest.permission.CAMERA
            , Manifest.permission.READ_CONTACTS
            , Manifest.permission.ACCESS_FINE_LOCATION};

    static final int requestCode = 666;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        //请求权限
        if (EasyPermissions.hasPermissions(this, mPermissions)) {
            Toast.makeText(getApplicationContext(), "权限请求成功", Toast.LENGTH_SHORT).show();
        } else {
            //第二个参数是提示信息
            EasyPermissions.requestPermissions(this, "请给予照相机权限,否则app无法正常运行", requestCode, mPermissions);
        }

//        EasyPermissions.requestPermissions(
//                new PermissionRequest.Builder(this, requestCode, mPermissions)
//                        .setRationale("setRationale")
//                        .setPositiveButtonText("OK")
//                        .setNegativeButtonText("cancel")
//                        .build());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        // Some permissions have been granted
        // ...
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        // Some permissions have been denied
        // ...
    }
}
