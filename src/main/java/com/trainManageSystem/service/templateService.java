package com.trainManageSystem.service;

import com.trainManageSystem.model.template;

public interface templateService {
    template[] selectTemplate(String start_station, String end_station);
    template selectTemplateSimple(String train_no,String start_station, String end_station);

    void deleteTem(String train_no);
}
