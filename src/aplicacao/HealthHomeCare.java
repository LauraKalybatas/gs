package aplicacao;

import filas.FilaAtencao;
import filas.FilaMonitora;
import paciente.Paciente;
import java.util.Scanner;

public class HealthHomeCare {

    static Scanner le = new Scanner(System.in);

    static boolean sinaisAlterados(Paciente p) {
        return (p.getPressaoSistole() > 14 || p.getPressaoDiastole() > 10 || p.getFrequenciaCardiaca() > 110 || p.getFrequenciaCardiaca() < 60);
    }

    static void lerMedidas(Paciente p) {
        System.out.print("Sistole (mmHg): ");
        p.setPressaoSistole(le.nextInt());

        System.out.print("Diastole (mmHg): ");
        p.setPressaoDiastole(le.nextInt());

        System.out.print("Frequencia cardiaca (bpm): ");
        p.setFrequenciaCardiaca(le.nextInt());
    }

    static void inserirPaciente(FilaMonitora filaMonitora) {
        System.out.print("Codigo do paciente: ");
        int id = le.nextInt();
        Paciente p = new Paciente(id, 0, 0, 0, 0);
        filaMonitora.enqueue(p);
        System.out.println("Paciente " + id + " inserido na fila de monitoramento.");
    }

    static void percorrerFilaMonitoramento(FilaMonitora filaMonitora, FilaAtencao filaAtencao) {
        if (filaMonitora.isEmpty()) {
            System.out.println("Fila de monitoramento vazia.");
            return;
        }
        int total = filaMonitora.getQuantidade();
        for (int i = 0; i < total; i++) {
            Paciente p = filaMonitora.dequeue();
            System.out.println("Paciente ID: " + p.getId());
            lerMedidas(p);
            if (sinaisAlterados(p)) {
                p.setQuantidadeAlteracoes(p.getQuantidadeAlteracoes() + 1);
                System.out.println("Paciente " + p.getId() + " enviado para fila de atencao especial.");
                filaAtencao.enqueue(p);
            } else {
                filaMonitora.enqueue(p);
            }
        }
    }

    static void percorrerFilaAtencao(FilaMonitora filaMonitora, FilaAtencao filaAtencao) {
        if (filaAtencao.isEmpty()) {
            System.out.println("Fila de atencao especial vazia.");
            return;
        }
        int total = filaAtencao.getQuantidade();
        for (int i = 0; i < total; i++) {
            Paciente p = filaAtencao.dequeue();
            System.out.println("Paciente ID: " + p.getId());
            lerMedidas(p);
            if (sinaisAlterados(p)) {
                if (p.getQuantidadeAlteracoes() >= 1) {
                    System.out.println("Paciente " + p.getId() + " encaminhado para teleconsulta.");
                    System.out.println("1 - Retornar para fila de monitoramento");
                    System.out.println("2 - Retornar para o hospital");
                    System.out.print("Decisão do medico: ");
                    int decisao = le.nextInt();
                    if (decisao == 1) {
                        p.setQuantidadeAlteracoes(0);
                        filaMonitora.enqueue(p);
                        System.out.println("Paciente " + p.getId() + " retornou para fila de monitoramento.");
                    } else {
                        System.out.println("Paciente " + p.getId() + " retornou ao hospital.");
                    }
                } else {
                    p.setQuantidadeAlteracoes(p.getQuantidadeAlteracoes() + 1);
                    filaAtencao.enqueue(p);
                    System.out.println("Paciente " + p.getId() + " permanece na fila de atencao.");
                }
            } else {
                if (p.getQuantidadeAlteracoes() >= 1) {
                    p.setQuantidadeAlteracoes(0);
                    filaAtencao.enqueue(p);
                    System.out.println("Paciente " + p.getId() + " aguarda mais uma medição na fila de atenção.");
                } else {
                    filaMonitora.enqueue(p);
                    System.out.println("Paciente " + p.getId() + " voltou para fila de monitoramento.");
                }
            }
        }
    }

    static void retirarPaciente(FilaMonitora filaMonitora) {
        if (filaMonitora.isEmpty()) {
            System.out.println("Fila de monitoramento vazia.");
            return;
        }
        System.out.print("Código do paciente a receber alta: ");
        int id = le.nextInt();
        int total = filaMonitora.getQuantidade();
        boolean encontrado = false;
        for (int i = 0; i < total; i++) {
            Paciente p = filaMonitora.dequeue();
            if (p.getId() == id && !encontrado) {
                encontrado = true;
                System.out.println("Paciente " + id + " recebeu alta.");
            } else {
                filaMonitora.enqueue(p);
            }
        }
        if (!encontrado) {
            System.out.println("Paciente " + id + " nao encontrado.");
        }
    }

    static void encerrarSistema(FilaMonitora filaMonitora, FilaAtencao filaAtencao) {
        System.out.println("Pacientes na fila de monitoramento:");
        if (filaMonitora.isEmpty()) {
            System.out.println("(vazia)");
        }
        while (!filaMonitora.isEmpty()) {
            Paciente p = filaMonitora.dequeue();
            System.out.println("ID: " + p.getId());
        }
        System.out.println("Pacientes na fila de atenção especial:");
        if (filaAtencao.isEmpty()) {
            System.out.println("(vazia)");
        }
        while (!filaAtencao.isEmpty()) {
            Paciente p = filaAtencao.dequeue();
            System.out.println("ID: " + p.getId());
        }
    }

    public static void main(String[] args) {
        FilaMonitora filaMonitora = new FilaMonitora();
        FilaAtencao filaAtencao = new FilaAtencao();
        filaMonitora.init();
        filaAtencao.init();
        int opcao = 0;
        while(opcao != 5) {
            System.out.println("\nHealthHomeCare");
            System.out.println("1 - Inserir paciente na fila de monitoramento");
            System.out.println("2 - Percorrer fila de monitoramento");
            System.out.println("3 - Percorrer fila de atenção especial");
            System.out.println("4 - Retirar paciente da fila de monitoramento (alta)");
            System.out.println("5 - Encerrar sistema");
            System.out.print("Opção: ");
            opcao = le.nextInt();
            switch (opcao) {
                case 1:
                    inserirPaciente(filaMonitora);
                    break;
                case 2:
                    percorrerFilaMonitoramento(filaMonitora, filaAtencao);
                    break;
                case 3:
                    percorrerFilaAtencao(filaMonitora, filaAtencao);
                    break;
                case 4:
                    retirarPaciente(filaMonitora);
                    break;
                case 5:
                    encerrarSistema(filaMonitora, filaAtencao);
                    break;
                default:
                    System.out.println("Opção invalida.");
            }
        }
    }
}
