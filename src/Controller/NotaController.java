package Controller;

import Model.Nota;
import Repository.NotaRepository;
import java.util.ArrayList;

public class NotaController {

    private NotaRepository repository;

    public NotaController(NotaRepository repository) {
        this.repository = repository;
    }

    public boolean cadastrarNota(
            String matriculaAluno,
            String codigoDisciplina) {

        if (matriculaAluno == null || matriculaAluno.trim().isEmpty()) {
            return false;
        }

        if (codigoDisciplina == null || codigoDisciplina.trim().isEmpty()) {
            return false;
        }

        Nota nota = new Nota(
                matriculaAluno,
                codigoDisciplina
        );

        return repository.adicionar(nota);
    }

    public ArrayList<Nota> listarNotas() {
        return repository.listar();
    }

    public Nota buscarNota(
            String matriculaAluno,
            String codigoDisciplina) {

        if (matriculaAluno == null || matriculaAluno.trim().isEmpty()) {
            return null;
        }

        if (codigoDisciplina == null || codigoDisciplina.trim().isEmpty()) {
            return null;
        }

        return repository.buscar(
                matriculaAluno,
                codigoDisciplina
        );
    }

    public ArrayList<Nota> buscarPorAluno(String matriculaAluno) {

        if (matriculaAluno == null || matriculaAluno.trim().isEmpty()) {
            return new ArrayList<>();
        }

        return repository.buscarPorAluno(matriculaAluno);
    }

    public ArrayList<Nota> buscarPorDisciplina(String codigoDisciplina) {

        if (codigoDisciplina == null || codigoDisciplina.trim().isEmpty()) {
            return new ArrayList<>();
        }

        return repository.buscarPorDisciplina(codigoDisciplina);
    }

    public boolean removerNota(
            String matriculaAluno,
            String codigoDisciplina) {

        if (matriculaAluno == null || matriculaAluno.trim().isEmpty()) {
            return false;
        }

        if (codigoDisciplina == null || codigoDisciplina.trim().isEmpty()) {
            return false;
        }

        return repository.remover(
                matriculaAluno,
                codigoDisciplina
        );
    }

    public boolean atualizarNota(
            String matriculaAluno,
            String codigoDisciplina,
            ArrayList<Double> notas) {

        if (matriculaAluno == null || matriculaAluno.trim().isEmpty()) {
            return false;
        }

        if (codigoDisciplina == null || codigoDisciplina.trim().isEmpty()) {
            return false;
        }

        Nota nota = new Nota(
                matriculaAluno,
                codigoDisciplina,
                notas
        );

        return repository.atualizar(nota);
    }

    public double calcularMedia(
            String matriculaAluno,
            String codigoDisciplina) {

        Nota nota = repository.buscar(
                matriculaAluno,
                codigoDisciplina
        );

        if (nota == null) {
            return 0.0;
        }

        return nota.calcularMedia();
    }

    public String obterSituacao(
            String matriculaAluno,
            String codigoDisciplina) {

        Nota nota = repository.buscar(
                matriculaAluno,
                codigoDisciplina
        );

        if (nota == null) {
            return "Registro não encontrado";
        }

        return nota.getSituacao();
    }

    public void carregarArquivo(){
        repository.carregarArquivo();
    }
}