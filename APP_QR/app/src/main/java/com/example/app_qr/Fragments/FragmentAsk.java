package com.example.app_qr.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app_qr.Ask_activity;
import com.example.app_qr.Auxiliar.AskAuxiliar;
import com.example.app_qr.GameOver;
import com.example.app_qr.Models.Ask;
import com.example.app_qr.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

import uk.co.senab.photoview.PhotoViewAttacher;

public class FragmentAsk extends Fragment implements View.OnClickListener {
    ArrayList<Ask> asksList = new ArrayList<>();

    FloatingActionButton btn;
    TextView question;
    RadioButton req0;
    RadioButton req1;
    RadioButton req2;
    RadioButton req3;
    ImageView imgReq;
    boolean correctResponse = false;
    Ask ask = new Ask();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ask, container ,false);
        btn = view.findViewById(R.id.btnReq);
        question = view.findViewById(R.id.question);
        req0 = view.findViewById(R.id.req0);
        req1 = view.findViewById(R.id.req1);
        req2 = view.findViewById(R.id.req2);
        req3 = view.findViewById(R.id.req3);
        imgReq = view.findViewById(R.id.imgReq);

        req0.setOnClickListener(this);
        req1.setOnClickListener(this);
        req2.setOnClickListener(this);
        req3.setOnClickListener(this);

        asks();
        try {
            ask = getAsk();
            generateAsk(ask);
        }catch (Exception e){
            Intent intent = new Intent(getContext(), GameOver.class);
            startActivity(intent);
        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numberCode = ""+ AskAuxiliar.getNumberCode();
                Log.d("response", "onClick: onclick" + correctResponse);
                if(correctResponse){
                    //añadir numero a la lista
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("¡¡¡CORRECTO!!!");
                    builder.setMessage("Código : " +numberCode);

                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            correctResponse = false;
                            AskAuxiliar.datos.add(numberCode);
                            getActivity().finish();//----------------------------------
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();


                }else {
                    correctResponse = false;
                    Log.d("alert", "onClick: eeerteeee");
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Respuesta incorrecta");
                    builder.setMessage("Siguiente pregunta");
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ask = getAsk();
                            generateAsk(ask);

                        }
                    });
                    builder.create();
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

            }

        });
        return view;
    }

    @Override
    public void onClick(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        String textoView = "";

        switch(view.getId()) {
            case R.id.req0:
            case R.id.req1:
            case R.id.req2:
            case R.id.req3:
                if (checked){

                    textoView = (String) ((RadioButton) view).getText();
                    Log.d("response","-----** "+textoView);

                }

        }

        Log.d("response","texts " + textoView + " -- " + ask.getReqCorrect());

        if(textoView.equals(ask.getReqCorrect())){
            correctResponse = true;
        }
    }

    private void generateAsk(Ask ask) {

        String questionText = ask.getAsk();
        String tex0 = ask.getReq0();
        String tex1 = ask.getReq1();
        String tex2 = ask.getReq2();
        String tex3 = ask.getReq3();
        int img = ask.getImgReq();

        question.setText(questionText);
        req0.setText(tex0);
        req1.setText(tex1);
        req2.setText(tex2);
        req3.setText(tex3);
        imgReq.setImageResource(img);

        req0.setChecked(false);
        req1.setChecked(false);
        req2.setChecked(false);
        req3.setChecked(false);


    }

    private void asks() {
        asksList.add(new Ask("¿Qué nueva forma geométrica presente en las células epiteliales, fue descubierta por la Universidad de Sevilla en 2018?", "Prisma triangular", "Prismoide", "Escutoide", "Icosaedro", "Escutoide"));

        asksList.add(new Ask("¿Qué arquitectos ganaron el concurso internacional de ideas convocado en el año 2003 para la construcción del Museo Íbero de Jaén?","Rafael Moneo","EDDEA","SOLID ARQUITECTURA (Álvaro Soto y Javier Maroto)","Santiago Calatrava","SOLID ARQUITECTURA (Álvaro Soto y Javier Maroto)"));

        asksList.add(new Ask("¿Sobre qué edificio preexistente se levantó el Museo Íbero de Jaén?","Casa del gobernador","Estación de autobuses","Hospital de San Juan de Dios","Antigua prisión provincial de Jaén","Antigua prisión provincial de Jaén"));

        asksList.add(new Ask("¿Cuál es la superficie construida del Museo Íbero de Jaén?","11152 m2","15112 m2","52111 m2","21115 m2","11152 m2"));

        asksList.add(new Ask("¿Cuál fue el coste de la construcción del Museo Íbero de Jaén?","104 029 305,28 €","24 689 221,28 €","35 000 000,01 €","6 999 999,96 €","24 689 221,28 €"));

        asksList.add(new Ask("¿En qué año fue inaugurado el Museo Íbero de Jaén?","2017","2015","2018","2016","2017"));

        asksList.add(new Ask("Teresa de Torres fue  la esposa del afamado Condestable Lucas de Iranzo y, cuando ésta  enviudó, gobernó la ciudad de Jaén durante:","El mismo tiempo que su marido","Más años que su marido","Menos años que su marido","Nunca llegó a gobernar"," Más años que su marido"));

        asksList.add(new Ask("Jaén es la capital mundial del:","Turismo interior","Aceite de oliva","Sector industrial","Sector servicios"," Aceite de oliva"));

        asksList.add(new Ask("La provincia de Jaén se caracteriza por:","Tener los baños árabes más grandes y mejor conservados de Europa"," Contar con la mayor cantidad de castillos por m2 de Europa","Ser la provincia española con una mayor superficie natural protegida","Todas son ciertas","Todas son ciertas"));

        asksList.add(new Ask("En Andalucía, la agricultura supone:","El 76,4% de la producción"," El 26,4% de la producción"," El 18% de la producción"," El 5,6% de la producción"," El 5,6% de la producción"));

        asksList.add(new Ask("El sistema operativo:"," Es una placa","Es software","Hardware","Ninguna","Es software"));

        asksList.add(new Ask("¿Cuál dispositivo es un periférico?","Microprocesador","Memoria RAM","Placa Base","Impresora","Impresora"));

        asksList.add(new Ask("¿Cuál es el sistema de numeración empleado por los ordenadores?","El sistema binario","El sistema decimal","El sistema hexadecimal.","El sistema numérico","El sistema binario"));

        asksList.add(new Ask("De los siguientes descuentos en un establecimiento ¿cuál escogerías?","La segunda unidad a mitad de precio","3X2","33% ","La semana sin IVA","33% "));

        asksList.add(new Ask("Se cree que los primeros habitantes se establecieron en esta histórica ciudad en 1100 a. C. Hogar de la Armada española desde el siglo XVIII, fue fundada por los fenicios.","Motril","Cádiz","Almuñécar","Málaga","Cádiz"));

        asksList.add(new Ask("Cual de las siguientes obras pertenece a Murillo","Niños comiendo uvas y melón","El triunfo de Baco","El quitasol","Inmaculada Concepción","Niños comiendo uvas y melón"));

        asksList.add(new Ask("¿Quién compuso la letra del himno de Andalucía? ¿Y la melodía?","Blas Infante compuso la letra y José del Castillo Díaz la melodía.","Ninguna de las anteriores es correcta.","Blas Infante compuso la letra y Emilio Cebrián Ruiz la melodía.","José del Castillo compuso la letra y Blas Infante la melodía.","Blas Infante compuso la letra y José del Castillo Díaz la melodía."));

        asksList.add(new Ask("¿Cuál es el nombre de la bandera de Andalucía?","Ikurriña","Señera","Arbonaida","Almudaina","Arbonaida"));

        asksList.add(new Ask("¿Qué héroe mitológico aparece en el escudo de Andalucía?","Hermes","Hércules","Hades","Helios","Hércules"));

        asksList.add(new Ask("¿Por qué celebramos el 28 de febrero el día de Andalucía?","Porque el 28 de febrero de 1980 se formaron las ocho provincias que conforman la comunidad autónoma de Andalucía"," Porque el 28 de febrero de 1980 se celebró la Asamblea de Ronda por la que se formó la comunidad autónoma de Andalucía.","Porque el 28 de febrero de 1980 murió Blas Infante, padre del regionalismo andaluz."," Porque el 28 de febrero de 1980 se celebró un referéndum en el que los andaluces aceptaron la formación de la comunidad autónoma de Andalucía."," Porque el 28 de febrero de 1980 se celebró un referéndum en el que los andaluces aceptaron la formación de la comunidad autónoma de Andalucía."));

        asksList.add(new Ask(" La primera constitución española se realizó en Cádiz. ¿En qué año? ¿Cómo se le conoce?","En 1712 y se le conoce como La Paca.","En 1812 y se le conoce como La Pepa.","En 1812 y se le conoce como La Pepita","En 1912 y se le conoce como La Paquita.","En 1812 y se le conoce como La Pepa."));

        asksList.add(new Ask("¿En qué año se aprobó el estatuto de autonomía de Andalucía y en qué año se reformó?","Se aprobó en 1980 y se reformó en 2004.","Se aprobó en 1982 y se reformó en 2006.","Se aprobó en 1981 y se reformó en 2005.","Se aprobó en 1981 y se reformó en 2007","Se aprobó en 1981 y se reformó en 2007"));

        asksList.add(new Ask("¿Cuál es el puerto de España más grande en tráfico total de mercancías?","Algeciras","Barcelona","Valencia","Sevilla","Algeciras"));

        asksList.add(new Ask("¿En qué olimpiada consiguió el oro en bádminton Carolina Marín? ","Río 2016","Londes 2012","Sevilla 2016","Madrid 2020","Río 2016"));

        asksList.add(new Ask("¿Qué provincia española es la principal exportadora de tomates?","Granada","Almería","Murcia","Alicante","Almería"));

        asksList.add(new Ask("Petites créatures bleues logeant dans un village champignon au milieu d'une vaste forêt.","Fábrica de luz","Fábrica de hojalata","Fábrica de tapices","Fábrica de chocolate","Fábrica de hojalata"));

        asksList.add(new Ask("Medalla de Andalucía más reciente.","Carmen Linares","Juanito Valderrama","Joaquín Sabina","Raphael","Raphael"));

        asksList.add(new Ask("¿A quién relacionas con unicornio?","Juan Eslava Galán","Erika Martínez","Antonio Muñoz Molina","Patricia García-Rojo","Juan Eslava Galán"));

        asksList.add(new Ask("¿En qué importante ciudad se constituyó la Junta Suprema de Andalucía en 1835?","Linares","Jaén","Baeza","Andújar","Andújar"));

        asksList.add(new Ask("¿Qué texto dice en su artículo 1º “Andalucía es soberana y Autónoma; se organiza en una democracia republicana representativa, y no recibe su poder de ninguna autoridad exterior...”?","Estatuto de Autonomía de 2007","Estatuto de Autonomía de 1981","Constitución Federal de Andalucía de 1883","Manifiesto andalucista de Córdoba de 1919","Constitución Federal de Andalucía de 1883"));

        asksList.add(new Ask("Nombre del único rey de Tartesos del que se tienen referencias históricas...","Marco Antonio","Juan Antonio","Argantonio","Ninguno de los tres anteriores","Argantonio"));

        asksList.add(new Ask("En Al Ándalus los distintos territorios se dividían durante el periodo califal...","Por vertientes hidrográficas.","En comunidades autónomas.","Los territorios del norte en torno a ciudades importantes y los del sur se dividían en coras.","Todos se dividían en torno a ciudades importantes.","Los territorios del norte en torno a ciudades importantes y los del sur se dividían en coras."));

        asksList.add(new Ask("Algunas obras de Blas Infante son","Ideal Andaluz","La dictadura pedagógica","Orígenes de lo flamenco y secreto del cante jondo","Las tres respuestas anteriores son correctas","Las tres respuestas anteriores son correctas"));

        asksList.add(new Ask("Estados miembros de la Unión Europea con menor extensión que Andalucía son...","Austria, Bélgica, Croacia, Chequia e Irlanda, Luxemburgo y Malta.","Chipre, Dinamarca, Eslovaquia, Eslovenia, Estonia, Holanda.","Noruega, Suecia, Reino Unido y Polonia.","La a y la b son correctas, la c no.","La a y la b son correctas, la c no."));


        asksList.add(new Ask("Lienzo de Velázquez que lleva por título...","Las Infantas","Las Mayoralas","Las Damiselas","Las Meninas","Las Meninas", R.drawable.meninas));

    }

    private Ask getAsk() {

        Random randomNumber = new Random();
        int askNumber = randomNumber.nextInt(asksList.size());
        Ask randomAsk = asksList.get(askNumber);
        Log.d("randomNumber","getAask()" + askNumber);
        Log.d("randomNumber","getAask()" + askNumber + "  " +randomAsk.toString());
        Log.d("randomNumber","getAask()" + (AskAuxiliar.askDoneList.contains(randomAsk)));
        Log.d("randomNumber","getAask()size" + asksList.size());
        Log.d("randomNumber","getAask()size" + AskAuxiliar.askDoneList.size());

        while(AskAuxiliar.askDoneList.contains(randomAsk)){

            if(asksList.size() == AskAuxiliar.askDoneList.size()){
                Intent intent = new Intent(getContext(), GameOver.class);
                startActivity(intent);
                break;
            }

            askNumber = randomNumber.nextInt(asksList.size());
            randomAsk = asksList.get(askNumber);
            Log.d("randomNumber","getAask() --" + askNumber + "  " +randomAsk.toString());
        }
        AskAuxiliar.askDoneList.add(randomAsk);
        Log.d("randomNumber","getAask()size" + asksList.size());
        Log.d("randomNumber","getAask()size" + AskAuxiliar.askDoneList.size());
        return randomAsk;

    }

}