package com.example.nutrilac.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutrilac.R;
import com.example.nutrilac.model.Alimento;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ListagemCapinsActivity extends Fragment {
    GridView gridView;
    CustomAdapter customAdapter;

    String[] nomes = {"Bagaço de Cana-de-Açúcar", "Braquiária Brizantha Marandu", "Braquiária Brizantha MG4", "Braquiária Brizantha Piatã (61 a 90 dias)",
            "Braquiária Brizantha Xaraes", "Braquiária Brizantha", "Braquiária Decumbens", "Braquiária Híbrida Mulato Outono", "Braquiária Humidícola",
            "Cana-de-açúcar", "Capim Coast Cross", "Capim Colonião", "Capim Elefante Anão (61 a 90 dias)",
            "Capim Elefante Cameroon", "Capim Elefante Paraíso (61 a 90 dias)",
            "Capim Elefante", "Capim Gordura", "Capim Massai (61 a 90 dias)", "Capim Mombaça", "Capim Setária (61 a 90 dias)",
            "Capim Tanzânia", "Capim Tifton", "Milheto", "Sorgo Forrageiro"};

    boolean[] menuexpansivel = {false, true, true, false,
            false, true, true, false, false, false, false, true, false,
            true, false,
            true, true, false, true, false,
            true, true, false, false};

    int[] imagens = {R.drawable.cana_de_acucar, R.drawable.braquiaria_marandu, R.drawable.capim_mg4, R.drawable.capim_piata,
            R.drawable.braquiaria_xaraes, R.drawable.brachiaria_brizantha, R.drawable.brachiaria_decumbens, R.drawable.braquiaria_hibrida_mulato, R.drawable.capim_humidicola,
            R.drawable.cana_de_acucar, R.drawable.capim_coast_cross, R.drawable.capim_coloniao, R.drawable.capim_elefante_anao,
            R.drawable.capim_elefante_cameroon, R.drawable.capim_elefante_paraiso,
            R.drawable.capim_elefante, R.drawable.capim_gordura, R.drawable.capim_massai, R.drawable.capim_mombaca, R.drawable.capim_setaria,
            R.drawable.capim_tanzania, R.drawable.capim_tifton, R.drawable.milheto,
            R.drawable.sorgo_forrageiro};

    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigatView;

    public static final String TITLE_APPBAR = "Capins";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_listagem_volumosos, container, false);


        gridView = view.findViewById(R.id.grid_view);
        customAdapter = new CustomAdapter(getAlimentos(), view.getContext());
        gridView.setAdapter(customAdapter);

        return view;
    }

    private ArrayList<Alimento> getAlimentos() {
        ArrayList<Alimento> alimentos = new ArrayList<Alimento>();
        Alimento a;
        for (int i = 0; i < nomes.length; i++) {
            a = new Alimento(nomes[i], imagens[i], menuexpansivel[i]);
            alimentos.add(a);
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
                view = layoutInflater.inflate(R.layout.item_volumosos, viewGroup, false);
            }
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
                    } else {
                        checkBox.setChecked(true);
                        alimentos.get(position).setChecked(true);
                        if (alimentos.get(position).isExpansivel()) {
                            mostraConteudoExtra.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBox.isChecked()) {
                        alimentos.get(position).setChecked(true);
                        if (alimentos.get(position).isExpansivel()) {
                            mostraConteudoExtra.setVisibility(View.VISIBLE);
                        }
                    } else {
                        alimentos.get(position).setChecked(false);
                        if (alimentos.get(position).isExpansivel()) {
                            mostraConteudoExtra.setVisibility(View.GONE);
                        }
                    }
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
                filter = new CustomFilter();
            }
            return filter;
        }

        class CustomFilter extends Filter {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint != null && constraint.length() > 0) {
                    constraint = constraint.toString().toUpperCase();
                    ArrayList<Alimento> filtros = new ArrayList<Alimento>();

                    for (int i = 0; i < alimentosFiltrados.size(); i++) {
                        if (alimentosFiltrados.get(i).getNome().toUpperCase().contains(constraint)) {
                            Alimento a = new Alimento(alimentosFiltrados.get(i).getNome(), alimentosFiltrados.get(i).getImg(), alimentosFiltrados.get(i).isExpansivel());
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); // It's important here
    }

    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_pesquisa);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Pesquisar");
        SearchView.SearchAutoComplete theTextArea = searchView.findViewById(R.id.search_src_text);
        theTextArea.setTextColor(Color.WHITE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("TAG", "new text ==>" + newText);
                customAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_tratorzinho){
            Intent intent = new Intent(getActivity(),TratorzinhoActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
