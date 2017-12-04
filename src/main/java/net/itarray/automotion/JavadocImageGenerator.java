package net.itarray.automotion;

import com.google.common.collect.Lists;
import net.itarray.automotion.validation.ChunkUIElementValidator;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class JavadocImageGenerator {

    public static void main(String[] args) throws IOException {
        File targetDir = new File(args[0]);
        File generatedSourcesDir = new File(targetDir, "generated-sources");
        File apidocs = new File(generatedSourcesDir, "apidocs");
        String packageName = ChunkUIElementValidator.class.getPackage().getName();
        final File[] target = {apidocs};
        Stream.of(packageName.split("\\.")).forEach(n -> target[0] =new File(target[0], n));
        System.out.println("JavadocImageGenerator.main " +args[0]);
        System.out.println(target[0]);
        File docFiles = new File(target[0], "doc-files");
        docFiles.mkdirs();
        Path path = new File(docFiles, "sample.svg").toPath();
        List<String> lines = Lists.newArrayList(
                "<svg xmlns=\"http://www.w3.org/2000/svg\" \n" +
                        "          xmlns:xlink=\"http://www.w3.org/1999/xlink\" \n" +
                        "          version=\"1.1\" width=\"400\" height=\"110\">",
                  "<rect width=\"300\" height=\"100\" style=\"fill:rgb(0,0,255);stroke-width:3;stroke:rgb(0,0,0)\" />",
                "</svg>"
        );
        Charset charset = Charset.forName("UTF-8");
        Files.write(path, lines, charset);
    }
}
