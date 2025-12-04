package com.consultoria.repository;

import com.google.gson.*;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JsonRepository<T> {
    private final String filePath;
    private final Gson gson;
    private final Type listType;

    public JsonRepository(String filePath, Type listType) {
        this.filePath = filePath;
        this.listType = listType;

        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, ctx) ->
                        LocalDate.parse(json.getAsString()))
                .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (src, type, ctx) ->
                        new JsonPrimitive(src.toString()))
                .create();

        criarArquivoSeNaoExistir();
    }

    private void criarArquivoSeNaoExistir() {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            try {
                Files.writeString(Paths.get(filePath), "[]");
            } catch (IOException e) {
                throw new RuntimeException("Erro ao criar arquivo JSON: " + filePath, e);
            }
        }
    }

    public List<T> listAll() {
        try {
            String content = Files.readString(Paths.get(filePath));
            if (content == null || content.trim().isEmpty()) {
                return new ArrayList<>();
            }
            return gson.fromJson(content, listType);
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + filePath);
            return new ArrayList<>();
        }
    }

    public void saveAll(List<T> list) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(list, listType, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar no arquivo: " + filePath);
            e.printStackTrace();
        }
    }
}