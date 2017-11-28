package soufun.com.permissions_demo;

import android.Manifest;
import android.app.Activity;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class MainActivity extends Activity implements View.OnClickListener, PermissionsUtils.ApplyPermission {
    private Button call_btn;
    private int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        call_btn = (Button) findViewById(R.id.call_btn);
        call_btn.setOnClickListener(this);
        PermissionsUtils.setApplyPermission(this);
    }

    @Override
    public void doSuccess() {
        callPhone();
    }

    @Override
    public void doFailed() {
        Log.i("wrt","");
    }

    @Override
    public void onClick(View v) {
        PermissionsUtils.needPermission(MainActivity.this
                , Manifest.permission.CALL_PHONE, MY_PERMISSIONS_REQUEST_CALL_PHONE);

    }

    private void callPhone() {
        try{
            Intent intent = new Intent(Intent.ACTION_CALL);
            Uri data = Uri.parse("tel:" + "10086");
            intent.setData(data);
            startActivity(intent);
        }catch (SecurityException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionsUtils.onRequestPermissionsResult(MainActivity.this,requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
