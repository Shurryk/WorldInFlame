package remi.projet;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class SpinnerController implements AdapterView.OnItemSelectedListener {
    private static SpinnerController instance = null;
    public Spinner _s;

    protected SpinnerController(){

    }

    public static SpinnerController Instance(){
        if(instance == null)
            instance = new SpinnerController();
        return instance;
    }

    public void setUtils(Spinner s){
        _s = s;
    }

    @Override
    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
        String sNew = parentView.getItemAtPosition(position).toString();
        String sOld = Drawer.Instance()._modifFactory.getStateFactory().toString();
        Log.d("debugging", sNew + " HAHAHAHA " + sOld);

        State state = State.Active;
        for(Factory f : Model.getCountry(Drawer.Instance().currentCountry).getFactories())
        {
            if(f == Drawer.Instance()._modifFactory){
                state = f.getStateFactory();
                Log.d("debugging", state.toString() + " debugging 1");
                break;
            }
        }

        if(sNew != sOld && sNew != "") {
            Log.d("debugging", "if or not to if " + sNew);
            switch (sNew) {
                case "Inactive":
                    state = State.Inactive;
                    break;
                case "Surrounded":
                    state = State.Surrounded;
                    break;
                case "Occupied":
                    state = State.Occupied;
                    break;
                case "Destroyed":
                    state = State.Destroyed;
                    break;
                case "Active":
                    state = State.Active;
                    break;
            }

            for(Factory f : Model.getCountry(Drawer.Instance().currentCountry).getFactories())
            {
                if(f == Drawer.Instance()._modifFactory){
                    f.setStateFactory(state);
                    break;
                }
            }

            for(Factory f : Model.getCountry(Drawer.Instance().currentCountry).getFactories())
            {
                if(f == Drawer.Instance()._modifFactory){
                    Log.d("debugging", f.getStateFactory() + " debugging.");
                    break;
                }
            }

            Drawer.Instance()._modify = false;
            Drawer.Instance()._modifFactory = null;

            if (Drawer.Instance()._modify) {
                Log.d("debugging", "TRUE");
            }
            else
            {
                    Log.d("debugging", "FALSE");
            }

            Model.Instance().setScreen("Drawer");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parentView) {
        // your code here
    }
}
