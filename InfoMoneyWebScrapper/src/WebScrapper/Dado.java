package WebScrapper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dado {
    private String url;
    private String titulo;
    private String subtitulo;
    private String autor;
    private Date data;
    private String conteudo;

    public Dado(String url, String titulo, String subtitulo, String autor, Date data, String conteudo) {
        this.url = url;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.autor = autor;
        this.data = data;
        this.conteudo = conteudo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public String toString() {
        return "URL='" + url + "\'\n" +
                "Titulo='" + titulo + "\'\n" +
                "Subtitulo='" + subtitulo + "\'\n" +
                "Autor='" + autor + "\'\n" +
                "Data=" + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(data) + "\'\n" +
                "Conteudo='" + conteudo + "\'";
    }
}
