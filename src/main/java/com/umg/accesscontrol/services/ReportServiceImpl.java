package com.umg.accesscontrol.services;

import com.umg.accesscontrol.dto.ReportDto;
import com.umg.accesscontrol.utils.JasperReportManager;
import com.umg.accesscontrol.utils.TipoReporteEnum;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private JasperReportManager reportManager;

    @Autowired
    private DataSource dataSource;

    @Override
    public ReportDto getReport(Map<String, Object> params) throws JRException, IOException, SQLException {

        String fileName = "";

        if (params.get("report").toString().equalsIgnoreCase("1")) {
            fileName = "AccessReportByDepartment";
        } else if (params.get("report").toString().equalsIgnoreCase("2")) {
            fileName = "AccessReport";
        } else if (params.get("report").toString().equalsIgnoreCase("3")) {
            fileName = "AccessReportByEmployee";
        } else if (params.get("report").toString().equalsIgnoreCase("4")) {
            fileName = "AccessReportLateEntry";
        } else if (params.get("report").toString().equalsIgnoreCase("5")) {
            fileName = "AccessReportEarlyOff";
        }

        ReportDto dto = new ReportDto();
        String extension = params.get("type").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx"
                : ".pdf";
        dto.setFileName(fileName + extension);

        ByteArrayOutputStream stream = reportManager.export(fileName, params.get("type").toString(), params,
                dataSource.getConnection());

        byte[] bs = stream.toByteArray();
        dto.setStream(new ByteArrayInputStream(bs));
        dto.setLength(bs.length);

        return dto;
    }
}
