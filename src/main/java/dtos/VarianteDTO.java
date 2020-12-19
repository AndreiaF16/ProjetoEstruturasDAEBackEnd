package dtos;

import entities.Produto;

import javax.validation.constraints.NotNull;

public class VarianteDTO {
    private int codigo;

    private ProdutoDTO produto;

    private String nome;

    private double weff_p; //modolo elástico positivo
    private double weff_n; //modulo elástico negativo
    private double ar; // área
    private double sigmaC; //tensão de cedência
    private double pp; // peso próprio

    public VarianteDTO(int codigo, ProdutoDTO produtoDTO, String nome, double weff_p, double weff_n, double ar, double sigmaC, double pp) {
        this.codigo = codigo;
        this.produto = produtoDTO;
        this.nome = nome;
        this.weff_p = weff_p;
        this.weff_n = weff_n;
        this.ar = ar;
        this.sigmaC = sigmaC;
        this.pp = pp;
    }

    public VarianteDTO() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public ProdutoDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDTO produtoDTO) {
        this.produto = produtoDTO;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getWeff_p() {
        return weff_p;
    }

    public void setWeff_p(double weff_p) {
        this.weff_p = weff_p;
    }

    public double getWeff_n() {
        return weff_n;
    }

    public void setWeff_n(double weff_n) {
        this.weff_n = weff_n;
    }

    public double getAr() {
        return ar;
    }

    public void setAr(double ar) {
        this.ar = ar;
    }

    public double getSigmaC() {
        return sigmaC;
    }

    public void setSigmaC(double sigmaC) {
        this.sigmaC = sigmaC;
    }

    public double getPp() {
        return pp;
    }

    public void setPp(double pp) {
        this.pp = pp;
    }
}
