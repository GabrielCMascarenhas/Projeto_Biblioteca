import java.util.Comparator;
import java.util.Scanner;
import java.util.List;


public class Main {
    static Biblioteca biblioteca = new Biblioteca();
    static Scanner input = new Scanner(System.in);

    public static void clearTerminal() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    private static int inputNumerico(String mensagem) {
        boolean entradaValida = false;
        int valor = 0;
        System.out.println(mensagem);
    
        do {
            System.out.print("Resposta: ");
            String valorStr = input.nextLine();
            try { // Tenta converter a entrada para inteiro
                valor = Integer.parseInt(valorStr);
                entradaValida = true;  // Se conseguir, a entrada é válida
            } catch (NumberFormatException e) { // Se não, mensagem de erro
                System.out.println("Erro. Por favor, informe um número inteiro válido.");
            }
        } while (!entradaValida);  // Repete até conseguir
    
        return valor; 
    }
    

    // private static int inputNumerico2(String mensagem) {
    //     boolean entradaValida = false;
    //     int valor = 0;
    //     System.out.println(mensagem);

    //     do {
    //         String valorStr = input.nextLine();
    //         try {
    //             valor = Integer.parseInt(valorStr);
    //             entradaValida = true;
    //         } catch (NumberFormatException e) {
    //             System.out.println("Erro. Por favor informe um número inteiro");
    //         }
    //     } while (!entradaValida);
    //     return valor;
    // }

    private static void listar() {
        // List<Livro> livros = biblioteca.pesquisarTodos();
        var livros = biblioteca.pesquisarTodos();
        livros.sort(Comparator.comparing(Livro::getTitulo));
        System.out.println("============ LISTA DE LIVROS ===========");
        for (Livro livro : livros) {
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Ano: " + livro.getAnoPublicacao());
            System.out.println("N. Páginas: " + livro.getNumeroPaginas());
            System.out.println();
        }
    }

    private static void adicionar() {
        Livro novoLivro = new Livro();
        System.out.println("===========ADICIONANDO NOVO LIVRO=============");
        System.out.print("Informe o título do livro: ");
        String titulo = input.nextLine(); // essas suas
        novoLivro.setTitulo(titulo);

        System.out.print("Informe o nome do autor: "); // são = as essas
        novoLivro.setAutor(input.nextLine());

        novoLivro.setAnoPublicacao(inputNumerico("Informe o ano de publicação: "));

        novoLivro.setNumeroPaginas(inputNumerico("Informe o número de páginas: "));

        try {
            biblioteca.adicionar(novoLivro);
            System.out.println("Livro adicionado com Sucesso");
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        input.nextLine(); // espera o usuário dar um enter para continuar
    }

    private static void pesquisarPorTitulo() {
        System.out.print("Informe o título do livro a ser pesquisado: ");
        String titulo = input.nextLine();
        List<Livro> livrosEncontrados = biblioteca.pesquisarPorTitulo(titulo);

        if (livrosEncontrados.isEmpty()) {
            System.out.println("Nenhum livro encontrado com o título informado.");
        } else {
            System.out.println("LIVROS ENCONTRADOS:\n");
            for (Livro livro : livrosEncontrados) {
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Ano: " + livro.getAnoPublicacao());
                System.out.println("N. Páginas: " + livro.getNumeroPaginas());
                System.out.println();
            }
        }
    }

    private static void removerPorTitulo() {
        System.out.print("Informe o título do livro a ser removido: ");
        String titulo = input.nextLine();
        biblioteca.removerPorTitulo(titulo);
        System.out.println("Livro removido com sucesso (se existia).\n");
    }

    public static void main(String[] args) {
        clearTerminal();
        String menu = """
                SISTEMA DE GERENCIAMENTO DE BIBLIOTECA
                Escolha um das opções
                1- Adicionar novo livro
                2- Listar todos os livros
                3- Pesquisar livro
                4- Remover livro
                0- Sair
                """;
        int opcao;
        do {
            // System.out.println(menu);
            // opcao = input.nextInt();
            // input.nextLine();
            opcao = inputNumerico(menu);

            switch (opcao) {
                case 0:
                    clearTerminal();
                    System.out.println("Volte Sempre");
                    break;
                case 1:
                    clearTerminal();
                    adicionar();
                    break;
                case 2:
                    clearTerminal();
                    listar();
                    break;
                case 3:
                    clearTerminal();
                    pesquisarPorTitulo();
                    break;
                case 4:
                    clearTerminal();
                    removerPorTitulo();
                    break;
                default:
                    System.out.println("Opção Inválida");
                    input.nextLine();
                    break;
            }
        } while (opcao != 0);

        // Livro novoLivro = new Livro();
        // novoLivro.setTitulo ("O Senhor dos Anéis");
        // novoLivro.setAutor("J.R.R Tolkien");
        // novoLivro.setAnoPublicacao(2010);
        // novoLivro.setNumeroPaginas(340);

        // biblioteca.adicionar(novoLivro);
        // System.out.println(biblioteca);
    }
}