package com.example.demo.service.API;

import com.example.demo.model.ReporteAlumnosDTO;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public interface ReporteAlumnosServisAPI {
    ReporteAlumnosDTO obtenerReporteAlumnos(Map<String, Object> params) throws JRException, IOException, SQLException;

}
