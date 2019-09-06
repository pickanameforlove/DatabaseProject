package com.trainManageSystem.service.impl;

import com.trainManageSystem.dao.TicketDao;
import com.trainManageSystem.model.Ticket;
import com.trainManageSystem.service.TicketService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

@Service("ticketService")
public class TicketServiceImpl implements TicketService {
    private int[][] a = new int[][]{{2,18},{2,48},{2,85},{2,16},{2,36},{2,36},{2,66},{2,120},{2,120},{2,24}};
    @Resource
    private TicketDao dao;
    @Override
    public int selectCountTicket(String train_id, int from, int to) {
        return this.dao.selectCountTicket(train_id, from, to);
    }

    @Override
    public Ticket[] selectTicketsByOrder(int order_id) {
        return this.dao.selectTicketsByOrder(order_id);
    }

    @Override
    public void insertTicket(Ticket ticket) {
        this.dao.insertTicket(ticket);
    }

    @Override
    public int selectSeatType(String train_no, String seat_type) {
        return this.dao.selectSeatType(train_no, seat_type);
    }

    @Override
    public int selectRest(String train_no, String from, String to, String seat_type) {
        return this.dao.selectRest(train_no, from, to, seat_type);
    }

    @Override
    public int selectRestPlus(String train_no, int from, int to, String seat_type) {
        int i =  this.dao.selectRestPlus(train_no, from, to, seat_type);
        int index = 0;
        if(seat_type.equals("TDZ")){
            index = 0;
        }else if(seat_type.equals("YDZ")){
            index = 1;
        }else if(seat_type.equals("EDZ")){
            index = 2;
        }else if(seat_type.equals("GJRW")){
            index = 3;
        }else if(seat_type.equals("RW")){
            index = 4;
        }else if(seat_type.equals("DW")){
            index = 5;
        }else if(seat_type.equals("YW")){
            index = 6;
        }else if(seat_type.equals("RZ")){
            index = 7;
        }else if(seat_type.equals("YZ")){
            index = 8;
        }else{
            index = 9;
        }
        int res = a[index][0] * a[index][1] - i;
        return  res;
    }

    @Override
    public int deleteTicket(int ticket_id) {
        int i = this.dao.selectOrder_id(ticket_id);
        this.dao.deleteTicket(ticket_id);
        return i;
    }

    @Override
    public int selectCount(int order_id) {
        return this.dao.selectCount(order_id);
    }

    @Override
    public void updateTicket(int order_id, Date newdate, Date oldDate) {

        int days = (int) ((newdate.getTime() - oldDate.getTime()) / (1000*3600*24));
        Ticket[] ts = this.dao.selectTicketSimple(order_id);
        Calendar calendar = Calendar.getInstance();
        for(int i=0;i<ts.length;i++){
            Date d1 = ts[i].getDate();
            calendar.setTime(d1);
            calendar.add(Calendar.DATE, days);
            this.dao.updateTicket(ts[i].getTicket_id(),calendar.getTime());
        }
    }

    @Override
    public void deleteTs(int order_id) {
        this.dao.deleteTs(order_id);
    }

    @Override
    public void deleteT(String train_no) {
        this.dao.deleteT(train_no);
    }

    @Override
    public Ticket[] selectAll() {
        return this.dao.selectAll();
    }
}
