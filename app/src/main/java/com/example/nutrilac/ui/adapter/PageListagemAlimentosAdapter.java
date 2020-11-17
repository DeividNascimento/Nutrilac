package com.example.nutrilac.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.nutrilac.ui.activity.ListagemCapinsActivity;
import com.example.nutrilac.ui.activity.ListagemConcentradosActivity;
import com.example.nutrilac.ui.activity.ListagemFenosActivity;
import com.example.nutrilac.ui.activity.ListagemMineraisActivity;
import com.example.nutrilac.ui.activity.ListagemSilagensActivity;

public class PageListagemAlimentosAdapter extends FragmentPagerAdapter {

    private int tabCount;

    public PageListagemAlimentosAdapter(FragmentManager frag, int tabCount){
        super(frag, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.tabCount = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ListagemMineraisActivity();
            case 1:
                return new ListagemConcentradosActivity();
            case 2:
                return new ListagemCapinsActivity();
            case 3:
                return new ListagemFenosActivity();
            case 4:
                return  new ListagemSilagensActivity();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
