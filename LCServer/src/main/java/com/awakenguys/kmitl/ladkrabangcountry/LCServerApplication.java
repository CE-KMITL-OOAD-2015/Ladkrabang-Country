package com.awakenguys.kmitl.ladkrabangcountry;

import com.awakenguys.kmitl.ladkrabangcountry.model.Place;
import com.awakenguys.kmitl.ladkrabangcountry.model.Train_Service;
import com.awakenguys.kmitl.ladkrabangcountry.model.Van_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;

import java.lang.reflect.Member;


@SpringBootApplication
public class LCServerApplication implements CommandLineRunner {

    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private VanServiceRepo vanServiceRepo;
    @Autowired
    private TrainServiceRepo trainServiceRepo;


    public static void main(String[] args) {
        SpringApplication.run(LCServerApplication.class, args); }

    @Override
    public void run(String... args) throws Exception {
        placeRepo.deleteAll();
        vanServiceRepo.deleteAll();
        trainServiceRepo.deleteAll();
        savePlace();
        saveVanService();
        saveTrainService();
    }

    private void saveTrainService() throws Exception {
        Train_Service train_service = new Train_Service();
        train_service.add_Station("พระจอมเกล้าฯ", new Point(13.728180f, 100.775444f));
        trainServiceRepo.save(train_service);
    }

    private void saveVanService() throws Exception {
        Van_Service van_service = new Van_Service();
        van_service.add_Station("หน้าคณะวิทยาศาสตร์", new Point(13.728727f, 100.778177f));
        van_service.add_Route("อนุสาวรีย์ประชาธิปไตย", "ลาดกระบัง");
        vanServiceRepo.save(van_service);
    }

    private void savePlace() throws Exception{

        placeRepo.save(new Place("อาคารเรียนรวม 12 ชั้น(E12)", "วิศวกรรมศาสตร์", new Point(13.727621f, 100.772344f)));
        placeRepo.save(new Place("อาคารปฏิบัติการรวม CCA", "วิศวกรรมศาสตร์", new Point(13.726530f, 100.772984f)));
        placeRepo.save(new Place("โรงอาหาร C", "วิศวกรรมศาสตร์", new Point(13.726863f, 100.772091f)));
        placeRepo.save(new Place("อาคารยิมเนเซียม 1", "ทั่วไป", new Point(13.726826f, 100.773569f)));
        placeRepo.save(new Place("อาคารภาควิชาวิศวกรรมเครื่องกล(ME)", "วิศวกรรมศาสตร์", new Point(13.727669f, 100.773965f)));
        placeRepo.save(new Place("อาคารภาควิชาวิศวกรรมการวัดคุม", "วิศวกรรมศาสตร์", new Point(13.727442f, 100.774419f)));
        placeRepo.save(new Place("โรงอาหาร J", "วิศวกรรมศาสตร์", new Point(13.727711f, 100.774730f)));
        placeRepo.save(new Place("อาคารภาควิชาวิศวกรรมอิเล็กทรอนิกส์", "วิศวกรรมศาสตร์", new Point(13.727556f, 100.775307f)));
        placeRepo.save(new Place("อาคารภาควิชาวิศวกรรมโทรคมนาคม", "วิศวกรรมศาสตร์", new Point(13.727472f, 100.776110f)));
        placeRepo.save(new Place("หอประชุมสถาบัน", "ทั่วไป", new Point(13.727360f, 100.777301f)));
        placeRepo.save(new Place("สำนักงานคณบดีคณะวิศวกรรมศาสตร์", "วิศวกรรมศาสตร์", new Point(13.726974f, 100.776373f)));
        placeRepo.save(new Place("โรงอาหาร A", "วิศวกรรมศาสตร์", new Point(13.726954f, 100.775664f)));
        placeRepo.save(new Place("อาคารภาควิชาวิศวกรรมโยธา", "วิศวกรรมศาสตร์", new Point(13.726951f, 100.774367f)));
        placeRepo.save(new Place("อาคารเฉลิมพระเกียรติ 84 พรรษาภูมิพลมหาราชา(HM)", "วิศวกรรมศาสตร์", new Point(13.726552f, 100.775154f)));
        placeRepo.save(new Place("อาคารปฏิบัติการไฟฟ้า(L)", "วิศวกรรมศาสตร์", new Point(13.728486f, 100.775537f)));
        placeRepo.save(new Place("โรงอาหาร L", "วิศวกรรมศาสตร์", new Point(13.728760f, 100.775207f)));
        placeRepo.save(new Place("อาคารปฏิบัติการรวมวิศวกรรมศาสตร์ 2(ECC)", "วิศวกรรมศาสตร์", new Point(13.729158f, 100.775511f)));
        placeRepo.save(new Place("อาคารบูรณาการ", "สถาปัตยกรรมศาสตร์", new Point(13.725755f, 100.773841f)));
        placeRepo.save(new Place("อาคารเรียนรวมสถาปัตยกรรมศาสตร์", "สถาปัตยกรรมศาสตร์", new Point(13.725206f, 100.775100f)));
        placeRepo.save(new Place("หอประชุมศาสตราจารย์ประสม รังสิโรจน์", "สถาปัตยกรรมศาสตร์", new Point(13.725689f, 100.775117f)));
        placeRepo.save(new Place("สำนักงานคณบดีคณบดีสถาปัตยกรรมศาสตร์", "สถาปัตยกรรมศาสตร์", new Point(13.725275f, 100.776649f)));
        placeRepo.save(new Place("Convention Hall", "ทั่วไป", new Point(13.726469f, 100.779294f)));
        placeRepo.save(new Place("อาคารสำนักหอสมุดกลาง", "ทั่วไป", new Point(13.727659f, 100.778589f)));
        placeRepo.save(new Place("อาคารกรมหลวงนราธิวาสราชนครินทร์(สำนักงานอธิการบดี)", "ทั่วไป", new Point(13.730806f, 100.777616f)));
        placeRepo.save(new Place("หอพักนักศึกษา(เก่า)", "ทั่วไป", new Point(13.728962f, 100.773941f)));
        placeRepo.save(new Place("หอพักนักศึกษา(ใหม่)", "ทั่วไป", new Point(13.729640f, 100.774683f)));
        placeRepo.save(new Place("สมาคมศิษย์เก่า", "ทั่วไป", new Point(13.731067f, 100.774665f)));
        placeRepo.save(new Place("อาคารเรียนรวมสมเด็จพระเทพฯ", "ทั่วไป", new Point(13.730115f, 100.776802f)));
        placeRepo.save(new Place("อาคารเฉลิมพระเกียรติ 55 พรรษา สมเด็จพระเทพฯ", "ทั่วไป", new Point(13.729952f, 100.775283f)));
        placeRepo.save(new Place("อาคารองค์การนักศึกษา", "ทั่วไป", new Point(13.728772f, 100.778624f)));
        placeRepo.save(new Place("อาคารสำนักบริการคอมพิวเตอร์", "ทั่วไป", new Point(13.731218f, 100.780278f)));
        placeRepo.save(new Place("อาคารวิทยาลัยการบริหารและจัดการ", "วิทยาลัยการบริหารและการจัดการ", new Point(13.731218f, 100.780278f)));
        placeRepo.save(new Place("อาคารหอพระราชประวัติ ร.4", "ทั่วไป", new Point(13.731213f, 100.778577f)));
        placeRepo.save(new Place("สนามกีฬากลางพระจอมเกล้าลาดกระบัง", "ทั่วไป", new Point(13.730202f, 100.772183f)));
        placeRepo.save(new Place("อาคารยิมเนเซียม 2", "ทั่วไป", new Point(13.728669f, 100.772869f)));
        placeRepo.save(new Place("อาคารฝึกงานซ่อมสร้างวิทยาศาสตร์", "วิทยาศาสตร์", new Point(13.729298f, 100.778598f)));
        placeRepo.save(new Place("อาคารคณะวิทยาศาสตร์", "วิทยาศาสตร์", new Point(13.729261f, 100.779189f)));
        placeRepo.save(new Place("อาคารจุฬาภรณ์วลัยลักษณ์ 1", "วิทยาศาสตร์", new Point(13.729658f, 100.779978f)));
        placeRepo.save(new Place("หอประชุมจุฬาภรณ์", "วิทยาศาสตร์", new Point(13.729842f, 100.779333f)));
        placeRepo.save(new Place("อาคารจุฬาภรณ์วลัยลักษณ์ 2", "วิทยาศาสตร์", new Point(13.730232f, 100.779318f)));
        placeRepo.save(new Place("อาคารปฏิบัติการใหม่", "วิทยาศาสตร์", new Point(13.729046f, 100.779856f)));
        placeRepo.save(new Place("อาคารกิจกรรม", "วิทยาศาสตร์", new Point(13.728947f, 100.778425f)));
        placeRepo.save(new Place("อาคารปฏิบัติการพิเศษจอมไตร", "วิทยาศาสตร์", new Point(13.729574f, 100.780795f)));
        placeRepo.save(new Place("อาคารคณะเทคโนโลยีสารสนเทศ", "เทคโนโลยีสารสนเทศ", new Point(13.730976f, 100.781116f)));
        placeRepo.save(new Place("โรงอาหารคณะเทคโนโลยีสารสนเทศ", "เทคโนโลยีสารสนเทศ", new Point(13.731161f, 100.782077f)));
        placeRepo.save(new Place("โรงอาหารคณะสถาปัตยกรรมศาสตร์", "สถาปัตยกรรมศาสตร์", new Point(13.731161f, 100.782077f)));
        placeRepo.save(new Place("อาคารภาควิชาวิจิตรศิลป์", "สถาปัตยกรรมศาสตร์", new Point(13.724917f, 100.773337f)));
        placeRepo.save(new Place("หอศิลป์พระจอมเกล้า", "สถาปัตยกรรมศาสตร์", new Point(13.726315f, 100.776421f)));
        placeRepo.save(new Place("อาคาร 2", "สถาปัตยกรรมศาสตร์", new Point(13.725519f, 100.775958f)));
        placeRepo.save(new Place("โรงปฏิบัติงานไม้", "สถาปัตยกรรมศาสตร์", new Point(13.724915f, 100.777545f)));
        placeRepo.save(new Place("โรงปฏิบัติงานโลหะ", "สถาปัตยกรรมศาสตร์", new Point(13.724899f, 100.777132f)));
        placeRepo.save(new Place("โรงปฏิบัติงานเครื่องปั้นดินเผา", "สถาปัตยกรรมศาสตร์", new Point(13.725209f, 100.777547f)));
        placeRepo.save(new Place("โรงปฏิบัติงานศิลป์", "สถาปัตยกรรมศาสตร์", new Point(13.725200f, 100.777154f)));
        placeRepo.save(new Place("โรงปฏิบัติสถาปัตย์ภายใน", "สถาปัตยกรรมศาสตร์", new Point(13.725520f, 100.777530f)));
        placeRepo.save(new Place("โรงปฏิบัติสถาปัตย์อุตสาหกรรม", "สถาปัตยกรรมศาสตร์", new Point(13.725512f, 100.777120f)));
        placeRepo.save(new Place("โรงปฏิบัติงานสิ่งทอ", "สถาปัตยกรรมศาสตร์", new Point(13.725802f, 100.777549f)));
        placeRepo.save(new Place("อาคารปฏิบัติการด้านพลังงาน", "สถาปัตยกรรมศาสตร์", new Point(13.725941f, 100.777094f)));
        placeRepo.save(new Place("โรงอาหารสมเด็จพระเทพฯ", "ทั่วไป", new Point(13.729995f, 100.775932f)));
        placeRepo.save(new Place("สระว่ายน้ำสมเด็จพระเทพฯ", "ทั่วไป", new Point(13.730479f, 100.775444f)));
        placeRepo.save(new Place("อาคารภาควิชาวิศวกรรมไฟฟ้า", "วิศวกรรมศาสตร", new Point(13.728888f, 100.776220f)));
        placeRepo.save(new Place("อาคารเจ้าคุณทหาร", "อุตสาหกรรมการเกษตร เทคโนโลยีการเกษตร", new Point(13.726536f, 100.780341f)));
        placeRepo.save(new Place("โรงอาหารคณะอุตสาหกรรมการเกษตร", "อุตสาหกรรมการเกษตร", new Point(13.725263f, 100.780632f)));
        placeRepo.save(new Place("อาคารบุนนาค", "เทคโนโลยีการเกษตร", new Point(13.729740f, 100.782029f)));
    }




}
