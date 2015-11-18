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
public class MotorcycleMap extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    //พิกัดรถตู้วิทยา
    private static final LatLng RNP = new LatLng(13.727885,100.764432);
    private static final LatLng KAY3 = new LatLng(13.727968,100.769679);
    private static final LatLng LOAH = new LatLng(13.727989,100.772917);
    private static final LatLng LIBRARY = new LatLng(13.728010,100.777847);
    private static final LatLng FBT = new LatLng(13.722517,100.780116);


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
        MarkerOptions rnp = new MarkerOptions();
        rnp.position(RNP);
        rnp.title("วินมอเตอร์ไซต์ RNP");

        MarkerOptions kay3 = new MarkerOptions();
        kay3.position(KAY3);
        kay3.title("วินมอเตอร์ไซต์เกกี3");

        MarkerOptions loah = new MarkerOptions();
        loah.position(LOAH);
        loah.title("วินมอเตอร์ไซต์หน้าอาคารเรียนรวม 12 ชั้น (ตึกโหล)");

        MarkerOptions library = new MarkerOptions();
        library.position(LIBRARY);
        library.title("วินมอเตอร์ไซต์ตรงข้ามหอสมุด");

        MarkerOptions fbt = new MarkerOptions();
        fbt.position(FBT);
        fbt.title("วินมอเตอร์ไซต์FBT");

        map.addMarker(rnp);
        //ย้ายกล้องไปที่วินวิทยา กำหนดซูมระดับ 5
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(RNP, 5));

        map.addMarker(kay3);
        //ย้ายกล้องไปที่วินวิทยา กำหนดซูมระดับ 5
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(KAY3,5));

        map.addMarker(loah);
        //ย้ายกล้องไปที่วินวิทยา กำหนดซูมระดับ 5
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LOAH,5));

        map.addMarker(library);
        //ย้ายกล้องไปที่วินวิทยา กำหนดซูมระดับ 5
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LIBRARY,5));

        map.addMarker(fbt);
        //ย้ายกล้องไปที่วินวิทยา กำหนดซูมระดับ 5
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(FBT,5));

        //ซุมมาที่ระดับ 13 แบบมีแอนิเมชั่น
        map.animateCamera(CameraUpdateFactory.zoomTo(15),200,null);
    }
}