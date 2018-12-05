package com.kuehne_nagel.app;

import com.kuehne_nagel.app.props.Props;
import org.w3c.dom.Element;
import picocli.CommandLine;

import java.io.File;

import static com.kuehne_nagel.app.xml.XMLIO.loadXML;
import static com.kuehne_nagel.app.xml.XMLIO.saveXML;
import static com.kuehne_nagel.app.xml.XMLProcessor.processPlaceholders;

@CommandLine.Command(name = "XML filler")
public class Application implements Runnable {
    @CommandLine.Option(names={"-i"}, required = true, description = "Input XML file with placeholders")
    private File input;

    @CommandLine.Option(names={"-o"}, required = true, description = "Output XML file")
    private File output;

    @CommandLine.Option(names={"-p"}, required = true, description = "Properties file")
    private File properties;

    @CommandLine.Option(names = {"-help"}, usageHelp = true, description = "Display this help message")
    boolean help;

    public void run() {
        try {
            Props props = new Props(properties);
            Element root = loadXML(input);
            processPlaceholders(root, props);
            saveXML(root, output);
            //printXML(root);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        CommandLine.run(new Application(), args);
    }
}
