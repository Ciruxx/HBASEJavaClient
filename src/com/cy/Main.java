package com.cy;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("############## HBASE Client ##############");
        printWhale();
        String action = getDataFromUser("Cosa vuoi fare? (put, get, delete)");

        switch (action){
            case "put": {
                String tableName = getDataFromUser("Nome della tabella?");
                String rowKey = getDataFromUser("Row?");
                String family = getDataFromUser("Family?");
                String qualifier = getDataFromUser("Qualifier?");
                String value = getDataFromUser("Value?");
                addRecord(tableName, rowKey, family, qualifier, value);
            }
            case "get": {
                String tableName = getDataFromUser("Nome della tabella?");
                String rowKey = getDataFromUser("Row?");
                Cell record = getOneRecord(tableName, rowKey);
                if (record != null) {
                    System.out.print(new String(CellUtil.cloneRow(record)) + " ");
                    System.out.print(new String(CellUtil.cloneFamily(record)) + ":");
                    System.out.print(new String(CellUtil.cloneQualifier(record)) + " ");
                    System.out.println(new String(CellUtil.cloneValue(record)));
                } else {
                    System.out.println("Non ho trovato row, sorry! ¯\\_(ツ)_/¯");
                }
            }
            case "delete": {
                String tableName = getDataFromUser("Nome della tabella?");
                String rowKey = getDataFromUser("Row?");
                delRecord(tableName, rowKey);
            }
            default: {
                System.out.println(getRandom(insults));
            }
        }
    }

    private static void printWhale() {
        System.out.println("                  __       __\n" +
                "                     '.'--.--'.-'\n" +
                "       .,_------.___,   \\' r'\n" +
                "       ', '-._a      '-' .'\n" +
                "        '.    '-'Y \\._  /\n" +
                "          '--;____'--.'-,\n" +
                "          /..'       '''\n");
    }

    private static String getDataFromUser(String s) {
        Scanner scan = new Scanner("");
        System.out.println(s);
        System.out.print("> ");
        String value = "";
        value = scan.nextLine();
        scan.close();
        return value;
    }

    private static Cell getOneRecord(String tableName, String rowKey) throws IOException {
        Table table = getTable(tableName);

        Get get = new Get(Bytes.toBytes(rowKey));
        Result result = table.get(get);

        Cell[] cells = result.rawCells();

        table.close();

        if (cells.length == 0){
            return null;
        }
        return cells[0];
    }

    private static Table getTable(String tableName) throws IOException {
        Configuration conf = HBaseConfiguration.create();

        Connection connection = ConnectionFactory.createConnection(conf);
        TableName name = TableName.valueOf(tableName);
        Table table = connection.getTable(name);

        System.out.println("Table name: " + table.getName());
        return table;
    }

    private static void delRecord(String tableName, String rowKey) throws IOException {
        Table table = getTable(tableName);
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        table.delete(delete);
        table.close();
    }

    private static void addRecord(String tableName, String rowKey, String family, String qualifier, String value) throws IOException {
        Table table = getTable(tableName);
        Put p = new Put(Bytes.toBytes(rowKey));
        final byte[] byteFamily = Bytes.toBytes(family);
        final byte[] byteQualifier = Bytes.toBytes(qualifier);
        final byte[] byteValue = Bytes.toBytes(value);
        p.addColumn(byteFamily, byteQualifier, byteValue);
        table.put(p);
        table.close();
    }

    private static String[] insults = new String[]{
            "Ma te la fai finita?",
            "Guardalo, fa er simpatico, mò mi riavvii.",
            "Famo che hai sbagliato e amici come prima.",
            "Non faccio quello che ti aspetti, ci sarà un motivo?",
            "Fatti una domanda e datti una risposta.",
            "Si m'pò n'cojone compà.",
            "Sei un baby."
    };
    private static String getRandom(String[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
}
