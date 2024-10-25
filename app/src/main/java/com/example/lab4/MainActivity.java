package com.example.lab4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView tv_meal;
    private Button btn_select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_meal = findViewById(R.id.tv_meal);  // 連接 TextView 元件
        btn_select = findViewById(R.id.btn_choice);  // 連接 Button 元件
        btn_select.setOnClickListener(view -> {  // Button 點擊事件
            mStartForResult.launch(
                    new Intent(this, MainActivity2.class)  // 透過 Intent 切換至 Main2Activity
            );
        });
    }

    private final ActivityResultLauncher<Intent> mStartForResult =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {  // 確認執行的結果
                    Intent intent = result.getData();
                    if (intent != null && intent.getExtras() != null) {
                        Bundle b = intent.getExtras();  // 取得飲料、甜度、冰塊資料
                        String str1 = b.getString("drink");
                        String str2 = b.getString("sugar");
                        String str3 = b.getString("ice");

                        tv_meal.setText(String.format("飲料: %s\n甜度: %s\n冰塊: %s", str1, str2, str3));
                        // 透過 TextView.setText() 顯示資料
                    }
                }
            });
}
