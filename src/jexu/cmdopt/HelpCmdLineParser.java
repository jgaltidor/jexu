package jexu.cmdopt;
import java.util.*;
import static java.lang.String.format;

/**
 * This example shows how to dynamically create basic output for a --help option. 
 */
public class HelpCmdLineParser extends CmdLineParser
{
	private final String usageMsg;
	List<String> optionHelpStrings = new LinkedList<String>();
	Option help;

	public HelpCmdLineParser() { this("<usage>"); }

	public HelpCmdLineParser(String usageMsg) {
		this.usageMsg = usageMsg;
		help = addHelp(addBooleanOption('h', "help"),
			"Show this help message and exit");
	}

	public Option addHelp(Option option, String helpString) {
		if (option.wantsValue()) return addHelp(option, helpString, "ARG");
		String beginning =
		  format("  -%s, --%s", option.shortForm(), option.longForm());
		beginning = extendStringWithSpacesToLength(beginning, 30);
		optionHelpStrings.add(beginning + "   " + helpString);
		return option;
	}

	public Option addHelp(Option option, String helpString, String metavar) {
		if (!option.wantsValue()) return addHelp(option, helpString);
		String beginning = format("  -%s %s, --%s=%s",
		  option.shortForm(), metavar, option.longForm(), metavar);
		beginning = extendStringWithSpacesToLength(beginning, 30);
		optionHelpStrings.add(beginning + "   " + helpString);
		return option;
	}

	private static String extendStringWithSpacesToLength(String beginning, int length) {
		if (length < 1) return beginning;
		int num = length - beginning.length();
		if (num < 1) return beginning;
		char[] buffer = new char[length];
		int index = 0;
		while (index < beginning.length()) {  buffer[index] = beginning.charAt(index); index++;  }
		while (index < length) {  buffer[index] = ' '; index++;  }
		return new String(buffer);
	}

	public void printUsage() {
		System.out.println(usageMsg);
		System.out.println();
		System.out.println("options:");
		for(String s : optionHelpStrings) System.out.println(s);
	}

	public void parseWithHelp(String[] args) throws CmdLineParser.OptionException {
		parse(args);
		if ( Boolean.TRUE.equals(getOptionValue(help))) {
			printUsage();
			System.exit(0);
		}
	}

	// Main method for testing this class
	public static void main( String[] args ) {
		HelpCmdLineParser parser =
			new HelpCmdLineParser("usage: java HelpCmdLineParser [options]");
		CmdLineParser.Option verbose = parser.addHelp(
				parser.addBooleanOption('v', "verbose"),
				"Print extra information");
		CmdLineParser.Option size = parser.addHelp(
				parser.addIntegerOption('s', "size"),
				"The extent of the thing", "SIZE");
		CmdLineParser.Option name = parser.addHelp(
				parser.addStringOption('n', "name"),
				"Name given to the widget", "NAME");
		CmdLineParser.Option fraction = parser.addHelp(
				parser.addDoubleOption('f', "fraction"),
				"What percentage should be discarded");
		try {
			parser.parseWithHelp(args);
		}
		catch ( CmdLineParser.OptionException e ) {
			System.err.println(e.getMessage());
			parser.printUsage();
			System.exit(2);
		}

		// Extract the values entered for the various options -- if the
		// options were not specified, the corresponding values will be
		// null.
		Boolean verboseValue = (Boolean)parser.getOptionValue(verbose);
		Integer sizeValue = (Integer)parser.getOptionValue(size);
		String nameValue = (String)parser.getOptionValue(name);
		Double fractionValue = (Double)parser.getOptionValue(fraction);

		// For testing purposes, we just print out the option values
		System.out.println("verbose: " + verboseValue);
		System.out.println("size: " + sizeValue);
		System.out.println("name: " + nameValue);
		System.out.println("fraction: " + fractionValue);

		// Extract the trailing command-line arguments ('a_nother') in the
		// usage string above.
		String[] otherArgs = parser.getRemainingArgs();
		System.out.println("remaining args: ");
		for ( int i = 0; i < otherArgs.length; ++i ) {
			System.out.println(otherArgs[i]);
		}

		// In a real program, one would pass the option values and other
		// arguments to a function that does something more useful.

		System.exit(0);
	}
}
