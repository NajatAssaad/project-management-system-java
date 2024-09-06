/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nfa035.project;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class IDManager {

    private static final String FILE_NAME = "C:\\JanFiles\\CNAM\\Projects\\NFA035_CODE_GR84\\build\\classes\\nfa035\\project\\Data\\lastIds.obj";
    private static Map<String, Integer> lastIds = loadLastIds();

    public static synchronized int getNextId(String className) {

        int nextId = lastIds.getOrDefault(className, 0) + 1;
        lastIds.put(className, nextId);
        saveLastIds();

        return nextId;

    }

    private static void saveLastIds() {
        try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(lastIds);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Integer> loadLastIds() {
        try (FileInputStream fileIn = new FileInputStream(FILE_NAME); ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (Map<String, Integer>) in.readObject();
        } catch (FileNotFoundException | EOFException e) {
            return new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {

            return new HashMap<>();
        }

    }
}
