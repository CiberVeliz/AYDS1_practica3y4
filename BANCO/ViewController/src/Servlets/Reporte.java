package Servlets;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Map;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;


@WebServlet(name = "Reporte", urlPatterns = { "/reporte" })
public class Reporte extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);

        Map parametros = null;

        try {
            //Parámetros que recibe el reporte
            parametros = request.getParameterMap();

            //Obtenemos los parámetros en este caso solamente uno, "tipoReporte"
            String[] params = (String[]) parametros.get("tipoReporte");
            String tipoReporte = params[0];
            String[] params2 = (String[]) parametros.get("nombreReporte");
            String nombreReporte = params2[0];
            //Obtenemos la variable de sesión que trae el objeto JasperPrint
            HttpSession session = request.getSession();
            JasperPrint reporte = (JasperPrint) session.getAttribute("reporte");

            ServletOutputStream outPdf = response.getOutputStream();
            //OutputStream out = response.getOutputStream();

            JRExporter exporter = null;

            try {

                if (parametros.containsKey("tipoReporte")) {
                    //Para el input del pdf se usa el archivo con la extensión jasper
                    if (tipoReporte.equalsIgnoreCase("pdf")) {

                        response.setHeader("Window-target:", "_blank");
                        response.setContentType("application/pdf");

                        if (reporte != null) {

                            ByteArrayOutputStream baos = new ByteArrayOutputStream();

                            exporter = new JRPdfExporter();
                            //exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, datos);//Para reportes en lote
                            exporter.setParameter(JRExporterParameter.JASPER_PRINT, reporte);
                            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
                            exporter.exportReport();
                            outPdf.write(baos.toByteArray());
                            outPdf.flush();
                        }
                        outPdf.close();

                    } else if (tipoReporte.equalsIgnoreCase("html")) {

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();

                        exporter = new JRHtmlExporter();
                        exporter.setParameter(JRExporterParameter.JASPER_PRINT, reporte);
                        //exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
                        exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, " ");
                        exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
                        //exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,imgPath);//Si se tuvieran imagenes
                        exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, " ");
                        exporter.exportReport();
                        outPdf.write(baos.toByteArray());
                        outPdf.flush();
                        outPdf.close();

                    }
                    //removemos la variable de sesión
                    //session.removeAttribute("reporte");
                }

            } catch (JRException e) {
                throw new ServletException(e);
            } finally {
                if (outPdf != null) {
                    try {
                        outPdf.flush(); //Tiraba una excepción
                        outPdf.close();
                    } catch (IOException ex) {
                        //System.out.println(ex.getMessage());
                        System.out.println("IOException se ha anulado una conexión establecida por el software");
                        ///throw (ex);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}