package com.example.nutrilac.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
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
import android.widget.TextView;

import com.example.nutrilac.R;
import com.example.nutrilac.model.Alimento;

import java.io.Serializable;
import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ListagemMineraisActivity extends Fragment {

    GridView gridView;
    CustomAdapter customAdapter;

    String[] nomes = {"Calcário", "Cloreto de Cálcio", "Cloreto de Potássio", "Flor de Enxofre", "Fosfato Bicálcico",
            "Iodato de Potássio", "Óxido de Magnésio", "Sal Comum", "Selenito de Sódio", "Sulfato de Cobalto", "Sulfato de Cobre",
            "Sulfato de Manganês", "Sulfato de Zinco"};
    int[] imagens = {R.drawable.calcario, R.drawable.cloreto_calcio, R.drawable.cloreto_potassio, R.drawable.flor_de_enxofre, R.drawable.fosfato_bicalcico,
            R.drawable.iodato_potassio, R.drawable.oxido_magnesio, R.drawable.sal_comum, R.drawable.selenito_sodio, R.drawable.sulfato_cobalto, R.drawable.sulfato_cobre,
            R.drawable.sulfato_manganes, R.drawable.sulfato_zinco};


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.activity_listagem_minerais, container, false);



        gridView = view.findViewById(R.id.grid_view_minerais);
        customAdapter = new CustomAdapter(getAlimentos(),view.getContext());
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
                view = layoutInflater.inflate(R.layout.item_minerais, viewGroup, false);
            }
            TextView name = view.findViewById(R.id.lista_minerais_nome);
            TextView preco = view.findViewById(R.id.lista_minerais_preco);
            ImageView imageView = view.findViewById(R.id.lista_mineirais_imagem);
            final CheckBox checkBox = view.findViewById(R.id.lista_minerais_checkbox);
            checkBox.setTag(position);
            checkBox.setChecked(mSparseBooleanArray.get(position));
            checkBox.setOnCheckedChangeListener(mCheckedChangeListener);


            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBox.isChecked()) {
                        checkBox.setChecked(false);
                    } else {
                        checkBox.setChecked(true);
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
