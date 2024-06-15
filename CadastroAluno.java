import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Aluno {
    private int matricula;
    private String nome;
    private String curso;
    private Map<String, Double> desempenho; 
    private String preferenciaEstudo;

    public Aluno(int matricula, String nome, String curso) {
        this.matricula = matricula;
        this.nome = nome;
        this.curso = curso;
        this.desempenho = new HashMap<>();
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Map<String, Double> getDesempenho() {
        return desempenho;
    }

    public void setDesempenho(Map<String, Double> desempenho) {
        this.desempenho = desempenho;
    }

    public String getPreferenciaEstudo() {
        return preferenciaEstudo;
    }

    public void setPreferenciaEstudo(String preferenciaEstudo) {
        this.preferenciaEstudo = preferenciaEstudo;
    }

    public void adicionarDesempenho(String materia, double nota) {
        desempenho.put(materia, nota);
    }
}

public class CadastroAluno {
    private static ArrayList<Aluno> alunos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    listarAlunos();
                    break;
                case 3:
                    buscarAlunoPorMatricula();
                    break;
                case 4:
                    adicionarDesempenho();
                    break;
                case 5:
                    analisarDesempenho();
                    break;
                case 6:
                    proporPlanoEstudo();
                    break;
                case 7:
                    sair();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 7);
    }

    private static void exibirMenu() {
        System.out.println("\nMenu Sistema de Suporte a Decisões Educacionais:");
        System.out.println("1. Cadastrar Aluno");
        System.out.println("2. Listar Alunos");
        System.out.println("3. Buscar Aluno por Matrícula");
        System.out.println("4. Adicionar Desempenho Acadêmico");
        System.out.println("5. Analisar Desempenho Acadêmico");
        System.out.println("6. Propor Plano de Estudo");
        System.out.println("7. Sair");
        System.out.print("Digite a opção desejada: ");
    }

    private static void cadastrarAluno() {
        System.out.print("Digite a matrícula: ");
        int matricula = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o curso: ");
        String curso = scanner.nextLine();

        Aluno novoAluno = new Aluno(matricula, nome, curso);
        alunos.add(novoAluno);
        System.out.println("Aluno cadastrado com sucesso!");
    }

    private static void listarAlunos() {
        if (alunos.isEmpty()) {
            System.out.println("Não há alunos cadastrados.");
            return;
        }

        System.out.println("\nLista de Alunos:");
        for (Aluno aluno : alunos) {
            System.out.println("Matrícula: " + aluno.getMatricula());
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Curso: " + aluno.getCurso());
            System.out.println("------------------");
        }
    }

    private static void buscarAlunoPorMatricula() {
        System.out.print("Digite a matrícula do aluno: ");
        int matricula = scanner.nextInt();
        scanner.nextLine(); 

        Aluno alunoEncontrado = null;
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula() == matricula) {
                alunoEncontrado = aluno;
                break;
            }
        }

        if (alunoEncontrado != null) {
            System.out.println("\nAluno encontrado:");
            System.out.println("Matrícula: " + alunoEncontrado.getMatricula());
            System.out.println("Nome: " + alunoEncontrado.getNome());
            System.out.println("Curso: " + alunoEncontrado.getCurso());
        } else {
            System.out.println("Aluno com a matrícula informada não encontrado.");
        }
    }

    private static void adicionarDesempenho() {
        System.out.print("Digite a matrícula do aluno: ");
        int matricula = scanner.nextInt();
        scanner.nextLine();

        Aluno aluno = buscarAluno(matricula);
        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        System.out.print("Digite a matéria: ");
        String materia = scanner.nextLine();

        System.out.print("Digite a nota: ");
        double nota = scanner.nextDouble();
        scanner.nextLine(); 

        aluno.adicionarDesempenho(materia, nota);
        System.out.println("Desempenho adicionado com sucesso!");
    }

    private static void analisarDesempenho() {
        System.out.print("Digite a matrícula do aluno: ");
        int matricula = scanner.nextInt();
        scanner.nextLine(); 

        Aluno aluno = buscarAluno(matricula);
        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        Map<String, Double> desempenho = aluno.getDesempenho();
        if (desempenho.isEmpty()) {
            System.out.println("Não há dados de desempenho disponíveis.");
            return;
        }

        System.out.println("\nAnálise de Desempenho:");
        for (Map.Entry<String, Double> entry : desempenho.entrySet()) {
            System.out.println("Matéria: " + entry.getKey() + " - Nota: " + entry.getValue());
        }
    }

    private static void proporPlanoEstudo() {
        System.out.print("Digite a matrícula do aluno: ");
        int matricula = scanner.nextInt();
        scanner.nextLine(); 

        Aluno aluno = buscarAluno(matricula);
        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        System.out.print("Digite a preferência de estudo (visual, auditivo, etc.): ");
        String preferencia = scanner.nextLine();
        aluno.setPreferenciaEstudo(preferencia);

        Map<String, Double> desempenho = aluno.getDesempenho();
        if (desempenho.isEmpty()) {
            System.out.println("Não há dados de desempenho disponíveis.");
            return;
        }

        System.out.println("\nProposta de Plano de Estudo:");
        for (Map.Entry<String, Double> entry : desempenho.entrySet()) {
            if (entry.getValue() < 60) {
                System.out.println("Matéria: " + entry.getKey() + " - Nota baixa (" + entry.getValue() + ")");
                System.out.println("Recomendações: Revisar conteúdo, realizar exercícios práticos, assistir videoaulas.");
            } else {
                System.out.println("Matéria: " + entry.getKey() + " - Nota boa (" + entry.getValue() + ")");
                System.out.println("Recomendações: Continuar com a abordagem atual.");
            }
        }
    }

    private static Aluno buscarAluno(int matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula() == matricula) {
                return aluno;
            }
        }
        return null;
    }

    private static void sair() {
        System.out.println("Saindo do programa...");
    }
}
