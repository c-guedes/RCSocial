package caique.rcsocial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HomePadrao extends Activity {

    public ImageButton alimenta;
    public void init(){
        alimenta = (ImageButton)findViewById(R.id.btn_alimentacao);
        alimenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(HomePadrao.this,Login.class);
                startActivity(toy);

            }
        });
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        init();
    }
}
