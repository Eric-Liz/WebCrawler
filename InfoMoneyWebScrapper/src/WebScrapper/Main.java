package WebScrapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ParseException, JSONException {
        try {
            Calendar today = Calendar.getInstance();
            Calendar last = Calendar.getInstance();
            last.add(Calendar.DATE, -2);

            String firstDoc = Jsoup.connect("https://www.infomoney.com.br/?infinity=scrolling")
                    .data("action", "infinite_scroll")
                    .data("page", "1")
                    .data("order", "DESC")
                    .data("currentday", new SimpleDateFormat("dd.MM.yy").format(last.getTime()))
                    .data("query_args[category_name]", "mercados")
                    .data("query_args[p]", "0")
                    .data("query_args[post_type][0]", "post")
                    .data("query_args[post_type][1]", "page")
                    .data("query_args[post_type][2]", "colunistas")
                    .data("query_args[post_type][3]", "patrocinados")
                    .data("query_args[posts_per_page]", "30")
                    .data("query_args[order]", "DESC")
                    .data("query_before", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(today.getTime()))
                    .data("last_post_date", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(last.getTime()))
                    .ignoreContentType(true)
                    .method(Connection.Method.POST)
                    .execute().body();

            JSONObject json = new JSONObject(firstDoc);
            JSONObject postflair = json.getJSONObject("postflair");

            Iterator keys = postflair.keys();

            List<Dado> list = new ArrayList<>();
            while (keys.hasNext()) {
                String url = (String) keys.next();

                Document secondDoc = Jsoup.connect(url).get();
                Elements secondPage = secondDoc.getElementsByClass("col-md-10 col-xl-8 m-auto");

                String titulo = secondPage.select(".page-title-1").text();
                String subtitulo = secondPage.select(".article-lead").text(); // subtitulo
                String autor = secondPage.select(".author-name").text(); // autor
                Elements contentPage = secondDoc.getElementsByClass("col-md-9 col-lg-8 col-xl-6 m-sm-auto m-lg-0 article-content");
                String conteudo = contentPage.select("p").text();
                String dtSite = secondPage.select(".published.entry-date").text().replace("h", ":");
                Date dtConverted = new SimpleDateFormat("dd MMM yyyy HH:mm").parse(dtSite);

                list.add(new Dado(url, titulo, subtitulo, autor, dtConverted, conteudo));
            }

            Collections.sort(list, new Comparator<Dado>() {
                @Override
                public int compare(Dado o1, Dado o2) {
                    return o1.getData().compareTo(o2.getData());
                }
            });

            Collections.reverse(list);

            for(Dado dado : list ) {
                System.out.println(dado);
                System.out.println("###############################");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
