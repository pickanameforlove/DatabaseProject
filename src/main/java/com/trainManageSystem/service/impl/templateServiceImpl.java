package com.trainManageSystem.service.impl;

import com.trainManageSystem.dao.templateDao;
import com.trainManageSystem.model.template;
import com.trainManageSystem.service.templateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("templateService")
public class templateServiceImpl implements templateService {
    @Resource
    private templateDao dao;
    @Override
    public template[] selectTemplate(String start_station, String end_station) {
        return this.dao.selectTemplate(start_station,end_station);
    }

    @Override
    public template selectTemplateSimple(String train_no, String start_station, String end_station) {
        return this.dao.selectTemplateSimple(train_no, start_station, end_station);
    }

    @Override
    public void deleteTem(String train_no) {
this.dao.deleteTem(train_no);
    }
}
