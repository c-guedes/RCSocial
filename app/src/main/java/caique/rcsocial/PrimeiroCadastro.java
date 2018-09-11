package caique.rcsocial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrimeiroCadastro extends AppCompatActivity {

    public Button voltar,btn_aluno, btn_professor, btn_pais, btn_outro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_cadastrar);
        init();
    }


    public void chamaCadastro(int tipo){
        if (tipo == 0){
            finish();
            Intent toy = new Intent(PrimeiroCadastro.this, FazCadastro.class);
            toy.putExtra("tipo", "aluno");
            startActivity(toy);
        }else{
            Intent toy = new Intent(PrimeiroCadastro.this, FazCadastro.class);
            toy.putExtra("tipo", "");
            startActivity(toy);
        }

    }

    public void init(){
        voltar = (Button)findViewById(R.id.backToLogin);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent toy = new Intent(PrimeiroCadastro.this, Login.class);
                finish();
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

        btn_pais = (Button)findViewById(R.id.btn_familia);
        btn_pais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamaCadastro(1);
            }
        });

        btn_outro = (Button)findViewById(R.id.btn_outro);
        btn_outro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamaCadastro(2);
            }
        });

        btn_professor = (Button)findViewById(R.id.btn_professor);
        btn_professor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamaCadastro(3);
            }
        });
    }
}
