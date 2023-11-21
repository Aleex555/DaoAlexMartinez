package com.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class DaoLlenguatge implements Dao<ObjLlenguatge> {

    private void writeList(ArrayList<ObjLlenguatge> llista) {
        try {
            JSONArray jsonArray = new JSONArray();
            for (ObjLlenguatge llenguatge : llista) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", llenguatge.getId());
                jsonObject.put("nom", llenguatge.getNom());
                jsonObject.put("any", llenguatge.getAny());
                jsonObject.put("dificultat", llenguatge.getDificultat());
                jsonObject.put("popularitat", llenguatge.getPopularitat());
                jsonArray.put(jsonObject);
            }
            PrintWriter out = new PrintWriter(MainDao.llenguatgesPath);
            out.write(jsonArray.toString(4)); // 4 es l'espaiat
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getPosition(int id) {
        int result = -1;
        ArrayList<ObjLlenguatge> llista = getAll();
        for (int cnt = 0; cnt < llista.size(); cnt = cnt + 1) {
            ObjLlenguatge alumne = llista.get(cnt);
            if (alumne.getId() == id) {
                result = cnt;
                break;
            }
        }
        return result;
    }

    public void setDificultat(int id, String dificultat) {
        ArrayList<ObjLlenguatge> llista = getAll();
        int pos = getPosition(id);

        if (pos != -1) {
            ObjLlenguatge software = llista.get(pos);
            software.setDificultat(dificultat);
            writeList(llista);
        }
    }

    public void setPopularitat(int id, int popularitat) {
        ArrayList<ObjLlenguatge> llista = getAll();
        int pos = getPosition(id);

        if (pos != -1) {
            ObjLlenguatge software = llista.get(pos);
            software.setPopularitat(popularitat);
            writeList(llista);
        }
    }

    @Override
    public void add(ObjLlenguatge t) {
        ArrayList<ObjLlenguatge> llista = getAll();
        ObjLlenguatge item = get(t.getId());
        if (item == null) {
            llista.add(t);
            writeList(llista);
        }
    }

    @Override
    public ObjLlenguatge get(int id) {
        ObjLlenguatge result = null;
        ArrayList<ObjLlenguatge> llista = getAll();
        int pos = getPosition(id);
        if (pos != -1) {
            result = llista.get(pos);
        }
        return result;
    }

    @Override
    public ArrayList<ObjLlenguatge> getAll() {
        ArrayList<ObjLlenguatge> result = new ArrayList<>();

        try {
            String content = new String(Files.readAllBytes(Paths.get(MainDao.llenguatgesPath)));
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String nom = jsonObject.getString("nom");
                int any = jsonObject.getInt("any");
                String dificultat = jsonObject.getString("dificultat");
                int popularitat = jsonObject.getInt("popularitat");

                result.add(new ObjLlenguatge(id, nom, any, dificultat, popularitat));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void update(int id, ObjLlenguatge t) {
        ArrayList<ObjLlenguatge> llista = getAll();
        int pos = getPosition(id);
        if (pos != -1) {
            llista.set(pos, t);
            writeList(llista);
        }
    }

    @Override
    public void delete(int id) {
        ArrayList<ObjLlenguatge> llista = getAll();
        int pos = getPosition(id);
        if (pos != -1) {
            llista.remove(pos);
            writeList(llista);
        }
    }

    @Override
    public void print() {
        ArrayList<ObjLlenguatge> llista = getAll();
        for (int cnt = 0; cnt < llista.size(); cnt = cnt + 1) {
            System.out.println("    " + llista.get(cnt));
        }
    }

    @Override
    public void setNom(int id, String nom) {
        ArrayList<ObjLlenguatge> llista = getAll();
        int pos = getPosition(id);

        if (pos != -1) {
            ObjLlenguatge software = llista.get(pos);
            software.setNom(nom);
            writeList(llista);
        }
    }

    @Override
    public void setAny(int id, int any) {
        ArrayList<ObjLlenguatge> llista = getAll();
        int pos = getPosition(id);

        if (pos != -1) {
            ObjLlenguatge software = llista.get(pos);
            software.setAny(any);
            writeList(llista);
        }
    }

}
