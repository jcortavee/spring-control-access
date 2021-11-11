package com.umg.accesscontrol.services;

import com.umg.accesscontrol.dto.ReportDto;
import com.umg.accesscontrol.repositories.EmployeeRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public interface ReportService {

    ReportDto getReport(Map<String, Object> params) throws JRException, IOException, SQLException;

}
