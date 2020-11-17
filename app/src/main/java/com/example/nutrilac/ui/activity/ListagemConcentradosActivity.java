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
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ListagemConcentradosActivity extends Fragment {

    CustomAdapter customAdapter;
    GridView gridView;
    String[] nomes = {"Amiréia", "Caroço de Algodão", "Casca de Soja", "Citrus Polpa", "Farelo de Algodão", "Farelo de Amendoim", "Farelo de Girassol",
            "Farelo de Soja", "Farelo de Trigo", "Mandioca Raspa", "Milho Desintegrado com Palha e Sabugo", "Milho Espiga Silagem (Earlage)",
            "Milho Fubá", "Milho Grão Reidratado Silagem", "Milho Grão Úmido Silagem", "Resíduo de Cervejaria Úmido", "Soja em Grão",
            "Soja Grão Tostada", "Sorgo Grão", "Torta de Algodão", "Ureia"};

    int[] imagens = {R.drawable.amireia, R.drawable.caroco_algodao, R.drawable.casca_soja, R.drawable.citrus_polpa, R.drawable.algodao_farelo,
            R.drawable.farelo_amendoim, R.drawable.farelo_girassol,
            R.drawable.farelo_soja, R.drawable.trigo_farelo, R.drawable.mandioca_raspa, R.drawable.milho_desintegrado, R.drawable.milho_espiga_silagem,
            R.drawable.milho_fuba, R.drawable.milho_grao_reidratado_silagem, R.drawable.milho_grao_umido_silagem, R.drawable.residuo_cervejaria,
            R.drawable.soja_grao, R.drawable.soja_grao_tostado, R.drawable.sorgo_grao, R.drawable.algodao_torta, R.drawable.ureia};

    public static final String TITLE_APPBAR = "Concentrados";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_listagem_concentrados, container, false);


        gridView = view.findViewById(R.id.grid_view_concentrados);
        customAdapter = new CustomAdapter(getAlimentos(), view.getContext());
        gridView.setAdapter(customAdapter);
        return view;
    }

    private ArrayList<Alimento> getAlimentos() {
        ArrayList<Alimento> alimentos = new ArrayList<Alimento>();
        Alimento a;
        for (int i = 0; i < nomes.length; i++) {
            a = new Alimento(nomes[i], imagens[i], false);
            alimentos.add(a);
        }
        return alimentos;
    }

    public class CustomAdapter extends BaseAdapter implements Filterable {
        ArrayList<Alimento> alimentos;
        ArrayList<Alimento> alimentosFiltrados;
        CustomFilter filter;
        private Context context;
        private LayoutInflater layoutInflater;
        SparseBooleanArray mSparseBooleanArray;

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
            final TextView name = view.findViewById(R.id.item_alternativo3_nome);
            TextView preco = view.findViewById(R.id.item_alternativo3_preco);
            ImageView imageView = view.findViewById(R.id.item_alternativo3_imagem);
            final CheckBox checkBox = view.findViewById(R.id.checkbox_alimento_opcao3);
            final ConstraintLayout mostraConteudoExtra = view.findViewById(R.id.constraint_expansivel);
            checkBox.setTag(position);
            checkBox.setChecked(mSparseBooleanArray.get(position));
            checkBox.setOnCheckedChangeListener(mCheckedChangeListener);

            // Definindo as opções caso o usuário clique no Farelo de algodão, pois existem três tipos de farelo
            RadioButton opcao1 = view.findViewById(R.id.listagem_volumosos_radio1);
            RadioButton opcao2 = view.findViewById(R.id.listagem_volumosos_radio2);
            RadioButton opcao3 = view.findViewById(R.id.listagem_volumosos_radio3);
            RadioButton opcao4 = view.findViewById(R.id.listagem_volumosos_radio4);
            RadioButton opcao5 = view.findViewById(R.id.listagem_volumosos_radio5);
            RadioButton opcao6 = view.findViewById(R.id.listagem_volumosos_radio6);
            RadioButton opcao7 = view.findViewById(R.id.listagem_volumosos_radio7);
            RadioButton opcao8 = view.findViewById(R.id.listagem_volumosos_radio8);
            RadioButton opcao9 = view.findViewById(R.id.listagem_volumosos_radio9);
            opcao1.setText("Farelo de Algodão 28% PB");
            opcao2.setText("Farelo de Algodão 38% PB");
            opcao3.setText("Farelo de Algodão 42% PB");
            opcao4.setVisibility(View.GONE);
            opcao5.setVisibility(View.GONE);
            opcao6.setVisibility(View.GONE);
            opcao7.setVisibility(View.GONE);
            opcao8.setVisibility(View.GONE);
            opcao9.setVisibility(View.GONE);

            if (alimentos.get(position).getNome().equals("Farelo de Algodão") && checkBox.isChecked() == true) {
                mostraConteudoExtra.setVisibility(View.VISIBLE);
            } else {
                mostraConteudoExtra.setVisibility(View.GONE);
            }

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBox.isChecked()) {
                        checkBox.setChecked(false);
                        mostraConteudoExtra.setVisibility(View.GONE);
                    } else {
                        checkBox.setChecked(true);
                        if (alimentos.get(position).getNome().equals("Farelo de Algodão")) {
                            mostraConteudoExtra.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBox.isChecked()) {
                        if (alimentos.get(position).getNome().equals("Farelo de Algodão")) {
                            mostraConteudoExtra.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (alimentos.get(position).getNome().equals("Farelo de Algodão")) {
                            mostraConteudoExtra.setVisibility(View.GONE);
                        }
                    }
                }
            });

            name.setText(alimentos.get(position).getNome());
            imageView.setImageResource(alimentos.get(position).getImg());
            preco.setText("R$ XX,XX");
            return view;
        }

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

        CompoundButton.OnCheckedChangeListener mCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                mSparseBooleanArray.put((Integer) buttonView.getTag(), isChecked);
            }
        };
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true); // It's important here
        super.onCreate(savedInstanceState);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
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
            Intent intent = new Intent(getActivity(),HomeActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
