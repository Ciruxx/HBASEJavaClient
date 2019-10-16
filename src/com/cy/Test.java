//package com.cy;
//
//import java.io.*;
//import java.util.*;
//import org.apache.hadoop.conf.*;
//import org.apache.hadoop.fs.*;
//import org.apache.hadoop.hbase.*;
//import org.apache.hadoop.hbase.client.*;
//import org.apache.hadoop.hbase.util.*;
//
//public class Test {
//    public static void main(String[] args) {
//        Configuration conf = HBaseConfiguration.create();
//        conf.addResource(new Path("/home/cy/hbase-2.2.1-bin/hbase-2.2.1/conf/hbase-default.xml"));
//        conf.addResource(new Path("/home/cy/hbase-2.2.1-bin/hbase-2.2.1/conf/hbase-site.xml"));
//
//
//        try {
//            // 操作対象の情報
//            byte[] key = Bytes.toBytes("row-by-java-client");
//            byte[] val = Bytes.toBytes("val");
//
//            // テーブル操作のためのクラス
//            HTable table = new HTable(conf, "sample");
//
//            // 1) データの挿入
//            Put p = new Put(key);
//            byte[] family = Bytes.toBytes("data");
//            byte[] column = Bytes.toBytes("column");
//            p.add(family, column, val);
//            table.put(p);
//
//            // 2) データの取得
//            Get g = new Get(key);
//            Result r = table.get(g);
//            System.out.println("Get: " + r);
//
//            // 3) データのスキャン
//            Scan scan = new Scan();
//            ResultScanner scanner = table.getScanner(scan);
//            try {
//                // Scannerの結果をイテレート
//                for (Result sr: scanner)
//                    System.out.println("Scan: " + sr);
//            } finally {
//                scanner.close();
//            }
//
//            // 4) 特定のrowからのscan
//            byte[] start = Bytes.toBytes("row3");
//            scan = new Scan(start);
//            scanner = table.getScanner(scan);
//            try {
//                // Scannerの結果をイテレート
//                for (Result sr: scanner)
//                    System.out.println("Scan: " + sr);
//            } finally {
//                scanner.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
