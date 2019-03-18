package com.repair.lsb.easyrepair.map;

import android.content.Context;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.util.List;

public class GetDistance {
    Context context;

    public GetDistance(Context context){
        this.context = context;
    }

    private AMapLocationClient aMapLocationClient;
    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    private AMapLocationListener mLocationListener;

    public AMapLocationClientOption mLocationOption = null;

    static {

    }

    public void k(){
        //初始化定位
        mLocationClient = new AMapLocationClient(context);
        mLocationClient.setLocationListener(mLocationListener);
        mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
//可在其中解析amapLocation获取相应内容。
                        Log.i("城市：纬度，经度", amapLocation.getAddress()+amapLocation.getLatitude()+amapLocation.getLongitude());

                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                    }
                }
            }
        };
        //设置定位回调监听
        mLocationOption = new AMapLocationClientOption();
        //mLocationOption.setLocationCacheEnable(false);
        mLocationOption.setInterval(2000);
        LocationManager locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        assert locationManager != null;
        List<String> providers = locationManager.getProviders(true);
        if(providers.contains(LocationManager.GPS_PROVIDER)){
            if(providers.contains(LocationManager.NETWORK_PROVIDER)){
                mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            }else
                mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);
        }else if(providers.contains(LocationManager.NETWORK_PROVIDER)){
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        }else{
            Toast.makeText(context,"没有打开GPS，数据网络",Toast.LENGTH_SHORT).show();
            return;
        }


        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
//启动定位
        //mLocationClient.startLocation();
    }

}
