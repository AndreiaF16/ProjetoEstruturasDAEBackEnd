package ejbs;


import entities.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

@Singleton(name = "ConfigEJB")
@Startup
public class ConfigBean {
    @EJB
    ClienteBean clienteBean;

    @EJB
    ProjetistaBean projetistaBean;

    @EJB
    ProjetoBean projetoBean;

    @EJB
    ProdutoBean produtoBean;

    @EJB
    VarianteBean varianteBean;

    @EJB
    SimulacaoBean simulacaoBean;

    @EJB
    FamiliaBean familiaBean;

    @EJB
    CombinacaoAcoesVerificacaoDeformacaoBean combinacaoAcoesVerificacaoDeformacaoBean;

    @EJB
    AplicacaoBean aplicacaoBean;

    @EJB
    SobrecargaCategoriaBean sobrecargaCategoriaBean;

    @EJB
    EstruturaBean estruturaBean;

    @PostConstruct
    public void populateBD() {
        try {
            Cliente cliente = clienteBean.create("Andreia","123", "email@gmail.com","Andreia1", 123456521, "Morada");
            Cliente cliente1 = clienteBean.create("Miguelaaaaaa", "123123123", "email123@gmail.com", "Miguel", 123456521, "Morada");
            Projetista projetista = projetistaBean.create("Projetista1","123", "p1@gmail.com","p1");


            Projeto projeto = projetoBean.create("Projeto Um", cliente.getId(),true);
            Projeto projeto2 = projetoBean.create("Projeto Dois", cliente1.getId(), true);

            //Criar Categorias já existentes no site online
            System.out.println("####### A criar defauls values...");
            Set<Familia> familias = new HashSet<>();
            familias.add(familiaBean.create("Omega"));
            familias.add(familiaBean.create("C"));
            familias.add(familiaBean.create("Z"));
            aplicacaoBean.create("Cobertura");
            aplicacaoBean.create("Fachada");
            aplicacaoBean.create("Geral");
            combinacaoAcoesVerificacaoDeformacaoBean.create("Característica");
            combinacaoAcoesVerificacaoDeformacaoBean.create("Frequente");
            combinacaoAcoesVerificacaoDeformacaoBean.create("Quase permanente");
            sobrecargaCategoriaBean.create("Categoria", 'x');
            sobrecargaCategoriaBean.create("Categoria A: zonas de habitação", 'A');
            sobrecargaCategoriaBean.create("Categoria B: zonas de escritório", 'B');
            sobrecargaCategoriaBean.create("Categoria C: zonas de reunião de pessoas", 'C');
            sobrecargaCategoriaBean.create("Categoria D: zonas comerciais", 'D');
            sobrecargaCategoriaBean.create("Categoria E: zonas de armazenamento", 'E');
            sobrecargaCategoriaBean.create("Categoria F: zonas de tráfego, peso dos veículos ≤ 30 kN", 'F');
            sobrecargaCategoriaBean.create("Categoria G: zonas de tráfego, 30 kN < peso dos veículos ≥ 160 kN", 'G');
            estruturaBean.create(projeto.getId(), familias, "Cobertura", 'A', "Inox", 1,1,1,1,1,1,1,true,1,1,true,1,false,1,"9.8e-8","Característica",1,1,1,1,1,1,1,1,1,1,1,1, 1);
            estruturaBean.create(projeto2.getId(), familias, "Cobertura", 'B', "Inox", 1,1,1,1,1,1,1,true,1,1,true,1,false,1,"9.8e-8","Característica",1,1,1,1,1,1,1,1,1,1,1,1, 1);
            Estrutura estrutura = estruturaBean.create(projeto.getId(), familias, "Cobertura", 'A', "Inox", 1, 1, 1, 1, 1, 1, 1, true, 1, 1, true, 1, false, 1, "9.8e-8", "Característica", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
            Estrutura estrutura1 = estruturaBean.create(projeto2.getId(), familias, "Cobertura", 'B', "Inox", 1, 1, 1, 1, 1, 1, 1, true, 1, 1, true, 1, false, 1, "9.8e-8", "Característica", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);

            projeto.addEstruturas(estrutura);
            estrutura.setProjeto(projeto);
            projeto2.addEstruturas(estrutura1);
            estrutura1.setProjeto(projeto2);

            projeto.addProjetistas(projetista);
            projetista.addProjeto(projeto);

            projeto2.addProjetistas(projetista);
            projetista.addProjeto(projeto2);
        }
        catch(Exception e){
        }


       /*
        >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

                                    CODIGO FORNECIDO PELO STOR

         >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        */
        System.out.println("####### A criar produtos...");
        produtoBean.create("Section C 220 BF");
        produtoBean.create("Section Z 220 BF");
        System.out.println("####### A criar variantes...");

        //PODE LER-SE OS VALORES DOS PRODUTOS/VARIANTES DE EXCELS OU CSVs (ver excels fornecidos)
        //Exemplo básico de adição de variantes "à mão"
        varianteBean.create(1, "Section C 220 BF", "C 120/50/21 x 1.5", 13846, 13846, 375, 220000);
        varianteBean.create(2, "Section C 220 BF", "C 120/60/13 x 2.0", 18738, 18738, 500, 220000);

        //PODE LER-SE OS VALORES mcr_p E mcr_n A PARTIR DE UM EXCEL OU CSV (ver excels fornecidos para os produtos Perfil C e Z, que têm os valores mcr)
        //Exemplo básico de adição de valores mcr "à mão"
        Variante variante1 = varianteBean.getVariante(1);
        variante1.addMcr_p(3.0, 243.2123113);
        variante1.addMcr_p(4.0, 145.238784);
        variante1.addMcr_p(5.0, 99.15039028);
        variante1.addMcr_p(6.0, 73.71351699);
        variante1.addMcr_p(7.0, 58.07716688);
        variante1.addMcr_p(8.0, 47.68885195);
        variante1.addMcr_p(9.0, 40.37070843);
        variante1.addMcr_p(10.0, 34.9747033);
        variante1.addMcr_p(11.0, 30.84866055);
        variante1.addMcr_p(12.0, 27.59984422);

        //Válido para variantes simétricas, em que os mcr_p são iguais aos mcr_n
        variante1.setMcr_n((LinkedHashMap<Double, Double>) variante1.getMcr_p().clone());

        Variante variante2 = varianteBean.getVariante(2);
        variante2.addMcr_p(3.0, 393.1408237);
        variante2.addMcr_p(4.0, 241.9157907);
        variante2.addMcr_p(5.0, 169.7815504);
        variante2.addMcr_p(6.0, 129.3561949);
        variante2.addMcr_p(7.0, 104.0782202);
        variante2.addMcr_p(8.0, 86.9803928);
        variante2.addMcr_p(9.0, 74.71876195);
        variante2.addMcr_p(10.0, 65.52224563);
        variante2.addMcr_p(11.0, 58.37786338);
        variante2.addMcr_p(12.0, 52.65428332);

        //Válido para variantes de geometria simétrica, em que os mcr_p são iguais aos mcr_n
        variante2.setMcr_n((LinkedHashMap<Double, Double>) variante2.getMcr_p().clone());


        System.out.println("####### FINISHED!!");

        //EXEMPLO DA SIMULAÇÃO PARA DUAS VARIANTES DO PERFIL C, E PARA UMA ESTRUTURA DE 3 vãos (nb) de 3m cada (LVao) E SOBRECARGA 500000 (q)
        if (simulacaoBean.simulaVariante(3, 3.0, 500000, variante1)) {
            System.out.println(variante1.getNome() + " pode ser usada.");
        } else {
            System.out.println(variante1.getNome() + " não pode ser usada.");
        }

        if (simulacaoBean.simulaVariante(3, 3.0, 500000, variante2)) {
            System.out.println("A variante " + variante2.getNome() + " pode ser usada.");
        } else {
            System.out.println("A variante " + variante2.getNome() + " não pode ser usada.");
        }


    }

}
