package remi.projet;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {
    public static Context context;

    //Variables globales
    public int idTour = 0;
    public int impulseTour = 0;
    public String initTour = "";

    public void checkTour() {

        impulseTour += 1;
        if(impulseTour > 13 || idTour == 0){
            idTour += 1;
            impulseTour = 0;
            getInitiative();
        }
    }

    public void getInitiative() {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Groupe à l'initiative");


        // add a radio button list
        String[] contriesGroup = {"Axe", "Aliés"};
        int checkedItem = 1; // cow
        builder.setSingleChoiceItems(contriesGroup, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // user checked an item
            }
        });

        // add OK and Cancel buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 1){
                    initTour = "Axe";
                }
                else {
                    initTour = "Aliés";
                }
            }
        });
        builder.setNegativeButton("Retour", null);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        Model.Instance().addObserver(this);
        MainContentController MCC = MainContentController.Instance();
        MCC.setUtils();
        RightDrawerController RDC = RightDrawerController.Instance();
        RDC.setUtils();

        getInitiative();
        setContentView(Drawer.Instance());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Log.d("erreur","screen updating");
        setContentView(Model.Instance().getScreen());
    }
}
