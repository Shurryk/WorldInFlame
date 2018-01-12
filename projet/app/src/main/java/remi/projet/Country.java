package remi.projet;

import android.graphics.Color;

import java.util.ArrayList;

public class Country {
    public String _sName;
    private ArrayList<Factory> _factories;

    public Country(String name){
        _sName = name;
        _factories = new ArrayList<Factory>();
        switch(name){
            case "France":
                _factories.add(new Factory("75", "PARIS", Color.RED));
                _factories.add(new Factory("69", "LYON", Color.BLACK));
                _factories.add(new Factory("43", "LE-PUY-EN-VELAY", Color.RED));
                _factories.add(new Factory("71", "FLEURY LA MONTAGNE", Color.RED));
                break;
            case "Germany":
                _factories.add(new Factory("10", "BERLIN", Color.BLACK));
                _factories.add(new Factory("90", "NUREMBERG", Color.BLACK));
                _factories.add(new Factory("60", "FRANKFURT", Color.BLACK));
                break;
            case "Spain":
                _factories.add(new Factory("28", "MADRID", Color.RED));
                _factories.add(new Factory("08", "BARCELONA", Color.RED));
                break;
            case "UK":
                _factories.add(new Factory("00", "LONDON", Color.BLACK));
                break;
            default:
                _factories.add(new Factory("??", "NULL", Color.BLACK));
                break;
        }
    }

    public ArrayList<Factory> getFactories(){
        return _factories;
    }

    public void addFactory(String name, String code, int color){
        _factories.add(new Factory(code, name, color));
    }
}
