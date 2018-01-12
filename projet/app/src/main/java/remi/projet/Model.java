package remi.projet;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.Observable;

public class Model extends Observable {
    private static Model instance = null;
    private String _screen = "Accueil";
    public Context _context;

    private static HashMap<String, Country> countries;
    private String _currentCountry = "";

    private Model(){
        _context = MainActivity.context;
        countries = new HashMap<>();
    }

    public static Model Instance(){
        if(instance == null)
            instance = new Model();
        return instance;
    }

    public void setCurrentCountry(String s){
        if(s != "") {
            Log.d("erreur", "Current Country Loop Open");
            setCountry(s);
            Log.d("erreur", "Test if " + s + " is in the HashMap...");
            _currentCountry = s;
            Log.d("erreur", "Current Country Set to " + _currentCountry + " with success.");
        }
    }

    public Country getCurrentCountry(){
        return getCountry(_currentCountry);
    }

    public void setScreen(String screen) {
        Log.d("erreur","Screen Set.");
        _screen = screen;
        setChanged();
        notifyObservers();
    }

    public View getScreen(){
        Log.d("erreur",_screen + " getting");
        switch(_screen) {
            case "Drawer":
                Log.d("erreur", "Drawer Opening");
                Drawer.Instance().fill(_currentCountry);
                return Drawer.Instance();
            default:
                return null;
        }
    }

    public static void setCountry(String s){
        if(s != "") {
            if (countries.get(s) == null)
                countries.put(s, new Country(s));
            Log.d("erreur", "Exit of [SET] Country static routine");
        }
    }

    public static Country getCountry(String s){
        if(s != "") {
            if (countries.get(s) == null)
                setCountry(s);
            Log.d("erreur", "Exit of [GET] Country static routine");
            return countries.get(s);
        }
        return null;
    }
}