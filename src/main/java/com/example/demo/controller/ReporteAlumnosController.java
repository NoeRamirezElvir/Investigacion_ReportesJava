package com.example.demo.controller;


import com.example.demo.enums.tipoReporteEnum;
import com.example.demo.model.ReporteAlumnosDTO;
import com.example.demo.service.API.ReporteAlumnosServisAPI;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/reporte")
public class ReporteAlumnosController {
    @Autowired
    private ReporteAlumnosServisAPI reporteAlumnosServiceAPI;

    @GetMapping(path = "/alumnos/descargar")
    public ResponseEntity<Resource> descargar(@RequestParam Map<String, Object> parametro)
            throws JRException, IOException, SQLException {
        ReporteAlumnosDTO dto = reporteAlumnosServiceAPI.obtenerReporteAlumnos(parametro);

        InputStreamResource streamResource = new InputStreamResource(dto.getStream());
        MediaType mediaType = null;
        if (parametro.get("tipo").toString().equalsIgnoreCase(tipoReporteEnum.EXCEL.name())) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        } else {
            mediaType = MediaType.APPLICATION_PDF;
        }

        return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getNombreArchivo() + "\"")
                .contentLength(dto.getLongitud()).contentType(mediaType).body(streamResource);
    }
}
