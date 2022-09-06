import com.dao.CidadeDAO;
import com.dao.ContatoDAO;
import com.dao.DestinoDAO;
import com.dao.PaisDAO;
import com.models.Cidade;
import com.models.Contato;
import com.models.Destino;
import com.models.Pais;

import java.util.Scanner;

public class Main {
    public static int contatoOpt = 1;
    public static int destinoOpt = 1;
    public static int paisOpt = 1;
    public static int cidadeOpt = 1;
    public static int opt = 0;
    public static Scanner in;

    public static void main(String[] args) {
        in = new Scanner(System.in);

        boolean sair = false;
        do {
            if (opt == 0) {
                System.out.println("=============== SISTEMA ===============");
                System.out.println("[1] -> Contatos");
                System.out.println("[2] -> Destinos");
                System.out.println("[3] -> Paises");
                System.out.println("[4] -> Cidades");
                System.out.println("[5] -> Sair");
                System.out.print("Opção: ");
                opt = in.nextInt();
            } else if (opt == 1){
                mostrarMenuContato();
            } else if (opt == 2) {
                mostrarMenuDestinos();
            } else if (opt == 3) {
                mostrarMenuPais();
            } else if (opt == 4) {
                mostrarMenuCidade();
            } else if(opt == 5) {
                sair = true;
            }
        } while(!sair);
    }

    public static void mostrarMenuContato() {
        ContatoDAO contatoDAO = new ContatoDAO();
        System.out.println("===================== Contatos ====================");
        System.out.println("------|--------------------|-----------------------");
        System.out.printf("%-5s %-20s %-10s\n", "|ID", "|Nome", "|Email");
        System.out.println("------|--------------------|-----------------------");
        for (Contato c: contatoDAO.all()) {
            System.out.printf("%-5s %-20s %-10s\n", "|"+c.getId(), "|"+c.getNome(), "|"+c.getEmail());
        }
        System.out.println("---------------------------------------------------");
        System.out.println();
        System.out.println();

        System.out.println("[1] -> Cadastrar");
        System.out.println("[2] -> Atualizar");
        System.out.println("[3] -> Deletar");
        System.out.println("[0] -> Voltar");

        System.out.print("Opção: ");
        contatoOpt = in.nextInt();

         if (contatoOpt == 1) {
            Contato contato = new Contato();

            String nome = "";
            String email = "";

            in.nextLine();
            while(nome.equals("") || email.equals("")){
                System.out.print("Digite o Nome: ");
                nome = in.nextLine();

                if (!nome.equals("")) {
                    System.out.print("Digite o Email: ");
                    email = in.nextLine();
                }
            }

            contato.setNome(nome);
            contato.setEmail(email);

            contatoDAO.create(contato);
            contatoOpt = 1;
        } else if (contatoOpt == 2) {
             System.out.print("Digite o ID do contato para edição: ");
             int id = in.nextInt();
             Contato contato = contatoDAO.show(id);
             System.out.print("[ID: "+contato.getId()+", Nome: "+contato.getNome()+", Email: "+contato.getEmail()+"]");
             System.out.println();
             String nome = "";

             System.out.print("Digite o Nome: ");
             while (nome.equals("")) {
                 nome = in.nextLine();
             }

             contato.setNome(nome);
             contatoDAO.update(contato);

             contatoOpt = 1;
        } else if (contatoOpt == 3) {
            int id = -1;

            while (id < 0) {
                System.out.println("Confirme antes o id, pois essa operação não pode ser desfeita");
                System.out.print("Digite o ID do contato(Para voltar só digitar 0): ");
                id = in.nextInt();
                if (id > 0) {
                    contatoDAO.remove(id);
                }
            }
            contatoOpt = 1;

        } else if (contatoOpt == 0) {
            opt = contatoOpt;
        }
    }

    public static void mostrarMenuPais() {
        PaisDAO paisDAO = new PaisDAO();
        System.out.println("=============== Paises ===============");
        System.out.println();
        System.out.printf("%-5s %-20s\n", "|ID", "|Nome");
        System.out.println("------|--------------------|-----------------------");
        for (Pais p: paisDAO.all()) {
            System.out.printf("%-5s %-20s\n", "|"+p.getId(), "|"+p.getNome());
        }
        System.out.println("-------------------------------------------------");
        System.out.println();
        System.out.println();

        System.out.println("[2] -> Cadastrar");
        System.out.println("[3] -> Atualizar");
        System.out.println("[4] -> Deletar");
        System.out.println("[5] -> Voltar");

        System.out.print("Opção: ");
        paisOpt = in.nextInt();

        if (paisOpt == 2) {
            Pais pais = new Pais();

            String nome = "";

            in.nextLine();
            while(nome.equals("")){
                System.out.print("Digite o Nome: ");
                nome = in.nextLine();
            }

            pais.setNome(nome);

            paisDAO.create(pais);
            paisOpt = 1;
        } else if (paisOpt == 3) {
            System.out.print("Digite o ID do pais para edição: ");
            int id = in.nextInt();
            Pais pais = paisDAO.show(id);
            System.out.print("[ID: "+pais.getId()+", Nome: "+pais.getNome()+" ]");
            System.out.println();
            String nome = "";

            System.out.print("Digite o Nome: ");
            while (nome.equals("")) {
                nome = in.nextLine();
            }

            pais.setNome(nome);
            paisDAO.update(pais);

            paisOpt = 1;
        }  else if (paisOpt == 4) {
            int id = -1;

            while (id < 0) {
                System.out.println("Confirme antes o id, pois essa operação não pode ser desfeita");
                System.out.print("Digite o ID do País (Para voltar só digitar 0): ");
                id = in.nextInt();

                if (Pais.verificarPossuiCidadesFilhas(id)) {
                    System.out.println("O país selecionado possui cidades relacionadas, você deve excluir as cidades primeiro");
                    id = 0;
                }

                if (id > 0) {
                    paisDAO.remove(id);
                }
            }
            paisOpt = 1;

        } else if (paisOpt == 5) {
            opt = 0;
        }
    }

    public static void mostrarMenuCidade() {
        CidadeDAO cidadeDAO = new CidadeDAO();
        System.out.println();
        System.out.println("=============== Cidades ===============");
        System.out.printf("%-5s %-20s %-10s\n", "|ID", "|Nome", "|Pais");
        System.out.println("------|--------------------|-----------------------");
        for (Cidade c: cidadeDAO.all()) {
            System.out.printf("%-5s %-20s %-10s\n", "|"+c.getId(), "|"+c.getNome(), "|"+c.getPais());
        }
        System.out.println("-------------------------------------------------");
        System.out.println();
        System.out.println();

        System.out.println("[2] -> Cadastrar");
        System.out.println("[3] -> Atualizar");
        System.out.println("[4] -> Deletar");
        System.out.println("[5] -> Voltar");

        System.out.print("Opção: ");
        cidadeOpt = in.nextInt();
        if (cidadeOpt == 2) {
            Cidade cidade = new Cidade();
            PaisDAO paisDAO = new PaisDAO();
            System.out.println("=============== Lista de Paises para relacionar ===============");
            System.out.printf("%-5s %-20s\n", "|ID", "|Nome");
            System.out.println("------|--------------------");
            for (Pais p: paisDAO.all()) {
                System.out.printf("%-5s %-20s\n", "|"+p.getId(), "|"+p.getNome());
            }
            System.out.println("----------------------------");
            System.out.println();

            String nome = "";
            int id_pais = 0;

            in.nextLine();
            while(nome.equals("") || id_pais == 0){
                System.out.print("Digite o nome da Cidade: ");
                nome = in.nextLine();

                if (!nome.equals("")) {
                    System.out.print("Digite o ID do País: ");
                    id_pais = in.nextInt();
                }
            }

            cidade.setNome(nome);
            cidade.setId_pais(id_pais);

            cidadeDAO.create(cidade);
            cidadeOpt = 1;
        } else if (cidadeOpt == 3) {
            System.out.print("Digite o ID da cidade para edição: ");
            int id = in.nextInt();
            Cidade cidade = cidadeDAO.show(id);
            System.out.print("[ID: "+cidade.getId()+", Nome: "+cidade.getNome()+", Pais: "+cidade.getPais()+" ]");
            System.out.println();
            String nome = "";

            System.out.print("Digite o nome da Cidade: ");
            while (nome.equals("")) {
                nome = in.nextLine();
            }

            cidade.setNome(nome);
            cidadeDAO.update(cidade);

            cidadeOpt = 1;
        } else if (cidadeOpt == 4) {
            int id = -1;

            while (id < 0) {
                System.out.println("Confirme antes o id, pois essa operação não pode ser desfeita");
                System.out.print("Digite o ID da Cidade (Para voltar só digitar 0): ");
                id = in.nextInt();

                if (Cidade.verificarCidadePossuiDestinos(id)) {
                    System.out.println("Essa cidade possui destinos relacionados, você deve excluir esses destinos antes");
                    id = 0;
                }

                if (id > 0) {
                    System.out.println("POSSUI");
                    cidadeDAO.remove(id);
                }
            }
            cidadeOpt = 1;

        } else if (cidadeOpt == 5) {
            opt = 0;
        }
    }

    public static void mostrarMenuDestinos() {
        DestinoDAO destinoDAO = new DestinoDAO();
        System.out.println();
        System.out.println("=============== Destinos ===============");
        System.out.printf("%-5s %-20s %-10s\n", "|ID", "|Cidade de Partida", "|Cidade de Destino");
        System.out.println("------|--------------------|-----------------------");
        for (Destino d: destinoDAO.all()) {
            System.out.printf("%-5s %-20s %-10s\n", "|"+d.getId(), "|"+d.getPartida(), "|"+d.getDestino());
        }
        System.out.println("-------------------------------------------------");
        System.out.println();
        System.out.println();

        System.out.println("[1] -> Cadastrar");
        System.out.println("[2] -> Deletar");
        System.out.println("[0] -> Voltar");

        System.out.print("Opção: ");
        destinoOpt = in.nextInt();
        if (destinoOpt == 1) {
            Destino destino = new Destino();
            CidadeDAO cidadeDAO = new CidadeDAO();
            System.out.println();
            System.out.println("=============== Cidades disponiveis ===============");
            System.out.printf("%-5s %-20s %-10s\n", "|ID", "|Nome", "|Pais");
            System.out.println("------|--------------------|-----------------------");
            for (Cidade c: cidadeDAO.all()) {
                System.out.printf("%-5s %-20s %-10s\n", "|"+c.getId(), "|"+c.getNome(), "|"+c.getPais());
            }
            System.out.println("-------------------------------------------------");
            System.out.println();

            int id_partida = 0;
            int id_destino = 0;

            in.nextLine();
            while(id_partida == 0 || id_destino == 0) {
                if (id_partida == 0) {
                    System.out.print("Digite o id da Cidade de partida: ");
                    id_partida = in.nextInt();
                    if (!Cidade.validarId(id_partida)) {
                        id_partida = 0;
                    }
                }

                if (id_partida > 0) {
                    System.out.print("Digite o id da Cidade de destino: ");
                    id_destino = in.nextInt();
                    if (!Cidade.validarId(id_destino)) {
                        id_destino = 0;
                    }
                }

                if (id_destino == 0 || id_partida == 0) {
                    System.out.println("Digite um id de cidade válido");
                }

                if ((id_destino != 0 && id_partida != 0) && id_destino == id_partida) {
                    id_destino = 0;
                    System.out.println("A cidade de partida nao pode ser igual a cidade de destino");
                }
            }

            destino.setCidade_partida_id(id_partida);
            destino.setCidade_destino_id(id_destino);

            destinoDAO.create(destino);
            destinoOpt = 1;
        } else if (destinoOpt == 2) {
            int id = -1;

            while (id < 0) {
                System.out.println("Confirme antes o id, pois essa operação não pode ser desfeita");
                System.out.print("Digite o ID do Destino (Para voltar só digitar 0): ");
                id = in.nextInt();
                if (id > 0) {
                    destinoDAO.remove(id);
                }
            }
            destinoOpt = 1;

        } else if (destinoOpt == 0) {
            opt = destinoOpt;
        }
    }
}
