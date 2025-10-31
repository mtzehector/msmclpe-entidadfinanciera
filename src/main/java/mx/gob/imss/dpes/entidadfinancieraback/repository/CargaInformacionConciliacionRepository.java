package mx.gob.imss.dpes.entidadfinancieraback.repository;

import mx.gob.imss.dpes.entidadfinancieraback.entity.MclcEstatusConciliacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CargaInformacionConciliacionRepository extends JpaRepository<MclcEstatusConciliacion, Long>, JpaSpecificationExecutor<MclcEstatusConciliacion> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO MGPMCLPE04.MCLT_RESUMEN_CONCILIACION "
            + "(CVE_RESUMEN_CONCILIACION,PERIODO,CVE_ENTIDAD_FINANCIERA_SIPRE,CVE_DELEGACION,PRESTAMOS_PAGADOS,IMPORTE_PAGADOS,PRESTAMOS_NO_APLICADOS,"
            + "IMPORTE_NO_APLICADOS,PRESTAMOS_FALLECIDOS,IMPORTE_FALLECIDOS,CASOS_301_380,IMPORTE_301_380,CASOS_301,IMPORTE_301,CASOS_380,IMPORTE_380,"
            + "CASOS_PAGADOS_301_380,IMPORTE_PAGADOS_301_380,CASOS_NO_PAGADOS,IMPORTE_NO_PAGADOS,TOTAL_RETENCIONES,CASOS_FALLECIDOS_RESUMEN,IMPORTE_FALLECIDOS_RESUMEN) "
            + "SELECT MGPMCLPE04.MCLS_RESUMEN_CONCILIACION.nextval,PERIODO,CVE_ENTIDAD_FINANCIERA_SIPRE,CVE_DELEGACION,PRESTAMOS_PAGADOS,IMPORTE_PAGADOS,PRESTAMOS_NO_APLICADOS,"
            + "IMPORTE_NO_APLICADOS,PRESTAMOS_FALLECIDOS,IMPORTE_FALLECIDOS,CASOS_301_380,IMPORTE_301_380,CASOS_301,IMPORTE_301,CASOS_380,IMPORTE_380,CASOS_PAGADOS_301_380,IMPORTE_PAGADOS_301_380,"
            + "CASOS_NO_PAGADOS,IMPORTE_NO_PAGADOS,TOTAL_RETENCIONES,CASOS_FALLECIDOS_RESUMEN,IMPORTE_FALLECIDOS_RESUMEN "
            + "FROM ("
            + "SELECT TblResumen.periodo,TblResumen.cve_entidad_financiera_sipre,TblResumen.cve_delegacion,"
            + "SUM(TblResumen.prestamos_pagados) PRESTAMOS_PAGADOS,SUM(TblResumen.importe_pagados) IMPORTE_PAGADOS,SUM(TblResumen.PRESTAMOS_NO_APLICADOS) PRESTAMOS_NO_APLICADOS,"
            + "SUM(TblResumen.IMPORTE_NO_APLICADOS) IMPORTE_NO_APLICADOS,SUM(TblResumen.PRESTAMOS_FALLECIDOS) PRESTAMOS_FALLECIDOS,SUM(TblResumen.IMPORTE_FALLECIDOS) IMPORTE_FALLECIDOS,"
            + "SUM(TblResumen.CASOS_301_380) CASOS_301_380,SUM(TblResumen.IMPORTE_301_380) IMPORTE_301_380,SUM(TblResumen.CASOS_301) CASOS_301,"
            + "SUM(TblResumen.IMPORTE_301) IMPORTE_301,SUM(TblResumen.CASOS_380) CASOS_380,SUM(TblResumen.IMPORTE_380) IMPORTE_380,"
            + "SUM(TblResumen.CASOS_PAGADOS_301_380) CASOS_PAGADOS_301_380,SUM(TblResumen.IMPORTE_PAGADOS_301_380) IMPORTE_PAGADOS_301_380,SUM(TblResumen.CASOS_NO_PAGADOS) CASOS_NO_PAGADOS,"
            + "SUM(TblResumen.IMPORTE_NO_PAGADOS) IMPORTE_NO_PAGADOS,SUM(TblResumen.TOTAL_RETENCIONES) TOTAL_RETENCIONES,SUM(TblResumen.CASOS_FALLECIDOS_RESUMEN) CASOS_FALLECIDOS_RESUMEN,"
            + "SUM(TblResumen.IMPORTE_FALLECIDOS_RESUMEN) IMPORTE_FALLECIDOS_RESUMEN "
            + "FROM ("
            + "SELECT periodo,cve_entidad_financiera_sipre,cve_delegacion,"
            + "COUNT(periodo) PRESTAMOS_PAGADOS,NVL(SUM(NVL(imp_recuperado,0)),0) IMPORTE_PAGADOS,"
            + "0 PRESTAMOS_NO_APLICADOS,0 IMPORTE_NO_APLICADOS,0 PRESTAMOS_FALLECIDOS,0 IMPORTE_FALLECIDOS,0 CASOS_301_380,0 IMPORTE_301_380,0 CASOS_301,0 IMPORTE_301,"
            + "0 CASOS_380,0 IMPORTE_380,0 CASOS_PAGADOS_301_380,0 IMPORTE_PAGADOS_301_380,0 CASOS_NO_PAGADOS,0 IMPORTE_NO_PAGADOS,0 TOTAL_RETENCIONES,0 CASOS_FALLECIDOS_RESUMEN,0 IMPORTE_FALLECIDOS_RESUMEN "
            + "FROM mgpmclpe04.mclt_descuento WHERE periodo = :periodo AND id_movimiento = 'DA' GROUP BY periodo,cve_entidad_financiera_sipre,cve_delegacion"
            + " UNION "
            + "SELECT periodo,cve_entidad_financiera_sipre,cve_delegacion,"
            + "0 PRESTAMOS_PAGADOS,0 IMPORTE_PAGADOS,"
            + "COUNT(periodo) PRESTAMOS_NO_APLICADOS,NVL(SUM(NVL(imp_descuento_nomina,0)),0) IMPORTE_NO_APLICADOS,"
            + "0 PRESTAMOS_FALLECIDOS,0 IMPORTE_FALLECIDOS,0 CASOS_301_380,0 IMPORTE_301_380,0 CASOS_301,0 IMPORTE_301,"
            + "0 CASOS_380,0 IMPORTE_380,0 CASOS_PAGADOS_301_380,0 IMPORTE_PAGADOS_301_380,0 CASOS_NO_PAGADOS,0 IMPORTE_NO_PAGADOS,0 TOTAL_RETENCIONES,0 CASOS_FALLECIDOS_RESUMEN,0 IMPORTE_FALLECIDOS_RESUMEN "
            + "FROM mgpmclpe04.mclt_descuento WHERE periodo = :periodo AND id_movimiento = 'NA' GROUP BY periodo,cve_entidad_financiera_sipre,cve_delegacion"
            + " UNION "
            + "SELECT periodo,cve_entidad_financiera_sipre,cve_delegacion,"
            + "0 PRESTAMOS_PAGADOS,0 IMPORTE_PAGADOS,0 PRESTAMOS_NO_APLICADOS,0 IMPORTE_NO_APLICADOS,"
            + "COUNT(periodo) PRESTAMOS_FALLECIDOS,NVL(SUM(NVL(imp_recuperado,0)),0) IMPORTE_FALLECIDOS,"
            + "0 CASOS_301_380,0 IMPORTE_301_380,0 CASOS_301,0 IMPORTE_301,"
            + "0 CASOS_380,0 IMPORTE_380,0 CASOS_PAGADOS_301_380,0 IMPORTE_PAGADOS_301_380,0 CASOS_NO_PAGADOS,0 IMPORTE_NO_PAGADOS,0 TOTAL_RETENCIONES,0 CASOS_FALLECIDOS_RESUMEN,0 IMPORTE_FALLECIDOS_RESUMEN "
            + "FROM mgpmclpe04.mclt_descuento WHERE periodo = :periodo AND id_movimiento = 'FA' GROUP BY periodo,cve_entidad_financiera_sipre,cve_delegacion"
            + " UNION "
            + "SELECT periodo,cve_entidad_financiera_sipre,cve_delegacion,"
            + "0 PRESTAMOS_PAGADOS,0 IMPORTE_PAGADOS,0 PRESTAMOS_NO_APLICADOS,0 IMPORTE_NO_APLICADOS,0 PRESTAMOS_FALLECIDOS,0 IMPORTE_FALLECIDOS,"
            + "COUNT(periodo) CASOS_301_380,NVL(SUM(NVL(imp_recuperado,0)),0) IMPORTE_301_380,"
            + "0 CASOS_301,0 IMPORTE_301,"
            + "0 CASOS_380,0 IMPORTE_380,0 CASOS_PAGADOS_301_380,0 IMPORTE_PAGADOS_301_380,0 CASOS_NO_PAGADOS,0 IMPORTE_NO_PAGADOS,0 TOTAL_RETENCIONES,0 CASOS_FALLECIDOS_RESUMEN,0 IMPORTE_FALLECIDOS_RESUMEN "
            + "FROM mgpmclpe04.mclt_descuento WHERE periodo = :periodo AND id_movimiento <> 'FA' AND desc_concepto in (301,380) GROUP BY periodo,cve_entidad_financiera_sipre,cve_delegacion"
            + " UNION "
            + "SELECT periodo,cve_entidad_financiera_sipre,cve_delegacion,"
            + "0 PRESTAMOS_PAGADOS,0 IMPORTE_PAGADOS,0 PRESTAMOS_NO_APLICADOS,0 IMPORTE_NO_APLICADOS,0 PRESTAMOS_FALLECIDOS,0 IMPORTE_FALLECIDOS,0 CASOS_301_380,0 IMPORTE_301_380,"
            + "COUNT(periodo) CASOS_301,NVL(SUM(NVL(imp_recuperado,0)),0) IMPORTE_301,"
            + "0 CASOS_380,0 IMPORTE_380,0 CASOS_PAGADOS_301_380,0 IMPORTE_PAGADOS_301_380,0 CASOS_NO_PAGADOS,0 IMPORTE_NO_PAGADOS,0 TOTAL_RETENCIONES,0 CASOS_FALLECIDOS_RESUMEN,0 IMPORTE_FALLECIDOS_RESUMEN "
            + "FROM mgpmclpe04.mclt_descuento WHERE periodo = :periodo AND id_movimiento <> 'FA' AND desc_concepto in (301) GROUP BY periodo,cve_entidad_financiera_sipre,cve_delegacion"
            + " UNION "
            + "SELECT periodo,cve_entidad_financiera_sipre,cve_delegacion,"
            + "0 PRESTAMOS_PAGADOS,0 IMPORTE_PAGADOS,0 PRESTAMOS_NO_APLICADOS,0 IMPORTE_NO_APLICADOS,0 PRESTAMOS_FALLECIDOS,0 IMPORTE_FALLECIDOS,0 CASOS_301_380,0 IMPORTE_301_380,0 CASOS_301,0 IMPORTE_301,"
            + "COUNT(periodo) CASOS_380,NVL(SUM(NVL(imp_recuperado,0)),0) IMPORTE_380,"
            + "0 CASOS_PAGADOS_301_380,0 IMPORTE_PAGADOS_301_380,0 CASOS_NO_PAGADOS,0 IMPORTE_NO_PAGADOS,0 TOTAL_RETENCIONES,0 CASOS_FALLECIDOS_RESUMEN,0 IMPORTE_FALLECIDOS_RESUMEN "
            + "FROM mgpmclpe04.mclt_descuento WHERE periodo = :periodo AND id_movimiento <> 'FA' AND desc_concepto in (380) GROUP BY periodo,cve_entidad_financiera_sipre,cve_delegacion"
            + " UNION "
            + "SELECT periodo,cve_entidad_financiera_sipre,cve_delegacion,"
            + "0 PRESTAMOS_PAGADOS,0 IMPORTE_PAGADOS,0 PRESTAMOS_NO_APLICADOS,0 IMPORTE_NO_APLICADOS,0 PRESTAMOS_FALLECIDOS,0 IMPORTE_FALLECIDOS,0 CASOS_301_380,0 IMPORTE_301_380,0 CASOS_301,0 IMPORTE_301,0 CASOS_380,0 IMPORTE_380,"
            + "COUNT(periodo) CASOS_PAGADOS_301_380,NVL(SUM(NVL(imp_recuperado,0)),0) IMPORTE_PAGADOS_301_380,"
            + "0 CASOS_NO_PAGADOS,0 IMPORTE_NO_PAGADOS,0 TOTAL_RETENCIONES,0 CASOS_FALLECIDOS_RESUMEN,0 IMPORTE_FALLECIDOS_RESUMEN "
            + "FROM mgpmclpe04.mclt_descuento WHERE periodo = :periodo AND id_movimiento = 'DA' AND desc_concepto in (301,380) GROUP BY periodo,cve_entidad_financiera_sipre,cve_delegacion"
            + " UNION "
            + "SELECT periodo,cve_entidad_financiera_sipre,cve_delegacion,"
            + "0 PRESTAMOS_PAGADOS,0 IMPORTE_PAGADOS,0 PRESTAMOS_NO_APLICADOS,0 IMPORTE_NO_APLICADOS,0 PRESTAMOS_FALLECIDOS,0 IMPORTE_FALLECIDOS,0 CASOS_301_380,0 IMPORTE_301_380,0 CASOS_301,0 IMPORTE_301,0 CASOS_380,0 IMPORTE_380,0 CASOS_PAGADOS_301_380,0 IMPORTE_PAGADOS_301_380,"
            // + "COUNT(periodo) CASOS_NO_PAGADOS,NVL(SUM(NVL(imp_descuento_nomina,0)),0) IMPORTE_NO_PAGADOS,"
            + "COUNT(periodo) CASOS_NO_PAGADOS,NVL(SUM(NVL(imp_recuperado,0)),0) IMPORTE_NO_PAGADOS,"
            + "0 TOTAL_RETENCIONES,0 CASOS_FALLECIDOS_RESUMEN,0 IMPORTE_FALLECIDOS_RESUMEN "
            + "FROM mgpmclpe04.mclt_descuento WHERE periodo = :periodo AND id_movimiento = 'NA' GROUP BY periodo,cve_entidad_financiera_sipre,cve_delegacion"
            + " UNION "
            + "SELECT periodo,cve_entidad_financiera_sipre,cve_delegacion,"
            + "0 PRESTAMOS_PAGADOS,0 IMPORTE_PAGADOS,0 PRESTAMOS_NO_APLICADOS,0 IMPORTE_NO_APLICADOS,0 PRESTAMOS_FALLECIDOS,0 IMPORTE_FALLECIDOS,0 CASOS_301_380,0 IMPORTE_301_380,0 CASOS_301,0 IMPORTE_301,0 CASOS_380,0 IMPORTE_380,0 CASOS_PAGADOS_301_380,0 IMPORTE_PAGADOS_301_380,0 CASOS_NO_PAGADOS,0 IMPORTE_NO_PAGADOS,"
            + "NVL(SUM(NVL(imp_recuperado,0)),0) TOTAL_RETENCIONES,"
            + "0 CASOS_FALLECIDOS_RESUMEN,0 IMPORTE_FALLECIDOS_RESUMEN "
            + "FROM mgpmclpe04.mclt_descuento WHERE periodo = :periodo AND id_movimiento = 'DA' GROUP BY periodo,cve_entidad_financiera_sipre,cve_delegacion"
            + " UNION "
            + "SELECT periodo,cve_entidad_financiera_sipre,cve_delegacion,"
            + "0 PRESTAMOS_PAGADOS,0 IMPORTE_PAGADOS,0 PRESTAMOS_NO_APLICADOS,0 IMPORTE_NO_APLICADOS,0 PRESTAMOS_FALLECIDOS,0 IMPORTE_FALLECIDOS,0 CASOS_301_380,0 IMPORTE_301_380,0 CASOS_301,0 IMPORTE_301,0 CASOS_380,0 IMPORTE_380,0 CASOS_PAGADOS_301_380,0 IMPORTE_PAGADOS_301_380,0 CASOS_NO_PAGADOS,0 IMPORTE_NO_PAGADOS,0 TOTAL_RETENCIONES,"
            + "COUNT(periodo) CASOS_FALLECIDOS_RESUMEN,NVL(SUM(NVL(imp_recuperado,0)),0) IMPORTE_FALLECIDOS_RESUMEN "
            + "FROM mgpmclpe04.mclt_descuento WHERE periodo = :periodo AND id_movimiento = 'FA' GROUP BY periodo,cve_entidad_financiera_sipre,cve_delegacion"
            + ") TblResumen GROUP BY TblResumen.periodo,TblResumen.cve_entidad_financiera_sipre,TblResumen.cve_delegacion)"
            , nativeQuery = true)
    Integer insertsInResumenConcilicacion(
            @Param("periodo") String periodo
    );

    @Transactional
    @Modifying
    @Query(value = "MERGE INTO mgpmclpe04.mclt_resumen_conciliacion conciliacion " +
            "USING (" +
                    "SELECT " +
                        "resumen.cve_entidad_financiera_sipre cve_sipre," +
                        "resumen.cve_delegacion delegacion," +
                        "resumen.importe_pagados * tblPorcentaje.porcentaje_administracion costo_administrativo," +
                        "resumen.total_retenciones * tblPorcentaje.porcentaje_administracion costo_admin_resumen " +
                    "FROM mgpmclpe04.mclt_resumen_conciliacion resumen " +
                "INNER JOIN (" +
                        "SELECT " +
                            "entidad.cve_entidad_financiera_sipre," +
                            "porcentaje.porcentaje_administracion " +
                        "FROM mgpmclpe04.mclc_inst_financiera_convenio entidad " +
                        "LEFT JOIN " +
                            "mgpmclpe04.mclc_convenio_admin_financiera porcentaje " +
                        "ON entidad.cve_convenio_admin_financiera = porcentaje.cve_convenio_admin_financiera " +
                        "WHERE :periodo >= entidad.periodo_inicio_vigencia AND " +
                        ":periodo <= entidad.periodo_fin_vigencia" +
                ") tblPorcentaje " +
            "ON resumen.cve_entidad_financiera_sipre = tblPorcentaje.cve_entidad_financiera_sipre WHERE resumen.periodo = :periodo) tblPorcentaje_Administrativo " +
            "ON (conciliacion.cve_entidad_financiera_sipre = tblPorcentaje_Administrativo.cve_sipre AND conciliacion.cve_delegacion = tblPorcentaje_Administrativo.delegacion) " +
            "WHEN MATCHED THEN " +
                "UPDATE SET conciliacion.porcentaje_costo_admin = costo_administrativo,conciliacion.porcentaje_costo_admin_resumen = costo_admin_resumen " +
                "WHERE conciliacion.periodo = :periodo"
            , nativeQuery = true)
    Integer updatePorcentajeCostoAdminAndPorcentajeCostoResumen(
            @Param("periodo") String periodo
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE mgpmclpe04.mclt_resumen_conciliacion SET " +
            "iva_costo_admin = porcentaje_costo_admin * (SELECT TO_NUMBER(valor) as iva FROM mgpmclpe04.mclc_parametro WHERE cve_parametro = 2)," +
            "iva_costo_admin_resumen = porcentaje_costo_admin_resumen * (SELECT TO_NUMBER(valor) as iva FROM mgpmclpe04.mclc_parametro WHERE cve_parametro = 2) " +
            "WHERE periodo = :periodo"
            , nativeQuery = true)
    Integer updateIvaCostoAdminAndIvaCostoResumen(
            @Param("periodo") String periodo
    );

    //ESTE PROCESO SE FUE SUSTITUIDO POR "actualizaPorcentajeIvaPermisoItinerante", LINEA NUMERO 205, PORQUE EL USUARIO INDICO QUE YA NO APLICA PERMISO ITINERANTE
    @Transactional
    @Modifying
    @Query(value = "MERGE INTO mgpmclpe04.mclt_resumen_conciliacion conciliacion " +
            "USING (" +
                        "SELECT " +
                            "resumen.cve_entidad_financiera_sipre cve_sipre," +
                            "resumen.cve_delegacion delegacion," +
                            "resumen.importe_pagados * tblPorcentaje.porcentaje_permiso_itinerante imp_permiso_itinerante," +
                            "resumen.total_retenciones * tblPorcentaje.porcentaje_permiso_itinerante imp_permiso_itinerante_resumen " +
                        "FROM mgpmclpe04.mclt_resumen_conciliacion resumen " +
                    "INNER JOIN (" +
                        "SELECT " +
                            "entidad.cve_entidad_financiera_sipre," +
                            "porcentaje.porcentaje_permiso_itinerante " +
                        "FROM mgpmclpe04.mclc_inst_financiera_convenio entidad " +
                        "LEFT JOIN " +
            "               mgpmclpe04.mclc_convenio_admin_financiera porcentaje " +
                        "ON entidad.cve_convenio_admin_financiera = porcentaje.cve_convenio_admin_financiera " +
                        "WHERE :periodo >= entidad.periodo_inicio_vigencia AND " +
                               ":periodo <= entidad.periodo_fin_vigencia) tblPorcentaje " +
                        "ON resumen.cve_entidad_financiera_sipre = tblPorcentaje.cve_entidad_financiera_sipre WHERE resumen.periodo = :periodo) tblPorcentaje_Itinerante " +
                        "ON (conciliacion.cve_entidad_financiera_sipre = tblPorcentaje_Itinerante.cve_sipre AND conciliacion.cve_delegacion = tblPorcentaje_Itinerante.delegacion) " +
                        "WHEN MATCHED THEN " +
                            "UPDATE SET conciliacion.porcentaje_permiso_itinerante = imp_permiso_itinerante,conciliacion.permiso_itinerante_resumen = imp_permiso_itinerante_resumen  " +
                            "WHERE conciliacion.periodo = :periodo"
            , nativeQuery = true)
    Integer updatePorcentajePermisoItineranteAndPorcentajeItineranteResumen(
            @Param("periodo") String periodo
    );

    //ESTE PROCESO SE FUE SUSTITUIDO POR "actualizaPorcentajeIvaPermisoItinerante", LINEA NUMERO 205, PORQUE EL USUARIO INDICO QUE YA NO APLICA PERMISO ITINERANTE
    @Transactional
    @Modifying
    @Query(value = "UPDATE mgpmclpe04.mclt_resumen_conciliacion SET " +
            "iva_permiso_itinerante = porcentaje_permiso_itinerante * (SELECT TO_NUMBER(valor) as iva FROM mgpmclpe04.mclc_parametro WHERE cve_parametro = 2)," +
            "iva_permiso_itinerante_resumen = permiso_itinerante_resumen * (SELECT TO_NUMBER(valor) as iva FROM mgpmclpe04.mclc_parametro WHERE cve_parametro = 2)  " +
            "WHERE periodo = :periodo"
            , nativeQuery = true)
    Integer updateIvaPermisoItineranteAndIvaPermisoItineranteResumen(
            @Param("periodo") String periodo
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE mgpmclpe04.mclt_resumen_conciliacion SET  " +
            "neto_pagar = importe_pagados - porcentaje_costo_admin - iva_costo_admin - importe_fallecidos," +
            "neto_sin_descuento_fallecidos = total_retenciones - porcentaje_costo_admin_resumen - iva_costo_admin_resumen," +
            "pago_real = total_retenciones - porcentaje_costo_admin_resumen - iva_costo_admin_resumen - importe_fallecidos_resumen  " +
            "WHERE periodo = :periodo"
            , nativeQuery = true)
    Integer updateNetoPagarAndNetoSinDescuentoFallecidosAndPagoReal(
            @Param("periodo") String periodo
    );

    @Transactional
    @Modifying
    @Query(value = "DELETE mgpmclpe04.mclt_resumen_conciliacion " +
            "WHERE periodo = :periodo"
            , nativeQuery = true)
    Integer deleteResumenConciliacion(
            @Param("periodo") String periodo
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE mgpmclpe04.mclt_resumen_conciliacion SET " +
                    "  porcentaje_permiso_itinerante = 0," +
                    "  permiso_itinerante_resumen = 0," +
                    "  iva_permiso_itinerante = 0," +
                    "  iva_permiso_itinerante_resumen = 0  " +
                    "  WHERE   " +
                    "  periodo = :periodo",
                    nativeQuery = true
    )
    Integer porcentajeIvaPermisoItineranteCero(
            @Param("periodo") String periodo
    );

}
