package com.example.nutrilac.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;

import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.nutrilac.R;
import com.example.nutrilac.database.DadosOpenHelper;
import com.example.nutrilac.model.Alimento;
import com.example.nutrilac.model.Dieta;
import com.example.nutrilac.model.TempoAlimento;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MenuAlimentoSelecionadoActivity extends AppCompatActivity {


    String[] nomesMin = {"Calcário", "Cloreto de Cálcio", "Cloreto de Potássio", "Flor de Enxofre", "Fosfato Bicálcico",
            "Iodato de Potássio", "Óxido de Magnésio", "Sal Comum", "Selenito de Sódio", "Sulfato de Cobalto", "Sulfato de Cobre",
            "Sulfato de Manganês", "Sulfato de Zinco"};
    int[] imagensMin = {R.drawable.calcario, R.drawable.cloreto_calcio, R.drawable.cloreto_potassio, R.drawable.flor_de_enxofre, R.drawable.fosfato_bicalcico,
            R.drawable.iodato_potassio, R.drawable.oxido_magnesio, R.drawable.sal_comum, R.drawable.selenito_sodio, R.drawable.sulfato_cobalto, R.drawable.sulfato_cobre,
            R.drawable.sulfato_manganes, R.drawable.sulfato_zinco};
    boolean[] expansivelMin = {false, false, false, false, false, false, false, false, false, false, false, false, false};

    String[] nomesCon = {"Amiréia", "Caroço de Algodão", "Casca de Soja", "Citrus Polpa", "Farelo de Algodão", "Farelo de Amendoim", "Farelo de Girassol",
            "Farelo de Soja", "Farelo de Trigo", "Mandioca Raspa", "Milho Desintegrado com Palha e Sabugo", "Milho Espiga Silagem (Earlage)",
            "Milho Fubá", "Milho Grão Reidratado Silagem", "Milho Grão Úmido Silagem", "Resíduo de Cervejaria Úmido", "Soja em Grão",
            "Soja Grão Tostada", "Sorgo Grão", "Torta de Algodão", "Ureia"};

    int[] imagensCon = {R.drawable.amireia, R.drawable.caroco_algodao, R.drawable.casca_soja, R.drawable.citrus_polpa, R.drawable.algodao_farelo,
            R.drawable.farelo_amendoim, R.drawable.farelo_girassol,
            R.drawable.farelo_soja, R.drawable.trigo_farelo, R.drawable.mandioca_raspa, R.drawable.milho_desintegrado, R.drawable.milho_espiga_silagem,
            R.drawable.milho_fuba, R.drawable.milho_grao_reidratado_silagem, R.drawable.milho_grao_umido_silagem, R.drawable.residuo_cervejaria,
            R.drawable.soja_grao, R.drawable.soja_grao_tostado, R.drawable.sorgo_grao, R.drawable.algodao_torta, R.drawable.ureia};

    boolean[] expansivelCon = {false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};

    String[] nomesCap = {"Bagaço de Cana-de-Açúcar", "Braquiária Brizantha Marandu", "Braquiária Brizantha MG4", "Braquiária Brizantha Piatã (61 a 90 dias)",
            "Braquiária Brizantha Xaraes", "Braquiária Brizantha", "Braquiária Decumbens", "Braquiária Híbrida Mulato Outono", "Braquiária Humidícola",
            "Cana-de-açúcar", "Capim Coast Cross", "Capim Colonião", "Capim Elefante Anão (61 a 90 dias)",
            "Capim Elefante Cameroon", "Capim Elefante Paraíso (61 a 90 dias)",
            "Capim Elefante", "Capim Gordura", "Capim Massai (61 a 90 dias)", "Capim Mombaça", "Capim Setária (61 a 90 dias)",
            "Capim Tanzânia", "Capim Tifton", "Milheto", "Sorgo Forrageiro"};

    boolean[] menuexpansivelCap = {false, true, true, false,
            false, true, true, false, false, false, false, true, false,
            true, false,
            true, true, false, true, false,
            true, true, false, false};

    int[] imagensCap = {R.drawable.cana_de_acucar, R.drawable.braquiaria_marandu, R.drawable.capim_mg4, R.drawable.capim_piata,
            R.drawable.braquiaria_xaraes, R.drawable.brachiaria_brizantha, R.drawable.brachiaria_decumbens, R.drawable.braquiaria_hibrida_mulato, R.drawable.capim_humidicola,
            R.drawable.cana_de_acucar, R.drawable.capim_coast_cross, R.drawable.capim_coloniao, R.drawable.capim_elefante_anao,
            R.drawable.capim_elefante_cameroon, R.drawable.capim_elefante_paraiso,
            R.drawable.capim_elefante, R.drawable.capim_gordura, R.drawable.capim_massai, R.drawable.capim_mombaca, R.drawable.capim_setaria,
            R.drawable.capim_tanzania, R.drawable.capim_tifton, R.drawable.milheto,
            R.drawable.sorgo_forrageiro};

    String[] nomesFen = {"Capim Braquiária Brizantha Feno", "Capim Braquiária Coast Cross Feno",
            "Capim Braquiária Decumbens Feno", "Capim Elefante Feno", "Capim Tifton 85 Feno"};

    boolean[] menuexpansivelFen = {false, false, false, false, false};

    int[] imagensFen = {R.drawable.campim_braquiaria_feno, R.drawable.feno_coast_cross,
            R.drawable.feno_brizantha, R.drawable.capim_elefante_feno, R.drawable.capim_tifton_feno};

    String[] nomesSil = {"Cana-de-Açúcar Silagem", "Capim Elefante Silagem", "Capim Mombaça Silagem", "Estilosantes Silagem", "Milho sem Espiga Silagem", "Milho Silagem",
            "Sorgo Forrageiro Silagem", "Sorgo Silagem"};

    boolean[] menuexpansivelSil = {true, false, false, false, false, false, false, true};

    int[] imagensSil = {R.drawable.cana_acucar_silagem, R.drawable.capim_elefante_silagem, R.drawable.capim_mombaca_silagem, R.drawable.capim_estilosantes,
            R.drawable.milho_sem_espiga_silagem, R.drawable.milho_silagem, R.drawable.sorgo_forrageiro_silagem, R.drawable.sorgo_silagem};

    String[] nomes = {"Calcário", "Cloreto de Cálcio", "Cloreto de Potássio", "Flor de Enxofre", "Fosfato Bicálcico",
            "Iodato de Potássio", "Óxido de Magnésio", "Sal Comum", "Selenito de Sódio", "Sulfato de Cobalto", "Sulfato de Cobre",
            "Sulfato de Manganês", "Sulfato de Zinco", "Amiréia", "Caroço de Algodão", "Casca de Soja", "Citrus Polpa", "Farelo de Algodão", "Farelo de Amendoim", "Farelo de Girassol",
            "Farelo de Soja", "Farelo de Trigo", "Mandioca Raspa", "Milho Desintegrado com Palha e Sabugo", "Milho Espiga Silagem (Earlage)",
            "Milho Fubá", "Milho Grão Reidratado Silagem", "Milho Grão Úmido Silagem", "Resíduo de Cervejaria Úmido", "Soja em Grão",
            "Soja Grão Tostada", "Sorgo Grão", "Torta de Algodão", "Ureia", "Bagaço de Cana-de-Açúcar", "Braquiária Brizantha Marandu", "Braquiária Brizantha MG4", "Braquiária Brizantha Piatã (61 a 90 dias)",
            "Braquiária Brizantha Xaraes", "Braquiária Brizantha", "Braquiária Decumbens", "Braquiária Híbrida Mulato Outono", "Braquiária Humidícola",
            "Cana-de-açúcar", "Capim Coast Cross", "Capim Colonião", "Capim Elefante Anão (61 a 90 dias)",
            "Capim Elefante Cameroon", "Capim Elefante Paraíso (61 a 90 dias)",
            "Capim Elefante", "Capim Gordura", "Capim Massai (61 a 90 dias)", "Capim Mombaça", "Capim Setária (61 a 90 dias)",
            "Capim Tanzânia", "Capim Tifton", "Milheto", "Sorgo Forrageiro", "Capim Braquiária Brizantha Feno", "Capim Braquiária Coast Cross Feno",
            "Capim Braquiária Decumbens Feno", "Capim Elefante Feno", "Capim Tifton 85 Feno", "Cana-de-Açúcar Silagem", "Capim Elefante Silagem", "Capim Mombaça Silagem", "Estilosantes Silagem", "Milho sem Espiga Silagem", "Milho Silagem",
            "Sorgo Forrageiro Silagem", "Sorgo Silagem"};
    int[] imagens = {R.drawable.calcario, R.drawable.cloreto_calcio, R.drawable.cloreto_potassio, R.drawable.flor_de_enxofre, R.drawable.fosfato_bicalcico,
            R.drawable.iodato_potassio, R.drawable.oxido_magnesio, R.drawable.sal_comum, R.drawable.selenito_sodio, R.drawable.sulfato_cobalto, R.drawable.sulfato_cobre,
            R.drawable.sulfato_manganes, R.drawable.sulfato_zinco, R.drawable.amireia, R.drawable.caroco_algodao, R.drawable.casca_soja, R.drawable.citrus_polpa, R.drawable.algodao_farelo,
            R.drawable.farelo_amendoim, R.drawable.farelo_girassol,
            R.drawable.farelo_soja, R.drawable.trigo_farelo, R.drawable.mandioca_raspa, R.drawable.milho_desintegrado, R.drawable.milho_espiga_silagem,
            R.drawable.milho_fuba, R.drawable.milho_grao_reidratado_silagem, R.drawable.milho_grao_umido_silagem, R.drawable.residuo_cervejaria,
            R.drawable.soja_grao, R.drawable.soja_grao_tostado, R.drawable.sorgo_grao, R.drawable.algodao_torta, R.drawable.ureia, R.drawable.cana_de_acucar, R.drawable.braquiaria_marandu, R.drawable.capim_mg4, R.drawable.capim_piata,
            R.drawable.braquiaria_xaraes, R.drawable.brachiaria_brizantha, R.drawable.brachiaria_decumbens, R.drawable.braquiaria_hibrida_mulato, R.drawable.capim_humidicola,
            R.drawable.cana_de_acucar, R.drawable.capim_coast_cross, R.drawable.capim_coloniao, R.drawable.capim_elefante_anao,
            R.drawable.capim_elefante_cameroon, R.drawable.capim_elefante_paraiso,
            R.drawable.capim_elefante, R.drawable.capim_gordura, R.drawable.capim_massai, R.drawable.capim_mombaca, R.drawable.capim_setaria,
            R.drawable.capim_tanzania, R.drawable.capim_tifton, R.drawable.milheto,
            R.drawable.sorgo_forrageiro, R.drawable.campim_braquiaria_feno, R.drawable.feno_coast_cross,
            R.drawable.feno_brizantha, R.drawable.capim_elefante_feno, R.drawable.capim_tifton_feno, R.drawable.cana_acucar_silagem, R.drawable.capim_elefante_silagem, R.drawable.capim_mombaca_silagem, R.drawable.capim_estilosantes,
            R.drawable.milho_sem_espiga_silagem, R.drawable.milho_silagem, R.drawable.sorgo_forrageiro_silagem, R.drawable.sorgo_silagem};
    boolean[] exp = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
            false, true, true, false,
            false, true, true, false, false, false, false, true, false,
            true, false,
            true, true, false, true, false,
            true, true, false, false, false, false, false, false, false, true, false, false, false, false, false, false, true};

    ArrayList<String> alimentSelec = new ArrayList<String>();
    ArrayList<Alimento> alimentoDef, alimento, listCompleta;
    GridView gridView;
    CustomAdapter customAdapter;

    public static final String TITULO_APPBAR = "Alimentos";
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigatView;
    private TabLayout tabLayout;
    private TabItem min, con, cap, sil, fen;
    private Button btnAdd;
    private Button finalizarDieta;

    ArrayList<TempoAlimento> radiobtnNome;

    public String nomeLote;
    public String nomeDieta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_alimento_selecionado);
        setTitle(TITULO_APPBAR);

        configuraMenu();
        valores();

        Intent i = getIntent();
        nomeDieta = i.getStringExtra("nome");
        nomeLote = i.getStringExtra("lote");

        gridView = findViewById(R.id.grid_view);
        listCompleta = listaCompleta();
        alimentoDef = getAlimentos(nomesMin, imagensMin, expansivelMin);
        customAdapter = new CustomAdapter(selecionaClasse(0), MenuAlimentoSelecionadoActivity.this);
        gridView.setAdapter(customAdapter);

        tabLayout = findViewById(R.id.tabLayoutMenu);
        min = findViewById(R.id.min);
        con = findViewById(R.id.con);
        cap = findViewById(R.id.cap);
        fen = findViewById(R.id.fen);
        sil = findViewById(R.id.sil);
        btnAdd = findViewById(R.id.btnAdicionarAlimentos);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent t = new Intent(MenuAlimentoSelecionadoActivity.this, TratorzinhoActivity.class);
                t.putExtra("N", nomes);
                t.putExtra("I", imagens);
                t.putExtra("E", exp);
                //t.putExtra("todos",  listCompleta);
                t.putExtra("alimentosSelecionados", alimentSelec);
                t.putExtra("R", radiobtnNome);
                t.putExtra("nomeDieta",nomeDieta);
                t.putExtra("nomeLote",nomeLote);
                startActivity(t);

            }
        });


        finalizarDieta = findViewById(R.id.btnFinalizarDieta);

        finalizarDieta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DadosOpenHelper meuBD = new DadosOpenHelper(MenuAlimentoSelecionadoActivity.this);
                Dieta dieta = new Dieta();
                dieta.setNome(nomeDieta);
                dieta.setId_lote(0);
                dieta.setNomeLote(nomeLote);
                meuBD.adicionarDieta(dieta);

                Intent intent = new Intent(MenuAlimentoSelecionadoActivity.this,ListaDietasActivity.class);
                startActivity(intent);
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                alimento = selecionaClasse(tab.getPosition());
                customAdapter = new CustomAdapter(alimento, MenuAlimentoSelecionadoActivity.this);
                gridView.setAdapter(customAdapter);

                for (int i = 0; i < alimento.size(); i++) {
                    for (int j = 0; j < alimentSelec.size(); j++) {
                        if (alimento.get(i).getNome().equals(alimentSelec.get(j))) {
                            alimento.get(i).setChecked(true);
                        }
                    }
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    public ArrayList<Alimento> listaCompleta() {
        ArrayList<Alimento> al = new ArrayList<>();

        for (int i = 0; i < nomesCap.length; i++) {
            Alimento a = new Alimento(nomesCap[i], imagensCap[i], menuexpansivelCap[i]);
            al.add(a);

        }
        for (int i = 0; i < nomesCon.length; i++) {
            Alimento a = new Alimento(nomesCon[i], imagensCon[i], expansivelCon[i]);
            al.add(a);

        }
        for (int i = 0; i < nomesSil.length; i++) {
            Alimento a = new Alimento(nomesSil[i], imagensSil[i], menuexpansivelSil[i]);
            al.add(a);

        }
        for (int i = 0; i < nomesFen.length; i++) {
            Alimento a = new Alimento(nomesFen[i], imagensFen[i], menuexpansivelFen[i]);
            al.add(a);

        }
        for (int i = 0; i < nomesMin.length; i++) {
            Alimento a = new Alimento(nomesMin[i], imagensMin[i], expansivelMin[i]);
            al.add(a);

        }

        return al;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_pesquisa);
        MenuItem menuItem2 = menu.findItem(R.id.menu_tratorzinho);

        menuItem2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent t = new Intent(MenuAlimentoSelecionadoActivity.this, TratorzinhoActivity.class);
                t.putExtra("N", nomes);
                t.putExtra("I", imagens);
                t.putExtra("E", exp);
                //t.putExtra("todos",  listCompleta);
                t.putExtra("alimentosSelecionados", alimentSelec);
                t.putExtra("R", radiobtnNome);
                t.putExtra("nomeDieta",nomeDieta);
                t.putExtra("nomeLote",nomeLote);
                startActivity(t);
                return false;
            }
        });

        final SearchView pesquisar = (SearchView) menuItem.getActionView();
        pesquisar.setMaxWidth(Integer.MAX_VALUE);
        pesquisar.setQueryHint("Pesquisar");
        SearchView.SearchAutoComplete theTextArea = pesquisar.findViewById(R.id.search_src_text);
        theTextArea.setTextColor(Color.WHITE);
        pesquisar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                customAdapter.getFilter().filter(newText);

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    private void configuraMenu() {
        mdrawerLayout = (DrawerLayout) findViewById(R.id.layoutSelec);
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
                        Intent intentInicio = new Intent(MenuAlimentoSelecionadoActivity.this, HomeActivity.class);
                        startActivity(intentInicio);
                        break;
                    case R.id.menu_relatorio:
                    case R.id.menu_minhaconta:
                    case R.id.menu_contato:
                    case R.id.menu_ajuda:
                        Toast.makeText(MenuAlimentoSelecionadoActivity.this, "Essa tela ainda será desenvolvida", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_sairDaConta:
                        Intent intent = new Intent(MenuAlimentoSelecionadoActivity.this, TelaPrimeiroAcessoActivity.class);
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

    private ArrayList<Alimento> getAlimentos(String[] nomes, int[] imagens, boolean[] menuexpansivel) {
        ArrayList<Alimento> alimentos = new ArrayList<Alimento>();
        Alimento a;
        for (int i = 0; i < nomes.length; i++) {
            a = new Alimento(nomes[i], imagens[i], menuexpansivel[i]);
            alimentos.add(a);
        }
        return alimentos;
    }

    public ArrayList<Alimento> selecionaClasse(int alimento) {
        ArrayList<Alimento> a;
        switch (alimento) {
            case 0:
                a = getAlimentos(nomesMin, imagensMin, expansivelMin);
                break;
            case 1:
                a = getAlimentos(nomesCon, imagensCon, expansivelCon);
                break;
            case 2:
                a = getAlimentos(nomesCap, imagensCap, menuexpansivelCap);
                break;
            case 3:
                a = getAlimentos(nomesFen, imagensFen, menuexpansivelFen);
                break;
            case 4:
                a = getAlimentos(nomesSil, imagensSil, menuexpansivelSil);
                break;
            default:
                return null;
        }
        return a;
    }

    public void valores() {
        Intent it = getIntent();

        if (it.getStringArrayListExtra("A").size() >0) {
            alimentSelec = it.getStringArrayListExtra("A");
            radiobtnNome = (ArrayList<TempoAlimento>) it.getSerializableExtra("R");
        }
    }

    public class CustomAdapter extends BaseAdapter implements Filterable {
        private Context context;
        private LayoutInflater layoutInflater;
        SparseBooleanArray mSparseBooleanArray;
        ArrayList<Alimento> alimentos;
        ArrayList<Alimento> alimentosFiltrados;
        CustomFilter filter;
        RadioGroup radioGroup;

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
                view = layoutInflater.inflate(R.layout.item_volumosos, viewGroup, false);
            }
            final RadioGroup opcao = view.findViewById(R.id.listagem_volumosos_radio_group);
            TextView name = view.findViewById(R.id.item_alternativo3_nome);
            TextView preco = view.findViewById(R.id.item_alternativo3_preco);
            ImageView imageView = view.findViewById(R.id.item_alternativo3_imagem);
            final CheckBox checkBox = view.findViewById(R.id.checkbox_alimento_opcao3);
            final ConstraintLayout mostraConteudoExtra = view.findViewById(R.id.constraint_expansivel);
            checkBox.setTag(position);
            //checkBox.setChecked(mSparseBooleanArray.get(position));
            //checkBox.setOnCheckedChangeListener(mCheckedChangeListener);


            final Alimento alim = (Alimento) getItem(position);

            checkBox.setOnCheckedChangeListener(null); //important
            checkBox.setChecked(alim.isChecked());
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    alim.setChecked(isChecked);
                }
            });


            if (alimentos.get(position).isExpansivel() && checkBox.isChecked()) {
                mostraConteudoExtra.setVisibility(View.VISIBLE);
                for (int i = 0; i < alimentSelec.size(); i++) {
                    if (radiobtnNome != null) {
                        for (int j = 0; j < radiobtnNome.size(); j++) {
                            if (alimentSelec.get(i).equals(radiobtnNome.get(j).getNome())) {
                                opcao.check(radiobtnNome.get(j).getId());
                            }
                        }
                    }
                }
            }


            tratamentoDosAlimentos(alimentos.get(position).getNome(), view);

            if (alimentos.get(position).isExpansivel() == true && checkBox.isChecked() == true) {
                mostraConteudoExtra.setVisibility(View.VISIBLE);
            } else {
                mostraConteudoExtra.setVisibility(View.GONE);
            }

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBox.isChecked()) {
                        alimentos.get(position).setChecked(false);
                        checkBox.setChecked(false);
                        mostraConteudoExtra.setVisibility(View.GONE);
                        if (alimentSelec != null) {
                            for (int i = 0; i < alimentSelec.size(); i++) {
                                if (alimentSelec.size() > 0) {
                                    if (alimentSelec.get(i).equals(alimentos.get(position).getNome())) {
                                        alimentSelec.remove(alimentSelec.get(i));
                                    }
                                }
                            }
                        }
                    } else {
                        checkBox.setChecked(true);
                        alimentos.get(position).setChecked(true);
                        alimentSelec.add(alimentos.get(position).getNome());
                        if (alimentos.get(position).isExpansivel()) {
                            mostraConteudoExtra.setVisibility(View.VISIBLE);
                            if (radiobtnNome != null) {
                                for (int i = 0; i < alimentSelec.size(); i++) {
                                    for (int j = 0; j < radiobtnNome.size(); j++) {
                                        if (alimentSelec.get(i).equals(radiobtnNome.get(j).getNome())) {
                                            opcao.check(radiobtnNome.get(j).getId());
                                            alimento.get(position).setIdRadioGp(radiobtnNome.get(j).getId());
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            });

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBox.isChecked()) {
                        alimentos.get(position).setChecked(true);
                        alimentSelec.add(alimentos.get(position).getNome());
                        if (alimentos.get(position).isExpansivel()) {
                            mostraConteudoExtra.setVisibility(View.VISIBLE);
                            if (radiobtnNome != null) {
                                for (int i = 0; i < alimentSelec.size(); i++) {
                                    for (int j = 0; j < radiobtnNome.size(); j++) {
                                        if (alimentSelec.get(i).equals(radiobtnNome.get(j).getNome())) {
                                            opcao.check(radiobtnNome.get(j).getId());
                                            alimento.get(position).setIdRadioGp(radiobtnNome.get(j).getId());
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        alimentos.get(position).setChecked(false);
                        if (alimentSelec != null) {
                            for (int i = 0; i < alimentSelec.size(); i++) {
                                if (alimentSelec.size() > 0) {
                                    if (alimentSelec.get(i).equals(alimentos.get(position).getNome())) {
                                        alimentSelec.remove(alimentSelec.get(i));
                                    }
                                }
                            }
                        }
                        if (alimentos.get(position).isExpansivel()) {
                            mostraConteudoExtra.setVisibility(View.GONE);
                        }
                    }
                }
            });

            opcao.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    TempoAlimento tp;
                    radiobtnNome = new ArrayList<TempoAlimento>();
                    for (int i = 0; i < alimentSelec.size(); i++) {
                        if (radiobtnNome != null) {
                            for (int j = 0; j < radiobtnNome.size(); j++) {
                                if (radiobtnNome.get(i).getNome().equals(radiobtnNome.get(j).getNome())) {
                                    radiobtnNome.remove(radiobtnNome.get(i));
                                }
                            }
                        }
                    }
                    tp = new TempoAlimento(alimentos.get(position).getNome(), checkedId);
                    radiobtnNome.add(tp);

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
            } else if (nomeImagen.equals("Sorgo Silagem")) {
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
            } else if (nomeImagen.equals("Cana-de-Açúcar Silagem")) {
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
            } else if (nomeImagen.equals("Farelo de Algodão")) {
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
                filter = new MenuAlimentoSelecionadoActivity.CustomAdapter.CustomFilter();
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

                    for (int i = 0; i < listCompleta.size(); i++) {
                        if (listCompleta.get(i).getNome().toUpperCase().contains(constraint)) {
                            Alimento a = new Alimento(listCompleta.get(i).getNome(), listCompleta.get(i).getImg(), listCompleta.get(i).isExpansivel());
                            filtros.add(a);
                        }
                    }
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
}



