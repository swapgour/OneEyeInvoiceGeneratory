package com.sainivas.oneeyeinvoicegenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.io.File;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.LinearLayout;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import com.google.android.material.textfield.TextInputLayout;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;

public class Home extends AppCompatActivity {

    String[] items = {"Other","Co-erd set","One piece","Off shoulder","Jumpsuit","Tops","Tang top","Shirts","Shorts","Skrit","Stright fit jeans","Pant","Mom fit jeans","Highwaist jeans","Footwear"};

    RadioButton mr,ms, radioButton;
    Button invoiceButton,createPDF;
    Bitmap bmp,scaledbmp;
    private int EXTERNAL_STORAGE_PERMISSION_CODE = 23;
    final Calendar calendar= Calendar.getInstance();
    RadioGroup radioGroup;
    TextInputLayout dropdown_menu1,dropdown_menu2,dropdown_menu3,dropdown_menu4,dropdown_menu5;
    LinearLayout buyerDetailsLayour,productDetailsLayour;
    EditText editFullName,editMobileNumber,amount1,amount2,amount3,amount4,amount5;
    TextView product1,product2,product3,product4,product5;
    AutoCompleteTextView autoCompleteTextView1,autoCompleteTextView2,autoCompleteTextView3,autoCompleteTextView4,autoCompleteTextView5;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Find Element:

        mr = findViewById(R.id.mr);
        ms = findViewById(R.id.ms);
        radioGroup = findViewById(R.id.radioGroup);
        editFullName=findViewById(R.id.editFullName);
        editMobileNumber=findViewById(R.id.editMobileNumber );
        amount1=findViewById(R.id.amount1);
        amount2=findViewById(R.id.amount2);
        amount3=findViewById(R.id.amount3);
        amount4=findViewById(R.id.amount4);
        amount5=findViewById(R.id.amount5);
        product1=findViewById(R.id.product1);
        product2=findViewById(R.id.product2);
        product3=findViewById(R.id.product3);
        product4=findViewById(R.id.product4);
        product5=findViewById(R.id.product5);
        buyerDetailsLayour= findViewById(R.id.buyerDetailsLayour);
        productDetailsLayour= findViewById(R.id.productDetailsLayour);
        dropdown_menu1=findViewById(R.id.dropdown_menu1);
        dropdown_menu2=findViewById(R.id.dropdown_menu2);
        dropdown_menu3=findViewById(R.id.dropdown_menu3);
        dropdown_menu4=findViewById(R.id.dropdown_menu4);
        dropdown_menu5=findViewById(R.id.dropdown_menu5);
        createPDF = findViewById(R.id.invoiceButton);
        bmp = BitmapFactory.decodeResource(getResources(),R.drawable.invoice);
        scaledbmp = Bitmap.createScaledBitmap(bmp,1414,2000,false);

        //Create OneEye Directory:

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
        LocalDateTime now = LocalDateTime.now();

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) , "/OneEye_Invoice/"+dtf.format(now));

        if(!file.exists()) {
            file.mkdirs();
        }

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        autoCompleteTextView1 = findViewById(R.id.auto_complete_text1);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_items,items);
        autoCompleteTextView1.setAdapter(adapterItems);
        autoCompleteTextView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Items: "+item, Toast.LENGTH_SHORT).show();
            }
        });

        autoCompleteTextView2 = findViewById(R.id.auto_complete_text2);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_items,items);
        autoCompleteTextView2.setAdapter(adapterItems);
        autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Items: "+item, Toast.LENGTH_SHORT).show();
            }
        });

        autoCompleteTextView3 = findViewById(R.id.auto_complete_text3);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_items,items);
        autoCompleteTextView3.setAdapter(adapterItems);
        autoCompleteTextView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Items: "+item, Toast.LENGTH_SHORT).show();
            }
        });

        autoCompleteTextView4 = findViewById(R.id.auto_complete_text4);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_items,items);
        autoCompleteTextView4.setAdapter(adapterItems);
        autoCompleteTextView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Items: "+item, Toast.LENGTH_SHORT).show();
            }
        });

        autoCompleteTextView5 = findViewById(R.id.auto_complete_text5);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_items,items);
        autoCompleteTextView5.setAdapter(adapterItems);
        autoCompleteTextView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Items: "+item, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkRadioButton(View view)
    {

        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);

        Toast.makeText(Home.this,"Title :  "+radioButton.getText(),Toast.LENGTH_LONG).show();
    }


    public void expandBuyerDetails(View view)
    {
        int v = (editFullName.getVisibility() == View.GONE) ? view.VISIBLE: view.GONE;
        TransitionManager.beginDelayedTransition(buyerDetailsLayour,new AutoTransition());
        mr.setVisibility(v);
        ms.setVisibility(v);
        editFullName.setVisibility(v);
        editMobileNumber.setVisibility(v);
    }

    public void expandProductDetails(View view) {

        //Below code is to minimise Tenent Details
        int i = (editFullName.getVisibility() == View.GONE) ? view.VISIBLE: view.GONE;
        TransitionManager.beginDelayedTransition(buyerDetailsLayour,new AutoTransition());
        mr.setVisibility(i);
        ms.setVisibility(i);
        editFullName.setVisibility(i);
        editMobileNumber.setVisibility(i);

        //Below code is to Maximise Owner Details
        int v = (product1.getVisibility() == View.VISIBLE) ? view.GONE: view.VISIBLE;
        TransitionManager.beginDelayedTransition(productDetailsLayour,new AutoTransition());

        product1.setVisibility(v);
        product2.setVisibility(v);
        product3.setVisibility(v);
        product4.setVisibility(v);
        product5.setVisibility(v);
        amount1.setVisibility(v);
        amount2.setVisibility(v);
        amount3.setVisibility(v);
        amount4.setVisibility(v);
        amount5.setVisibility(v);
        dropdown_menu1.setVisibility(v);
        dropdown_menu2.setVisibility(v);
        dropdown_menu3.setVisibility(v);
        dropdown_menu4.setVisibility(v);
        dropdown_menu5.setVisibility(v);


    }


    public void createPDF(View view) throws Exception {
        if(editFullName.getText().toString().length()==0||editMobileNumber.getText().toString().length()==0 || amount1.getText().toString().length()==0)
        {
            Toast.makeText(Home.this,"Fields are Empty",Toast.LENGTH_LONG).show();
        }
        else
        {
            PdfDocument myPdfDocument = new PdfDocument();
            Paint myPaint = new Paint();
            Paint invoiceNumber = new Paint();
            Paint date = new Paint();
            Paint fullName = new Paint();

            PdfDocument.PageInfo myPageInfo1 = new PdfDocument.PageInfo.Builder(1414,2000,1).create();
            PdfDocument.Page myPage1 = myPdfDocument.startPage(myPageInfo1);
            Canvas canvas = myPage1.getCanvas();
            canvas.drawBitmap(scaledbmp,0,0,myPaint);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            date.setTextAlign(Paint.Align.CENTER);
            date.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
            date.setTextSize(25);
            canvas.drawText(dtf.format(now),410,540,date);


            invoiceNumber.setTextAlign(Paint.Align.CENTER);
            invoiceNumber.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
            invoiceNumber.setTextSize(25);
            canvas.drawText(dtf.format(now).split(" ")[0].replace("/","")+dtf.format(now).split(" ")[1].replace(":",""),390,609,invoiceNumber);

            fullName.setTextAlign(Paint.Align.CENTER);
            fullName.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
            fullName.setTextSize(25);
            canvas.drawText(editFullName.getText().toString()+ " ("+editMobileNumber.getText().toString()+")",420,680,fullName);



            //First Product:

            if(editFullName.getText().toString().length()==0||editMobileNumber.getText().toString().length()==0 || amount1.getText().toString().length()==0)
            {
                Toast.makeText(Home.this,"Fields are Empty",Toast.LENGTH_LONG).show();
            }

            myPdfDocument.finishPage(myPage1);

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    EXTERNAL_STORAGE_PERMISSION_CODE);


            File file = new File(""+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)+"/OneEye_Invoice/"+dtf.format(now).split(" ")[0].replace("/","_") , "/"+editFullName.getText().toString()+"_"+dtf.format(now).split(" ")[1].replace(":","")+".pdf");

            try {

                myPdfDocument.writeTo(new FileOutputStream(file));


                //Uri uri = Uri.fromFile(file);
                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND);
                share.setType("application/pdf");
                share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+file));
                share.setPackage("com.whatsapp");
                startActivity(Intent.createChooser(share,"Share the file"));
            }
            catch (IOException e1) {
                e1.printStackTrace();

            }
            myPdfDocument.close();

            Toast.makeText(Home.this,"File Saved - Kindly Check Document Folder!!",Toast.LENGTH_LONG).show();
        }
    }

}