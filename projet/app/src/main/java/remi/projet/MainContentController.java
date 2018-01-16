package remi.projet;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainContentController implements View.OnClickListener {
    private static MainContentController instance = null;
    public Model _m;
    public Drawer _d;

    protected MainContentController(){

    }

    public void setUtils(){
        _m = Model.Instance();
        _d = Drawer.Instance();
    }

    public static MainContentController Instance(){
        if(instance == null)
            instance = new MainContentController();
        return instance;
    }

    @Override
    public void onClick(View v) {
        // Handle navigation view item clicks here.
        Log.d("erreur", "Click on " + v.getTag());
        Button b = _d.findViewWithTag(v.getTag());
        if (v.getTag() == "AddFactory") {
            LinearLayout ll = _d.findViewById(R.id.linearLayoutLeft);
            ll.setVisibility(View.VISIBLE);
            Toast.makeText(MainActivity.context,
                    "Add Factory Panel is now visible in the left drawer.",
                    Toast.LENGTH_SHORT).show();
            _d.open("LEFT");
            _m.setScreen("Drawer");
        }
        else if(b != null){
            Log.d("erreur", "Button : " + b.getText().toString() + " pressed.");
            _m.setCurrentCountry(b.getText().toString());
            Toast.makeText(MainActivity.context,
                    "You can check your country's factory in the right drawer.",
                    Toast.LENGTH_SHORT).show();
            _d.open("RIGHT");
            _m.setScreen("Drawer");
        }
    }
}