package com.awakenguys.kmitl.ladkrabangcountry;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
public class Maps_Activity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    //พิกัดรถตู้วิทยา
    private static final LatLng VIT_YA_VAN = new LatLng(13.728632,100.778115);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_);
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap map)
    {
        //สร้างหมุดสำหรับรถตู้วิทยา กำหนดค่าต่างๆของหมุด แล้วเพิ่มหมุดลงบนแผนที่
        MarkerOptions vityavan = new MarkerOptions();
        vityavan.position(VIT_YA_VAN);
        vityavan.title("วินรถตู้หน้าคณะวิทยาศาสตร์");
        map.addMarker(vityavan);
        //ย้ายกล้องไปที่วินวิทยา กำหนดซูมระดับ 5
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(VIT_YA_VAN,5));
        //ซุมมาที่ระดับ 13 แบบมีแอนิเมชั่น
        map.animateCamera(CameraUpdateFactory.zoomTo(15),200,null);
    }
}