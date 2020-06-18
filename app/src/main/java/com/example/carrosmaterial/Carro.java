package com.example.carrosmaterial;

public class Carro {
    private String placa;
    private String marca;
    private String color;
    private int foto;
    private String id;

    public Carro (String placa, String marca, String color, int foto){
        this.placa = placa;
        this.marca = marca;
        this.color = color;
        this.foto = foto;
    }


    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Carro (String placa, String marca, String color, int foto, String id){
        this.placa = placa;
        this.marca = marca;
        this.color = color;
        this.foto = foto;
        this.id = id;
    }

    public void guardar(){
        Datos.guardar(this);
    }

    public void eliminar(){
        Datos.eliminar(this);
    }
}
