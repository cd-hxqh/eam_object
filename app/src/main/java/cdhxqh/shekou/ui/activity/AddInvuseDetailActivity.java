package cdhxqh.shekou.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cdhxqh.shekou.R;
import cdhxqh.shekou.api.JsonUtils;
import cdhxqh.shekou.bean.InvuseResult;
import cdhxqh.shekou.config.Constants;
import cdhxqh.shekou.model.Invuse;
import cdhxqh.shekou.model.Invuseline;
import cdhxqh.shekou.model.Item;
import cdhxqh.shekou.model.Option;
import cdhxqh.shekou.utils.AccountUtils;
import cdhxqh.shekou.utils.GetDateAndTime;
import cdhxqh.shekou.utils.MessageUtils;
import cdhxqh.shekou.webserviceclient.AndroidClientService;

/**
 * 新增备件单行*
 */

public class AddInvuseDetailActivity extends BaseActivity {
    private static final String TAG = "AddInvuseDetailActivity";

    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    /**界面信息**/
    /**
     * 备件*
     */
    private TextView itemText;
    /**
     * 备件描述*
     */
    private TextView itemdescriptionText;


    /**
     * 数量
     */
    private TextView quantityText;
    /**
     * 货柜
     */
    private TextView frombinText;

    /**
     * 领料人
     */
    private TextView issuetoText;

    /**
     * 备注*
     */
    private EditText remarkText;

    /**
     * 新建*
     */
    private Button addButton;

    /**
     * 类型*
     */
    private String udapptype;


    /**
     * 货柜*
     */
    private String frombin;
    /**
     * 领料人*
     */
    private String issueto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addinvuseline);
        initData();
        findViewById();
        initView();
    }

    private void initData() {
        udapptype = getIntent().getExtras().getString("udapptype");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        itemText = (TextView) findViewById(R.id.invuseline_item_num_text_id);
        itemdescriptionText = (TextView) findViewById(R.id.invuseline_description_text_id);


        quantityText = (TextView) findViewById(R.id.quantity_text_id);
        frombinText = (TextView) findViewById(R.id.frombin_txt);
        issuetoText = (TextView) findViewById(R.id.issueto_text_id);
        remarkText = (EditText) findViewById(R.id.remark_text_id);


        addButton = (Button) findViewById(R.id.add_button_id);

    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.add_invuseline_text));

        itemText.setOnClickListener(itemTextOnClickListener);
        frombinText.setOnClickListener(frombinTextOnClickListener);
        issuetoText.setOnClickListener(issuetoTextOnClickListener);
        addButton.setOnClickListener(addButtonOnClickListener);


    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setResult(1);
            finish();
        }
    };


    /**
     * 备件*
     */
    private View.OnClickListener itemTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = getIntent();
            intent.setClass(AddInvuseDetailActivity.this, ItemChooseActivity.class);
            intent.putExtra("requestCode", Constants.ITEMCODE);
            startActivityForResult(intent, Constants.ITEMCODE);
        }
    };

    /**
     * 部位5级*
     */
    private View.OnClickListener level6TextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };
    /**
     * 部位6级*
     */
    private View.OnClickListener level5TextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };
    /**
     * 货柜*
     */
    private View.OnClickListener frombinTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AddInvuseDetailActivity.this, OptionActivity.class);
            intent.putExtra("requestCode", Constants.LOCATIONCODE);
            startActivityForResult(intent, Constants.LOCATIONCODE);
        }
    };


    /**
     * 领料人*
     */
    private View.OnClickListener issuetoTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(AddInvuseDetailActivity.this, OptionActivity.class);
            intent.putExtra("requestCode", Constants.PERSONCODE);
            startActivityForResult(intent, Constants.PERSONCODE);
        }
    };


    /**
     * 新建*
     */
    private View.OnClickListener addButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            Bundle bundle = new Bundle();

            bundle.putParcelable("invuseline", encapsulationInvuseLine());
            intent.putExtras(bundle);
            setResult(0, intent);

            MessageUtils.showMiddleToast(AddInvuseDetailActivity.this, "数据保存成功");

            finish();
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;

        switch (resultCode) {
            case Constants.ITEMCODE:
                Item item = (Item) data.getSerializableExtra("item");
                itemText.setText(item.itemnum);
                itemdescriptionText.setText(item.description);
                break;
            case Constants.LOCATIONCODE:
                option = (Option) data.getSerializableExtra("option");
                frombin = option.getName();
                frombinText.setText(option.getDescription());
                break;

            case Constants.PERSONCODE:
                option = (Option) data.getSerializableExtra("option");
                issueto = option.getName();
                issuetoText.setText(option.getDescription());
                break;

            default:
                break;

        }


    }


    /**
     * 封装领料单行信息*
     */
    private Invuseline encapsulationInvuseLine() {
        String item = itemText.getText().toString(); //备件

        String description = itemdescriptionText.getText().toString(); //备件名称


        String quantity = quantityText.getText().toString(); //数量

//        String issueto = issuetoText.getText().toString(); //领料人

        String remark = remarkText.getText().toString(); //备注


        Invuseline invuseline = new Invuseline();
        invuseline.invusenum = "";
        invuseline.itemnum = item;
        invuseline.description = description;
//        invuseline.usetype = "发放";
        invuseline.quantity = quantity;
        invuseline.frombin = frombin;
        invuseline.taskid = "";
        invuseline.issueto = issueto;
        invuseline.remark = remark;

        return invuseline;
    }


}
