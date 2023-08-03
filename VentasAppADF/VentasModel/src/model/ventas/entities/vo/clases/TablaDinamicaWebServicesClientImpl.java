package model.ventas.entities.vo.clases;



import java.math.BigInteger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.example.GetDataTableRqType;
import org.example.GetDataTableRsType;
import org.example.TcaTareaConBtnDin;
import com.oracle.sca.soapservice.cursosoa_soa.clase001.getdatatabledinamic.ExecutePtt;
import com.oracle.sca.soapservice.cursosoa_soa.clase001.getdatatabledinamic.Services;
import com.oracle.sca.soapservice.cursosoa_soa.clase001.getdatatabledinamic.portNameClient;

public class TablaDinamicaWebServicesClientImpl {
    
    public static final String MENSAJE_WS ="Error al invocar el WebServices ::: ConsultarConfigCondicionesFinancieras :::";
    
    
    public TablaDinamicaWebServicesClientImpl() {
        super();
    }

    @SuppressWarnings("oracle.jdeveloper.java.semantic-warning")
    public String invocaJar(String s, int i){
        portNameClient invoke = new portNameClient();
        //invoke.implementa(s, i);
        
     return "Invocación Interna Jar";   
    }
    
    
    public Map<String,TablaDinamicaVOBean> invocarWebServcices(String nombreTabla, Integer idTareaBpm ){
        Services ws_services;
        ExecutePtt executePtt;
        GetDataTableRqType ws_request;
        GetDataTableRsType ws_response;
        Map<String,TablaDinamicaVOBean> objMapas;
        
        try{
            System.out.println("========================================");
            System.out.println("-- PREPARACION DE WEB SERCVICES ");
            ws_services = new Services();
            System.out.println("-- PREPARACION DE new Services() ");
            executePtt = ws_services.getPortName();
            System.out.println("-- PREPARACION DE ws_services.getPortName() ");
            ws_request = new GetDataTableRqType();
            System.out.println("-- PREPARACION DE new GetDataTableRqType() ");
            ws_request.setNameTabla(nombreTabla);
            System.out.println("-- PREPARACION DE ws_request.setNameTabla(nombreTabla) ");
            ws_request.setIdTarea(BigInteger.valueOf(idTareaBpm));
            System.out.println("-- PREPARACION DE ws_request.setIdTarea(BigInteger.valueOf(idTareaBpm) ");
            
            
            ws_response = executePtt.execute(ws_request);
            objMapas = desempaquetarColecion(ws_response);
            System.out.println("==========================================");
        }catch(Exception ex){
            System.out.println("Excepción " + ex);
            objMapas= new HashMap <String,TablaDinamicaVOBean>();
            objMapas.put(MENSAJE_WS , null);
            return objMapas;
        }        
        
    
    return objMapas;
    }
    
    public Map<String,TablaDinamicaVOBean> desempaquetarColecion(GetDataTableRsType ws_response){
        int contador;
        Map<String,TablaDinamicaVOBean> mapa = new HashMap <String,TablaDinamicaVOBean>();
        List<TcaTareaConBtnDin> listaRegistros;
        Iterator<TcaTareaConBtnDin> iterador;
        TcaTareaConBtnDin bean;
        TablaDinamicaVOBean beanLocal;
        
        listaRegistros = ws_response.getDataTable();
        iterador = listaRegistros.iterator();
        
        System.out.println("=================================================================================");
        System.out.println(Arrays.asList(listaRegistros));
        System.out.println("=================================================================================");
        
        contador = 1;
        Integer [][] tablaVerdad = new Integer[2][2];
        
        
        while(iterador.hasNext()){
                // Generamos un Objeto contenedor del fila de la tabla
                bean = new TcaTareaConBtnDin();
            //Se obtiene el Objeto fila que se esta iterando en el ciclo ( 1 de n registros )
                bean = iterador.next();
                //Se manda a imprimr el Objeto para su proceado individual
            /* Tabla [posición del registro PRODUCTO_CONFIGURADO ] [ posición del registro RETORNO ] = Asigna La columna VISIBLE  */
            System.out.println("INDICE TABLA = " + bean.getId().toString() + " FILA_PRO_CONFIG[" + bean.getProductoConfigurado() + "], " +" COLUMNA_RETOR[" + bean.getRetorno()+ "] =======> " + bean.getVisible());
            
            tablaVerdad[bean.getProductoConfigurado()][bean.getRetorno()] =  bean.getVisible();
            if( contador == 4 ){
                contador = 1;
                beanLocal = new TablaDinamicaVOBean( bean.getId().intValue(),
                                                     bean.getComponente(),
                                                     bean.getIdTareaBpm().intValue(),
                                                     bean.getIdPrpoducto().intValue(),
                                                     bean.getAccion(),
                                                     tablaVerdad);
                mapa.put(bean.getComponente().toString(), beanLocal);
                imprimirTablaVerdad(tablaVerdad);
            }else{
                contador+=1;
            }
            
        }
        
        
        return mapa;
    }
    
    public void imprimirTablaVerdad(Integer[][] t){
        
        for (int x= 0; x <2; x++) {
            for (int y=0; y<2; y++) {
                System.out.println("X[" + x +"]" + "Y[" + y + "]" + " = " + t[x][y] );    
           }
       }
        
        }
    
}
