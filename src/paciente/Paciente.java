package paciente;

public class Paciente {
    private int id;
    private int pressaoSistole;
    private int pressaoDiastole;
    private int frequenciaCardiaca;
    private int quantidadeAlteracoes;

    public Paciente(int id,int pressaoSistole,int pressaoDiastole,int frequenciaCardiaca,int quantidadeAlteracoes) {
        this.id = id;
        this.pressaoSistole = pressaoSistole;
        this.pressaoDiastole = pressaoDiastole;
        this.frequenciaCardiaca = frequenciaCardiaca;
        this.quantidadeAlteracoes = quantidadeAlteracoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPressaoSistole() {
        return pressaoSistole;
    }

    public void setPressaoSistole(int pressaoSistole) {
        this.pressaoSistole = pressaoSistole;
    }

    public int getPressaoDiastole() {
        return pressaoDiastole;
    }

    public void setPressaoDiastole(int pressaoDiastole) {
        this.pressaoDiastole = pressaoDiastole;
    }

    public int getFrequenciaCardiaca() {
        return frequenciaCardiaca;
    }

    public void setFrequenciaCardiaca(int frequenciaCardiaca) {
        this.frequenciaCardiaca = frequenciaCardiaca;
    }

    public int getQuantidadeAlteracoes() {
        return quantidadeAlteracoes;
    }

    public void setQuantidadeAlteracoes(int quantidadeAlteracoes) {
        this.quantidadeAlteracoes = quantidadeAlteracoes;
    }
}