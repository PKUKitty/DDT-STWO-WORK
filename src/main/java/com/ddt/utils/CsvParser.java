package com.ddt.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CsvParser {

    /**the csv file name*/
    private String fileName;

    /** the csv file header*/
    private boolean hasHeader;

    /** file content info*/
    private ArrayList<ArrayList<String>> rows;

    /**
     * constructors
     * @param fileName file path and name;
     * @param hasHeader has header;
     */
    public CsvParser(final String fileName, final boolean hasHeader) {
        this.fileName = fileName;
        this.hasHeader = hasHeader;
        rows = new ArrayList<>();
        parseCsv();
    }

    /**
     * Get the row count;
     * @return the row count;
     */
    public int getRowCount() {
        return rows.size();
    }

    /**
     * get the column count for the specified row
     * @param row row number in the csv file
     * @return the column count on a row
     */
    public int getColumnCount(final int row) {
        return rows.get(row).size();
    }

    /**
     * Get the specified element as integer.
     * @param row row number in the csv file
     * @param column column number in the csv file
     * @return an element as integer;
     */
    public int getInt(final int row, final int column) {
        return Integer.valueOf(rows.get(row).get(column));
    }

    /**
     * Get the specified element as double.
     * @param row row number in the csv file
     * @param column column number in the csv file
     * @return an element as double;
     */
    public double getDouble(final int row, final int column) {
        return Double.valueOf(rows.get(row).get(column));
    }

    /**
     * Get the specified element as string.
     * @param row row number in the csv file
     * @param column column number in the csv file
     * @return an element as string;
     */
    public String getString(final int row, final int column) {
        return rows.get(row).get(column);
    }


    /**
     * using buffer reader and file reader parse csv file;
     * write the csv file into the array list;
     */
    private void parseCsv() {
        BufferedReader bufferedReader;
        FileReader fileReader;
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        String line;

        //skip the file header;
        if (hasHeader) {
            try {
                bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }

                // Be Windows friendly and delete the \r if present.
                if (line.charAt(line.length() - 1) == '\r') {
                    line = line.substring(0, line.length() - 1);
                }

                ArrayList<String> oneLineList = new ArrayList<>();
                oneLineList.addAll(Arrays.asList(line.split(",")));

                rows.add(oneLineList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
