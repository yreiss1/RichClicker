package com.yuval.reiss.richclicker.Upgrades;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yuval.reiss.richclicker.MainActivity;
import com.yuval.reiss.richclicker.R;

public class InvestmentFragment extends Fragment {

    private Button savingsBuy;
    private Button savingsLiquidate;
    private Button savingsSell;
    private Button indexBuy;
    private Button indexLiquidate;
    private Button indexSell;
    private Button realEstateBuy;
    private Button realEstateLiquidate;
    private Button realEstateSell;
    private Button stocksBuy;
    private Button stocksLiquidate;
    private Button stocksSell;
    private Button cryptoBuy;
    private Button cryptoLiquidate;
    private Button cryptoSell;


    private TextView savingsInvestedTextview;
    private TextView savingsAssetTextView;
    private TextView indexInvestedTextview;
    private TextView indexAssetTextView;
    private TextView realEstateInvestedTextview;
    private TextView realEstateAssetTextView;
    private TextView stocksInvestedTextview;
    private TextView stocksAssetTextView;
    private TextView cryptoInvestedTextview;
    private TextView cryptoAssetTextView;


    private float savingsInvested = 0f;




    public static InvestmentFragment newInstance() {
        InvestmentFragment fragment = new InvestmentFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.investments_frag, container, false);


        savingsBuy = view.findViewById(R.id.savings_buy_button);
        savingsLiquidate = view.findViewById(R.id.savings_liquidate_button);
        savingsSell = view.findViewById(R.id.savings_sell_button);
        indexBuy = view.findViewById(R.id.index_buy_button);
        indexLiquidate = view.findViewById(R.id.index_liquidate_button);
        indexSell = view.findViewById(R.id.index_sell_button);
        realEstateBuy = view.findViewById(R.id.real_estate_buy_button);
        realEstateLiquidate = view.findViewById(R.id.real_estate_liquidate_button);
        realEstateSell = view.findViewById(R.id.real_estate_sell_button);
        stocksBuy = view.findViewById(R.id.stocks_buy_button);
        stocksLiquidate = view.findViewById(R.id.stocks_liquidate_button);
        stocksSell = view.findViewById(R.id.stocks_sell_button);
        cryptoBuy = view.findViewById(R.id.crypto_buy_button);
        cryptoLiquidate = view.findViewById(R.id.crypto_liquidate_button);
        cryptoSell = view.findViewById(R.id.crypto_sell_button);


        savingsInvestedTextview = view.findViewById(R.id.savings_invested_textview);
        savingsAssetTextView = view.findViewById(R.id.savings_asset_textview);
        indexInvestedTextview = view.findViewById(R.id.index_invested_textview);
        indexAssetTextView = view.findViewById(R.id.index_asset_textview);
        realEstateInvestedTextview = view.findViewById(R.id.real_estate_invested_textview);
        realEstateAssetTextView = view.findViewById(R.id.real_estate_asset_textview);
        stocksInvestedTextview = view.findViewById(R.id.stocks_invested_textview);
        stocksAssetTextView = view.findViewById(R.id.stocks_asset_textview);
        cryptoInvestedTextview = view.findViewById(R.id.crypto_invested_textview);
        cryptoAssetTextView = view.findViewById(R.id.crypto_asset_textview);

        savingsBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText amount = new EditText(getContext());
                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        .setTitle("Buy Savings")
                        .setMessage("Enter the amount of savings to purchase")
                        .setView(amount)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String val = String.valueOf(amount.getText());
                                Float fVal = Float.parseFloat(val);

                                if (fVal <= MainActivity.UserStats.liquid) {
                                    savingsInvested += fVal;

                                    MainActivity.UserStats.liquid -= fVal;
                                    MainActivity.UserStats.savingsAssetValue += fVal;

                                    savingsInvestedTextview.setText("Invested: $" + savingsInvested);
                                    savingsAssetTextView.setText("Asset Value: $" + MainActivity.UserStats.savingsAssetValue);

                                } else {
                                    Toast.makeText(getContext(), "You do not have enough money!", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                alertDialog.show();
            }
        });

        indexBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText amount = new EditText(getContext());
                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        .setTitle("Buy Index")
                        .setMessage("Enter the amount of index to purchase")
                        .setView(amount)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(amount.getText());
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                alertDialog.show();
            }
        });

        realEstateBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText amount = new EditText(getContext());
                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        .setTitle("Buy Real Estate")
                        .setMessage("Enter the amount of real estate to purchase")
                        .setView(amount)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(amount.getText());
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                alertDialog.show();
            }
        });

        stocksBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText amount = new EditText(getContext());
                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        .setTitle("Stocks Estate")
                        .setMessage("Enter the amount of stocks to purchase")
                        .setView(amount)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(amount.getText());
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                alertDialog.show();
            }
        });

        cryptoBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText amount = new EditText(getContext());
                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        .setTitle("Crypto Estate")
                        .setMessage("Enter the amount of crypto to purchase")
                        .setView(amount)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(amount.getText());
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                alertDialog.show();
            }
        });

        savingsLiquidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText amount = new EditText(getContext());

                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        .setTitle("Sell Savings")
                        .setMessage("Enter the amount of savings to sell")
                        .setView(amount)
                        .setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String val = String.valueOf(amount.getText());
                                Float fVal = Float.parseFloat(val);
                                Log.i("FVAL: ", Float.toString(fVal));
                                Log.i("SAVINGS ASSET VALUE: ", Float.toString(MainActivity.UserStats.savingsAssetValue));


                                if (fVal <= MainActivity.UserStats.savingsAssetValue) {
                                    savingsInvested = savingsInvested - fVal < 0 ? 0 : savingsInvested - fVal;
                                    MainActivity.UserStats.savingsAssetValue -= fVal;
                                    MainActivity.UserStats.liquid += fVal;
                                } else {
                                    Toast.makeText(getContext(), "Selling too much!", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                savingsInvestedTextview.setText("Invested: $" + savingsInvested);
                                savingsAssetTextView.setText("Asset Value: $" + MainActivity.UserStats.savingsAssetValue);

                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                alertDialog.show();
            }
        });

        indexLiquidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText amount = new EditText(getContext());
                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        .setTitle("Sell Index")
                        .setMessage("Enter the amount of Index to sell")
                        .setView(amount)
                        .setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(amount.getText());
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                alertDialog.show();
            }
        });

        realEstateLiquidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText amount = new EditText(getContext());
                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        .setTitle("Sell Real Estate")
                        .setMessage("Enter the amount of real estate to sell")
                        .setView(amount)
                        .setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(amount.getText());
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                alertDialog.show();
            }
        });

        stocksLiquidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText amount = new EditText(getContext());
                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        .setTitle("Sell Stocks")
                        .setMessage("Enter the amount of stocks to sell")
                        .setView(amount)
                        .setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(amount.getText());
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                alertDialog.show();
            }
        });

        cryptoLiquidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText amount = new EditText(getContext());
                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        .setTitle("Sell Crypto")
                        .setMessage("Enter the amount of crypto to sell")
                        .setView(amount)
                        .setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(amount.getText());
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                alertDialog.show();
            }
        });

        savingsSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.UserStats.liquid += MainActivity.UserStats.savingsAssetValue;
                savingsInvested = savingsInvested - MainActivity.UserStats.savingsAssetValue < 0 ? 0 : savingsInvested - MainActivity.UserStats.savingsAssetValue;
                savingsAssetTextView.setText("Asset Value: $" + 0f);
                savingsInvestedTextview.setText("Invested: $" + savingsInvested);
                MainActivity.UserStats.savingsAssetValue = 0;
            }
        });



        return view;
    }
}
