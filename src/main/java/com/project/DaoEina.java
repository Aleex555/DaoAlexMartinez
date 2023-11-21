package com.project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class DaoEina implements Dao<ObjEina> {

    private void writeList(ArrayList<ObjEina> llista) {
        try {
            JSONArray jsonArray = new JSONArray();
            for (ObjEina curs : llista) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", curs.getId());
                jsonObject.put("nom", curs.getNom());
                jsonObject.put("any", curs.getAny());
                JSONArray jsonLlenguatges = new JSONArray(curs.getLlenguatges());
                jsonObject.put("llenguatges", jsonLlenguatges);
                jsonArray.put(jsonObject);
            }
            PrintWriter out = new PrintWriter(MainDao.einesPath);
            out.write(jsonArray.toString(4)); // 4 es l'espaiat
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private int getPosition(int id) {
        int result = -1;
        ArrayList<ObjEina> llista = getAll();
        for (int cnt = 0; cnt < llista.size(); cnt = cnt + 1) {
            ObjEina curs = llista.get(cnt);
            if (curs.getId() == id) {
                result = cnt;
                break;
            }
        }
        return result;
    }

    public void setLlenguatgesAdd(int id, int idLlenguatge) {
        ArrayList<ObjEina> llista = getAll();
        int pos = getPosition(id);

        if (pos != -1) {
            ObjEina eina = llista.get(pos);
            eina.addLlenguatge(idLlenguatge);
            writeList(llista);
        }
    }

    public void setLlenguatgesDelete(int id, int idLlenguatge) {
        ArrayList<ObjEina> llista = getAll();
        int pos = getPosition(id);

        if (pos != -1) {
            ObjEina eina = llista.get(pos);
            eina.removeLlenguatge(idLlenguatge);
            writeList(llista);
        }
    }

    @Override
    public void add(ObjEina t) {
        ArrayList<ObjEina> llista = getAll();
        ObjEina item = get(t.getId());
        if (item == null) {
            llista.add(t);
            writeList(llista);
        }
    }

    @Override
    public ObjEina get(int id) {
        ObjEina result = null;
        ArrayList<ObjEina> llista = getAll();
        int pos = getPosition(id);
        if (pos != -1) {
            result = llista.get(pos);
        }
        return result;
    }

    @Override
    public ArrayList<ObjEina> getAll() {
        ArrayList<ObjEina> result = new ArrayList<>();
        ArrayList<Integer> llenguatges = new ArrayList<>();

        try {
            String content = new String(Files.readAllBytes(Paths.get(MainDao.einesPath)));
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                llenguatges.removeAll(llenguatges);
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String nom = jsonObject.getString("nom");
                int any = jsonObject.getInt("any");

                JSONArray llenguatgesArray = jsonObject.getJSONArray("llenguatges");
                for (int j = 0; j < llenguatgesArray.length(); j++) {
                    llenguatges.add(llenguatgesArray.getInt(j));
                }

                result.add(new ObjEina(id, nom, any, llenguatges));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void update(int id, ObjEina t) {
        ArrayList<ObjEina> llista = getAll();
        int pos = getPosition(id);
        if (pos != -1) {
            llista.set(pos, t);
            writeList(llista);
        }
    }

    @Override
    public void delete(int id) {
        ArrayList<ObjEina> llista = getAll();
        int pos = getPosition(id);
        if (pos != -1) {
            llista.remove(pos);
            writeList(llista);
        }
    }

    @Override
    public void print() {
        ArrayList<ObjEina> llista = getAll();
        for (int cnt = 0; cnt < llista.size(); cnt = cnt + 1) {
            System.out.println("    " + llista.get(cnt));
        }
    }

    @Override
    public void setNom(int id, String nom) {
        ArrayList<ObjEina> llista = getAll();
        int pos = getPosition(id);

        if (pos != -1) {
            ObjEina software = llista.get(pos);
            software.setNom(nom);
            writeList(llista);
        }
    }

    @Override
    public void setAny(int id, int any) {
        ArrayList<ObjEina> llista = getAll();
        int pos = getPosition(id);

        if (pos != -1) {
            ObjEina software = llista.get(pos);
            software.setAny(any);
            writeList(llista);
        }
    }

}
