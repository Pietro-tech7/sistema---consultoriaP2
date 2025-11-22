package com.consultoria.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class JsonRepository<T> {
    private final File file;
    private final Gson gson;
    private final Type type;

    public JsonRepository(String path, Type type) {
        this.file = new File(path);
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.type = type;
        ensureFile();
    }

    private void ensureFile() {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                Files.writeString(file.toPath(), "[]");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<T> listAll() {
        try {
            String content = Files.readString(file.toPath());
            if (content.isBlank()) return new ArrayList<>();
            return gson.fromJson(content, type);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void saveAll(List<T> list) {
        try (Writer w = new FileWriter(file)) {
            gson.toJson(list, type, w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

