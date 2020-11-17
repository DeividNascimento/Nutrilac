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

public class ListagemSilagensActivity extends Fragment {

    GridView gridView;
    CustomAdapter customAdapter;

    String[] nomes = {"Cana-de-Açúcar Silagem", "Capim Elefante Silagem", "Capim Mombaça Silagem", "Estilosantes Silagem", "Milho sem Espiga Silagem", "Milho Silagem",
            "Sorgo Forrageiro Silagem", "Sorgo Silagem"};

    boolean[] menuexpansivel = {true, false, false, false, false, false, false, true};

    int[] imagens = {R.drawable.cana_acucar_silagem, R.drawable.capim_elefante_silagem, R.drawable.capim_mombaca_silagem, R.drawable.capim_estilosantes,
            R.drawable.milho_sem_espiga_silagem, R.drawable.milho_silagem, R.drawable.sorgo_forrageiro_silagem, R.drawable.sorgo_silagem};

    public static final String TITLE_APPBAR = "Silagens";
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigatView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.activity_listagem_minerais, container, false);

        gridView = view.findViewById(R.id.grid_view_minerais);
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
            checkBox.setChecked(mSparseBooleanArray.get(position));
            checkBox.setOnCheckedChangeListener(mCheckedChangeListener);

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
            opcao4.setVisibility(View.GONE);
            opcao5.setVisibility(View.GONE);
            opcao6.setVisibility(View.GONE);
            opcao7.setVisibility(View.GONE);
            opcao8.setVisibility(View.GONE);
            opcao9.setVisibility(View.GONE);

            if(nomeImagen.equals("Cana-de-Açúcar Silagem")){
                opcao1.setText("Cana-de-Açúcar Silagem");
                opcao2.setText("Cana-de-Açúcar Silagem (0 a 0.5 % CaO)");
                opcao3.setVisibility(View.GONE);
            }
            else if(nomeImagen.equals("Sorgo Silagem")){
                opcao1.setText("Sorgo Silagem");
                opcao2.setText("Sorgo Silagem Com Tanino");
                opcao3.setText("Sorgo Silagem Sem Tanino");
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