import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Biblioteca {
    // banco de dados
    private List<Livro> acervo = new ArrayList<>();
    int anoAtual = LocalDate.now().getYear();

    public void adicionar(Livro livro) throws Exception {

        if (livro.getTitulo() == null || livro.getTitulo().isEmpty())
            throw new Exception("Não é permitido cadastrar livro sem título");

        if (livro.getAutor() == null || livro.getAutor().isEmpty())
            throw new Exception("Não é permitido cadastrar livro sem o nome do autor");

        if (livro.getAnoPublicacao() < 1400 || livro.getAnoPublicacao() > anoAtual)
            throw new Exception("Não é possível cadastrar um livro com essa data. Informe uma data válida");

        if (livro.getNumeroPaginas() <= 0)
            throw new Exception("Não é permitido cadastrar livros com número de páginas negativo ou zerado");

        for (Livro livroAcervo : acervo) {
            if (livroAcervo.getTitulo().equalsIgnoreCase(livro.getTitulo()))
                throw new Exception("Já existe livro cadastrado com este título");
        }
        acervo.add(livro);
    }

    public List<Livro> pesquisarPorTitulo(String titulo) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                livrosEncontrados.add(livro);
        }
        return livrosEncontrados;
    }

    public void removerPorTitulo(String titulo) {
        for (Livro livro : acervo) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                acervo.remove(livro);
                break;
            }
        }
    }

    public List<Livro> pesquisarTodos() {
        return this.acervo;
    }
}