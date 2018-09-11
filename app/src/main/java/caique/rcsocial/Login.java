package caique.rcsocial;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    ProgressBar barraDeProgresso;


    public Button acessa, esqueci,novo;

    private void logar(){
        EditText editTextEmail = (EditText)findViewById(R.id.tf_email);
        EditText editTextSenha = (EditText)findViewById(R.id.tf_senha);

        String email = editTextEmail.getText().toString().trim();
        String senha = editTextSenha.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Insira um email válido");
            editTextEmail.requestFocus();
            return;
        }
        if(senha.length()<8){
            editTextSenha.setError("Senha deve conter 8 ou mais dígitos");
            editTextSenha.requestFocus();
            return;
        }
        if (email.isEmpty()){
            editTextEmail.setError("E-mail necessário");
            editTextEmail.requestFocus();
            return;
        }
        if (senha.isEmpty()) {
            editTextSenha.setError("Senha está vazia");
            editTextSenha.requestFocus();
            return;
        }
        barraDeProgresso.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                barraDeProgresso.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Bem vindo!", Toast.LENGTH_SHORT).show();
                    Intent toy = new Intent(Login.this, AlimentacaoEscolar.class);
                    toy.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(toy);
                }else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    // AAAAAAAAAAAAAAAAAAAAAAAAAA

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mAuth = FirebaseAuth.getInstance();
        barraDeProgresso = (ProgressBar)findViewById(R.id.loginProgress);
        init();
    }


    public void init(){
        ///// database /////////////

        /////////////botoes//////////

        findViewById(R.id.tf_email).requestFocus();
        acessa = (Button)findViewById(R.id.btn_acessa);
        acessa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logar();
            }
        });

        esqueci = (Button)findViewById(R.id.btn_esqueci);
        esqueci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent toy = new Intent(Login.this, EsqueciSenha.class);
                startActivity(toy);
            }

        });
        novo = (Button)findViewById(R.id.btn_novo);
        novo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent toy = new Intent(Login.this, PrimeiroCadastro.class);
                startActivity(toy);
            }

        });
    }
}