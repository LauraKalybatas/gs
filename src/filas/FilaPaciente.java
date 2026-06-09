package filas;

import paciente.Paciente;

public class FilaPaciente {

    private class NO {
        Paciente dado;
        NO prox;
    }

    private NO ini, fim;
    private int quantidade;

    public void init() {
        ini = fim = null;
        quantidade = 0;
    }

    public boolean isEmpty() {
        return (ini == null && fim == null);
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void enqueue(Paciente p) {
        NO novo = new NO();
        novo.dado = p;
        novo.prox = null;
        if (isEmpty()) {
            ini = novo;
        } else {
            fim.prox = novo;
        }
        fim = novo;
        quantidade++;
    }

    public Paciente dequeue() {
        Paciente p = ini.dado;
        ini = ini.prox;
        if (ini == null) {
            fim = null;
        }
        quantidade--;
        return p;
    }

    public Paciente first() {
        return ini.dado;
    }
}