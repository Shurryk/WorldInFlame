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
    public String groupWar = "";

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
        _iBuildPoint += tempBuildPoint;
    }
}


