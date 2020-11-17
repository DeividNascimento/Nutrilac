package com.example.nutrilac.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutrilac.R;
import com.example.nutrilac.database.DadosOpenHelper;
import com.example.nutrilac.model.Alimento;
import com.example.nutrilac.model.Dieta;
import com.example.nutrilac.model.TempoAlimento;
import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.util.ArrayList;

public class TratorzinhoActivity extends AppCompatActivity {

    GridView gridView;
    public static final String TITULO_APPBAR = "Itens Selecionados";
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigatView;
    CustomAdapter customAdapter;
    ArrayList<Alimento>  aparecerao;
    ArrayList<String> alimentoSelec;
    ArrayList<TempoAlimento> radiobtn;
    ArrayList<Integer> radiobtnNovo;

    public String nomeDieta;
    public String nomeLote;

    int[] img;
    String[] nome;
    boolean[] exp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratorzinho);
        setTitle(TITULO_APPBAR);

        configuraMenu();

        radiobtn = new ArrayList<TempoAlimento>();
        Intent i= getIntent();
        nomeDieta = i.getStringExtra("nomeDieta");
        nomeLote = i.getStringExtra("nomeLote");
        nome=i.getStringArrayExtra("N");
        img=i.getIntArrayExtra("I");
        exp=i.getBooleanArrayExtra("E");
        radiobtn= (ArrayList<TempoAlimento>) i.getSerializableExtra("R");
        alimentoSelec = i.getStringArrayListExtra("alimentosSelecionados");
        aparecerao = alimentos();
        TextView mensagem_tratorzinho_vazio = findViewById(R.id.tratorzinho_msg_trator_vazio);

        if(alimentoSelec.size()==0){
            mensagem_tratorzinho_vazio.setVisibility(View.VISIBLE);
        }
        else {
            mensagem_tratorzinho_vazio.setVisibility(View.INVISIBLE);
        }

        gridView = findViewById(R.id.grid_view_trt);
        customAdapter = new CustomAdapter(aparecerao,getApplicationContext());
        gridView.setAdapter(customAdapter);

        Button btnOk = findViewById(R.id.btnCarrinho);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DadosOpenHelper meuBD = new DadosOpenHelper(TratorzinhoActivity.this);
                Dieta dieta = new Dieta();
                dieta.setNome(nomeDieta);
                dieta.setId_lote(0);
                dieta.setNomeLote(nomeLote);
                meuBD.adicionarDieta(dieta);

                Intent intent = new Intent(TratorzinhoActivity.this,ListaDietasActivity.class);
                startActivity(intent);
            }
        });

        Button btnVoltar = findViewById(R.id.btnCarrinho_continuarAdicionado);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TratorzinhoActivity.this,MenuAlimentoSelecionadoActivity.class);
                intent.putExtra("A", alimentoSelec);
                intent.putExtra("R", radiobtn);
                intent.putExtra("nomeDieta",nomeDieta);
                intent.putExtra("nomeLote",nomeLote);
                startActivity(intent);
            }
        });

    }


    public ArrayList<Alimento> alimentos(){
        ArrayList<Alimento> alimentos = new ArrayList<>();
        for (int i = 0; i < nome.length; i++) {
            for (int j = 0; j < alimentoSelec.size(); j++) {
                if (nome[i].equals(alimentoSelec.get(j))) {
                    Alimento a =new Alimento(nome[i],img[i],exp[i]);
                    alimentos.add(a);
                }
            }
        }
        return alimentos;
    }

    public class CustomAdapter extends BaseAdapter implements Filterable {
        private Context context;
        private LayoutInflater layoutInflater;
        SparseBooleanArray mSparseBooleanArray;
        ArrayList<Alimento> alimentos;
        ArrayList<Alimento> alimentosFiltrados;
        CustomFilter filter;

        public CustomAdapter(ArrayList<Alimento> alimentos, Context context) {
            this.alimentos = alimentos;
            this.alimentosFiltrados = alimentos;
            this.context = context;
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            mSparseBooleanArray = new SparseBooleanArray();
        }


        @Override
        public int getCount() {
            return alimentos.size();
        }

        @Override
        public Object getItem(int position) {
            return alimentos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return alimentos.indexOf(getItem(position));
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = layoutInflater.inflate(R.layout.item_tratorzinho, viewGroup, false);
            }
            final RadioGroup opcao = view.findViewById(R.id.listagem_volumosos_radio_group);
            ImageButton btnExc = view.findViewById(R.id.btnExcluir);
            TextView name = view.findViewById(R.id.lista_carrinho_nome);
            TextView preco = view.findViewById(R.id.lista_carrinho_preco);
            ImageView imageView = view.findViewById(R.id.lista_carrinho_imagem);
            final CheckBox checkBox = view.findViewById(R.id.checkbox_carrinho_opcao3);
            final ConstraintLayout mostraConteudoExtra = view.findViewById(R.id.constraint_expansivel);
            checkBox.setTag(position);
            checkBox.setVisibility(View.INVISIBLE);

            final Alimento alim = (Alimento) getItem(position);

            checkBox.setOnCheckedChangeListener(null); //important
            checkBox.setChecked(alim.isChecked());
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    alim.setChecked(isChecked);
                }
            });

            tratamentoDosAlimentos(alimentos.get(position).getNome(), view);

            if (alimentos.get(position).isExpansivel() == true) {
                mostraConteudoExtra.setVisibility(View.VISIBLE);
            } else {
                mostraConteudoExtra.setVisibility(View.GONE);
            }

            if (alimentos.get(position).isExpansivel()) {
                for (int i=0;i<aparecerao.size();i++){
                    for (int j=0;j<radiobtn.size();j++){
                        if (radiobtn.get(j).getNome().equals(aparecerao.get(i).getNome())){
                            opcao.check(radiobtn.get(j).getId());
                        }
                    }
                }
                if (opcao.getCheckedRadioButtonId() == 0) {
                    radiobtnNovo.add(opcao.getCheckedRadioButtonId());
                }
            }


            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBox.isChecked()) {
                        alimentos.get(position).setChecked(false);
                        checkBox.setChecked(false);
                        mostraConteudoExtra.setVisibility(View.GONE);


                    } else {
                        checkBox.setChecked(true);
                        alimentos.get(position).setChecked(true);
                        if (alimentos.get(position).isExpansivel()) {
                            mostraConteudoExtra.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });

            btnExc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int j ;
                    for(int i = 0 ; i<aparecerao.size();i++){
                        for (int k=0;k<alimentoSelec.size();k++){
                            if (alimentoSelec.get(k).equals(aparecerao.get(i).getNome())){
                                alimentoSelec.remove(alimentoSelec.get(k));
                            }
                        }
                        if (aparecerao.get(i).getNome().equals(alimentos.get(position).getNome())){
                            j =  i;
                            aparecerao.remove(j);
                        }
                    }
                    customAdapter = new CustomAdapter(aparecerao,getApplicationContext());
                    gridView.setAdapter(customAdapter);
                }
            });

            name.setText(alimentos.get(position).getNome());
            preco.setText("R$ XX,XX");
            imageView.setImageResource(alimentos.get(position).getImg());
            return view;
        }

        private void tratamentoDosAlimentos(String nomeImagen, View view) {
            RadioButton opcao1 = view.findViewById(R.id.listagem_volumosos_radio1);
            RadioButton opcao2 = view.findViewById(R.id.listagem_volumosos_radio2);
            RadioButton opcao3 = view.findViewById(R.id.listagem_volumosos_radio3);
            RadioButton opcao4 = view.findViewById(R.id.listagem_volumosos_radio4);
            RadioButton opcao5 = view.findViewById(R.id.listagem_volumosos_radio5);
            RadioButton opcao6 = view.findViewById(R.id.listagem_volumosos_radio6);
            RadioButton opcao7 = view.findViewById(R.id.listagem_volumosos_radio7);
            RadioButton opcao8 = view.findViewById(R.id.listagem_volumosos_radio8);
            RadioButton opcao9 = view.findViewById(R.id.listagem_volumosos_radio9);

            opcao1.setVisibility(View.VISIBLE);
            opcao2.setVisibility(View.VISIBLE);
            opcao3.setVisibility(View.VISIBLE);
            opcao4.setVisibility(View.VISIBLE);
            opcao5.setVisibility(View.VISIBLE);
            opcao6.setVisibility(View.VISIBLE);
            opcao7.setVisibility(View.VISIBLE);
            opcao8.setVisibility(View.VISIBLE);
            opcao9.setVisibility(View.VISIBLE);

            if (nomeImagen.equals("Braquiária Brizantha")) {
                opcao1.setText("Braquiária Brizantha");
                opcao2.setText("Braquiária Brizantha (46 a 60 dias)");
                opcao3.setText("Braquiária Brizantha (61 a 90 dias)");
                opcao4.setText("Braquiária Brizantha (91 a 120 dias)");
                opcao5.setVisibility(View.GONE);
                opcao6.setVisibility(View.GONE);
                opcao7.setVisibility(View.GONE);
                opcao8.setVisibility(View.GONE);
                opcao9.setVisibility(View.GONE);
            } else if (nomeImagen.equals("Braquiária Brizantha Marandu")) {
                opcao1.setText("Braquiária Brizantha Marandu");
                opcao2.setText("Braquiária Brizantha Marandu (61 a 90 dias)");
                opcao3.setText("Braquiária Brizantha Marandu Outono");
                opcao4.setText("Braquiária Brizantha Marandu Primavera");
                opcao5.setText("Braquiária Brizantha Marandu Verão");
                opcao6.setVisibility(View.GONE);
                opcao7.setVisibility(View.GONE);
                opcao8.setVisibility(View.GONE);
                opcao9.setVisibility(View.GONE);
            } else if (nomeImagen.equals("Braquiária Brizantha MG4")) {
                opcao1.setText("Braquiária Brizantha MG4");
                opcao2.setText("Braquiária Brizantha MG4 (61 a 90 dias)");
                opcao3.setVisibility(View.GONE);
                opcao4.setVisibility(View.GONE);
                opcao5.setVisibility(View.GONE);
                opcao6.setVisibility(View.GONE);
                opcao7.setVisibility(View.GONE);
                opcao8.setVisibility(View.GONE);
                opcao9.setVisibility(View.GONE);
            } else if (nomeImagen.equals("Braquiária Decumbens")) {
                opcao1.setText("Braquiária Decumbens");
                opcao2.setText("Braquiária Decumbens (26 a 30 dias)");
                opcao3.setText("Braquiária Decumbens (46 a 60 dias)");
                opcao4.setText("Braquiária Decumbens (61 a 90 dias)");
                opcao5.setText("Braquiária Decumbens (91 a 120 dias)");
                opcao6.setText("Braquiária Decumbens (121 a 150 dias)");
                opcao7.setText("Braquiária Decumbens Águas-Seca");
                opcao8.setText("Braquiária Decumbens Outubro");
                opcao9.setText("Braquiária Decumbens Período Seco");
            } else if (nomeImagen.equals("Capim Colonião")) {
                opcao1.setText("Capim Colonião Outono");
                opcao2.setText("Capim Colonião Primavera");
                opcao3.setText("Capim Colonião Verão");
                opcao4.setVisibility(View.GONE);
                opcao5.setVisibility(View.GONE);
                opcao6.setVisibility(View.GONE);
                opcao7.setVisibility(View.GONE);
                opcao8.setVisibility(View.GONE);
                opcao9.setVisibility(View.GONE);
            } else if (nomeImagen.equals("Capim Elefante")) {
                opcao1.setText("Capim Elefante");
                opcao2.setText("Capim Elefante (26 a 30 dias)");
                opcao3.setText("Capim Elefante (36 a 45 dias)");
                opcao4.setVisibility(View.GONE);
                opcao5.setVisibility(View.GONE);
                opcao6.setVisibility(View.GONE);
                opcao7.setVisibility(View.GONE);
                opcao8.setVisibility(View.GONE);
                opcao9.setVisibility(View.GONE);
            } else if (nomeImagen.equals("Capim Elefante Cameroon")) {
                opcao1.setText("Capim Elefante Cameroon");
                opcao2.setText("Capim Elefante Cameroon (61 a 90 dias)");
                opcao3.setVisibility(View.GONE);
                opcao4.setVisibility(View.GONE);
                opcao5.setVisibility(View.GONE);
                opcao6.setVisibility(View.GONE);
                opcao7.setVisibility(View.GONE);
                opcao8.setVisibility(View.GONE);
                opcao9.setVisibility(View.GONE);
            } else if (nomeImagen.equals("Capim Gordura")) {
                opcao1.setText("Capim Gordura");
                opcao2.setText("Capim Gordura (2 a 30 dias)");
                opcao3.setText("Capim Gordura (46 a 60 dias)");
                opcao4.setText("Capim Gordura (61 a 90 dias)");
                opcao5.setText("Capim Gordura (91 a 120 dias)");
                opcao6.setText("Capim Gordura (121 a 150 dias)");
                opcao7.setVisibility(View.GONE);
                opcao8.setVisibility(View.GONE);
                opcao9.setVisibility(View.GONE);
            } else if (nomeImagen.equals("Capim Mombaça")) {
                opcao1.setText("Capim Mombaça");
                opcao2.setText("Capim Mombaça (61 a 90 dias)");
                opcao3.setVisibility(View.GONE);
                opcao4.setVisibility(View.GONE);
                opcao5.setVisibility(View.GONE);
                opcao6.setVisibility(View.GONE);
                opcao7.setVisibility(View.GONE);
                opcao8.setVisibility(View.GONE);
                opcao9.setVisibility(View.GONE);
            } else if (nomeImagen.equals("Capim Tanzânia")) {
                opcao1.setText("Capim Tanzânia");
                opcao2.setText("Capim Tanzânia (61 a 90 dias)");
                opcao3.setVisibility(View.GONE);
                opcao4.setVisibility(View.GONE);
                opcao5.setVisibility(View.GONE);
                opcao6.setVisibility(View.GONE);
                opcao7.setVisibility(View.GONE);
                opcao8.setVisibility(View.GONE);
                opcao9.setVisibility(View.GONE);
            } else if (nomeImagen.equals("Capim Tifton")) {
                opcao1.setText("Capim Tifton 68");
                opcao2.setText("Capim Tifton 85");
                opcao3.setText("Capim Tifton 85 (26 a 30 dias)");
                opcao4.setText("Capim Tifton 85 (46 a 60 dias)");
                opcao5.setVisibility(View.GONE);
                opcao6.setVisibility(View.GONE);
                opcao7.setVisibility(View.GONE);
                opcao8.setVisibility(View.GONE);
                opcao9.setVisibility(View.GONE);
            }else if(nomeImagen.equals("Sorgo Silagem")){
                opcao1.setVisibility(View.VISIBLE);
                opcao2.setVisibility(View.VISIBLE);
                opcao3.setVisibility(View.VISIBLE);
                opcao4.setVisibility(View.GONE);
                opcao5.setVisibility(View.GONE);
                opcao6.setVisibility(View.GONE);
                opcao7.setVisibility(View.GONE);
                opcao8.setVisibility(View.GONE);
                opcao9.setVisibility(View.GONE);
                opcao1.setText("Sorgo Silagem");
                opcao2.setText("Sorgo Silagem Com Tanino");
                opcao3.setText("Sorgo Silagem Sem Tanino");
            }else if(nomeImagen.equals("Cana-de-Açúcar Silagem")){
                opcao1.setVisibility(View.VISIBLE);
                opcao2.setVisibility(View.VISIBLE);
                opcao3.setVisibility(View.VISIBLE);
                opcao4.setVisibility(View.GONE);
                opcao5.setVisibility(View.GONE);
                opcao6.setVisibility(View.GONE);
                opcao7.setVisibility(View.GONE);
                opcao8.setVisibility(View.GONE);
                opcao9.setVisibility(View.GONE);
                opcao1.setText("Cana-de-Açúcar Silagem");
                opcao2.setText("Cana-de-Açúcar Silagem (0 a 0.5 % CaO)");
                opcao3.setVisibility(View.GONE);
            }else if (nomeImagen.equals("Farelo de Algodão")){
                opcao1.setText("Farelo de Algodão 28% PB");
                opcao2.setText("Farelo de Algodão 38% PB");
                opcao3.setText("Farelo de Algodão 42% PB");
                opcao4.setVisibility(View.GONE);
                opcao5.setVisibility(View.GONE);
                opcao6.setVisibility(View.GONE);
                opcao7.setVisibility(View.GONE);
                opcao8.setVisibility(View.GONE);
                opcao9.setVisibility(View.GONE);
            }
        }

        CompoundButton.OnCheckedChangeListener mCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                mSparseBooleanArray.put((Integer) buttonView.getTag(), isChecked);
            }
        };

        @Override
        public Filter getFilter() {
            if (filter == null) {
                filter = new TratorzinhoActivity.CustomAdapter.CustomFilter();
            }
            return filter;
        }

        public class CustomFilter extends Filter {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint != null && constraint.length() > 0) {
                    constraint = constraint.toString().toUpperCase();
                    ArrayList<Alimento> filtros = new ArrayList<Alimento>();
                    results.count = filtros.size();
                    results.values = filtros;
                } else {
                    results.count = alimentosFiltrados.size();
                    results.values = alimentosFiltrados;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                alimentos = (ArrayList<Alimento>) results.values;
                notifyDataSetChanged();
            }
        }
    }


    private void configuraMenu() {
        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutCarrinho);
        mToggle = new ActionBarDrawerToggle(this, mdrawerLayout, R.string.open, R.string.close);
        mdrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigatView = (NavigationView) findViewById(R.id.navView);
        navigatView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_inicio:
                        Intent intentInicio = new Intent(TratorzinhoActivity.this, HomeActivity.class);
                        startActivity(intentInicio);
                        break;
                    case R.id.menu_relatorio:
                    case R.id.menu_minhaconta:
                    case R.id.menu_contato:
                    case R.id.menu_ajuda:
                        Toast.makeText(TratorzinhoActivity.this, "Essa tela ainda será desenvolvida", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_sairDaConta:
                        Intent intent = new Intent(TratorzinhoActivity.this, TelaPrimeiroAcessoActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}