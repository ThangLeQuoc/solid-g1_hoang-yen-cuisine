
package hoang_yen_cuisine.main;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

import hoang_yen_cuisine.basic.DevModeData;

@SuppressWarnings("deprecation")
public class App {

	private static final String CML_SYNTAX = "hoang_yen_cuisine";
	private static final CommandLineParser PARSER = new DefaultParser();
	
	private static final Options OPTIONS = constructOptions();

	public static void main(String[] args) {
		System.out.println(">>>   Welcome to Hoang Yen Cuisine   <<<");
		printUsage();
		
		
		boolean keepRunning = true;
			
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			while (keepRunning) {
				System.out.print("Enter your command: ");
		        String rawCommand = br.readLine();
		        
		        if (isEmpty(rawCommand)) {
		        	printUsage();
		        }

				keepRunning = processCommandLine(rawCommand);
			}

		}
		catch (IOException ioe) {
			System.err.println("Caught exception during read user input: " + ioe);
			ioe.printStackTrace();
		}
		
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	private static Options constructOptions() {
		final Options options = new Options();

		Option help = OptionBuilder.withDescription("Prints out usage").create("help");
		Option user = OptionBuilder.withDescription("Sets username").hasArg().withArgName("username").create("user");
		Option order = OptionBuilder.withDescription("Orders a lunch").hasArg().withArgName("name of dish").create("order");
		Option pay = OptionBuilder.withDescription("Pays your order within this week").create("pay");
		Option menu = OptionBuilder.withDescription("View menu").create("menu");
		Option godmode = OptionBuilder.withDescription("God mode").create("poweroverwhelming");
		Option quit = OptionBuilder.withDescription("Burns it down").create("quit");
		
		options.addOption(help);
		options.addOption(user);
		options.addOption(order);
		options.addOption(pay);
		options.addOption(menu);
		options.addOption(godmode);
		options.addOption(quit);

		return options;
	}

	private static void printUsage() {
		final HelpFormatter usageFormatter = new HelpFormatter();
		usageFormatter.printHelp(CML_SYNTAX, OPTIONS);
	}

	private static boolean processCommandLine(final String commands) {
		boolean keepRunning = true;
		CommandLine cl = null;
		try {
			cl = PARSER.parse(OPTIONS, commands.split(" "));
		}
		catch (Exception e) {
			System.err.println(">> Dude, it wasn't funny! Invalid option: " + e);
			return true;
		}
		
		if (cl.hasOption("help")) {
			printUsage();
		}
		if (cl.hasOption("user")) {
			DevModeData.USERNAME = cl.getOptionValue("user");
		}
		if (cl.hasOption("menu")) {
			// TODO (vhphuc May 31, 2018): print out the sample menu
			System.out.println("No menu to shown");
		}
		if (cl.hasOption("order")) {
			// TODO (vhphuc May 31, 2018):
			// Remember to take out command value
			System.out.println("Order service is under construction");
		}
		if (cl.hasOption("pay")) {
			// TODO (vhphuc May 31, 2018):
			System.out.println("Payment service is under construction");
		}
		if (cl.hasOption("poweroverwhelming")) {
			DevModeData.GOD_MODE = true;
			System.out.println("All your base are belong to us");
		}
		if (cl.hasOption("quit")) {
			keepRunning = false;
		}
		return keepRunning;
	}

}
