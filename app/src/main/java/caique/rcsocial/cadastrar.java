package caique.rcsocial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class cadastrar extends AppCompatActivity {

    public Button voltar,btn_aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_cadastrar);
        init();
    }


    public void chamaCadastro(int tipo){
        if (tipo == 0){
            finish();
            Intent toy = new Intent(cadastrar.this, faz_cadastro.class);
            toy.putExtra("tipo", "aluno");
            startActivity(toy);
        }

    }

    public void init(){
        voltar = (Button)findViewById(R.id.backToLogin);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent toy = new Intent(cadastrar.this, logi.class);
                startActivity(toy);
            }

        });

        btn_aluno = (Button)findViewById(R.id.btn_aluno);
        btn_aluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaCadastro(0);
            }

        });
    }
}
