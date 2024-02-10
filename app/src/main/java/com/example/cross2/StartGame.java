package com.example.cross2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class StartGame extends AppCompatActivity {

    TableLayout tbl;
    char arr[][] = new char[3][3];
    int step = 0;
    int endGame = 0;


    Button b1, b2, b3, b4, b5, b6, b7, b8, b9;
    Button resultsBtn;
    Button btn;

    Button arrBt[][] = new Button[3][3];


    boolean twoPlayerGame = false;

    int win() {

        Button bt1, bt2, bt3;
        for(int  i = 0; i < 3; i++) {
            bt1 = findViewById(getResources().getIdentifier("c"+(i * 3 + 1), "id", getPackageName()));
            bt2 = findViewById(getResources().getIdentifier("c"+(i * 3 + 2), "id", getPackageName()));
            bt3 = findViewById(getResources().getIdentifier("c"+(i * 3 + 3), "id", getPackageName()));
            if(bt1.getText().equals(bt2.getText()) && bt1.getText().equals(bt3.getText()) && !bt1.getText().equals("")) return bt2.getText().equals("x") ? -1 : 1;
        }

        for(int  i = 0; i < 3; i++) {
            bt1 = findViewById(getResources().getIdentifier("c"+(1 + i), "id", getPackageName()));
            bt2 = findViewById(getResources().getIdentifier("c"+(4 + i), "id", getPackageName()));
            bt3 = findViewById(getResources().getIdentifier("c"+(7 + i), "id", getPackageName()));
            if(bt1.getText().equals(bt2.getText()) && bt1.getText().equals(bt3.getText()) && !bt1.getText().equals("")) return bt2.getText().equals("x") ? -1 : 1;
        }

        bt1 = findViewById(getResources().getIdentifier("c"+(1), "id", getPackageName()));
        bt2 = findViewById(getResources().getIdentifier("c"+(5), "id", getPackageName()));
        bt3 = findViewById(getResources().getIdentifier("c"+(9), "id", getPackageName()));
        if(bt1.getText().equals(bt2.getText()) && bt1.getText().equals(bt3.getText()) && !bt1.getText().equals("")) return bt2.getText().equals("x") ? -1 : 1;


        bt1 = findViewById(getResources().getIdentifier("c"+(3), "id", getPackageName()));
        bt2 = findViewById(getResources().getIdentifier("c"+(5), "id", getPackageName()));
        bt3 = findViewById(getResources().getIdentifier("c"+(7), "id", getPackageName()));
        if(bt1.getText().equals(bt2.getText()) && bt1.getText().equals(bt3.getText()) && !bt1.getText().equals("")) return bt2.getText().equals("x") ? -1 : 1;

        if(draw()) return 2;

        return 0;
    }

    boolean draw() {
        Button bt1;
        for(int i = 1; i <= 9; i++) {
            bt1 = findViewById(getResources().getIdentifier("c"+i, "id", getPackageName()));
            if(bt1.getText().equals("")) return false;
        }
        return true;
    }

    void isEndGame() {
//        return endGame;
        if(endGame != 0) {
            Intent intent1 = new Intent(StartGame.this, FinishedGame.class);
            intent1.putExtra("winner", endGame == 2 ? "DRAW" : endGame == -1 ? "x" : "0");
            startActivity(intent1);

        }
    }

    int isEndGameForMinMax(char[][] board) {

        for(int i = 0; i < 3; i++) {
            if(board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                return board[i][1] == 'x' ? -1 : 1;
            }
        }
        for(int i = 0; i < 3; i++) {
            Log.w("If: ", "2");
            if(board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
                return board[1][i] == 'x' ? -1 : 1;
            }
        }

        if(board[0][0] == board[1][1] && board[0][0] == board[2][2]  && board[1][1] != ' ') {
            Log.w("If: ", "3");
            return board[1][1] == 'x' ? -1 : 1;
        }
        if(board[0][2] == board[1][1] && board[2][0] == board[1][1]  && board[1][1] != ' ') {
            Log.w("If: ", "4");
            return board[1][1] == 'x' ? -1 : 1;
        }

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i][j] == ' ') return 0;
            }
        }

        return 2;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        Button btnMod = findViewById(R.id.button3);
        btnMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twoPlayerGame = !twoPlayerGame;
                if(twoPlayerGame) btnMod.setText("Bot Game");
                else btnMod.setText("Play with friend");
                Intent int56 = new Intent(StartGame.this, StartGame.class);
                startActivity(int56);

            }
        });



        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                arr[i][j] = ' ';
            }
        }

        tbl = findViewById(R.id.gamePlace);

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                String a = "c" + (i * 3 + j + 1);

                Button btn = findViewById(getResources().getIdentifier(a, "id", getPackageName()));
                final int q1 = i;
                final int q2 = j;
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(!btn.getText().equals("")) return;
                        arr[q1][q2] = 'x';
                        if(twoPlayerGame) {
                            btn.setText(step % 2 == 0 ? "x" : "0");
                            step++;
                        } else {
                            btn.setText("x");
                        }
                        endGame = isEndGameForMinMax(arr);
                        isEndGame();


                        if(!twoPlayerGame) {
                            Pair<Integer, Integer> move = get_computer_position(arr);
                            Log.w("Message: ", String.valueOf(move));

                            if (move != null) {
                                int x = move.first;
                                int y = move.second;
                                arr[y][x] = '0';
                                int st_int = y * 3 + x + 1;
                                String st = String.valueOf(st_int);
                                Button btnnn = findViewById(getResources().getIdentifier("c" + st, "id", getPackageName()));
                                btnnn.setText("0");
                            }
                        }

                        endGame = isEndGameForMinMax(arr);
                        isEndGame();
                    }
                });

                arrBt[i][j] = btn;
            }
        }


        resultsBtn = findViewById(R.id.btn_res);


        resultsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartGame.this, ResultsScreen.class);
                startActivity(intent);
            }
        });

        btn = findViewById(R.id.btn_logout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.currentUser = "";
                Intent intent = new Intent(StartGame.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    Pair<Integer, Integer> get_computer_position(char[][] field) {
        Pair<Integer, Integer> move = null;


        long best_score = Math.round(Double.NEGATIVE_INFINITY);

        char[][] board = new char[3][3];

        for(int j = 0; j < 3; j++) {
            for(int i = 0; i < 3; i++) {
                board[j][i] = field[j][i];
            }
        }

        for(int i = 0; i < 3; i++) {
            Log.w("Arr:", board[i][0] + " " + board[i][1] + " " + board[i][2]);
        }

        for(int j = 0; j < 3; j++) {
            for(int i = 0; i < 3; i++) {
                if(board[j][i] == ' ') {
                    board[j][i] = '0';
                    long score = enemyStep(board, 0, false);
                    board[j][i] = ' ';
                    if(score >= best_score) {
                        best_score = score;
                        move = new Pair<>(i, j);
                    }
                }
            }
        }

        return move;
    }

    long enemyStep(char[][] board, int depth, boolean is_ai_turn) {

//        Log.w("Depth:", depth + "");
//        for (int i = 0; i < 3; i++) {
//            Log.w("Arr:", board[i][0] + " " + board[i][1] + " " + board[i][2]);
//        }

        int isEndGameForMinMaxx = isEndGameForMinMax(board);
        if(isEndGameForMinMaxx == -1) {
            Log.w("Score: :", "-100");
          return -100;
        } else if(isEndGameForMinMaxx == 1) {
            Log.w("Score: :", "100");
          return 100;
        } else if(isEndGameForMinMaxx == 2) {
            Log.w("Score: :", "0");
          return 0;
        }

        long best_score = 0;
        if(is_ai_turn) {
            best_score = Math.round(Double.NEGATIVE_INFINITY);

            for(int j = 0; j < 3; j++) {
                for(int i = 0; i < 3; i++) {
                    if(board[j][i] == ' ') {
                        board[j][i] = '0';
                        long score = enemyStep(board, depth + 1, false);
                        board[j][i] = ' ';
                        best_score = Math.max(best_score, score);
                    }
                }
            }
        } else {
            best_score = Math.round(Double.POSITIVE_INFINITY);

            for(int j = 0; j < 3; j++) {
                for(int i = 0; i < 3; i++) {
                    if(board[j][i] == ' ') {
                        board[j][i] = 'x';
                        long score = enemyStep(board, depth + 1, true);
                        board[j][i] = ' ';
                        best_score = Math.min(best_score, score);
                    }
                }
            }
        }


//        TextView tv = findViewById(R.id.textView);
//        tv.setText((int) best_score);
        Log.w("Message: ", String.valueOf(best_score));
        return best_score;
    }

    /*

def minimax(board, depth, is_ai_turn):
    if is_win(computer_char, board):
        return scores[computer_char]
    if is_win(user_char, board):
        return scores[user_char]
    if is_draw(board):
        return scores['draw']

    if is_ai_turn:
        # выбираем ход который нам выгодней
        best_score = - sys.maxsize
        for y in range(3):
            for x in range(3):
                if board[y][x] == EMPTY_CHAR:
                    board[y][x] = computer_char
                    score = minimax(board, depth + 1, USER_TURN)
                    board[y][x] = EMPTY_CHAR
                    best_score = max(best_score, score)
    else:
        # противник выбирает ход который нам не выгоден
        best_score = sys.maxsize
        for y in range(3):
            for x in range(3):
                if board[y][x] == EMPTY_CHAR:
                    board[y][x] = user_char
                    score = minimax(board, depth + 1, AI_TURN)
                    board[y][x] = EMPTY_CHAR
                    best_score = min(best_score, score)
    return best_score
     */

}