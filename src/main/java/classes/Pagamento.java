package classes;

public class Pagamento {
    private int id;
    private String data;
    private double valor;

    public Pagamento(int id, String data, double valor){
        this.id = id;
        this.data = data;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id;}

    public String getData() {
        return data;
    }

    public void setData(String data) {this.data = data;}

    public double getValor() {
        return valor;
    }

    public void setEmail(double valor) {this.valor = valor;}
}
