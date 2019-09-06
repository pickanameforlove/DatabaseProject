package com.trainManageSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.trainManageSystem.model.*;
import com.trainManageSystem.service.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@org.springframework.stereotype.Controller
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class Controller {
    private int carriage_tdz_sum = 18;
    private int carriage_ydz_sum = 48;
    private int carriage_edz_sum = 85;
    private int carriage_gjrw_sum = 16;
    private int carriage_rw_sum = 36;
    private int carriage_dw_sum = 36;
    private int carriage_yw_sum = 66;
    private int carriage_rz_sum = 120;
    private int carriage_yz_sum = 120;
    private int carriage_wz_sum = 24;

    private int[][] a = new int[][]{{2,18},{2,48},{2,85},{2,16},{2,36},{2,36},{2,66},{2,120},{2,120},{2,24}};
    @Resource
    private TrainService trainService;
    @Resource
    private templateService templateService;

    @Resource
    private StationService stationService;
    @Resource
    private TicketService ticketService;
    @Resource
    private TwoTrainsService twoTrainsService;
    @Resource
    private UserService userService;
    @Resource
    private DingdanService dingdanService;
    @Resource
    private PassengerService passengerService;
    @Resource
    private Train_timeService train_timeService;

    @RequestMapping("train/select/trains")
    public void selectTrain(HttpServletRequest request, HttpServletResponse response) throws
            IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Train[] trains = this.trainService.selectAllTrain();
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write("[");
        for(int i=0;i<trains.length -1 ;i++){
            response.getWriter().write(mapper.writeValueAsString(trains[i]));
            response.getWriter().write(",");
        }
        response.getWriter().write(mapper.writeValueAsString(trains[trains.length-1]));
        response.getWriter().write("]");

        response.getWriter().close();
    }
    @RequestMapping("/train/select/count")
    public void selectCount(HttpServletRequest request, HttpServletResponse response) throws
            IOException{
        response.setCharacterEncoding("UTF-8");
        int count = this.trainService.selectCount();
        response.getWriter().write(count+"");
        response.getWriter().close();
    }
    @RequestMapping("/train/select/batchTrains")
    public void selectBatchTrains(String start,String end, HttpServletRequest request, HttpServletResponse response) throws
            IOException{
        response.setCharacterEncoding("UTF-8");
        Train[] trains = this.trainService.selectBatchTrains(Integer.parseInt(start),Integer.parseInt(end));
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write("[");
        for(int i=0;i<trains.length -1 ;i++){
            response.getWriter().write(mapper.writeValueAsString(trains[i]));
            response.getWriter().write(",");
        }
        response.getWriter().write(mapper.writeValueAsString(trains[trains.length-1]));
        response.getWriter().write("]");

        response.getWriter().close();
    }

    @RequestMapping("/template/select/template")
    public void selectTemplate(String start,String end,HttpServletRequest request, HttpServletResponse response) throws
            IOException{
        response.setCharacterEncoding("UTF-8");
        List<template> result = new ArrayList<template>();
        Station[] s1 = this.stationService.selectStationByName(start+"%");
        Station[] s2 = this.stationService.selectStationByName(end + "%");
        for(int i=0;i<s1.length;i++){
            for(int j=0;j<s2.length;j++){
                template[] t = this.templateService.selectTemplate(s1[i].getStation_name(),s2[j].getStation_name());
                for(int k=0;k<t.length;k++){
                    int from = this.train_timeService.selectOne(t[k].getTrain_no(),t[k].getStart_station());
                    int to = this.train_timeService.selectOne(t[k].getTrain_no(),t[k].getEnd_station());
                    if(t[k].getTDZ()!=null) {
                        int res = this.ticketService.selectRestPlus(t[k].getTrain_no(),from,to,"TDZ");
                        if(res>0)
                        t[k].setTDZ(res+"");
                        else {
                            t[k].setTDZ("无票");
                        }
                    }
                    if(t[k].getYDZ()!=null){
                        int res = this.ticketService.selectRestPlus(t[k].getTrain_no(),from,to,"YDZ");
                        if(res>0)
                            t[k].setYDZ(res+"");
                        else {
                            t[k].setYDZ("无票");
                        }
                    }
                    if(t[k].getEDZ()!=null)
                    {
                        int res = this.ticketService.selectRestPlus(t[k].getTrain_no(),from,to,"EDZ");
                        if(res>0)
                            t[k].setEDZ(res+"");
                        else {
                            t[k].setEDZ("无票");
                        }
                    }
                    if(t[k].getGJRW()!=null)
                    {
                        int res = this.ticketService.selectRestPlus(t[k].getTrain_no(),from,to,"GJRW");

                        if(res>0)
                            t[k].setGJRW(res+"");
                        else {
                            t[k].setGJRW("无票");
                        }
                    }
                    if(t[k].getRW()!=null)
                    {
                        int res = this.ticketService.selectRestPlus(t[k].getTrain_no(),from,to,"RW");
                        if(res>0)
                            t[k].setRW(res+"");
                        else {
                            t[k].setRW("无票");
                        }
                    }
                    if(t[k].getDW()!=null)
                    {
                        int res = this.ticketService.selectRestPlus(t[k].getTrain_no(),from,to,"DW");
                        if(res>0)
                            t[k].setDW(res+"");
                        else {
                            t[k].setDW("无票");
                        }
                    }
                    if(t[k].getYW()!=null)
                    {
                        int res = this.ticketService.selectRestPlus(t[k].getTrain_no(),from,to,"YW");
                        if(res>0)
                            t[k].setYW(res+"");
                        else {
                            t[k].setYW("无票");
                        }
                    }
                    if(t[k].getRZ()!=null)
                    {
                        int res = this.ticketService.selectRestPlus(t[k].getTrain_no(),from,to,"RZ");
                        if(res>0)
                            t[k].setRZ(res+"");
                        else {
                            t[k].setRZ("无票");
                        }
                    }
                    if(t[k].getYZ()!=null)
                    {
                        int res = this.ticketService.selectRestPlus(t[k].getTrain_no(),from,to,"YZ");
                        if(res>0)
                            t[k].setYZ(res+"");
                        else {
                            t[k].setYZ("无票");
                        }
                    }
                    if(t[k].getWZ()!=null)
                    {
                        int res = this.ticketService.selectRestPlus(t[k].getTrain_no(),from,to,"WZ");
                        if(res>0)
                            t[k].setWZ(res+"");
                        else {
                            t[k].setWZ("无票");
                        }
                    }
//                    int i_j_1 = this.ticketService.selectCountTicket(t[k].getTrain_id(),t[k])
                    result.add(t[k]);
                }
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write("[");
        for(int i=0;i<result.size() -1  ;i++){
            response.getWriter().write(mapper.writeValueAsString(result.get(i)));
            response.getWriter().write(",");
        }

        response.getWriter().write(mapper.writeValueAsString(result.get(result.size()-1)));
        response.getWriter().write("]");

        response.getWriter().close();
    }
    @RequestMapping("/TwoTrains/select/All")
    public void selectAll(String start,String end,String index,HttpServletRequest request, HttpServletResponse response) throws
            IOException, ParseException {
        System.out.println(1);
        int httpindex = Integer.parseInt(index);
        response.setCharacterEncoding("utf-8");
        TwoTrains[] trains = this.twoTrainsService.selectAll(start,end,httpindex);
        int len = trains.length;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        for(int i=0;i<len;i++){
            int tem = i;
            for(int j=i+1;j<len;j++){
                String date = "2017-8-17 "+trains[tem].getEnd_time1();
                Date alpha1 = format.parse(date);
                long beta1 = alpha1.getTime();
                String date1 = "2017-8-17 "+trains[tem].getStart_time2();
                Date alpha2 = format.parse(date1);
                long beta2 = alpha2.getTime();

                if(beta1 < beta2){
                    long delta1 = beta2 - beta1;
                    String date3 = "2017-8-17 "+trains[j].getEnd_time1();
                    Date alpha3 = format.parse(date3);
                    long beta3 = alpha3.getTime();
                    String date4 = "2017-8-17 "+trains[j].getStart_time2();
                    Date alpha4 = format.parse(date4);
                    long beta4 = alpha4.getTime();

                    if(beta3 < beta4){
                        long delta2 = beta4 - beta3;
                        if(delta2 < delta1){
                            tem = j;
                        }
                    }else{
                        String date5 = "2017-8-18 "+trains[j].getStart_time2();
                        Date alpha5 = format.parse(date5);
                        long beta5 = alpha5.getTime();
                        long delta3 = beta5 - beta3;
                        if(delta3 < delta1){
                            tem = j;
                        }
                    }
                }else{
                    String date6 = "2017-8-18 "+trains[tem].getStart_time2();
                    Date alpha6 = format.parse(date6);
                    long beta6 = alpha6.getTime();

                    long delta1 = beta6 - beta1;
                    String date3 = "2017-8-17 "+trains[j].getEnd_time1();
                    Date alpha3 = format.parse(date3);
                    long beta3 = alpha3.getTime();
                    String date4 = "2017-8-17 "+trains[j].getStart_time2();
                    Date alpha4 = format.parse(date4);
                    long beta4 = alpha4.getTime();

                    if(beta3 < beta4){
                        long delta2 = beta4 - beta3;
                        if(delta2 < delta1){
                            tem = j;
                        }
                    }else{
                        String date5 = "2017-8-18 "+trains[j].getStart_time2();
                        Date alpha5 = format.parse(date5);
                        long beta5 = alpha5.getTime();
                        long delta3 = beta5 - beta3;
                        if(delta3 < delta1){
                            tem = j;
                        }
                    }
                }
            }
            if(tem!=i){
                TwoTrains rr = trains[tem];
                trains[tem] = trains[i];
                trains[i] = rr;
            }
        }
        template[] tem = new template[trains.length*2];
        for(int i=0;i<tem.length;i++){
            tem[i] = new template();
        }
        for(int i=0;i<trains.length;i++){
            tem[2*i].setTrain_no(trains[i].getTrain_no1());
            tem[2*i].setTrain_name(trains[i].getTrain_name1());
            tem[2*i].setStart_station(trains[i].getStart_station1());
            tem[2*i].setEnd_station(trains[i].getEnd_station1());
            tem[2*i].setStart_time(trains[i].getStart_time1());
            tem[2*i].setEnd_time(trains[i].getEnd_time1());
            tem[2*i].setDuration(trains[i].getDuration1());

            int from1 = this.train_timeService.selectOne(trains[i].getTrain_no1(),trains[i].getStart_station1());
            int to1 = this.train_timeService.selectOne(trains[i].getTrain_no1(),trains[i].getEnd_station1());
            if(trains[i].getTDZ1()!=null) {
                tem[2*i].setTDZ((this.ticketService.selectRestPlus(trains[i].getTrain_no1(), from1, to1, "TDZ") == 0) ? "无座" : this.ticketService.selectRestPlus(trains[i].getTrain_no1(), from1, to1, "TDZ") + "");
            }else{
                tem[2*i].setTDZ(null);
            }
            if(trains[i].getYDZ1()!=null) {
                tem[2*i].setYDZ((this.ticketService.selectRestPlus(trains[i].getTrain_no1(), from1, to1, "YDZ") == 0) ? "无座" : this.ticketService.selectRestPlus(trains[i].getTrain_no1(), from1, to1, "YDZ") + "");
            }else {
                tem[2*i].setYDZ(null);
            }
            if(trains[i].getEDZ1()!=null) {
                tem[2*i].setEDZ((this.ticketService.selectRestPlus(trains[i].getTrain_no1(), from1, to1, "EDZ") == 0) ? "无座" : this.ticketService.selectRestPlus(trains[i].getTrain_no1(), from1, to1, "EDZ") + "");
            }else {
                tem[2*i].setEDZ(null);
            }
            if(trains[i].getGJRW1()!=null) {
                tem[2*i].setGJRW((this.ticketService.selectRestPlus(trains[i].getTrain_no1(), from1, to1, "GJRW") == 0) ? "无座" : this.ticketService.selectRestPlus(trains[i].getTrain_no1(), from1, to1, "GJRW") + "");
            }else {
                tem[2*i].setGJRW(null);
            }
            if(trains[i].getRW1()!=null) {
                tem[2*i].setRW((this.ticketService.selectRestPlus(trains[i].getTrain_no1(), from1, to1, "RW") == 0) ? "无座" : this.ticketService.selectRestPlus(trains[i].getTrain_no1(), from1, to1, "RW") + "");
            }else {
                tem[2*i].setRW(null);
            }
            if(trains[i].getDW1()!=null) {
                tem[2*i].setDW((this.ticketService.selectRestPlus(trains[i].getTrain_no1(), from1, to1, "DW") == 0) ? "无座" : this.ticketService.selectRestPlus(trains[i].getTrain_no1(), from1, to1, "DW") + "");
            }else{
                tem[2*i].setDW(null);
            }
            if(trains[i].getYW1()!=null) {
                tem[2*i].setYW((this.ticketService.selectRestPlus(trains[i].getTrain_no1(), from1, to1, "YW") == 0) ? "无座" : this.ticketService.selectRestPlus(trains[i].getTrain_no1(), from1, to1, "YW") + "");
            }else{
                tem[2*i].setYW(null);
            }
            if(trains[i].getRZ1()!=null) {
                tem[2*i].setRZ((this.ticketService.selectRestPlus(trains[i].getTrain_no1(), from1, to1, "RZ") == 0) ? "无座" : this.ticketService.selectRestPlus(trains[i].getTrain_no1(), from1, to1, "RZ") + "");
            }else {
                tem[2*i].setRZ(null);
            }
            if(trains[i].getYZ1()!=null) {
                tem[2*i].setYZ((this.ticketService.selectRestPlus(trains[i].getTrain_no1(), from1, to1, "YZ") == 0) ? "无座" : this.ticketService.selectRestPlus(trains[i].getTrain_no1(), from1, to1, "YZ") + "");
            }else {
                tem[2*i].setYZ(null);
            }
            if(trains[i].getWZ1()!=null) {
                tem[2*i].setWZ((this.ticketService.selectRestPlus(trains[i].getTrain_no1(), from1, to1, "WZ") == 0) ? "无座" : this.ticketService.selectRestPlus(trains[i].getTrain_no1(), from1, to1, "WZ") + "");
            }else {
                tem[2*i].setWZ(null);
            }


            int from2 = this.train_timeService.selectOne(trains[i].getTrain_no2(),trains[i].getStart_station2());
            int to2 = this.train_timeService.selectOne(trains[i].getTrain_no2(),trains[i].getEnd_station2());
            tem[2*i+1].setTrain_no(trains[i].getTrain_no2());
            tem[2*i+1].setTrain_name(trains[i].getTrain_name2());
            tem[2*i+1].setStart_station(trains[i].getStart_station2());
            tem[2*i+1].setEnd_station(trains[i].getEnd_station2());
            tem[2*i+1].setStart_time(trains[i].getStart_time2());
            tem[2*i+1].setEnd_time(trains[i].getEnd_time2());
            tem[2*i+1].setDuration(trains[i].getDuration2());
            if(trains[i].getTDZ2()!=null) {
                tem[2*i+1].setTDZ((this.ticketService.selectRestPlus(trains[i].getTrain_no2(), from2, to2, "TDZ") == 0) ? "无座" : this.ticketService.selectRestPlus(trains[i].getTrain_no2(), from2, to2, "TDZ") + "");
            }else{
                tem[2*i+1].setTDZ(null);
            }
            if(trains[i].getYDZ2()!=null) {
                tem[2*i+1].setYDZ((this.ticketService.selectRestPlus(trains[i].getTrain_no2(), from2, to2, "YDZ") == 0) ? "无座" : this.ticketService.selectRestPlus(trains[i].getTrain_no2(), from2, to2, "YDZ") + "");
            }else {
                tem[2*i+1].setYDZ(null);
            }
            if(trains[i].getEDZ2()!=null) {
                tem[2*i+1].setEDZ((this.ticketService.selectRestPlus(trains[i].getTrain_no2(), from2, to2, "EDZ") == 0) ? "无座" : this.ticketService.selectRestPlus(trains[i].getTrain_no2(), from2, to2, "EDZ") + "");
            }else {
                tem[2*i+1].setEDZ(null);
            }
            if(trains[i].getGJRW2()!=null) {
                tem[2*i+1].setGJRW((this.ticketService.selectRestPlus(trains[i].getTrain_no2(), from2, to2, "GJRW") == 0) ? "无座" : this.ticketService.selectRestPlus(trains[i].getTrain_no2(), from2, to2, "GJRW") + "");
            }else {
                tem[2*i+1].setGJRW(null);
            }
            if(trains[i].getRW2()!=null) {
                tem[2*i+1].setRW((this.ticketService.selectRestPlus(trains[i].getTrain_no2(), from2, to2, "RW") == 0) ? "无座" : this.ticketService.selectRestPlus(trains[i].getTrain_no2(), from2, to2, "RW") + "");
            }else {
                tem[2*i+1].setRW(null);
            }
            if(trains[i].getDW2()!=null) {
                tem[2*i+1].setDW((this.ticketService.selectRestPlus(trains[i].getTrain_no2(), from2, to2, "DW") == 0) ? "无座" : this.ticketService.selectRestPlus(trains[i].getTrain_no2(), from2, to2, "DW") + "");
            }else{
                tem[2*i+1].setDW(null);
            }
            if(trains[i].getYW2()!=null) {
                tem[2*i+1].setYW((this.ticketService.selectRestPlus(trains[i].getTrain_no2(), from2, to2, "YW") == 0) ? "无座" : this.ticketService.selectRestPlus(trains[i].getTrain_no2(), from2, to2, "YW") + "");
            }else{
                tem[2*i+1].setYW(null);
            }
            if(trains[i].getRZ2()!=null) {
                tem[2*i+1].setRZ((this.ticketService.selectRestPlus(trains[i].getTrain_no2(), from2, to2, "RZ") == 0) ? "无座" : this.ticketService.selectRestPlus(trains[i].getTrain_no2(), from2, to2, "RZ") + "");
            }else {
                tem[2*i+1].setRZ(null);
            }
            if(trains[i].getYZ2()!=null) {
                tem[2*i+1].setYZ((this.ticketService.selectRestPlus(trains[i].getTrain_no2(), from2, to2, "YZ") == 0) ? "无座" : this.ticketService.selectRestPlus(trains[i].getTrain_no2(), from2, to2, "YZ") + "");
            }else {
                tem[2*i+1].setYZ(null);
            }
            if(trains[i].getWZ2()!=null) {
                tem[2*i+1].setWZ((this.ticketService.selectRestPlus(trains[i].getTrain_no2(), from2, to2, "WZ") == 0) ? "无座" : this.ticketService.selectRestPlus(trains[i].getTrain_no2(), from2, to2, "WZ") + "");
            }else {
                tem[2*i+1].setWZ(null);
            }

        }
//        for(int i=0;i<)
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write("[[");
        for(int i=0;i<trains.length-1;i++){
            response.getWriter().write(mapper.writeValueAsString(tem[2*i]));
            response.getWriter().write(",");
            response.getWriter().write(mapper.writeValueAsString(tem[2*i+1]));
            response.getWriter().write("]");
            response.getWriter().write(",[");
        }
        response.getWriter().write(mapper.writeValueAsString(tem[tem.length-2]));
        response.getWriter().write(",");
        response.getWriter().write(mapper.writeValueAsString(tem[tem.length-1]));
        response.getWriter().write("]]");

//        response.getWriter().write("[");
//        for(int i=0;i<trains.length-1;i++){
//            String s = mapper.writeValueAsString(tem[2*i]);
//            response.getWriter().write("{\"id\":");
//            int i1 = 2*i;
//            response.getWriter().write("\""+i1+"\"");
//            response.getWriter().write(",");
//            response.getWriter().write(s.substring(1,s.length()-1));
//            response.getWriter().write(",\"children\":[");
//            String s2 = mapper.writeValueAsString(tem[2*i+1]);
//            response.getWriter().write("{\"id\":");
//            int i2 = 2*i+1;
//            response.getWriter().write("\""+i2+"\"");
//            response.getWriter().write(",");
//            response.getWriter().write(s2.substring(1,s2.length()));
//            response.getWriter().write("]},");
//        }
//        String s = mapper.writeValueAsString(tem[tem.length-2]);
//        response.getWriter().write("{\"id\":");
//        int i1 =  tem.length-2;
//        response.getWriter().write("\""+i1+"\"");
//        response.getWriter().write(",");
//        response.getWriter().write(s.substring(1,s.length()-1));
//        response.getWriter().write(",\"children\":[");
//        String s2 = mapper.writeValueAsString(tem[tem.length-1]);
//        response.getWriter().write("{\"id\":");
//        int i2 = tem.length-1;
//        response.getWriter().write("\""+i2+"\"");
//        response.getWriter().write(",");
//        response.getWriter().write(s2.substring(1,s2.length()));
//        response.getWriter().write("]}]");
        response.getWriter().close();
    }
    @RequestMapping("/login")
    public void login(String account,String password,HttpServletRequest request, HttpServletResponse response) throws
            IOException{
        response.setCharacterEncoding("utf-8");
        String ps = this.userService.login(account);
        System.out.println("ps"+ps);
        System.out.println("password"+password);
//        response.getWriter().write("true");
        try {
            if(ps.equals(password)){
                response.getWriter().write("{\"status\":\"true\"}");
            }else{
                response.getWriter().write("{\"status\":\"false\"}");
            }
        }catch (NullPointerException e){
            response.getWriter().write("{\"status\":\"false\"}");
        }

        response.getWriter().close();
    }
    @RequestMapping("/Dingdan/select/All")
    public void selectByAccount(String account,HttpServletRequest request, HttpServletResponse response) throws
            IOException{
        response.setCharacterEncoding("utf-8");
        Dingdan[] result = this.dingdanService.selectByAccount(account);
        ObjectMapper mapper = new ObjectMapper();
        if(result!=null){
            response.getWriter().write("[");
            for(int i=0;i<result.length -1 ;i++){
                response.getWriter().write(mapper.writeValueAsString(result[i]));
                response.getWriter().write(",");
            }
            response.getWriter().write(mapper.writeValueAsString(result[result.length-1]));
            response.getWriter().write("]");
        }


        response.getWriter().close();
    }

    @RequestMapping("/Ticket/select/AllByOrder")
    public void selectTicketsByOrder(String order_id,HttpServletRequest request, HttpServletResponse response) throws
            IOException{
        int order = Integer.parseInt(order_id);
        response.setCharacterEncoding("utf-8");
        Ticket[] result = this.ticketService.selectTicketsByOrder(order);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write("[");
        for(int i=0;i<result.length -1 ;i++){
            response.getWriter().write(mapper.writeValueAsString(result[i]));
            response.getWriter().write(",");
        }
        response.getWriter().write(mapper.writeValueAsString(result[result.length-1]));
        response.getWriter().write("]");

        response.getWriter().close();
    }
    @RequestMapping("/template/select/simple")
    public void selectTemplateSimple(String train_no,String start_station,String end_station,HttpServletRequest request, HttpServletResponse response) throws
            IOException{
        boolean isStart = false;
        template result = this.templateService.selectTemplateSimple(train_no, start_station, end_station);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("[");
        if(result.getTDZ()!=null){
            response.getWriter().write("{");
            response.getWriter().write("\"seat_type\":");
            response.getWriter().write("\"特等座\"");
            response.getWriter().write(",");
            response.getWriter().write("\"seat_price\":");
            response.getWriter().write(result.getTDZ());
            response.getWriter().write("}");
            isStart = true;
        }
        if(result.getYDZ()!=null)
        {
            if(isStart){
                response.getWriter().write(",");
            }
            response.getWriter().write("{");
            response.getWriter().write("\"seat_type\":");
            response.getWriter().write("\"一等座\"");
            response.getWriter().write(",");
            response.getWriter().write("\"seat_price\":");
            response.getWriter().write(result.getYDZ());
            response.getWriter().write("}");
            isStart = true;

        }
        if(result.getEDZ()!=null)
            {

                if(isStart){
                    response.getWriter().write(",");
                }
                response.getWriter().write("{");
                response.getWriter().write("\"seat_type\":");
                response.getWriter().write("\"二等座\"");
                response.getWriter().write(",");
                response.getWriter().write("\"seat_price\":");
                response.getWriter().write(result.getEDZ());
                response.getWriter().write("}");
                isStart = true;
            }
        if(result.getGJRW()!=null)
        {

            if(isStart){
                response.getWriter().write(",");
            }
            response.getWriter().write("{");
            response.getWriter().write("\"seat_type\":");
            response.getWriter().write("\"高级软卧\"");
            response.getWriter().write(",");
            response.getWriter().write("\"seat_price\":");
            response.getWriter().write(result.getGJRW());
            response.getWriter().write("}");
            isStart = true;
        }
        if(result.getRW()!=null)
           {
               if(isStart){
                   response.getWriter().write(",");
               }
               response.getWriter().write("{");
               response.getWriter().write("\"seat_type\":");
               response.getWriter().write("\"软卧\"");
               response.getWriter().write(",");
               response.getWriter().write("\"seat_price\":");
               response.getWriter().write(result.getRW());
               response.getWriter().write("}");
               isStart = true;

           }
        if(result.getDW()!=null)
        {

            if(isStart){
                response.getWriter().write(",");
            }
            response.getWriter().write("{");
            response.getWriter().write("\"seat_type\":");
            response.getWriter().write("\"动卧\"");
            response.getWriter().write(",");
            response.getWriter().write("\"seat_price\":");
            response.getWriter().write(result.getDW());
            response.getWriter().write("}");
            isStart = true;
        }
        if(result.getYW()!=null)
        {
            if(isStart){
                response.getWriter().write(",");
            }
            response.getWriter().write("{");
            response.getWriter().write("\"seat_type\":");
            response.getWriter().write("\"硬卧\"");
            response.getWriter().write(",");
            response.getWriter().write("\"seat_price\":");
            response.getWriter().write(result.getYW());
            response.getWriter().write("}");
            isStart = true;
        }
        if(result.getRZ()!=null)
        {
            if(isStart){
                response.getWriter().write(",");
            }
            response.getWriter().write("{");
            response.getWriter().write("\"seat_type\":");
            response.getWriter().write("\"软座\"");
            response.getWriter().write(",");
            response.getWriter().write("\"seat_price\":");
            response.getWriter().write(result.getRZ());
            response.getWriter().write("}");
            isStart = true;

        }
        if(result.getYZ()!=null)
           {
               if(isStart){
                   response.getWriter().write(",");
               }
               response.getWriter().write("{");
               response.getWriter().write("\"seat_type\":");
               response.getWriter().write("\"硬座\"");
               response.getWriter().write(",");
               response.getWriter().write("\"seat_price\":");
               response.getWriter().write(result.getYZ());
               response.getWriter().write("}");
               isStart = true;

           }
        if(result.getWZ()!=null)
        {
            if(isStart){
                response.getWriter().write(",");
            }
            response.getWriter().write("{");
            response.getWriter().write("\"seat_type\":");
            response.getWriter().write("\"无座\"");
            response.getWriter().write(",");
            response.getWriter().write("\"seat_price\":");
            response.getWriter().write(result.getWZ());
            response.getWriter().write("}");
            isStart = true;

        }
        response.getWriter().write("]");
        response.getWriter().close();
    }
    @RequestMapping("/ticket/insert/one")
    public void insertTicket(String train_no,String from_station, String to_station,String id,String seat_type,String price,
                             String date,String start_time,String end_time,String arrive_day_diff,String carriage_id,
                             String seat_number,String order_id,HttpServletRequest request, HttpServletResponse response) throws
            IOException{
        System.out.println("train_no"+train_no);
        System.out.println("from"+from_station);
        System.out.println("to"+to_station);
        System.out.println("id"+id);
        System.out.println("seat_type"+seat_type);
        System.out.println("price"+price);
        System.out.println("date"+date);
        System.out.println("start_time"+start_time);
        System.out.println("end_time"+end_time);
        System.out.println("arrive"+arrive_day_diff);
        System.out.println("carriage"+carriage_id);
        System.out.println("seat_number"+seat_number);
        System.out.println("order_id"+order_id);
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Ticket ticket = new Ticket();
        ticket.setTrain_no(train_no);
        ticket.setFrom_station(from_station);
        ticket.setTo_station(to_station);
        ticket.setId(id);
        String s_t = "";
        if(seat_type.equals("特等座")){
            s_t = "TDZ";
        }else if(seat_type.equals("一等座")){
            s_t = "YDZ";
        }
        else if(seat_type.equals("二等座")){
            s_t = "EDZ";
        }
        else if(seat_type.equals("高级软卧")){
            s_t = "RJRW";
        }
        else if(seat_type.equals("动卧")){
            s_t = "DW"; }
        else if(seat_type.equals("软卧")){
            s_t = "RW";
        }
        else if(seat_type.equals("硬卧")){
            s_t = "YW";
        }
        else if(seat_type.equals("软座")){
            s_t = "RZ";
        }
        else if(seat_type.equals("硬座")){
            s_t = "YZ";
        }else if(seat_type.equals("无座")){
            s_t = "WZ";
        }
        ticket.setSeat_type(s_t);
        ticket.setPrice(price);
        try {
            ticket.setDate(format1.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ticket.setStart_time(start_time);
        ticket.setEnd_time(end_time);
        ticket.setArrive_day_diff(Integer.parseInt(arrive_day_diff));
        ticket.setCarriage_id(Integer.parseInt(carriage_id));
        ticket.setSeat_number(Integer.parseInt(seat_number));
        ticket.setOrder_id(Integer.parseInt(order_id));

        this.ticketService.insertTicket(ticket);


    }
    @RequestMapping("/ticket/select/seat_type")
    public void selectSeatType(String train_no,String seat_type,String index,HttpServletRequest request, HttpServletResponse response) throws
            IOException{
        String s_t = "";
        int inde = 0;
        if(seat_type.equals("特等座")){
            s_t = "TDZ";
            inde = 0;
        }else if(seat_type.equals("一等座")){
            s_t = "YDZ";
            inde = 1;
        }
        else if(seat_type.equals("二等座")){
            s_t = "EDZ";
            inde = 2;
        }
        else if(seat_type.equals("高级软卧")){
            s_t = "RJRW";
            inde = 3;
        }
        else if(seat_type.equals("动卧")){
            s_t = "DW";
            inde = 5;
        }
        else if(seat_type.equals("软卧")){
            s_t = "RW";
            inde = 4;
        }
        else if(seat_type.equals("硬卧")){
            s_t = "YW";
            inde = 6;
        }
        else if(seat_type.equals("软座")){
            s_t = "RZ";
            inde = 7;
        }
        else if(seat_type.equals("硬座")){
            s_t = "YZ";
            inde = 8;
        }else if(seat_type.equals("无座")){
            s_t = "WZ";
            inde = 9;
        }
        int count = this.ticketService.selectSeatType(train_no,s_t);
        int c_index = Integer.parseInt(index);
        System.out.println(seat_type+a[inde][1]);
        int carriage_id = 1;
        int seat_id = 0;
        for(int i=1;i<=a[inde][0];i++){
            if(i*a[inde][1]>count){
                carriage_id = i;
                seat_id = count-a[inde][1]*(i-1) + 1 ;
                break;
            }
        }
        carriage_id += c_index*2;
        response.getWriter().write("{\"carriage_id\":");
        response.getWriter().write(carriage_id+"");
        response.getWriter().write(",");
        response.getWriter().write("\"seat_number\":");
        response.getWriter().write(seat_id+"");
        response.getWriter().write("}");
        response.getWriter().close();
    }

    @RequestMapping("/Dingdan/insert/one")
    public void insertDD(String from_station,String to_station,String date,String account,String money,HttpServletRequest request, HttpServletResponse response) throws
            IOException{

        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println("from"+from_station);
//        System.out.println("to"+to_station);
//        System.out.println("date"+date);
//        System.out.println("account"+account);
//        System.out.println("money"+money);
        Dingdan dd = new Dingdan();
        dd.setFrom_station(from_station);
        dd.setTo_station(to_station);
        try {
            dd.setDate(format1.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dd.setAccount(account);
        dd.setMoney(money);
        this.dingdanService.insertDD(dd);
        int order_id = this.dingdanService.selectMax();
        response.getWriter().write("{\"order_id\":");
        response.getWriter().write(order_id+"");
        response.getWriter().write("}");
        response.getWriter().close();
//        System.out.println(date);
//        System.out.println(new Date(date).getTime());

    }
    @RequestMapping("/Passenger/insert/pas")
    public void insertP(String id,String name,HttpServletRequest request, HttpServletResponse response) throws
            IOException{
        passenger p = new passenger();
        p.setId(id);
        p.setName(name);
        this.passengerService.insertP(p);
    }
    @RequestMapping("/date")
    public void DateAdd(String date,String delta,HttpServletRequest request, HttpServletResponse response) throws
            IOException, ParseException {
        SimpleDateFormat sj = new SimpleDateFormat("yyyy-MM-dd");
        Date d = sj.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        int p = Integer.parseInt(delta);
        calendar.add(Calendar.DATE, p);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("{");
        response.getWriter().write("\"date\":\"");
        response.getWriter().write(sj.format(calendar.getTime()));
        response.getWriter().write("\"");
        response.getWriter().write("}");
        response.getWriter().close();
    }

    @RequestMapping("/passenger/select/test")
    public void selectName(String id,HttpServletRequest request, HttpServletResponse response) throws
            IOException{
        String name = this.passengerService.selectNameById(id);
        System.out.println(name);
        try {
            if (name.equals(null)) {
                System.out.println(123);
            } else {
                response.getWriter().write("{");
                response.getWriter().write("\"status\":");
                response.getWriter().write("\"false\"");
                response.getWriter().write("}");
            }
        }catch (Exception e){
            response.getWriter().write("{");
            response.getWriter().write("\"status\":");
            response.getWriter().write("\"true\"");
            response.getWriter().write("}");
        }
        response.getWriter().close();
    }

    @RequestMapping("/user/select/passenger")
    public void selectPassenger(String account,HttpServletRequest request, HttpServletResponse response) throws
            IOException, ParseException {
        user u = this.userService.selectUserByacc(account);
        if(u.getId()!=null){
            String id = this.passengerService.selectNameById(u.getId());
            response.setCharacterEncoding("utf-8");
            ObjectMapper mapper = new ObjectMapper();
            String content = mapper.writeValueAsString(u);
            response.getWriter().write("{");
            response.getWriter().write("\"name\":");
            response.getWriter().write("\"");
            response.getWriter().write(id);
            response.getWriter().write("\"");
            response.getWriter().write(",");
            response.getWriter().write(content.substring(1));
            response.getWriter().close();
        }else{
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(mapper.writeValueAsString(u));
        }

    }

    @RequestMapping("/ticket/delete/undo")
    public void deleteTicket(String ticket_id,HttpServletRequest request, HttpServletResponse response) throws
            IOException{
        int t_i = Integer.parseInt(ticket_id);
        int i = this.ticketService.deleteTicket(t_i);
        int res = this.ticketService.selectCount(i);
        if(res==0){
            this.dingdanService.deleteDD(i);
        }

    }

    @RequestMapping("/dingDan/update/date")
    public void updateDingdan(String order_id,String newdate,String oldDate,HttpServletRequest request, HttpServletResponse response) throws
            IOException, ParseException {
        SimpleDateFormat sj = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = sj.parse(newdate);
        Date olddate = sj.parse(oldDate);
        int order = Integer.parseInt(order_id);
        this.dingdanService.updateDate(order,newDate);
        this.ticketService.updateTicket(order,newDate,olddate);
    }

    @RequestMapping("/user/insert/register")
    public void register(String account,String password,HttpServletRequest request, HttpServletResponse response) throws
            IOException{
        this.userService.register(account,password);
    }
    @RequestMapping("/passenger/add/user")
    public void link(String account,String id,String name,HttpServletRequest request, HttpServletResponse response) throws
            IOException{
        String Name = this.passengerService.selectNameById(id);
        if (Name.equals(null)) {
            passenger p = new passenger();
            p.setName(name);
            p.setId(id);
            this.passengerService.insertP(p);
            this.userService.updateId(account,id);
        }else{
            this.userService.updateId(account, id);
        }
    }


    @RequestMapping("/user/update/password")
    public void updatePassword(String account,String password,HttpServletRequest request, HttpServletResponse response) throws
            IOException{
        this.userService.updatePassword(account, password);
    }
    @PostMapping("/test")
    public void ma(@RequestBody Map dataMap, HttpServletRequest request, HttpServletResponse response) throws
            IOException{

        String seat_type = (String) dataMap.get("seat_type");
        String train_no = (String) dataMap.get("train_no");

        String s_t = "";
        int inde = 0;
        if(seat_type.equals("特等座")){
            s_t = "TDZ";
            inde = 0;
        }else if(seat_type.equals("一等座")){
            s_t = "YDZ";
            inde = 1;
        }
        else if(seat_type.equals("二等座")){
            s_t = "EDZ";
            inde = 2;
        }
        else if(seat_type.equals("高级软卧")){
            s_t = "RJRW";
            inde = 3;
        }
        else if(seat_type.equals("动卧")){
            s_t = "DW";
            inde = 5;
        }
        else if(seat_type.equals("软卧")){
            s_t = "RW";
            inde = 4;
        }
        else if(seat_type.equals("硬卧")){
            s_t = "YW";
            inde = 6;
        }
        else if(seat_type.equals("软座")){
            s_t = "RZ";
            inde = 7;
        }
        else if(seat_type.equals("硬座")){
            s_t = "YZ";
            inde = 8;
        }else if(seat_type.equals("无座")){
            s_t = "WZ";
            inde = 9;
        }
        int count = this.ticketService.selectSeatType(train_no,s_t);
        int c_index = (int)dataMap.get("index");
        System.out.println(seat_type+a[inde][1]);
        int carriage_id = 1;
        int seat_id = 0;
        for(int i=1;i<=a[inde][0];i++){
            if(i*a[inde][1]>count){
                carriage_id = i;
                seat_id = count-a[inde][1]*(i-1) + 1 ;
                break;
            }
        }
        carriage_id += c_index*2;


        String aa = (String) dataMap.get("ff");
        JSONArray af = JSONArray.fromObject(aa);
        for(int i=0;i<af.size();i++){
            JSONObject p = af.getJSONObject(i);
            String id = (String) p.get("id");
            if(id!=null) {
                int carriage_id1 = carriage_id;
                int seat_id1 = seat_id + i + 1;
                if(seat_id1-1 >= a[inde][1]){
                    carriage_id1++;
                    seat_id1 = seat_id1 - a[inde][1];
                }

                String name = this.passengerService.selectNameById(id);
                if(name==null){
                    passenger pf = new passenger();
                    pf.setId(id);
                    pf.setName((String)p.get("name"));
                    this.passengerService.insertP(pf);


                    DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                    Ticket ticket = new Ticket();
                    ticket.setTrain_no(train_no);
                    ticket.setFrom_station((String)dataMap.get("from_station"));
                    ticket.setTo_station((String)dataMap.get("to_station"));
                    ticket.setId(id);
                    ticket.setSeat_type(s_t);
                    ticket.setPrice(dataMap.get("price")+"");
                    try {
                        ticket.setDate(format1.parse((String)dataMap.get("date")));
                    } catch (ParseException ef) {
                        ef.printStackTrace();
                    }
                    ticket.setStart_time((String)dataMap.get("start_time"));
                    ticket.setEnd_time((String)dataMap.get("end_time"));
                    ticket.setArrive_day_diff((int)dataMap.get("arrive_day_diff"));
                    ticket.setCarriage_id(carriage_id1);
                    ticket.setSeat_number(seat_id1);
                    ticket.setOrder_id((int)dataMap.get("order_id"));

                    this.ticketService.insertTicket(ticket);
                }else{
                    DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                        Ticket ticket = new Ticket();
                        ticket.setTrain_no(train_no);
                        ticket.setFrom_station((String)dataMap.get("from_station"));
                        ticket.setTo_station((String)dataMap.get("to_station"));
                        ticket.setId(id);
                        ticket.setSeat_type(s_t);
                        ticket.setPrice(dataMap.get("price")+"");
                        try {
                            ticket.setDate(format1.parse((String)dataMap.get("date")));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        ticket.setStart_time((String)dataMap.get("start_time"));
                        ticket.setEnd_time((String)dataMap.get("end_time"));
                        ticket.setArrive_day_diff((int)dataMap.get("arrive_day_diff"));
                        ticket.setCarriage_id(carriage_id1);
                        ticket.setSeat_number(seat_id1);
                        ticket.setOrder_id((int)dataMap.get("order_id"));

                        this.ticketService.insertTicket(ticket);
                }
            }
        }

//        System.out.println( dataMap.get("ff").getClass());



    }

    @RequestMapping("/train/delete/trainNo")
    public void deleteTrain(String train,HttpServletRequest request, HttpServletResponse response) throws
            IOException{
//        try {
            this.trainService.deleteTrain(train);
//        }catch (Exception e){
//            response.getWriter().write("{");
//            response.getWriter().write("\"status\":");
//            response.getWriter().write("\"false\"");
//            response.getWriter().write("}");
//        }
//        response.getWriter().write("{");
//        response.getWriter().write("\"status\":");
//        response.getWriter().write("\"true\"");
//        response.getWriter().write("}");
//        response.getWriter().close();
    }
    @RequestMapping("/user/select/tem")
    public void selectUser(HttpServletRequest request, HttpServletResponse response) throws
            IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        user[] trains = this.userService.selectUser();
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write("[");
        for(int i=0;i<trains.length -1 ;i++){
            response.getWriter().write(mapper.writeValueAsString(trains[i]));
            response.getWriter().write(",");
        }
        response.getWriter().write(mapper.writeValueAsString(trains[trains.length-1]));
        response.getWriter().write("]");

        response.getWriter().close();
    }
@RequestMapping("/user/delete/user")
    public void deleteUser(String account,HttpServletRequest request, HttpServletResponse response) throws
        IOException{
        Dingdan[] dd = this.dingdanService.selectDD(account);
        for(int i=0;i<dd.length;i++){
            this.ticketService.deleteTs(dd[i].getOrder_id());
        }
        this.dingdanService.deleteDD(account);
        this.userService.deleteUser(account);
        }

        @RequestMapping("/train/delete")
    public void deleteTrain2(String train_no,HttpServletRequest request, HttpServletResponse response) throws
                IOException{
        this.templateService.deleteTem(train_no);
        this.ticketService.deleteT(train_no);
        this.train_timeService.deleteTT(train_no);
        this.trainService.deleteTrain(train_no);
        }

        @RequestMapping("/train_time/select/all")
    public void selectTrainTimeAll(HttpServletRequest request, HttpServletResponse response) throws
                IOException{
        response.setCharacterEncoding("utf-8");
        Dingdan[] tts = this.dingdanService.selectAll();
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write("[");
            for(int i=0;i<tts.length -1 ;i++){
                response.getWriter().write(mapper.writeValueAsString(tts[i]));
                response.getWriter().write(",");
            }
            response.getWriter().write(mapper.writeValueAsString(tts[tts.length-1]));
            response.getWriter().write("]");

            response.getWriter().close();
        }

        @RequestMapping("/ticket/select/all")
    public void selectAllTicket(HttpServletRequest request, HttpServletResponse response) throws
                IOException{
        response.setCharacterEncoding("utf-8");
        Ticket[] tts = this.ticketService.selectAll();
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write("[");
            for(int i=0;i<tts.length -1 ;i++){
                response.getWriter().write(mapper.writeValueAsString(tts[i]));
                response.getWriter().write(",");
            }
            response.getWriter().write(mapper.writeValueAsString(tts[tts.length-1]));
            response.getWriter().write("]");
            response.getWriter().close();
        }

        @RequestMapping(value = "/user/add/person",method = RequestMethod.POST)
    public void addUser(@RequestBody Map dataMap,HttpServletRequest request, HttpServletResponse response) throws
                IOException{
//            System.out.println(dataMap.get("name"));
            String name = (String) dataMap.get("name");
            String account = (String) dataMap.get("account");
            String password = (String) dataMap.get("password");
            String id = (String) dataMap.get("id");
            passenger p = new passenger();
            p.setId(id);
            p.setName(name);
            String kk = this.passengerService.selectNameById(id);
            if(kk == null) {
                this.passengerService.insertP(p);
            }
            user u = new user();
            u.setAccount(account);
            u.setId(id);
            u.setPassword(password);
            this.userService.insert(u);
        }
}
