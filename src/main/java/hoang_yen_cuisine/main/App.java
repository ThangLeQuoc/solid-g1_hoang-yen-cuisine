
package hoang_yen_cuisine.main;

import static hoang_yen_cuisine.basic.MotherOfRepositories.CURRENT_USER;
import static hoang_yen_cuisine.basic.MotherOfRepositories.GOD_MODE;
import static hoang_yen_cuisine.basic.MotherOfRepositories.MENU;
import static hoang_yen_cuisine.basic.MotherOfRepositories.OTHER_USERS;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

import com.google.gson.Gson;

import hoang_yen_cuisine.basic.Dish;
import hoang_yen_cuisine.basic.LunchOrderProcessor;
import hoang_yen_cuisine.basic.MotherOfRepositories;
import hoang_yen_cuisine.notification.NotificationProcessor;

@SuppressWarnings("deprecation")
public class App {

	private static final String CML_SYNTAX = "hoang_yen_cuisine";
	private static final CommandLineParser PARSER = new DefaultParser();
	
	private static final Options OPTIONS = constructOptions();
	
	private static LunchOrderProcessor orderProcess = LunchOrderProcessor.getInstance();
	private static NotificationProcessor notificationProcessor = NotificationProcessor.getInstance();

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
		}
		
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	private static Options constructOptions() {
		final Options options = new Options();

		Option help = OptionBuilder.withDescription("Prints out usage").create("help");
		Option user = OptionBuilder.withDescription("Sets username").hasArg().withArgName("username").create("user");
		Option order = OptionBuilder.withDescription("Orders a lunch").hasArg().withArgName("ID of dish").create("order");
		Option pay = OptionBuilder.withDescription("Pays your order within this week").create("pay");
		Option addDish = OptionBuilder.withDescription("Add new dish for today. Usage: \n\n -addDish [{\"name\":\"ca\",\"price\":25},{\"name\":\"dau-hu\",\"price\":25}]").create("addDish");
		Option menu = OptionBuilder.withDescription("View menu").create("menu");
		Option report = OptionBuilder.withDescription("Generate reports").create("report");
		Option godmode = OptionBuilder.withDescription("God mode").create("poweroverwhelming");
		Option quit = OptionBuilder.withDescription("Burns it down").create("quit");

		options.addOption(help);
		options.addOption(user);
		options.addOption(order);
		options.addOption(pay);
		options.addOption(addDish);
		options.addOption(menu);
		options.addOption(report);
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
			CURRENT_USER = cl.getOptionValue("user");
			GOD_MODE = false;
			System.out.println("Welcome " + CURRENT_USER + "!");
		}

		if (cl.hasOption("quit")) {
			System.out.println("Have a nice day!");
			orderProcess.tearDown();
			keepRunning = false;
		}

		if (cl.hasOption("addDish")) {
			if (!GOD_MODE) {
				throw new IllegalAccessError("You ain't Yen!!!");
			}
			String[] args = cl.getArgs();

			try {
				Gson gson = new Gson();

				Dish[] dishes = gson.fromJson(args[0], Dish[].class);

				MENU.addAll(Arrays.asList(dishes));
			}
			catch (Exception e) {
				System.err.println(">> Wrong format. Ex: -addDish [{\"name\":\"fried-fish\",\"price\":25},{\"name\":\"tofu\",\"price\":25}]");
				return true;
			}
			notificationProcessor.sendNotification(OTHER_USERS);
		}

		if (cl.hasOption("menu")) {
			orderProcess.viewMenu();
		}

		if (cl.hasOption("order")) {
			String rawDishId = cl.getOptionValue("order");
			try {
				int dishId = Integer.parseInt(rawDishId);
				orderProcess.makeLunchOrder(dishId);
			}
			catch (NumberFormatException nfe) {
				System.err.println("Invalid dishId: " + rawDishId);
			}
		}
		
		if (cl.hasOption("pay")) {
			orderProcess.pay();
		}

		if (cl.hasOption("poweroverwhelming")) {
			GOD_MODE = true;
			System.out.println("All your base are belong to us");
		}
		
		if (cl.hasOption("report")) {
			if (!GOD_MODE) {
				throw new IllegalAccessError("You ain't Yen!!!");
			}
			String report = orderProcess.generateReport();
			System.out.println(report);
		}

		return keepRunning;
	}

}
