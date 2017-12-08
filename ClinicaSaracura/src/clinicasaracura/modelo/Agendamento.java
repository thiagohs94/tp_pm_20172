/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.modelo;

public class Agendamento {
    /*
    Para os agendamentos sao considerados 30 dias e os seguintes horarios: 
    01 - 08:00 as 08:30  07 - 11:00 as 11:30  13 - 14:00 as 14:30
    02 - 08:30 as 09:00  08 - 11:30 as 12:00  14 - 14:30 as 15:00
    03 - 09:00 as 09:30  09 - 12:00 as 12:30  15 - 15:00 as 15:30
    04 - 09:30 as 10:00  10 - 12:30 as 13:00  16 - 15:30 as 16:00
    05 - 10:00 as 10:30  11 - 13:00 as 13:30  17 - 16:00 as 16:30
    06 - 10:30 as 11:00  12 - 13:30 as 14:00  18 - 16:30 as 17:00
    */    
    
    public static final int TIPO_CORTESIA = 1;
    public static final int TIPO_PARTICULAR = 2;
    public static final int TIPO_CONVENIO = 3;
    
    public static final int TIPO_PAGAMENTO_DINHEIRO = 1;
    public static final int TIPO_PAGAMENTO_CARTAO = 2;
    public static final int TIPO_PAGAMENTO_CHEQUE = 3;
    
    private int dia;
    private int horario;
    private Cliente cliente;
    private int tipo;

    public Agendamento(int dia, int horario, Cliente cliente, int tipo) {
        this.dia = dia;
        this.horario = horario;
        this.cliente = cliente;
        this.tipo = tipo;
    }
    
    public Agendamento(){
    
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getHorario() {
        return horario;
    }

    public void setHorario(int horario) {
        this.horario = horario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }   

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    public static String getTextoHorario(int horario){
        switch(horario){
            case 1: return "08:00 - 08:30";
            case 2: return "08:30 - 09:00";    
            case 3: return "09:00 - 09:30";    
            case 4: return "09:30 - 10:00";    
            case 5: return "10:00 - 10:30";    
            case 6: return "10:30 - 11:00";    
            case 7: return "11:00 - 11:30";
            case 8: return "11:30 - 12:00";
            case 9: return "12:00 - 12:30";
            case 10: return "12:30 - 13:00";
            case 11: return "13:00 - 13:30";
            case 12: return "13:30 - 14:00";
            case 13: return "14:00 - 14:30";
            case 14: return "14:30 - 15:00";
            case 15: return "15:00 - 15:30";
            case 16: return "15:30 - 16:00";
            case 17: return "16:00 - 16:30";
            case 18: return "16:30 - 17:00";
            default: return "";
        }
    }
    
    public static String getTextoTipo(int tipo){
        switch(tipo){
            case TIPO_CORTESIA: return "Cortesia";
            case TIPO_PARTICULAR: return "Particular";    
            case TIPO_CONVENIO: return "Convênio";    
            default: return "";
        }
    }
    
    public static String getTextoTipoPagamento(int tipo){
        switch(tipo){
            case TIPO_PAGAMENTO_DINHEIRO: return "Dinheiro";
            case TIPO_PAGAMENTO_CARTAO: return "Cartão";    
            case TIPO_PAGAMENTO_CHEQUE: return "Cheque ";    
            default: return "";
        }
    }
    
}
