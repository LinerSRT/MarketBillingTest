package com.liner.testing;

import com.sun.istack.internal.Nullable;

import java.io.*;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("unchecked")
public class Reader<T extends Model> {
    protected File file;
    protected BufferedReader bufferedReader;

    public Reader(File file) {
        this.file = file;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            bufferedReader = null;
        }
    }

    public Reader(String path) {
        this(new File(path));
    }

    @Nullable
    public T next() {
        if (bufferedReader == null)
            return null;
        try {
            String data = bufferedReader.readLine();
            if(data == null || !data.contains(","))
                return null;
            return data.split(",").length == 2 ? (T) Market.get(data) : (T) Billing.get(data);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
