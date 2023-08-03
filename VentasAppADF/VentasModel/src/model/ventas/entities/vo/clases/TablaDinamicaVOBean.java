package model.ventas.entities.vo.clases;

public class TablaDinamicaVOBean {
    
    private Integer idTabla;
    private String componente;
    private Integer idTareaBpm;
    private Integer idProducto;
    private String accion;
    private Integer tablaVerdad[][];


    public TablaDinamicaVOBean(Integer idTabla, String componente, Integer idTareaBpm, Integer idProducto, String accion, Integer [][] tablaVerdad) {
        super();
        this.idTabla = idTabla;
        this.componente = componente;
        this.idTareaBpm = idTareaBpm;
        this.idProducto = idProducto;
        this.accion = accion;
        this.tablaVerdad = tablaVerdad;
    }


    public void setIdTabla(Integer idTabla) {
        this.idTabla = idTabla;
    }

    public Integer getIdTabla() {
        return idTabla;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public String getComponente() {
        return componente;
    }

    public void setIdTareaBpm(Integer idTareaBpm) {
        this.idTareaBpm = idTareaBpm;
    }

    public Integer getIdTareaBpm() {
        return idTareaBpm;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getAccion() {
        return accion;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setTablaVerdad(Integer[][] tablaVerdad) {
        this.tablaVerdad = tablaVerdad;
    }

    public Integer[][] getTablaVerdad() {
        return tablaVerdad;
    }
}
