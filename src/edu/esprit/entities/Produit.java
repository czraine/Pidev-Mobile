/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author Hedi
 */
public class Produit {
    private int idProduit;
    private String nameProd;
    private String prodDescription;
    private String typeProd;
    private float priceProd;
    private int quantite;
    private String imageProd;
    private String status;

    public Produit() {
    }

    public Produit(int idProduit, String nameProd, String prodDescription, String typeProd, float priceProd, int quantite, String imageProd, String status) {
        this.idProduit = idProduit;
        this.nameProd = nameProd;
        this.prodDescription = prodDescription;
        this.typeProd = typeProd;
        this.priceProd = priceProd;
        this.quantite = quantite;
        this.imageProd = imageProd;
        this.status = status;
    }

    public Produit(String nameProd, String prodDescription, String typeProd, float priceProd, int quantite, String imageProd, String status) {
        this.nameProd = nameProd;
        this.prodDescription = prodDescription;
        this.typeProd = typeProd;
        this.priceProd = priceProd;
        this.quantite = quantite;
        this.imageProd = imageProd;
        this.status = status;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNameProd() {
        return nameProd;
    }

    public void setNameProd(String nameProd) {
        this.nameProd = nameProd;
    }

    public String getProdDescription() {
        return prodDescription;
    }

    public void setProdDescription(String prodDescription) {
        this.prodDescription = prodDescription;
    }

    public String getTypeProd() {
        return typeProd;
    }

    public void setTypeProd(String typeProd) {
        this.typeProd = typeProd;
    }

    public float getPriceProd() {
        return priceProd;
    }

    public void setPriceProd(float priceProd) {
        this.priceProd = priceProd;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getImageProd() {
        return imageProd;
    }

    public void setImageProd(String imageProd) {
        this.imageProd = imageProd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Produit{" + "nameProd=" + nameProd + ", prodDescription=" + prodDescription + ", typeProd=" + typeProd + ", priceProd=" + priceProd + ", quantite=" + quantite + ", imageProd=" + imageProd + ", status=" + status + '}';
    }
    
}
