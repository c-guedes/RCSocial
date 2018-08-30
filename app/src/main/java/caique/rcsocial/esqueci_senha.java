package caique.rcsocial;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class esqueci_senha extends Activity {
    private FirebaseAuth mAuth;
    public Button voltar,btn_recuperar;

    private void recuperar(){
        EditText editTextEmail = (EditText)findViewById(R.id.tf_recupera);
        String email = editTextEmail.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Insira um email válido");
            editTextEmail.requestFocus();
            return;
        }
        if (email.isEmpty()){
            editTextEmail.setError("E-mail necessário");
            editTextEmail.requestFocus();
            return;
        }
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Email de recuperação enviado", Toast.LENGTH_SHORT).show();
                }else{
                        Toast.makeText(getApplicationContext(),"Usuário não encontrado",Toast.LENGTH_SHORT).show();                    }
            }
            });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_esqueci_senha);
        mAuth = FirebaseAuth.getInstance();
        init();
    }

    public void init() {
        voltar = (Button) findViewById(R.id.backToLogin);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(esqueci_senha.this, logi.class);
                finish();
                startActivity(toy);
            }

        });
        btn_recuperar = (Button) findViewById(R.id.btn_recuperar);
        btn_recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperar();
            }
        });

    }
}
