package caique.rcsocial;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class faz_cadastro extends Activity {
    public Button voltar,btn_finalizar;
    public Spinner sp;

    private static final String TAG = "AdicionarDBA";

    ProgressBar ProgressBar;

    private String[] municipios = new String[]{"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","bb"};

    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dataBaseReference;


    private void registrarUser(){
        EditText editTextEmail = (EditText)findViewById(R.id.tf_email);
        EditText editTextSenha = (EditText)findViewById(R.id.tf_senha);
        EditText editTextNome = (EditText)findViewById(R.id.tf_nome);
        EditText editTextCPF = (EditText)findViewById(R.id.tf_cpf);
        final EditText editTextConfirma = (EditText)findViewById(R.id.tf_confirmaSenha);
        String municipio = getMunicipio().toString();

        final String email = editTextEmail.getText().toString().trim();
        String senha = editTextSenha.getText().toString().trim();
        final String nome = editTextNome.getText().toString().trim().toUpperCase();
        final String cpf = editTextCPF.getText().toString();
        final String confirma = editTextConfirma.getText().toString();

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
        if(cpf.length()!=11){
            editTextCPF.setError("O CPF deve conter 11 dígitos");
            editTextCPF.requestFocus();
            return;
        }
        if (email.isEmpty()){
            editTextEmail.setError("E-mail necessário");
            editTextEmail.requestFocus();
            return;
        }if (cpf.isEmpty()){
            editTextCPF.setError("CPF é obrigatório");
            editTextCPF.requestFocus();
            return;
        }if (confirma.isEmpty()){
            editTextConfirma.setError("É necessário confirmar a senha");
            editTextConfirma.requestFocus();
            return;
        }
        if (nome.isEmpty()){
            editTextNome.setError("Quem é você?");
            editTextNome.requestFocus();
            return;
        }
        if (senha.isEmpty()) {
            editTextSenha.setError("Senha está vazia");
            editTextSenha.requestFocus();
            return;
        }if (!senha.equals(confirma)){
            editTextConfirma.setError("A senha digitada não é igual");
            editTextConfirma.requestFocus();
            return;
        }
        ProgressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                ProgressBar.setVisibility(View.GONE);

                if(task.isSuccessful()){
                    dataBaseReference.child("user").push().child(cpf);
                    dataBaseReference.child("user").child("cpf").child("nome").setValue(nome);
                    dataBaseReference.child("user").child("cpf").child("email").setValue(email);
                    dataBaseReference.child("user").child("cpf").child("cpf").setValue(cpf);
                    dataBaseReference.child("user").child("cpf").child("id").setValue(mAuth.getUid());
                    Toast.makeText(getApplicationContext(),"Usuário registrado!", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getApplicationContext(),"Usuário já registrado",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public String getMunicipio(){
        String municipio = (String)sp.getSelectedItem();
        return municipio;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_faz_cadastro);
        mAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(faz_cadastro.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        dataBaseReference = firebaseDatabase.getReference();

        ProgressBar = (ProgressBar)findViewById(R.id.progressBar);
        init();
        }

    public void init() {
        voltar = (Button) findViewById(R.id.backToCadastro);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent toy = new Intent(faz_cadastro.this, cadastrar.class);
                startActivity(toy);
            }

        });

        btn_finalizar = (Button)findViewById(R.id.finalizar);
        btn_finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUser();
            }

        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, municipios);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp = (Spinner)findViewById(R.id.selecionar_municipio);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

}
