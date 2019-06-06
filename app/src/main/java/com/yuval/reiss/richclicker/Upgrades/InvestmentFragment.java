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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Timer;
import java.util.TimerTask;

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


    private BigDecimal savingsInvested = new BigDecimal(0);
    private BigDecimal indexInvested = new BigDecimal(0);
    private BigDecimal realEstateInvested = new BigDecimal(0);
    private BigDecimal stocksInvested = new BigDecimal(0);
    private BigDecimal cryptoInvested = new BigDecimal(0);



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
                                BigDecimal fVal = new BigDecimal(Float.parseFloat(val));

                                if (Float.parseFloat(val) <= MainActivity.UserStats.liquid.floatValue()) {
                                    savingsInvested = savingsInvested.add(fVal);

                                    MainActivity.UserStats.liquid = MainActivity.UserStats.liquid.subtract(fVal);
                                    MainActivity.UserStats.savingsAssetValue = MainActivity.UserStats.savingsAssetValue.add(fVal);

                                    savingsInvested = savingsInvested.setScale(2, BigDecimal.ROUND_HALF_UP);
                                    MainActivity.UserStats.savingsAssetValue = MainActivity.UserStats.savingsAssetValue.setScale(2, BigDecimal.ROUND_HALF_UP);
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
                                String val = String.valueOf(amount.getText());
                                BigDecimal fVal = new BigDecimal(Float.parseFloat(val));

                                if ( Float.parseFloat(val)<= MainActivity.UserStats.liquid.floatValue()) {
                                    indexInvested = indexInvested.add(fVal);

                                    MainActivity.UserStats.liquid = MainActivity.UserStats.liquid.subtract(fVal);
                                    MainActivity.UserStats.indexAssetValue = MainActivity.UserStats.indexAssetValue.add(fVal);

                                    indexInvested = indexInvested.setScale(2, BigDecimal.ROUND_HALF_UP);
                                    MainActivity.UserStats.indexAssetValue = MainActivity.UserStats.indexAssetValue.setScale(2, BigDecimal.ROUND_HALF_UP);
                                    indexInvestedTextview.setText("Invested: $" + indexInvested);
                                    indexAssetTextView.setText("Asset Value: $" + MainActivity.UserStats.indexAssetValue);

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
                                String val = String.valueOf(amount.getText());
                                BigDecimal fVal = new BigDecimal(Float.parseFloat(val));

                                if (Float.parseFloat(val) <= MainActivity.UserStats.liquid.floatValue()) {
                                    realEstateInvested = realEstateInvested.add(fVal);

                                    MainActivity.UserStats.liquid = MainActivity.UserStats.liquid.subtract(fVal);
                                    MainActivity.UserStats.realEstateAssetValue = MainActivity.UserStats.realEstateAssetValue.add(fVal);

                                    realEstateInvested = realEstateInvested.setScale(2, BigDecimal.ROUND_HALF_UP);
                                    MainActivity.UserStats.realEstateAssetValue = MainActivity.UserStats.realEstateAssetValue.setScale(2, BigDecimal.ROUND_HALF_UP);
                                    realEstateInvestedTextview.setText("Invested: $" + realEstateInvested);
                                    realEstateAssetTextView.setText("Asset Value: $" + MainActivity.UserStats.realEstateAssetValue);

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
                                String val = String.valueOf(amount.getText());
                                BigDecimal fVal = new BigDecimal(Float.parseFloat(val));

                                if (Float.parseFloat(val) <= MainActivity.UserStats.liquid.floatValue()) {
                                    stocksInvested = stocksInvested.add(fVal);

                                    MainActivity.UserStats.liquid = MainActivity.UserStats.liquid.subtract(fVal);
                                    MainActivity.UserStats.stocksAssetValue = MainActivity.UserStats.stocksAssetValue.add(fVal);

                                    stocksInvested = stocksInvested.setScale(2, BigDecimal.ROUND_HALF_UP);
                                    MainActivity.UserStats.stocksAssetValue = MainActivity.UserStats.stocksAssetValue.setScale(2, BigDecimal.ROUND_HALF_UP);
                                    stocksInvestedTextview.setText("Invested: $" + stocksInvested);
                                    stocksAssetTextView.setText("Asset Value: $" + MainActivity.UserStats.stocksAssetValue);

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
                                String val = String.valueOf(amount.getText());
                                BigDecimal fVal = new BigDecimal(Float.parseFloat(val));

                                if (Float.parseFloat(val) <= MainActivity.UserStats.liquid.floatValue()) {
                                    cryptoInvested = cryptoInvested.add(fVal);

                                    MainActivity.UserStats.liquid = MainActivity.UserStats.liquid.subtract(fVal);
                                    MainActivity.UserStats.cryptoAssetValue = MainActivity.UserStats.cryptoAssetValue.add(fVal);

                                    cryptoInvested = cryptoInvested.setScale(2, BigDecimal.ROUND_HALF_UP);
                                    MainActivity.UserStats.cryptoAssetValue = MainActivity.UserStats.cryptoAssetValue.setScale(2, BigDecimal.ROUND_HALF_UP);
                                    cryptoInvestedTextview.setText("Invested: $" + cryptoInvested);
                                    cryptoAssetTextView.setText("Asset Value: $" + MainActivity.UserStats.cryptoAssetValue);

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
                                BigDecimal fVal = new BigDecimal(Float.parseFloat(val));


                                if (Float.parseFloat(val) <= MainActivity.UserStats.savingsAssetValue.floatValue()) {
                                    savingsInvested = savingsInvested.subtract(fVal).signum() < 0 ? BigDecimal.ZERO : savingsInvested.subtract(fVal);
                                    MainActivity.UserStats.savingsAssetValue = MainActivity.UserStats.savingsAssetValue.subtract(fVal);
                                    MainActivity.UserStats.liquid = MainActivity.UserStats.liquid.add(fVal);
                                } else {
                                    Toast.makeText(getContext(), "Selling too much!", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                savingsInvested = savingsInvested.setScale(2, BigDecimal.ROUND_HALF_UP);
                                MainActivity.UserStats.savingsAssetValue = MainActivity.UserStats.savingsAssetValue.setScale(2, BigDecimal.ROUND_HALF_UP);
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
                                String val = String.valueOf(amount.getText());
                                BigDecimal fVal = new BigDecimal(Float.parseFloat(val));


                                if (Float.parseFloat(val) <= MainActivity.UserStats.indexAssetValue.floatValue()) {
                                    indexInvested = indexInvested.subtract(fVal).signum() < 0 ? BigDecimal.ZERO: indexInvested.subtract(fVal);
                                    MainActivity.UserStats.indexAssetValue = MainActivity.UserStats.indexAssetValue.subtract(fVal);
                                    MainActivity.UserStats.liquid = MainActivity.UserStats.liquid.add(fVal);
                                } else {
                                    Toast.makeText(getContext(), "Selling too much!", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                indexInvested = indexInvested.setScale(2, BigDecimal.ROUND_HALF_UP);
                                MainActivity.UserStats.indexAssetValue = MainActivity.UserStats.indexAssetValue.setScale(2, BigDecimal.ROUND_HALF_UP);
                                indexInvestedTextview.setText("Invested: $" + indexInvested);
                                indexAssetTextView.setText("Asset Value: $" + MainActivity.UserStats.indexAssetValue);

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
                                String val = String.valueOf(amount.getText());
                                BigDecimal fVal = new BigDecimal(Float.parseFloat(val));


                                if (Float.parseFloat(val) <= MainActivity.UserStats.realEstateAssetValue.floatValue()) {
                                    realEstateInvested = realEstateInvested.subtract(fVal).signum() < 0 ? BigDecimal.ZERO : realEstateInvested.subtract(fVal);
                                    MainActivity.UserStats.realEstateAssetValue = MainActivity.UserStats.realEstateAssetValue.subtract(fVal);
                                    MainActivity.UserStats.liquid = MainActivity.UserStats.liquid.add(fVal);
                                } else {
                                    Toast.makeText(getContext(), "Selling too much!", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                realEstateInvested = realEstateInvested.setScale(2, BigDecimal.ROUND_HALF_UP);
                                MainActivity.UserStats.realEstateAssetValue = MainActivity.UserStats.realEstateAssetValue.setScale(2, BigDecimal.ROUND_HALF_UP);
                                realEstateInvestedTextview.setText("Invested: $" + realEstateInvested);
                                realEstateAssetTextView.setText("Asset Value: $" + MainActivity.UserStats.realEstateAssetValue);

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
                                String val = String.valueOf(amount.getText());
                                BigDecimal fVal = new BigDecimal(Float.parseFloat(val));


                                if (Float.parseFloat(val) <= MainActivity.UserStats.stocksAssetValue.floatValue()) {
                                    stocksInvested = stocksInvested.subtract(fVal).signum() < 0 ? BigDecimal.ZERO : stocksInvested.subtract(fVal);
                                    MainActivity.UserStats.stocksAssetValue = MainActivity.UserStats.stocksAssetValue.subtract(fVal);
                                    MainActivity.UserStats.liquid = MainActivity.UserStats.liquid.add(fVal);
                                } else {
                                    Toast.makeText(getContext(), "Selling too much!", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                stocksInvested = stocksInvested.setScale(2, BigDecimal.ROUND_HALF_UP);
                                MainActivity.UserStats.stocksAssetValue = MainActivity.UserStats.stocksAssetValue.setScale(2, BigDecimal.ROUND_HALF_UP);
                                stocksInvestedTextview.setText("Invested: $" + stocksInvested);
                                stocksAssetTextView.setText("Asset Value: $" + MainActivity.UserStats.stocksAssetValue);

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
                                String val = String.valueOf(amount.getText());
                                BigDecimal fVal = new BigDecimal(Float.parseFloat(val));



                                if (Float.parseFloat(val) <= MainActivity.UserStats.cryptoAssetValue.floatValue()) {
                                    cryptoInvested = cryptoInvested.subtract(fVal).signum() < 0 ? BigDecimal.ZERO : cryptoInvested.subtract(fVal);
                                    MainActivity.UserStats.cryptoAssetValue = MainActivity.UserStats.cryptoAssetValue.subtract(fVal);
                                    MainActivity.UserStats.liquid = MainActivity.UserStats.liquid.add(fVal);
                                } else {
                                    Toast.makeText(getContext(), "Selling too much!", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                cryptoInvested = cryptoInvested.setScale(2, BigDecimal.ROUND_HALF_UP);
                                MainActivity.UserStats.cryptoAssetValue = MainActivity.UserStats.cryptoAssetValue.setScale(2, BigDecimal.ROUND_HALF_UP);
                                cryptoInvestedTextview.setText("Invested: $" + cryptoInvested);
                                cryptoAssetTextView.setText("Asset Value: $" + MainActivity.UserStats.cryptoAssetValue);

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
                MainActivity.UserStats.liquid = MainActivity.UserStats.liquid.add(MainActivity.UserStats.savingsAssetValue);
                savingsInvested = savingsInvested.subtract(MainActivity.UserStats.savingsAssetValue).signum() < 0 ? BigDecimal.ZERO : savingsInvested.subtract(MainActivity.UserStats.savingsAssetValue).setScale(2, BigDecimal.ROUND_HALF_UP);;
                savingsAssetTextView.setText("Asset Value: $" + BigDecimal.ZERO.toString());
                savingsInvestedTextview.setText("Invested: $" + savingsInvested);
                MainActivity.UserStats.savingsAssetValue = BigDecimal.ZERO;
            }
        });

        indexSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.UserStats.liquid = MainActivity.UserStats.liquid.add(MainActivity.UserStats.indexAssetValue);
                indexInvested = indexInvested.subtract(MainActivity.UserStats.indexAssetValue).signum() < 0 ? BigDecimal.ZERO : indexInvested.subtract(MainActivity.UserStats.indexAssetValue).setScale(2, BigDecimal.ROUND_HALF_UP);;
                indexAssetTextView.setText("Asset Value: $" + BigDecimal.ZERO.toString());
                indexInvestedTextview.setText("Invested: $" + indexInvested);
                MainActivity.UserStats.indexAssetValue = BigDecimal.ZERO;
            }
        });

        realEstateSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.UserStats.liquid = MainActivity.UserStats.liquid.add(MainActivity.UserStats.realEstateAssetValue);
                realEstateInvested = realEstateInvested.subtract(MainActivity.UserStats.realEstateAssetValue).signum() < 0 ? BigDecimal.ZERO : realEstateInvested.subtract(MainActivity.UserStats.realEstateAssetValue).setScale(2, BigDecimal.ROUND_HALF_UP);;
                realEstateAssetTextView.setText("Asset Value: $" + BigDecimal.ZERO.toString());
                realEstateInvestedTextview.setText("Invested: $" + realEstateInvested);
                MainActivity.UserStats.realEstateAssetValue = BigDecimal.ZERO;
            }
        });

        stocksSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.UserStats.liquid = MainActivity.UserStats.liquid.add(MainActivity.UserStats.stocksAssetValue);
                stocksInvested = stocksInvested.subtract(MainActivity.UserStats.stocksAssetValue).signum() < 0 ? BigDecimal.ZERO : stocksInvested.subtract(MainActivity.UserStats.stocksAssetValue).setScale(2, BigDecimal.ROUND_HALF_UP);;
                stocksAssetTextView.setText("Asset Value: $" + BigDecimal.ZERO.toString());
                stocksInvestedTextview.setText("Invested: $" + stocksInvested);
                MainActivity.UserStats.stocksAssetValue = BigDecimal.ZERO;
            }
        });

        cryptoSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.UserStats.liquid = MainActivity.UserStats.liquid.add(MainActivity.UserStats.cryptoAssetValue);
                Boolean bool = (cryptoInvested.subtract(MainActivity.UserStats.cryptoAssetValue)).signum() < 0;
                cryptoInvested = bool ? BigDecimal.ZERO : cryptoInvested.subtract(MainActivity.UserStats.cryptoAssetValue).setScale(2, BigDecimal.ROUND_HALF_UP);
                cryptoAssetTextView.setText("Asset Value: $" + BigDecimal.ZERO.toString());
                cryptoInvestedTextview.setText("Invested: $" + cryptoInvested);
                MainActivity.UserStats.cryptoAssetValue = BigDecimal.ZERO;
            }
        });




        timeUpdate();
        return view;
    }

    private void updateUI() {
        indexAssetTextView.setText("Asset Value: $" + MainActivity.UserStats.indexAssetValue.setScale(2, RoundingMode.HALF_UP));
        savingsAssetTextView.setText("Asset Value: $" + MainActivity.UserStats.savingsAssetValue.setScale(2, RoundingMode.HALF_UP));
        realEstateAssetTextView.setText("Asset Value: $" + MainActivity.UserStats.realEstateAssetValue.setScale(2, RoundingMode.HALF_UP));
        stocksAssetTextView.setText("Asset Value: $" + MainActivity.UserStats.stocksAssetValue.setScale(2, RoundingMode.HALF_UP));
        cryptoAssetTextView.setText("Asset Value: $" + MainActivity.UserStats.cryptoAssetValue.setScale(2, RoundingMode.HALF_UP));

    }
    private void timeUpdate() {

        final Runnable updateUIRunnable = new Runnable() {
            public void run() {
                updateUI();
            }
        };
        class timeUpdate extends TimerTask {

            public void run() {
                MainActivity.UserStats.savingsAssetValue = MainActivity.UserStats.savingsAssetValue.multiply(new BigDecimal(1.01));
                MainActivity.UserStats.indexAssetValue = MainActivity.UserStats.indexAssetValue.multiply(new BigDecimal(.98 + Math.random() * (1.06 - .98)));
                MainActivity.UserStats.realEstateAssetValue = MainActivity.UserStats.realEstateAssetValue.multiply(new BigDecimal(.9 + Math.random() * (1.2 - .9)));
                MainActivity.UserStats.stocksAssetValue = MainActivity.UserStats.stocksAssetValue.multiply(new BigDecimal(.5 + Math.random() * (2 - .5)));
                MainActivity.UserStats.cryptoAssetValue = MainActivity.UserStats.cryptoAssetValue.multiply(new BigDecimal(Math.random() < .95 ? (Math.random() * 1.5) : (10 + Math.random() * (20 - 10))));
                BigDecimal totalAssetValue = MainActivity.UserStats.savingsAssetValue.add(MainActivity.UserStats.indexAssetValue).add(MainActivity.UserStats.realEstateAssetValue).add(MainActivity.UserStats.stocksAssetValue).add(MainActivity.UserStats.cryptoAssetValue);
                MainActivity.UserStats.netWorth = MainActivity.UserStats.liquid.add(MainActivity.UserStats.workerValue).add(totalAssetValue);

                getActivity().runOnUiThread(updateUIRunnable);
            }



        }

        Timer timer = new Timer();
        timer.schedule(new timeUpdate(), 0, 10000);
    }
}
