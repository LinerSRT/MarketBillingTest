package com.liner.testing;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int count = 1000000;
        int count2 = 0;
        boolean enablePrint = false;
        System.out.println("Generating dataset: " + count);
        try {
            generateBilling("billing_3.csv", count);
            generateMarket("market_3.csv", count);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Dataset generated, generation took: " + (System.currentTimeMillis() - startTime));
        System.out.println("Starting...");
        startTime = System.currentTimeMillis();
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("input.txt")), StandardCharsets.UTF_8));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String[] dataSet = line.split(" ");
                final Reader<Market> marketReader = new Reader<>(dataSet[0]);
                final Reader<Billing> billingReader = new Reader<>(dataSet[1]);
                Market market = marketReader.next();
                Billing billing = billingReader.next();
                while (market != null && billing != null) {
                    if (market.getId() == billing.getShopId()) {
                        count2++;
                        if (enablePrint)
                            System.out.printf("%d,%s,%d,%d %n %n", billing.getId(), market.getName(), market.getId(), billing.getCost());
                    }
                    market = marketReader.next();
                    billing = billingReader.next();
                }
            }
            bufferedReader.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        System.out.println("Time elapsed: " + (System.currentTimeMillis() - startTime) + " ms");
        System.out.println("Processed " + count2+ " entries");

    }


    private static void generateBilling(String name, int count) throws IOException {
        final Random random = new Random();
        final FileOutputStream fileOutputStream = new FileOutputStream(new File(name));
        final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        outputStreamWriter.write("order_id,shop_id,cost\n");
        for (int i = 0; i < count; i++) {
            outputStreamWriter.write(String.format("%s,%s,%s\n", random.nextInt(999) + 1, random.nextInt(5), random.nextInt(999) + 1));
        }
        outputStreamWriter.close();
    }

    private static void generateMarket(String name, int count) throws IOException {
        final Random random = new Random();
        final FileOutputStream fileOutputStream = new FileOutputStream(new File(name));
        final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        outputStreamWriter.write("shop_id,shop_name\n");
        for (int i = 0; i < count; i++) {
            outputStreamWriter.write(String.format("%s,%s\n", random.nextInt(5), UUID.randomUUID().toString().substring(0, 5)));
        }
        outputStreamWriter.close();
    }

}
