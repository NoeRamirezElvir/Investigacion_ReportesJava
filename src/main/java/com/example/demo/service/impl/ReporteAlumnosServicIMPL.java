package com.example.demo.service.impl;

import com.example.demo.commons.JasperReportManager;
import com.example.demo.enums.tipoReporteEnum;
import com.example.demo.model.ReporteAlumnosDTO;
import com.example.demo.service.API.ReporteAlumnosServisAPI;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class ReporteAlumnosServicIMPL implements ReporteAlumnosServisAPI {
    @Autowired
    private JasperReportManager reportManager;
    @Autowired
    private DataSource dataSource;
    public ReporteAlumnosDTO obtenerReporteAlumnos(Map<String, Object> params)
            throws JRException, IOException, SQLException {
        String fileName = "reporte_de_alumnos";
        ReporteAlumnosDTO dto = new ReporteAlumnosDTO();
        String extension = params.get("tipo").toString().equalsIgnoreCase(tipoReporteEnum.EXCEL.name()) ? ".xlsx"
                : ".pdf";
        dto.setNombreArchivo(fileName + extension);

        ByteArrayOutputStream stream = reportManager.exportar(fileName, params.get("tipo").toString(), params,
                dataSource.getConnection());

        byte[] bs = stream.toByteArray();
        dto.setStream(new ByteArrayInputStream(bs));
        dto.setLongitud(bs.length);

        return dto;
    }
}
