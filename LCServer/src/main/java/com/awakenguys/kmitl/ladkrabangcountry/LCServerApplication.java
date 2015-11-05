package com.awakenguys.kmitl.ladkrabangcountry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LCServerApplication implements CommandLineRunner {

    @Autowired
    private Repository placeRepository;


    public static void main(String[] args) {
        SpringApplication.run(LCServerApplication.class, args); }

    @Override
    public void run(String... args) throws Exception {
        placeRepository.deleteAll();
        savePlace();
    }



    private void savePlace() throws Exception{
        placeRepository.save(new Place("อาคารเรียนรวม 12 ชั้น(E12)", "วิศวกรรมศาสตร์", 13.727621f, 100.772344f));
        placeRepository.save(new Place("อาคารปฏิบัติการรวม CCA", "วิศวกรรมศาสตร์", 13.726530f, 100.772984f));
        placeRepository.save(new Place("โรงอาหาร C", "วิศวกรรมศาสตร์", 13.726863f, 100.772091f));
        placeRepository.save(new Place("อาคารยิมเนเซียม 1", "ทั่วไป", 13.726826f, 100.773569f));
        placeRepository.save(new Place("อาคารภาควิชาวิศวกรรมเครื่องกล(ME)", "วิศวกรรมศาสตร์", 13.727669f, 100.773965f));
        placeRepository.save(new Place("อาคารภาควิชาวิศวกรรมการวัดคุม", "วิศวกรรมศาสตร์", 13.727442f, 100.774419f));
        placeRepository.save(new Place("โรงอาหาร J", "วิศวกรรมศาสตร์", 13.727711f, 100.774730f));
        placeRepository.save(new Place("อาคารภาควิชาวิศวกรรมอิเล็กทรอนิกส์", "วิศวกรรมศาสตร์", 13.727556f, 100.775307f));
        placeRepository.save(new Place("อาคารภาควิชาวิศวกรรมโทรคมนาคม", "วิศวกรรมศาสตร์", 13.727472f, 100.776110f));
        placeRepository.save(new Place("หอประชุมสถาบัน", "ทั่วไป", 13.727360f, 100.777301f));
        placeRepository.save(new Place("สำนักงานคณบดีคณะวิศวกรรมศาสตร์", "วิศวกรรมศาสตร์", 13.726974f, 100.776373f));
        placeRepository.save(new Place("โรงอาหาร A", "วิศวกรรมศาสตร์", 13.726954f, 100.775664f));
        placeRepository.save(new Place("อาคารภาควิชาวิศวกรรมโยธา", "วิศวกรรมศาสตร์", 13.726951f, 100.774367f));
        placeRepository.save(new Place("อาคารเฉลิมพระเกียรติ 84 พรรษาภูมิพลมหาราชา(HM)", "วิศวกรรมศาสตร์", 13.726552f, 100.775154f));
        placeRepository.save(new Place("อาคารปฏิบัติการไฟฟ้า(L)", "วิศวกรรมศาสตร์", 13.728486f, 100.775537f));
        placeRepository.save(new Place("โรงอาหาร L", "วิศวกรรมศาสตร์", 13.728760f, 100.775207f));
        placeRepository.save(new Place("อาคารปฏิบัติการรวมวิศวกรรมศาสตร์ 2(ECC)", "วิศวกรรมศาสตร์", 13.729158f, 100.775511f));
        placeRepository.save(new Place("อาคารบูรณาการ", "สถาปัตยกรรมศาสตร์", 13.725755f, 100.773841f));
        placeRepository.save(new Place("อาคารเรียนรวมสถาปัตยกรรมศาสตร์", "สถาปัตยกรรมศาสตร์", 13.725206f, 100.775100f));
        placeRepository.save(new Place("หอประชุมศาสตราจารย์ประสม รังสิโรจน์", "สถาปัตยกรรมศาสตร์", 13.725689f, 100.775117f));
        placeRepository.save(new Place("สำนักงานคณบดีคณบดีสถาปัตยกรรมศาสตร์", "สถาปัตยกรรมศาสตร์", 13.725275f, 100.776649f));
        placeRepository.save(new Place("Convention Hall", "ทั่วไป", 13.726469f, 100.779294f));
        placeRepository.save(new Place("อาคารสำนักหอสมุดกลาง", "ทั่วไป", 13.727659f, 100.778589f));
        placeRepository.save(new Place("อาคารกรมหลวงนราธิวาสราชนครินทร์(สำนักงานอธิการบดี)", "ทั่วไป", 13.730806f, 100.777616f));
        placeRepository.save(new Place("หอพักนักศึกษา(เก่า)", "ทั่วไป", 13.728962f, 100.773941f));
        placeRepository.save(new Place("หอพักนักศึกษา(ใหม่)", "ทั่วไป", 13.729640f, 100.774683f));
        placeRepository.save(new Place("สมาคมศิษย์เก่า", "ทั่วไป", 13.731067f, 100.774665f));
        placeRepository.save(new Place("อาคารเรียนรวมสมเด็จพระเทพฯ", "ทั่วไป", 13.730115f, 100.776802f));
        placeRepository.save(new Place("อาคารเฉลิมพระเกียรติ 55 พรรษา สมเด็จพระเทพฯ", "ทั่วไป", 13.729952f, 100.775283f));
        placeRepository.save(new Place("อาคารองค์การนักศึกษา", "ทั่วไป", 13.728772f, 100.778624f));
        placeRepository.save(new Place("อาคารสำนักบริการคอมพิวเตอร์", "ทั่วไป", 13.731218f, 100.780278f));
        placeRepository.save(new Place("อาคารวิทยาลัยการบริหารและจัดการ", "วิทยาลัยการบริหารและการจัดการ", 13.731218f, 100.780278f));
        placeRepository.save(new Place("อาคารหอพระราชประวัติ ร.4", "ทั่วไป", 13.731213f, 100.778577f));
        placeRepository.save(new Place("สนามกีฬากลางพระจอมเกล้าลาดกระบัง", "ทั่วไป", 13.730202f, 100.772183f));
        placeRepository.save(new Place("อาคารยิมเนเซียม 2", "ทั่วไป", 13.728669f, 100.772869f));
        placeRepository.save(new Place("อาคารฝึกงานซ่อมสร้างวิทยาศาสตร์", "วิทยาศาสตร์", 13.729298f, 100.778598f));
        placeRepository.save(new Place("อาคารคณะวิทยาศาสตร์", "วิทยาศาสตร์", 13.729261f, 100.779189f));
        placeRepository.save(new Place("อาคารจุฬาภรณ์วลัยลักษณ์ 1", "วิทยาศาสตร์", 13.729658f, 100.779978f));
        placeRepository.save(new Place("หอประชุมจุฬาภรณ์", "วิทยาศาสตร์", 13.729842f, 100.779333f));
        placeRepository.save(new Place("อาคารจุฬาภรณ์วลัยลักษณ์ 2", "วิทยาศาสตร์", 13.730232f, 100.779318f));
        placeRepository.save(new Place("อาคารปฏิบัติการใหม่", "วิทยาศาสตร์", 13.729046f, 100.779856f));
        placeRepository.save(new Place("อาคารกิจกรรม", "วิทยาศาสตร์", 13.728947f, 100.778425f));
        placeRepository.save(new Place("อาคารปฏิบัติการพิเศษจอมไตร", "วิทยาศาสตร์", 13.729574f, 100.780795f));
        placeRepository.save(new Place("อาคารคณะเทคโนโลยีสารสนเทศ", "เทคโนโลยีสารสนเทศ", 13.730976f, 100.781116f));
        placeRepository.save(new Place("โรงอาหารคณะเทคโนโลยีสารสนเทศ", "เทคโนโลยีสารสนเทศ", 13.731161f, 100.782077f));
        placeRepository.save(new Place("โรงอาหารคณะสถาปัตยกรรมศาสตร์", "สถาปัตยกรรมศาสตร์", 13.731161f, 100.782077f));
        placeRepository.save(new Place("อาคารภาควิชาวิจิตรศิลป์", "สถาปัตยกรรมศาสตร์", 13.724917f, 100.773337f));
        placeRepository.save(new Place("หอศิลป์พระจอมเกล้า", "สถาปัตยกรรมศาสตร์", 13.726315f, 100.776421f));
        placeRepository.save(new Place("อาคาร 2", "สถาปัตยกรรมศาสตร์", 13.725519f, 100.775958f));
        placeRepository.save(new Place("โรงปฏิบัติงานไม้", "สถาปัตยกรรมศาสตร์", 13.724915f, 100.777545f));
        placeRepository.save(new Place("โรงปฏิบัติงานโลหะ", "สถาปัตยกรรมศาสตร์", 13.724899f, 100.777132f));
        placeRepository.save(new Place("โรงปฏิบัติงานเครื่องปั้นดินเผา", "สถาปัตยกรรมศาสตร์", 13.725209f, 100.777547f));
        placeRepository.save(new Place("โรงปฏิบัติงานศิลป์", "สถาปัตยกรรมศาสตร์", 13.725200f, 100.777154f));
        placeRepository.save(new Place("โรงปฏิบัติสถาปัตย์ภายใน", "สถาปัตยกรรมศาสตร์", 13.725520f, 100.777530f));
        placeRepository.save(new Place("โรงปฏิบัติสถาปัตย์อุตสาหกรรม", "สถาปัตยกรรมศาสตร์", 13.725512f, 100.777120f));
        placeRepository.save(new Place("โรงปฏิบัติงานสิ่งทอ", "สถาปัตยกรรมศาสตร์", 13.725802f, 100.777549f));
        placeRepository.save(new Place("อาคารปฏิบัติการด้านพลังงาน", "สถาปัตยกรรมศาสตร์", 13.725941f, 100.777094f));
        placeRepository.save(new Place("โรงอาหารสมเด็จพระเทพฯ", "ทั่วไป", 13.729995f, 100.775932f));
        placeRepository.save(new Place("สระว่ายน้ำสมเด็จพระเทพฯ", "ทั่วไป", 13.730479f, 100.775444f));
        placeRepository.save(new Place("อาคารภาควิชาวิศวกรรมไฟฟ้า", "วิศวกรรมศาสตร", 13.728888f, 100.776220f));
        placeRepository.save(new Place("อาคารเจ้าคุณทหาร", "อุตสาหกรรมการเกษตร เทคโนโลยีการเกษตร", 13.726536f, 100.780341f));
        placeRepository.save(new Place("โรงอาหารคณะอุตสาหกรรมการเกษตร", "อุตสาหกรรมการเกษตร", 13.725263f, 100.780632f));
        placeRepository.save(new Place("อาคารบุนนาค", "เทคโนโลยีการเกษตร", 13.729740f, 100.782029f));
    }


}
