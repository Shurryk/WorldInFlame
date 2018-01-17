package remi.projet;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Drawer extends DrawerLayout {
    private static Drawer instance = null;

    public String currentCountry = "";
    public boolean _modify = false;
    public Factory _modifFactory = null;

    EditText _etNameF = null;
    EditText _etCodeF = null;

    protected Drawer(){
        super(MainActivity.context);
        LayoutInflater inflater = LayoutInflater.from(MainActivity.context);
        inflater.inflate(R.layout.activity_main,this);
        Log.d("myDebug","Constructor Drawer");
        fill("");
        Log.d("myDebug","App Created");
    }

    public static Drawer Instance(){
        if(instance == null)
            instance = new Drawer();
        return instance;
    }

    public void fill(String s){
        Log.d("myDebug", "fill.");
        fillRightDrawer(s);
        Log.d("myDebug", "fillR");
        fillMainContent();
        Log.d("myDebug", "fillM");
        fillLeftDrawer(s);
        Log.d("myDebug", "fillL");
    }

    private void fillRightDrawer(String s){
        Log.d("myDebug", "fillR_start");
        currentCountry = s;
        Log.d("myDebug", "fillR_country_string");
        LinearLayout llr = findViewById(R.id.linearLayoutRight);
        Log.d("myDebug", "Number of child in llr : " + llr.getChildCount());
        if(llr.getChildCount() != 0) {
            llr.removeAllViewsInLayout();
            //llr.removeAllViews();
        }
        Log.d("myDebug", "Layout Found and Views removed");

        ImageView imageView = new ImageView(MainActivity.context);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        Log.d("myDebug", "Flag set and added.");

        if(currentCountry != "") {
            String ls = currentCountry.toLowerCase();
            int imageResource = MainActivity.context.getResources().getIdentifier(
                    ls, "mipmap", MainActivity.context.getPackageName()
            );
            Log.d("myDebug", Integer.toString(imageResource));
            imageView.setImageResource(imageResource);
            imageView.setScaleType(ImageView.ScaleType.FIT_START);
        }

        //which adds the imageview to your layout
        llr.addView(imageView);
        Log.d("myDebug", "Flag set and added.");

        textViewSetting(llr, currentCountry, Color.DKGRAY);
        Log.d("myDebug", "Country name set and added.");
        if(Model.getCountry(currentCountry) != null) {
            for (Factory f : Model.getCountry(currentCountry).getFactories()) {
                LinearLayout ll = new LinearLayout(MainActivity.context);
                ll.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                ll.setOrientation(LinearLayout.HORIZONTAL);
                textViewSettingFactory(ll, f);
                if(_modify == true && _modifFactory == f){
                    spinnerDefine(ll);
                }
                else {
                    textViewSetting(ll, " " + f.getStateFactory().toString(), f.getColorFactory());
                    buttonEditSetting(ll, f);
                }

                Log.d("myDebug", "TextView : " + f.toString() + " added to list.");
                llr.addView(ll);
            }
        }
    }

    private void fillMainContent(){
        Log.d("erreur","Main content filled");

        LayoutInflater inflater = LayoutInflater.from(MainActivity.context);
        inflater.inflate(R.layout.content_view,this);

        LinearLayout linearLayout = findViewById(R.id.content_view);
        linearLayout.removeAllViews();
        Log.d("erreur","LinearLayout get and cleaned");

        Controller MainController = MainContentController.Instance();
        Log.d("myDebug","inflation MyDrawer");
        if(currentCountry != "") {
            buttonSetting(linearLayout, "Add Factory in Left Drawer", "AddFactory", MainController);
        }
        buttonSetting(linearLayout, "France", "bFrance", MainController);
        buttonSetting(linearLayout, "Germany", "bGermany", MainController);
        buttonSetting(linearLayout, "Spain", "bSpain", MainController);
        buttonSetting(linearLayout, "UK", "bUK", MainController);
    }

    private void fillLeftDrawer(String s){
        currentCountry = s;
        LinearLayout lll = findViewById(R.id.linearLayoutLeft);
        lll.removeAllViews();
        Log.d("myDebug", "Layout Found and Views removed");

        Controller leftController = LeftDrawerController.Instance();
        Log.d("myDebug","inflation MyDrawer");

        textViewSetting(lll, s, Color.BLUE);

        _etNameF = new EditText(MainActivity.context);
        _etCodeF = new EditText(MainActivity.context);
        Log.d("myDebug","Input creation");

        _etNameF.setHint("Nom de l'usine");
        _etCodeF.setHint("Case de l'usine");

        _etNameF.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        _etCodeF.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        Log.d("myDebug","Params setting");

        lll.addView(_etNameF);
        lll.addView(_etCodeF);
        Log.d("myDebug","Input adding");

        buttonSetting(lll, "Ajout", "bAdd", leftController);
        buttonSetting(lll, "Retour", "bBack", leftController);
        Log.d("myDebug","Button set");
    }

    protected void buttonSetting(LinearLayout l, String text, String tag, Controller controller){
        Button b = new Button(MainActivity.context);
        b.setText(text);
        b.setTag(tag);
        b.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        Log.d("erreur","Params Setting");

        l.addView(b);
        Log.d("erreur","View Added");

        b.setOnClickListener(controller);
        Log.d("erreur","Controller Setting");
    }

    protected void buttonEditSetting(LinearLayout ll, Factory f){
        Button b = new Button(MainActivity.context);
        b.setText("✎");
        b.setTag(f.getCodeFactory());
        b.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        Log.d("erreur","Params Setting");

        ll.addView(b);
        Log.d("erreur","View Added");

        b.setOnClickListener(EditController.Instance());
        Log.d("erreur","Controller Setting");
    }

    protected void textViewSetting(LinearLayout l, String text, int color){
        TextView tv = new TextView(MainActivity.context);
        tv.setText(text);
        tv.setTextColor(color);
        tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        Log.d("myDebug","Params Setting");
        l.addView(tv);
        Log.d("myDebug","View Added");
    }

    protected void textViewSettingFactory(LinearLayout l, Factory f){
        TextView tv = new TextView(MainActivity.context);
        tv.setText(f.toString());
        tv.setTextColor(f.getColorFactory());
        tv.setTag(f.getCodeFactory());
        tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        Log.d("myDebug","Params Setting");
        l.addView(tv);
        Log.d("myDebug","View Added");
    }

    private void spinnerDefine(LinearLayout ll){
        Spinner spinner = new Spinner(MainActivity.context);
        ArrayAdapter<String> aas = new ArrayAdapter<String>(
                MainActivity.context, android.R.layout.simple_dropdown_item_1line);
        aas.add("");

        for(State s : State.values()) {
            String ss = s.toString();
            if (ss != "Destroyed")
                aas.add(s.toString());
            else if (_modifFactory.getColorFactory() != Color.BLACK)
                aas.add(s.toString());
        }

        spinner.setAdapter(aas);
        SpinnerController.Instance().setUtils(spinner);
        spinner.setOnItemSelectedListener(SpinnerController.Instance());
        ll.addView(spinner);
    }

    public void close(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        if (drawer.isDrawerOpen(GravityCompat.END))
            drawer.closeDrawer(GravityCompat.END);
    }

    public void open(String s){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        _modify = false;
        _modifFactory = null;
        if(s == "LEFT")
            if (!drawer.isDrawerOpen(GravityCompat.START))
                drawer.openDrawer(GravityCompat.START);
        if(s == "RIGHT")
            if (!drawer.isDrawerOpen(GravityCompat.END))
                drawer.openDrawer(GravityCompat.END);
    }

    public void addFactoryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.context);
        // Get the layout inflater
        LayoutInflater inflater = LayoutInflater.from(MainActivity.context);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.add_factory, null));
        builder.setPositiveButton("Ajout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Log.d("myDebug", "Cliquer sur Ajout");

                EditText name = findViewById(R.id.et_nameF);
                Log.d("myDebug", "là 1");
                String sName = name.getText().toString();
                Log.d("myDebug", "là 2");
                EditText code = findViewById(R.id.et_codeF);
                Log.d("myDebug", "là 3");
                String sCode = name.getText().toString();
                Log.d("myDebug", "là 4");

                Log.d("myDebug", "Ceci est nul : " + sName + " " + sCode);

                int color = Color.RED;
                if(sName.trim().length() != 0 && sCode.trim().length() != 0) {
                    Model.getCountry(currentCountry).addFactory(name.toString(), code.toString(), color, State.Active);
                    LinearLayout ll = findViewById(R.id.linearLayoutLeft);
                    ll.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.context,
                            "Factory added.",
                            Toast.LENGTH_SHORT).show();
                    close();
                    Model.Instance().setScreen("Drawer");
                } else {
                    Toast.makeText(MainActivity.context,
                            "The form isn't correctly filled.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Retour", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}