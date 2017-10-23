package pandaq.com.gifemoticonpro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import pandaq.com.gifemoticon.EmoticonView;
import pandaq.com.gifemoticon.IEmoticonMenuClickListener;
import pandaq.com.gifemoticon.KeyBoardManager;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_input)
    EditText mEtInput;
    @BindView(R.id.emoticonView)
    EmoticonView mEmoticonView;
    @BindView(R.id.llIndicator)
    RelativeLayout mLlIndicator;
    @BindView(R.id.test_button)
    Button mTestButton;
    private KeyBoardManager emotionKeyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initEmotionKeyboard();
        mEmoticonView.attachEditText(mEtInput);
        mEmoticonView.setEmotionAddVisiable(true);
        mEmoticonView.setEmoticonExtClickListener(new IEmoticonMenuClickListener() {
            @Override
            public void onTabAddClick(View view) {
                Toast.makeText(MainActivity.this, "点击添加按钮", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabSettingClick(View view) {
                Toast.makeText(MainActivity.this, "点击设置按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initEmotionKeyboard() {
        emotionKeyboard = KeyBoardManager.with(this);
        emotionKeyboard.bindToContent(mLlIndicator);
        emotionKeyboard.bindToEmotionButton(mTestButton);
        emotionKeyboard.bindToEditText(mEtInput);
        emotionKeyboard.setEmotionLayout(mEmoticonView);
    }

    @Override
    public void onBackPressed() {
        if (!emotionKeyboard.interceptBackPress()) {
            finish();
        }
    }
}
