package com.example.nutrilac.ui.activity;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.nutrilac.R;
import com.example.nutrilac.model.Alimento;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ListagemFenosActivity extends Fragment {

    GridView gridView;
    CustomAdapter customAdapter;

    String[] nomes = {"Capim Braquiária Brizantha Feno", "Capim Braquiária Coast Cross Feno",
            "Capim Braquiária Decumbens Feno", "Capim Elefante Feno", "Capim Tifton 85 Feno"};

    boolean[] menuexpansivel = {false, false, false, false, false};

    int[] imagens = {R.drawable.campim_braquiaria_feno, R.drawable.feno_coast_cross,
            R.drawable.feno_brizantha, R.drawable.capim_elefante_feno, R.drawable.capim_tifton_feno};

    public static final String TITLE_APPBAR = "Fenos";
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigatView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.activity_listagem_volumosos, container, false);


        gridView = view.findViewById(R.id.grid_view);
        customAdapter = new ListagemFenosActivity.CustomAdapter(getAlimentos(),view.getContext());
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
            checkBox.setChecked(mSparseBooleanArray.get(position));
            checkBox.setOnCheckedChangeListener(mCheckedChangeListener);

            if (alimentos.get(position).isExpansivel() == true && checkBox.isChecked() == true) {
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
                        if (alimentos.get(position).isExpansivel()) {
                            mostraConteudoExtra.setVisibility(View.VISIBLE);
                        }
                    } else {
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
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
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

}