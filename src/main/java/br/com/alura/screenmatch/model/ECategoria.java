package br.com.alura.screenmatch.model;

public enum ECategoria {
    ACAO("Action"),
     ROMANCE("Romance"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    CRIME("Crime");

    private String categoriaOmdb;
    private String categoriaPortugues;

    ECategoria( String categoriaOmdb){
        this.categoriaOmdb = categoriaOmdb;
    }

    public static ECategoria fromString (String text){
        for(ECategoria eCategoria : ECategoria.values()){
            if (eCategoria.categoriaOmdb.equalsIgnoreCase(text)){
                return eCategoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para esta série");
    }

    public static ECategoria fromPortugues(String text) {
        for (ECategoria categoria : ECategoria.values()) {
            if (categoria.categoriaPortugues.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
