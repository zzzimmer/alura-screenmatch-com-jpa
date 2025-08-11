package br.com.alura.screenmatch.model;

public enum ECategoria {
    ACAO("Action", "Ação"),
     ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comédia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime")
//    ANIMACAO("Animation", "Animação")
;

    private String categoriaOmdb;
    private String categoriaPortugues;

    ECategoria( String categoriaOmdb, String categoriaPortugues){
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaPortugues = categoriaPortugues;
    }

    public static ECategoria fromString (String text){
        for(ECategoria eCategoria : ECategoria.values()){
            if (eCategoria.categoriaOmdb.equalsIgnoreCase(text)){
                return eCategoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para esta série");
    }

    public static ECategoria fromPortugues( String text){
        for(ECategoria eCategoria : ECategoria.values()){
            if (eCategoria.categoriaPortugues.equalsIgnoreCase(text)){
                return eCategoria;
            }
        }
        throw new IllegalArgumentException("Categoria não encontrada");
    }
}
