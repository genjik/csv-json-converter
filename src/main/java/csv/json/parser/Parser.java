package csv.json.parser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONArray;

public class Parser {
    private int columnNumber = 0;
    private ArrayList<String> headerNames = new ArrayList<>();
    private JSONArray output = new JSONArray();

    private void getHeader(String line) {
        for (int i = 0; i < columnNumber; i++) {
            headerNames.add(line.split(",")[i]);
        }
    }
    
    private void createJsonObject(String line) {
        LinkedHashMap<String, String> jsonObj = new LinkedHashMap<>();
        String []values = line.split(",");

        if (values.length == headerNames.size()) {
            for (int i = 0; i < headerNames.size(); i++) {
                jsonObj.put(headerNames.get(i), values[i]); 
            }
        }
        output.put(jsonObj);
    }

    public JSONArray parse(String path) {
        try(FileReader file = new FileReader(path); Scanner sc = new Scanner(file)) {
            String firstLine = sc.nextLine();
            columnNumber = firstLine.split(",").length;

            getHeader(firstLine);

            while (sc.hasNextLine()) {
                createJsonObject(sc.nextLine());
            }
        } catch (IOException e) {
            System.out.println("File was not found");
            e.printStackTrace();
        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.println("No lines were found or file is closed");
            e.printStackTrace();
        }
        return output;
    }
}
