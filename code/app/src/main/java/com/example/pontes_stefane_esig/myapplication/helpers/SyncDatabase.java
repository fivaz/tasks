package com.example.pontes_stefane_esig.myapplication.helpers;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class SyncDatabase extends AsyncTask {

    Context context;

    public SyncDatabase(Context context) {
        this.context = context;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        /*
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();

        AlunoConverter conversor = new AlunoConverter();
        String json = conversor.converteParaJSON(alunos);

        WebClient client = new WebClient();
        String userJSON = client.post(json);
        Toast.makeText(this, resposta, Toast.LENGTH_LONG).show();
        */
        return null;
    }
}