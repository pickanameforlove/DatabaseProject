package com.trainManageSystem.dao;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.trainManageSystem.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.json.JsonObject;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class TrainDaoTest {

    @Autowired
    private TrainDao dao;
    @Autowired
    private StationDao sdao;
    @Autowired
    private Train_seatDao tsdao;
    @Autowired
    private TicketDao ticketDao;
    @Autowired
    private TwoTrainsDao ttDao;
    @Autowired
    private userDao userDao;
    @Autowired
    private DingdanDao ddDao;

    @Test
    public void testSelectTrain() throws Exception{
//        Train[] ts = dao.selectAllTrain();
//        for (int i=0;i<5;i++){
//            System.out.println(ts[i].getE_plat());
//        }
//        Station[] s = sdao.selectStationByName("北京%");
//        for(int i=0;i<=s.length-1;i++){
//            System.out.println(s[i].getStation_name());
//        }
//        List<Station> m = new ArrayList<Station>();
//        Station s1 = new Station();
//        s1.setStation_id("444");
//        s1.setStation_name("lll");
//        m.add(s1);
//        ObjectMapper mapper = new ObjectMapper();
//        System.out.println(mapper.writeValueAsString(m.get(0)));

//        train_seat[] p = tsdao.selectTrain_seatByTrain("2400000G710S");
//        for(int i=0;i<p.length;i++){
//            System.out.println(p[i].getSeat_type());
//        }
//        int i = this.ticketDao.selectCountTicket("D1",5,8);
//        System.out.println(i);
//        TwoTrains[] tt = this.ttDao.selectAll("%长治%","%济南%");
//        System.out.println(tt.length);
//        String pa = this.userDao.login("12345678");
//        System.out.println(pa);
//        Dingdan[] dd = this.ddDao.selectByAccount("12345678");
//        System.out.println(dd.length);

//        SimpleDateFormat sj = new SimpleDateFormat("yyyy-MM-dd");
//        String today = "2015-11-30";
//        Date d = sj.parse(today);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(d);
//        calendar.add(Calendar.DATE, 1);
//        System.out.println("明天：" +     calendar.getTime());
//        //此时日期变为2015-12-01 ，所以下面的-2，
//        //理论上讲应该是2015-11-29
//        calendar.add(calendar.DATE, -2);
//        System.out.println("前天：" + sj.format(calendar.getTime()));
//        Ticket[] a = this.ticketDao.selectTicketSimple(2);
//        for(int i=0;i<a.length;i++)
//        System.out.println(a[i].getTicket_id());
//        String t = "[{\"id\":\"123456789123456789\",\"name\":\"小芳\"},{\"id\":\"111111111111111111\",\"name\":\"小明\"},{\"id\":\"\",\"name\":\"\"},{\"id\":\"\",\"name\":\"\"},{\"id\":\"\",\"name\":\"\"},{\"id\":\"\",\"name\":\"\"},{\"id\":\"\",\"name\":\"\"},{\"id\":\"\",\"name\":\"\"},{\"id\":\"\",\"name\":\"\"},{\"id\":\"\",\"name\":\"\"},{\"id\":\"\",\"name\":\"\"},{\"id\":\"\",\"name\":\"\"}]";
//            this.dao.deleteTrain("010000221005");
//        Time a = new Time();

    }
}
