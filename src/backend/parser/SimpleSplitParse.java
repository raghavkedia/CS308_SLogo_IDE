package backend.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import backend.data.Data;
import exceptions.SlogoError;

//This entire file is part of my masterpiece.
//Christine Zhou
/*
 * The purpose of this code is to take the input received from the back end manager and send it to the 
 * tree builder to make the expression tree, execute, and return the result. If the input has already
 * been defined, then the the result can be found by executing the previously built expression trees
 * and getting the result. I think this is well designed because the parser delegates the task of 
 * getting the result to other classes. The parser does not handle converting any part of the string
 * into nodes. It just sends the cleaned input to the tree builder and receives a result object back.
 * The hash table was added because there is the option to click and input old commands in the interface,
 * with the hash table, if a user entered many of the same input from history in, than the tree doesn't
 * have to be built again, only executed again. 
 */
public class SimpleSplitParse implements Parseable {
	private static final int HASH_PRIME_MULTIPLIER = 17;
	private static final int HASH_PRIME_START = 7;
	private static final int TABLE_SIZE = 100;
	private static final String RESULT_STATEMENT = "The result is ";
	private static final String END_LINE_STRING = "\n";
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/languages/";
	public static final String SYNTAX = "Syntax";
	private String language;
	private ExpressionTrees[] myExpressionTrees;

	public SimpleSplitParse(String language) {
		this.language = language;
		ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SYNTAX);
		myExpressionTrees = new ExpressionTrees[TABLE_SIZE];
	}

	/*
	 * Runs the input by cleaning the strings and calling the tree builder to
	 * build and execute the tree. Gets the output from tree builder or from the
	 * table and returns the statement.
	 */
	@Override
	public String runInput(String input, Data myData, ResourceBundle myResources) throws SlogoError {
		SLogoTreeResult myResult = null;
		double myDouble = 0;
		input = cleanStrings(input);
		int myHash = hashFunction(input);
		ExpressionTrees myTrees = myExpressionTrees[myHash % TABLE_SIZE];
		if (myTrees != null && myTrees.getString().equals(input)) {
			myDouble = executeStoredTrees(myDouble, myTrees);
		} else {
			myDouble = executeNewInputTree(input, myData, myResult, myHash);
		}
		String statement = RESULT_STATEMENT + myDouble;
		return statement;
	}

	private double executeNewInputTree(String input, Data myData, SLogoTreeResult myResult, int myHash)
			throws SlogoError {
		double myDouble;
		List<String> myStrings = new ArrayList<String>(Arrays.asList(input.split("\\s+")));
		LogoExpressionTreeBuilder myTreeBuilder = initializeTreeBuilder(myData);
		List<TreeResult> myExpressionNodes = new ArrayList<TreeResult>();
		myResult = buildAndExecuteTree(myResult, myStrings, myTreeBuilder, myExpressionNodes);
		myDouble = myResult.getMyResult();
		myExpressionTrees[myHash % TABLE_SIZE] = new SLogoExpressionTrees(myExpressionNodes, input);
		return myDouble;
	}

	private LogoExpressionTreeBuilder initializeTreeBuilder(Data myData) {
		CommandFactory myFactory = new CommandFactory(myData.getCharacterList(), myData.getVariablesList(),
				myData.getUserDefinedCommands(), myData.getProperties(), myData.getColorMap(), myData.getShapeMap());
		LogoExpressionTreeBuilder myTreeBuilder = new LogoExpressionTreeBuilder(language, myFactory,
				myData.getUserDefinedCommands());
		return myTreeBuilder;
	}

	private double executeStoredTrees(double myDouble, ExpressionTrees myTrees) throws SlogoError {
		List<TreeResult> myExpressionNodes = myTrees.getExpressionTrees();
		for (TreeResult t : myExpressionNodes) {
			myDouble = t.getMyTree().execute();
		}
		return myDouble;
	}

	private SLogoTreeResult buildAndExecuteTree(SLogoTreeResult myResult, List<String> myStrings,
			LogoExpressionTreeBuilder myTreeBuilder, List<TreeResult> myExpressionNodes) throws SlogoError {
		while (!myStrings.isEmpty()) {
			myResult = myTreeBuilder.executeExpression(myStrings);
			myExpressionNodes.add(myResult);
		}
		return myResult;
	}

	private String cleanStrings(String input) {
		input = input.replaceAll("#.*?\n", "\n ").replaceAll(END_LINE_STRING, " ").toLowerCase();
		return input;
	}

	private int hashFunction(String input) {
		int hash = HASH_PRIME_START;
		for (int k = 0; k < input.length(); k++) {
			hash = hash * HASH_PRIME_MULTIPLIER + input.charAt(k);
		}
		return hash;
	}

	@Override
	public Collection<String> stringParse(String input) {
		return null;
	}

}
