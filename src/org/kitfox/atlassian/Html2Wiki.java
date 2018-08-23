package org.kitfox.atlassian;

/**
 * Created by jealar2 on 2018-06-15
 */
public class Html2Wiki {

//    private final WysiwygConverter converter;
//
//    public Html2Wiki() {
//        this.converter = new DefaultWysiwygConverter();;
//    }
//
//    private void convert(String file) throws Exception {
//
//        Path path = Paths.get(this.getClass().getResource(file).toURI());
//        StringBuilder builder = new StringBuilder();
//        Files.lines(path).forEachOrdered(s -> {
//            builder.append(s);
//        });
//        Path out = Paths.get(this.getClass().getResource("").toURI()).resolve("wiki.txt");
//        Files.write(out, this.converter.convertXHtmlToWikiMarkup(builder.toString()).getBytes(), StandardOpenOption.CREATE);
//    }
//
//    public static void main(String[] args) throws Exception {
//        Html2Wiki wiki = new Html2Wiki();
//
//        wiki.convert("./dashboard.html");
//    }
}
