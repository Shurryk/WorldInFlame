package remi.projet;

import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditController implements View.OnClickListener {
    private static EditController instance = null;

    protected EditController(){

    }

    public static EditController Instance(){
        if(instance == null)
            instance = new EditController();
        return instance;
    }

    @Override
    public void onClick(View v) {
        String s = v.getTag().toString();
        Country c = Model.getCountry(Drawer.Instance().currentCountry);
        for(Factory f : c.getFactories()){
            if(f.getCodeFactory() == s){
                Drawer.Instance()._modifFactory = f;
                Drawer.Instance()._modify = true;
                break;
            }
        }
        Model.Instance().setScreen("Drawer");
    }
}
