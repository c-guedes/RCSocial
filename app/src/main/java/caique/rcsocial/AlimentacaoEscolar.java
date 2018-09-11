package caique.rcsocial;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AlimentacaoEscolar extends AppCompatActivity {
    private FirebaseAuth mAuth;

    TextView boasVindas;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private BottomNavigationView navigationView;


    // configurar botões da navbar

    private void setBoasVindas(){
        String nome = user.getDisplayName();
        boasVindas = (TextView)findViewById(R.id.tf_boasvindas);

        if(Character.toString(nome.charAt(nome.length() - 1)) == "A"){
            boasVindas.setText(String.format("Seja bem vinda %s!",nome));
        }else{
            boasVindas.setText(String.format("Seja bem vindo %s!",nome));
        }

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentacao_escolar);

        // setar botoes da navibar
        BottomNavigationView bottomNavi = findViewById(R.id.bott);
        bottomNavi.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new PadraoFragment()).commit();

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.action_perfil:
                    selectedFragment = new PerfilFragment();
                    break;
                case R.id.action_inicio:
                    selectedFragment = new PadraoFragment();
                    break;
                case R.id.action_auditoria:
                    selectedFragment = new AuditoriasFragment();
                    break;
                case R.id.action_monitoramento:
                    selectedFragment = new MonitoriasFragment();
                    break;
                case R.id.action_sair:
                    getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    Intent sair = new Intent(AlimentacaoEscolar.this, Login.class);
                    sair.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(sair);
                    finish();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return  true;
        }
    };
    public void init(){
        setBoasVindas();
    }
}
