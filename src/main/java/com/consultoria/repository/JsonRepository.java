package com.consultoria.repository;

import com.google.gson.*;
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

        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(java.time.LocalDate.class,
                        (JsonDeserializer<java.time.LocalDate>) (json, typeOfT, context) ->
                                java.time.LocalDate.parse(json.getAsString()))
                .registerTypeAdapter(java.time.LocalDate.class,
                        (JsonSerializer<java.time.LocalDate>) (localDate, typeOfSrc, context) ->
                                new JsonPrimitive(localDate.toString()))
                .create();

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
