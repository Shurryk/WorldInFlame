package remi.projet;

/**
 * Created by elbaylot on 12/01/2018.
 */
import android.widget.ImageView;

import java.util.ArrayList;

public class Player extends Country{
    public String _sName;
    public ImageView _ivPicture;
    public int _iBuildPoint = 0;
    public ArrayList<Factory> _pPUsines;
    public int ID;
    public ArrayList<Country> _pCountryList;

    public Player(String name) {
        super(name);
    }


    public void SeeCountries(){
        for(Country c:_pCountryList){
            //c._sName;
        }
    }

    public void CypherBuildPoint(){
        int tempBuildPoint = 0;
        ArrayList<Factory> tempFactoriesActive = new ArrayList<>();
        for(Country c: _pCountryList){
            tempFactoriesActive = c.getFactoriesActive();
            for(Factory f: tempFactoriesActive){
                tempBuildPoint += 1; //Multiplicateur temps tempBuildPoint += 1*getMultiplicateur();
            }
        }
    }
}


/*

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
}*/
