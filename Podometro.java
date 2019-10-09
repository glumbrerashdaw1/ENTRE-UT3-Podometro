/**
 * La clase modela un sencillo pod�metro que registra informaci�n
 * acerca de los pasos, distancia, ..... que una persona (hombre o mujer)
 * ha dado en una semana. 
 * 
 * @Gaizka Lumbreras Huarte 
 * 
 */
public class Podometro {
    //Fijas
    private final char HOMBRE = 'H';
    private final char MUJER = 'M';
    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;
    private final int SABADO = 6;
    private final int DOMINGO = 7;

    //Variables
    private String marca;
    private double altura;
    private char sexo;
    private double longitudZancada;
    private int totalPasosLaborables;
    private int totalPasosSabado;
    private int totalPasosDomingo;
    private double totalDistanciaSemana;
    private double totalDistanciaFinSemana;
    private int tiempo;
    private int caminatasNoche;

    /**
     * Inicializa el pod�metro con la marca indicada por el par�metro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {
        marca = queMarca;
        altura = 0;
        sexo = MUJER;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }

    /**
     * accesor para la marca
     *  
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Simula la configuraci�n del pod�metro.
     * Recibe como par�metros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna adem�s el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {
        altura = queAltura;
        sexo = queSexo;
        if (sexo == MUJER) {
            longitudZancada = Math.floor(altura * ZANCADA_MUJER);
        }else{
            longitudZancada = Math.ceil(altura * ZANCADA_HOMBRE);
        }
    }

    /**
     *  Recibe cuatro par�metros que supondremos correctos:
     *    pasos - el n� de pasos caminados
     *    dia - n� de d�a de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - S�bado, 7 - Domingo)
     *    horaInicio � hora de inicio de la caminata
     *    horaFina � hora de fin de la caminata
     *    
     *    A partir de estos par�metros el m�todo debe realizar ciertos c�lculos
     *    y  actualizar� el pod�metro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,
    int horaFin) {
        String nombreDiaSemana = "";
        switch (dia) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: totalPasosLaborables += pasos;    
            break;
            case 6: totalPasosSabado += pasos;
            break;
            case 7: totalPasosDomingo += pasos;
            break;
        }
        totalDistanciaSemana += pasos * longitudZancada;

        if (horaInicio >= 2100){
            caminatasNoche++;
        }
    }

    /**
     * Muestra en pantalla la configuraci�n del pod�metro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        String queSexo;

        if (sexo == 'M'){
            queSexo = "Mujer";
        }else{
            queSexo = "Hombre";
        }

        System.out.println("Configuraci�n del pod�metro" +
            "\n*********************************" +
            "\nAltura: " + (altura / 100) + " mtos" + 
            "\nSexo: " + queSexo  + 
            "\nLongitud zancada: " + (longitudZancada / 100) + "mtos");
    }

    /**
     * Muestra en pantalla informaci�n acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstad�sticas() {
        String queDiaMasCaminado;
        if (totalPasosDomingo > totalPasosLaborables && totalPasosDomingo > totalPasosSabado) {
            queDiaMasCaminado = "DOMINGO";
        }else if (totalPasosSabado > totalPasosLaborables) {
            queDiaMasCaminado = "SABADO";
        }else {
            queDiaMasCaminado = "LABORABLES";
        }

        System.out.println("Estad�sticas" +
            "\n*********************************" +
            "\nDistancia recorrida toda la semana: " + totalDistanciaSemana +
            "\nDistancia recorrida fin de semana: " + totalDistanciaFinSemana +
            "\n" +
            "\nN� pasos d�as laborables: " + totalPasosLaborables +
            "\nN� pasos S�BADO: " + totalPasosSabado +
            "\nN� pasos DOMINGO: " + totalPasosDomingo +
            "\n" +
            "\nN� caminatas realizadas a partir de las 21h.: " + caminatasNoche +
            "\n" +
            "\nTiempo total caminado en la semana: " + (tiempo / 60) + "h. y " +
            (tiempo % 60) + "m." +
            "\nD�a/s con m�s pasos caminados: " + queDiaMasCaminado);
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del d�a
     *  en el que se ha caminado m�s pasos - "S�BADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        String queDiaMasCaminado;
        if (totalPasosDomingo > totalPasosLaborables && totalPasosDomingo > totalPasosSabado) {
            queDiaMasCaminado = "DOMINGO";
        }else if (totalPasosSabado > totalPasosLaborables) {
            queDiaMasCaminado = "SABADO";
        }else {
            queDiaMasCaminado = "LABORABLES";
        }
        return queDiaMasCaminado;
    }

    /**
     * Restablecer los valores iniciales del pod�metro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no var�a
     *  
     */    
    public void reset() {
        marca = "";
        altura = 0;
        sexo = MUJER;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }
}
