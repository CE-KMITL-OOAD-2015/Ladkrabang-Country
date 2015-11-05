package com.awakenguys.kmitl.ladkrabangcountry;

import android.app.ListActivity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import static com.awakenguys.kmitl.ladkrabangcountry.Constants.FACULTY;
import static com.awakenguys.kmitl.ladkrabangcountry.Constants.LAT;
import static com.awakenguys.kmitl.ladkrabangcountry.Constants.LNG;
import static com.awakenguys.kmitl.ladkrabangcountry.Constants.TABLE_NAME;
import static com.awakenguys.kmitl.ladkrabangcountry.Constants.TITLE;
import static com.awakenguys.kmitl.ladkrabangcountry.Constants._ID;

public class BuildingProvider extends ListActivity {
    private static String[] COLUMNS = {BaseColumns._ID, Constants.TITLE, Constants.FACULTY, Constants.LAT, Constants.LNG};
    private static int[] VIEWS = {R.id.title};
    private BuildingDB buildingDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);
        buildingDB = new BuildingDB(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String contains = bundle.getString("contains");
            showFilter(contains);
        }
    }

    public Cursor getAll() {
        SQLiteDatabase db = buildingDB.getReadableDatabase();
        return db.query(TABLE_NAME, COLUMNS, null, null, null, null, Constants.TITLE + (" ASC"));
    }

    public Cursor getByFaculty(String faculty) {
        SQLiteDatabase db = buildingDB.getReadableDatabase();
        return db.query(TABLE_NAME, COLUMNS, FACULTY + " LIKE ?", new String[]{"%"+faculty+"%"},
                null, null, Constants.TITLE + " ASC");
    }

    public Cursor getById(long id) {
        SQLiteDatabase db = buildingDB.getReadableDatabase();
        return db.query(TABLE_NAME, COLUMNS, "_id=?", new String[]{(int) id + ""},
                null, null, Constants.TITLE + " ASC");
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String title = null;
        float lat = 0, lng = 0;
        Cursor cursor = getById(id);

        if (cursor.moveToFirst() && cursor.getCount() >= 1)
            title = cursor.getString(1);
        if (cursor.moveToFirst() && cursor.getCount() >= 1)
            lat = cursor.getFloat(3);
        if (cursor.moveToFirst() && cursor.getCount() >= 1)
            lng = cursor.getFloat(4);
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:" + lat + ", " + lng + "?q=" + lat + ", " + lng + " (" + title + ")"));
        intent.setComponent(new ComponentName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity"));
        startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showFilter(String str) {
        Cursor cursor;
        try {
            if (str.equals("")) {
                cursor = getAll();
            } else {
                cursor = getByFaculty(str);
            }
            show(cursor);
        } finally {
            buildingDB.close();
        }
    }

    private void show(Cursor cursor) {
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.building_all,
                cursor, new String[]{Constants.TITLE}, VIEWS, 0);
        setListAdapter(adapter);
    }
}

class BuildingDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "building.db";
    private static final int DATABASE_VERSION = 1;

    public BuildingDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE + " TEXT, " + FACULTY + " TEXT, " + LAT + " FLOAT, " + LNG + " FLOAT);");
        addAll(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    private void addAll(SQLiteDatabase db) {
        add(db, "อาคารเรียนรวม 12 ชั้น(E12)", "วิศวกรรมศาสตร์", 13.727621f, 100.772344f);
        add(db, "อาคารปฏิบัติการรวม CCA", "วิศวกรรมศาสตร์", 13.726530f, 100.772984f);
        add(db, "โรงอาหาร C", "วิศวกรรมศาสตร์", 13.726863f, 100.772091f);
        add(db, "อาคารยิมเนเซียม 1", "ทั่วไป", 13.726826f, 100.773569f);
        add(db, "อาคารภาควิชาวิศวกรรมอุตสาหการ", "วิศวกรรมศาสตร์", 13.727688f, 100.773359f);
        add(db, "อาคารภาควิชาวิศวกรรมเครื่องกล(ME)", "วิศวกรรมศาสตร์", 13.727669f, 100.773965f);
        add(db, "อาคารภาควิชาวิศวกรรมการวัดคุม", "วิศวกรรมศาสตร์", 13.727442f, 100.774419f);
        add(db, "โรงอาหาร J", "วิศวกรรมศาสตร์", 13.727711f, 100.774730f);
        add(db, "อาคารภาควิชาวิศวกรรมอิเล็กทรอนิกส์", "วิศวกรรมศาสตร์", 13.727556f, 100.775307f);
        add(db, "อาคารภาควิชาวิศวกรรมโทรคมนาคม", "วิศวกรรมศาสตร์", 13.727472f, 100.776110f);
        add(db, "หอประชุมสถาบัน", "ทั่วไป", 13.727360f, 100.777301f);
        add(db, "สำนักงานคณบดีคณะวิศวกรรมศาสตร์", "วิศวกรรมศาสตร์", 13.726974f, 100.776373f);
        add(db, "โรงอาหาร A", "วิศวกรรมศาสตร์", 13.726954f, 100.775664f);
        add(db, "อาคารภาควิชาวิศวกรรมโยธา", "วิศวกรรมศาสตร์", 13.726951f, 100.774367f);
        add(db, "อาคารเฉลิมพระเกียรติ 84 พรรษาภูมิพลมหาราชา(HM)", "วิศวกรรมศาสตร์", 13.726552f, 100.775154f);
        add(db, "อาคารปฏิบัติการไฟฟ้า(L)", "วิศวกรรมศาสตร์", 13.728486f, 100.775537f);
        add(db, "โรงอาหาร L", "วิศวกรรมศาสตร์", 13.728760f, 100.775207f);
        add(db, "อาคารปฏิบัติการรวมวิศวกรรมศาสตร์ 2(ECC)", "วิศวกรรมศาสตร์", 13.729158f, 100.775511f);
        add(db, "อาคารบูรณาการ", "สถาปัตยกรรมศาสตร์", 13.725755f, 100.773841f);
        add(db, "อาคารเรียนรวมสถาปัตยกรรมศาสตร์", "สถาปัตยกรรมศาสตร์", 13.725206f, 100.775100f);
        add(db, "หอประชุมศาสตราจารย์ประสม รังสิโรจน์", "สถาปัตยกรรมศาสตร์", 13.725689f, 100.775117f);
        add(db, "สำนักงานคณบดีคณบดีสถาปัตยกรรมศาสตร์", "สถาปัตยกรรมศาสตร์", 13.725275f, 100.776649f);
        add(db, "Convention Hall", "ทั่วไป", 13.726469f, 100.779294f);
        add(db, "อาคารสำนักหอสมุดกลาง", "ทั่วไป", 13.727659f, 100.778589f);
        add(db, "อาคารกรมหลวงนราธิวาสราชนครินทร์(สำนักงานอธิการบดี)", "ทั่วไป", 13.730806f, 100.777616f);
        add(db, "หอพักนักศึกษา(เก่า)", "ทั่วไป", 13.728962f, 100.773941f);
        add(db, "หอพักนักศึกษา(ใหม่)", "ทั่วไป", 13.729640f, 100.774683f);
        add(db, "สมาคมศิษย์เก่า", "ทั่วไป", 13.731067f, 100.774665f);
        add(db, "อาคารเรียนรวมสมเด็จพระเทพฯ", "ทั่วไป", 13.730115f, 100.776802f);
        add(db, "อาคารเฉลิมพระเกียรติ 55 พรรษา สมเด็จพระเทพฯ", "ทั่วไป", 13.729952f, 100.775283f);
        add(db, "อาคารองค์การนักศึกษา", "ทั่วไป", 13.728772f, 100.778624f);
        add(db, "อาคารสำนักบริการคอมพิวเตอร์", "ทั่วไป", 13.731218f, 100.780278f);
        add(db, "อาคารวิทยาลัยการบริหารและจัดการ", "วิทยาลัยการบริหารและการจัดการ", 13.731218f, 100.780278f);
        add(db, "อาคารหอพระราชประวัติ ร.4", "ทั่วไป", 13.731213f, 100.778577f);
        add(db, "สนามกีฬากลางพระจอมเกล้าลาดกระบัง", "ทั่วไป", 13.730202f, 100.772183f);
        add(db, "อาคารยิมเนเซียม 2", "ทั่วไป", 13.728669f, 100.772869f);
        add(db, "อาคารฝึกงานซ่อมสร้างวิทยาศาสตร์", "วิทยาศาสตร์", 13.729298f, 100.778598f);
        add(db, "อาคารคณะวิทยาศาสตร์", "วิทยาศาสตร์", 13.729261f, 100.779189f);
        add(db, "อาคารหอประชุมจุฬาภรณ์วลัยลักษณ์", "วิทยาศาสตร์", 13.729708f, 100.779698f);
        add(db, "อาคารจุฬาภรณ์วลัยลักษณ์ 1", "วิทยาศาสตร์", 13.729658f, 100.779978f);
        add(db, "หอประชุมจุฬาพรณ์", "วิทยาศาสตร์", 13.729842f, 100.779333f);
        add(db, "อาคารจุฬาภรณ์วลัยลักษณ์ 2", "วิทยาศาสตร์", 13.730232f, 100.779318f);
        add(db, "อาคารปฏิบัติการใหม่", "วิทยาศาสตร์", 13.729046f, 100.779856f);
        add(db, "อาคารกิจกรรม", "วิทยาศาสตร์", 13.728947f, 100.778425f);
        add(db, "อาคารปฏิบัติการพิเศษจอมไตร", "วิทยาศาสตร์", 13.729574f, 100.780795f);
        add(db, "อาคารคณะเทคโนโลยีสารสนเทศ", "เทคโนโลยีสารสนเทศ", 13.730976f, 100.781116f);
        add(db, "โรงอาหารคณะเทคโนโลยีสารสนเทศ", "เทคโนโลยีสารสนเทศ์", 13.731161f, 100.782077f);
        add(db, "โรงอาหารคณะสถาปัตยกรรมศาสตร์", "สถาปัตยกรรมศาสตร์", 13.731161f, 100.782077f);
        add(db, "อาคารภาควิชาวิจิตรศิลป์", "สถาปัตยกรรมศาสตร์", 13.724917f, 100.773337f);
        add(db, "หอศิลป์พระจอมเกล้า", "สถาปัตยกรรมศาสตร์", 13.726315f, 100.776421f);
        add(db, "อาคาร 2", "สถาปัตยกรรมศาสตร์", 13.725519f, 100.775958f);
        add(db, "โรงปฏิบัติงานไม้", "สถาปัตยกรรมศาสตร์", 13.724915f, 100.777545f);
        add(db, "โรงปฏิบัติงานโลหะ", "สถาปัตยกรรมศาสตร์", 13.724899f, 100.777132f);
        add(db, "โรงปฏิบัติงานเครื่องปั้นดินเผา", "สถาปัตยกรรมศาสตร์", 13.725209f, 100.777547f);
        add(db, "โรงปฏิบัติงานศิลป์", "สถาปัตยกรรมศาสตร์", 13.725200f, 100.777154f);
        add(db, "โรงปฏิบัติสถาปัตย์ภายใน", "สถาปัตยกรรมศาสตร์", 13.725520f, 100.777530f);
        add(db, "โรงปฏิบัติสถาปัตย์อุตสาหกรรม", "สถาปัตยกรรมศาสตร์", 13.725512f, 100.777120f);
        add(db, "โรงปฏิบัติงานสิ่งทอ", "สถาปัตยกรรมศาสตร์", 13.725802f, 100.777549f);
        add(db, "อาคารปฏิบัติการด้านพลังงาน", "สถาปัตยกรรมศาสตร์", 13.725941f, 100.777094f);
        add(db, "โรงอาหารสมเด็จพระเทพฯ", "ทั่วไป", 13.729995f, 100.775932f);
        add(db, "สระว่ายน้ำสมเด็จพระเทพฯ", "ทั่วไป", 13.730479f, 100.775444f);
        add(db, "อาคารภาควิชาวิศวกรรมไฟฟ้า", "วิศวกรรมศาสตร", 13.728888f, 100.776220f);
        add(db, "อาคารเจ้าคุณทหาร", "อุตสาหกรรมการเกษตร เทคโนโลยีการเกษตร", 13.726536f, 100.780341f);
        add(db, "โรงอาหารคณะอุตสาหกรรมการเกษตร", "อุตสาหกรรมการเกษตร", 13.725263f, 100.780632f);
        add(db, "อาคารบุนนาค", "เทคโนโลยีการเกษตร", 13.729740f, 100.782029f);
    }

    private void add(SQLiteDatabase db, String title, String faculty, float lat, float lng) {
        ContentValues values = new ContentValues();
        values.put(TITLE, title);
        values.put(FACULTY, faculty);
        values.put(LAT, lat);
        values.put(LNG, lng);
        db.insertOrThrow(TABLE_NAME, null, values);
    }
}
