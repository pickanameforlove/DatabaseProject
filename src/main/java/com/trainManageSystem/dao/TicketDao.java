package com.trainManageSystem.dao;

import com.trainManageSystem.model.Ticket;

import java.util.Date;

public interface TicketDao {
    int selectCountTicket(String train_id,int from,int to);
    Ticket[] selectTicketsByOrder(int order_id);
    void insertTicket(Ticket ticket);
    int selectSeatType(String train_no,String seat_type);
    int selectRest(String train_no,String from,String to,String seat_type);
    int selectRestPlus(String train_no,int from,int to,String seat_type);
    void deleteTicket(int ticket_id);
    int selectOrder_id(int ticket_id);
    int selectCount(int order_id);
    Ticket[] selectTicketSimple(int order_id);
    void updateTicket(int ticket_id,Date date);
    void deleteTs(int order_id);
    void deleteT(String train_no);
    Ticket[] selectAll();


}
