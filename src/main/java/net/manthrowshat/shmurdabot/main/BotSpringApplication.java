package net.manthrowshat.shmurdabot.main;

import net.manthrowshat.shmurdabot.DiscordBot;
import org.apache.commons.cli.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.Collections;

/**
 * Main class that grabs arguments as necessary and starts Spring
 *
 * @author mwalker
 */
@SpringBootApplication
public class BotSpringApplication {
    private static final Options commandLineFlags;
    /**
     * public static void main
     * @param args (String...)
     */
    public static void main(String... args) {
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(commandLineFlags, args);

            if (line.hasOption("h")) {
                HelpFormatter helpFormatter = new HelpFormatter();
                helpFormatter.printHelp("shmurdabot", "shmurdabot: Bobby Shmurda Discord Bot version " + DiscordBot.getCurrentVersion(), commandLineFlags,
                        "You must specify a bot token. Then, the web interface will launch and you will be able to control the program from there. This program also passes arguments onto Spring Boot, so you may specify Spring Boot arguments as needed.", true);
                System.exit(0);
            }

            if (!line.hasOption("t")) throw new ParseException("no bot token specified");
            String botToken = line.getOptionValue("t");

            SpringApplication spring = new SpringApplication(BotSpringApplication.class);

            ConfigurableApplicationContext context = spring.run(line.getArgs());
        } catch (ParseException e) {
            System.err.println("shmurdabot: error starting up: " + e.getLocalizedMessage());
        }


    }

    static {
        Option[] options = new Option[]{
                Option.builder().option("t").longOpt("bot-token").hasArg().argName("TOKEN").desc("Discord Build-a-Bot token").build(),

                Option.builder().option("h").longOpt("help").desc("prints this message").build(),
        };

        commandLineFlags = new Options();
        Arrays.stream(options).forEach(commandLineFlags::addOption);
    }
}
