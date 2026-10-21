package src;

import java.io.IOException;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import generated.OFPLexer;
import generated.OFPParser;

public class Main {
    
    public static void main(String[] args) {
        String testProgram ="OFP_input.txt";

        System.out.println("Parsing Started");

        try{
            CharStream inputStream = CharStreams.fromFileName(testProgram); // OFP ke input ko read krta hay 
            OFPLexer lexer = new OFPLexer(inputStream); // jo already generate howay
            OFPParser parser = new OFPParser(new CommonTokenStream(lexer));
            OFPParser.ProgramContext root = parser.program(); // parsing shoro krta entry point say

            System.out.println("Parsing Completed successfully");

            SymbolTableListener listener = new SymbolTableListener();
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(listener, root);
            Trees.inspect(root, parser); // new

            System.out.println("System Table Generated");
            System.out.println("\n--Symbol Table--");
            listener.getGlobalScope().print("");

            System.out.println("\n-- Undeclared Check --");
            UndeclaredCheckListener checkListener = new UndeclaredCheckListener(listener.getScopes(), listener.getGlobalScope());
            walker.walk(checkListener, root);

            System.out.println("Total Errors: " + checkListener.getErrorCount());

            System.out.println("\n--- Type Check ---");
            TypeCheckListener typeListener = new TypeCheckListener(listener.getScopes(), listener.getGlobalScope());
            walker.walk(typeListener, root);
            System.out.println("Type Errors: " + typeListener.getErrorCount());


            
            int totalErrors = listener.getErrorCount() +checkListener.getErrorCount() + typeListener.getErrorCount();
            System.out.println("\n=== FINAL RESULT ===");
            System.out.println("Total Semantic Errors: " + totalErrors);
            if (totalErrors == 0) {
                System.out.println("Program is semantically VALID.");

                // ===== A3 NAYA CODE SHURU =====
                PythonGenerator generator = new PythonGenerator(listener.getScopes());
                String pythonCode = generator.visit(root);

                String outputFileName = testProgram.substring(0, testProgram.lastIndexOf('.')) + ".py";
                java.io.FileWriter writer = new java.io.FileWriter(outputFileName);
                writer.write(pythonCode);
                writer.close();

                System.out.println("\nPython code generated: " + outputFileName);
                // ===== A3 NAYA CODE KHATAM =====

            } else {
                System.out.println("Program has semantic ERRORS.");
            } /// new at last

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}